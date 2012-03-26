package com.scwcd.framework.business.core;


public class DefaultServiceLocator {

	private final static DefaultServiceLocator INSTANCE = new DefaultServiceLocator();

	private DefaultServiceLocator() {
	}

	public static DefaultServiceLocator getInstance() {
		return INSTANCE;
	}

	public BusinessService locate(final BusinessDelegate businessDelegate) {
		return businessDelegate.create();
	}
}