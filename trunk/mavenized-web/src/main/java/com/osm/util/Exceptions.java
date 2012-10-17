package com.osm.util;

public enum Exceptions {

    ;
        
    public static InstantiationException instantiationException() {
        return new InstantiationException("This class is not to be instantiated");
    }
}
