package com.osm.web.action.auth;

import com.opensymphony.xwork2.ActionSupport;
import com.osm.auth.Authenticator;

@SuppressWarnings("serial")
public class Logout extends ActionSupport {

    private Authenticator authenticator;

    @Override
    public String execute() throws Exception {
        authenticator.logout();
        return SUCCESS;
    }

    public void setAuthenticator(final Authenticator authenticator) {
        this.authenticator = authenticator;
    }
}