package com.example.demo.util;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class HttpClientUtil {

	/*public static final String SMS_URl = "http://112.74.15.132:8080/front/emergency/querySmsFormCredit2Go";// 开通存管获取即信的短信验证码url
	public static final String REGISTER_URL = "http://112.74.15.132:8080/front/emergency/borrowingRegist";// 同步立业贷注册url
	public static final String WOS_URl = "http://112.74.15.132:8080/front/emergency/whetherDepository";// 判断是否开通存管
	public static final String OPEN_STORAGE_URL = "http://112.74.15.132:8080/front/emergency/borrowerOpenAccount";// 开通存管	
	public static final String VEHICLE_INFORMATION = "http://116.62.230.202/api.php?s=/Xljj/get_asset_info";// 去ERP获取车辆信息
*/	public static final String PUSH_LOAN_INFO = "http://116.62.230.202/api.php?s=/Xljj/push_loan_info";// 推借款信息到erp
	public static final String PULL_LOAN_INFO = "http://112.74.15.132:8080/front/emergency/borrowPullBidStatus";//拉取立业贷信息
    private static final String submitUrl = "https://api.tongdun.cn/preloan/apply/v5";// 同盾submit方式API
    private static final String queryUrl = "https://api.tongdun.cn/preloan/report/v6";// 同盾query方式api
    private static final int WAIT_TIME = 500 * 1000;// 最大等待连接时间
    private static final String PARTNER_CODE = "liyedai";// 合作方标识
    private static final String PARTNER_KEY = "testkey";// 合作方密钥
    private static final String PARTNER_APP = "liyedai";// 应用名
    // utf-8 字符编码集
    public static final String CHARSET_UTF_8 = "utf-8";
    // HTTP类容类型
    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";
    // HTTP类容类型.相当于form 表单的形式提交数据
    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";
    // HTTP内容类型。相当于form表单的形式，提交数据
    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";
    // 连接管理器
    private static PoolingHttpClientConnectionManager pool;
    // 请求配置
    private static RequestConfig requestconfig;

    static {
        // 初始化请求配置
        try {
            // 初始化httpClientTest
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
            pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
            pool.setMaxTotal(20);
            // 设置最大路由
            pool.setDefaultMaxPerRoute(2);
            // 根据默认超时限制初始化requestConfig
            int socketTimeout = 10000;
            int connectTimeout = 10000;
            int connectionRequestTimeout = 10000;
            requestconfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)
                    .setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
            System.out.println("初始化client结束");

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        // 设置请求的超时时间
        requestconfig = RequestConfig.custom().setSocketTimeout(WAIT_TIME).setConnectTimeout(WAIT_TIME)
                .setConnectionRequestTimeout(WAIT_TIME).build();

    }

    //获取同盾 接口
    public static HttpPost getHttpPost() {
        //拼接URL
        String urlString = new StringBuilder().append(submitUrl).append("?partner_code=").append(PARTNER_CODE)
                .append("&partner_key=").append(PARTNER_KEY).append("&app_name=").append(PARTNER_APP).toString();
        HttpPost httpPost = null;

        httpPost = new HttpPost(urlString);

        return httpPost;
    }

    // 测试submitpost请求

    public void Test() {

    }

    // 发送get请求

    private static String TongdunHttpGet(HttpGet httpGet) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpGet.setConfig(requestconfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 可以获得响应头
            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
            // for (Header header : headers) {
            // System.out.println(header.getName());
            // }

            // 得到响应类型
            // System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

            // 判断响应状态
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;

    }

    // 获取httpclient的方法
    public static CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(pool)
                // 设置请求配置
                .setDefaultRequestConfig(requestconfig)
                // 设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();

        return httpClient;
    }

    // 发送post请求
    private static String sendHttpPost(HttpPost httpPost) {

        CloseableHttpClient httpClient = null;

        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpPost.setConfig(requestconfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 可以获得响应头
            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
            // for (Header header : headers) {
            // System.out.println(header.getName());
            // }

            // 得到响应类型
            // System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

            // 判断响应状态
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    // 发送get请求
    private static String sendHttpGet(HttpGet httpGet) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = getHttpClient();
            // 配置请求信息
            httpGet.setConfig(requestconfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            // 可以获得响应头
            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
            // for (Header header : headers) {
            // System.out.println(header.getName());
            // }

            // 得到响应类型
            // System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());

            // 判断响应状态
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
            }

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
                EntityUtils.consume(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;

    }

    /**
     * 发送post请求
     *
     * @param httpUrl
     * @return
     */

    public static String sendHttpPost(String httpUrl) {
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 get请求
     *
     * @param
     */

    public void testget() {
        String repponse = this.sendHttpGet("https://www.baidu.com/index.php?tn=monline_3_dg");

        System.out.println(repponse);
    }

    public static String sendHttpGet(String httpUrl) {
        // 创建get请求
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet);
    }

    /**
     * 261 * 发送 post请求（带文件） 262 * 263 * @param httpUrl 264 * 地址 265 * @param
     * maps 266 * 参数 267 * @param fileLists 268 * 附件 269
     */
    public static String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        if (maps != null) {
            for (String key : maps.keySet()) {
                meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
            }
        }
        if (fileLists != null) {
            for (File file : fileLists) {
                FileBody fileBody = new FileBody(file);
                meBuilder.addPart("files", fileBody);
            }
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:key1=value1&key2=value2)
     */
    public static String sendHttpPost(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            if (params != null && params.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(params, "UTF-8");
                stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
                httpPost.setEntity(stringEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     *
     * @param maps 参数
     */
    public static String sendHttpPost(String httpUrl, Map<String, String> maps) {
        String parem = convertStringParamter(maps);
        return sendHttpPost(httpUrl, parem);
    }

    /**
     * 发送 post请求 发送json数据
     *
     * @param httpUrl    地址
     * @param paramsJson 参数(格式 json)
     */
    public static String sendHttpPostJson(String httpUrl, String paramsJson) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            if (paramsJson != null && paramsJson.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
                stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
                httpPost.setEntity(stringEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求 发送xml数据
     *
     * @param httpUrl   地址
     * @param paramsXml 参数(格式 Xml)
     */
    public static String sendHttpPostXml(String httpUrl, String paramsXml) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            if (paramsXml != null && paramsXml.trim().length() > 0) {
                StringEntity stringEntity = new StringEntity(paramsXml, "UTF-8");
                stringEntity.setContentType(CONTENT_TYPE_TEXT_HTML);
                httpPost.setEntity(stringEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
     *
     * @param parameterMap 需要转化的键值对集合
     * @return 字符串
     */
    public static String convertStringParamter(Map parameterMap) {
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (parameterMap.get(key) != null) {
                    value = (String) parameterMap.get(key);
                } else {
                    value = "";
                }
                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }
        return parameterBuffer.toString();
    }
    
    /**
     * httpPost<BR>
     * 方法名：httpPost<BR>
     * 创建人：huangqi <BR>
     * 时间：2017年12月11日-下午4:45:51 <BR>
     * @param url
     * @param users
     * @return JSONObject<BR>
     * @exception <BR>
     * @since  1.0.0
    */
    public  JSONObject httpPost(String url,Object obj){
        return httpPost(url, obj, false);
    }
 
    
    /**
     * post请求<BR>
     * 方法名：httpPost<BR>
     * 创建人：huangqi <BR>
     * 时间：2017年12月11日-下午4:46:35 <BR>
     * @param url
     * @param users
     * @param noNeedResponse
     * @return JSONObject<BR>
     * @exception <BR>
     * @since  1.0.0
    */
    public static JSONObject httpPost(String url,Object obj, boolean noNeedResponse){
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", "123456789");
        jsonObject.put("password", "4564945613513");
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != obj) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSON.parseObject(str);
                } catch (Exception e) {
                	
                	
                }
            }
        } catch (IOException e) {
        	
        }
        return jsonResult;
    }

//    public static String sendTestHttpPost() throws Exception {
//        String  body ="{\"linkedMerchantId\":\"2088621969012844\",\"partnerId\":\"xdkj\",\"productId\":\"102006\",\"extParam\":{\"openId\":\"268818317566263422687859813\"},\"verifyKey\":\"8a12a3a20a454a7195913726db792adc\"}";
//        String  url="https://api.baiqishi.com/credit/zhima/search";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader("Content-Type", "application/json");
//        httpPost.setEntity(new StringEntity(body));
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        System.out.println(response.getStatusLine().getStatusCode() + "\n");
//        HttpEntity entity = response.getEntity();
//        String responseContent = EntityUtils.toString(entity, "UTF-8");
//        System.out.println(responseContent);
//        response.close();
//        httpClient.close();
//        return responseContent;
//    }


 
}
