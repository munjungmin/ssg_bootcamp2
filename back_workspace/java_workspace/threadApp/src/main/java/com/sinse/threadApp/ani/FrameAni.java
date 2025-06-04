package com.sinse.threadApp.ani;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameAni extends JFrame{
	JPanel p_center;
	Image[] imgArray = new Image[18];
	Thread thread;
	int idx = 0;
	
	public FrameAni() {
		//생성
		createImage(); //패널이 그림을 그리기 전에 배열을 완성 
		
		p_center = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(imgArray[idx], 0, 0, 300, 300, p_center);
			}
		};
		
		thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(50);
						changeImg();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		thread.start();   //JVM에게 runnable 상태로의 진입을 부탁
	
		// 조립
		add(p_center);		
		
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void createImage() {
		for(int i = 0; i < 18; i++) {
			URL url = this.getClass().getClassLoader().getResource("images/hero/image" + (i+1) + ".png");
			BufferedImage bufImg = null;
			
			try {
				bufImg = ImageIO.read(url);
				Image img = bufImg.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
				imgArray[i] = img;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void changeImg() {
		idx++;
		idx = (idx % 18);
		p_center.repaint();
	}
	
	public static void main(String[] args) {
		new FrameAni();
	}

}
