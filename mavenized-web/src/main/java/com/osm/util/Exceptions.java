package com.osm.util;

public final class Exceptions {

    public static InstantiationException newInstantiationException() {
        return new InstantiationException(
                String.format("The class %s shouldn't be instantiated",
                        Thread.currentThread().getStackTrace()[2].getClassName())
        );
    }

    private Exceptions() throws InstantiationException {
        throw newInstantiationException();
    }
}