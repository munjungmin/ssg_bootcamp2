package com.sinse.shopadmin.product.view;

public class ExceptionTest2 {

	public void test() {
		try {
			test2();
		} catch (NumberConvertFailException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void test2() throws NumberConvertFailException{
		String s = "100k";
		try {
			Integer.parseInt(s);
		} catch(NumberFormatException e) {
			//사용자 정의 예외를 메모리에 생성하고, 예외를 일부러 일으킨다.
			e.printStackTrace();
			throw new NumberConvertFailException("숫자로 못바꿨어", e);
		}
	}
	
	public static void main(String[] args) {
		new ExceptionTest2().test();
	}
}


/**
 *  test()는 test2에서 일어난 예외를 모른다. 근데 호출한 메서드가 예외 정보를 알아야 하는 경우가 있어서 throws 사용
 *  (예외가 발생한 곳에서 예외 처리를 하면 안되는 경우가 있음)  
 */
