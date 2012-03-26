package com.scwcd.framework.deployment.core;


public class WebContextManager {

	private static WebContext wc;

	public static WebContext newWebContext() {
		return new WebContext();
	}

	public static void setWebContext(final WebContext _wc) {
		wc = _wc;
	}

	public static WebContext getWebContext() {
		return wc;
	}
}