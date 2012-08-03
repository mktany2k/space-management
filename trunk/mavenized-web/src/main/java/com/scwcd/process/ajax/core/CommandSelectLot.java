package com.scwcd.process.ajax.core;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;


public class CommandSelectLot extends AbstractServletCommand {

	private static final String JSP = "/WEB-INF/jsp/lot.jsp";

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) 
			throws IOException {

		final ApplicationSession appSession = getSession(request);
		final int projectId = Integer.parseInt(request.getParameter("n1"));
		final int planId = Integer.parseInt(request.getParameter("n2"));
		final int lotId = Integer.parseInt(request.getParameter("n3"));

		final AjaxBusinessDelegate bizDelegate = new AjaxBusinessDelegate(appSession, new ServiceSelectLot());
		final Lot lot = bizDelegate.getLot(projectId, planId, lotId);

		appSession.setCache(lot);
		request.setAttribute("lot", lot);

		prepareResponse(response);

		return JSP;
	}
}