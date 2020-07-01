package com.example.demo.design.patterns.state;

/**
 * 售尽的状态
 * SoldOutState
 * @author zhangqi 
 * @date 2020年7月1日-下午5:50:05
 * 
 */
public class SoldOutState extends State{
	
	GumballMachine gumballMachine;
	
	public SoldOutState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("糖果已经售尽");
        returnQuarter();
	}

	@Override
	public void ejectQuarter() {
		System.out.println("没有投币，无法退币");
	}

	@Override
	public void turnCrank() {
		System.out.println("糖果已经售尽");
	}

	@Override
	public void dispense() {
		System.out.println("糖果已经售尽");		
	}
	

}
