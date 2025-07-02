<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% int totalRecord = 26;  //총 레코드 수 
	int pageSize = 10;    //한 페이지당 보여질 레코드 수
	int totalPage = (int)Math.ceil((float)totalRecord / pageSize);   // 총 페이지 수
	int blockSize = 10;  //블럭당 보여질 페이지 수
	int currentPage = 1; //현재 유저가 보고있는 페이지 
	
	if(request.getParameter("currentPage") != null) {  //페이지 파라미터가 넘어올때만 (최초에 게시판 목록을 볼땐 첫페이지니까 파라미터가 없어도 보여야됨)
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int lastPage =  (int)Math.ceil((float)currentPage / blockSize) * blockSize;   //블럭당 마지막 페이지
	int firstPage = lastPage - (blockSize - 1);  //블럭당 시작 페이지  // currentPage - (currentPage -1) % blockSize
%>  
<%= "totalRecord = " + totalRecord + "<br>" %>
<%= "pageSize = " + pageSize + "<br>" %>
<%= "totalPage = " + totalPage + "<br>" %>
<%= "blockSize = " + blockSize + "<br>" %>
<%= "currentPage = " + currentPage + "<br>" %> 
<%= "firstPage = " + firstPage + "<br>" %> 
<%= "lastPage = " + lastPage + "<br>" %> 
<!DOCTYPE html>
<html>
<head>
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

.pageNum{
	font-size: 27px;
	font-weight: bold;
	color: red;
}
</style>
</head>
<body>

	<h2>Zebra Striped Table</h2>
	<p>For zebra-striped tables, use the nth-child() selector and add a
		background-color to all even (or odd) table rows:</p>

	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Points</th>
		</tr>
		<%for(int i = 0; i < pageSize; i++){ %>
		<tr>
			<td>Jill</td>
			<td>Smith</td>
			<td>50</td>
		</tr>
		<%} %>
		
		<tr>
			
			<td colspan="3" align="center">
			<%if(firstPage > 1){ %>
				<a href="/board/list.jsp?currentPage=" <%=firstPage -1%>">◀</a>
			<%} else { %>
				<a href ="javascript:alert('최신 페이지입니다.')">◀</a>
			<%} %>
			
			<%for(int i = firstPage; i <= lastPage; i++){ %>
			<% if(i == totalPage + 1){ 
				break;
			}%> 
			<a <%if(i == currentPage){ %>class ="pageNum" <%} %> href="/board/list.jsp?currentPage=<%=i%>">[<%=i%>]</a>
			<%}%>
			
			<%if(lastPage < totalPage){ %>
			<a href="/board/list.jsp?currentPage=<%=lastPage+1%>">▶</a>
			<%} else { %>
				<a href ="javascript:alert('마지막 페이지입니다.')">▶</a>
			<%} %>
			</td>
		</tr>
	</table>

</body>
</html>
