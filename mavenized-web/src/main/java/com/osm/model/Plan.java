package com.osm.model;


import java.util.Date;


public class Plan {

	private int projectId;

	private int planId;

	private String name;

	private String description;

	private String filename;

	private Date dtCreated;

	private Date dtModified;

	private String updatedBy;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	public Date getDtModified() {
		return dtModified;
	}

	public void setDtModified(Date dtModified) {
		this.dtModified = dtModified;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public int hashCode() {
		return (projectId * 100000000) + (planId * 100000);
	}

	@Override
	public String toString() {
		StringBuilder sbString = new StringBuilder();
		sbString.append("Plan[");
		sbString.append(projectId).append(", ");
		sbString.append(planId).append(", ");
		sbString.append(name).append(", ");
		sbString.append(description).append(", ");
		sbString.append(filename).append(", ");
		sbString.append(dtCreated).append(", ");
		sbString.append(dtModified).append(", ");
		sbString.append(updatedBy).append("]");
		return sbString.toString();
	}
}