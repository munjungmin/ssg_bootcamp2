<%@page import="com.sinse.memberapp.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 이 서블릿에 요청을 시도할 때, 웹컨테이너에서는 세션 객체를 생성하면서, 동시에 세션ID가 할당됨
	// 그리고 이 세션 아이디는 톰캣이 보내는 응답 정보에 쿠키형태로 존재하게 된다. 
	// 단 세션과 세션 아이디는 매 요청마다 만들어지는게 아니라, 클라이언트가 요청시 톰캣이 발급한 쿠키가 존재하지 않을때만 새로 만든다. 
	// 또한 요청 후 일정시간동안 아무런 재요청이 없을때는 사용하지 않는 것으로 간주하여 새롭게 세션 할당 
	//String sessionId = session.getId();

	//만일 이 페이지의 요청자가 로그인에 성공한 사람이라면, 세션에 member라는 이름으로 Member가 담겨있을 것임
	Member member = (Member)session.getAttribute("member");

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=member.getName() %>님 반갑습니다
</body>
</html>