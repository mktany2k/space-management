package com.scwcd.process.ajax.core;


import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.enterprise.sql.util.SqlUtility;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;
import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommandSelectFloor extends AbstractServletCommand {

	private static final String JSP = "/WEB-INF/jsp/floors.jsp";

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {

		final ApplicationSession appSession = getSession(request);
		final AjaxBusinessDelegate bizDelegate = new AjaxBusinessDelegate(appSession);

		final Project project = (Project) appSession.getProject();
		request.setAttribute("pid", project.getProjectId());

		bizDelegate.setParameters(request.getParameterValues("planId[]"));
		final Set<Plan> plans = bizDelegate.getPlans();

		request.setAttribute("floors", SqlUtility.convertToDelimited(plans));

		prepareResponse(response);

		return JSP;
	}
}