package com.example.demo.design.patterns;

/**
 * 单例模式-懒汉式
 * 线程安全
 * 优点：第一次调用才初始化，避免内存浪费
 * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率
 * Singleton02<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年7月3日-下午2:04:45 <BR>
 * @version 1.0.0
 * 
 */
public class Singleton02 {
	
	private static Singleton02 singleton02;
	
	private Singleton02() {}
	
	public static synchronized Singleton02 getInstance() {
		if(singleton02 == null) {
			singleton02 = new Singleton02();
		}
		return singleton02;
	}

}
