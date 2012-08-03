package com.scwcd.process.ajax.core;


import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.framework.command.core.AbstractServletCommand;
import com.scwcd.framework.command.core.ApplicationSession;


public class CommandSelectView extends AbstractServletCommand {

	@Override
	public String execute(final HttpServletRequest request, final HttpServletResponse response) 
			throws IOException {

		final ApplicationSession appSession = getSession(request);
		final int viewId = Integer.parseInt(request.getParameter("vid"));
		
		final AjaxBusinessDelegate bizDelegate = new AjaxBusinessDelegate(appSession, new ServiceSelectView());
		final byte[] viewJSON = bizDelegate.getView(viewId);
		
		final OutputStream os = response.getOutputStream();
		os.write(viewJSON);
		os.flush();
		os.close();
		
		return null;
	}
}