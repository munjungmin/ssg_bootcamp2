package com.sinse.threadApp.ani;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Gallery extends JFrame{
	
	JPanel p_west;   //
	JPanel p_container;   // p_west 제외한 영역, BorderLayout
	JPanel p_north;
	JPanel p_center;
	
	ImageUtil imageUtil = new ImageUtil();
	Image[] imgArray = new Image[9];
	
	//화면에 렌더링하지는 않지만, 원하는 좌표에 사각형을 메모리 상에 존재시키면, 원하는 영역에 이벤트를 부여할 수 있다(게임에서 자주 쓰는 방식)
	Rectangle[] rectArray = new Rectangle[imgArray.length];
	int idx = 0;
	float targetY;
	float y = 0;
	int curY = 5;
	Thread thread; 
	
	float a = 0.1f;
	
	public Gallery() {
		createImage();
		
		thread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
						move();
						p_west.repaint();
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		thread.start();
		
		p_west = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(int i = 0; i < imgArray.length; i++) {
					g.drawImage(imgArray[i], 5, 5 + (i * 95), 90, 90, p_west);
				}
				
				//그려진 이미지 위로 옮겨다닐 사각 포인터
				Graphics2D g2 = (Graphics2D)g;
				g2.setStroke(new BasicStroke(4));
				g.setColor(Color.RED);
				
				g.drawRect(5, (int)y, 90, 90);
			}
		};
		p_north = new JPanel();
		p_center = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(imgArray[idx], 0, 0, 800, 850, p_center);
			}
		};
		p_container = new JPanel(new BorderLayout());
		
		//스타일
		p_west.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p_west.setPreferredSize(new Dimension(100, 1000));
		
		//조립
		add(p_west, BorderLayout.WEST);
		p_container.add(p_north, BorderLayout.NORTH);
		p_container.add(p_center);
		add(p_container);

		//좌측 패널에 마우스 이벤트 연결
		p_west.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				for(int i = 0; i < rectArray.length; i++) {
					if(rectArray[i].contains(e.getPoint())) {
						targetY = rectArray[i].y;
						idx = i;
						p_center.repaint();
					}
				}
				
				
			}
		});
		
		setVisible(true);
		setSize(900, 900);
	}
	
	public void createImage() {
		for(int i = 0; i < 9; i++) {
			imgArray[i] = imageUtil.getImage("images/geographic/animal" + (i+1) + ".jpg", 90, 90);
			rectArray[i] = new Rectangle(5, 5 + (i * 95), 90, 90);
		}
	}
	
	public void move() {
		//y = 현재y + a * (목표y - 현재y)
		y = y + a * (targetY - y);
		
		
	}
	
	public static void main(String[] args) {
		new Gallery();
	}
}
