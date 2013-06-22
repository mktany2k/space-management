package com.osm.auth;

/**
 * Throw when anything related to authentication.
 */
public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(final String message) {
        super(message);
    }

    public AuthenticationException(final Throwable cause) {
        super(cause);
    }

    public AuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
