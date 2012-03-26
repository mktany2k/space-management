package com.scwcd.framework.command.core;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public abstract class AbstractServletCommand implements IServletCommand {

	@Override
	public final String execute(final Object[] input) {
		final HttpServletRequest request = (HttpServletRequest) input[0];
		final HttpServletResponse response = (HttpServletResponse) input[1];

		try {
			return execute(request, response);
		} catch (IOException e) {
			// TODO: Log error
			e.printStackTrace();
		}

		return null;
	}

	public abstract String execute(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException;

	@Override
	public IServletCommand create() {
		return this;
	}

	protected ApplicationSession getSession(final HttpServletRequest request) {
		final HttpSession session = request.getSession();

		ApplicationSession appSession = (ApplicationSession) session.getAttribute(ApplicationSession.SESSION_APPLICATION);
		if (appSession == null) {
			appSession = new ApplicationSession(session);
			session.setAttribute(ApplicationSession.SESSION_APPLICATION, appSession);
			return appSession;
		}

		return appSession;
	}

	protected void debugRequest(final HttpServletRequest request, final boolean outputToFile) {
		{
			System.out.println("request.getAttributeNames()");
			Enumeration<?> enumeration = request.getAttributeNames();
			while (enumeration.hasMoreElements()) {
				final String element = (String) enumeration.nextElement();
				final Object value = request.getAttribute(element);
				System.out.println(element + ": " + value);
			}
		}

		{
			System.out.println("request.getHeaderNames()");
			Enumeration<?> enumeration = request.getHeaderNames();
			while (enumeration.hasMoreElements()) {
				final String element = (String) enumeration.nextElement();
				final String value = request.getHeader(element);
				System.out.println(element + ": " + value);
			}
		}

		{
			System.out.println("request.getParameterNames()");
			Enumeration<?> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				final String element = (String) enumeration.nextElement();
				final String[] values = request.getParameterValues(element);
				for (final String value : values) {
					System.out.println(element + ": " + value);
				}
			}
		}
		
		if (outputToFile) {
			try {
				final String filename = MessageFormat.format("c:\\temp\\svg-{0,date,yyyyMMdd_HHmmss_SSS}.log", new Date());
				final FileOutputStream fos = new FileOutputStream(filename);
				final InputStream is = request.getInputStream();
				int b = 0;
				while ((b = is.read()) != -1) {
					fos.write(b);
				}
				is.close();
				fos.close();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void prepareResponse(final HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		response.addHeader("Content-Encoding", "UTF-8");
		response.addHeader("Content-Type", "text/xml");
	}
}