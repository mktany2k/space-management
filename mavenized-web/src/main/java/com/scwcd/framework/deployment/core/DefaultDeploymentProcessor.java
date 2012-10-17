package com.scwcd.framework.deployment.core;


import com.scwcd.framework.deployment.xslt.XsltTemplate;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultDeploymentProcessor implements Processor {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDeploymentProcessor.class);

	private XsltTemplate xsltTemplate;

	private String projectPath;

	private int projectId;

	private final StringBuilder outputPath = new StringBuilder();

	private Map<String, Integer> hashtable;

	public void setXsltTemplate(final XsltTemplate xsltTemplate) {
		this.xsltTemplate = xsltTemplate;
	}

	public void setProjectPath(final String projectPath) {
		this.projectPath = projectPath;
		outputPath.append(projectPath).append(File.separator)
				  .append(WebContext.DATA_PATH).append(File.separator)
				  .append(projectId).append(File.separator)
				  .append("output");
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectId(final int projectId) {
		this.projectId = projectId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setHashtable(final Map<String, Integer> hashtable) {
		this.hashtable = hashtable;
	}

	@Override
	public void process(final Exchange exchange) {
		try {
			final File outputPath = new File(projectPath + WebContext.DATA_PATH 
								  + File.separator + projectId 
								  + WebContext.OUTPUT_PATH);
			outputPath.mkdir();
	
			final GenericFile<?> genericFile = (GenericFile<?>) exchange.getIn().getBody();
			final File file = (File) genericFile.getFile();
			final String filename = file.getName();
			final String xsl = xsltTemplate.getFilename();

			// allow file manager to validate if Plan needs to be created in DBMS
			final SvgFileManager fileManager = new SvgFileManager();
			fileManager.validate(hashtable.containsKey(filename));
			fileManager.insert(filename, projectId, hashtable);
	
			final TransformerFactory factory = TransformerFactory.newInstance();
			final Transformer transformer = factory.newTransformer(new StreamSource(projectPath + "/xsl/" + xsl));

			final File outputFile = new File(projectPath + WebContext.DATA_PATH 
								  + projectId + WebContext.OUTPUT_PATH 
								  + File.separator + filename);
			outputFile.createNewFile();
			
			final int planId = hashtable.get(outputFile.getName());
			final StreamResult output = new StreamResult(outputFile);
			final StreamSource xmlsource = new StreamSource(file);

			transformer.setParameter("projectId", projectId);
			transformer.setParameter("planId", planId);

			final long start = System.currentTimeMillis();
			transformer.transform(xmlsource, output);
			final long end = System.currentTimeMillis();
			logging(file, start, end);
		} catch (final TransformerFactoryConfigurationError | IOException | TransformerException e) {
			e.printStackTrace();
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
}