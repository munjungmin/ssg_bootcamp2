package com.sinse.web0618.member.controller;

import java.io.IOException;
import java.io.PrintWriter;import java.util.EmptyStackException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.web0618.exception.MemberException;
import com.sinse.web0618.member.model.Member;
import com.sinse.web0618.member.repository.MemberDAO;

//클라이언트의 각종 요청을 처리하는 회원 관련 서블릿
// 일반클래스에서 httpservlet을 상속받으면 서버에서 동작하는 클래스로!
public class MemberRegist extends HttpServlet{
	MemberDAO memberDAO = new MemberDAO();
	
	//웹브라우저인 클라이언트가 post로 전송 (join.html에서 전송 버튼 클릭) -> 서버는 반드시 doPost() 메서드로 요청을 처리해야함 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//네트워크를 타고 전송된 파라미터와 그 값들을 받아서 처리하자 
		//이때 사용되는 객체가 '요청'정보를 가진 request 객체이다.
		// 보낼때 직렬화? 해서 보내가지고 무조건 String으로 받는다.
		String id = request.getParameter("id");   //html 컴포넌트에 부여한 name속성의 값을 적는다.
		String pwd = request.getParameter("pwd");   //html 컴포넌트에 부여한 name속성의 값을 적는다.
		String name = request.getParameter("name");   //html 컴포넌트에 부여한 name속성의 값을 적는다.
		
		System.out.println("전송된 아이디: " + id);   //Tomcat 서버의 콘솔에 출력됨
		System.out.println("전송된 패스워드: " + pwd);   //Tomcat 서버의 콘솔에 출력됨
		System.out.println("전송된 이름: " + name);   //Tomcat 서버의 콘솔에 출력됨
		
		//db insert
		Member member = new Member();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(name);
		
		String resultMsg = null;
		try {
			memberDAO.insert(member);
			resultMsg = "회원가입을 축하합니다.";
		} catch (MemberException e) {
			resultMsg = e.getMessage();
			e.printStackTrace();
		}
		
		
		// 회원가입 후에는, 유저가 보게 될 디자인 코드를 작성하여, 결과를 보여줘야 한다. 
		//이 메서드의 매개변수 2개 중 첫번째 매개변수인 request 객체는 클라이언트의 요청 정보를 가지고 있고, 
		//response 객체는 클라이언트가 보게될 응답 정보를 개발자가 구성할 수 있는 객체 
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ resultMsg
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>");
		
		
		
		
	}
}
