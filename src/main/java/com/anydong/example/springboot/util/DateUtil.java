package com.anydong.example.springboot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author anydong
 */
public class DateUtil {
    /**
     * e.g: 2019-12-13T22:21:47CST
     * e.g: 2019-12-13T14:23:44.945Z
     */
    public static final String PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ssz";
    /**
     * e.g: Fri, 13 Dec 2019 14:23:58 GMT
     */
    public static final String PATTERN_UTC = "yyyy-MM-dd'T'HH:mm:ssz";

    /**
     * 参考：https://date-fns.org/v2.8.1/docs/format#syntax
     *
     * @param date   原始日期时间
     * @param format 格式化字符串，e.g: yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String format(Date date, String format) {
        return format(date, format, TimeZone.getDefault());
    }

    /**
     * 参考：https://date-fns.org/v2.8.1/docs/format#syntax
     *
     * @param date     原始日期时间
     * @param format   格式化字符串，e.g: yyyy-MM-dd HH:mm:ss
     * @param timeZone 时区
     * @return String
     */
    public static String format(Date date, String format, TimeZone timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }


    public static Date parse(String dateString, String formatString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        return simpleDateFormat.parse(dateString);
    }
}
