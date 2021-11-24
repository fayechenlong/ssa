package com.plumejob.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : pdl
 * @date : 2021/2/5 17:41
 */
@Component
public class EmptyJob {

    @Scheduled(cron = "0/30 * * * * ? ")
    public void job() {
        System.out.println("empty job");
    }

}
