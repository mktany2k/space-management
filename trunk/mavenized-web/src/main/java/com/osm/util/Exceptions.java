package com.osm.util;

public enum Exceptions {

    ;
        
    public static InstantiationException newInstantiationException() {
        return new InstantiationException("This class is not to be instantiated");
    }
}
