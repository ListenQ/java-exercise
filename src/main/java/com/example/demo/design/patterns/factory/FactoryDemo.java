package com.example.demo.design.patterns.factory;

/**
 * 
 * FactoryDemo<BR>
 * 创建人：zhangqi <BR>
 * 时间：2020年3月28日-下午5:41:03 <BR>
 * @version 1.0.0
 * 
 */
public class FactoryDemo {

	
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		
		shape1.draw();
		
		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		
		shape2.draw();
		
		Shape shape3 = shapeFactory.getShape("SQUARE");
		
		shape3.draw();
	}
}
