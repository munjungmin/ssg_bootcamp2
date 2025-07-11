package imageClient.client;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class UploadClient extends JFrame{
	
	JTextField t_title;
	JFileChooser chooser;
	JButton bt_file; //파일 선택 버튼
	JButton bt_regist;
	File[] files;   //유저가 선택한 파일들 
	
	public UploadClient() {
		t_title = new JTextField(15);
		bt_file = new JButton("파일 선택");
		bt_regist = new JButton("업로드");
		
		
		chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);  //여러 파일 선택 옵션 추가
		
		
		//Flow 
		setLayout(new FlowLayout());
		add(t_title);
		add(bt_file);
		add(bt_regist);
		
		//파일 선택
		bt_file.addActionListener(e -> {
			int result = chooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION) {
				files = chooser.getSelectedFiles();
			}
		});
		
		//파일 업로드
		bt_regist.addActionListener(e -> {
			upload();
		});
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 170);
		setVisible(true);
	}
	
	//유저가 선택한 파일 수만큼 반복하면서 서버로 바이너리 파일을 전송해야함
	public void upload() {
		//톰캣과 통신하려면 HTTP로 말걸어야됨(http 규칙을 따른) -> HTTP 통신이 가능한 api를 이용해야 한다. 
		// 1. 고전적인 방식 HttpURLConnection 객체 이용 
		// 2. 최신 방식 HttpClient 객체 이용 : javaSE 미포함, apache에서 만든 것 -> maven repository -> maven project로 전환 
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//post 방식 흉내내야됨
		HttpPost post = new HttpPost("http://192.168.60.59:8282/upload/regist");
		
		//서버로 전송할 데이터 구성하기 
		StringBody titleBody = new StringBody(t_title.getText(), ContentType.create("text/plain", Consts.UTF_8));
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		
		builder.addPart("title", titleBody);  //텍스트 파라미터 만들기  title은 html의 name과 동일, java 버전의 파라미터
		
		//이미지 수만큼 반복하면서 빌더에 바이너리 채우기 
		for(int i = 0; i < files.length; i++) {
			FileBody fileBody = new FileBody(files[i]);
			builder.addPart("photo" + i, fileBody);  //name을 지정해야 하는데 파일이 여러개니까, photo1, photo2 이런식으로 <input type="file" name="photo1">
		}
		
		//다 완성된 HTTP 파라미터와 그 값을 post 객체에 담기(body에 담겨짐)
		HttpEntity entity = builder.build();   //builder가 전송 직전에 엔티티로 전환됨 
		post.setEntity(entity);
		
		//짐을 챙겼으니 서버로 출발(요청 시도)
		//서버에서 전송한 Http Status Code를 반환받자
		CloseableHttpResponse response = null;  //서버가 전송한 상태코드를 보유한 객체
		try {
			response = httpClient.execute(post);   //웹브라우저가 요청하는 행동과 동일
			
			int status = response.getStatusLine().getStatusCode();  //int형으로된 응답 코드
			if(status == 200) {
				JOptionPane.showMessageDialog(this, "업로드 성공");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public static void main(String[] args) {
		new UploadClient();
	}
}


