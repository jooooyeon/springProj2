<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(function(){
	//이미지 미리보기 시작------------------------------
	$("#file").on("change",handleImg);
	//e:change이벤트 객체
	function handleImg(e){
		console.log("e : ",e)
		//let file = $("file");
		//file[0].files;
		let files = e.target.files;
		let fileArr = Array.prototype.slice.call(files);
		
		//f : 각각의 이미지 파일
		fileArr.forEach(function(f){
			//이미지가 아니면 멈춤
			if(!f.type.match("image.*")){
				alert("이미지만 가능합니다");
				return;
			}
			
			let reader = new FileReader();
			//e : 리더가 이미지를 읽을 때 그 이벤트
			reader.onload = function(e){
				//e.target.result : 이미지를 읽은 결과물
				let img_html = "<img src ='" + e.target.result +  "'style = 'width:30%;'/>";
				$("#div01").append(img_html);
			}
			//다음 이미지 파일(f)을 읽기 위해 리더를 초기화
			reader.readAsDataURL(f);
		});
	}
	//이미지 미리보기 끝-------------------------------
	
	//ajax 파일 업로드 시작////////////////////////////
	//e : 클릭 이벤트 객체
	$("#inputFile").on("click", function(e){
		//가상의 폼 생성(이미지들을 넣을 것임)
		let formData = new FormData();
		let inputFile = $("input[name='file']");
		//이미지파일들을 files오브젝트에 할당
		let files = inputFile[0].files;
		
		console.log("files : " + files);
		
		for(let i=0; i<files.length;i++){
			//files[0]:
			//<input type="file" name="uploadFile"
			formData.append("uploadFile", files[i]);
		}
		/*
		<form>
			<input type="file" name="uploadFile".. />
			<input type="file" name="uploadFile"../>
			<input type="file" name="uploadFile"../>
		</form>
		
		*/
		//아작났어유..피씨다타써//
		//contentType : 보내는 데이터 타입
		//dataType : 응답 데이터 타입
		$.ajax({
			url:"/ajax/register06Post",
			processData:false,
			contentType:false,
			data:formData,
			type:"post",
			dataType:"json",
			success:function(result){
				console.log("result : " + JSON.stringify(result));
				
			}
			
			
		});
	});
	//ajax 파일 업로드 끝 ////////////////////////////
	
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
	<p><input type="file" name="file" id="file" multiple /></p>
	<p><button type="button" id="inputFile">파일 업로드(ajax)</button></p>
</form>