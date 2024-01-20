package com.solvd.carservice.domain.parse;

public enum EnumParser {
    STAX(1),
    JAXB(2),
    JACKSON(3);
    private final int parseType;

    EnumParser(int parseType) {
        this.parseType = parseType;
    }
    public int getParseType() {
        return parseType;
    }
}
