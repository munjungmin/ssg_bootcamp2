<%@page import="java.util.List"%>
<%@page import="notice.model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html; charset=utf-8" %>  <!--무조건 적어야함-->
<%!
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/shop";
	String user = "shop";
	String pwd = "1234";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void connect(){
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
%>
<%
	//서블릿의 service() 메서드 영역
	connect();
	StringBuffer sql = new StringBuffer();
	sql.append("select * from notice where notice_id = ?");  // 유저가 선택한 게시물의 pk값 
	out.print(sql.toString());
%>


<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		<style>
		body {font-family: Arial, Helvetica, sans-serif;}
		* {box-sizing: border-box;}
		
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
	
		<!-- summernote link -->
		<!-- include libraries(jQuery, bootstrap) -->
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		
		<!-- include summernote css/js -->
		<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
		
		<script type="text/javascript">
		
			<!--addEventListener("load", function(){}) 과 동일 -->
			$(document).ready(function(){
				$("#summernote").summernote();
			})
			
		</script>
	
	
	</head>
	<body>
		<h3>Contact Form</h3>
		
		<div class="container">
		  <form method="post" action="/notice/write" >
		    <label for="fname">제목</label>
		    <input type="text" id="fname" name="title" placeholder="Your name..">
		
		    <label for="lname">작성자</label>
		    <input type="text" id="lname" name="writer" placeholder="Your last name..">

		
		    <label for="subject">내용</label>
		    <textarea id="summernote" name="content" placeholder="Write something.." style="height:200px"></textarea>

		    <input type="button" value="수정">
		    <input type="button" value="삭제">
		    <input type="button" value="목록">
		  </form>
		</div>
	</body>
</html>

<%
	con.close();

%>
