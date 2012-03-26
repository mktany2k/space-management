package com.scwcd.enterprise.servlet.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.scwcd.framework.deployment.core.DeploymentManager;


public abstract class FrameworkListener implements ServletContextListener {

	@Override
	public final void contextInitialized(final ServletContextEvent sce) {
		// framework objects registration
		init(sce);

		final DeploymentManager manager = DeploymentManager.getInstance();
		manager.init();
		manager.start();
	}

	public abstract void init(final ServletContextEvent sce);

	@Override
	public final void contextDestroyed(final ServletContextEvent sce) {
	}
}