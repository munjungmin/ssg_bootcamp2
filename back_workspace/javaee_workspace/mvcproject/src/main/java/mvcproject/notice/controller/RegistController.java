package mvcproject.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.notice.domain.Notice;
import mvcproject.notice.repository.NoticeDAO;
import mvcproject.web.servlet.Controller;

public class RegistController implements Controller{
	
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		noticeDAO.insert(notice);  // 3단계 일시키기 
		//4단계 생략 
	}
	
	@Override
	public boolean isForward() {
		return false;  //포워딩 안하고 브라우저로 하여금 새로 들어오게 함 
	}
	
	@Override
	public String getViewName() {
		return "/notice/regist/view";  // 새로 등록한 글까지 보여줘야 하니까. db 거쳐야 하므로??
	}
	

}
