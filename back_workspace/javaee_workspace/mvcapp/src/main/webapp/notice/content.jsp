<%@page import="com.sinse.mvcapp.model.Notice"%>
<%@ page contentType="text/html; charset=utf-8" %>
<% 
	Notice notice = (Notice)session.getAttribute("notice");
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">

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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>

<script type="text/javascript">
	$(()=>{
		//서머노트 연동
		$("#content").summernote({
			height: 250			
		}); //서머노트 연동
		
		$("#content").summernote('code', "<%=notice.getContent()%>");
		
		//버튼에 이벤트 연결 
		$("input[type='button']").click(()=>{
			$("form").attr({
				action: "/notice/regist",
				method: "POST", //머리에 데이터를 실어 나르게 됨, 따라서 편지 봉투에 나르는 격,문제 1) 노출   문제 2) 용량 제한으로 내용이 짤린다.
			});
			
			$("form").submit();  //전송
		})
		
		//0번 수정
		$("#bt_edit").click(()=>{
			if(confirm("수정하시겠어요?")){
				//서버로 입력폼의 내용을 모두 가져가야 하므로, Post 방식으로 보내야함 
				$("form").attr({
					method: "POST", 
					action: "/notice/update"
				}); 
				
				$("form").submit();
			}
		})
		//1번 삭제 
		$("#bt_del").click(()=>{
			if(confirm("삭제하시겠어요?")){
				//Get 방식 요청 (링크)
				location.href = "/notice/del?notice_id=<%=notice_id%>";
			}
		})
		//2번 목록
		$("#bt_list").click(()=>{
			location.href = "/notice/list.jsp";
		});
		
	});  
	
	
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>
  <!--
    hidden은 html의 컴포넌트 역할을 수행하지만, 시각적으로 표현되지는 않음 
  	노출되지 않은 상태로 데이터를 전송할 때 사용 
  -->
  
    <input type="hidden" name="notice_id" value="<%=notice.getNotice_id()%>">
    
    <label for="fname">Title</label>
    <input type="text" name="title" value="<%=notice.getTitle()%>">

    <label >Writer</label>
    <input type="text" name="writer" value ="<%=notice.getWriter()%>">

    <label>Content</label>
    <textarea id="content" name="content" style="height:200px"></textarea>

    <input type="button" id="bt_edit" value="수정">
    <input type="button" id="bt_del" value="삭제">
    <input type="button" id="bt_list" value="목록">
  </form>
</div>

</body>
</html>
