package com.scwcd.framework.deployment.core;

import com.google.common.collect.Maps;
import com.scwcd.enterprise.sql.dao.DAOProject;
import com.scwcd.enterprise.sql.dao.ProjectParameter;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.deployment.xslt.XsltTemplate;
import com.scwcd.framework.sql.core.DAOFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.camel.Processor;

/**
 * @deprecated This class will be removed.
 */
@Deprecated
public final class DeploymentManager {

    private static final DeploymentManager INSTANCE = new DeploymentManager();
    private CamelManager camelManager;
    private final Map<String, Integer> hashtable;

    private DeploymentManager() {
        this.hashtable = Maps.newConcurrentMap();
    }

    @Deprecated
    public static DeploymentManager getInstance() {
        return INSTANCE;
    }

    @Deprecated
    public void init() {
        // retrieve project directory path

        // retrieve all projects
        final DAOFactory factory = DAOFactory.getInstance();
        final DAOProject dao = (DAOProject) factory.getInstance(DAOProject.class);
        // for every projects, create a new processor and a new parser
        List<Project> projects = dao.doList();
        final List<Processor> processors = new ArrayList<>(projects.size());
        for (Project project : dao.doList()) {
            for (Plan plan : project.getPlans()) {
                hashtable.put(plan.getFilename(), plan.getPlanId());
            }

            final XsltTemplate parserType = XsltTemplate.getTemplate(project.getParser());
            final int projectId = project.getProjectId();

            final DefaultDeploymentProcessor processor = new DefaultDeploymentProcessor();
            processor.setProjectId(projectId);
            processor.setProjectPath(WebContextManager.getWebContext().getProjectPath());
            processor.setXsltTemplate(parserType);
            processor.setHashtable(hashtable);

            processors.add(processor);
        }

        try {
            camelManager = new CamelManager(processors);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (!camelManager.isRunning()) {
            camelManager.start();
        }
    }

    public void listen(final int projectId, final String configPath) {
        if (camelManager.isRunning()) {
            final DAOFactory factory = DAOFactory.getInstance();
            final DAOProject dao = (DAOProject) factory.getInstance(DAOProject.class);
            final Project project = dao.doSelect(new ProjectParameter(projectId));

            final XsltTemplate xsltTemplate = XsltTemplate.getTemplate(project.getParser());

            final DefaultDeploymentProcessor processor = new DefaultDeploymentProcessor();
            processor.setProjectId(projectId);
            processor.setProjectPath(configPath);
            processor.setXsltTemplate(xsltTemplate);
            processor.setHashtable(hashtable);

            camelManager.load(processor);
        }
    }

    public void stop() {
        if (camelManager.isRunning()) {
            camelManager.stop();
        }
    }
}