package com.sinse.shopdemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppMain extends JFrame{
	
	JPanel p_north, p_util, p_navi, p_container; 
	JLabel la_login, la_join, la_cart, la_wishlist;
	JLabel la_home, la_category, la_new, la_best, la_cs;
	
	public AppMain() {
		p_north = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		p_util = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
		
		p_north.setPreferredSize(new Dimension(1400, 90));
		p_util.setPreferredSize(new Dimension(1400, 40));
		p_navi.setPreferredSize(new Dimension(1400, 50));
		p_container.setPreferredSize(new Dimension(1400, 810));
		
		//조립
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
		
		
		setBounds(300, 50, 1400, 900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
    public static void main(String[] args){
        new AppMain();
    }
}
