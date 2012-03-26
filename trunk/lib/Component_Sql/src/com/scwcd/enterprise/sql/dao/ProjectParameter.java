package com.scwcd.enterprise.sql.dao;


import com.scwcd.framework.sql.core.IParameter;


public class ProjectParameter implements IParameter {
	
	private int projectId;

	public ProjectParameter(final int projectId) {
		this.projectId = projectId;
	}

	public int getProjectId() {
		return this.projectId;
	}
}