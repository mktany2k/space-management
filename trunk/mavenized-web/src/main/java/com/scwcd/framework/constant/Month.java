package com.scwcd.framework.constant;

public enum Month {

    JANUARY(1, "January"),
    FEBRUARY(2, "February"),
    MARCH(3, "March"),
    APRIL(4, "April"),
    MAY(5, "May"),
    JUNE(6, "June"),
    JULY(7, "July"),
    AUGUST(8, "August"),
    SEPTEMBER(9, "September"),
    OCTOBER(10, "October"),
    NOVEMBER(11, "November"),
    DECEMBER(12, "December");
    private int month;
    private String name;

    Month(int month, String name) {
        this.month = month;
        this.name = name;
    }
    private static final Month[] MONTHS = values();

    public static String getMonth(int m) {
        for (Month month : MONTHS) {
            if (month.month == m) {
                return month.toString();
            }
        }
        return null;
    }

    public int value() {
        return month;
    }

    @Override
    public String toString() {
        return name;
    }
}