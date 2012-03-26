package com.scwcd.framework.command.core;


import java.util.ResourceBundle;


public class CommandResource {

	private static final CommandResource INSTANCE = new CommandResource();

	private static final String RESOURCE = "com.scwcd.framework.command.resource.command_en";

	private CommandResource() {
	}

	public static CommandResource getInstance() {
		return INSTANCE;
	}
	
	public String getOperation(Class<?> clazz) {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE);
		final String key = clazz.getCanonicalName();
		return resourceBundle.getString(key);
	}
	
	public String[] getOperations(Class<?> clazz) {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE);
		final String key = clazz.getCanonicalName();
		return resourceBundle.getString(key).split(",");
	}
}