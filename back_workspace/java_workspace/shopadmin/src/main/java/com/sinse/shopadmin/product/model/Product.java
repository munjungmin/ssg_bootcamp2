package com.sinse.shopadmin.product.model;

import java.util.List;

/**
 * 	product_id int primary key auto_increment
	, product_name varchar(25)
	, brand varchar(15)
	, price int default 0
	, discount int default 0
	, introduce varchar(250)
	, detail text 
	, subcategory_id int 
	
	 멤버변수가 db 컬럼명과 일치하도록 한다. 
 */
public class Product {
	private int product_id;
	private String productName;
	private String brand;
	private int price;
	private int discount;
	private String introduce;
	private String detail;
	//db는 key값으로 조회를 할 수 있지만, 자바에서는 그런게 안되기 때문에 객체 자체를 보유해야함
	private SubCategory subCategory;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}	
	
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
}
