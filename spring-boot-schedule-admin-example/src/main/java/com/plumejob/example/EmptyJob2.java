package com.plumejob.example;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : pdl
 * @date : 2021/2/5 17:41
 */
@Component
public class EmptyJob2 {
    private static org.slf4j.Logger logger= LoggerFactory.getLogger(EmptyJob2.class);
    @Scheduled(cron = "0/10 * * * * ? ")
    public void job() {
        logger.info("empty job 2");
    }

    @Scheduled(cron = "0/30 * * * * ? ")
    public void job3() {
        logger.info("empty job 3");
    }

}
