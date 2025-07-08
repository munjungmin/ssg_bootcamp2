<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//서블릿과는 달리 jsp이므로, session을 얻어올 때 내장객체로 접근
		// 세션은 java.util.Map을 계승하였으므로,key와 value로 데이터를 관리
		// 따라서 세션에 넣을 수 있는 데이터의 종류는 한계가 없다. 
		// 그래서 반환형이 Object
		String img = (String)session.getAttribute("img");
		out.print(img);
	%>
	<img src="/data/<%=img %>" style="width:300px; height:300px">
</body>
</html>