package com.sinse.shopadmin.security;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sinse.shopadmin.AppMain;
import com.sinse.shopadmin.common.config.Config;
import com.sinse.shopadmin.common.util.StringUtil;
import com.sinse.shopadmin.common.view.Page;
import com.sinse.shopadmin.security.model.Admin;

public class LoginForm extends Page{
	
	JLabel la_id;
	JLabel la_pwd;
	JTextField t_id;
	JPasswordField t_pwd;
	JButton bt_login;
	JButton bt_join;
	
	public LoginForm(AppMain appMain) {
		super(appMain);
		la_id = new JLabel("ID");
		la_pwd = new JLabel("Password");
		t_id = new JTextField();
		t_pwd = new JPasswordField();
		bt_login = new JButton("Login");
		bt_join = new JButton("Join");
		
		Dimension d = new Dimension(100, 30);
		la_id.setPreferredSize(d);
		la_pwd.setPreferredSize(d);
		t_id.setPreferredSize(d);
		t_pwd.setPreferredSize(d);
		
		//조립
		this.setLayout(new FlowLayout());
		add(la_id);
		add(t_id);
		add(la_pwd);
		add(t_pwd);
		add(bt_login);
		add(bt_join);
		
		bt_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
		bt_join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appMain.showPage(Config.JOIN_PAGE);
			}
		});
		
		setPreferredSize(new Dimension(270, 145));
		setVisible(true);
	}
	
	
	public void loginCheck() {
		String id = t_id.getText();
		String pwd = new String(t_pwd.getPassword());
		String sql = "select * from admin where id = ? and pwd = ?";  // ? : 바인드 변수
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = appMain.con.prepareStatement(sql);
			//쿼리문을 수행하기 위해 바인드 변수를 먼저 지정해야 한다.
			pstmt.setString(1, id);  //1부터 시작 ...
			
			pstmt.setString(2, StringUtil.getSecuredPass(pwd));
			pstmt.executeQuery();  
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {  //일치하는 데이터가 없으면 커서는 존재하지만 가리키는게 없음
				JOptionPane.showMessageDialog(this, "로그인 성공");
				
				
				// 로그인 이후에 로그인 한 사람의 정보를 메인에 넘겨야됨 
				// 로그인 성공한 사람의 정보 담기 (rs가 가리킬 수 있는걸 모델에 넘기는 이유: rs는 finally에서 닫아야 해서)
				Admin admin = new Admin();
				admin.setAdmin_id(rs.getInt("admin_id"));
				admin.setId(rs.getString("id"));
				admin.setName(rs.getString("name"));
				admin.setPwd(rs.getString("pwd"));
				appMain.admin = admin;
				
				this.setVisible(false);  // 자기 자신은 안보여야함 주의: System.exit(0)는 전체 프로그램이 종료됨
				
				// 현재 유저가 보고 있는 페이지가 MainPage로 교체 
				appMain.showPage(Config.MAIN_PAGE);
				
			}else {
				JOptionPane.showMessageDialog(this, "로그인 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
