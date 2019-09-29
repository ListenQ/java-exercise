package com.example.demo.reptile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

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
public class TestReptile{
	
	public static String strUrl = "https://m.wxccp33.com/v1/lottery/openResult?lotteryCode=1309&dataNum=180";
	
	public static boolean saveFlag = true;
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		URL url = null;
		BufferedReader br=null;
		
//		url = new URL(strUrl+num);
//		URLConnection conn=url.openConnection();
//		InputStream is=conn.getInputStream();
//		br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
		String line = HttpClientUtil.sendHttpGet(strUrl);
		System.err.println("耗时:"+(System.currentTimeMillis() - start));
		
		if(StringUtils.isNotBlank(line)) {
			JSONObject jsonObject = JSON.parseObject(line);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			System.out.println(jsonArray);
			if(saveFlag) {
				Sqllink.saveToDb(jsonArray);
			}
		}
		IOUtils.closeQuietly(br);
	}
	
}
