package com.osm.web.action.auth;

import com.opensymphony.xwork2.ActionSupport;
import com.osm.auth.AuthenticationException;
import com.osm.auth.Authenticator;
import java.lang.invoke.MethodHandles;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("authentication")
@Scope("prototype")
public class Authentication extends ActionSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;
    private transient Authenticator authenticator;
    private String username;
    private String password;

    @Actions({
        @Action(value = "/auth/login", results = {
            @Result(name = SUCCESS, type = "redirectAction",
                    params = {"namespace", "/", "actionName", "index"},
                    location = "/WEB-INF/jsp/admin/administration.jsp"),
            @Result(name = INPUT, location = "/WEB-INF/jsp/login.jsp")
        })
    })
    public String login() throws Exception {
        LOGGER.debug("authenticating {}", username);
        try {
            authenticator.login(username, password);
            LOGGER.debug("user {} is authenticated", username);
        } catch (AuthenticationException e) {
            LOGGER.debug("authentication error: {}", e.getMessage());
            addActionError(getText("invalid.login"));
            return INPUT;
        }
        return SUCCESS;
    }

    @Actions({
        @Action(value = "/auth/logout", results = {
            @Result(name = SUCCESS, type = "redirectAction",
                    params = {"namespace", "/", "actionName", "index"},
                    location = "/WEB-INF/jsp/admin/administration.jsp")
        })
    })
    public String logout() {
        authenticator.logout();
        return SUCCESS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Autowired
    public void setAuthenticator(final Authenticator authenticator) {
        this.authenticator = authenticator;
    }
}