package com.sinse.shopadmin.product.view;

/**
 * 에러(개발자의 범위 벗어난 문제 상황) - java(프로그램)의 관심대상이 아님 
 * 예외? 에러 중 개발자가 처리 가능한 수준의 에러 
 * 
 * 예외)
 * 1) 컴파일러 처리를 강요하는 예외 - 강요된 예외 (컴파일러에 의해 예외처리를 강제 당하는 것)
 * 2) 강제하지 않는 예외 - 개발자가 원하면 처리할 수 있는 예외 (RuntimeException의 자손들)
 * 					심각하지 않은 예외, 주로 개발자의 논리적 실수 ex) out of bounds, .. 
 */
public class ExceptionTest {
	public void test() throws ClassNotFoundException{
		load();
	}
	
	//throws: 나를 호출한 메서드에게 예외 처리 책임을 전가시키겠다. 
	public void load() throws ClassNotFoundException{
		Class.forName("babo");
		
	}
	
	//main이 던진 예외는 JVM이 처리
	public static void main(String[] args) throws ClassNotFoundException{
		ExceptionTest et = new ExceptionTest();
		et.test();
	}
}
