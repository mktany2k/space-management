package com.scwcd.framework.deployment.xslt;


public enum XsltTemplate {
	DEFAULT("default.xsl"),
	VISIO2007("visio2007.xsl"),
	AUTOCAD("autocad.xsl");

	private String filename;

	XsltTemplate(final String filename) {
		this.filename = filename;
	}

	public static XsltTemplate getTemplate(final String type) {
		if (XsltTemplate.DEFAULT.name().equals(type)) {
			return XsltTemplate.DEFAULT;
		} else if (XsltTemplate.VISIO2007.name().equals(type)) {
			return XsltTemplate.VISIO2007;
		} else if (XsltTemplate.AUTOCAD.name().equals(type)) {
			return XsltTemplate.AUTOCAD;
		}
		return null;
	}
	
	public String getFilename() {
		return filename;
	}
}