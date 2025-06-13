package com.sinse.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shopadmin.common.exception.ProductException;
import com.sinse.shopadmin.common.util.DBManager;
import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.model.SubCategory;
import com.sinse.shopadmin.product.model.TopCategory;

//product 테이블에 대한 crud만을 수행 - db 작업코드만 작성해야됨 
public class ProductDAO {
	
	DBManager dbManager = DBManager.getInstance();
	
	public void insert(Product product) throws ProductException{
		
		System.out.println(product.getProductName());
		System.out.println(product.getPrice());
		System.out.println(product.getSubCategory().getSubcategory_id());
		
		
		Connection con = null;
		PreparedStatement pstmt = null; 
		int result = 0;
		
		con = dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("insert into product(product_name, brand, price, discount, introduce, detail, subcategory_id) values(?, ?, ? ,? , ?, ?, ?)");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getBrand());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getDiscount());
			pstmt.setString(5, product.getIntroduce());
			pstmt.setString(6, product.getDetail());
			pstmt.setInt(7, product.getSubCategory().getSubcategory_id());
			
			result = pstmt.executeUpdate();
			if(result == 0) {
				throw new ProductException("어째서인지 등록에 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			//e.printStackTrace() 처리만 하면, 바깥쪽 즉 프로그램을 사용하는 유저는 에러의 원인을 알 수 없어 신뢰성 떨어짐 
			// 따라서 에러가 발생하면, 이 영역에서만 처리를 국한시키지 말고 외부 영역까지 에러 원인을 전달해야 한다.
			
			e.printStackTrace();
			throw new ProductException("등록에 실패하였습니다.", e);
		} finally {
			dbManager.release(pstmt);
		}
		
	}
	
	//방금 수행한 insert에 의해 증가된 pk의 최신값 얻기
	//나의 세션 내에서 증가된 것만 가져오기 (select last_insert_id() 함수 - mysql)
	//max()는 사용X, 다른 유저계정에 의한 증가값도 반환해버리기 때문에 
	public int selectRecentPk() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pk = 0; 
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select last_insert_id() as product_id");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pk = rs.getInt("product_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pk;
	}
	
	//모든 상품 목록 가져오기 (상품 + 상위 + 하위)
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		List<Product> list = new ArrayList<>();  //만일 배열을 쓸 경우 rs는 전방향, 후방향 스크롤까지 가능해야 함. 배열은 생성 시 크기를 먼저 지정해야 하므로 
		
		con = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.topcategory_id, top_name, s.subcategory_id, sub_name");
		sql.append(", product_id, product_name, brand, price, discount, introduce, detail");
		sql.append(" from topcategory t inner join subcategory s inner join product p");
		sql.append(" on t.topcategory_id = s.topcategory_id");
		sql.append(" and s.subcategory_id = p.subcategory_id");
		
		System.out.println(sql.toString());
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TopCategory topCategory = new TopCategory();
				topCategory.setTopcategory_id(rs.getInt("t.topcategory_id"));
				topCategory.setTop_name(rs.getString("top_name"));
				
				SubCategory subCategory = new SubCategory();
				subCategory.setSubcategory_id(rs.getInt("s.subcategory_id"));
				subCategory.setSubcategory_name(rs.getString("sub_name"));
				subCategory.setTopcategory(topCategory);
				
				Product product = new Product();
				product.setProduct_id(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setBrand(rs.getString("brand"));
				product.setPrice(rs.getInt("price"));
				product.setDiscount(rs.getInt("discount"));
				product.setIntroduce(rs.getString("introduce"));
				product.setDetail(rs.getString("detail"));
				product.setSubCategory(subCategory);
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		
		return list;
	}
	
	
	
	
}
