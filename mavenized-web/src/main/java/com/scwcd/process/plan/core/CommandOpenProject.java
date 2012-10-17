package com.scwcd.process.plan.core;


import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.component.view.core.View;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.enterprise.sql.hbm.User;
import com.scwcd.enterprise.sql.util.SqlUtility;
import com.scwcd.framework.business.util.JSONResponseUtility;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;


public class CommandOpenProject extends AbstractServletCommand {

	private static final String JSP = "/WEB-INF/jsp/plan.jsp";

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) {
		final ApplicationSession appSession = getSession(request);
		final PlanBusinessDelegate planBizDelegate = new PlanBusinessDelegate(appSession, new ServiceOpenProject());

		// set miscellaneous attributes
		request.setAttribute("SESSION_USER", ApplicationSession.SESSION_USER);

		// set Project
		final int _projectId = getProjectId(request);
		final Project project = planBizDelegate.selectProject(_projectId);
		request.setAttribute("project", project);
		request.setAttribute("pid", project.getProjectId());
		appSession.setProject(project);

		// set Set<Plan>
		final Set<Plan> plans = project.getPlans();
		final Set<Integer> planIds = appSession.getPlanIds();
		final Set<Plan> floors = PlanBusinessDelegate.selectPlans(plans, planIds.toArray(new Integer[planIds.size()]));
		request.setAttribute("floors", SqlUtility.convertToDelimited(floors));

		// set Function
		final ViewBusinessDelegate viewBizDelegate = new ViewBusinessDelegate(appSession, new ServiceSelectView());
		final List<View> views = viewBizDelegate.selectViews();
		request.setAttribute("views", views);

		// build gState
		final User user = (User) appSession.getUser();
		final String state = JSONResponseUtility.buildState(request, user.getUsername());
		request.setAttribute(JSONResponseUtility.STATE, state);

		prepareResponse(response);

		return JSP;
	}

	protected int getProjectId(final HttpServletRequest request) {
		final String projectId = request.getParameter("projectId");
		if (projectId == null) {
			final ApplicationSession appSession = getSession(request);
			final Project project = (Project) appSession.getProject();
			return project.getProjectId();
		}
		
		return Integer.parseInt(projectId);
	}
}