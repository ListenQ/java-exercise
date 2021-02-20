package com.example.demo.test;

import cn.hutool.extra.cglib.CglibUtil;
import lombok.Getter;
import lombok.Setter;

public class Test16 {
	
	public static void main(String[] args) {
		User u = new User();
		u.setAge(21);
		u.setName("李四");
		
		Person p = new Person();
//		p.setName(null);
		p.setAddress("深圳南山");
		
		CglibUtil.copy(p, u);
		
		System.out.println(u);
	}
	
	@Getter
	@Setter
	static class User {
		private Integer age;
		
		private String name;
		
		private String address;

		@Override
		public String toString() {
			return "User [age=" + age + ", name=" + name + ", address=" + address + "]";
		}
		
	}
	
	@Getter
	@Setter
	static class Person {
		private String name;
		
		private String address;

		@Override
		public String toString() {
			return "Person [name=" + name + ", address=" + address + "]";
		}
	}

}
