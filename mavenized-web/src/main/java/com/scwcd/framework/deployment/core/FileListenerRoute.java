package com.scwcd.framework.deployment.core;


import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


public class FileListenerRoute extends RouteBuilder {
	
	private Processor processor;

	public FileListenerRoute(final Processor processor) {
		this.processor = processor;
	}

	@Override
	public void configure() throws Exception {
		final StringBuilder sb = new StringBuilder();
		sb.append("file://");
		sb.append(((DefaultDeploymentProcessor) processor).getProjectPath()).append("\\dat\\").append(((DefaultDeploymentProcessor) processor).getProjectId()).append("\\input");
		sb.append("?delay=5000");
		sb.append("&move=../processed/${date:now:yyyyMMddHHmmssSSS}-${file:onlyname}");
		sb.append("&moveFailed=../error/${date:now:yyyyMMddHHmmssSSS}-${file:onlyname}");

        from(sb.toString()).process(processor);
	}
}