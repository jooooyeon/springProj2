<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(function(){
	//http://localhost/resp/goHome0101
	//아작났어요.. 피싸다타써
	//contentType,data : 보내는 타입 생략
	//dataType:json
	$.ajax({
		url:"/respRest/goHome0615",
		type:"get",
		dataType : "json",
		success:function(result){
			//result {"korea":"한국","germany":"독일","english":"미국"}
			console.log("result :" + JSON.stringify(result));
			
			//한국,독일,미국
			console.log(result.korea + "," + result.germany + ","+ result.english);
			
			
	         let str = "";
	         $.each(result,function(key,value){
	            console.log("key : " + key + ", value : " + value);
	            str +="<option value='"+key+"'>"+value+"</option>";
	         });
	         
	         $("#nation").append(str)


		}
		
		
		
	});
})
</script>

<h2>Home0101</h2>

<!-- 
요청 : /respRest/goHome0615
응답 : {"korea":"한국","germany":"독일","english":"미국"}
 -->
 <select name="nation" id="nation">
 	<option>선택하세요</option>
<!--  	<option value="korea">한국</option>
 	<option value="germany">독일</option>
 	<option value="english">미국</option> -->
 </select>