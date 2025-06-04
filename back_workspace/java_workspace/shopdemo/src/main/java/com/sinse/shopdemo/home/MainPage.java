package com.sinse.shopdemo.home;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sinse.shopdemo.common.view.Page;

public class MainPage extends Page{
	JPanel p_visual;
	JPanel p_content;
	Image image; 
	
	public MainPage() {
		//기능	설명
//		.class 파일 로드	JVM이 클래스 코드를 사용할 수 있게 메모리로 로드
//		동적 클래스 로딩	실행 중에도 클래스를 로딩할 수 있음 (Class.forName())
//		리소스 접근	.getResource()나 .getResourceAsStream()을 통해 리소스 파일 접근
		URL url = this.getClass().getClassLoader().getResource("images/banner.png");
		Image img = null;
		
		try {
			BufferedImage bufImg = ImageIO.read(url);
			img = bufImg.getScaledInstance(1400, 400, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p_visual = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, 1400, 300, this);
			}
		};  
		
		
	}

}
