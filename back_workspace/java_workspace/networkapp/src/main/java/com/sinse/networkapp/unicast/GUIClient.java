package com.sinse.networkapp.unicast;

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

public class GUIClient extends JFrame implements Runnable{
	
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
	
	
	public GUIClient() {
		p_north = new JPanel();
		box_ip = new JComboBox();
		t_port = new JTextField("9999",8);
		bt = new JButton("접속");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		t_input = new JTextField();
		
		area.setBackground(Color.YELLOW);
		
		p_north.add(box_ip);
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(t_input, BorderLayout.SOUTH);
		
		createIp();
		
		bt.addActionListener(e -> {
			thread = new Thread(GUIClient.this);
			thread.start();
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//보내고 
					send();
					t_input.setText("");
					
					//듣자
					listen();
				}
			}
		});
		
		setBounds(1000, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//서버에 메시지 보내기 
	public void send() {
		String msg = t_input.getText();
		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			area.append(msg + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connectServer() {
		try {
			socket = new Socket((String)box_ip.getSelectedItem(), Integer.parseInt(t_port.getText()));
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		connectServer();
		
	}
	
	public void createIp() {
		for(int i=20;i<=59;i++) {
			box_ip.addItem("192.168.60."+i);
		}
	}
	
		
	public static void main(String[] args) {
		new GUIClient();
	}
	
}
