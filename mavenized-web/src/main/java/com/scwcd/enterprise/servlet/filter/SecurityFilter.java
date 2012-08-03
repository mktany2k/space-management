package com.scwcd.enterprise.servlet.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.scwcd.enterprise.sql.hbm.User;
import com.scwcd.framework.command.core.ApplicationSession;
import com.scwcd.process.login.core.CommandLogout;


public class SecurityFilter implements Filter {

	@Override
	public void init(final FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, 
			final FilterChain chain) throws IOException, ServletException {

		final boolean authentic = verifySession((HttpServletRequest) request, (HttpServletResponse) response);
		if (!authentic) {
			request.getRequestDispatcher(CommandLogout.JSP).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

	protected boolean verifySession(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException {

		final HttpSession session = request.getSession();
		final User user = (User) session.getAttribute(ApplicationSession.SESSION_USER);

		if (user == null) {
			return false;
		}

		return true;
	}
}