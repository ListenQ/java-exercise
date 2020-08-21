package com.example.demo.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test6 {

	
	public static void main(String[] args) {
		
		List<Person> list = new ArrayList<Test6.Person>();
		list.add(new Person("王五", "sdfssd", 22));
		list.add(new Person("逗比", "sdfssd", 22));
		list.add(new Person("李四", "sdfssd", 21));
		list.add(new Person("张三", "sdfssd", 20));
		list.add(new Person("嘻嘻", "sdfssd", 20));
		list.add(new Person("哈哈", "sdfssd", 21));
		list.add(new Person("傻吊", "sdfssd", 23));
		
		LinkedHashMap<Integer,List<Person>> linkHashmap = new LinkedHashMap<>();
		Map<Integer, List<Person>> personGroups = list.parallelStream().collect(Collectors.groupingBy(Person::getAge));
		personGroups.putIfAbsent(22, null);
		personGroups.putIfAbsent(23, null);
		personGroups.putIfAbsent(24, null);
		personGroups.entrySet().parallelStream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEachOrdered(m ->linkHashmap.put(m.getKey(), m.getValue()));
		
		Iterator<?> it = linkHashmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
			System.out.println("Age " + persons.getKey() + " = " + persons.getValue());
		}
		
		//根据条件分组
		Map<Boolean, List<Person>> collect = list.parallelStream().collect(Collectors.groupingBy(e ->fetchGroupByKey(e)));
		System.out.println("条件分组:"+collect.get(false));
		
		//List 转为 Map<k,v> key重复会出现IllegalStateException异常
		Map<String, Person> collect2 = list.stream().collect(Collectors.toMap(Person::getName, p -> p));
		System.out.println("转map对象："+collect2);
		
		//List 转为 Map<k,v> key重复不会出异常，后者覆盖前者
		Map<String, Person> collect3 = list.stream().collect(Collectors.toMap(Person::getName, p -> p , (p1,p2) -> p2));
		System.out.println("转map对象："+collect3);
		
		list.sort(Comparator.comparing(Person::getAge).reversed());
		Integer total = list.subList(3, list.size()).parallelStream().map(Person::getAge).reduce(0,Integer::sum);
		List<Person> subList = list.subList(0, 3);
		subList.add(new Person("其他之和", "sdfssd", total));
		System.out.println(subList);
	}
	
	/**
	 * 必须为静态的方法
	 */
	private static boolean fetchGroupByKey(Person person) {
		return person.getAge() > 20;
	}
	
	static class Person{
		private String name;
		private String pwd;
		private Integer age;
		
		public Person(String name, String pwd, Integer age) {
			this.name = name;
			this.pwd = pwd;
			this.age = age;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", pwd=" + pwd + ", age=" + age + "]";
		}
		
	}
}
