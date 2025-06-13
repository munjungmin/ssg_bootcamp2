package com.sinse.shopadmin.product.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.sinse.shopadmin.product.model.Product;
import com.sinse.shopadmin.product.repository.ProductDAO;

//JTable은 껍데기이므로 이 모델만을 바라보고 정보를 가져감 
public class ProductModel extends AbstractTableModel{
	ProductDAO productDAO;
	List<Product> list;
	String[] column = {
			"topcategory_id", "top_name", "subcategory_id", "sub_name",
			"product_id", "product_name", "brand", "price", "discount", "detail", "introduce"
		};
	
	public ProductModel() {
		productDAO = new ProductDAO();
		list = productDAO.selectAll();
	}
	
	@Override
	public int getRowCount() {		
		return list.size();
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return column[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Product product = list.get(row);
		
		switch(col) {
			case 0: return product.getSubCategory().getTopcategory().getTopcategory_id();
			case 1: return product.getSubCategory().getTopcategory().getTop_name(); 
			case 2: return product.getSubCategory().getSubcategory_id();
			case 3: return product.getSubCategory().getSubcategory_name();
			case 4: return product.getProduct_id();
			case 5: return product.getProductName();
			case 6: return product.getBrand();
			case 7: return product.getPrice();
			case 8: return product.getDiscount();
			case 9: return product.getIntroduce();
			case 10: return product.getDetail();
		}
		
		return null;
	}
}
