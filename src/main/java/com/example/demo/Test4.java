package com.example.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;

public class Test4 {
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name1", "dsf");
		map.put("time", "08:30:03");
		map.put("date", "2019-08-15");
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name1", "zqq");
		map1.put("time", "11:30:59");
		map1.put("date", "2019-08-15");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name1", "zqqasd");
		map2.put("time", "10:00:03");
		map2.put("date", "2019-08-19");
		
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("name1", "zqqasd");
		map3.put("time", "15:00:03");
		map3.put("date", "2019-08-15");
		
		Map<String, Map<String, String>> mapp = new HashMap<>();
		mapp.put("sz001", map);
		mapp.put("sz002", map1);
		mapp.put("st003", null);
		mapp.put("sh004", map2);
		mapp.put("sh005", map3);
		long start = System.currentTimeMillis();
		JSONObject object = (JSONObject) JSON.toJSON(mapp);
		System.out.println(filter(object));
//		System.out.println(filter2(object));
//		System.out.println(JSON.toJSONString(mapp, dataFilter));
		System.out.println("花费了:"+(System.currentTimeMillis()-start));
	}
	
	private static JSONObject filter(JSONObject jsonObject) {
		Iterator<String> it = jsonObject.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			JSONObject json = jsonObject.getJSONObject(key);
			if(json == null || json.isEmpty()) {
				it.remove();
			}else if (StringUtils.isBlank(json.getString("time")) || isNotInTime(json.getString("time"))) {
				it.remove();
			} else if (StringUtils.isBlank(json.getString("date")) || isNotInDate(json.getString("date"))) {
				it.remove();
			}
		}
		return jsonObject;
	}
	
	private static JSONObject filter2(JSONObject jsonObject) {
		JSONObject data = new JSONObject();
		Set<Entry<String, Object>> keySets = jsonObject.entrySet();
		keySets.parallelStream().parallel().filter(enj ->{
			JSONObject json = (JSONObject)enj.getValue();
			if(json ==null || json.isEmpty()) {
				return false;
			}else if (StringUtils.isBlank(json.getString("time")) || isNotInTime(json.getString("time"))){
				return false;
			}else if (StringUtils.isBlank(json.getString("date")) || isNotInDate(json.getString("date"))) {
				return false;
			}
			return true;
		}).parallel().map(obj ->{
			data.put(obj.getKey(),obj.getValue());
			return obj;
		});
		return data;
	}
	
	private static boolean isCorrect(String key,JSONObject jsonObject) {
		JSONObject json = jsonObject.getJSONObject(key);
		if(json ==null || json.isEmpty()) {
			return false;
		}else if (StringUtils.isBlank(json.getString("time")) || isNotInTime(json.getString("time"))){
			return false;
		}else if (StringUtils.isBlank(json.getString("date")) || isNotInDate(json.getString("date"))) {
			return false;
		}
		return true;
	}
	
	private static PropertyFilter dataFilter = new PropertyFilter() {
		@Override
		public boolean apply(Object object, String name, Object value) {
			if (value != null && value instanceof Map && (name.startsWith("sh")|| name.startsWith("sz"))) {
				JSONObject json = (JSONObject) JSON.toJSON(value);
				if (StringUtils.isBlank(json.getString("time")) || isNotInTime(json.getString("time"))) {
					return false;
				} else if (StringUtils.isBlank(json.getString("date")) || isNotInDate(json.getString("date"))) {
					return false;
				}
			}
			return true;
		}
	};
	
	
	
	
	private static boolean isNotInTime(String time) {
		/*int [] result = {0};
		Arrays.asList(transactionTime).forEach(t ->{
			if(time.compareTo(t[0]) >=0 && time.compareTo(t[1]) <=0) {//这种写法慢个二三十毫秒
				result[0] = 1;
				return;
			}
		});
		return result[0] > 0;*/
		if(time.compareTo("09:30:00")<0 || (time.compareTo("13:00:00") < 0 && time.compareTo("11:30:59") >0)
				|| time.compareTo("15:00:59") > 0){
			return true;
		}
		return false;
	}
	
	private static boolean isNotInDate(String date) {
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd").compareTo(date)!=0;
	}

}
