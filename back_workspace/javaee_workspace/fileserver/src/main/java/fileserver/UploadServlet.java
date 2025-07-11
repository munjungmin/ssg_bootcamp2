package fileserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadServlet extends HttpServlet{
	
	//클라이언트의 요청이 post 방식이므로, doPost 재정의 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");  // jsp 지시영역의 page contentType과 같다.
		
		
		// 파일은 어떻게 받지 
		PrintWriter out = response.getWriter();
		out.print("post 요청 받음");
		
		// 직접 파일 업로드 하려면 스트림,, 소켓 제어 등등 필요
		// 파일 업로드 컴포넌트 중 cos.jar 써보자 
		// 1번 생성자: 한글 파일인 경우 이름이 깨져서 탈락
		//MultipartRequest multi = new MultipartRequest(request, "C:\\repos\\ssg_bootcamp2\\back_workspace\\javaee_workspace\\fileserver\\src\\main\\webapp\\data");
		
		// 4번 생성자 인코딩 설정 가능 
		int maxLimit = 3 * 1024 * 1024;  //3MB
		MultipartRequest multi = new MultipartRequest(request, "C:\\repos\\ssg_bootcamp2\\back_workspace\\javaee_workspace\\fileserver\\src\\main\\webapp\\data", maxLimit, "utf-8");
	}
}
