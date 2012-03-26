package com.scwcd.enterprise.sql.dao;


import org.hibernate.Session;
import com.scwcd.enterprise.sql.hbm.User;
import com.scwcd.framework.sql.core.Saveable;
import com.scwcd.framework.sql.core.Selectable;
import com.scwcd.framework.sql.util.HibernateUtility;


public class DAOUser implements Selectable<UserParameter, User>, Saveable<User> {

	@Override
	public User doSelect(final UserParameter parameter) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        
        User result = (User) session.get(User.class, parameter.getUsername());
        session.close();

    	return result;
	}

	@Override
    public void doSave(final User user) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}