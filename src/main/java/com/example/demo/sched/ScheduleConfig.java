package com.example.demo.sched;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 定时任务的线程池配置
 * 
 * @author shenyangkun
 * @date 2020/4/29
 **/
@Configuration
@EnableAsync
public class ScheduleConfig {
    
    @Value("${schedule.thread.pool.size:5}")
    private int poolSize;
    
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(poolSize);
        taskScheduler.setThreadNamePrefix("My_sched");
        return taskScheduler;
    }
    
}
