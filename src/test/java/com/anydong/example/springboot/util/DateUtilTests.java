package com.anydong.example.springboot.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

@SpringBootTest
public class DateUtilTests {
    @Test
    public void formatTest() {
        Date now = new Date();
        String datetimeString = DateUtil.format(now, "yyyy-MM-dd HH:mm:ss");
        System.out.println(TimeZone.getDefault());
        System.out.println(datetimeString);
    }

    @Test
    public void parseTest() throws ParseException {
        Date date = DateUtil.parse("2019-12-12 23:08:52", "yyyy-MM-dd HH:mm:ss");
        System.out.println(TimeZone.getDefault());
        System.out.println(date);
    }
}
