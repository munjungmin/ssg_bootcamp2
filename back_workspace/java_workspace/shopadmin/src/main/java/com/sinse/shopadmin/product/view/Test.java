package com.sinse.shopadmin.product.view;

public class Test {
	public static void main(String[] args) {
		
		
		for(int i = 0; i < 5; i++) {
			System.out.println(System.currentTimeMillis());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		
	}
}
