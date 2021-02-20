package com.example.demo.util;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.test.MD5Util;

import lombok.extern.slf4j.Slf4j;


/**
 * 短信发送
 * @author zhuyl
 *
 */
@Slf4j
public class SendSmsUtils {
    //登录账号
    public static String USER_NAME = "JJ3662";
    //登录密码
    public static String PASSWORD = "251411";
	/*
	 * private static String smsRootPath;
	 * 
	 * @Value("${serverconfig.sms-rootpath}") public void setSmsRootPath(String
	 * smsRootPath) { SendSmsUtils.smsRootPath = smsRootPath; }
	 */
    
    public static String encryptionMd5(String mobile,String content) {
    	
    	String uploadUrl = "http://43.240.124.85:8901/sms/v2/std/batch_send";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {

            HttpPost httpPost = new HttpPost(uploadUrl);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE).setCharset(CharsetUtils.get("UTF-8"));
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	    	String date=sdf.format(new Date());
	    	String timestamp=date.substring(date.length()-10, date.length());
	    	String plainText=USER_NAME+"00000000"+PASSWORD+timestamp;	    	
	    	String toMD5= MD5Util.toMD5(plainText);
	    	String result="{\"userid\":\""+USER_NAME+"\",\"pwd\":\""+toMD5+"\",\"mobile\":\""+mobile+"\",\"content\":\""
	    	+URLEncoder.encode(content)+"\",\"timestamp\":\""+timestamp+"\",\"svrtype\":\"\",\"exno\":\"\",\"custid\":\"\",\"exdata\":\"\"}";
            //将字符串转换成jsonObject对象
    		JSONObject sapJson = JSON.parseObject(result);
//    		builder.addTextBody("result", sapJson.toJSONString()); 
    		//builder.addBinaryBody("result", result); 
            httpPost.setHeader("Accept", "application/json");
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String resp = null;
            if (responseEntity != null) {
            	resp = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
            log.info("上传返回报文：{}", resp);
        }catch (Exception e) {
				// TODO: handle exception
		}
    	String retInt = "";
        return retInt;
    }

    public static void main(String[] args) {
       // SendSmsUtils ftp =new SendSmsUtils(); 
        encryptionMd5("18201954974","恭喜你中了一百万!");
//    	System.out.println(MD5Util.toMD5("123456"));
    }
}
