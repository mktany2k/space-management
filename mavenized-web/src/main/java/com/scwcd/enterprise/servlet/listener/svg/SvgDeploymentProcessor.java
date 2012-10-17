package com.scwcd.enterprise.servlet.listener.svg;


import com.scwcd.framework.business.handler.IParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class SvgDeploymentProcessor implements Processor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SvgDeploymentProcessor.class);
	
	private IParser parser;

	@SuppressWarnings("unused")
	private String projectPath;

	private int projectId;
	
	private final StringBuilder outputPath = new StringBuilder();
	
	private CustomEntityResolver cer;

	private Map<String, Integer> hashtable;

	public void setParser(final IParser parser) {
		this.parser = parser;
	}

	public void setProjectPath(final String projectPath) {
		this.projectPath = projectPath;
		outputPath.append(projectPath).append(File.separator)
				  .append("floor").append(File.separator)
				  .append(projectId).append(File.separator)
				  .append("output");
		cer = new CustomEntityResolver(projectPath+ File.separator + "dtd");
	}

	public void setProjectId(final int projectId) {
		this.projectId = projectId;
	}

	public void setHashtable(final Map<String, Integer> hashtable) {
		this.hashtable = hashtable;
	}

	@Override
	public void process(final Exchange exchange) throws Exception {
		if (parser instanceof SvgDefaultParser) {
			final Message message = exchange.getIn();

			final GenericFile<?> genericFile = (GenericFile<?>) message.getBody();
			final File file = (File) genericFile.getFile();
			final String filename = file.getName();

			// allow file manager to validate if Plan needs to be created in DBMS
			final SvgFileManager fileManager = new SvgFileManager();
			fileManager.validate(hashtable.containsKey(filename));
			fileManager.insert(filename, projectId, hashtable);

			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);

			final DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setEntityResolver(cer);

			final long start = System.currentTimeMillis();
			final Document document = builder.parse(file);
			final long end = System.currentTimeMillis();
			logging(file, start, end);

			new File(outputPath.toString()).mkdir();
			final File outputFile = new File(outputPath.toString() + File.separator + filename);

			SvgFileDispatcher.dispatch(parser, document, outputFile, projectId, hashtable);
		} else {
			throw new UnsupportedOperationException("Parser not supported");
		}
	}

	private void logging(final File file, final long start, final long end) {
		final StringBuilder logMessage = new StringBuilder();
		logMessage.append(file.getName());
		logMessage.append(" successfully parsed in ");
		logMessage.append(end - start);
		logMessage.append(" ms");
		LOGGER.info(logMessage.toString());
	}

	private static final class CustomEntityResolver implements EntityResolver {

		private String dtdPath;

		private CustomEntityResolver(final String dtdPath) {
			this.dtdPath = dtdPath;
		}

		@Override
		public InputSource resolveEntity(final String publicId, final String systemId)
				throws SAXException, IOException {

			if (publicId.equals("-//W3C//DTD SVG 1.0//EN")) {
				return new InputSource(new FileInputStream(dtdPath + File.separator + "svg10.dtd"));
			}

			return new InputSource(systemId);
		}
	}
}