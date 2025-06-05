package com.sinse.threadApp.ani;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer extends JFrame{
	
	JButton bt = new JButton("시작");
	JPanel p1 = new JPanel(new BorderLayout());
	JPanel p2 = new JPanel(new BorderLayout());
	JPanel p_container = new JPanel();
	int a = 0, b = 0;
	JLabel lb1 = new JLabel("0");
	JLabel lb2 = new JLabel("1");
	
	Thread thread1, thread2;
	
	public Timer() {
		Dimension d = new Dimension(200, 200);
		
		p1.setPreferredSize(d);
		p2.setPreferredSize(d);
		p_container.setPreferredSize(new Dimension(500, 400));
		
		lb1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lb2.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		
		lb1.setHorizontalAlignment(JLabel.CENTER);
		lb2.setHorizontalAlignment(JLabel.CENTER);
		
		p1.setBackground(Color.pink);
		p2.setBackground(Color.orange);
		
		p1.add(lb1);
		p2.add(lb2);
		
		add(bt, BorderLayout.NORTH);
		p_container.add(p1);
		p_container.add(p2);
		add(p_container);
		
		setSize(600, 350);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		thread1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
						a++;
						lb1.setText(Integer.toString(a));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		thread2 = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(500);
						b++;
						lb2.setText(Integer.toString(b));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thread1.start();
				thread2.start();
				bt.setEnabled(false);
			}
		});
	}
	
	public static void main(String[] args) {
		new Timer();
	}

}
