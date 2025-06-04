package com.sinse.threadApp.ani;

public class ProgressThread extends Thread{
	boolean	flag = true;
	MyJProgressBar bar;
	
	public ProgressThread(MyJProgressBar bar) {
		this.bar = bar;
	}
	
	@Override
	public void run() {
		while(flag) {
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

}
