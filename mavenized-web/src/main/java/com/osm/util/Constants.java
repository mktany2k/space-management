package com.osm.util;

public final class Constants {

    private Constants() throws InstantiationException {
        throw Exceptions.newInstantiationException();
    }
    
    public static final class HibernateGenerator {

        public static final String NAME = "hibernate-uuid";
        public static final String STRATEGY = "uuid2";

        private HibernateGenerator() throws InstantiationException {
            throw Exceptions.newInstantiationException();
        }
    }
}