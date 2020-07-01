package com.example.demo.design.patterns.state;

public class SoldState extends State{
	
	GumballMachine gumballMachine;
	
	public SoldState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}

	@Override
	public void insertQuarter() {
		System.out.println("已投币，请等待糖果");
        returnQuarter();
	}

	@Override
	public void ejectQuarter() {
		System.out.println("无法退币，正在发放糖果，请等待");		
	}

	@Override
	public void turnCrank() {
		System.out.println("已按过曲轴，请等待");		
	}

	
	@Override
	public void dispense() {
		 int candyCount = gumballMachine.getCandyCount();
		 if (candyCount > 0) {
			 System.out.println("分发一颗糖果");
			 candyCount --;
			 gumballMachine.setCandyCount(candyCount);
			 if (candyCount > 0) {
				 gumballMachine.setState(gumballMachine.noQuarterState);
				 return;
			 }
		 }
		 System.out.println("抱歉，糖果已售尽");
		 gumballMachine.setState(gumballMachine.soldOutState);
	}
    
}
