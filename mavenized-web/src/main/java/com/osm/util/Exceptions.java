package com.osm.util;

public final class Exceptions {

    public static InstantiationException newInstantiationException() {
        return new InstantiationException("This class is not to be instantiated");
    }

    private Exceptions() throws InstantiationException {
        throw newInstantiationException();
    }
}