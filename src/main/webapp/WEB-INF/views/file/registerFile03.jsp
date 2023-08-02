<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
//document에 모든 요소가 로딩된 후에 실행
$(function(){
	///이미지 미리보기 시작///////////////////////////////////////
	$("#input_imgs").on("change",handleImageFileSelect);
	
	
	//e:onchange이벤트 객체
	function handleImageFileSelect(e){
		//e.target : <input type="file" id="input_imgs"...>
		//파일들을 끄집어 내서 files오브젝트에 담음
		let files = e.target.files;
		//이미지 오브젝트를 배열로 나열 할 수 있음
		let fileArr = Array.prototype.slice.call(files);
		
		//fileArr : 이미지배열
		//f : 이미지배열에서 끄집어낸 이미지 파일
		fileArr.forEach(function(f){
			//이미지가 아니면 종료
			if(!f.type.match("image.*")){
				alert("이미지만 미리보기가 가능합니다.")
				//함수 종료
				return;
			}
			//이미지가 맞으면 이미지를 읽어보자
			let reader = new FileReader();
			//e : reader객체가 이미지를 읽을 때 바로 그 이벤트
			reader.onload = function(e){
				//e.target : 읽고 있는 이미지 객체
				//e.target.result : 이미지를 읽은 그 결과 데이터
				let img_html = 
				"<img src=\"" + e.target.result + "\" style='width:50%;' />";
				//객체.append : 누적, .html : 새로고침, innerHTML : J/S
				$("#card-images").html(img_html);
			}
			//다음이미지파일(f)을 위해 리더를 초기화
			reader.readAsDataURL(f);
			
		});
	}
	
	///이미지 미리보기 끝///////////////////////////////////////
});
</script>
<!-- 
1. enctype="multipart/form-data"
2. pom.xml
	-commons-fileupload, commons-io
	- imgscalr-lib, thumbnailator
3. root-context.xml 설정
4. web.xml설정
5. WAS(Tomcat) > config > context.xml설정
6. method="post"이어야함 무조건
7. input type = "file"
 -->
 <!-- 요청 URI : /file/registerFile03
 	  요청 파라미터 : {userId=gaeddongi&password=java&picture=파일객체}
 	  
  -->
<form action="/file/registerFile03" method="post" enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="gaeddongi"> </p>
	<p>password : <input type="password" name="password" value="java"> </p>
	<p><input type="file" id="input_imgs" name="picture" /></p>
	<p id="card-images"></p>
	<p><input type="submit" value="업로드" /></p>
</form>

<!-- 이미지 미리보기-->
