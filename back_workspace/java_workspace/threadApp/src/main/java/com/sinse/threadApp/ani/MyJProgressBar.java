package com.sinse.threadApp.ani;

import java.awt.Dimension;

import javax.swing.JProgressBar;

public class MyJProgressBar extends JProgressBar{
	int speed = 3;
	boolean flag = true;
	
	
	public MyJProgressBar(int speed) {
		Dimension dm = new Dimension(750, 40);
		setPreferredSize(dm);
		this.speed = speed;
	}
	
	public void move() {
		int n = getValue() + speed;
		setValue(n);
		if(n > 100) flag = false;
	}

}
