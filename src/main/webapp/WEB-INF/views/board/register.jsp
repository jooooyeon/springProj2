<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>

<h2>Board Register : 로그인한 회원만 접근 가능</h2>
<!-- method="get"은 기본으로 생략 가능
HTTP 메소드 : get
	@RequestMapping(value = "/register", method=RequestMethod.GET)에 매핑 -->
<form action="/board/register">
	<input type="submit" value="register(GET)" />
</form>
<hr />
<!-- 
HTTP 메소드 : post
	@RequestMapping(value = "/register", method=RequestMethod.POST)에 매핑 -->
<form action="/board/register" method="post">
	<input type="submit" value="register(POST)" />
</form>

<hr />
<!-- 요청 URI : /board/post
			  /board.post?register
	요청 방식 : post
	params : register
	//value가 없음(버튼이라?)
	 -->
<form action="/board/post" method="post">
	<button type="submit" name="register">Register(Params)</button>
</form>


<hr />
<!-- 
	요청 URI : /board/get
	요청방식 : get
	params : modify -->
<form action="/board/get">
	<button type="submit" name="modify">Modify(Params)</button>
</form>	

<hr/>
<!-- 
	요청 URI : /board/get
	요청방식(HTTP메소드) : get
	params : list -->

<a href="/board/get?list">목록</a>
<hr />

<a href="/board/get?read">상세</a>
<hr />

<p><input type="text" id="boardNo" name="boardNo" required /></p>
<p><input type="text" id="title" name="title" required /></p>
<p>
	<textarea id="contents" name="contents" rows="3" cols="5"></textarea>
</p>
<p><input type="text" id="writer" name="writer" /></p>
<button type="button" id="putBtn">Headers매핑</button>
<button type="button" id="getBtn">Accept매핑</button>
<script type="text/javascript">
$(function(){
	
	$("#getBtn").on("click", function(){
		let boardNo = $("#boardNo").val();
		
		console.log("boardNo : " + boardNo);
		//요청 URI : /board/1
		$.get("/board/" + boardNo, function(data){
			//JSON.stringify(json오브젝트) => string으로 json데이터를 변환
			console.log("data : " + JSON.stingify(data));
		})
	});
	
	
	$("#putBtn").on("click", function(){
		
	//json Object
	let boardObject ={
			boardNo:$("#boardNo").val(),
			title:$("#title").val(),
			contents:$("#contents").val(),
			writer:$("#writer").val()
		}
		//boardObject : {"boardNo":"4","title":"4","content":"4","writer":"4"}
		console.log("boardObject : " + JSON.stringify(boardObject));
		
		//아작났어유.. ㅜㅜ피씨다타써
		//contentType : 보내는 데이터의 타입
		//dataType : 응답 데이터 타입(헤더를 통해서 옴)
		$.ajax({
			url:"/board/"+ $("#boardNo").val(),
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(boardObject),
			type:"post",
			success:function(result){
				//result : SUCCESS
				console.log("result : " + result);
			}
		});
		
	})
});
</script>








