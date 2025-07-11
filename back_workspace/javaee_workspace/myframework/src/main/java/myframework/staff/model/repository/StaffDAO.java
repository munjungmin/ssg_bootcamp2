package myframework.staff.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import myframework.exception.StaffException;
import myframework.mybatis.MybatisConfig;
import myframework.staff.model.domain.Staff;

public class StaffDAO {
	
	
	public Staff select(int staff_id) {
		return null;
	}
	public List selectAll() {
		return null;
	}
	public void insert(SqlSession sqlSession, Staff staff) throws StaffException{
		int result = sqlSession.insert("Staff.insert", staff);
		if(result < 1) {
			throw new StaffException("Staff 등록 실패");
		}
	}
	public void update(Staff staff) {
		
	}
	public void delete(int staff_id) {
		
	}

}
