package com.scwcd.enterprise.servlet.listener.svg;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.scwcd.enterprise.servlet.ApplicationServlet;
import com.scwcd.enterprise.sql.dao.DAOProject;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.sql.core.DAOFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class SvgFileListener implements ServletContextListener {

	private static final String TEMPLATE_CAMEL = "camel.cfg.xml.tpl";

	private static final String TEMPLATE_DIR = "WEB-INF";

	@Override
	public void contextInitialized(final ServletContextEvent sce)  {
		try {
			// retrieve all projects
			final DAOFactory factory = DAOFactory.getInstance();
			final DAOProject dao = (DAOProject) factory.getInstance(DAOProject.class);
			final List<Project> projects = dao.doList();

			// initialize servlet context
			final ServletContext ctx = sce.getServletContext();
			final String configPath = ctx.getRealPath(TEMPLATE_DIR);
			final String contextFile = ctx.getInitParameter(ApplicationServlet.PARAM_CONTEXT);
			final String contextPath = ctx.getRealPath(contextFile);

			// transform template camel.cfg.xml.tpl
			final FileWriter writer = new FileWriter(contextPath);
			final Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File(configPath));
			final Template template = configuration.getTemplate(TEMPLATE_CAMEL);
			final Map<String, Object> map = new HashMap<String, Object>();
			map.put("projects", projects);
			map.put("projectPath", configPath);
			template.process(map, writer);
			writer.flush();
			writer.close();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final TemplateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
	}
}