package com.sinse.shopadmin.product.model;

//오직 레코드 1건을 담기 위한 객체를 모델 객체라 한다. 
public class SubCategory {
	
	//데이터베이스의 컬럼명과 일치하는 멤버변수를 보유하고, 은닉화 시켜야함 
	private int subcategory_id;
	private String subcategory_name;
	private TopCategory topcategory;  //DB에서 부모를 표현한 모델을 자식이 보유 (부모 id가 아니라 부모 객체를 보유하도록함) 
	
	public int getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(int subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public String getSubcategory_name() {
		return subcategory_name;
	}
	public void setSubcategory_name(String subcategory_name) {
		this.subcategory_name = subcategory_name;
	}
	public TopCategory getTopcategory() {
		return topcategory;
	}
	public void setTopcategory(TopCategory topcategory) {
		this.topcategory = topcategory;
	}
	
	public String toString() {
		return this.subcategory_name;
	}
	

	
}
