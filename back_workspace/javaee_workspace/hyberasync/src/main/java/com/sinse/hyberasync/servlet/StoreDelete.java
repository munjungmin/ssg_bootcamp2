package com.sinse.hyberasync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sinse.hyberasync.exception.StoreException;
import com.sinse.hyberasync.model.Message;
import com.sinse.hyberasync.model.Store;
import com.sinse.hyberasync.repository.StoreDAO;

public class StoreDelete extends HttpServlet{
	
	StoreDAO storeDAO = new StoreDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String store_id = request.getParameter("store_id");
		
		Store store = new Store();
		store.setStore_id(Integer.parseInt(store_id));
		
		//응답정보 만들기 
		Message message = new Message();
		Gson gson=new Gson();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		try {
			storeDAO.delete(store);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);//204
			message.setResult("success");
			message.setMsg("삭제 성공");
			
		} catch (StoreException e) {
			e.printStackTrace();
			message.setResult("fail");
			message.setMsg(e.getMessage());
		}
		
		out.print(gson.toJson(message));
		
	}
}
