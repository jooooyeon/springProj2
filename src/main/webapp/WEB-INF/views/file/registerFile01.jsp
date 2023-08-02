<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 <!-- 요청 URI : /file/registerFile01
 	  요청 파라미터 : {picture=파일객체}
  -->
<form action="/file/registerFile01" method="post" enctype="multipart/form-data">
	<p><input type="file" name="picture" /></p>
	<p><input type="submit" value="업로드" /></p>
</form>