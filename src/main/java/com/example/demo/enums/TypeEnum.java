package com.example.demo.enums;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.StrUtil;

public enum TypeEnum {
	
	ONE("one","one12356"){

		@Override
		protected void apply(String type, String num) {
			System.out.println("1");
		}
	},
	
	ONE_D("one","d12356"){

		@Override
		protected void apply(String type, String num) {
			System.out.println("1-2");
		}
	},
	
	TWO("two","two456789"){

		@Override
		protected void apply(String type, String num) {
			System.out.println("2");
		}
	},
	
	THREE(null,null){

		@Override
		protected void apply(String type, String num) {
			System.out.println("3");
		}
	};
	
	private String type;
	
	private String num;
	
	
	TypeEnum(String type,String num) {
		this.type = type;
		this.num = num;
	}
	
	private static Map<String,TypeEnum> map = new HashMap<>();
	
	static {
		
		for (TypeEnum e : TypeEnum.values()) {
			map.put(String.format("%s:%s", e.getType(),e.getNum()), e);
		}
	}
	
	protected abstract void apply(String type,String num);

	public String getType() {
		return type;
	}

	public String getNum() {
		return num;
	}
	
	@Deprecated
	public static TypeEnum execute(String type,String num) {
		for(TypeEnum t: TypeEnum.values()) {
			if ("one".equals(t.getType()) && t.getType().equals(type)) {
				if (null!= num && num.startsWith(t.getNum())) {
					
				}
			} else {
				if("two".equals(t.getType()) && t.getType().equals(type)) {
					
				}
			}
		}
		return null;
	}

	public static TypeEnum getTypeEnum(String type, String num) {
		for(TypeEnum t: TypeEnum.values()) {
			if (t.getType().equals(type) || t.getType().contains(type)) {
				if (num !=null && num.contains(t.getNum())) {
					return map.get(String.format("%s:%s", type,t.getNum()));
				}
			}else {
				return map.get(String.format("%s:%s", type,t.getNum()));
			}
		}
		return null;
	}
	
}
