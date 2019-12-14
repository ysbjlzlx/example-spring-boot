package com.anydong.example.springboot;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;

@SpringBootTest
public class DateUtilTests {
    public static final String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssZZ";
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void dateParse() throws ParseException {
        String dateStr = "2019-12-14T11:45:00Z";
        Date date = FastDateFormat.getInstance(ISO_8601).parse(dateStr);
        System.out.println(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(date));
    }

    @Test
    public void dateFormat() {
        Date date = new Date();
        // 2019-12-14T16:26:46+08:00
        System.out.println(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(date));
        // Sat, 14 Dec 2019 16:26:46 +0800
        System.out.println(DateFormatUtils.SMTP_DATETIME_FORMAT.format(date));
        // Sat Dec 14 16:26:46 CST 2019
        System.out.println(date.toString());
    }
}
