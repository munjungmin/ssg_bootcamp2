package com.sinse.borderapp.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Mybatis의 설정 파일인 xml을 읽어들여, 필요한 객체를 만들기 
 */
public class MybatisConfig {
	SqlSessionFactory sqlSessionFactory;
	
	public MybatisConfig() {
		// 비록 패키지이더라도, 대상 파일이 java가 아니면, 일반 디렉토리 취급하자
		String resource = "com/sinse/borderapp/mybatis/mybatis-config.xml";
		InputStream inputStream;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//앞으로는 쿼리문 수행 시 PreparedStatement 및 JDBC를 직접 호출하는 일을 하지말자.. 
	//SqlSession을 이용하여 쿼리를 수행해야 한다.
	public SqlSession getSqlSession() {
		return null;
	}
}
