package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 数据库工具包
 * @author Administrator
 *
 */
public class DBUtil {
	String url="jdbc:sqlserver://127.0.0.1:1433;databasename=users";
	String user = "sa";
	String pwd = "123456";
	public Connection conn;
	public PreparedStatement ps;
	public ResultSet rs;
	
	public DBUtil() {
		// TODO Auto-generated constructor stub
		//加载驱动
		try {//"com.microsoft.sqlserver.jdbc.SQLServerDriver"
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取连接对象
	public Connection getConn(){
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//用于增删改
	public int execUpdate(String sql,Object[] obj){
		try {
			//获取连接对象
			getConn();
			//获取操作对象
			ps = conn.prepareStatement(sql);
			if(obj!=null){
				//循环赋值
				for(int i=0;i<obj.length;i++){
					ps.setObject(i+1, obj[i]);
				}
			}
			 return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//用于查询
	public ResultSet execQuery(String sql,Object[] obj){
		try {
			//获取连接对象
			getConn();
			//获取操作对象
			ps = conn.prepareStatement(sql);
			if(obj!=null){
				for(int i=0;i<obj.length;i++){
					ps.setObject(i+1, obj[i]);
				}
			}
			 return ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//关闭数据库的对象
	public void getClose(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
