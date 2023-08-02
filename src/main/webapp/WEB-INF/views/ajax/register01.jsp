<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(function(){
	//jsp가 실행후 바로 실행됨
	//요청URI : /ajax/register01/gaeddongi
	//요청방식 : get
	$.get("/ajax/register01/gaeddongi", function(result){//get방식이라 get이붙음
		//result : success
		console.log("result : " + result)
	}); 
	
	$("#registerBtn02").on("click",function(){
		let userId = $("input[name='userId']").val();
		let password = $("input[name='password']").val();
		
		console.log("userId : " + userId + ", password : " + password);
		
		//JSON 오브젝트
		let userObject = {'userId':userId,'password':password};
		console.log("userObject : " + JSON.stringify(userObject));
		//아작났어유..피씨다타써
		//요청 URI : /ajax/register02/board/3
		//요청파라미터 : {"userId :"gaeddongi", "password":"java"}
		//요청방식 : post
		$.ajax({
			url:"/ajax/register02/board/3",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(userObject),
			type:"post",
			success:function(result){
				//result : success
				console.log("result : " + result);
			}
		});
	});
	$("#registerBtn03").on("click",function(){
		let userObjectArray = [];
		//name에 맞추어서 들어감
		let userObject = {"userId":document.frm01.userId.value,
						  "password":document.frm01.password.value};
		//배열에 json 오브젝트를 넣자
		userObjectArray.push(userObject);
		
		userObject = {"userId":document.frm02.userId.value,
				"password":document.frm01.password.value};
		
		userObjectArray.push(userObject);
		//모든 Javascript Object는 JSON으로 변환가능
		//userObjectArray : [{"userId":"gaeddongi","password":"java"},{"userId":"Harry","password":"java"}]
		console.log("userObjectArray : "+JSON.stringify(userObjectArray));
		
		$.ajax({
			url:"/ajax/register03",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(userObjectArray),
			type:"post",
			success:function(result){
				//result : success
				console.log("result : " + result);
			}
		});//ajax
		
	});//registerBtn03
});//큰function
</script>
<form name="frm01" id="frm01">
	<p><input type="text" name="userId" value="gaeddongi" /> </p>
	<p><input type="password" name="password" value="java" /> </p>
	<p><button type="button" id="registerBtn02">등록</button> </p>
</form>
<hr />
<form name="frm02" id="frm02">
	<p><input type="text" name="userId" value="Harry" /> </p>
	<p><input type="password" name="password" value="1234" /> </p>
	<p><button type="button" id="registerBtn03">다중등록</button> </p>
</form>
<hr />












