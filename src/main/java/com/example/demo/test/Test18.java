package com.example.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//Listenq:zq19940726@github.com/ListenQ/java-exercise.git

import com.example.demo.test.Test16.Person;

import lombok.Getter;

public class Test18 {
	
	public static void main(String[] args) {
		Person person = new Test16.Person();
		person.setAddress("撒旦发");
		person.setName("哈哈哈");
		Object c = person;
		
//		System.out.println(JacksonUtil.toJSon(c));
		
		person.setAmount(new BigDecimal("236.5454"));
//		System.out.println(person.getAmount().negate());
		
		// 1 2 持有 3已经清仓 4 中签IPO
		List<Person> a = new ArrayList<>();
		a.add(new Test16.Person("00700", "撒旦法", BigDecimal.ONE));
		a.add(new Test16.Person("03690", "阿斯蒂芬", BigDecimal.ZERO));
		a.add(new Test16.Person("02318", "gg", BigDecimal.TEN));
		List<Person> b = new ArrayList<>();
		b.add(new Test16.Person("00700", "asdf", BigDecimal.TEN));
		b.add(new Test16.Person("03690", "wetr", BigDecimal.ONE));
		b.add(new Test16.Person("00005", "hfrg", BigDecimal.ZERO));
		
		
//		List<Person> collect = a.stream().filter(l -> b.contains(l)).collect(Collectors.toList());
//		System.out.println(collect);
		//取并集
		List<Person> collect2 = Stream.of(a,b).flatMap(x -> x.stream()).filter(j -> a.stream().map(Person::getName).collect(Collectors.toList()).contains(j.getName())).collect(Collectors.toList());
		System.out.println(collect2);
		
		Optional.ofNullable(a).orElse(new ArrayList<>()).addAll(b);
//		System.out.println(a);
		
		
		IntStream.range(1,3).boxed().collect(Collectors.toMap(Function.identity(), i -> i));
		
	}
	
	
	@Getter
	static enum StockMarketEnum{
		HK(1, "港股"), US(2, "美股"), A(3, "沪深");
		private Integer market;
		private String desc;
		
		
		StockMarketEnum(Integer market, String desc) {
			this.market = market;
			this.desc = desc;
		}

	}
	
	
	

}
