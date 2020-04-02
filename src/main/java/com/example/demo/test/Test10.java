package com.example.demo.test;

import com.example.demo.cp.Lucky;
import com.example.demo.enums.PlateFormEnum;

public class Test10 {
	
	public static void main(String[] args) {
		Lucky lucky = new Lucky("1");
		PlateFormEnum.getMethodByType(lucky.getType()).settCase(lucky);
		
		
		System.out.println(lucky);
	}

}
