package com.scwcd.enterprise.servlet.listener.svg;


import com.scwcd.enterprise.sql.dao.DAOPlan;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.framework.sql.core.DAOFactory;
import java.util.Date;
import java.util.Map;


class SvgFileManager {

	private boolean exists;

	void validate(final boolean exists) {
		this.exists = exists;
	}

	void insert(final String filename, final int projectId, final Map<String, Integer> hashtable) {
		if (!exists) {
			final Plan plan = new Plan();
			plan.setProjectId(projectId);
			plan.setPlanId(0);
			plan.setName(filename);
			plan.setFilename(filename);

			final Date now = new Date();
			plan.setDtCreated(now);
			plan.setDtModified(now);
			plan.setUpdatedBy("SYSTEM");

			final DAOFactory factory = DAOFactory.getInstance();
			final DAOPlan dao = (DAOPlan) factory.getInstance(DAOPlan.class);
			dao.doInsert(plan);

			// add into hashtable so the next re-deploy of same file would not persist to DBMS
			hashtable.put(filename, plan.getPlanId());
		}
	}
}