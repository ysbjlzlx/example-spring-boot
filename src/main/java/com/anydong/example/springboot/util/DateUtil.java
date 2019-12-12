package com.anydong.example.springboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author anydong
 */
public class DateUtil {
    /**
     * 参考：https://date-fns.org/v2.8.1/docs/format
     *
     * @param date   原始日期时间
     * @param format 格式化字符串，e.g: yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String format(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
