package com.scwcd.process.ajax.core;


import com.scwcd.enterprise.sql.dao.DAOLot;
import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.sql.core.DAOFactory;


class ServiceUpdateLot extends AbstractBusinessService<Lot> {

	private Lot lot;
	
	void setLot(final Lot lot) {
		this.lot = lot;
	}

	@Override
	public void perform() {
		// updates storage
		final DAOFactory factory = DAOFactory.getInstance();
		final DAOLot dao = (DAOLot) factory.getInstance(DAOLot.class);
		dao.doUpdate(lot);

		// redeploy svg file
		final Plan plan = lot.getPlan();
		final int projectId = plan.getProjectId();
		final String filename = plan.getFilename();
		System.out.println(projectId + ":" + filename);
		
		setRcCode(0);
	}
}