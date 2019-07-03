package com.example.demo.design.patterns;

/**
 * 单例模式-双检锁/双重校验锁
 * 线程安全
 * 采用双锁机制，安全且在多线程情况下能保持高性能。
 * SingletonDCL<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年7月3日-下午3:02:13 <BR>
 * @version 1.0.0
 * 
 */
public class SingletonDCL {

	private volatile static SingletonDCL singletonDCL;
	
	private SingletonDCL() {}
	
	public static SingletonDCL getSingleton() {
		if(singletonDCL == null) {
			synchronized(SingletonDCL.class) {
				if(singletonDCL == null) {
					singletonDCL = new SingletonDCL();
				}
			}
		}
		return singletonDCL;
	}
	
}
