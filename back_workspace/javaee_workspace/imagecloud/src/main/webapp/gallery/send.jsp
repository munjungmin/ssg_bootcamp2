<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>  <!-- jquery 땡겨오기 -->
<script type="text/javascript">

	$(()=>{
		$("button").click(()=>{
			//$("form").attr("method", "post");
			//$("form").attr("action", "/upload/regist");
			//$("form").attr("enctype", "multipart/form-data");  //텍스트 뿐 아니라 바이너리 파일로 폼전송을 하려면 복합적 형식을 선언해야함: multipart/form-data
			
			//위의 html dom 속성을 일일이 명시하기보단, json을 이용해 코드를 줄이자 
			$("form").attr({
				"method": "post",
				"action" : "/upload/regist", 
				"enctype" : "multipart/form-data"
			});
			
			$("form").submit();
		});
	});
	
</script>
</head>
<body>
	<pre>
		<form>
			<input type="text" name="title"> <br>  <!-- 텍스트 데이터 -->
			<input type="file" name="photo"> <br>  <!-- 바이너리 데이터 -->
			<button type="button">업로드</button>
		</form>
	</pre>
</body>
</html>