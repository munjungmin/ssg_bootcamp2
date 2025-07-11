package notice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 글쓰기 요청을 처리하는 서버측의 서블릿 정의
public class WriteServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//요청을 받고 전송된 데이터에 대한 언어 인코딩 
		request.setCharacterEncoding("utf-8"); //보낼때 쪼갠 데이터를 어떻게 복원할지를 결정  
		response.setContentType("text/html;charset=utf-8");   //고양이가 클라이언트에게 보여줄 문서를 만들때 다국어 인코딩하도록 
		
		//text/html과 같은 파일의 종류, 유형을 가리켜 마임타입(MIMEType)이라고 함
		//application/json  text/xml  image/jpeg  ... 
		
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		System.out.println(title);
		
		PrintWriter out = response.getWriter();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "shop", "1234");
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into notice(title, writer, content) values(?, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int result = pstmt.executeUpdate();   //DML 수행
			if(result > 0) {
				//문자열을 적재해 놓으면, 응답 정보를 만들때 톰캣이 참고한다.
				//브라우저에서 직접 작성하는 스크립트는 ';'생략해도 잘 작동하지만, 서버에서 문자열로 작성하는 js 코드는 문장의 끝에 반드시 명시해야함. (안하면 한 줄이 안끝났다고 생각하고 대기상태에 빠짐) 
				out.print("<script>");
				out.print("alert('글 등록 완료');");    
				out.print("location.href='/notice/list';");
				out.print("</script>");
			} else {
				out.print("<script>");
				out.print("alert('글 등록 실패');");    
				out.print("history.back();");  //브라우저의 뒤로가기 버튼과 동일 
				out.print("</script>");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
