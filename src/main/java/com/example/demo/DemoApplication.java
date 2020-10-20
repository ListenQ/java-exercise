package com.example.demo;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(value="com.example.demo")
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	/**
	 * 服务启动指定时间为美东时间
	 * @author zhangqi 
	 * @date 2020年1月7日-下午7:23:47 void
	*/
	@PostConstruct 
	void setDefaultTimezone() {
	    TimeZone.setDefault(TimeZone.getTimeZone("America/New_York")); 
	}  

}
