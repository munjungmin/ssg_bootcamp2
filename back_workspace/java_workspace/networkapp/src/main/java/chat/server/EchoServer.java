package chat.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 개발자는 네트워크에 대한 직접 연동을 하지 않고, Socket을 통해 스트림을 얻어와, 결국 스트림 제어만 하면 됨
 * 개발자가 네트워크에 대한 지식이 없이도, 네트워크 연동 프로그램을 작성할 수 있도록 지원해주는 객체가 Socket이다.
 * 1) 에코서버와 클라이언트 모델
 * 		-다중 접속 불가 
 * 2) 유니캐스팅 모델
 *  	-다중 접속 가능
 *  	- 서버에 접속된 수많은 사람들이, 에코로 운영됨  
 * 3) 멀티캐스팅 모델 
 * 
 */
public class EchoServer extends JFrame implements Runnable{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server;   //대화용 소켓이 아니라, 접속자를 감지하고, 접속이 감지되면 대화용 소켓을 반환, 서버가동버튼 누르면 메모리에 올라와야함 
	Thread thread; //Runnable은 쓰레드가 아니므로, Runnable을 구현한다고 하여, 이 객체가 쓰레드라고 오해하면 안된다. 별도의 쓰레드 객체를 사용해야 함 
	// 단지 해당 스레드의 run 메서드를 내가 가진 것 뿐이다.
	
	//클라이언트와의 대화를 위한 스트림 준비
	BufferedReader buffr;  //듣기용
	BufferedWriter buffw;  //말하기 용 
	
	public EchoServer() {
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
		
		//가동 버튼에 리스너 연결
		bt.addActionListener(e -> {
			thread = new Thread(EchoServer.this);   //그냥 this라고 쓰면 람다의 익명객체를 의미하므로 
			thread.start();
		});
	
		setBounds(1300, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void run() {
		startServer();
	}
	
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 객체 생성 및 사용자 접속 청취중...\n");
			Socket socket = server.accept();  //접속자가 있을 때까지 무한 대기에 빠짐, 대화형 소켓 반환
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip + " 접속자 발견\n");
			
			//얻어진 소켓으로부터 스트림 두 개 뽑자 
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//클라이언트가 보낸 메시지 듣기 
			while(true) {
				String msg = buffr.readLine();
				area.append(msg + "\n");   //서버에 로그 남기기 
				buffw.write(msg + "\n");   //버퍼 기반의 스트림이므로, 문자열의 끝을 알려주지 않으면 무한 대기에 빠짐
				buffw.flush();
			}
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 우리가 알고있던 실행부라 불렀던 대상의 정체가 사실 쓰레드였다.. (주의: 일반 스레드 아님)
	 * 프로그램 운영 스레드이다. 운영스레드의 주 역할(이벤트 감지, gui구성, 관리 ...)
	 * 스마트폰 개발 시, 메인스레드 자체를 대기 상태에 넣는 것 자체가 에러. 금지.  
	 */
	public static void main(String[] args) {
		new EchoServer();
	}
}
