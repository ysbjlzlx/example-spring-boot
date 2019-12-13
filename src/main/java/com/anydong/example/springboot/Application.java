package com.anydong.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.annotation.PostConstruct;

/**
 * Application
 *
 * @author Where
 * @date 2019/10/31
 */
@SpringBootApplication
@ServletComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 设置默认时区为：Asia/Shanghai
     * 设置 URL 中可传入的特殊字符
     */
    @PostConstruct
    public void init() {
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}");
    }
}
