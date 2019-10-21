package com.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author 张琦
 * @date 2014-07-25
 *
 */
public class BaseDao {

	private static final String driverName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/cp?useUnicode=true&amp;characterEncoding=gb2312";
	private static final String userName = "root";
	private static final String userPwd = "123456";
	private static Connection conn = null;
	private static PreparedStatement psmt = null;
	private static ResultSet rs = null;

	public static Connection getConnection() {
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, userName, userPwd);
		} catch (Exception ex) {
		}
		return conn;
	}
	
	public static ResultSet getExecuteQuery(String sql) {
		try {
			psmt = getConnection().prepareStatement(sql);
			rs = psmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet getExecuteQuery(String sql, Object[] params) {
		try {
			psmt = getConnection().prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					psmt.setObject(i + 1, params[i]);
				}
			}
			rs = psmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
	}

	public static int executeSQL(String sql) {
		return executeSQL(sql, null);
	}

	public static int executeSQL(String sql, Object[] params) {
		int result = 0;
		try {
			psmt = getConnection().prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					psmt.setObject(i + 1, params[i]);
				}
			}
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return result;
	}

	public static void closeAll() {
			try {
				if (conn != null)
				conn.close();
				if(psmt!=null)
					psmt.close();
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = BaseDao.getConnection();
		if (con != null){
			System.out.println("链接成功");
	}else{
		System.out.println("链接失败");
		}
	}

}
