<%@page import="java.util.List"%>
<%@page import="com.sinse.borderapp.model.Comment"%>
<%@page import="com.sinse.mvcapp.respository.CommentDAO"%>
<%@page import="com.sinse.borderapp.model.News"%>
<%@page import="com.sinse.mvcapp.respository.NewsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! NewsDAO newsDAO = new NewsDAO(); 
	CommentDAO commentDAO = new CommentDAO();
%>
<%
	//내장 객체
	String news_id = request.getParameter("news_id");
	out.print("넘겨받은 파라미터는 " + news_id);
	News news = newsDAO.select(Integer.parseInt(news_id));
	
	List<Comment> commentList = commentDAO.selectByNewsId(Integer.parseInt(news_id));
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">

<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}
input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<%@ include file="/inc/head_link.jsp" %>
<script type="text/javascript">
	$(()=>{
		//서머노트 연동
		$("#content").summernote({
			height: 250
		}); //서머노트 연동
		$("#content").summernote("code", "<%=news.getContent()%>"); //서머노트 연동
		
		
		$("#bt_news_edit").click(()=>{
			
		});
		$("#bt_news_del").click(()=>{
			
		});
		
		$("#bt_news_list").click(()=>{
			
		});
		$("#bt_comment_regist").click(()=>{
			$("#comment_form").attr({
				method: "POST",   //HTTP 프로토콜 통신에 사용되는 데이터 구성(Payload) body에 탑재됨 
				action: "/comment/regist" 
			});
			$("#comment_form").submit();
		});		
		
	});  
	
	
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>
    <label for="fname">First Name</label>
    <input type="text" id="fname" name="title" value="<%=news.getTitle()%>">

    <label for="lname">Last Name</label>
    <input type="text" id="lname" name="writer" value="<%=news.getWriter()%>">

    <label for="content">Content</label>
    <textarea id="content" name="content" placeholder="내용 입력" style="height:200px"></textarea>

    <input type="button" id="bt_news_edit" value="수정">
    <input type="button" id="bt_news_del" value="삭제">
    <input type="button" id="bt_news_list" value="목록">
  </form>
  	<div id="comment_header">
  		<form id="comment_form">
	  		<input type="text" style="width:75%" name="msg">
	  		<input type="text" style="width:25%" name="user">
	  		<input type="hidden" name="news_id" value="<%=news.getNews_id()%>">
	  		<input type="button" id="bt_comment_regist" value="등록">
  		</form>
  	</div>
  	<div id="comment_content">
  		<table>
  			<tr>
  				<th>댓글 제목</th>
  				<th>작성자</th>
  				<th>등록일</th>
  			</tr>
  			<%for(int i = 0; i < commentList.size(); i++){ 
  				Comment comment = commentList.get(i);
  			%>
  			<tr>
  				<td><%=comment.getMsg()%></td>
  				<td><%=comment.getUser()%></td>
  				<td><%=comment.getMsgdate()%></td>
  			</tr>
  			<%} %>
  		</table>
  	</div>
</div>

</body>
</html>
