package com.sinse.shop.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.sinse.shop.AppMain;
import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.util.ImageUtil;
import com.sinse.shop.common.view.Page;
import com.sinse.shop.product.model.Product;

public class MainPage extends Page{
	JPanel p_visual;   //메인 비쥬얼 영억(메인 배너 - carousel? 움직이는 영역?)
	JPanel p_content;  // 상품이 출력될 영역 
	ImageUtil imageUtil = new ImageUtil(); // 쓸거니까 보유
	Image image;

	
	
	//최신 상품 목록 중 유저가 지금 클릭한 그 상품
	public Product product;
	
	
	public MainPage(AppMain appMain) {
		super(appMain);
		// 생성
		
		// 이미지를 paint 하자 -> 패널을 이름없는 익명 클래스로 재정의, 별도의 .java 파일을 생성할 필요 없음
		//내부클래스여서 외부클래스인 MainPage의 멤버를 공유할 수 있다. (매우 많이 씀)
		
		image = imageUtil.getImage("images/banner.jpg", Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT);
		
		p_visual = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);  //update()에 지워진 배경을 스스로 복구 
				
				//Toolkit은 이미지를 구성하는 바이트 정보에 접근 불가 
				//BufferedImage 객체를 이용하여 얻어온 이미지는 훨씬 더 다양한 제어가 가능하다
				
				
				//우리가 원하는 그림을 그리자.. 즉 패널의 그림을 뺏어 그리자 
				g.drawImage(image, 0, 0, Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT, p_visual);  //이미지옵저버: 이미지를 포함하려는 패널을 넣으면 됨 (우리가 알 필요 없다)
				
			}
		};   
		
		p_content = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		
		//스타일
		p_visual.setBackground(Color.WHITE);
		p_content.setBackground(Color.BLACK);
		p_visual.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT));
		p_content.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, 410));  // config로 따로 관리할 수 있지만 지금은..안함 
		
		this.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT - (Config.UTIL_HEIGHT + Config.NAVI_HEIGHT)));
		
		//조립
		add(p_visual);
		add(p_content);
		
		setVisible(true);
		
	}

}
