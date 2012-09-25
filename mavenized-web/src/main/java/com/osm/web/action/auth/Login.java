package com.osm.web.action.auth;

import com.google.common.base.Strings;
import com.opensymphony.xwork2.ActionSupport;
import com.osm.auth.AuthenticationException;
import com.osm.auth.Authenticator;

@SuppressWarnings("serial")
public class Login extends ActionSupport {

    private Authenticator authenticator;

    @Override
    public String execute() throws Exception {
        if (Strings.isNullOrEmpty(getUsername())) {
            return INPUT;
        }

        if (Strings.isNullOrEmpty(getPassword())) {
            return INPUT;
        }

        try {
            authenticator.login(username, password);
        } catch (AuthenticationException e) {
            return INPUT;
        }
        return SUCCESS;
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

    public void setAuthenticator(final Authenticator authenticator) {
        this.authenticator = authenticator;
    }
}