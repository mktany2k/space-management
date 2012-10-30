package com.scwcd.framework.deployment.core;

import com.scwcd.enterprise.sql.dao.DAOPlan;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.enterprise.sql.util.SqlUtility;
import com.scwcd.framework.sql.core.DAOFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

class SvgFileManager {

    private boolean exists;

    void validate(final boolean exists) {
        this.exists = exists;
    }

    void insert(final String filename, final int projectId, final Map<String, Integer> hashtable) {
        if (exists) {
            return;
        } 
        
        // generate plan id
        final List<Integer> planIds = new ArrayList<>(hashtable.values());
        Collections.sort(planIds);
        final int planId = planIds.isEmpty() ? 1 : planIds.get(planIds.size() - 1) + 1;

        final Plan plan = new Plan();
        plan.setProjectId(projectId);
        plan.setPlanId(planId);
        plan.setName(filename);
        plan.setFilename(filename);

        final Date now = new Date();
        plan.setDtCreated(now);
        plan.setDtModified(now);
        plan.setUpdatedBy(SqlUtility.UPDATED_BY);

        final DAOFactory factory = DAOFactory.getInstance();
        final DAOPlan dao = (DAOPlan) factory.getInstance(DAOPlan.class);
        dao.doInsert(plan);

        // add into hashtable so the next re-deploy of same file would not persist to storage
        hashtable.put(filename, plan.getPlanId());
    }
}