package com.osm.web.action.auth;

import com.opensymphony.xwork2.ActionSupport;
import com.osm.auth.AuthenticationException;
import com.osm.auth.Authenticator;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Authentication extends ActionSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long serialVersionUID = 1L;
    private Authenticator authenticator;

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

    public String logout() {
        authenticator.logout();
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