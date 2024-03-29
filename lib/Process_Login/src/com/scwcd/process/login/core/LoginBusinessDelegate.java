package com.scwcd.process.login.core;


import java.util.List;
import java.util.Map;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.enterprise.sql.hbm.User;
import com.scwcd.framework.business.core.AbstractBusinessDelegate;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.command.core.ApplicationSession;


class LoginBusinessDelegate extends AbstractBusinessDelegate {

	protected LoginBusinessDelegate(final ApplicationSession session, final AbstractBusinessService<?> service) {
		super(session, service);
	}

	Map<String, String> login(final String username, final char[] password) {
		ServiceLogin service = (ServiceLogin) getService();
		service.setUsername(username);
		service.setPassword(password);

		service.perform();

		final User user = (User) service.getOutput();
		if (user != null) {
			final ApplicationSession session = getSession();
			session.setUser(user);
		}

		return service.getRcCodes();
	}

	List<Project> getProjects() {
		ServiceListProjects service = (ServiceListProjects) getService();
		service.perform();
		return service.getOutput();
	}
}