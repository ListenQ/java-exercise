package com.example.demo.design.patterns.decorator;

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
