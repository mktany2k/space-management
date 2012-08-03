package com.scwcd.enterprise.sql.hbm;


import java.io.Serializable;


public class LotKey implements Serializable {

	private static final long serialVersionUID = 1001L;

	private int projectId;
	
	private int planId;
	
	private int lotId;

	public LotKey() {
	}

	public LotKey(final int projectId, final int planId, final int lotId) {
		this.projectId = projectId;
		this.planId = planId;
		this.lotId = lotId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setLotId(int lotId) {
		this.lotId = lotId;
	}

	public int getLotId() {
		return lotId;
	}

	@Override
	public int hashCode() {
		return (projectId * 10000000) + (planId * 10000) + (lotId);
	}

	@Override
	public String toString() {
		StringBuilder sbString = new StringBuilder();
		sbString.append("LotKey[");
		sbString.append(projectId).append(", ");
		sbString.append(planId).append(", ");
		sbString.append(lotId).append("]");
		return sbString.toString();
	}
}