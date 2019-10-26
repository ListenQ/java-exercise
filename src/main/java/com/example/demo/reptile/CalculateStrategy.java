package com.example.demo.reptile;

import java.util.List;
import java.util.Map;

import com.example.demo.cp.Lucky;

public interface CalculateStrategy {
	
	Map<String,List<Map<String, Object>>> calculate(List<Lucky> list,String calType);

}
