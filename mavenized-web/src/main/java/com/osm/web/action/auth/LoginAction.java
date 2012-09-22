package com.osm.web.action.auth;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Namespace("/auth")
@Action("Login")
@Results({
    @Result(name = "success", location = "/index"),
    @Result(name = "fail", location = "/login")})
public class LoginAction extends ActionSupport {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String password;
    private static final long serialVersionUID = 1001L;

    public String execute() throws Exception {
        System.out.println("LoginAction.execute");
        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword());

        try {
            SecurityUtils.getSubject().login(token);
            return SUCCESS;
        } catch (AuthenticationException exception) {
            return ERROR;
        }
    }
}