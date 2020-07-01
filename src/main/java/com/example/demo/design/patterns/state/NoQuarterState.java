package com.example.demo.design.patterns.state;

/**
 * 没有硬币的状态
 * NoQuarterState
 * @author zhangqi 
 * @date 2020年6月30日-下午5:46:52
 * 
 */
public class NoQuarterState extends State{
	
	GumballMachine gumballMachine;
	
	public NoQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("你投入了一个硬币");
		 //转换为有硬币状态
        gumballMachine.setState(gumballMachine.hasQuarterState);
	}

	@Override
	public void ejectQuarter() {
		System.out.println("没有硬币，无法弹出");
	}

	@Override
	public void turnCrank() {
		System.out.println("请先投币");
	}

	@Override
	public void dispense() {
		System.out.println("没有投币，无法发放糖果");
	}

}
