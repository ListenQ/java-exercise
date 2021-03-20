package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * json工具类
 *
 * @author zhouyuan
 * @describe TODO
 * @version: V1.0
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "AlibabaClassNamingShouldBeCamel"})
public class JSONUtil {
    /***
     * 对象转json串
     *
     * @describe 如果列为空则补序列化
     * @param obj
     * @return
     * @throws IOException
     * @author zhouyuan - 2015年3月13日 上午10:07:21
     */
    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
    }

    public static String toJSONString(Object obj) {
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * json转对象
     *
     * @param clazz
     * @param json
     * @return
     * @throws IOException
     * @describe TODO
     * @author zhouyuan - 2015年3月13日 上午10:07:51
     */
    public static <T> T toJSONObject(String json, Class<T> clazz) throws IOException {
        // ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    /**
     * json转数组
     *
     * @param clazz
     * @param jsonArrStr
     * @return
     * @throws IOException
     * @describe TODO
     * @author zhouyuan - 2015年3月17日 下午5:03:53
     */
    public static List toJSONArray(Class clazz, String jsonArrStr)
            throws IOException {
        JSONArray jsonArr = JSONArray.parseArray(jsonArrStr);
        List list = new ArrayList();
        for (int i = 0; i < jsonArr.size(); i++) {
            //如果是String,int之类的java定义类型，直接获取
            if (isJavaClass(clazz)) {
                list.add(jsonArr.get(i));
            } else {
                //修改某个具体对象反序列化方式，支持JsonProperties注解
                list.add(mapper.readValue(jsonArr.getString(i), clazz));
            }
        }
        return list;
    }

    public static boolean isJavaClass(Class<?> clz) {
        return clz != null && clz.getClassLoader() == null;
    }

    /**
     * json根据key获取对应的值
     *
     * @param content json串
     * @param key     key
     * @return
     */
    public static Object getValue(String content, String key) {
        JSONObject jsonObject = JSONObject.parseObject(content);
        String[] keys = key.split("[.]");
        for (String paramKey : keys) {
            if (jsonObject == null) {
                return null;
            }
            try {
                jsonObject = (JSONObject) jsonObject.get(paramKey);
            } catch (ClassCastException e) {
                return jsonObject.get(paramKey);
            }

        }
        return jsonObject;
    }

    /**
     * 将对象专成指定类型对象，主要应用与map，json对象专程自定义对象
     *
     * @author: zhouyuan
     * @create: 2019/8/28
     **/
    public static <T> T convertValue(Object fromValue, Class<T> toValueType)
            throws IllegalArgumentException {
        return mapper.convertValue(fromValue, toValueType);
    }
    
    
    /**
     * (将对象专成指定类型对象，主要应用与List，json对象专程自定义对象)<BR>
     * @methodName：convertListValue<BR>
     * @author： zhangqi <BR>
     * @date：2019年9月12日-下午3:15:50 <BR>
     * @param formValue
     * @param toValueType
     * @return List<T><BR>
    */
    public static <T> List<T> convertListValue(Object formValue, Class<T> toValueType){
    	CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, toValueType);
    	return mapper.convertValue(formValue, collectionType);
    }


}
