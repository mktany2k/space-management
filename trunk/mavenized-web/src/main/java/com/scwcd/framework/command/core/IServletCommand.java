package com.scwcd.framework.command.core;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IServletCommand extends ICommand<Object[], String> {
	String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException;

	IServletCommand create();
}