package com.scwcd.framework.deployment.core;


import java.io.File;


public class WebContext {

	public static final String DATA_PATH = File.separator + "dat";
	
	public static final String OUTPUT_PATH = File.separator + "output";

	private String projectPath;
	
	WebContext() {
	}

	public void setProjectPath(final String projectPath) {
		this.projectPath = projectPath;
	}

	public String getProjectPath() {
		return this.projectPath;
	}
	
}