package com.sinse.shopadmin.common.exception;

public class ProductImgException extends RuntimeException{
	//에러의 원인 및 에러메시지를 예외 객체에 담으려면, 생성자에서 처리할 수 있는데 
	//자바 문법상 부모 생성자는 물려받지 못하므로, 부모 생성자를 직접 호출하여 에러 원인 및 메시지를 객체에 담아보자 
	
	public ProductImgException(String msg) {
		super(msg);
	}
	
	//예외 객체들의 최상위 객체가 Throwable 인터페이스
	//어떤 예외가 전달되어도 다 받을 수 있다.
	public ProductImgException(Throwable e) {
		super(e);
	}
	
	public ProductImgException(String msg, Throwable e) {
		super(msg, e);
	}
}
