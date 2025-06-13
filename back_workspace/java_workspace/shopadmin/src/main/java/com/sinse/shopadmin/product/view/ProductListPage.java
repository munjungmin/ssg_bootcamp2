package com.sinse.shopadmin.product.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.view.Page;

public class ProductListPage extends Page{
	JTable table;
	JScrollPane scroll;
	ProductModel productModel;
	
	JPanel p_content;
	JButton bt_regist;
	JButton bt_del;
	
	public ProductListPage(AppMain appMain) {
		super(appMain);
		
		table = new JTable(productModel = new ProductModel());
		scroll = new JScrollPane(table);
		//표의 크기는 실제 add한 대상에 줘야함 (scroll)
		scroll.setPreferredSize(new Dimension(Config.ADMINMAIN_WIDTH - Config.SIDE_WIDTH - 100, 400));
		
		
		add(scroll);
	
		
		
		
		
	}
	
}
