package com.sinse.networkapp.unicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//접속자마다 1:1 대응하여 인스턴스가 생성될 대화용 쓰레드 
// 대화가 가능하려면, 입력, 출력 스트림이 필요함 
public class ServerThread extends Thread{
	GUIServer guiServer;
	Socket socket;
	BufferedReader buffr; 
	BufferedWriter buffw; 
	
	//소켓을 서버로부터 전달받으면 된다. 접속자가 들어올 때마다 
	public ServerThread(GUIServer guiServer, Socket socket) {
		this.socket = socket;
		this.guiServer = guiServer;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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
	
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			guiServer.area.append(msg + " \n");
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String msg) {
		try {
			buffw.write(msg + "/n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
