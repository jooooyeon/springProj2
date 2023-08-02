<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#inputFile").on("click",function(){
		let inputFile = $("input[name='file']");
		
		//이미지 파일들을 변수에 담음
		//inputFile[0].files : 여러개의 이미지 일 수 있음
		let files = inputFile[0].files;
		
		console.log("files : " +files); //[object FileList]
		
		//<form></form>
		let formData = new FormData();
		
		//<form><input type="file" name="file"/></form>
		//files[0] : 여러개의 이미지들 중에 첫번째 이미지 하나만
		formData.append("file",files[0]);
		
		//아작났어유..피씨다타써
		//contentType : 보내는 타입
		//dataType : 응답타입
		$.ajax({
			url:"/ajax/register05Post",
			processData:false,
			contentType:false,
			data:formData,
			type:"post",
			dataType:"text",
			success:function(result){
				//result :ff4e474c-5e48-4e5b-9100-9afa210fdc48_11.jpg
				console.log("result : " + result);
				let str = "<img src='/resources/upload"+result+"' style='width:50%;' />"
				
				//5초정도 시간 지연
				setTimeout(function(){
					$("#div01").html(str);
				},5000);
			}
			
		});
	});
});
</script>
<!-- form방식 파일 업로드
action ="URI경로"
method="post"
entype = "multipart/form-data"
 -->
 <!-- http://localhost/ajax/register05 -->
<form name="frm01">
	<div id="div01"></div>
	<p><input type="file" name="file" /></p>
	<p><button type="button" id="inputFile">파일 업로드(ajax)</button></p>
</form>