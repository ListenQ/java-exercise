package com.example.demo.queue;

/**java 5 以前
 * @author Administrator
 *
 */
public class JavaDemo {
	
	public static void main(String[] args) {
//		System.out.println(System.currentTimeMillis()+"**"+System.nanoTime());
		
		/**
		 * 子线程
		 */
		  Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.printf("当前线程%s-时间是%s\n", Thread.currentThread().getName(),System.currentTimeMillis());
			}
		}, "mythreadt1");
		
		
		Thread t2 =new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.printf("当前线程%s-时间是%s\n", Thread.currentThread().getName(),System.currentTimeMillis());
			}
		}, "mythreadt2");
		
		System.out.println("水电费水电费");
		try {
			t2.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
	}

}
