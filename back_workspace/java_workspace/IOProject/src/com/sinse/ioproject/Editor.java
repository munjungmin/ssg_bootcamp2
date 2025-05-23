package com.sinse.ioproject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Editor extends JFrame implements ActionListener{
	JMenuBar bar; //눈에 보이지는 않지만 메뉴들을 얹혀놓을 막대기, 위치가 윈도우 창 상단으로 고정돼있다.
	JMenu[] menus = new JMenu[5];  //메뉴는 없는 상태임
	String[] menuTitles = {"파일", "편집", "서식", "보기", "도움말"};
	JMenuItem[] items = new JMenuItem[8];
	String[] itemTitles = {"새로 만들기", "새 창", "열기", "저장", "다른 이름으로 저장", "페이지 설정", "인쇄", "끝내기"};
	JTextArea area;
	JFileChooser chooser;
	
	// 메뉴에 아이템을 붙일때 index로 접근하는게 직관성이 떨어짐 -> 메뉴이름을 상수로 정의
	public static final int FILE = 0;
	public static final int EDIT = 0;
	public static final int STYLE = 0;
	public static final int VIEW = 0;
	public static final int HELP = 0;
	
	
	public Editor() {
		bar = new JMenuBar();
		chooser = new JFileChooser("C:\\lecture_workspace\\back_workspace\\java_workspace");
		area = new JTextArea();
		
		//메뉴, 메뉴아이템 생성 
		for(int i = 0; i < menus.length; i++) { menus[i] = new JMenu(menuTitles[i]); }
		for(int i = 0; i < items.length; i++) {
			items[i] = new JMenuItem(itemTitles[i]);
		
		}
		
		// 메뉴아이템을 메뉴에 부착, 메뉴를 메뉴바에 부착, 메뉴바를 프레임에 부착
		for(int i = 0; i < items.length; i++) { 
			menus[FILE].add(items[i]); 
			if(i==4 ||i==6) menus[FILE].addSeparator();
		}
		for(int i = 0; i < menus.length; i++) {
			
			bar.add(menus[i]);
		}		
		this.setJMenuBar(bar);
		add(area);
		
		
		items[2].addActionListener(this); //열기 메뉴에 이벤트 연결 		
		items[items.length-1].addActionListener(this);  // 끝내기 메뉴에 이벤트 연결
		
		setBounds(1000, 100, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void openFile() {
		File file = null;
		FileInputStream fis = null;
		
		//어떤 파일을 대상으로 열지는 개발자가 아니라 사용자가 결정 -> 새창을 띄워주자
		int result = chooser.showOpenDialog(this);
		
		if(result == JFileChooser.APPROVE_OPTION) { //열기 누르면
			file = chooser.getSelectedFile();	
		}
		
		try {
			fis = new FileInputStream(file);
			
			int data = -1;
			
			while((data = fis.read()) != -1) {
				area.append(Character.toString((char)data));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == items[items.length -1]) {  //exit 
			if(JOptionPane.showConfirmDialog(this, "프로그램을 종료하시겠습니까?") == JOptionPane.OK_OPTION) {
				System.exit(0); //프로세스 종료
			}
		}else if(e.getSource() == items[2]) {  //open
			openFile();
		}
		
	}
	
	public static void main(String[] args) {
		new Editor();
	}

}
