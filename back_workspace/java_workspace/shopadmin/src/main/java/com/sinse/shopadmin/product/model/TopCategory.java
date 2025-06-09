package com.sinse.shopadmin.product.model;

//아래의 클래스는 로직을 담기 위한 객체가 아니라, 단지 DB의 TopCategory테이블의 레코드 1건을 담기 위한 모델 객체이다.
public class TopCategory {
	private int topcategory_id;
	private String top_name;
	
	
	public int getTopcategory_id() {
		return topcategory_id;
	}
	public void setTopcategory_id(int topcategory_id) {
		this.topcategory_id = topcategory_id;
	}
	public String getTop_name() {
		return top_name;
	}
	public void setTop_name(String top_name) {
		this.top_name = top_name;
	}
	
	public String toString() {
		return top_name;
	}
}
