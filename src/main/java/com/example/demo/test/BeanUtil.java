package com.example.demo.test;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * bean操作工具类
 * BeanUtil<BR>
 * -创建人：zhangqi <BR>
 * -时间：2019年8月7日-下午6:45:24 <BR>
 * @version 1.0.0
 * 
 */
public class BeanUtil {
	
	
	
	public static void copyPropertiesIgnoreNull(Object src,Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}
	
	
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		
		Set<String> emptyNames = new HashSet<>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if(srcValue == null) emptyNames.add(pd.getName());
		}
		
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
	
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public static void copyListPropertiesIgnoreNull(List src,List target,Class clazz) {
		if(src !=null && src.size() !=0) {
			for(Object object : src) {
				try {
					Object object2 = clazz.newInstance();
					copyPropertiesIgnoreNull(object, object2);
					target.add(object2);
				}catch(InstantiationException e) {
					e.printStackTrace();
				}catch(IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	

}
