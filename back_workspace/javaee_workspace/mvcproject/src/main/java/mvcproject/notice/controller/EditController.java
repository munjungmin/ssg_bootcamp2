package mvcproject.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.notice.domain.Notice;
import mvcproject.notice.repository.NoticeDAO;
import mvcproject.web.servlet.Controller;

public class EditController implements Controller{
	
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//3단계 일 시키기
		noticeDAO.update(notice);
		
		// 4단계 : content.jsp가 기다리고 있는 파라미터 형태인 notice (멤버 변수 각각이 reqeust에 저장됐지 notice 자체가 저장된건 아니어서)
		request.setAttribute("notice", notice);
		
	}
	
	@Override
	public boolean isForward() {
		// return false;  //redirect가 맞지만, 매핑 파일 자체가 변수(notice_id)가 올 수 없으므로 포워딩으로 처리한다.
		return true;
	}
	
	@Override
	public String getViewName() {
		return "/notice/edit/view";
	}
	
}
