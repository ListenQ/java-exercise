package com.example.demo.design.patterns;

/**
 * 单例模式-饿汉式
 * 线程安全
 * 优点：没有加锁，执行效率会提高
 * 缺点：类加载时就初始化，浪费内存
 * Singleton03<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年7月3日-下午2:18:04 <BR>
 * @version 1.0.0
 * 
 */
public class Singleton03 {
	
	private static Singleton03 singleton03 = new Singleton03();
	
	private Singleton03() {};
	
	public static Singleton03 getInstance() {
		return singleton03;
	}

}
