package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shopadmin.common.exception.ProductColorException;
import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.ProductColor;

public class ProductColorDAO {

	DBManager dbManager = DBManager.getInstance();
	
	public void insert(ProductColor productColor) throws ProductColorException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product_color(product_id, color_id) values(?, ?)");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, productColor.getProduct().getProduct_id());
			pstmt.setInt(2, productColor.getColor().getColor_id());
			int result = pstmt.executeUpdate();
			
			if(result < 1) {
				throw new ProductColorException("상품 색상이 등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductColorException("상품의 색상 등록시 문제가 발생하였습니다.", e);
		} finally {
			dbManager.release(pstmt);
		}
	}
}
