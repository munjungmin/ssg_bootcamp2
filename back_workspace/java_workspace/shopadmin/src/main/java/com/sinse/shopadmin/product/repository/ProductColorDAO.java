package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.ProductColor;

public class ProductColorDAO {

	DBManager dbManager = DBManager.getInstance();
	
	public int insert(ProductColor productColor) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product_color(product_id, color_id) values(?, ?)");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, productColor.getProduct().getProduct_id());
			pstmt.setInt(2, productColor.getColor().getColor_id());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		return result;
	}
}
