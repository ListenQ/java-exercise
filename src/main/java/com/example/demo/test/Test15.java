package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test15 {
	
	public static void main(String[] args) {
		try {
			System.out.println(1/0);
		} catch (Throwable e) {
			log.error("异常:{},{}","45",e);
		}
	}

}
