package com.sinse.shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.view.Page;
import com.sinse.shop.home.MainPage;

/**
 * com.sinse.shop.도메인
 * com.sinse : 그룹id
 * shop: 프로젝트 id (artifact)
 * 
 * 도메인: 상품, 회원, 주문 ..(home, product, member, order, cs ... ) 
 * 공통이면 global, common (설정, 상수 등)
 * 유틸, 네비게이션 panel, 
 * 그 아래 변경되는 페이지는 .java 파일로 만듦 ?
 */
public class AppMain extends JFrame{
	
	JPanel p_north;     //p_util, p_navi 공존시켜야 하므로
	JPanel p_util;      //최상단 유틸리티 네비 영역 (서브메뉴)
	JPanel p_navi;      //메인 네비게이션
	JPanel p_container; //모든 페이지가 전환될 컨테이너 영역
	
	//유틸리티 네비관련
	JLabel la_login;
	JLabel la_join;   
	JLabel la_cart;
	JLabel la_wishlist;
	
	//메인 네비게이션 관련 
	JLabel la_home;
	JLabel la_category;
	JLabel la_new;
	JLabel la_best;
	JLabel la_cs;
	
	Page[] pages;
	
	public AppMain() {
		
		//생성
		p_north = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));  //flowlayout은 디폴트로 마진 존재, 왼쪽 정렬, 좌우/상하
		p_util = new JPanel(new FlowLayout(FlowLayout.RIGHT));  // 오른쪽 정렬
		p_navi = new JPanel();
		p_container = new JPanel();
		
		la_login = new JLabel("Login");
		la_join = new JLabel("Join");
		la_cart = new JLabel("Cart");
		la_wishlist = new JLabel("WishList");
		
		la_home = new JLabel("Home");
		la_category = new JLabel("Category");
		la_new = new JLabel("New");
		la_best = new JLabel("Best");
		la_cs = new JLabel("CS");
		
		
		//스타일
		p_util.setBackground(Color.YELLOW);
		p_navi.setBackground(Color.PINK);
		p_container.setBackground(Color.GREEN);
		
		p_north.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.UTIL_HEIGHT + Config.NAVI_HEIGHT));
		p_util.setPreferredSize(new Dimension(Config.UTIL_WIDTH, Config.UTIL_HEIGHT));   //이렇게 하면 웹사이트 width 바꿀때 수정할 곳이 너무 많다. -> 상수 집합 파일
		p_navi.setPreferredSize(new Dimension(Config.NAVI_WIDTH, Config.NAVI_HEIGHT));
		p_container.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT));
		
		//la_wishlist.setPreferredSize(new Dimension(200, 20));
		
		//조립
		// borderlayout 북쪽에 util, navi 두개 붙이면 마지막에 붙은거만 보임(겹쳐져서) -> 패널 추가
		
		p_util.add(la_login);
		p_util.add(la_join);
		p_util.add(la_cart);
		p_util.add(la_wishlist);
		
		p_navi.add(la_home);
		p_navi.add(la_category);
		p_navi.add(la_new);
		p_navi.add(la_best);
		p_navi.add(la_cs);
		
		p_north.add(p_util);
		p_north.add(p_navi);
		add(p_north, BorderLayout.NORTH);
		add(p_container);
		
		createPage();   // 앱이 가동될 때 모든 페이지 생성 및 부착
		
		showPage(Config.MAIN_PAGE);  //부착된 페이지들 중 보고싶은 페이지의 index를 넘기자
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //DB 연동 후에 지우기 
		setBounds(300, 50, 1400, 900);
		setVisible(true);
	}
	
	//쇼핑몰의 모든 페이지를 생성하여 부착
	public void createPage() {
		//배열 생성
		pages = new Page[1];  // 본인이 만든 페이지 수로 추후 대체
		
		
		//페이지 생성
		pages[Config.MAIN_PAGE] = new MainPage();
		
		//모든 페이지를 p_container 부착
		for(int i = 0; i < pages.length; i++) {
			p_container.add(pages[i]);
		}
		
	}
	
	// 원하는 페이지를 보여주는 메서드(home, category, new, 등등 누를때마다 보이는 페이지가 동적으로 바뀌어야함) 
	public void showPage(int target) {
		//반복문의 대상이 되려면 모든 페이지는 같은 자료형의 배열로 존재해야 함 -> 최상위 객체 Page
		for(int i = 0; i < pages.length; i++) {
			pages[i].setVisible((i == target) ? true : false);
		}
		
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
	
}
