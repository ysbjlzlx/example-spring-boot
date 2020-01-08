package com.anydong.example.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author anydong
 */
@Configuration
@EnableScheduling
public class SchedulingConfigurerImpl implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        scheduledTaskRegistrar.setScheduler(executor);
    }
}


