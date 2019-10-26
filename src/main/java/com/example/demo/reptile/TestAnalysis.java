package com.example.demo.reptile;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	private static CalculateStrategy calculat = new Calculation();
	
	private static boolean saveFlag = true;
	private static final String sql = "select * from t_lucky where issue like ? order by issue asc";
	
	public static void main(String[] args) throws SQLException, IOException {
		ResultSet rs = BaseDao.getExecuteQuery(sql,new String [] {"20191025%"});
		List<Lucky> list = new ArrayList<>();
		while(rs.next()) {
			Lucky lucky = new Lucky(rs.getInt(1), 
					rs.getInt(2), rs.getString(3), rs.getString(4), 
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), null, null);
			list.add(lucky);
		}
		//计算龙虎
		Map<String, List<Map<String, Object>>> resultMap = calculat.calculate(list, "大小");
		resultMap.putAll(calculat.calculate(list, "奇偶"));
		resultMap.putAll(calculat.calculate(list, "龙虎"));
		
		System.out.println(resultMap);
		if(saveFlag) {
			Sqllink.saveToDb(resultMap);
		}
	}
}
