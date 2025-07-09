package com.sinse.mvcapp.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinse.mvcapp.controller.Controller;
import com.sinse.mvcapp.model.Notice;
import com.sinse.mvcapp.respository.NoticeDAO;

public class DetailController implements Controller{
	
	NoticeDAO noticeDAO = new NoticeDAO();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String notice_id = request.getParameter("notice_id");
		Notice notice = noticeDAO.select(Integer.parseInt(notice_id));
		
		HttpSession session = request.getSession();
		session.setAttribute("notice", notice);		
	}

	@Override
	public String getViewPage() {
		return "/notice/detail/view";
	}

}
