package com.osm.web.action;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;


public class PlanAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	/*
	 * Get the number of .svg files in /WEB-INF/dat.
	 * 
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		// read floor-plan.svg files
		return ActionSupport.SUCCESS;
	}

	@Override
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}
}