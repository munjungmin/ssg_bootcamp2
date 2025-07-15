package springapp.cook;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springapp.config.AppConfig;

public class AppMain {
	public static void main(String[] args) {
		
		//개발자가 작성해 놓은 설정 파일의 역할을 대신하는 클래스를 이용하여
		// 프로그램 가동과 동시에 인스턴스를 싱글톤으로 생성 및 보관해주는 객체인 
		// ApplicationContext를 생성해보자
		// ApplicationContext는 인스턴스들을 보관 및 관리해준다고 하여 스프링 컨테이너라고도 함 
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);  //어노테이션 방식의 설정 파일을 읽어들인다
		
		// 이 시점에 이미, 모든 인스턴스가 메모리에 올라왔고, ApplicationContext가 보유중 
		// 이제 직접 만들지 말라고 달라고 하자
		Cook cook = context.getBean(Cook.class);
		cook.makeFood();
		

	}
}
