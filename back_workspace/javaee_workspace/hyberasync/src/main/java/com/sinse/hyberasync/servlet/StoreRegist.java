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
import com.sinse.hyberasync.model.FoodType;
import com.sinse.hyberasync.model.Message;
import com.sinse.hyberasync.model.Store;
import com.sinse.hyberasync.repository.StoreDAO;

// 맛집 등록 요청을 처리하는 서블릿
public class StoreRegist extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	StoreDAO storeDAO = new StoreDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String food_type_id = request.getParameter("food_type_id");
		String store_name = request.getParameter("store_name");
		String tel = request.getParameter("tel");
		
		logger.debug("food_type_id="+food_type_id);
		logger.debug("store_name=" + store_name);
		logger.debug("tel="+tel);
		
		Store store = new Store();
		store.setStore_name(store_name);
		store.setTel(tel);
		
		FoodType foodType = new FoodType();
		foodType.setFood_type_id(Integer.parseInt(food_type_id));
		store.setFoodType(foodType);
		
		// 응답정보를 html이 아닌 json으로 생성하여 보내자 
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Message msg = new Message();
		Gson gson = new Gson();
		
		try {
			storeDAO.insert(store);
			//Http Status Code : 서버가 클라이언트에게 응답 시 보내는 상태 코드 (성공, 실패 ...)
			//IETF(Internet Engineering Task Force) - 인터넷 표준 프로토콜을 정의하는 국제 조직
			response.setStatus(HttpServletResponse.SC_CREATED);
			
		} catch (StoreException e) {
			e.printStackTrace();
			msg.setResult("fail");
			msg.setMsg(e.getMessage());
			out.print(gson.toJson(msg));  //메시지가 json 문자열로 변환되어 전송
		}
	}

}
