package com.sinse.hyberasync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sinse.hyberasync.exception.StoreException;
import com.sinse.hyberasync.model.Message;
import com.sinse.hyberasync.model.Store;
import com.sinse.hyberasync.repository.StoreDAO;

public class StoreDetail extends HttpServlet{
	
	Logger logger=LoggerFactory.getLogger(getClass());
	StoreDAO storeDAO = new StoreDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String store_id=request.getParameter("store_id");
		
		response.setContentType("application/json");
		
		logger.debug("store_id"+store_id);
		Gson gson=new Gson();
		PrintWriter out = response.getWriter();
		Message message = new Message();
		
		try {
			Store store = storeDAO.select(Integer.parseInt(store_id));
			out.print(gson.toJson(store));//문자열 로 변환하여 전송하기 위함
		} catch (StoreException e) {
			e.printStackTrace();
			message.setResult("fail");
			message.setMsg(e.getMessage());
			out.print( gson.toJson(message));
		}
		
	}
}