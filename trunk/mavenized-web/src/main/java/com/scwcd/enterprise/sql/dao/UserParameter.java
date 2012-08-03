package com.scwcd.enterprise.sql.dao;


import com.scwcd.framework.sql.core.IParameter;


public class UserParameter implements IParameter {

	private String username;

	public UserParameter(final String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}
}