package com.example.demo.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.example.demo.cp.Lucky;

public enum PlateFormEnum {
	
	/**
	 * h5
	 */
	ONE("1") {
		@Override
		public void settCase(Lucky dto) {
			dto.setBigSmall("n1");
		}
	},
	
	/**
	 * pc
	 */
	TWO("2") {

		@Override
		public void settCase(Lucky dto) {
			dto.setBigSmall("N2");
		}
	};
	
	
	
	
	private final String value;
	// 定义一个静态map 加载类时首先既初始化，执行步骤一
    private static Map<String, PlateFormEnum> map = new HashMap<>();
	
    /**
     * 静态代码块，根据类加载顺序，仅在静态变量后进行加载，在步骤一之后进行加载
     */
    static {
        for (PlateFormEnum demo : PlateFormEnum.values()) {
            map.put(demo.getValue(), demo);
        }
    }
    
    
	PlateFormEnum(String value) {
		this.value = value;
	}
	
	
	
	/**
     * 根据类型查找对应的枚举类
     *
     * @param type
     * @return
     */
    public static PlateFormEnum getMethodByType(String type) {
        // 此处根据自己代码中的类型判断进行判断，是否有相应的枚举，本文限制类型只能为1-3
        if (StringUtils.isBlank(type)) {
            // 下面是自定义异常，也可以根据需求自定义实现业务
            throw new RuntimeException();
        }
        return map.get(type);
    }
	
	
	public String getValue() {
		return value;
	}

	public abstract void settCase(Lucky dto);
	
	
	

	
	
	
	
	

}
