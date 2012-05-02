package com.scwcd.process.ajax.core;


import com.scwcd.enterprise.sql.dao.DAOLot;
import com.scwcd.enterprise.sql.dao.LotParameter;
import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.sql.core.DAOFactory;


class ServiceSelectLot extends AbstractBusinessService<Lot> {

	private int projectId;
	private int planId;
	private int lotId;

	void setProjectId(final int projectId) {
		this.projectId = projectId;
	}

	void setPlanId(final int planId) {
		this.planId = planId;
	}

	void setLotId(final int lotId) {
		this.lotId = lotId;
	}

	@Override
	public void perform() {
		final DAOLot dao = (DAOLot) DAOFactory.getInstance().getInstance(DAOLot.class);
		setOutput(dao.doSelect(new LotParameter(projectId, planId, lotId)));
	}
}