package com.scwcd.process.ajax.core;


import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;


public class CommandUpdateLot extends AbstractServletCommand {

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) 
			throws IOException {
		
//		final String image = request.getParameter("i");
//		System.out.println(image);

		final ApplicationSession appSession = getSession(request);
		final AjaxBusinessDelegate bizDelegate = new AjaxBusinessDelegate(appSession, new ServiceUpdateLot());
		final Map<String, String> rcCodes = bizDelegate.updateLot(request.getParameter("n"),
																  request.getParameter("d"),
																  new Date(),
																  request.getParameter("u"));
		request.setAttribute(ApplicationSession.RCCODES, rcCodes);
		//try { Thread.sleep(5000); } catch (Exception e) { }

		return null;
	}
}