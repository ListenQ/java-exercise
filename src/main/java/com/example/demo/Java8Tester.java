package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Java8Tester {

	static String s1 = "132";
	final static String salutation = "Hello! ";

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO lambda表达式 -->js里的闭包
		List<String> names1 = new ArrayList<String>();
		names1.add("Google ");
		names1.add("Runoob ");
		names1.add("Taobao ");
		names1.add("Baidu ");
		names1.add("Sina ");

		System.out.println("排序之前是：" + names1);
		sortUsingJava8(names1);
		System.out.println("排序之后：" + names1);
		
		//TODO 并行排序
		int [] data = {4,12,1,3,5,7,9};
		Arrays.parallelSort(data);
		System.out.println(Arrays.toString(data));
		
		int [] data2 = {4, 16, 17, 20, 25, 32, 41};
		Arrays.parallelPrefix(data2, Integer :: sum);
		System.out.println(Arrays.toString(data2));

		// TODO 变量作用域 不能在 lambda 内部修改定义在域外的局部变量(非final变量是可以的)
		// lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改如 testMsg,而局外的变量是可以修改的 如s1
		String testMsg = "test";
		GreetingService greetService1 = message -> System.out.println(s1 + salutation + message+testMsg);
		greetService1.sayMessage("Runoob");
		//testMsg = "hahah";  //TODO 放开会编译报错 局部变量不能被后面修改
		s1 = "888";

		// TODO 方法引用
		List<String> names = new ArrayList<>();
		names.add("Google");
		names.add("Runoob");
		names.add("Taobao");
		names.add("Baidu");
		names.add("Sina");
		names.forEach(System.out::println);// 吧System.out.println这个方法当做参数传递

		// TODO 函数式接口
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println("输出所有数据：");
		eval(list, n -> true);
		System.out.println("输出所有的偶数：");
		eval(list, n -> n % 2 == 0);
		System.out.println("输出所有大于3的数：");
		eval(list, n -> n > 3);

		// TODO 默认方法和静态方法
		Vehicle vehicle = new Car();
		vehicle.print();

		// TODO 流 Stream stream串行流,parallelStream并行流
		List<String> strs = Arrays.asList("abs", "", "bc", "egf", "abcd", "jlk");
		List<String> collect = strs.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
		System.out.println(collect);

		Random rand = new Random();
		rand.ints().limit(10).forEach(System.out::println);
		//TODO 可变汇聚
		System.out.println("---------------------------------");
		rand.ints().limit(10).collect(() -> new ArrayList<Integer>(),(list1,item) ->list1.add(item),(list2,list3) ->list2.addAll(list3));
		
		// map 方法用于映射每个元素到对应的结果
		List<Integer> number = Arrays.asList(3, 2, 2, 3, 7, 3, 5, 75);
		List<Integer> maps = number.stream().map(i -> i * i).distinct().collect(Collectors.toList());
		System.out.println("平方数是：" + maps);

		// filter 方法用于通过设置的条件过滤出元素
		List<String> strings = Arrays.asList("abs", "", "bc", "egf", "abcd", "jlk");
		long count = strings.stream().filter(str -> str.isEmpty()).count();
		System.out.println("字符串为空的个数是：" + count);

		// sorted 方法用于对流进行排序
		Random random = new Random();
		random.ints().limit(10).sorted().forEach(System.out::println);

		// parallelStream 是流并行处理程序的代替方法
		List<String> strLists = Arrays.asList("abs", "", "bc", "egf", "abcd", "jlk");
		long num = strLists.parallelStream().filter(str -> str.isEmpty()).count();
		long reduce = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long::sum);
		System.out.println("字符串为空的数量有：" + num);

		// Collectors 类实现了很多归约操作
		List<String> strss = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		List<String> filtered = strss.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		System.out.println("筛选列表: " + filtered);
		String mergedString = strss.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
		System.out.println("合并字符串: " + mergedString);

		// 统计
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5, -10);
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("列表中最大的数 : " + stats.getMax());
		System.out.println("列表中最小的数 : " + stats.getMin());
		System.out.println("所有数之和 : " + stats.getSum());
		System.out.println("平均数 : " + stats.getAverage());

		// TODO Optional 类是一个可以为null的容器对象
		Integer value1 = null;
		Integer value2 = new Integer(10);
		// Optional.ofNullable - 允许传递为 null 参数
		Optional<Integer> a = Optional.ofNullable(value1);
		// Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
		Optional<Integer> b = Optional.of(value2);
		System.out.println(Java8Tester.sum(a, b));

		// TODO Nashorn 是一个javascript引擎   完全支持es5  可以java <=> javascript 相互调用
		//交互式编程 cmd打开控制台  输入 jjs print("hello")  quit()
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
		String name = "Runoob";
		Integer result = null;
		try {
			nashorn.eval("print('" + name + "')");
			result = (Integer) nashorn.eval("10 + 2");
		} catch (ScriptException e) {
			System.out.println("执行脚本错误: " + e.getMessage());
		}
		System.out.println(result.toString());
		
		//TODO 日期时间API
		Java8Tester test = new Java8Tester();
		test.testLocalDateTime();
		
		//时区的日期时间
		test.testZonedDateTime();
		
		//TODO Base64
		// 使用基本编码
		String base64Str = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
		System.out.println("Base64 基本编码："+base64Str);
		//解码
		byte [] base64Bytes = Base64.getDecoder().decode(base64Str);
		System.out.println("原始字符串是："+new String(base64Bytes,"UTF-8"));
		base64Str = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
		System.out.println("Base64编码字符串 url:"+base64Str);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; ++i) {
			sb.append(UUID.randomUUID().toString());
		}
		byte [] mimeBytes=sb.toString().getBytes("utf-8");
		String mimeStr = Base64.getMimeEncoder().encodeToString(mimeBytes);
		System.out.println("Base64 编码字符串 (MIME)："+mimeStr);
		
	}

	private static void sortUsingJava8(List<String> names) {
		Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
	}

	public static void eval(List<Integer> list, Predicate<Integer> predicate) {
		for (Integer n : list) {
			if (predicate.test(n)) {
				System.out.print(n + " ");
			}
		}
	}

	public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
		// Optional.isPresent - 判断值是否存在
		System.out.println("第一个参数值存在: " + a.isPresent());
		System.out.println("第二个参数值存在: " + b.isPresent());
		// Optional.orElse - 如果值存在，返回它，否则返回默认值
		Integer value1 = a.orElse(new Integer(0));
		// Optional.get - 获取值，值需要存在
		Integer value2 = b.get();
		return value1 + value2;
	}
	
	public void testLocalDateTime(){
		// 获取当前的日期时间
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("当前时间："+localDateTime);
		
		LocalDate date1 = localDateTime.toLocalDate();
		System.out.println("date1:"+date1);
		
		Month month = localDateTime.getMonth();
		int day = localDateTime.getDayOfMonth();
		int seconds = localDateTime.getSecond();
		System.out.println("月："+month+",日："+day+",秒："+seconds);
		
		LocalDateTime date2 = localDateTime.withDayOfMonth(10).withYear(2012);
		System.out.println("date2:"+date2);
		
		LocalDate date3 = LocalDate.of(2018, Month.AUGUST, 26);
		System.out.println("date3:"+date3);
		
		LocalTime date4 = LocalTime.of(22, 15);
		System.out.println("date4:"+date4);
		
		LocalTime date5 = LocalTime.parse("20:56:30");
		System.out.println("date5:"+date5);
	}
	
	public void testZonedDateTime(){
		ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
		System.out.println("时区日期-date1:"+date1);
		
		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId:"+id);
		
		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("当期时区："+currentZone);
	}

}

interface GreetingService {
	void sayMessage(String message);
}

interface Converter<T1, T2> {
	void convert(int i);
}

// TODO 默认方法和静态方法
interface Vehicle {
	default void print() {
		System.out.println("我是一辆车");
	}

	static void blowHorn() {
		System.out.println("按喇叭!!!");
	}
}

interface FourWheeler {
	default void print() {
		System.out.println("我是一辆四轮车!");
	}
}

class Car implements Vehicle, FourWheeler {
	public void print() {
		Vehicle.super.print();
		FourWheeler.super.print();
		Vehicle.blowHorn();
		System.out.println("我是一辆汽车!");
	}
}
