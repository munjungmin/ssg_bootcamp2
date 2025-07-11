package myframework.shop.staff.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import myframework.exception.BioException;
import myframework.exception.StaffException;
import myframework.mybatis.MybatisConfig;
import myframework.staff.model.domain.Bio;
import myframework.staff.model.domain.Staff;
import myframework.staff.model.repository.BioDAO;
import myframework.staff.model.repository.StaffDAO;
import myframework.staff.model.service.StaffService;
import myframework.web.servlet.Controller;

//사원 등록 요청을 처리하는 하위 컨트롤러 (3, 4단계 업무 수행)
public class RegistController implements Controller{
	
	StaffService staffService = new StaffService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		String name = request.getParameter("name");
		String sal = request.getParameter("sal");
		String email = request.getParameter("email");
		
		String blood = request.getParameter("blood");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		
		Staff staff = new Staff();  // 이 staff 인스턴스가 mybatis에게까지 전달되어 id가 채워짐 
		staff.setName(name);
		staff.setSal(Integer.parseInt(sal));
		staff.setEmail(email);
		
		Bio bio = new Bio();
		bio.setBlood(blood);
		bio.setHeight(Integer.parseInt(height));
		bio.setWeight(Integer.parseInt(weight));
		bio.setStaff(staff);
		
		staffService.regist(bio);
	}

	@Override
	public String getViewName() {
		return "/shop/staff/regist/view";
	}

	@Override
	public boolean isForward() {
		return false;
	}
	
}
