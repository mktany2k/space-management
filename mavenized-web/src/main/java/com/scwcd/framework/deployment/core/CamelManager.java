package com.scwcd.framework.deployment.core;


import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultCamelContext;


class CamelManager {

    private CamelContext context = new DefaultCamelContext();

    private boolean running;

	CamelManager(final List<Processor> processors) throws Exception {
		context.setApplicationContextClassLoader(ClassLoader.getSystemClassLoader());
		for (final Processor processor : processors) {
			final FileListenerRoute route = new FileListenerRoute(processor);
	        context.addRoutes(route);
		}
	}

	void start() {
		try {
	        context.start();
			running = true;
		} catch (final Exception e) {
			running = false;
			e.printStackTrace();
		}
	}

	void stop() {
		try {
			context.stop();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			running = false;
		}
	}

	boolean isRunning() {
		return running;
	}

	void load(final Processor processor) {
		try {
			final FileListenerRoute route = new FileListenerRoute(processor);
	        context.addRoutes(route);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}