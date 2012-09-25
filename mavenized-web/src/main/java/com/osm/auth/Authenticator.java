package com.osm.auth;

public interface Authenticator {

    void login(String username, String password) throws AuthenticationException;

    void logout();
}
