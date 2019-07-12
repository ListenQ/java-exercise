package com.example.demo.design.patterns.decorator;

/**
 * 装饰器设计模式测试
 * Demo<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年7月9日-上午9:49:14 <BR>
 * @version 1.0.0
 * 
 */
public class Demo {
	
	public static void main(String[] args) {
		Shape shape = new Circle();
		ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
		ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
		System.out.println("Circle with normal border");
		shape.draw();
		
		System.out.println("\n Circle of red border");
		
		redCircle.draw();
		
		System.out.println("\n Rectangle of red border");
		redRectangle.draw();
	}

}
