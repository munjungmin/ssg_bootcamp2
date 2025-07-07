package com.sinse.hyberasync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sinse.hyberasync.exception.StoreException;
import com.sinse.hyberasync.model.Message;
import com.sinse.hyberasync.repository.StoreDAO;

public class StoreList extends HttpServlet{
	
	StoreDAO storeDAO = new StoreDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		Message msg = new Message();
		
		try {
			List list = storeDAO.selectAll();
			out.print(gson.toJson(list));  //  클라이언트가 받을 json 문자열 
		} catch (StoreException e) {
			e.printStackTrace();
			msg.setMsg(e.getMessage());
			msg.setResult("fail");
			out.print(gson.toJson(msg));
		}
		
	}

}
