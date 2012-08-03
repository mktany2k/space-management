package com.scwcd.process.plan.core;


import com.scwcd.enterprise.sql.dao.DAOProject;
import com.scwcd.enterprise.sql.dao.ProjectParameter;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.sql.core.DAOFactory;


class ServiceOpenProject extends AbstractBusinessService<Project> {

	private int projectId;

	void setProjectId(final int projectId) {
		this.projectId = projectId;
	}

	@Override
	public void perform() {
		DAOFactory factory = DAOFactory.getInstance();
		DAOProject dao = (DAOProject) factory.getInstance(DAOProject.class);
		Project project = dao.doSelect(new ProjectParameter(projectId));
		setOutput(project);
	}
}