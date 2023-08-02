<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery-3.6.4.min.js"></script>
<title>테스트</title>
<script type="text/javascript">
//document의 요소들이 모두 로딩된 후 실행
$(function(){
	$("button").on("click", function(){
		//콜백함수
		$("#frm").submit();		
	});
});
</script>
</head>
<body>
<h1>테스트</h1>
<!-- Bookcontroller에
1)
요청 URI : /test
요청방식 : get
test.jsp를 포워딩
2)
요청URI : /test
요청방식 : post
console에 {username:a001, password:1234}를 받아서 sysout으로 출력
test.jsp를 포워딩
 -->
<form id="frm" action="/test" method="post">
	<p>아이디 : <input type="text" name="username" required placeholder="아이디를 입력해 주세요"/> </p>
	<p><input type="password" name="password" required placeholder="비밀번호를 입력해 주세요"/> </p>
	<p><button type="button">로그인</button> </p>
</form>
</body>
</html>