package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.SubCategory;
import com.sinse.shopadmin.product.model.TopCategory;

public class SubCategoryDAO {
	DBManager dbManager = DBManager.getInstance();
	TopCategoryDAO topCategoryDAO = new TopCategoryDAO(); 
	//한 건 가져오기
	public void select() {}
	
	// 하위 카테고리 중 유저가 선택한 상위 카테고리에 소속된 목록만 가져오기
	public List selectByTop(TopCategory topcategory) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList(); 
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from subcategory where topcategory_id = ?");
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, topcategory.getTopcategory_id());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SubCategory subcategory = new SubCategory();
				subcategory.setSubcategory_id(rs.getInt("subcategory_id"));
				subcategory.setSubcategory_name(rs.getString("sub_name"));
				
				//ERD 상에서 부모인 TopCategory의 정보는 TopCategory 모델 객체로 표현되므로 
				// TopCategory 부모 레코드를 반영한 모델 객체를 아래의 setter에 대입 
				
				subcategory.setTopcategory(topcategory);
				list.add(subcategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt, rs);
		}
		
		return list;
		
	}
}
