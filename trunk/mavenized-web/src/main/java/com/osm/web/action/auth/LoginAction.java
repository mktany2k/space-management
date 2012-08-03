package com.osm.web.action.auth;


import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1001L;

	public String execute() throws Exception {
		System.out.println("LoginAction.execute");
		return SUCCESS;
	}
}