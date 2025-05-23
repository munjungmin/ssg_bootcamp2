package com.sinse.ioproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleTest {
	public static void main(String[] args) {
		//오라클에 접속하기
		/*
		 * 1) java언어가 데이터베이스를 핸들링 하기 위해서는 db를 제작한 벤더사에서 제공하는 구현체인 드라이버를 먼저 메모리에 로드해야 한다.
		 * 	  단, 일반 클래스처럼 new 할 수 없고, method 영역에 개발자가 직접 올려야 한다. 
		 * 
		 *   Class.forName(); //JVM의 method 영역에 개발자가 직접 로드 
		 */
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			//원격지의 오라클에 접속 getConnection(url, id, pwd);   // url은 정해져있음, port=1521 : 오라클 서비스 이름
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "1234");
			
			if(con != null) {
				System.out.println("접속 성공");
			}else {
				System.out.println("접속 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
