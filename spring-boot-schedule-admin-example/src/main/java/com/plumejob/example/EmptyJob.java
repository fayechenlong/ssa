package com.plumejob.example;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : pdl
 * @date : 2021/2/5 17:41
 */
@Component

public class EmptyJob {
    private static org.slf4j.Logger logger= LoggerFactory.getLogger(EmptyJob.class);
    @Scheduled(cron = "0/3 * * * * ? ")
    public void job() {
        logger.info("empty job 1");
    }

}
