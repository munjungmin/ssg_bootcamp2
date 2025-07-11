<%@page import="java.util.List"%>
<%@page import="notice.model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
	//클라이언트가 전송한 notice_id 값을, 요청 객체로부터 꺼내서 사용해보자
	//jsp에서는 필수적인 기능들을 이미 생성하여 변수명까지도 지정해버리는데, 이러한 객체를 내장객체라고 한다.
	request.getParameter("notice_id");
	
	//아래의 변수 out은 개발자가 현재 코드에서 선언한 적이 없음에도 불구하고 그 기능을 제대로 한다...
	//개발자가 정의하지 않아도 톰켓 컨테이너가 jsp내에서 기본적으로 사용할 수 있도록 미리 생성해놓고 개발에 사용될 수 있도록 지원하는 jsp의 객체들을 가리켜 내장 객체라고 한다. built-in object라 한다.
	//out은 문자기반의 출력 스트림이다..
	out.print("접속 객체는 " + con);
	
	//레코드 가져오기
	pstmt = con.prepareStatement("select * from notice order by notice_id desc");
	
	rs =pstmt.executeQuery();
	
	List<Notice> list = new ArrayList();
	
	while(rs.next()){
		Notice notice = new Notice();
		notice.setNotice_id(rs.getInt("notice_id"));
		notice.setTitle(rs.getString("title"));
		notice.setWriter(rs.getString("writer"));
		notice.setContent(rs.getString("content"));
		notice.setRegdate(rs.getString("regdate"));
		notice.setHit(rs.getInt("hit"));
		
		list.add(notice);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
  background-color: #F2F2F2;
}
a{
	text-decoration: none;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(()=>{
		$("button").click(()=>{
			location.href="/notice/write.html"
		});
	});
</script>
</head>
<body>
<h2>Zebra Striped Table</h2>
<p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table rows:</p>
<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  <%for(Notice notice : list) {%>
  <tr>
    <td>0</td>
    <td><a href="/notice/detail.jsp?notice_id=<%notice.getNotice_id()%>"><%= notice.getTitle() %></a></td>
    <td><%= notice.getWriter() %></td>
    <td><%= notice.getRegdate() %></td>
    <td><%= notice.getHit() %></td>
  </tr>
  <%} %>
  <tr>
  	<td colspan="5"><button type="button">글등록</button>
  </tr>
</table>
</body>
</html>
<%
	rs.close();
	pstmt.close();
	con.close();
%>





