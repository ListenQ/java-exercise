package com.example.demo.design.patterns.decorator;

/**
 * 裝飾器设计模式
 * ShapeDecorator<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年7月5日-下午5:53:05 <BR>
 * @version 1.0.0
 * 
 */
public abstract class ShapeDecorator implements Shape{
	
	protected Shape shape;
	
	public ShapeDecorator(Shape shape) {
		this.shape =shape;
	}
	
	public void draw() {
		shape.draw();
	}

}
