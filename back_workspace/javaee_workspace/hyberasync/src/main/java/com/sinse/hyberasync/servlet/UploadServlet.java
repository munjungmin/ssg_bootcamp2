package com.sinse.hyberasync.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadServlet extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	String uploadPath; // 이 서블릿이 작동하는 프로젝트 hyberasync의 realpath!
	
	//서블릿의 인스턴스가 생성될 때, 서블릿의 초기화를 담당하는 메서드인 init()을 이용하면, 개발자는 이 서블릿에 원하는 정보를 전달할 수 잇다.
	// 필터와 동일 
	@Override
	public void init(ServletConfig config) throws ServletException{
		uploadPath = config.getServletContext().getRealPath(config.getInitParameter("uploadPath"));
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("저장할 실제 경로는 "+uploadPath);
		//logger.debug(request.getParameter("title"));
		//logger.debug(request.getParameter("pic"));
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		
		//클라이언트의 요청을 분석 (파싱)
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			logger.debug("전송된 컴포넌트 수는 =" + list.size());
			
			for(int i=0;i<list.size();i++) {  //<input type="text">, <input type="file">
				
				FileItem item=list.get(i);
				logger.info("for inner ");
				
				
				if(item.isFormField()) {  //텍스트 컴포넌트의 파라미터라면.. 
					logger.debug(item.getString("utf-8"));
				}else { // 파일 컴포넌트의 파라미터라면..
					logger.debug("파일명은 " + item.getName());
					item.write(new File(uploadPath, item.getName()));
					
					//세션은 웹컨테이너가 생성하므로, 개발자가 new 할 수 없고 이미 생성된 세션을 얻어오는것만 가능하다.
					HttpSession session = request.getSession();
					session.setAttribute("img", item.getName());
				}
			}
			
			// 클라이언트의 브라우저로 하여금, 이미지를 볼 수 있는 jsp를 재요청하게 만들자
			response.sendRedirect("/gallery/result.jsp"); //지정된 url로 클라이언트의 브라우저가 재요청하게 함
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
