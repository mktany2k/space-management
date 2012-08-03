package com.scwcd.process.ajax.core;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;


public class CommandPrintSession extends AbstractServletCommand {

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) {
		final ApplicationSession appSession = getSession(request);
		System.out.println("appSession: " + appSession);
		//debugRequest();

		return null;
	}
}