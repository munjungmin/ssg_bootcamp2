package chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame implements Runnable{
	JPanel p_north;
	JComboBox box_ip;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	Thread thread; 
	Socket socket; //종이컵
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public EchoClient() {
		p_north = new JPanel();
		box_ip = new JComboBox();
		t_port = new JTextField("9999",8);
		bt = new JButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input = new JTextField();
		
		
		area.setBackground(Color.YELLOW);
		
		createIp();
		
		
		p_north.add(box_ip);
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(t_input, BorderLayout.SOUTH);
		
		//접속 버튼과 리스너 연결
		bt.addActionListener(e -> {
			thread = new Thread(EchoClient.this);
			thread.start();  // runnable 상태로 변경 
		});
		
		//텍스트 입력 컴포넌트와 리스너 연결 
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					send();
					listen();
					t_input.setText("");
				}
			}
		});
		
		
		setBounds(1000, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//서버의 메시지 듣기
	public void listen() {
		String msg = null;
		
		try {
			msg = buffr.readLine();
			area.append(msg + "\n"); //로그 남기기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버에 메시지 보내기
	public void send() {
		
		//버퍼 처리된 출력스트림 계열을 데이터 전송 시 버퍼를 비워야 함 
		try {
			String msg = t_input.getText(); 
			buffw.write(msg + "\n");  //버퍼 기반의 스트림이므로, 문자열의 끝을 알려줘야 한다.
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void run() {
		//대화용 소켓을 생성 == 접속
		connect();
		
	}
	
	
	public void connect() {
		String ip = (String)box_ip.getSelectedItem();
		
		try {
			socket = new Socket(ip, Integer.parseInt(t_port.getText()));
			area.append("접속 성공\n");
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (NumberFormatException | IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void createIp() {
		for(int i=20;i<=59;i++) {
			box_ip.addItem("192.168.60."+i);
		}
	}
	
		
	public static void main(String[] args) {
		new EchoClient();
	}

}