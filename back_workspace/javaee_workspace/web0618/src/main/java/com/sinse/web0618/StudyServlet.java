package com.sinse.web0618;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudyServlet extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출");
	}
	
	/**
	 * service()에서 먼저 요청을 받아 클라이언트의 HTTP 요청 방식이 GET이면 doGet(), Post면 doPost(), Put이면 doPut(), delete면 doDelete()
	 * doXX()형 메서드가 호출된다. 따라서 개발자는 실제 로직을 doXXX형을 오버라이드하여 처리 
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int x = 3;
		x = 7;
		//요청을 마무리 짓는 단계에서 고객이 보아야할 응답 정보를 구성하자
		// out은 웹브라우저에 직접 출력하는게 아니라, response객체에 데이터를 누적하는 것이다. (안에 printStream이 있음)
		PrintWriter out = response.getWriter();
		out.print("<h1>this is 결과 " + x + "</h1>");
	}
	
	@Override
	public void destroy() {
	}

}
