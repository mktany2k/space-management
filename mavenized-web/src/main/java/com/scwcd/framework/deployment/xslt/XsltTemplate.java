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
        return XsltTemplate.valueOf(type);
    }

    public String getFilename() {
        return filename;
    }
}