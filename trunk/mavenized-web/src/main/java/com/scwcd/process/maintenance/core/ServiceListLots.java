package com.scwcd.process.maintenance.core;


import java.util.List;
import com.scwcd.enterprise.sql.dao.DAOLot;
import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.sql.core.DAOFactory;


class ServiceListLots extends AbstractBusinessService<List<Lot>> {

	private int projectId;

	private int[] planIds;

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setPlanIds(int[] planIds) {
		this.planIds = planIds;
	}

	public int[] getPlanIds() {
		return planIds;
	}

	@Override
	public void perform() {
		DAOFactory daoFactory = DAOFactory.getInstance();
		DAOLot dao = (DAOLot) daoFactory.getInstance(DAOLot.class);
		List<Lot> lots = dao.doList(projectId, planIds);
		
		setOutput(lots);
	}
}