package com.sinse.shopadmin.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sinse.shopadmin.common.config.Config;

//AppMain에서 DB를 핸들링하지말고, 보다 중립적인 객체에서 Connection을 얻는 것뿐 아니라, 닫는 것마저도 대행해주는 기능을 보유한 객체를 선언
public class DBManager {
	private static DBManager instance;
	private Connection con; 
	
	private DBManager() {
		try {
			//1)드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2) 접속
			con = DriverManager.getConnection(Config.url, Config.user, Config.pwd);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBManager getInstance() {
		//만일 인스턴스가 존재하지 않으면 이 메서드에서 인스턴스 생성 
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return con;
	}
	
	//DB 관련 자원을 해제하는 메서드 
	
	public void release(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void release(PreparedStatement pstmt) {  //DML (insert, update, delete) 
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void release(PreparedStatement pstmt, ResultSet rs) {  //select  
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {  //select  
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
