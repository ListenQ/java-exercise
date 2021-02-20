package com.example.demo.juc;

/**
 * synchronized关键字
 * 对某个对象加锁
 * T_sync2<BR>
 * 创建人：zhangqi <BR>
 * 时间：2021年1月17日-下午9:41:50 <BR>
 * @version 1.0.0
 * 
 */
public class T_sync3 {
	
	private static int count = 10;
	
	
	/**
	 * static 是没有this对象的，不需要new一个对象，这里等同于synchronized(T.class)锁的就是当前类的对象
	 */
	public synchronized static void  m() {
		count --;
		System.out.println(Thread.currentThread().getName()+"count ="+count);
	}
	
	
	/**
	 * static 是没有this对象的，
	 */
	public static void mm() {
		synchronized (T_sync3.class) { // 这里是不可以写 synchronized(this)的,会编译报错
			count --;
		}
	}
	
	

}
