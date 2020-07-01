package com.example.demo.design.patterns.state;

/**
 * 状态抽象类
 * State
 * @author zhangqi 
 * @date 2020年6月30日-下午5:42:37
 * 
 */
public abstract class State {
	
	/**
	 * 投币
	 * @author zhangqi 
	 * @date 2020年6月30日-下午4:08:51 void
	 */
	public abstract void insertQuarter();
	
	
	/**
	 * 退币
	 * @author zhangqi 
	 * @date 2020年6月30日-下午5:34:27 void
	 */
	public abstract void ejectQuarter();
	
	
	/**
	 * 转动出糖曲轴
	 * @author zhangqi 
	 * @date 2020年6月30日-下午5:35:00 void
	 */
	public abstract void turnCrank();
	
	
	/**
	 * 发糖
	 * @author zhangqi 
	 * @date 2020年6月30日-下午5:36:55 void
	 */
	public abstract void dispense();
	
	
	/**
	 * 退还硬币
	 * @author zhangqi 
	 * @date 2020年6月30日-下午5:39:01 void
	 */
	protected void returnQuarter() {
		System.out.println("退币....");
	}
	
	

}
