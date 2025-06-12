package com.sinse.networkapp.unicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIServer extends JFrame implements Runnable{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Thread thread;  //서버 가동 시, 메인 쓰레드가 accept()에서 대기 상태에 빠지지 않게 하려고 
	ServerSocket server; 
	
	//사용자가 접속할때마다, 몇명이 현재 서버를 사용중인지, 기록할 객체 
	Vector<ServerThread> vec = new Vector<>();
	
	public GUIServer() {
		p_north = new JPanel();
		t_port = new JTextField(8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		area.setBackground(Color.YELLOW);
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		bt.addActionListener(e -> {
			thread = new Thread(GUIServer.this);
			thread.start();
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setBounds(1300, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void startServer(){
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 생성 및 접속자 청취중...\n");
			
			while(true) {
				Socket socket = server.accept();  // 여기서 대기상태에 빠지므로, 쓰레드로 처리했음  소켓이 태어나는 시점 
				String ip = socket.getInetAddress().getHostAddress();
				area.append(ip + " 접속\n");
				
				//접속자 1명당, 대화용 쓰레드인 ServerThread 인스턴스 만들면서 Socket을 선물로 주자!
				ServerThread serverThread = new ServerThread(this, socket);
				serverThread.start();
				vec.add(serverThread);
				area.append("현재 접속자 " + vec.size() + "명\n");
			}
					
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void run() {
		startServer();
	}
	
	
	public static void main(String[] args) {
		new GUIServer();
	}
}

