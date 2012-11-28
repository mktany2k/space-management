package com.scwcd.framework.sql.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * 
 * @deprecated This class no longer is use. Will remove after 5 Dec 2012.
 *
 */
@Deprecated
public class HibernateUtility {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	@Deprecated
    private static SessionFactory buildSessionFactory() {

        try {

            // Create the SessionFactory from hibernate.cfg.xml
        	return new Configuration().configure("../hibernate.cfg.xml").buildSessionFactory();
        } catch (final Throwable e) {

            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed. " + e);
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

	@Deprecated
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}