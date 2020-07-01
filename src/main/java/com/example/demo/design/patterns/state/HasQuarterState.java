package com.example.demo.design.patterns.state;

/**
 * 投硬币的状态
 * HasQuarterState
 * @author zhangqi 
 * @date 2020年7月1日-下午5:51:46
 * 
 */
public class HasQuarterState extends State{
	
	GumballMachine gumballMachine;
	
	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("请不要重复投币！");
        returnQuarter();		
	}

	@Override
	public void ejectQuarter() {
		returnQuarter();
		gumballMachine.setState(gumballMachine.noQuarterState);
	}

	@Override
	public void turnCrank() {
		System.out.println("转动曲轴，准备发糖");
		gumballMachine.setState(gumballMachine.soldState);
	}

	@Override
	public void dispense() {
		System.out.println("this method don't support");
	}

}
