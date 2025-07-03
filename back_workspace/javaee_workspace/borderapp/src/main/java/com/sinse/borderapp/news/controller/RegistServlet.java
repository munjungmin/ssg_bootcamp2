package com.sinse.borderapp.news.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.borderapp.exception.NewsException;
import com.sinse.borderapp.model.News;
import com.sinse.borderapp.respository.NewsDAO;

//뉴스 기사 등록 요청을 받는 서블릿
public class RegistServlet extends HttpServlet{
	
	NewsDAO newsDAO = new NewsDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //다국어 인코딩
		response.setContentType("text/html; charset=utf-8");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		//글쓰기 수행
		News news = new News();
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		PrintWriter out = response.getWriter();
		out.print("<script>");
		
		try {
			newsDAO.insert(news);
			out.print("location.href='/news/list.jsp';");
		} catch (NewsException e) {
			e.printStackTrace();
			out.print("alert('" + e.getMessage() + "');");
			out.print("history.back();");
		}
		out.print("</script>");
		
	}
}
