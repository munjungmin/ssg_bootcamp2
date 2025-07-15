package springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springapp.cook.Cook;
import springapp.cook.Fripan;
import springapp.cook.Induction;

/**
 * 전통적인 스프링의 개발 방식에서는 주로 XML이 사용되었으나, spring 2.5 버전부터 java 클래스에서도 
 * 설정이 가능한 즉 어노테이션 기반의 설정 방식을 지원하기 시작하여, 현재 스프링부트에 이르러서는 주로
 * java 기반 설정이 대세!
 */

@Configuration
public class AppConfig {
	
	// 클래스 중 스프링의 관리대상이 되는 클래스를 전통적으로 빈이라고 한다.
	@Bean
	public Fripan fripan() {
		return new Fripan();	//로직이 아니라 xml 설정파일을 java로 대체한거라서 new를 해도 된다.
	}
	
	@Bean
	public Induction induction() {
		return new Induction();
	}
	
	//요리사 빈을 생성자 주입으로 위빙(weaving 엮는다)
	@Bean
	public Cook cook() {
		return new Cook(fripan());   //생성자 주입
	}
}
