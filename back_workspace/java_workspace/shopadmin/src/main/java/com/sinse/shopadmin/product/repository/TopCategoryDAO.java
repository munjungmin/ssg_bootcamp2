package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.TopCategory;

/**
 * javaEE 개발에서는, 개발자들 간 협업을 위해, 정해진 규칙에 따라 코드를 작성해야 한다. 
 * 특히 DB에서 테이블이 하나 정의되면, java 개발자는 이 테이블에 대해 1:1 대응하는 모델객체 + 이 테이블에 대한 CRUD(create/read/update/delete)를 무조건 만든다.
 * 예를 들어, 협업 관계에 있는 같은 팀 프로젝트 구성원인 A, B가 있다고 가정했을때 
 * Product 테이블에 대해서 A가 모델, DAO를 만들면 B는 그 객체를 가져다 사용하면 됨.. 중복정의X 
 */
// 이 코드는 오직!! 데이터베이스 관련된 코드만 들어있어야 함. 따라서 Swing 제어 등의 디자인 코드가 절대로 들어있으면 안된다. 
// 왜? 이 객체는 MVC 영역 중 Model 영역, 즉 로직 객체 영역을 담당하므로... View가 섞여있으면 안됨
public class TopCategoryDAO {
	//누군가가 new TopCategoryDAO() 호출할때 초기화되면서 값이 채워진다.
	DBManager dbManager = DBManager.getInstance();
	
	//TopCategory의 모든 레코드 가져오기 
	public List selectAll() {
		
		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		
		con = dbManager.getConnection();
		

		StringBuffer sql = new StringBuffer();
		sql.append("select * from topcategory");
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			// Collec
			while(rs.next()) {
				TopCategory topcategory = new TopCategory();
				topcategory.setTopcategory_id(rs.getInt("topcategory_id"));
				topcategory.setTop_name(rs.getString("top_name"));
				list.add(topcategory); //js의 push() 와 동일
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt, rs);
		}
		return list;
		
	}
	
	//한 건 가져오기
	public TopCategory select(int topcategory_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TopCategory topcategory = null;
		
		con = dbManager.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("select * from topcategory where topcategory_id = ?");
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, topcategory_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				topcategory = new TopCategory();
				topcategory.setTopcategory_id(rs.getInt("topcategory_id"));
				topcategory.setTop_name(rs.getString("top_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt, rs);
		}
		return topcategory;
	}

	//한 건 입력
	public void insert() {}
	
	//한 건 수정
	public void update() {}
	
	//한 건 삭제
	public void delete() {}
	
	
}
