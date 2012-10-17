package com.scwcd.enterprise.sql.dao;


import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.framework.sql.core.Insertable;
import com.scwcd.framework.sql.core.Saveable;
import com.scwcd.framework.sql.core.Listable;
import com.scwcd.framework.sql.util.HibernateUtility;


public class DAOPlan implements Listable<Plan>, Saveable<Plan>, Insertable<Plan> {

	@Override
	public List<Plan> doList() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Plan.class);
        List<?> list = criteria.list();
        session.close();
        Plan[] plans = list.toArray(new Plan[list.size()]);
		return Arrays.asList(plans);
	}

	public List<Plan> doList(final int projectId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Plan.class);
        List<?> list = criteria.add(Restrictions.eq("projectId", projectId)).list();
        session.close();
        Plan[] plans = list.toArray(new Plan[list.size()]);
		return Arrays.asList(plans);
	}

	@Override
    public void doSave(final Plan plan) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        session.saveOrUpdate(plan);

        session.getTransaction().commit();
        session.close();
    }

	@Override
	public void doInsert(final Plan plan) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(plan);

        session.getTransaction().commit();
        session.close();
	}
}