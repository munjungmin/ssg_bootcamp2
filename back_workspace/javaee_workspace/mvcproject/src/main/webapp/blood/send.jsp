<%@page import="mvcproject.blood.model.BloodManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% 
 	/*
 		모델1 방식: jsp 또는 서블릿이 MVC 중 VC를 담당하는 개발방식 
 		모델2 방식: MVC 패턴을 JavaEE 기술로 구현해놓은 모델 
 				Model: .java (POJO : plain old java object) 
 								참고) POJO 유래: 초창기 java가 세상에 이름을 알리기시작하자, 엔터프라이즈 시장을 노림... 컴포넌트 기반의 Java 기술 -> 기업용 자바 (javaEE)
 				.. 어쩌구
 	*/
 	//JSP 파일 하나로 모든 것을 처리하는 방법 
 	String msg = null;
	out.print(msg);
 	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/blood.do" method="get">
		<select name="blood">
			<option value="">혈액형 선택</option>
			<option value="A">A형</option>
			<option value="B">B형</option>
			<option value="O">O형</option>
			<option value="AB">AB형</option>
		</select>
		<button type="submit">전송</button>
	</form>
</body>
</html>