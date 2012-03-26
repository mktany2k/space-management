package com.scwcd.framework.business.core;


import com.scwcd.framework.command.core.ApplicationSession;


public abstract class AbstractBusinessDelegate implements BusinessDelegate {

	private ApplicationSession session;

	private BusinessService service;

	protected AbstractBusinessDelegate(final ApplicationSession session, final BusinessService service) {
		this.session = session;
		this.service = service;
	}

	protected final AbstractBusinessService<?> getService() {
		DefaultServiceLocator locator = DefaultServiceLocator.getInstance();
		return (AbstractBusinessService<?>) locator.locate(this);
	}

	@Override
	public final BusinessService create() {
		return service;
	}

	protected final ApplicationSession getSession() {
		return session;
	}
}