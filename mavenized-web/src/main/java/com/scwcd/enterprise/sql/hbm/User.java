package com.scwcd.enterprise.sql.hbm;


import java.util.Date;


public class User {

	private String username;
	
	private char[] password;

	private String name;
	
	private String description;
	
	private Date dtCreated;
	
	private Date dtModified;
	
	private String updatedBy;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
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
	public String toString() {
		StringBuilder sbString = new StringBuilder();
		sbString.append("User[");
		sbString.append(username).append(", ");
		sbString.append(password).append(", ");
		sbString.append(name).append(", ");
		sbString.append(description).append(", ");
		sbString.append(dtCreated).append(", ");
		sbString.append(dtModified).append(", ");
		sbString.append(updatedBy).append("]");
		return sbString.toString();
	}
}