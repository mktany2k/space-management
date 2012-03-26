package com.scwcd.framework.deployment.core;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import org.apache.camel.Processor;
import com.scwcd.enterprise.sql.dao.DAOProject;
import com.scwcd.enterprise.sql.dao.ProjectParameter;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.deployment.xslt.XsltTemplate;
import com.scwcd.framework.sql.core.DAOFactory;


public class DeploymentManager {

	private static final DeploymentManager INSTANCE = new DeploymentManager();

	private CamelManager camelManager;
	
	private final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();

	private DeploymentManager() {
		// prevent instantiation
	}

	public static DeploymentManager getInstance() {
		return INSTANCE;
	}

	public void init() {
		// retrieve project directory path
		final WebContext ctx = WebContextManager.getWebContext();
		final String configPath = ctx.getProjectPath();

		// retrieve all projects
		final DAOFactory factory = DAOFactory.getInstance();
		final DAOProject dao = (DAOProject) factory.getInstance(DAOProject.class);
		final List<Project> projects = dao.doList();

		// for every projects, create a new processor and a new parser
		final List<Processor> processors = new ArrayList<Processor>();
		for (final Project project : projects) {
			final Set<Plan> plans = project.getPlans();
			for (final Plan plan : plans) {
				hashtable.put(plan.getFilename(), plan.getPlanId());
			}

			final XsltTemplate parserType = XsltTemplate.getTemplate(project.getParser());
			final int projectId = project.getProjectId();

			final DefaultDeploymentProcessor processor = new DefaultDeploymentProcessor();
			processor.setProjectId(projectId);
			processor.setProjectPath(configPath);
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