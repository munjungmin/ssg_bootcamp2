package mall.model.notice;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Notice;
import mall.exception.NoticeException;

@Slf4j
@Repository
public class HibernateNoticeDAO implements NoticeDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List selectAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Notice", Notice.class);
		return null;
	}

	@Override
	public Notice select(int notice_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Notice notice) throws NoticeException{
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(notice);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("등록 실패", e.getMessage(), e);  //for 개발자
			throw new NoticeException("등록 실패", e);  // for 유저
		}
	}

	@Override
	public void update(Notice notice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int notice_id) {
		// TODO Auto-generated method stub
		
	}

}
