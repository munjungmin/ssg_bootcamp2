package com.sinse.threadApp.ani;

import java.awt.Dimension;

import javax.swing.JProgressBar;

public class MyJProgressBar extends JProgressBar{
	int n = 0;
	int d = 3;
	boolean flag = true;
	Dimension dm = new Dimension(750, 40);
	
	public MyJProgressBar() {
		setPreferredSize(dm);
	}
	
	public void move() {
		n += d;
		setValue(n);
		if(n > 100) flag = false;
	}

}
