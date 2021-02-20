package com.example.demo.juc;

/**
 * synchronized关键字
 * 对某个对象加锁
 * T_sync<BR>
 * 创建人：zhangqi <BR>
 * 时间：2021年1月17日-下午9:41:50 <BR>
 * @version 1.0.0
 * 
 */
public class T_sync {
	
	private int count = 10;
	private Object o = new Object();
	
	
	/**
	 * 每次想要成功调用m方法就得new Object o 比较麻烦，可以改成synchronized(this) 锁定当前对象就行
	 */
	public void  m() {
		synchronized (o) {
			count --;
			System.out.println(Thread.currentThread().getName()+"count = "+ count);
		}
	}

}
