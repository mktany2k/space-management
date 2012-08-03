package com.scwcd.enterprise.servlet;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.scwcd.framework.command.core.IServletCommand;
import com.scwcd.framework.command.core.ServletCommandFactory;


public class DefaultServlet extends HttpServlet {

	private static final long serialVersionUID = 1001L;
	
	private static final String COMMAND_KEY = "op";

	private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
	
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}
	
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		// get singleton instance for ServletCommandFactory object
		final ServletCommandFactory factory = ServletCommandFactory.getInstance();

		// creates appropriate IServletCommand
		final String commandKey = getCommandKey(request);
		
		/*
		System.out.println("commandKey: " + commandKey);
		System.out.println("request.getMethod(): " + request.getMethod());
		System.out.println("request.getContextPath(): " + request.getContextPath());
		System.out.println("request.getPathInfo(): " + request.getPathInfo());
		System.out.println("request.getRequestURL(): " + request.getRequestURL());
		System.out.println("request.getQueryString(): " + request.getQueryString());
		System.out.println("request.getRequestURI(): " + request.getRequestURI());
		*/

		String page = null;
		try {
			// execute command and dispatch to result page
			final IServletCommand command = factory.getInstance(commandKey);
			page = command.execute(request, response);
		} catch (final UnsupportedOperationException e) {
			page = ERROR_PAGE;
		} finally {
			if (page != null) {
				setGlobalValues(request);
				dispatch(request, response, page);
			}
		}
	}

	public static void setGlobalValues(final HttpServletRequest request) 
			throws ServletException, IOException {

		final String page_title = "Space Management";
		request.setAttribute("page_title", page_title);

		final String page_footer = MessageFormat.format("&copy; Copyright {0,date,yyyy} All rights reserved.", new Date());
		request.setAttribute("page_footer", page_footer);
	}

	protected void dispatch(final HttpServletRequest request, final HttpServletResponse response, final String page) 
			throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	protected String getCommandKey(final HttpServletRequest request) {
		final String commandKey = request.getParameter(COMMAND_KEY);
		if (commandKey == null) {
			return request.getRequestURI();
		}
		return commandKey;
	}
}