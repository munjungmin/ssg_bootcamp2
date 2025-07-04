package com.sinse.memberapp.repository;

import org.apache.ibatis.session.SqlSession;

import com.sinse.memberapp.exception.MemberNotFoundException;
import com.sinse.memberapp.model.Member;
import com.sinse.memberapp.mybatis.MybatisConfig;

public class MemberDAO {
	MybatisConfig config = MybatisConfig.getInstance();
	
	public Member login(Member member) throws MemberNotFoundException{
		SqlSession sqlSession = config.getSqlSession();
		Member obj = sqlSession.selectOne("Member.login", member);
		sqlSession.close();
		
		if(obj == null) {
			throw new MemberNotFoundException("회원 정보를 찾을 수 없습니다!");
		}
		
		return obj;		
	}
}
