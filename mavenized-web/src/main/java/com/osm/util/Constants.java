package com.osm.util;

public interface Constants {

    public static final class HibernateGenerator {

        public static final String NAME = "hibernate-uuid";
        public static final String STRATEGY = "uuid2";

        private HibernateGenerator() throws InstantiationException {
            throw Exceptions.newInstantiationException();
        }
    }
}