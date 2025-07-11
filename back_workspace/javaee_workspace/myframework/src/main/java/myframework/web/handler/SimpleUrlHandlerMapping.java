package myframework.web.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import myframework.web.servlet.Controller;

public class SimpleUrlHandlerMapping implements HandlerMapping{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	JsonObject root;  //DispatcheServlet이 생성한 json 매핑 파일을 해석한(parsing) 결과 객체
	
	//하위 컨트롤러들을 key-value의 쌍으로 보관해놓자 
	//그래야 요청이 들어올 때, 해당 요청에 동작할 하위 컨트롤러를 DispatcherServlet에게 반환할 것이기 때문
	//Map<url, 하위컨트롤러>
	Map<String, Controller> controllerMap = new HashMap<>(); 
	
	@Override
	public void setRoot(JsonObject root) {
		this.root = root;
		logger.debug("DispatcherServlet으로부터 넘겨받은 root=" + root);
	}

	@Override
	public void initialize() {
		//root를 이용하여 json의 controllerMappings 검색
		JsonObject controllerMappings = root.getAsJsonObject("controllerMappings");
		logger.debug("controllerMappings=" + controllerMappings);
		
		//반복문으로 객체의 모든 키값에 매핑된 클래스명을 대상으로 인스턴스화 작업을 시도하고 controllerMap에 수집해놓기
//		for(Map.Entry<K, V> : controllerMappings.entrySet()) {
//			
//		}
		Set set = controllerMappings.keySet();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			String uri = it.next(); 
			logger.debug("controllerMappings 요소의 key=" + uri);
			
			String controllerName = controllerMappings.get(uri).getAsString();
			logger.debug("controller이름=" + controllerName);
			
					
			try {
				Controller controller = (Controller)Class.forName(controllerName).newInstance();
				logger.debug("controller=" + controller);
				
				controllerMap.put(uri, controller);
				logger.debug("size=" + controllerMap.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public Controller getController(String uri) {
		
		logger.debug("hi");
		
		return controllerMap.get(uri);
	}
	
	

}
