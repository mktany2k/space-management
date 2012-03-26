package com.scwcd.enterprise.servlet;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.camel.component.http.CamelServlet;
import org.springframework.web.context.ContextLoader;


public class ApplicationServlet extends CamelServlet {

	private static final long serialVersionUID = 1001L;

	public static final String PARAM_CONTEXT = "contextConfigLocation";

    private ContextLoader contextLoader;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        
        final ServletContext ctx = getServletContext();
        final String contextConfigLocation = ctx.getInitParameter(PARAM_CONTEXT);
        
        if (contextConfigLocation != null && contextConfigLocation.trim().length() != 0) {
        	contextLoader = new ContextLoader();
            try {
            	contextLoader.initWebApplicationContext(ctx);
            } catch (final IllegalStateException e) {
            	e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        if (contextLoader != null) {
            contextLoader.closeWebApplicationContext(getServletContext());
        }
    }
}