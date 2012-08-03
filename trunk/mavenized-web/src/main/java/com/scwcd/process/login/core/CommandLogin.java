package com.scwcd.process.login.core;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;


public class CommandLogin extends AbstractServletCommand {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandLogin.class);

	private static final String SUCCESS = "/WEB-INF/jsp/redirect.jsp";

	private static final String FAIL = "/WEB-INF/jsp/login.jsp";

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) {
		final ApplicationSession appSession = getSession(request);
		final LoginBusinessDelegate bizDelegate = new LoginBusinessDelegate(appSession, new ServiceLogin());

		final String username = request.getParameter("username");
		final char[] password = request.getParameter("password").toCharArray();
		final Map<String, String> rcCodes = bizDelegate.login(username, password);
		request.setAttribute(ApplicationSession.RCCODES, rcCodes);

		LOGGER.info(username + ":" + rcCodes);

		if (!rcCodes.containsKey("SYS0001")) {
			return FAIL;
		}

		return SUCCESS;
	}
}