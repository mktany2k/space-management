package com.scwcd.framework.deployment.parser;

import com.google.common.collect.Maps;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.scwcd.enterprise.sql.dao.DAOLot;
import com.scwcd.enterprise.sql.dao.LotParameter;
import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.enterprise.sql.hbm.LotKey;
import com.scwcd.framework.deployment.core.WebContextManager;
import com.scwcd.framework.sql.core.DAOFactory;

public class Visio2007Parser extends SvgDefaultParser {

    private static final SimpleNamespaceContext namespace = new SimpleNamespaceContext();
    private static final String CSS = "st1";
    private static final String DEFAULT_LOT_DESC = "Information not available";
    private static final String DEFAULT_LOT_IMG = "image-not-available.png";
    private static final String MAP_PREFIX = "m_";

    @Override
    public void parse(final Object... object) {
        // initialize variables
        final Document document = (Document) object[0];
        final int projectId = (Integer) object[1];
        final int planId = (Integer) object[2];
        final Set<Lot> generated = new HashSet<>();
        final Map<String, Lot> map = new HashMap<>();

        // configure document header, i.e. <!-- comments -->, <!DOCTYPE/>
        configureHeader(document);

        // configure root node, i.e. <svg/>
        configureSvgNode(document, projectId, planId);

        // configure title node, i.e. <title/>
        configureTitleNode(document);

        // configure shape node, i.e. <rect/>, <path/>, <group/>
        configureLot(document, projectId, planId, generated);

        // before continue to configure CSS node, retrieve lot information from storage and put into map
        mapLotId(projectId, planId, generated, map);

        // configure style node, i.e. <style/>
        configureCss(document, projectId, planId, map);
    }

    static void configureHeader(final Document document) {
        // remove DOCTYPE and comments
        while (true) {
            final Node node = document.getFirstChild();
            final short type = node.getNodeType();

            if (type == Node.ELEMENT_NODE) {
                break;
            }

            document.removeChild(node);
        }
    }

    static void configureSvgNode(final Document document, final int projectId, final int planId) {
        // configure svg element
        final String svgId = "floor-" + projectId + "-" + planId;
        final NodeList nodes = document.getElementsByTagName("svg");
        for (int i = 0; i < nodes.getLength(); i++) {
            final Element element = (Element) nodes.item(i);
            element.setAttribute("id", svgId);
            element.setAttribute("width", "100%");
            element.setAttribute("height", "100%");
            element.setAttribute("viewBox", "0 0 2400 1400");
        }
    }

    static void configureTitleNode(final Document document) {
        /*
         * Remove all title tags manually. NOTE: unable to use
         * XPath to retrieve all 'title' tags - reason: unknown
         */
        while (true) {
            final NodeList nodes = document.getElementsByTagName("title");
            if (nodes.getLength() == 0) {
                break;
            }

            for (int i = 0; i < nodes.getLength(); i++) {
                final Node node = nodes.item(i);
                node.getParentNode().removeChild(node);
            }
        }
    }

    static void configureLot(final Document document, final int projectId, final int planId, final Set<Lot> generated) {
        // configure id value for each lot
        final String plotId = "plot-" + projectId + "-" + planId + "-";
        final String lotId = "lot-" + projectId + "-" + planId + "-";
        final XPathFactory factory = XPathFactory.newInstance();
        final XPath xPath = factory.newXPath();
        xPath.setNamespaceContext(namespace);

        try {
            final NodeList nodes = (NodeList) xPath.evaluate("//*[@class='" + CSS + "']", document, XPathConstants.NODESET);
            for (int i = 1; i <= nodes.getLength(); i++) {
                final Element element = (Element) nodes.item(i - 1);
                final Node groupNode = element.getParentNode().getParentNode();
                final NamedNodeMap attributes = groupNode.getAttributes();
                final Node attribute = attributes.getNamedItem("id");
                attribute.setNodeValue(plotId + i);
                element.setAttribute("id", lotId + i);

                final Lot lot = new Lot();
                lot.setLotKey(new LotKey(projectId, planId, i)); // Lot.LotID is generated
                lot.setName(lotId + i);
                lot.setUpdatedBy("SYSTEM");

                generated.add(lot);
            }
        } catch (final XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    static void mapLotId(final int projectId, final int planId, final Set<Lot> generated, final Map<String, Lot> map) {
        final DAOFactory factory = DAOFactory.getInstance();
        final DAOLot dao = (DAOLot) factory.getInstance(DAOLot.class);

        final List<Lot> queried = dao.doList(new LotParameter(projectId, planId));
        final String lotId = "lot-" + projectId + "-" + planId + "-";
        for (final Lot lot : queried) {
            map.put(MAP_PREFIX + lotId + lot.getLotKey().getLotId(), lot);
        }

        generated.removeAll(queried);

        final String projectPath = WebContextManager.getWebContext().getProjectPath() + File.separator
                + "meta" + File.separator
                + projectId + File.separator
                + planId;
        final Date now = new Date();
        for (final Lot lot : generated) {
            lot.setDescription(DEFAULT_LOT_DESC);
            lot.setImage(DEFAULT_LOT_IMG);
            lot.setDtCreated(now);
            lot.setDtModified(now);
            dao.doInsert(lot);

            map.put(MAP_PREFIX + lotId + lot.getLotKey().getLotId(), lot);
            final File file = new File(projectPath + File.separator + lot.getLotKey().hashCode());
            file.mkdirs();
        }
    }

    static void configureCss(final Document document, final int projectId, final int planId, final Map<String, Lot> map) {
        // configure css for whole document
        final String replacement = ".css-" + projectId + "-" + planId + "-";
        final XPathFactory factory = XPathFactory.newInstance();
        final XPath xPath = factory.newXPath();
        xPath.setNamespaceContext(namespace);

        try {
            final NodeList nodes = (NodeList) xPath.evaluate("//*[@type='text/css']", document, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                final Node node = nodes.item(i);
                String textContent = node.getTextContent();
                textContent = textContent.replaceAll("\\.st", replacement);
                node.setTextContent(textContent);

                textContent = textContent.replaceAll("\\s*\\{.*\\}\\s*", "").trim();
                final StringTokenizer st = new StringTokenizer(textContent, ".");
                while (st.hasMoreTokens()) {
                    final String cssToken = st.nextToken();
                    final String cssId = cssToken.replaceAll("css-(\\d)*-(\\d)*-", "");
                    final NodeList _nodes = (NodeList) xPath.evaluate("//*[@class='st" + cssId + "']", document, XPathConstants.NODESET);
                    for (int k = 0; k < _nodes.getLength(); k++) {
                        final Element element = (Element) _nodes.item(k);
                        element.setAttribute("class", cssToken);

                        // only for the first CSS style (i.e - st1)
                        if (1 == Integer.parseInt(cssId)) {
                            element.setAttribute("onmouseover", "javascript: hoverSvgLot(evt, this);");
                            element.setAttribute("onmouseout", "javascript: hoverSvgLot(evt, this);");
                            element.setAttribute("onclick", "javascript: selectSvgLot(evt, this);");
                            element.setAttribute("style", "cursor: pointer;");

                            final String lotId = element.getAttribute("id");
                            final Lot lot = map.get(MAP_PREFIX + lotId);
                            element.setAttribute("l-title", lot.getName());
                            element.setAttribute("l-desc", lot.getSummary());
                        }
                    }
                }
            }
        } catch (final XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private static final class SimpleNamespaceContext implements NamespaceContext {

        private Map<String, String> namespace = Maps.newHashMap();

        private SimpleNamespaceContext() {
            namespace.put("v", "http://schemas.microsoft.com/visio/2003/SVGExtensions/");
            namespace.put("xlink", "http://www.w3.org/1999/xlink");
        }

        @Override
        public String getNamespaceURI(final String prefix) {
            return namespace.get(prefix);
        }

        @Override
        public String getPrefix(final String namespaceURI) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Iterator<?> getPrefixes(final String namespaceURI) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}