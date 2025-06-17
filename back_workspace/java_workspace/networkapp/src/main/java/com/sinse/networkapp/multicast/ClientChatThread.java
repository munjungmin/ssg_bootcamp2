package com.sinse.networkapp.multicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//client가 메시지를 보낼때만 청취를 하게 되면, 실시간으로 서버에서 진행되고 있는 메시지들을 모두 받을 수 없으므로, 유저가 특별한 action을 취하지 않아도 실시간으로 메시지를 받기 위해 
// 스레드로 정의하고, 특히 listen()을 무한루프로 돌리자 

public class ClientChatThread extends Thread{
	
	Client client;
	Socket socket;
	BufferedReader br;
	BufferedWriter bw;
	
	public ClientChatThread(Client client, Socket socket) {
		this.client = client;
		this.socket = socket;
		
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			listen();
		}
	}
	
	public void listen() {   //항상 listen
		System.out.println("listen()");
		String msg = null;
		try {
			msg = br.readLine();
			System.out.println(msg);
			client.area.append(msg + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String msg) {    //원할때만 (엔터칠때) send
		try {
			bw.write(msg+ "\n");
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}





/*
 * 특정 유저에게만 send 
 * 
 */
