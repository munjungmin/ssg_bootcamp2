package myframework.staff.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import myframework.exception.BioException;
import myframework.mybatis.MybatisConfig;
import myframework.staff.model.domain.Bio;

public class BioDAO {
	
	public Bio select() {
		
		return null;
	}
	public List selectAll() {
		
		return null;
	}
	public void insert(SqlSession sqlSession, Bio bio) throws BioException {
		int result = sqlSession.insert("Bio.insert", bio);
		if(result < 1) {
			throw new BioException("Staff의 신체정보 등록 실패");
		}
	}
	
	public void update(Bio bio) {
		
	}
	public void delete(int bio_id) {
		
	}
}
