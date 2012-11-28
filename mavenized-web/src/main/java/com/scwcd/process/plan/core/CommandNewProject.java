package com.scwcd.process.plan.core;

import com.scwcd.framework.command.core.ApplicationSession;
import com.scwcd.framework.deployment.core.DeploymentManager;
import com.scwcd.framework.deployment.core.WebContext;
import com.scwcd.framework.deployment.core.WebContextManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Deprecated
public class CommandNewProject extends CommandOpenProject {

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) {
        return super.execute(request, response);
    }

    @Override
    protected int getProjectId(final HttpServletRequest request) {
        // retrieve project directory path
        final WebContext ctx = WebContextManager.getWebContext();
        final String configPath = ctx.getProjectPath();

        // creates new project by persisting into database
        final ApplicationSession appSession = getSession(request);
        final PlanBusinessDelegate planBizDelegate = new PlanBusinessDelegate(appSession, new ServiceNewProject());

        final String name = request.getParameter("name");
        final String description = request.getParameter("description");
        final String parser = request.getParameter("parser");
        final String unit = request.getParameter("unit");
        final int projectId = planBizDelegate.createProject(name, description, parser, unit);

        final DeploymentManager manager = DeploymentManager.getInstance();
        manager.listen(projectId, configPath);

        return projectId;
    }
}