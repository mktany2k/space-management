package com.scwcd.framework.deployment.parser;


public enum ParserType {
	DEFAULT,
	VISIO2007,
	AUTOCAD;
	
	public static ParserType getParserType(final String type) {
		if (ParserType.DEFAULT.toString().equals(type)) {
			return ParserType.DEFAULT;
		} else if (ParserType.VISIO2007.toString().equals(type)) {
			return ParserType.VISIO2007;
		} else if (ParserType.AUTOCAD.toString().equals(type)) {
			return ParserType.AUTOCAD;
		}
		return null;
	}
}