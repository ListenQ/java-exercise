package com.example.demo;

public class Test5 {
	
	public static void main(String[] args) {
//		Double d= null;
//		BigDecimal b =new BigDecimal(d+"");
//		System.out.println(b.setScale(2, BigDecimal.ROUND_UP));
		int month = 0,day = 0;
		
		boolean result = month>12 | month <1 & day >31 | day <1;
		System.out.println(result);
    	
	}

}
