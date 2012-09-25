package com.osm.web.action.auth;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/auth")
@SuppressWarnings("serial")
public class Login extends ActionSupport {

    @Override
    @Action(value = "Login", results = {
        @Result(name = "input", location = "/WEB-INF/jsp/login.jsp"),
        @Result(name = "success", type = "redirectAction", params = {"namespace", "/", "actionName", "index"})})
    public String execute() throws Exception {
        if (isInvalid(getUsername())) {
            return INPUT;
        }

        if (isInvalid(getPassword())) {
            return INPUT;
        }
        
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            return INPUT;
        }
        return SUCCESS;
    }

    private boolean isInvalid(String value) {
        return (value == null || value.isEmpty());
    }
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}