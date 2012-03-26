package com.scwcd.framework.sql.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtility {

	private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	return new Configuration().configure("../hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable e) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed. " + e);
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}