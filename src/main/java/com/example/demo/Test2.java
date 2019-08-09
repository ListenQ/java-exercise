package com.example.demo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.example.demo.mult.thread.countdownlatch.TAbract;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class Test2 {
	
	private static List<TAbract> t;
	
	public void setT(List<TAbract> t) {
		this.t = t;
	}
	private static final String [][] transactionTime = {{"09:30:00","11:30:00"},{"13:00:00","15:00:00"}}; 

	public static void main(String[] args) throws InterruptedException, ParseException {
//		long number = t();
//		System.out.println("change最大的数是："+number);
		
//		String str= "{messageId=adfasdfasdf,maqTag=TC-HD-OERE,topic=YFDGP-DFS-DG}".replaceAll("\\{|\\}", "");
//		String[] strarr = str.split(",");
//		Map<String, Object> map = new HashMap<>();
//		for (String string : strarr) {
//			String [] keyarr = string.split("=");
//			map.put(keyarr[0], keyarr[1]);
//		}
//		System.out.println(map.get("messageId"));
		
		/*Test2 test = new Test2();
		test.setT(Arrays.asList(new T1(),new T2(),new T3()));
		
		ExecutorService executors= Executors.newFixedThreadPool(3);//这种写法不合理，应当用ThreadPoolExecutor
		for (final TAbract tt : t) {
			executors.execute(new ThreadDemo(tt));
		}
		executors.shutdown();*/
		
		/*try {
			CountDownLatch c = new CountDownLatch(2); // join
			for (int i=0;i< 2;i++) {
				executors.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(5000l);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName());
						c.countDown();
					}
				});
			}
			c.await(1 , TimeUnit.SECONDS);
			System.out.println(3);
		}catch(Exception e) {}
		finally {
			executors.shutdown();
		}
		System.out.println(4);*/
		
		
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("name1", "dsf");
		map.put("time", "09:00:03");
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name1", "zqq");
		map1.put("time", "13:00:03");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name1", "zqqasd");
		map2.put("time", "13:00:03");
		
		Map<String, Map<String, String>> mapp = new HashMap<>();
		mapp.put("st", map);
		mapp.put("st2", map1);
		mapp.put("st3", null);
		mapp.put("st4", map2);
		JSONObject json = JSONObject.fromObject(mapp);
		System.out.println(dataFilter(json));*/
		long start = System.currentTimeMillis();
		List<String> strs = initMinuteTime();
		System.out.println("花费了:"+(System.currentTimeMillis()-start));
		System.out.println(strs);
	}
	
	
	@SuppressWarnings("serial")
	private static List<String> initMinuteTime() throws ParseException {
		boolean flag = true;
		List<String> times = new ArrayList<String>() {{
			add("09:30");
		}};
		String start = "09:30";
		while(flag) {
			Date startTime = DateUtils.parseDate(start.toString(), "HH:mm");
			String nextTime = DateFormatUtils.format(DateUtils.addMinutes(startTime, 4), "HH:mm");
			if(nextTime.compareTo("11:30") <=0 || (nextTime.compareTo("15:00") <=0) && nextTime.compareTo("13:00") >= 0) {
				times.add(nextTime);
			}
			if((nextTime.compareTo("11:30") >0 && nextTime.compareTo("13:00") < 0)) {
				start = "12:56";
			}else {
				start= nextTime;
			}
			if(nextTime.compareTo("15:00") >0) {
				flag = false;
			}
		}
		return times;
	}
	
	
	
	private static long t() {
		long num = 0;
		try {
			long [] array = {59,3,4,7};
			 for(int i = 0;i<array.length;i++) {
				 if(i <array.length-1 && array[i] > array[i+1]) {
					 if(array[i] > num) {
						 num = array[i];
					 }
				 }
			 }
			 System.out.println("最大的数："+num);
			 return num;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			num = 726;
		}
		return num;
	}
	
	public static JSONObject dataFilter(JSONObject data) {
		if (data != null) {
			JsonConfig config = new JsonConfig();
			
			config.setJsonPropertyFilter(new PropertyFilter() {
				
				@Override
				public boolean apply(Object object, String property, Object value) {
					if(value!=null && value instanceof JSONObject) {
						JSONObject json = JSONObject.fromObject(value);
						if(StringUtils.isNotBlank(json.getString("time"))) {
							long start = System.currentTimeMillis();
							boolean result = isInDate(json.getString("time"));
							System.out.println("花费了:"+(System.currentTimeMillis()-start));
							if(!result) {
								return true;
							}
						}
					}
					return false;
				}
			});
			return JSONObject.fromObject(data, config);
		}
		return null;
	}
	
	private static boolean isInDate(String time) {
		/*int [] result = {0};
		Arrays.asList(transactionTime).forEach(t ->{
			if(time.compareTo(t[0]) >=0 && time.compareTo(t[1]) <=0) {//这种写法慢个二三十毫秒
				result[0] = 1;
				return;
			}
		});
		return result[0] > 0;*/
		if((time.compareTo("09:30:00")>=0 && time.compareTo("11:30:00") <=0)
				|| (time.compareTo("13:00:00") >=0 && time.compareTo("15:00:00") <=0)){
			return true;
		}
		return false;
	}

}
