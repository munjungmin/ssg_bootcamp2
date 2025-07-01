package com.sinse.borderapp.respository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sinse.borderapp.exception.NoticeException;
import com.sinse.borderapp.model.Notice;
import com.sinse.borderapp.pool.PoolManager;

//CRUD
public class NoticeDAO {
	
	PoolManager poolManager = PoolManager.getInstance();
	
	//모든 레코드 가져오기
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = new ArrayList<>();
		
		try {
			con = poolManager.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from notice");
			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				list.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			poolManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	//한 건 가져오기 
	public Notice select(int notice_id) {
	
		return null;
	}
	
	public void insert(Notice notice) throws NoticeException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = poolManager.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into notice(title, writer, content) values(?, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());
			
			int result = pstmt.executeUpdate();  //DML 수행 
			
			if(result < 1) {
				throw new NoticeException("글 등록 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			poolManager.release(con, pstmt);
		}
		
	}
	
	//수정
	public void update() {
		
	}
	
	//삭제
	public void delete() {
		
	}
	
}
