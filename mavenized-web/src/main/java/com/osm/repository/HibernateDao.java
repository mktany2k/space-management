package com.osm.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

/**
 * Convenience superclass for DAOs that contains annotations for injecting the session factory and accessing the session.
 */
public abstract class HibernateDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return SessionFactoryUtils.openSession(sessionFactory);
    }
}
