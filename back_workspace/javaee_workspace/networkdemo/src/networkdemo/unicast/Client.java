package networkdemo.unicast;

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
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * unicast: 한 송신자(서버) -> 한 수신자에게만 데이터 전송 (1:1)
 * 서버에게서 오는 메시지를 읽어보자. (서버는 내가 보낼때 그 메시지를 그대로 메아리처럼 전송)
 */

public class Client extends JFrame implements Runnable{
	JPanel p_north;
	JComboBox box_ip;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;

	Thread thread; 
	Socket socket;   //서버와 통신할 용도의 소켓
	BufferedReader br; 
	BufferedWriter bw;
	
	public Client() {
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

		
		bt.addActionListener(e -> {
			
			thread = new Thread(Client.this); 
			thread.start();
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					send();
					listen();
				}
			}
		});
		setBounds(1000, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void run() {
		connect();
	}
	
	public void send() {
		String msg = t_input.getText();
		try {
			bw.write(msg);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen() {
		System.out.println("Client.listen()");
		try {
			String msg = br.readLine();    //   
			area.append(msg + "\n"); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Client.listen() 종료");
	}
	
	
	
	public void connect() {
		String ip = (String)box_ip.getSelectedItem();
		int port = Integer.parseInt(t_port.getText());
		
		try {
			socket = new Socket(ip, port);
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createIp() {
		box_ip.addItem("192.168.60.59");
	}
	public static void main(String[] args) {
		new Client();
	}

}

