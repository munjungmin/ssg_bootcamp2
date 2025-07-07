package com.sinse.hyberasync.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sinse.hyberasync.exception.FoodTypeException;
import com.sinse.hyberasync.hibernate.HibernateConfig;
import com.sinse.hyberasync.model.FoodType;

public class FoodTypeDAO {
	
	HibernateConfig config = HibernateConfig.getInstance();
	
	//모든 음식 카테고리 가져오기
	public List selectAll() throws FoodTypeException{
		Transaction tx = null; //이건 hibernate가 요구하기 때문에 hibernate꺼 import
		List list = null;
		
		/**
		 * java7부터 try~with~resource 문법이 지원
		 * 자원을 사용한 후 finally 영역에서 닫는 중복 코드를 단순화시키기 위함
		 * 즉 언어 차원에서 개발자가 해야 할 close() 호출을 자동으로 해준다.
		 * 주의) 모든 close() 메서드를 대상으로 하지 않고, Closeable 인터페이스를 구현한 객체를 대상으로 함  
		 * 개발자는 try~catch만 집중하면 된다. 
		 */
		try(Session session = config.getSession()){
			tx = session.beginTransaction();
			//ORM은 직접 테이블에 접근하지 않는다. 따라서 아래의 문장이 마치 table을 접근하는 것처럼 보이지만, from 절의 대상이 되는 객체는 테이블이 아니라 클래스이다. (from foodtype (X),   from FoodType (O))
			// ""안에는 where 조건도 주는 등, 개발자에게 자유도를 주는 것임
			TypedQuery<FoodType> query = session.createQuery("from FoodType", FoodType.class);
			list = query.getResultList();
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			if(tx != null) tx.rollback();
			throw new FoodTypeException("데이터 조회 실패", e);
		}
		return list;
	}
	
	

}

//hibernate는 select문이여도 commit
