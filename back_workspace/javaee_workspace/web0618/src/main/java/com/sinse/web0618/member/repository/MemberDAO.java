package com.sinse.web0618.member.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.web0618.exception.MemberException;
import com.sinse.web0618.member.model.Member;

public class MemberDAO {
	//DB 접속 시도는 절대로 DAO에서 시도하면 안됨. 하지만 오늘은 연습이기 때문에
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/shop";
	String user = "shop";
	String pwd = "1234";
	
	//throws 목적: 이 메서드를 호출한 자가 에러의 원인을 알 수 있도록 전달하고자 함이 목적임 
	public void insert(Member member) throws MemberException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
			StringBuffer sql = new StringBuffer();
			sql.append("insert into member(id, pwd, email) values(?, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getEmail());
			
			int result = pstmt.executeUpdate();
			
			if(result == 0) {
				throw new MemberException("회원 가입 실패");  //여기는 그냥 회원가입 실패이고 
			}
		} catch (ClassNotFoundException e) {
			throw new MemberException("회원 가입 실패", e);  //여기는 에러 원인도 알 수 있음 
		} catch (SQLException e) {
			throw new MemberException("회원 가입 실패", e);
		} finally {
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
}


/**
 * java swing과의 차이점 
 * 스윙은 프로그램 시작시 커넥션 연결이 이루어지고 닫을때 connection 끊김
 * 근데 웹은  접속마다 연결?하는건가?
 */
