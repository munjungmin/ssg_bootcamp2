package com.sinse.borderapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.borderapp.exception.NoticeException;
import com.sinse.borderapp.model.Notice;
import com.sinse.borderapp.respository.NoticeDAO;

//클라이언트인 브라우저가 폼의 파라미터들을 POST로 요청하고 있음, 따라서 doPost로 처리 
public class UpdateServlet extends HttpServlet{
	
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기 전에 파라미터의 인코딩부터 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	
		
		//파라미터 받기
		String notice_id = request.getParameter("notice_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		PrintWriter out = response.getWriter();
		out.print("<script>");
		
		try {
			noticeDAO.update(notice);  // 수정 수행
			out.print("alert('수정 성공');");
			out.print("location.href='/notice/content.jsp?notice_id="+ notice_id + "';");
		} catch (NoticeException e) {
			e.printStackTrace();
			out.print("alert('" + e.getMessage() + "')");
		}
		
		out.print("</script>");
	}

}
