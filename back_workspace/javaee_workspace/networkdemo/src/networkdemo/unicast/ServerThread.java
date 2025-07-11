package networkdemo.unicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	Server server;
	Socket socket;
	
	BufferedReader br;
	BufferedWriter bw;
	
	public ServerThread(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
		
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));   // BufferedWriter → OutputStreamWriter → 바이트 전송 → 서버 InputStream
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println("ServerThread.listen()");
			listen();
		}
	}
	
	public void listen() {
		String data = null;
		try {
			data = br.readLine();
			server.area.append(data);
			send(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void send(String msg) {
		try {
			bw.write(msg+"/n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
