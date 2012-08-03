package com.scwcd.process.login.core;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;


public class CommandPostLogin extends AbstractServletCommand {

	private static final String JSP = "/WEB-INF/jsp/menu.jsp";

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) {
		final ApplicationSession appSession = getSession(request);
		final LoginBusinessDelegate bizDelegate = new LoginBusinessDelegate(appSession, new ServiceListProjects());

		final List<Project> projects = bizDelegate.getProjects();
		request.setAttribute("projects", projects);
		request.setAttribute("SESSION_USER", ApplicationSession.SESSION_USER);

		return JSP;
	}
}