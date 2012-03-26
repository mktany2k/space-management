package com.scwcd.enterprise.sql.dao;


import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.sql.core.Saveable;
import com.scwcd.framework.sql.core.Listable;
import com.scwcd.framework.sql.core.Selectable;
import com.scwcd.framework.sql.util.HibernateUtility;


public class DAOProject implements Listable<Project>, Selectable<ProjectParameter, Project>, Saveable<Project> {

	@Override
    public Project doSelect(final ProjectParameter parameter) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        
        Project result = (Project) session.get(Project.class, parameter.getProjectId());
        session.close();

    	return result;
    }

	@Override
    public List<Project> doList() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Project.class);
        List<?> list = criteria.list();
        session.close();
        Project[] projects = list.toArray(new Project[0]);
        return Arrays.asList(projects);
    }

	@Override
    public void doSave(final Project project) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(project);

        session.getTransaction().commit();
        session.close();
    }
}