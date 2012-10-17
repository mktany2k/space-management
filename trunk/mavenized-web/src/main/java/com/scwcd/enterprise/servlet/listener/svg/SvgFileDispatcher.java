package com.scwcd.enterprise.servlet.listener.svg;

import com.google.common.base.Stopwatch;
import com.scwcd.framework.business.handler.IParser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

class SvgFileDispatcher {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SvgFileDispatcher.class);
    
    static void dispatch(final IParser parser, final Document document, final File outputFile,
            final int projectId, final Map<String, Integer> hashtable) {
        
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder logMessage = new StringBuilder();
                logMessage.append(outputFile.getName());
                
                Stopwatch stopwatch = new Stopwatch();
                try {
                    final int planId = hashtable.get(outputFile.getName());
                    parser.parse(document, projectId, planId);
                    
                    if (outputFile.isFile() && outputFile.exists()) {
                        outputFile.delete();
                    }
                    
                    final boolean result = outputFile.createNewFile();
                    if (result) {
                        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                            final OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                            final StreamResult sr = new StreamResult(osw);
                            final DOMSource source = new DOMSource(document);

                            // transform document to file
                            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            final Transformer transformer = transformerFactory.newTransformer();
                            transformer.transform(source, sr);

                            // close and write to disk
                            fos.flush();
                        }
                        
                        stopwatch.stop();
                        logMessage.append(" successfully deployed in ");
                        logMessage.append(stopwatch.elapsedTime(TimeUnit.MICROSECONDS));
                        logMessage.append(" ms");
                    }
                } catch (final IOException e) {
                    logMessage.append(" failed to deploy; IOException[");
                    logMessage.append(e.getMessage());
                    logMessage.append("]");
                } catch (final TransformerConfigurationException e) {
                    logMessage.append(" failed to deploy; TransformerConfigurationException[");
                    logMessage.append(e.getMessage());
                    logMessage.append("]");
                } catch (final TransformerException e) {
                    logMessage.append(" failed to deploy; TransformerException[");
                    logMessage.append(e.getMessage());
                    logMessage.append("]");
                } finally {
                    LOGGER.info(logMessage.toString());
                }
            }
        });
        thread.start();
    }
}
