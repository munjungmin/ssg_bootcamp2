package com.sinse.shopadmin.product.view;

//throw : 에러 강제 발생 (굉장히 중요. 내가 원하는 에러를 일으켜서 남한테 전달할 수 있어서) 
//
public class ExceptionTest3 {

	public static void main(String[] args) throws NumberConvertFailException{
		System.out.println("A");
//		try {
//			throw new NumberConvertFailException("내가 만든 예외"); //throw로 예외 발생시켜서 실행부가 정상 실행해 B 출력 가능  
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		throw new NumberConvertFailException("내가 만든 예외");  //예외가 발생하면 호출한 메서드로 이동해버린다. 
	//	System.out.println("B");   //Unreachable code 
	}
}
