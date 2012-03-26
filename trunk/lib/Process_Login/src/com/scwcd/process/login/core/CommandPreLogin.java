package com.scwcd.process.login.core;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.framework.command.core.AbstractServletCommand;


public class CommandPreLogin extends AbstractServletCommand {

	private static final String JSP = "/WEB-INF/jsp/login.jsp";

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) {
		return JSP;
	}
}