package com.example.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.vo.SubscribedDto;

public class Test20 {
	
	public static void main(String[] args) {
		List<SubscribedDto> list = new ArrayList<>();
		SubscribedDto dto = new SubscribedDto();
		dto.setTs("HK");
		dto.setCode("01256");
		dto.setName("卓锐证券");
		dto.setType(2);
		dto.setMinPrice(BigDecimal.ONE);
		dto.setMaxPrice(BigDecimal.ONE);
		dto.setHandlingFee(BigDecimal.valueOf(100));
		dto.setVolunit(1000);
		dto.setEndTime(System.currentTimeMillis());
		list.add(dto);
		System.out.println(JSONObject.toJSONString(list));
	}

}
