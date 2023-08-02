<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
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
 <!-- 요청 URI : /file/registerFile04
 	  요청 파라미터 : {userId=gaeddongi&password=java&picture=파일객체}
 	  
  -->
<form action="/file/registerFile04" method="post" enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="gaeddongi"> </p>
	<p>password : <input type="password" name="password" value="java"> </p>
	<p><input type="file" name="picture" /></p>
	<p><input type="file" name="picture2" /></p>
	<p><input type="submit" value="업로드" /></p>
</form>
<hr />
 <!-- 요청 URI : /file/registerFile05
 	  요청 파라미터 : {userId=gaeddongi&password=java&pictureList[0]=파일객체&pictureList[1]=파일객체}
  -->
  <!-- 실패 -->
<form action="/file/registerFile05" method="post" enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="gaeddongi"> </p>
	<p>password : <input type="password" name="password" value="java"> </p>
	<p><input type="file" name="pictureList[0]" /></p>
	<p><input type="file" name="pictureList[1]" /></p>
	<p><input type="submit" value="업로드" /></p>
</form>
<hr />
 <!-- 요청 URI : /file/registerFile06
 	  요청 파라미터 : {userId=gaeddongi&password=java&pictureArray[0]=파일객체&pictureArray[1]=파일객체}
  -->
  <!-- 성공 -->
<form action="/file/registerFile06" method="post" enctype="multipart/form-data">
	<p>userId : <input type="text" name="userId" value="gaeddongi"> </p>
	<p>password : <input type="password" name="password" value="java"> </p>
	<p><input type="file" id="input_imgs" name="pictureArray" multiple /></p>
	<p id="card-images"></p>
	<p><input type="submit" value="업로드" /></p>
</form>

<script type="text/javascript">
$(function(){
	$("#input_imgs").on("change",handelImgFileSelect);
	//e : onchange 이벤트 객체
	function handelImgFileSelect(e){
		console.log(e)
		let files = e.target.files;
		let fileArr = Array.prototype.slice.call(files);
		let img_html="";
		$("#card-images").html("");
		//f : 이미지 파일
		fileArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("이미지만 가능합니다");
				return
			}
			let reader = new FileReader();
			
			//e : 이미지를 읽는 이벤트
			reader.onload = function(e){
				let img_html = "<img src=\"" + e.target.result + "\" style='width:50%;' />";
				$("#card-images").append(img_html);
			}
			
			reader.readAsDataURL(f);
			
		});//end forEach
	}
	
	
});
</script>
