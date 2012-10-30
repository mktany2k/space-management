package com.scwcd.process.plan.core;

import com.scwcd.enterprise.sql.dao.DAOProject;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.enterprise.sql.util.SqlUtility;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.sql.core.DAOFactory;
import java.util.Date;
import org.joda.time.DateTime;

class ServiceNewProject extends AbstractBusinessService<Integer> {

    private String name;
    private String description;
    private String parser;
    private String unit;

    void setName(final String name) {
        this.name = name;
    }

    void setDescription(final String description) {
        this.description = description;
    }

    void setParser(final String parser) {
        this.parser = parser;
    }

    void setUnit(final String unit) {
        this.unit = unit;
    }

    @Override
    public void perform() {
        DAOFactory factory = DAOFactory.getInstance();
        DAOProject dao = (DAOProject) factory.getInstance(DAOProject.class);

        final Date now = DateTime.now().toDate();
        final Project project = new Project();
        project.setProjectId(SqlUtility.INCREMENT_ID);
        project.setName(name);
        project.setDescription(description);
        project.setParser(parser);
        project.setUnit(unit);
        project.setDtCreated(now);
        project.setDtModified(now);
        project.setUpdatedBy(SqlUtility.UPDATED_BY);

        dao.doSave(project);

        setOutput(project.getProjectId());
    }
}