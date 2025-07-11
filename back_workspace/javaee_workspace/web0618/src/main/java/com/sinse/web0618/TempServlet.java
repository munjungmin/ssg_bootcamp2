package com.sinse.web0618;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *서블릿: 자바의 클래스 중 오직 javaEE 기반의 서버에서만 해석 및 실행될 수 있는 클래스 
 *서블릿은 개발자가 인스턴스를 생성하는 방법이 아니라, 개발자는 그냥 클래스 작성 후 서버에 올려놓으면 된다.
 * 그런 후 최초 요청에 의해 인스턴스가 한번 올라간다 (by tomcat)
 * 서블릿이 언제 태어나고, 어떤 일을 수행하며, 언제 소멸하는지에 대한 하나의 주기를 서블릿의 Life Cycle이라 한다.
 * 
 *  서블릿의 생명주기 관련 메서드
 *  init()   : 서블릿이 태어난 직후, 초기화를 위해 호출 (call by tomcat)
 *  service(): 서블릿이 클라이언트의 요청을 처리할때 호출됨 (call by thread)
 *  destroy(): 서블릿이 소멸될 때 호출됨 (call by tomcat)
 */

public class TempServlet extends HttpServlet{
	// init()은 생성된 이후에 init()이 호출되기 때문에 생성자보다 호출이 늦다.
	
	@Override
	public void init() throws ServletException {
		System.out.println("init(): 저 방금 태어나서 제가 누군지 알게되었어요");
	}
	
	//서블릿은 클라이언트의 요청을 처리하기 위해 태어났으므로, 이 요청 처리를 담당하는 메서드가 바로 service()
	//고객의 요청을 처리하려면 뭘 원하는지에 대한 요청 정보와 요청에 대한 응답 정보를 가지고 있어야 한다.
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service(): 요청을 처리합니다.");
	}
	
	//서블릿이 소멸하는 단계에서 호출되는 메서드 
	@Override
	public void destroy() {
		System.out.println("destroy(): 저 갑니다.. 여기서 무언가 다 반납할게요");
		
		
	}

}
