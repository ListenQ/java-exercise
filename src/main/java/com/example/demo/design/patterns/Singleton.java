package com.example.demo.design.patterns;

/**
 * 单例模式-懒汉式
 * 线程不安全
 * Singleton<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年7月3日-下午1:37:51 <BR>
 * @version 1.0.0
 * 
 */
public class Singleton {
	
	private static Singleton singleton;
	
	private Singleton() {};
	
	public static Singleton getInstance() {
		if(singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

}
