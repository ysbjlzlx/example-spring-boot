package com.anydong.example.springboot.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class DateUtilTests {
    @Test
    public void formatTest() {
        Date now = new Date();
        String datetimeString = DateUtil.format(now, "yyyy-MM-dd HH:mm:ss");
        System.out.println(datetimeString);
    }
}
