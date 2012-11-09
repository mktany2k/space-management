package com.scwcd.framework.constant;

import org.fest.assertions.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MonthUnitTest {

    @Test(dataProvider = "months")
    public void get_month(int monthInDigit, String monthInString) {
        Assertions.assertThat(Month.getMonth(monthInDigit)).isEqualTo(monthInString);
    }

    @DataProvider(name = "months")
    public Object[][] getData() {
        return new Object[][]{
                    {1, "January"},
                    {2, "February"},
                    {3, "March"},
                    {4, "April"},
                    {5, "May"},
                    {6, "June"},
                    {7, "July"},
                    {8, "August"},
                    {9, "September"},
                    {10, "October"},
                    {11, "November"},
                    {12, "December"}
                };
    }
}
