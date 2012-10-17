package com.scwcd.framework.deployment.parser;


public enum ParserType {
    DEFAULT,
    VISIO2007,
    AUTOCAD;


    public static ParserType getParserType(final String type) {
        try {
            return ParserType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}