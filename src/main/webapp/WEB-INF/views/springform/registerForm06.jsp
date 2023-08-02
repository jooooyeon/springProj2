<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<!-- modelAttribute form:form에서 사용 -->
<!-- java와 jsp를 연결. memberVO가 연결고리
	요청URI : /spring/form/registerForm06Post
	요청 파라미터 : {memId=a001, memName=개똥이, memEmail=test@test.com}
	요청방식 : post
 -->
<form:form modelAttribute="memberVO" action="registerForm06Post" method="post">
   <!-- MemberVO의 멤버 변수를 활용 -->
   <table>
      <tr>
         <td>유저ID</td>
         <!-- type="text" name="memId" id="memId" => path="memId" -->
         <!-- path : 폼 항목에 바인딩 됨 / modelAttribute에 쓰여있는 vo의 변수명-->
         <td>
         	 <!-- disabled="true" 하면 submit시 파라미터에 담아지지 않음 //readOnly="readOnly"  -->
	         <form:input path="memId" />
	         <font color = "red"><form:errors path="memId"/></font>
	         <button type="button" id="btnMemIdCheck">아이디 중복 체크</button>
         </td>
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
         <td>
        	 <form:input path="memName"/>
        	 <font color = "red"><form:errors path="memName"/></font>
         </td>
      </tr>
      <tr>
         <td>이메일</td>
         <!-- type="text" name="memMail" id="memMail" => path="memMail" -->
         <td><form:input path="memMail" /><form:errors path="memMail" /></td>
      </tr>
      <tr>
         <td>휴대폰</td>
         <!-- type="text" name="memHp" id="memHp" => path="memHp" -->
         <td><form:input path="memHp" /><form:errors path="memHp" /></td>
      </tr>
      <tr>
         <td>생일(1990-12-25)</td>
         <!-- type="text" name="memBir" id="memBir" => path="memBir" -->
         <td><input type="date" name="memBir" id="memBir"><form:errors path="memBir" /></td>
      </tr>
      
      
      <tr>
         <td>우편번호</td>
         <td><form:input path="addressVO.postCode" /><form:errors path="addressVO.postCode" />
         	<!--button / submit / reset  -->
         	<button type="button" id="btnPostCode">다음 우편번호 검색</button>
         </td>
      </tr>
      <tr>
         <td>주소</td>
         <td><form:input path="addressVO.location" /><form:errors path="addressVO.location" /></td>
      </tr>
      <tr>
         <td>상세주소</td>
         <td><form:input path="addressVO.detLocation" /><form:errors path="addressVO.detLocation" /></td>
      </tr>
      
      <tr>
         <td>카드1-번호</td>
         <td><form:input path="cardVO[0].no" /><form:errors path="cardVO[0].no" /></td>
      </tr>
      <tr>
         <td>카드1-유효년월</td>
         <td><form:input path="cardVO[0].validMonth" /><form:errors path="cardVO[0].validMonth" /></td>
      </tr>
      
      <tr>
         <td>카드2-번호</td>
         <td><form:input path="cardVO[1].no" /><form:errors path="cardVO[1].no" /></td>
      </tr>
      <tr>
         <td>카드2-유효년월</td>
         <td><form:input path="cardVO[1].validMonth" /><form:errors path="cardVO[1].validMonth" /></td>
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
      <form:button name="register" disabled="true"  >등록</form:button>
   </p>

</form:form>
<script type="text/javascript">
CKEDITOR.replace("memMemorial")

$(function(){
	
	//다음 우편번호 검색
	$("#btnPostCode").on("click", function(){
		new daum.Postcode({
			//다음 창에서 검색이 완료되면 콜백함수에 의해 결과 데이터가 data객체로 들어옴
			oncomplete:function(data){
				$("#addressVO\\.postCode").val(data.zonecode);
				$("#addressVO\\.location").val(data.address);
				$("#addressVO\\.detLocation").val(data.buildingName);
				
			}
		}).open();
	});
	
	//아이디 중복 체크
	$("#btnMemIdCheck").on("click",function(){
		let memId = $("#memId").val();
		
		console.log("memId : " + memId);
		
		let data = {"memId":memId};
		
		//processType : false,
		//let formData = new FormData();
		//formData.append("memId",memId);
		
		console.log("Data : " + JSON.stringify(data));
		//contentType : 보내는 타입
		//dataType : 응답타입
		//아작났어유.. 피씨다타써
		$.ajax({
			url:"/springform/checkMemId",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data),
			type:"post",
			dataYtpe:"json",
			success:function(result){
				//map.put("result","1")넘어옴
				
				//result : {"result":"1"}
				console.log("result : " + JSON.stringify(result));
				//result.result : 1
				console.log("result.result : " + result.result);
				
				let count = result.result;
				if(count>0){//중복됨
					Swal.fire({
						  title: '아이디가 중복되었습니다',
						  width: 600,
						  padding: '3em',
						  color: '#716add',
						  background: '#fff url(https://sweetalert2.github.io/images/trees.png)',
						  backdrop: `
						    rgba(0,0,123,0.4)
						    url("https://sweetalert2.github.io/images/nyan-cat.gif")
						    left top
						    no-repeat
						  `
						})
					
					$(this).focus();
				}else{//중복 안됨
					$("#register").removeAttr("disabled");
					
				}
			}
			
			
		});
		
	});
	
	
	
});


</script>
