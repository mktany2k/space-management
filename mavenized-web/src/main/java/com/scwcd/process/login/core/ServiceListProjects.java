package com.scwcd.process.login.core;


import java.util.List;
import com.scwcd.enterprise.sql.dao.DAOProject;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.sql.core.DAOFactory;


class ServiceListProjects extends AbstractBusinessService<List<Project>> {

	@Override
	public void perform() {
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOProject daoProject = (DAOProject) daoFactory.getInstance(DAOProject.class);
		List<Project> projects = daoProject.doList();
		setOutput(projects);
	}
}