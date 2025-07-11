package com.sinse.web0618.member.model;

// 로직용이 아닌 단순 레코드 담기 위한 용도의 객체
// 멤버변수와 DB의 컬럼명이 일치하게 작성한다. 은닉화 필요
public class Member {
	
	private int member_id;
	private String id;
	private String pwd;
	private String email;
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
