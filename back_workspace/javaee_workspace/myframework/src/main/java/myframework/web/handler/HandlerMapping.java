package myframework.web.handler;

import com.google.gson.JsonObject;

import myframework.web.servlet.Controller;

public interface HandlerMapping {
	
	//DispatcherServlet이 보유한 Root JsonObject가 있어야 json 설정 파일을 해석 가능하므로, 넘겨받자
	//얘도, amdin-servlet.json을 해석해야 하므로 (매핑타입 정보는 디스패쳐서블릿이 쓰고, 컨트롤러 매핑정보는 핸들러매핑이 사용하므로)
	public void setRoot(JsonObject root);   
	
	//각 핸들러 매핑하고 하고싶은 초기화 작업에 사용할 메서드
	public void initialize();
	
	public Controller getController(String uri);

}
