package com.example.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.hutool.extra.cglib.CglibUtil;
import lombok.Data;
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
		
//		CglibUtil.copy(p, u);
		
//		System.out.println(u);
		
		
		List<User> list = new ArrayList<>();
		list.add(u);
		list.stream().map(m ->{
			if (m.getAge() > 20) {
				System.out.println("进来了");
				return false;
			}
			return m;
		}).collect(Collectors.toList());
	}
	
	@Getter
	@Setter
	public static class User {
		private Integer age;
		
		private String name;
		
		private String address;

		@Override
		public String toString() {
			return "User [age=" + age + ", name=" + name + ", address=" + address + "]";
		}
		
	}
	
	@Data
	static class Person {
		private String name;
		
		private String address;
		
		@JsonProperty("amount")
		private BigDecimal amount;

		@Override
		public String toString() {
			return "Person [name=" + name + ", address=" + address + ", amount=" + amount + "]";
		}
		
	}

}
