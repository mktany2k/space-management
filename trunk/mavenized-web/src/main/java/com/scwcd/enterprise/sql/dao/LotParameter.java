package com.scwcd.enterprise.sql.dao;


import com.scwcd.framework.sql.core.IParameter;


public class LotParameter implements IParameter {

	private int projectId;
	
	private int planId;
	
	private int lotId;
	
	public LotParameter(int projectId, int planId) {
		this.projectId = projectId;
		this.planId = planId;
	}
	
	public LotParameter(int projectId, int planId, int lotId) {
		this.projectId = projectId;
		this.planId = planId;
		this.lotId = lotId;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public int getPlanId() {
		return this.planId;
	}

	public int getLotId() {
		return this.lotId;
	}
}