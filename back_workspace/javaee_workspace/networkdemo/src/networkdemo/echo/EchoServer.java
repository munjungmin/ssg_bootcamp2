package networkdemo.echo;

import java.awt.BorderLayout;
import java.awt.Color;
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
 * 에코서버가 보낸 메시지를 받아서 화면에 출력해보자
 */

public class EchoServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server;
	Thread thread; 
	
	public EchoServer() {
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
			
			InputStream is = socket.getInputStream();  // BufferedWriter → OutputStreamWriter → 바이트 전송 → 서버 InputStream
			OutputStream os = socket.getOutputStream();
			
			while(true) {
				int data = is.read();
				System.out.println((char)data);
				os.write(data);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//우리가 실행부라고 알고있었던 존재가 사실 메인 쓰레드라 불리는 프로그램 운영 쓰레이다.
	//아이폰, 안아드로이드폰,,,기타,,프로그램에서,  프로그램을 운영하는 메인쓰레드에게 금기시하는 경우가 있음 
	//1) 무한루프 , 2) 대기상태 accept(), read() 
	//메인 쓰레드를 대기상태 빠트리면, 이벤트 감지, GUI  그래픽 처리 불가..모든 게 다 멈춘다..
	public static void main(String[] args) {
		new EchoServer();
	}
	
}






