package com.sinse.borderapp.respository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sinse.borderapp.exception.CommentException;
import com.sinse.borderapp.model.Comment;
import com.sinse.borderapp.mybatis.MybatisConfig;

public class CommentDAO {
	
	MybatisConfig config = MybatisConfig.getInstance();
	
	public List selectAll() {
		return null;
	}
	
	public Comment select(int comment_id) {
		return null;
	}
	
	//해당 뉴스기사에 소속된 댓글만 가져오기
	public List selectByNewsId(int news_id) {
		SqlSession sqlSession = config.getSqlSession();
		List list = sqlSession.selectList("Comment.selectByNewsId", news_id);
		sqlSession.close();
		return list;
	}
	
	public void insert(Comment comment) throws CommentException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.insert("Comment.insert", comment);
		sqlSession.commit();
		sqlSession.close();
		
		if(result < 1) {
			throw new CommentException("댓글 등록 실패");
		}
		
	}
	
	public void update(Comment comment) {
		
	}
	
	public void delete(int comment_id) {
		
	}

}
