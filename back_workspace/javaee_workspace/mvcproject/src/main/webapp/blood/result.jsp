<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	당신이 선택한 혈액형에 대한 결과 <br>
	<%out.print(request.getAttribute("msg"));%>
</body>
</html>