package com.sinse.shopadmin.product.model;

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
	private List<Color> colorList; //보유 색상 
	
}
