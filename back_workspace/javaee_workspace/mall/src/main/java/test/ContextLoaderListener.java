package test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//톰캣이 가동될 때, 감지 처리 
public class ContextLoaderListener implements ServletContextListener{

	//Tomcat같은 웹컨테이너가 가동될 때 동작하는 메서드 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("서버 가동됨");
		//서버가 가동될때 모든 서블릿이 공유할 수 있는 ...그런 걸 미리 만들려고
		
		//애플리케이션 수준의 객체인 ServletContext에 나의 이름을 저장
		ServletContext context = sce.getServletContext();
		context.setAttribute("tel", "010-2222-3333");
		
		//스프링에 사용될 빈즈들을 
		
	}
	
	//Tomcat같은 웹컨테이너가 중단될 때 동작하는 메서드
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("서버 중단됨");
	}


}
