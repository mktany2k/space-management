package com.osm.example.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.shiro.SecurityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/example")
@Action("Logout")
@Result(location="login")
public class Logout extends ActionSupport {

    @Override
    public String execute() throws Exception {
        SecurityUtils.getSubject().logout();
        return SUCCESS;
    }
    
}
