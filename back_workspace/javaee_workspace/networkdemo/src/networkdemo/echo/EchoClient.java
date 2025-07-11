package networkdemo.echo;

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
 * 서버에 메시지를 보내보자
 */

public class EchoClient extends JFrame{
	JPanel p_north;
	JComboBox box_ip;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	Socket socket; //대화용 소켓,,이 객체를 메모리에 올릴때 접속이 발생함.
							//또한 접속이 성공되면, 그 시점부터 연결이 이루어진 것이므로, 스트림을 통해 데이터를  주고 받을 수 있음
	
	BufferedWriter buffw;
	BufferedReader buffr;

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
		
		//람다는 반드시 함수형 인터페이스(메서드 달랑 1개 인거)에만 사용 가능
		//접속 버튼과 리스너 연결 
		bt.addActionListener(e->{
			connect();
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {//엔터를 쳤을때만..
					//서버로 내보내기!!(출력) 
					String msg = t_input.getText();
					send(msg);
					t_input.setText("");
				}
			}
		});
		
		setBounds(1000, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void send(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		//소켓서버에 접속해 보기 
		String ip=(String)box_ip.getSelectedItem();
		
		//접속 시도...
		try {
			socket = new Socket(ip, Integer.parseInt(t_port.getText()));
			
			//소켓으로부터 스트림을 얻어오자
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));  //바이트 스트림을 바이트스트림리더로 읽어서 문자 스트림으로 바꾸고 그 문자 스트림을 문자스트림리더로 읽음 
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
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
		new EchoClient();
	}

}