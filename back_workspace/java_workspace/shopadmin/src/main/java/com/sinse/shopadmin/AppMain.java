package com.sinse.shopadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.config.view.ConfigPage;
import com.sinse.shopadmin.cs.view.CustomerPage;
import com.sinse.shopadmin.main.view.MainPage;
import com.sinse.shopadmin.member.view.MemberJoin;
import com.sinse.shopadmin.member.view.MemberPage;
import com.sinse.shopadmin.order.view.OrderPage;
import com.sinse.shopadmin.product.view.ProductPage;
import com.sinse.shopadmin.security.LoginForm;
import com.sinse.shopadmin.security.model.Admin;

public class AppMain extends JFrame{
	
	JPanel p_north; 
	JPanel p_west; 		 //사이드 메뉴 영역 
	JPanel p_container;  // 페이지가 교체될 영역
	
	JLabel la_user;  	 //현재 로그인한 유저
	
	JLabel la_product;
	JLabel la_order;
	JLabel la_member;
	JLabel la_cs;
	JLabel la_config;
	
	public Connection con;  // 원래 getter 해야되는데 귀찮 (웹에서는 이렇게 안함)
	public Admin admin;
	
	Page[] pages;
	 
	public AppMain() {
		//생성
		p_north = new JPanel();
		p_west = new JPanel();
		p_container = new JPanel();
		
		la_user = new JLabel("Scott");
		
		la_product = new JLabel("상품관리");
		la_order = new JLabel("주문관리");
		la_member = new JLabel("회원관리");
		la_cs = new JLabel("고객센터");
		la_config = new JLabel("환경설정");
		
		//스타일 
		p_north.setBackground(Color.PINK);
		p_north.setPreferredSize(new Dimension(Config.UTIL_WIDTH, Config.UTIL_HEIGHT));
		
		p_west.setPreferredSize(new Dimension(Config.SIDE_WIDTH, Config.SIDE_HEIGHT));
		p_west.setBackground(Color.ORANGE);
		
		p_container.setPreferredSize(new Dimension(Config.ADMINMAIN_WIDTH, Config.ADMINMAIN_HEIGHT-Config.UTIL_HEIGHT));
		p_container.setBackground(Color.YELLOW);
		
		Dimension d = new Dimension(180, 100);
		la_product.setPreferredSize(d);
		la_order.setPreferredSize(d);
		la_member.setPreferredSize(d);
		la_cs.setPreferredSize(d);
		la_config.setPreferredSize(d);
		Font f = new Font(null, Font.BOLD, 30);
		la_product.setFont(f);
		la_order.setFont(f);
		la_member.setFont(f);
		la_cs.setFont(f);
		la_config.setFont(f);
		
		
		la_config.setAlignmentX(CENTER_ALIGNMENT);
		
		//조립
		p_north.add(la_user);
		
		p_west.add(la_product);
		p_west.add(la_order);
		p_west.add(la_member);
		p_west.add(la_cs);
		p_west.add(la_config);
		
		add(p_north, BorderLayout.NORTH);
		add(p_west, BorderLayout.WEST);
		add(p_container);
		

		
		//모든 버튼에 이벤트 연결
		la_product.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.PRODUCT_PAGE);
			}
		});
		la_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.ORDER_PAGE);
			}
		});
		la_member.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.MEMBER_PAGE);
			}
		});
		la_cs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.CUSTOMER_PAGE);
			}
		});
		la_config.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.CONFIG_PAGE);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//db 접속 끊기 
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				//프로세스 종료
				System.exit(0);
			}
		});
		
		//DB 접속
		connect();
		createPage();
		showPage(Config.LOGIN_PAGE);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 50, 1300, 800);
		setVisible(true);
	}
	
	public void connect() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Config.url, Config.user, Config.pwd);
			
			if(con != null) {
				this.setTitle("DB 접속 성공");
			}else {
				this.setTitle("DB 접속 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//쇼핑몰에 사용할 모든 페이지 생성 및 부착
	public void createPage() {
		pages = new Page[8];
		
		pages[0] = new LoginForm(this);
		pages[1] = new MainPage(this);
		pages[2] = new ProductPage(this);
		pages[3] = new OrderPage(this);
		pages[4] = new MemberPage(this);
		pages[5] = new CustomerPage(this);
		pages[6] = new ConfigPage(this);
		pages[7] = new MemberJoin(this);
		
		for(int i = 0; i < pages.length; i++) {
			p_container.add(pages[i]);
		}
	}
	
	//부착된 페이지들 대상으로, 어떤 페이지를 보여줄지를 결정하는 메서드 
	public void showPage(int target) {
		if(target != Config.LOGIN_PAGE && admin == null && target != Config.JOIN_PAGE) {
			JOptionPane.showMessageDialog(this, "로그인이 필요한 서비스입니다.");
			return;
		}
		
		for(int i = 0; i < pages.length; i++) {
			pages[i].setVisible((i == target) ? true : false);
		}
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
}
