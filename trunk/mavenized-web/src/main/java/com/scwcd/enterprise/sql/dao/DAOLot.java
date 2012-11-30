package com.scwcd.enterprise.sql.dao;

import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.enterprise.sql.hbm.LotKey;
import com.scwcd.framework.sql.core.Insertable;
import com.scwcd.framework.sql.core.Listable;
import com.scwcd.framework.sql.core.Saveable;
import com.scwcd.framework.sql.core.Selectable;
import com.scwcd.framework.sql.core.Updateable;
import com.scwcd.framework.sql.util.HibernateUtility;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

public class DAOLot implements Saveable<Lot>, Insertable<Lot>, Selectable<LotParameter, Lot>,
        Listable<Lot>, Updateable<Lot> {

    @Override
    public void doSave(final Lot lot) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        session.saveOrUpdate(lot);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void doInsert(final Lot lot) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(lot);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Lot> doList() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Lot.class);
        List<?> list = criteria.list();
        session.close();

        Lot[] lots = list.toArray(new Lot[list.size()]);
        return Arrays.asList(lots);
    }

    public List<Lot> doList(final LotParameter parameter) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Lot.class);
        List<Lot> list = criteria.add(Restrictions.eq("lotKey.projectId", parameter.getProjectId()))
                .add(Restrictions.eq("lotKey.planId", parameter.getPlanId())).list();
        session.close();

        Lot[] lots = list.toArray(new Lot[list.size()]);
        return Arrays.asList(lots);
    }

    public static List<Lot> doList(final int projectId, final int... planIds) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Lot.class);
        criteria.add(Restrictions.eq("lotKey.projectId", projectId));

        Disjunction disjunction = Restrictions.disjunction();
        for (final int planId : planIds) {
            criteria.add(disjunction.add(Restrictions.eq("lotKey.planId", planId)));
        }

        List<?> list = criteria.list();
        session.close();

        Lot[] lots = list.toArray(new Lot[list.size()]);
        return Arrays.asList(lots);
    }

    @Override
    public Lot doSelect(final LotParameter parameter) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        final Lot result = (Lot) session.get(Lot.class, new LotKey(parameter.getProjectId(),
                parameter.getPlanId(),
                parameter.getLotId()));
        session.close();

        return result;
    }

    @Override
    public void doUpdate(final Lot lot) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        session.update(lot);

        session.getTransaction().commit();
        session.close();
    }
}