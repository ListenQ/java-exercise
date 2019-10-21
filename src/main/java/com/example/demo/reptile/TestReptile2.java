package com.example.demo.reptile;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.Sqllink;

/**
 * 测试爬虫
 * TestReptile<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年9月19日-下午8:13:41 <BR>
 * @version 1.0.0
 * 
 */
public class TestReptile2{
	
	public static String strUrl = "https://656447.com:8088/anls-api/data/xyft2/numTrend/180.do";
	
	public static boolean saveFlag = true;
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		
		String line = HttpClientUtil.sendHttpGet(strUrl);
		System.err.println("耗时:"+(System.currentTimeMillis() - start));
		
		if(StringUtils.isNotBlank(line)) {
			JSONObject jsonObject = JSON.parseObject(line);
			JSONArray jsonArray = jsonObject.getJSONArray("bodyList");
			System.out.println("过滤前数据:\n"+jsonArray);
			JSONArray filter2 = filter2(jsonArray);
			System.out.println(filter2);
			if(saveFlag) {
				Sqllink.saveToDb(jsonArray);
			}
		}
	}
	
	private static JSONArray filter2(JSONArray jsonObject) {
		Iterator<Object> iterator = jsonObject.iterator();
		while(iterator.hasNext()) {
			JSONObject json = (JSONObject)iterator.next();
			if(json ==null || json.isEmpty()) {
				iterator.remove();
			}else if (!json.getString("issue").contains("20191010")){
				iterator.remove();
			}
		}
		return jsonObject;
	}
	
}
