package com.sinse.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinse.mvcapp.color.model.BloodManager;
import com.sinse.mvcapp.color.model.ColorManager;

public class DispatcherServlet extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getRequestURI().equals("/blood.do")) { //클라이언트의 
		
			//요청을 받는다. 
			//요청 분석 
			String blood = request.getParameter("blood");
			
			BloodManager manager = new BloodManager();
			String result = manager.getAdvice(blood);
			
			HttpSession session = request.getSession();  // 이 요청에 의해 접근할 수 있는 세션 얻기 
			session.setAttribute("msg", result);  // 4. 데이터 보관
			
			//결과를 보여줄 알맞는 뷰 선택
			response.sendRedirect("/blood/result.jsp");   //클라이언트의 재접속 강제
		} else if(request.getRequestURI().equals("/color.do")) {
			//요청을 받는다. 
			//요청 분석 
			String color = request.getParameter("color");
			
			ColorManager manager = new ColorManager();
			String result = manager.getAdvice(color);
			
			HttpSession session = request.getSession();  // 이 요청에 의해 접근할 수 있는 세션 얻기 
			session.setAttribute("msg", result);  // 4. 데이터 보관
			
			//결과를 보여줄 알맞는 뷰 선택
			response.sendRedirect("/color/result.jsp");   //클라이언트의 재접속 강제
		}
	}

}
