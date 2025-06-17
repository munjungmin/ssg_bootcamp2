package com.sinse.networkapp.multicast;

import java.awt.BorderLayout;
import java.awt.Color;
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

import com.sinse.networkapp.unicast.ServerThread;

public class GUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	Thread thread; //서버 가동용 쓰레드 ( 메인쓰레드가 대기에 빠지지 않기 위해 필요)
	
	//ArrayList는 다중 스레드 환경에서 동기화 지원X, 
	// 운이 없다면, 인덱스에 동시에 쓰레드가 접근하게 되는 상황이 발생함 
	//이 경우 개발자가 syncronized{} 블럭으로 코드를 감싸면, 특정 쓰레드가 해당 블럭을 실행하는 동안 다른 스레드는 대기상태로 기다림 => 동기적 실행 (근데 이걸 직접 하면 불안정)
	// 그냥 vector 쓰자
	protected Vector<ServerChatThread> vec = new Vector<>();  
	
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
		
		bt.addActionListener(e->{
			thread = new Thread(()->{
				startServer();
			}); 
			thread.start();
		});
		
		setBounds(1300, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void startServer() {
		int port = Integer.parseInt(t_port.getText());
		try {
			ServerSocket server = new ServerSocket(port);
			area.append("서버 생성 및 접속자 감지 시작\n");
			
			while(true) {
				Socket socket=server.accept(); //접속자가 감지될때까지 무한대기에 있다가, 접속자가 발견되면
															//대화용 소켓이 반환되면서 대기상태는 풀린다..
				String ip=socket.getInetAddress().getHostAddress();
				area.append(ip+"님 접속 감지\n");
				
				ServerChatThread chatThread = new ServerChatThread(this, socket);
				chatThread.start();//쓰레드 동작 시작!!
				
				//현재 서버에 접속한 클라이언트 정보인 
				vec.add(chatThread);
				area.append("현재 " + vec.size() + " 명이 접속했습니다.\n");
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}
	
}


// A 접속시 사용자 명단에 추가




