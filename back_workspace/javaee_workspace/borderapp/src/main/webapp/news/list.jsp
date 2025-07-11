<%@page import="com.sinse.borderapp.model.News"%>
<%@page import="java.util.List"%>
<%@page import="com.sinse.mvcapp.respository.NewsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%! NewsDAO newsDAO = new NewsDAO(); %>
<%

	List<News> newsList = newsDAO.selectAll();

	int totalRecord = newsList.size();   //총 레코드 수
	int pageSize = 10;      // 한 페이지당 보여질 레코드 수
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);
	int blockSize = 10;     //한 블럭당 보여질 페이지 수
	int currentPage = 1;  // 현재 유저가 보고있는 페이지
	
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int lastPage = (int)Math.ceil((float)currentPage/blockSize) * blockSize;
	int firstPage = lastPage - (blockSize - 1);	
	
	int curPos = (currentPage - 1)* pageSize; //페이지당 커서의 위치 (List 내에서 몇번째 index부터 보여질지)
	int num = totalRecord - curPos; //페이지당 시작 번호
	
%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="/inc/head.jsp" %>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<style>
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

tr:nth-child(even) {
	background-color: #f2f2f2;
}
a {
	text-decoration: none
}

.pageNum {
	font-size:20px;
	color: red;
}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(()=>{
		$("button").click(()=>{
			location.href = "/news/write.jsp";	
		});
	});
</script>
</head>
<body>

	<h2>Zebra Striped Table</h2>
	<p>For zebra-striped tables, use the nth-child() selector and add a
		background-color to all even (or odd) table rows:</p>

	<table>
		<tr>
			<th>No</th>
			<th>기사제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<% for(int i = 1; i <= pageSize; i++){ 
			if(num < 1) break;
			News news = newsList.get(curPos++);
		%>
		<tr>
			<td><%=num--%></td>
			<td><a href="/news/content.jsp?news_id=<%=news.getNews_id()%>"><%=news.getTitle() %></a><%=news.getCommentList.size() %></td>
			<td><%=news.getWriter() %></td>
			<td><%=news.getRegdate() %></td>
			<td><%=news.getContent() %></td>
			<td><%=news.getHit() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5">
			<%if(firstPage > 1) { %>
				<a href="/news/list.jsp?currentPage=<%=firstPage-1%>"><</a>
			<%}%>
			<%for(int i = firstPage; i <= lastPage; i++) {%>
				<%if(i > totalPage) break; %>
				<a <%if(i == currentPage) {%>class="pageNum"<%} %> href="/news/list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
			<%} %>
			<%if(lastPage < totalPage){ %>
				<a href="/news/list.jsp?currentPage=<%=lastPage+1%>">></a>
			<%} %>
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				<button>글쓰기</button>
			</td>
		</tr>
	</table>

</body>
</html>
