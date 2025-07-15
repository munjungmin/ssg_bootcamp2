package springapp.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration  //XML을 대신하여, 스프링 컨테이너에게 빈을 관리하도록 부탁하는 설정 파일
@EnableAspectJAutoProxy  //AOP 기능을 켜주는 설정
@ComponentScan("springapp.school")  //스프링이 해당 패키지 내에서 자동으로 컴포넌트를 검색하고 빈으로 등록하게 해주는 설정.
public class AppConfig {
	@Bean	
	public Bell bell() {   //<bean class="~~.Bell" id="bell"></bean>과 동일
		return new Bell();
	}
	
	@Bean
	public Student student() {	//<bean class="~~.Student" id="student"></bean>
		return new Student();
	}
	
}
 