package com.example.demo.reptile;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.cp.Lucky;
import com.example.demo.util.BaseDao;
import com.example.demo.util.Sqllink;

/**
 * 分析
 * TestAnalysis<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年10月10日-下午10:13:05 <BR>
 * @version 1.0.0
 * 
 */
public class TestAnalysis {
	
	private static final String sql = "select * from t_lucky where issue like ? order by issue asc";
	
	public static void main(String[] args) throws SQLException, IOException {
		ResultSet rs = BaseDao.getExecuteQuery(sql,new String [] {"20191022%"});
		List<Lucky> list = new ArrayList<>();
		while(rs.next()) {
			Lucky lucky = new Lucky(rs.getInt(1), 
					rs.getInt(2), rs.getString(3), rs.getString(4), 
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), null, null);
			list.add(lucky);
		}
		
		//保存期数，开奖时间，次数
		Map<String,List<Map<String, Object>>> resultMap = new HashMap<>();
		int flag = 0, count = 0;
		List<Map<String, Object>> mapList = null;
		Map<String, Object> map = null;
		for (int i = 0,l=list.size(); i < l; i++) {
			Lucky up = list.get(Math.max((i-1), 0));
			Lucky curr = list.get(i);
			Lucky next = list.get(Math.min(i+1, l-1));
			map = new HashMap<>();
			if(flag ==0) {
				mapList = new ArrayList<>();
			}
			
			//如果当前期与下期的不一致则记录
			if(!curr.getDragonTiger().equals(next.getDragonTiger())  
					&& ( i>0 && !curr.getDragonTiger().equals(up.getDragonTiger()))) {
				flag ++;
				map.put("期数", curr.getIssue());
				map.put("开奖号码", curr.getOpenNumber());
				map.put("开奖时间", curr.getOpenTime());
				map.put("次数", flag);
				mapList.add(map);
			}else {
				flag++;
				map.put("期数", curr.getIssue());
				map.put("开奖号码", curr.getOpenNumber());
				map.put("开奖时间", curr.getOpenTime());
				map.put("次数", flag);
				mapList.add(map);
				
				flag ++;
				map = new HashMap<>();
				map.put("期数", next.getIssue());
				map.put("开奖号码", next.getOpenNumber());
				map.put("开奖时间", next.getOpenTime());
				map.put("次数", flag);
				mapList.add(map);
				//当出现相等时，看是否连续不同的次数到出相同的为此大于5
				if(flag>=5) {
					count ++;
					resultMap.put("龙虎"+count,mapList);
				}
				//出现相同后记录都清空
				flag = 0;
				mapList = null;
				map = null;
			}
		}
		System.out.println(resultMap);
		Sqllink.saveToDb(resultMap);
	}
	
	private void analysisTigerDragon() {
		
	}
	
	

}
