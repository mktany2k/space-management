package com.scwcd.enterprise.servlet.listener;


import com.scwcd.framework.deployment.core.DeploymentManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


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