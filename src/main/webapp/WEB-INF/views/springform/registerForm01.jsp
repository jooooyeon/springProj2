<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>

<!-- modelAttribute form:form에서 사용 -->
<!-- java와 jsp를 연결. memberVO가 연결고리
	요청URI : /spring/form/registerForm02Post
	요청 파라미터 : {memId=a001, memName=개똥이, memEmail=test@test.com}
	요청방식 : post
 -->
<form:form modelAttribute="memberVO" action="registerForm02Post" method="post">
   <!-- MemberVO의 멤버 변수를 활용 -->
   <table>
      <tr>
         <td>유저ID</td>
         <!-- type="text" name="memId" id="memId" => path="memId" -->
         <!-- path : 폼 항목에 바인딩 됨 -->
         <td><form:input path="memId" /></td>
      </tr>
      <tr>
         <td>비밀번호</td>
         <!-- type="password" name="memPass" id="memPass" => path="memPass" -->
         <!-- path : 폼 항목에 바인딩 됨 -->
         <!-- 그동안 패스워드 타입을 사용하였음 -->
         <!-- form:password 와 type="password" 는 같음 -->
         <td><form:password path="memPass" /></td>
      </tr>
      <tr>
         <td>이름</td>
         <!-- type="text" name="memName" id="memName" => path="memName" -->
         <td><form:input path="memName"/></td>
      </tr>
      <tr>
         <td>이메일</td>
         <!-- type="text" name="memMail" id="memMail" => path="memMail" -->
         <td><form:input path="memMail"/></td>
      </tr>
      <tr>
         <td>기념일내용</td>
         <!-- <textarea cols="30" rows="5" name="memMemorial" id = "memMemorial" </textarea> -->
         <td><form:textarea path="memMemorial" cols="30" rows="5"/></td>
      </tr>
 
      <tr>
    
         <td>
         <!-- <input type = "checkbox" name="memLike" id="memLike1" value="sports"/>스포츠 -->
         <!-- 	model.addAttribute("memListMap",memListMap);  첫번째 "memListMap"를 items에 넣어줌-->
         <!-- form:checkboxes 선택할것이 여러개이므로 박스가 아닌 박시스 -->
         <!-- path에는 멤버변수명이 들어가야 함 -->
         <!-- 들어올때는 맵으로 들어오지만 나갈대는 list이다?-->
<%--          <p><td><form:checkboxes items="${memLikeMap}" path="hobbyList" /></td></p>
         <p><td><form:checkboxes items="${memLikeMap}" path="hobbyArray" /></td></p> --%>
         
         <p>
         	<form:checkbox path="hobbyList" value="sports" 	label="스포츠" 	/>
         	<form:checkbox path="hobbyList" value="movie" 	label="영화감상" 	/>
         	<form:checkbox path="hobbyList" value="music"	label="음악감상"	/>
         </p>
         <p>
         	<form:checkbox path="hobbyArray" value="sports" label="스포츠" 	/>
         	<form:checkbox path="hobbyArray" value="movie" 	label="영화감상" 	/>
         	<form:checkbox path="hobbyArray" value="music"	label="음악감상"	/>
         </p>
         <p>
         	<form:checkbox path="hobby" value="sports" 	label="스포츠" 	/>
         	<form:checkbox path="hobby" value="movie" 	label="영화감상" 	/>
         	<form:checkbox path="hobby" value="music"	label="음악감상"	/>
         </p>
         <p>
         	<p>개발자여부</p>
         	<p>
         		<form:checkbox path="developer" value="Y" />
         	</p>
         </p>
         <p>
         	<p>외국인여부</p>
         	<p>
         		<form:checkbox path="foreigner" value="false" />
         	</p>
         </p>
         
         <p>
         	<p>성별</p>
         	<p>
         		<!-- model.addAttribute("genderMap",genderMap); -->
<%--          		<form:radiobuttons items="${genderMap}" path="gender"/> --%>
				<form:radiobutton path="gender" value="male" label="남성"/>
				<form:radiobutton path="gender" value="female" label="여성"/>
				<form:radiobutton path="gender" value="other" label="기타"/>
         	</p>
         </p>
         
         <p>
         	<p>국적</p>
         	<p>
         		<!-- Map<String, String> nationalityMap = new HashMap<String, String>(); -->
				<form:select path="nationality" items="${nationalityMap}" />
				
         	</p>
         </p>
         
         <p>
         	<p><form:label path="carArray">보유자동차</form:label></p>
         	<p>
         		<!-- model.addAttribute("carMap",carMap); -->
				<form:select path="carArray" items="${carMap}" multiple="true" />
				<form:select path="carList" multiple="true">
					<form:option value="" label="===선택해 주세요==="></form:option>
					<form:options items="${carMap}" />
				</form:select>
				
         	</p>
         </p>
         <p>
         	<p>숨겨진 필드요소</p>
         	<p><form:hidden path="memRegno1" /> </p>
         </p>
    	</td>
      </tr>
   </table>
   <p>
     <!--  <button id="register" name="register" type="submit" value="Submit">등록</button> -->
      <form:button name="register">등록</form:button>
   </p>

</form:form>
<script type="text/javascript">
CKEDITOR.replace("memMemorial")

</script>