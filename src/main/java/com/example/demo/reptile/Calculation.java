package com.example.demo.reptile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.cp.Lucky;

public class Calculation implements CalculateStrategy {

	@Override
	public Map<String, List<Map<String, Object>>> calculate(List<Lucky> list,String calType) {
		Map<String, List<Map<String, Object>>> resultMap = new HashMap<>();
		List<Map<String, Object>> tigerMapList = null;
		Map<String, Object> map = null;
		boolean result = false;
		int flag = 0, count = 0;
		for (int i = 0,l=list.size(); i < l; i++) {
			Lucky up = list.get(Math.max((i-1), 0));
			Lucky curr = list.get(i);
			Lucky next = list.get(Math.min(i+1, l-1));
			map = new HashMap<>();
			if(flag ==0) {tigerMapList = new ArrayList<>();}
			
			if("龙虎".equals(calType)) {
				result = !curr.getDragonTiger().equals(next.getDragonTiger())  
						&& ( i>0 && !curr.getDragonTiger().equals(up.getDragonTiger()));
			}else if("大小".equals(calType)) {
				result = !curr.getBigSmall().equals(next.getBigSmall())  
						&& ( i>0 && !curr.getBigSmall().equals(up.getBigSmall()));
			}else if("奇偶".equals(calType)) {
				result = !curr.getOddEven().equals(next.getOddEven())  
						&& ( i>0 && !curr.getOddEven().equals(up.getOddEven()));
			}else {
				return null;
			}
			//计算-如果当前期与下期的不一致则记录
			if(result) {
				flag ++;
				map.put("期数", curr.getIssue());
				map.put("开奖号码", curr.getOpenNumber());
				map.put("开奖时间", curr.getOpenTime());
				map.put("次数", flag);
				tigerMapList.add(map);
			}else {
				flag++;
				map.put("期数", curr.getIssue());
				map.put("开奖号码", curr.getOpenNumber());
				map.put("开奖时间", curr.getOpenTime());
				map.put("次数", flag);
				tigerMapList.add(map);
				
				flag ++;
				map = new HashMap<>();
				map.put("期数", next.getIssue());
				map.put("开奖号码", next.getOpenNumber());
				map.put("开奖时间", next.getOpenTime());
				map.put("次数", flag);
				tigerMapList.add(map);
				//当出现相等时，看是否连续不同的次数到出相同的为此大于5
				if(flag>=5) {
					count ++;
					resultMap.put(calType+count,tigerMapList);
				}
				//出现相同后记录都清空
				flag = 0;
				tigerMapList = null;
				map = null;
			}
		}
		return resultMap;
	}

}
