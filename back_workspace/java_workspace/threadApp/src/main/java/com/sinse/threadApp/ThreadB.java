package com.sinse.threadApp;

public class ThreadB extends Thread{
	@Override
	public void run() {
		for(int i = 1; i <= 50; i++) {
			System.out.println("B");
		}
	}
}
