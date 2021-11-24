package com.plumejob.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : pdl
 * @date : 2021/2/8 11:50
 */
@SpringBootApplication
@EnableScheduling
public class LiteExampleApp {
    public static void main(String[] args) {
        SpringApplication.run(LiteExampleApp.class);
    }
}
