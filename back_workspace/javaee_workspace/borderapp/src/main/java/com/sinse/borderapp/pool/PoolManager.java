package com.sinse.borderapp.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class PoolManager {
	private String driver = "com.mysql.cj.jdbc.Driver"; 
	private String url = "jdbc:mysql://192.168.60.59:3306/spring4";
	private String user = "spring4";
	private String pwd = "1234";
	
	
	// 비록 생성은 못하게 막았지만, 내가 지원해주는 메서드로 인스턴스를 가져가라 (싱글톤)
	private static PoolManager instance; 

	// Connection들을 모아서 관리할 벡터(순서가 있는 List)
	Vector<Connection> connectionPool = new Vector<>();
	
	private PoolManager() {
		
	}
	
	public static PoolManager getInstance() {
		if(instance == null) {
			instance = new PoolManager();
		}
		return instance;
	}
	
	//커넥션 담아두기 
	public void createConnection() {
		
		for(int i = 0; i < 20; i++) {
			try {
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url, user, pwd);
				//벡터에 모아놓기, 즉 풀을 만든다.
				connectionPool.add(con);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//빌려주기
	public synchronized Connection getConnection() {
		// 빌려줄 것이 없으면, 커넥션을 또 만든다
		/**
		 * 만약 남은 커넥션이 하나인데 A, B 쓰레드가 거의 동시에 isEmpty를 통과했다면 먼저 커넥션을 가져가지 못하면 에러가 발생
		 * 그래서 커넥션을 빌릴때는 비동기가 아닌 동기적으로 실행 (순서를 지킨다)
		 */
		if(connectionPool.isEmpty()) {  //요소가 소진되었다면... 
			createConnection();
		}
		return connectionPool.remove(0);   //반환과 동시에, 기존 벡터 요소 하나를 제거
	}
	
	public void release(Connection con) {
		if(con != null) {
			connectionPool.add(con);
		}
	}
	public void release(Connection con, PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null) {
			connectionPool.add(con);
		}
	}
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
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
			connectionPool.add(con);
		}
	}


}
