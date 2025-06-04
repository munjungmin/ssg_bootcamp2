package com.sinse.threadApp.ani;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

//스윙의 UI 컴포넌트 중, 진행율을 표현하는 컴포넌트인 JProgressBar를 사용해보자
public class ProgressTest extends JFrame{
	
	JButton bt;
	MyJProgressBar[] barArray = new MyJProgressBar[3];
	ProgressThread[] threadArray = new ProgressThread[3];
	
	public ProgressTest() {
		bt = new JButton("진행");
		
		
		for(int i = 0; i < barArray.length; i++) {
			barArray[i] = new MyJProgressBar();
			add(barArray[i]);
		}
		
		for(int i = 0; i < threadArray.length; i++) {
			threadArray[i] = new ProgressThread(barArray[i]);
		}
		
		this.setLayout(new FlowLayout());
		add(bt);
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < threadArray.length; i++) {
					threadArray[i].start();
				}
			}
		});
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		new ProgressTest();
	}

}
