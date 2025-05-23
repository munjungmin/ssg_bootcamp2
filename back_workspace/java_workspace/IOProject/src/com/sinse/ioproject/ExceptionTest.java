package com.sinse.ioproject;

public class ExceptionTest {

	public static void main(String[] args) {
		
		int[] arr = new int[3];
		
		try {
			throw new MyArrayException("배열관련에러발생");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}
