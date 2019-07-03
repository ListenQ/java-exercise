package com.example.demo.design.patterns;

/**
 * 单例模式-登记式/静态内部类
 * 线程安全
 * 能达到双检锁方式一样的功效，但实现更简单
 * 只适用于静态域的情况
 * Singleton04<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年7月3日-下午5:25:17 <BR>
 * @version 1.0.0
 * 
 */
public class Singleton04 {

	private static class SingletonHolder{
		private static final Singleton04 SINGLETON04 = new Singleton04();
	}
	
	private Singleton04() {};
	
	public static final Singleton04 getInstance() {
		return SingletonHolder.SINGLETON04;
	}
	
}
