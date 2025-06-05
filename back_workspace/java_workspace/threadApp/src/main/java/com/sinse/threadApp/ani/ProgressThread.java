package com.sinse.threadApp.ani;

public class ProgressThread extends Thread{
	MyJProgressBar bar;
	
	public ProgressThread(MyJProgressBar bar) {
		this.bar = bar;
	}
	
	@Override
	public void run() {
		try {
			while(bar.flag) {
				Thread.sleep(50);
				bar.move();	
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
	}

}
