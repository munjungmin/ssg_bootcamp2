package mall.spring.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"mall.shop.controller"})

// 빈 중에서도 특별한 역할을 하는 애들 = 컴포넌트
// 스프링 빈이 올라오는건 톰캣 가동하고 최초 요청이 들어오고 디스패처서블릿 init()?에서 설정파일 분석할때 올라옴
// 설정 파일 분석은 DefaultAnnotationHandlerMapping이 함 
/*
 * userWebConfig 만들면, ApplicationContext가 생기고 그 안에 DAO같은 모델 빈도 또 생김 => 모델은 재사용 가능한데 빈이 유저, 어드민에 각각 생겨버림 
 * -> 모델 파트인건 다 날려라 
 *
 */

public class UserWebConfig {
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}
