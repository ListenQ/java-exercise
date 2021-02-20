package com.example.demo.config;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.demo.cp.TestDto;
import com.example.demo.cp.TestService;
import com.example.demo.cp.TestServiceImpl;
import com.example.demo.cp.TestServiceImpl2;
import com.example.demo.cp.TestServiceImpl3;

@Configuration
public class TestBean {
	
//	@Bean("testServiceImpl")
//	public TestService testServiceImpl() {
//		return new TestServiceImpl();
//	}
//	
//	
//	@Bean("testServiceImpl2")
//	public TestService testServiceImpl2() {
//		return new TestServiceImpl2();
//	}
//	
//	
//	@Bean("testServiceImpl3")
//	public TestService testServiceImpl3() {
//		return new TestServiceImpl3();
//	}
	
	
	@Bean
	public TestDto t1() {
		return new TestDto("嘻嘻", new Date(), BigDecimal.ONE);
	}
	
	@Bean
	public TestDto t2() {
		return new TestDto("哈哈", new Date(), BigDecimal.TEN);
	}
	
	@Bean
	public TestDto t3() {
		return new TestDto("嘿嘿", new Date(), BigDecimal.ZERO);
	}
	
	
	@Bean
	@Primary
	public TestService test(@Qualifier("t1") TestDto t1,@Qualifier("t2") TestDto t2,@Qualifier("t3") TestDto t3) {
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		return new TestServiceImpl(t1);
	}
	

}
