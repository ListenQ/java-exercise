package com.example.demo.mult.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TaskTest {
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
	
	public static void main(String[] args) throws Exception {
		List<User> list = new ArrayList<>(1000);
		long start = System.currentTimeMillis();
		for (long i = 0; i < 1000; i++) {
			User user = new User();
			user.setUserId((i+1));
			user.setPwd("123456"+i);
			list.add(user);
		}
		System.out.println("数据花费了毫秒:"+(System.currentTimeMillis()-start)+",初始数据量大小:"+list.size());
//		List<User> result = asynchEncry(list, start);
		List<User> result = streamEncry(list, start);
		System.out.println("结果大小:"+result);
	}
	
	
	public static List<User> asynchEncry(List<User> list,long start){
		ForkJoinPool joinPool = new ForkJoinPool();
		EncryptTask task = new EncryptTask(list);
	    List<User> result = joinPool.invoke(task);
		System.out.println(String.format("forkJoin线程异步等待结果出来:%s s",(System.currentTimeMillis()-start)/1000));
		joinPool.shutdown();
		return result;
	}
	
	
	public static List<User> streamEncry(List<User> list,long start){
		List<User> result = list.parallelStream().parallel().map(user ->{
			user.setPwd(ENCODER.encode(user.getPwd()));
			return user;
		}).collect(Collectors.toList());
		System.out.println(String.format("stream并行流等待结果出来:%s s",(System.currentTimeMillis()-start)/1000));
		return result;
	}

}
