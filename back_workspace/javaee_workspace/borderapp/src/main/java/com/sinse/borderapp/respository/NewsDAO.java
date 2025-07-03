package com.sinse.borderapp.respository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sinse.borderapp.exception.NewsException;
import com.sinse.borderapp.model.News;
import com.sinse.borderapp.mybatis.MybatisConfig;

//DAO의 존재는 유지하되, DAO의 CRUD 메서드 안에 상투적인 JDBC 코드를 작성하지 않는다.
//Mybatis(SQL Mapper) 프레임웍과 Hibernate(ORM) 프레임웍을 이용하기 때문 
public class NewsDAO {
	
	MybatisConfig config = MybatisConfig.getInstance();
	
	public List selectAll() {
		SqlSession sqlSession = config.getSqlSession();
		List<News> list = sqlSession.selectList("News.selectAll");
		sqlSession.close();
		
		return list;
	}
	
	public News select(int news_id) {
		SqlSession sqlSession = config.getSqlSession();
		News news = sqlSession.selectOne("News.select", news_id);
		sqlSession.close();
		return news;
	}
	
	public void insert(News news) throws NewsException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.insert("News.insert", news);
		sqlSession.commit();   // mybatis가 mysql을 접속할때 기본 설정을 auto commit=false로 해놓기 때문에 커밋을 따로 해줘야 한다.
		sqlSession.close();
		if(result < 1) {
			throw new NewsException("등록 실패");
		}
	}
	
	public void update(News news) {
		
	}
	
	public void delete(int news_id) {
		
	}

}
