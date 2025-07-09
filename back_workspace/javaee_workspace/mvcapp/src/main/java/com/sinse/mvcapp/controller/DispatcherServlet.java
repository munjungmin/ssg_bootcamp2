package com.sinse.mvcapp.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DispatcherServlet extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//이 서블릿의 인스턴스가 생성될 때, 초기화가 진행되고, 초기화 진행 시점에 설정파일로부터 해당 요청에 대한
	FileInputStream fis;
	Properties props;
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		String realPath = context.getRealPath(config.getInitParameter("contextConfigLocation"));    // WEB-INF는 브라우저에서 접근하는걸 막은거지 자바 코드에서는 접근 가능하다.
		
		logger.debug(realPath);
		
		
		try {
			fis = new FileInputStream(realPath);
			props = new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 *  매 요청마다 1:1 대응되는 매핑을 피하기 위해 하나의 진입점으로 몰았으나, 
		 *  진입점이 되는 클래스가 매 요청마다 1:1 대응되는 if 조건문이 작성되고 있음 
		 */
		Controller controller = null;
		// 혈액형을 전문적으로 처리하는 컨트롤러에게 업무 분담!
		// 요청에 대한 처리를 1:1 대응하는 객체로 처리하는 개발 패턴을 가리켜 Command Pattern
		//controller = new BloodController();
		logger.debug(props.getProperty("/blood.do"));
		//new 연산자 만이 인스턴스를 생성할 수 있는건 아니다!!
		try {
			Class clazz = Class.forName(props.getProperty(request.getRequestURI()));
			controller = (Controller)clazz.newInstance();
			controller.execute(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		if(fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}

}
