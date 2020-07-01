package com.example.demo.design.patterns.state;

/**
 * 口香糖机
 * GumballMachine
 * @author zhangqi 
 * @date 2020年6月30日-下午5:49:57
 * 
 */
public class GumballMachine extends State{
	
	public State noQuarterState = new NoQuarterState(this);
    public State hasQuarterState = new HasQuarterState(this);
    public State soldState = new SoldState(this);
    public State soldOutState = new SoldOutState(this);
    
    private State state = soldOutState;
    private int candyCount = 0;
    
    public GumballMachine(int count) {
    	this.candyCount = count;
    	if (count > 0) {
    		setState(noQuarterState);
    	}
	}

	@Override
	public void insertQuarter() {
		state.insertQuarter();
	}

	@Override
	public void ejectQuarter() {
		state.ejectQuarter();
	}

	@Override
	public void turnCrank() {
		state.turnCrank();
	}

	@Override
	public void dispense() {
		state.dispense();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getCandyCount() {
		return candyCount;
	}

	public void setCandyCount(int candyCount) {
		this.candyCount = candyCount;
	}
	
	

}
