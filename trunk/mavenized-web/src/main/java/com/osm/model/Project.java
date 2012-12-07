package com.osm.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Project {

	private int projectId;

	private String name;

	private String description;

	private String parser;

	private String unit;

	private Date dtCreated;

	private Date dtModified;

	private String updatedBy;

	private Set<Plan> plans = new HashSet<>();

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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

	public void setParser(String parser) {
		this.parser = parser;
	}

	public String getParser() {
		return parser;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
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

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}

	public Set<Plan> getPlans() {
		return plans;
	}

	@Override
	public String toString() {
		StringBuilder sbString = new StringBuilder();
		sbString.append("Project[");
		sbString.append(projectId).append(", ");
		sbString.append(name).append(", ");
		sbString.append(description).append(", ");
		sbString.append(parser).append(", ");
		sbString.append(unit).append(", ");
		sbString.append(updatedBy).append(", ");
		sbString.append(dtCreated).append(", ");
		sbString.append(dtModified).append(", ");
		sbString.append(plans).append("]");
		return sbString.toString();
	}
}