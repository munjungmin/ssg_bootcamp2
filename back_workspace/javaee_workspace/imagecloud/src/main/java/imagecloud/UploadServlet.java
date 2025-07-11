package imagecloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

// 클라이언트가 전송한 텍스트 데이터, 바이너리 데이터까지 처리해보기
public class UploadServlet extends HttpServlet{
	
	String savePath = "C:\\repos\\ssg_bootcamp2\\back_workspace\\javaee_workspace\\imagecloud\\src\\main\\webapp\\public";  //바이너리 파일이 저장될 서버의 경로 (누구나 접근 가능한 공용 디렉토리 필요 -> public 생성)
	int maxLimit = 3 * 1024 * 1024;  //유지보수 목적상 계산결과를 쓰지말고 풀어서 쓰는게 좋다.
	//클라이언트가 상당히 많은 양의 바이너리로 요청을 시도하므로, 당연히 post로 전송 (서버에서 받아올때는 get, 내걸 서버로 보낼때는 post)
	// 이걸로 스윙과 통신이 가능? ㄷㄷ 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");  //전송되는 파라미터의 값들이 깨지지 않도록 인코딩 지정 
		response.setContentType("text/html;charset=utf-8");   //writer 얻어오기 전에 해야됨 
		PrintWriter out = response.getWriter();

		//업로드 처리 
		
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, maxLimit, "utf-8");
			System.out.println("업로드 성공");
			
			// 텍스트파라미터 추출, 파일업로드 컴포넌트를 이용하면 파라미터 추출마저도 업로드 컴포넌트를 이용 
			String title = multi.getParameter("title");  //무조건 String 반환, 그냥 
			out.print(title);
			
			//순서없는 컬렉션 객체를 처리할 때, 사용되는 도구가 2가지 유형이 있다(enumeration, iterator)
			//그 중 Enumeration을 제어한다. 
			Enumeration<String> en = multi.getFileNames();   //파일 태그의 name 속성값을 반환!! 업로드 선택한 파일명이 아니다.
			
			while(en.hasMoreElements()) {
				String param = en.nextElement();  //제네릭으로 표시안하면 Object반환돼서 (String) 강제 형변환 필요
				out.print("업로드된 파라미터명은 " + param);   // 클라이언트가 브라우저일때 
				System.out.println("업로드된 파라미터명은 " + param);
				
				//파라미터명을 이용하면 업로드된 파일명도 추출가능
				String filename = multi.getOriginalFileName(param);
				out.print("업로드된 파일명은 " + filename);
				System.out.println("업로드된 파일명은 " + filename);  //클라이언트가 브라우저가 아닐때
			}
			
			//[파일처리 관련]
			//1) 이미 서버에 저장된 이미지를 개발자가 원하는 이미지로 교체 
			// 2) 클라이언트 측에서 파일명을 결정짓고 업로드하면, 이 과정은 불필요
			
			/**
			 * collection framework란? 
			 * 객체를 모아서 처리할 때, 효율적으로 처리할 수 있도록 지원되는 java util 패키지에서 지원하는 api
			 * 1. 순서 있는 모음 - List   (배열은 기본 자료형도 제어하므로, 즉 객체만을 다루진 않아서 컬렉션프레임웍은 아니다.)  
			 * 2. 순서 없는 모음 - Set, Map 
			 * 
			 */
			
			
		} catch (IOException e) {
			System.out.println("업로드 실패, 용량 등을 확인하세요");
			e.printStackTrace();
		}
		
		
	}
}
