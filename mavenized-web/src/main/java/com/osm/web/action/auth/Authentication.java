package com.osm.web.action.auth;

import com.google.common.base.Strings;
import com.opensymphony.xwork2.ActionSupport;
import com.osm.auth.AuthenticationException;
import com.osm.auth.Authenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class Authentication extends ActionSupport {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Authentication.class);
    private Authenticator authenticator;
    
    public String login() throws Exception {
        if (Strings.isNullOrEmpty(getUsername())) {
            return INPUT;
        }
        
        if (Strings.isNullOrEmpty(getPassword())) {
            return INPUT;
        }
        
        LOGGER.debug("authenticating {}", username);
        try {
            authenticator.login(username, password);
            LOGGER.debug("user {} is authenticated", username);
        } catch (AuthenticationException e) {
            LOGGER.debug("authentication error: {}", e.getMessage());
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
