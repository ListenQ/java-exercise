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
public class T_sync2 {
	
	private int count = 10;
	
	
	/**
	 * 任何线程想要执行那个下面的代码，必须先要拿到this的锁
	 */
	public void  m() {
		synchronized (this) {
			count --;
			System.out.println(Thread.currentThread().getName()+"count = "+ count);
		}
	}
	
	/**
	 * 与上面方法等价，等同于在方法的代码执行时要synchronized(this)
	 */
	public synchronized void m2() {
		count --;
		System.out.println(Thread.currentThread().getName()+"count ="+count);
	}

}
