package networkdemo.unicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 클라이언트가 보낸 메시지를 받아서 다시 클라이언트로 전송해보자
 */

public class Server extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server;
	Thread thread; 
	
	public Server() {
		p_north = new JPanel();
		t_port = new JTextField("9999",8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		area.setBackground(Color.YELLOW);
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		bt.addActionListener(e -> {
			thread = new Thread() {
				public void run() {
					runServer();
				}
			};
			thread.start();
		});
		
		setBounds(1300, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void runServer() {
		
		//접속 감지 서버를 실행시킨다. 
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			
			area.append("Listening...\n");
			Socket socket = server.accept();
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip + " 님 접속\n");
			
			ServerThread chatThread = new ServerThread(this, socket);
			chatThread.start();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
	
}






