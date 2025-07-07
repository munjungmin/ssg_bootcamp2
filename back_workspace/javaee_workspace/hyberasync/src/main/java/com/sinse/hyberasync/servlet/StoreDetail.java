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
	
	Logger logger = LoggerFactory.getLogger(getClass());
	StoreDAO storeDAO = new StoreDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String store_id = request.getParameter("store_id");
		
		response.setContentType("application/json");
		logger.debug("store_id=" + store_id);
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		Message msg = new Message();
		
		try {
			Store store = storeDAO.select(Integer.parseInt(store_id));
			logger.debug("selected store=" + store.getStore_name());
			out.print(gson.toJson(store));
		} catch (StoreException e) {
			e.printStackTrace();
			msg.setResult("fail");
			msg.setMsg(e.getMessage());
			out.print(gson.toJson(msg));
		}
		
	}
	

}
