package com.scwcd.process.maintenance.core;


import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.framework.business.core.AbstractBusinessDelegate;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.command.core.ApplicationSession;
import java.util.List;


class MaintenanceBusinessDelegate extends AbstractBusinessDelegate {

	protected MaintenanceBusinessDelegate(final ApplicationSession session, final AbstractBusinessService<?> service) {
		super(session, service);
	}

	List<Lot> getLots(final int projectId, final int ... planIds) {
		final ServiceListLots service = (ServiceListLots) getService();

		service.setProjectId(projectId);
		service.setPlanIds(planIds);
		service.perform();
		
		return service.getOutput();
	}
}