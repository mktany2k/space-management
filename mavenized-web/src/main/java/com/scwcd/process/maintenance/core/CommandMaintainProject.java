package com.scwcd.process.maintenance.core;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;


public class CommandMaintainProject extends AbstractServletCommand {

	public static final String JSP = "/WEB-INF/jsp/maintenance.jsp";

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		final ApplicationSession appSession = getSession(request);
		final MaintenanceBusinessDelegate bizDelegate = new MaintenanceBusinessDelegate(appSession, new ServiceListLots());

		// get project id
		final Project project = (Project) appSession.getProject();
		final int projectId = project.getProjectId();
		request.setAttribute("projectId", projectId);

		// retrieve all lots related to the project
		final Set<Plan> plans = project.getPlans();
		final List<Plan> _plans = new ArrayList<>(plans);
		final int[] planIds = new int[_plans.size()];
		for (int i = 0; i < _plans.size(); i++) {
			final Plan plan = _plans.get(i);
			planIds[i] = plan.getPlanId();
		}

		final List<Lot> lots = bizDelegate.getLots(projectId, planIds);
		request.setAttribute("lots", lots);

		// unit of measurement
		request.setAttribute("unit", project.getUnit());

		appSession.setCache(lots);

		return JSP;
	}
}