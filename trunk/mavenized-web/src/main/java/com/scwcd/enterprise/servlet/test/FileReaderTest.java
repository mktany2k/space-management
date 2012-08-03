package com.scwcd.enterprise.servlet.test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;


public class FileReaderTest {

	public static void main(final String[] args) {
		System.out.println(Integer.MAX_VALUE);

		System.out.println(100000000 * 10);

		System.out.println((42 * 100000000) + (333 * 100000) + (222 * 100) + (11));
		
		System.out.println(2147483647);
		//performTest();
	}

	static void performTest() {
		long start = System.currentTimeMillis();

		modifyXml();

		long end = System.currentTimeMillis();
		System.out.println("Processed in " + (end - start) + " ms");
	}

	static void modifyXml() {
		try {
			final InputStream in = new FileInputStream("c:\\temp\\1-floor.svg");
			final XMLInputFactory factory = XMLInputFactory.newInstance();
			final XMLStreamReader staxParser = factory.createXMLStreamReader(in);

			final OutputStream os = new FileOutputStream("c:\\temp\\1-floor-bak.svg");
			final XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
			final XMLStreamWriter staxWriter = outputFactory.createXMLStreamWriter(os, "UTF-8");
			staxWriter.setNamespaceContext(new SimpleNamespaceContext());
		    
		    staxWriter.writeStartDocument("UTF-8", "1.0");
			while (staxParser.hasNext()) {
			    int event = staxParser.next();

			    switch (event) {
			    	case XMLStreamConstants.START_ELEMENT: {
				    	final String elementName = staxParser.getLocalName();
				    	
				    	final String elementNs = staxParser.getNamespaceURI();
				    	final String elementPrefix = staxWriter.getPrefix(elementNs) == null ? "" : staxWriter.getPrefix(elementNs) + ":";
				    	staxWriter.writeStartElement(elementPrefix + elementName);
				    	
				    	if (elementName.equals("documentProperties")) {
				    		System.out.println(elementName + ", " + elementPrefix+ ", " + elementNs);
				    	}

				    	for (int i = 0; i < staxParser.getNamespaceCount(); i++) {
					    	final String nsPrefix = staxParser.getNamespacePrefix(i) == null ? "xmlns" : "xmlns:" + staxParser.getNamespacePrefix(i);
					    	final String nsUri = staxParser.getNamespaceURI(i);
					    	staxWriter.writeAttribute(nsPrefix, nsUri);
				    	}

			    		for (int i = 0; i < staxParser.getAttributeCount(); i++) {
			    			final QName qname = staxParser.getAttributeName(i);
			    			final String attrNamespace = qname.getNamespaceURI();
			    			final String attrPrefix = qname.getPrefix();
			    			final String attrLocalName = qname.getLocalPart();
			    			final String attrValue = staxParser.getAttributeValue(i);
			    			
//			    			final String attrNamespace = staxParser.getAttributeNamespace(i);
//			    			final String attrPrefix = staxParser.getAttributePrefix(i);
//			    			final String attrLocalName = staxParser.getAttributeLocalName(i);
//			    			final String attrValue = staxParser.getAttributeValue(i);
//			    			System.out.println(attrPrefix + " " + attrNamespace + " " + attrLocalName + " " + attrValue);
			    			staxWriter.writeAttribute(attrPrefix, attrNamespace, attrLocalName, attrValue);
			    		}
				    	break;
			    	}
			    	case XMLStreamConstants.CHARACTERS: {
			    		staxWriter.writeCharacters(staxParser.getText());
			    		
//			    		if (elementName.equals("style")) {
//				    		staxWriter.writeCharacters(staxParser.getText());
//			    		}
				    	break;
			    	}
			    	case XMLStreamConstants.END_ELEMENT: {
			    		staxWriter.writeEndElement();
			    		break;
			    	}
			    	case XMLStreamConstants.NAMESPACE: {
			    		final String namespaceUri = staxParser.getNamespaceURI();
			    		System.out.println(namespaceUri);
			    		break;
			    	}
			    	case XMLStreamConstants.START_DOCUMENT: {
			    		final String elementName = staxParser.getLocalName();
			    		System.out.println(elementName);
			    		break;
			    	}
			    	case XMLStreamConstants.END_DOCUMENT: {
				    	staxParser.close();
				    	staxWriter.close();
			    	}
			    }
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static final class SimpleNamespaceContext implements NamespaceContext {

		private Map<String, String> namespace = new HashMap<String, String>();

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
			for (Entry<String, String> entry : namespace.entrySet()) {
				final String nsUri = entry.getValue();
				if (namespaceURI.equals(nsUri)) {
					return entry.getKey();
				}
			}
			return null;
		}

		@Override
		public Iterator<?> getPrefixes(final String namespaceURI) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}