package com.anydong.example.springboot.task;

import com.anydong.example.springboot.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author anydong
 */
@Component
public class LogTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogTask.class);

    @Scheduled(fixedDelay = 10000)
    public void printDatetime() {
        LOGGER.info(DateUtil.format(new Date(), DateUtil.PATTERN_ISO));
    }
}
