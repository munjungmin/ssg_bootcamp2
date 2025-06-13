package com.sinse.shop.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shop.common.exception.MemberException;
import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.member.model.Member;

public class MemberDAO {
	
	DBManager dbManager = DBManager.getInstance();
	
	//가입 
	public void insert(Member member) throws MemberException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member(id, pwd, name, email) values(?, ?, ?, ?)");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			
			// 얘는 디자인 페이지가 아니라서 유저가 알 수 있도록 디자인 쪽에 예외가 발생했음을 알리려고 던짐
			int result = pstmt.executeUpdate();
			if(result < 1) {
				//가입이 실패되면, 외부 영역에 알려줘야 하므로 예외를 전달해야 한다. 
				throw new MemberException("회원이 등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		
	}

}
