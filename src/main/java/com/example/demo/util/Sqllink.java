package com.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Sqllink {
	private static String DRIVERCLASS="com.mysql.jdbc.Driver";
	private static String URL="jdbc:mysql://localhost:3306/cp?characterEncoding=utf8";
	private static String USERNAME="root";		//数据库用户名
	private static String PASSWORD="123456"; 		//数据库密码

	public Sqllink() {}
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVERCLASS); 
		Connection conn =DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}

	public static void saveToDb(JSONArray jsonArray) throws SQLException {
		Connection con=null; 
		PreparedStatement ptatm=null;
		try {
			con  = Sqllink.getConnection();
			System.out.println("sql连接成功");

			String sql =  "INSERT INTO `cp`.`t_lucky`(`lotteryCode`, `issue`, `openNumber`, `openTime`, `dragonTiger`,`oddEven`,`bigSmall`) VALUES (?, ?, ?, ?,?,?,?)";
			con.setAutoCommit(false); //(重要)具体说明看文章的第4点注意事项
			ptatm = con.prepareStatement(sql); 

			Iterator<Object> it = jsonArray.iterator();
			while (it.hasNext()) {
				JSONObject next = (JSONObject)it.next();
				String number = next.containsKey("openNumber")?next.getString("openNumber"):next.getString("openNum");
				number = number.replace("[", "").replace("]", "");
				ptatm.setInt(1, next.containsKey("lotteryCode")?next.getIntValue("lotteryCode"):1309);
				ptatm.setString(2, next.getString("issue"));
				ptatm.setString(3, number);
				ptatm.setString(4,next.getString("openTime"));
				String[] split = number.split(",");
				ptatm.setString(5,isTiger(Integer.valueOf(split[0]),Integer.valueOf(split[9]))?"虎":"龙");
				ptatm.setString(6,isOdd(Integer.valueOf(split[0]))?"奇":"偶");
				ptatm.setString(7,isSmall(Integer.valueOf(split[0]))?"小":"大");
				ptatm.addBatch();
			}
			int[] batch = ptatm.executeBatch();	//执行批量SQL语句，该语句可能返回多个结果
			System.out.println("保存成功:"+Arrays.toString(batch));
			ptatm.clearBatch();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			ptatm.close();
      		con.commit();
		}
	}
	
	
	public static void saveToDb(Map<String, List<Map<String, Object>>> map) throws SQLException {
		Connection con=null; 
		PreparedStatement ptatm=null;
		try {
			con  = Sqllink.getConnection();
			System.out.println("sql连接成功");

			String sql =  "INSERT INTO `cp`.`t_lucky_result`(`openTime`,`count`,`issue`, `openNumber`, `wf`) VALUES (?, ?, ?, ?,?)";
			con.setAutoCommit(false); //(重要)具体说明看文章的第4点注意事项
			ptatm = con.prepareStatement(sql); 

			Iterator<Entry<String, List<Map<String, Object>>>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, List<Map<String, Object>>> jmap = it.next();
				String key = jmap.getKey();
				List<Map<String, Object>> next = jmap.getValue();
				Map<String, Object> object = next.get(next.size()-1);
				ptatm.setString(1, String.valueOf(object.get("开奖时间")));
				ptatm.setInt(2, Integer.valueOf(object.get("次数")+""));
				ptatm.setString(3, String.valueOf(object.get("期数")));
				ptatm.setString(4, String.valueOf(object.get("开奖号码")));
				ptatm.setString(5,key);
				ptatm.addBatch();
			}
			int[] batch = ptatm.executeBatch();	//执行批量SQL语句，该语句可能返回多个结果
			System.out.println("保存成功:"+Arrays.toString(batch));
			ptatm.clearBatch();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			ptatm.close();
      		con.commit();
		}
	}
	
	private static boolean isSmall(Integer number) {
		return number < 6;
	}
	private static boolean isTiger(Integer num1,Integer num2) {
		return num1 < num2;
	}
	private static boolean isOdd(Integer num) {
		return num %2==1;
	}

}
