package com.osm.web.action.auth;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.shiro.SecurityUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;


@Namespace("/example")
@Action("Logout")
@Result(type="redirectAction",params={"namespace", "/", "actionName", "index"})
@SuppressWarnings("serial")
public class Logout extends ActionSupport {

    @Override
    public String execute() throws Exception {
        SecurityUtils.getSubject().logout();
        return SUCCESS;
    }
}