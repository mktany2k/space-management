package com.scwcd.process.login.core;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;


public class CommandLogout extends AbstractServletCommand {

	public static final String JSP = "/WEB-INF/jsp/logout.jsp";

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) {
		final ApplicationSession appSession = getSession(request);
		appSession.destroy();

		return JSP;
	}
}