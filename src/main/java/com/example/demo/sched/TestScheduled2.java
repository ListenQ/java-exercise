package com.example.demo.sched;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
@EnableAsync
public class TestScheduled2 {
	
	
	
    @Async
    @Scheduled(fixedDelay = 2000)
    public void stockDataPush() throws InterruptedException {
    	Thread.sleep(2500l);
       log.info(Thread.currentThread().getName()+"执行了2");
    }
}
