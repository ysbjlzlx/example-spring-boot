package com.anydong.example.springboot.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author anydong
 */
@Component
@Slf4j
public class LogTask {

    @Scheduled(fixedDelay = 10000)
    public void printDatetime() {
        log.info(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(new Date()));
    }
}
