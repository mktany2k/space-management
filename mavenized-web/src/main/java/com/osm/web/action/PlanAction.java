package com.osm.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class PlanAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    /*
     * Get the number of .svg files in /WEB-INF/dat.
     *
     * (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    public String execute() {
        // read floor-plan.svg files
        return SUCCESS;
    }
}