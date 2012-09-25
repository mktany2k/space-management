package com.osm.auth.shiro;

import com.osm.auth.AuthenticationException;
import com.osm.auth.Authenticator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroAuthenticator implements Authenticator {

    @Override
    public void login(final String username, final String password) throws AuthenticationException {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
        } catch (org.apache.shiro.authc.AccountException e) {
            throw new AuthenticationException(e);
        }
    }

    @Override
    public void logout() {
        SecurityUtils.getSubject().logout();
    }
}
