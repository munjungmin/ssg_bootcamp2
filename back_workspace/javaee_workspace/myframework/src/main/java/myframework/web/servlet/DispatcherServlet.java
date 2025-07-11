package myframework.web.servlet;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import myframework.web.handler.HandlerMapping;

//웹 애플리케이션의 모든 요청을 1차적으로 처리하는 전면 컨트롤러 
public class DispatcherServlet extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	JsonObject root; //설정 파일인 .json을 읽어들인(parsing)한 Gson의 객체
	HandlerMapping handlerMapping; // 이 핸들러에 동생 인스턴스들이 uri 키값을 가지고 모여있다.
	/**
	 *이 서블릿이 초기화될때, 매핑 파일에 등록된 컨트롤러들만 인스턴스를 생성하여 모아야 하는데,
	 *이 서블릿이 직접 하지 않고, 개발자가 등록한 핸들러 매핑에게 맡김
	 *또한 추후 요청을 처리할 때도, 어떤 하위 컨트롤러가 동작해야 하는지도, 핸들러 매핑이 알아서 분석하여 이 서블릿에게 반환한다.
	 *어제까지는 요청이 들어올 때마다 하위 컨트롤러의 인스턴스를 생성하는 방식이기 때문에 메모리 낭비  
	 */
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//초기화 파라미터 읽기 (설정 파일의 위치 얻기)
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		
		//현재 애플리케이션의 정보 얻기
		ServletContext context = config.getServletContext();
		String realPath = context.getRealPath(contextConfigLocation);   //json 파일의 경로를 얻어왔으니 json 파일을 분석해야함  
		
		try(FileReader reader = new FileReader(realPath)){
			root = JsonParser.parseReader(reader).getAsJsonObject();
			logger.debug("root="+root);
			
			String mappingType = root.get("mappingType").getAsString();   // json의 value 타입이 string이라 getAsString, 객체면 또 달라짐
			logger.debug("mappingType=" + mappingType);
			
			// 동작할 HandlerMapping이 누구인지는 모르지만, 그 패키지를 포함한 클래스명이 mappingType 변수에 들어있으므로
			// string을 이용한 클래스 load를 수행할 수 있는 Class.forName을 이용하자.
			Class clazz = Class.forName(mappingType);
			handlerMapping = (HandlerMapping)clazz.newInstance();
			
			handlerMapping.setRoot(root);
			handlerMapping.initialize();
			
		}catch(Exception e) {
			
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
		//해당 요청을 처리
		String uri = request.getRequestURI();
		logger.debug("형님uri=" + uri);
		Controller controller = handlerMapping.getController(uri);
		controller.execute(request, response);
		
		// 하위 컨트롤러로부터 반환받은 뷰의 이름으로 가지고 
		JsonObject viewMappings = root.getAsJsonObject("viewMappings");
		
		Iterator<String> it = viewMappings.keySet().iterator();
		String viewPage = null;
		
		while(it.hasNext()) {
			String viewName = it.next();
			if(controller.getViewName().equals(viewName)) {
				viewPage = viewMappings.get(viewName).getAsString();
				logger.debug("viewPage=" + viewPage);
				break;
			}
		}
		
		//구해온 결과 페이지를 포워딩으로 처리할 경우
		if(controller.isForward()) {
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);
		} else { // 클라이언트로 하여금 결과 페이지를 재접속하게 처리할 경우(redirect)
			response.sendRedirect(viewPage);
		}
	}

}




