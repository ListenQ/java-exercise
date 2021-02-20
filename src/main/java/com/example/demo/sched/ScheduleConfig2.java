package com.example.demo.sched;

import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class ScheduleConfig2 implements SchedulingConfigurer, Serializable {

	private static final long serialVersionUID = 7802421788557143855L;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(scheduleExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public ScheduledExecutorService scheduleExecutor() {
		return Executors.newScheduledThreadPool(50);// 项目核心线程数大小50
	}
}

