package com.scwcd.process.plan.core;


import java.util.HashSet;
import java.util.Set;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.business.core.AbstractBusinessDelegate;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.command.core.ApplicationSession;


class PlanBusinessDelegate extends AbstractBusinessDelegate {

	protected PlanBusinessDelegate(final ApplicationSession session, final AbstractBusinessService<?> service) {
		super(session, service);
	}

	protected PlanBusinessDelegate(final ApplicationSession session) {
		this(session, null);
	}

	Project selectProject(final int projectId) {
		final ServiceOpenProject service = (ServiceOpenProject) getService();
		service.setProjectId(projectId);
		service.perform();
		return service.getOutput();
	}

	Set<Plan> selectPlans(final Set<Plan> plans, final Integer[] planIds) {
		final Set<Plan> floors = new HashSet<Plan>();
		for (int planId : planIds) {
			for (Plan plan : plans) {
				if (plan.getPlanId() == planId) {
					floors.add(plan);
					break;
				}
			}
		}
		return floors;
	}
	
	int createProject(final String name, final String description, final String parser, final String unit, final String levels) {
		// persist project into storage
		final ServiceNewProject service = (ServiceNewProject) getService();
		service.setName(name);
		service.setDescription(description);
		service.setParser(parser);
		service.setUnit(unit);
		service.perform();
		final int projectId = service.getOutput();
		
		return projectId;
	}
}