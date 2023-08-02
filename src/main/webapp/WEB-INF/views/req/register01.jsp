<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">

//http://localhost/req/register01
$(function(){
	$("#btn1").on("click",function(){
		$("#frm").attr("action","/req/register01Post2");
		$("#frm").submit();
	});
	
	let str ="";
		str+="<option value='volvo'>볼보</option>";
		str+="<option value='saab'>싸브</option>";
		str+="<option value='opel'>오펠</option>";
		str+="<option value='audi'>아우디</option>";
	//요소.append() : 누적
	$("select[name='cars']").append(str);
	
	
	str = ""; //str초기화
	str+="<option value='stake'>스테이크</option>";
	str+="<option value='jjajang'>짜장면</option>";
	str+="<option value='rice'>백반</option>";
	
	$("select[name='foodArray']").append(str);
	
	
	//.style("cursor:pointer")
	$("label").css("cursor","pointer")
	
	//무엇이 체크되었는지 미리 확인
	//input checkbox에서 체크된 값을 가져오기
	$(".chkClass").on("click",function(){
		let chkStr = "";
		//input type="checkbox" id="hobby1" name="hobby" value="sports">
		$("input:checkbox[name='hobby']").each(function(index){
			//this : 3개의 체크박스 요소가 반복되면서 focus된 바로 그 체크박스를 말함
			if($(this).is(":checked")==true){
				chkStr += $(this).val() +",";
			}
		});
		console.log("체크된 값:" + chkStr);
		
		//input checkbox의 전체 개수(hobby한정)
		let chkLength = $("input:checkbox[name='hobby']").length;
		console.log("체크박스의 전체 개수 : " + chkLength);
		
		//input checkbox에서 체크한 개수(hobby한정)
		let chkedLength = $("input:checkbox[name='hobby']:checked").length;
		console.log("체크박스의 체크된 개수 : " + chkedLength);
		
		
		//모든 체크박스 중에서 체크된 값을 가져오기(hobby뿐만 아니라 모두 다)=>안됨.
		//class="chkClass" 인풋 중에 체크된것만
		chkStr="";
		$(".chkClass").each(function(index){
			//this : 3개의 체크박스 요소가 반복되면서 focus된 바로 그 체크박스를 말함
			if($(this).is(":checked")==true){
				chkStr += $(this).val() +",";
			}
			
		});
		console.log("모든 체크박스 중에서 체크된값:" + chkStr)
	});
	
	
});
</script>
<h2>register01</h2>
<!-- 요청 URI : /req/register01Post
	 요청 파라미터 : {userId=gaeddongi, password=java, coin=100 
	 요청방식 : post
	 -->
<form id="frm" action="/req/register01Post" method="post">
	<p>userId : <input type="text" name="userId" value="gaeddongi" /></p>
	<p>password : <input type="text" name="password" value="java" /></p>
	<p>coin : <input type="text" name="coin" value="100" /></p>
	<p><input type="submit" value="전송" /> </p>
	<p><button type="button" id="btn1">자바빈즈 매개변수로 처리</button></p>
</form>
<hr />
<!-- 요청 URI : /req/register02Post
	 요청 파라미터 : {userId=gaeddongi, dateOfBirth=0527 
	 요청방식 : post
	 -->
<form id="frm2" action="/req/register02Post" method="post">
	<p>userId : <input type="text" name="userId" value="gaeddongi" /></p>
	<p>dateOfBirth : <input type="text" name="dateOfBirth" value="2023/05/27" /></p>
	<!-- <p>dateOfBirth : <input type="date" name="dateOfBirth" value="2023-05-27" /></p> -->
	<p><button type="submit">Date타입 처리</button></p>
</form>

<hr />
<!-- 요청 URI : /req/register02Post2
	 요청 파라미터 : {userId=gaeddongi, dateOfBirth=2023-04-26 
	 요청방식 : post
	 -->
<form id="frm3" action="/req/register02Post2" method="post">
	<p>userId : <input type="text" name="userId" value="gaeddongi" /></p>
	<p>dateOfBirth : <input type="date" name="dateOfBirth" value="2023-04-26" /></p>
	<p>
		<select name="nationality">
			<option value="Korea" selected>대한민국</option>
			<option value="Germany">독일</option>
			<option value="Austrailia">호주</option>
			<option value="Canada">캐나다</option>
		</select>
	</p>
	<p>
		<select name="cars" multiple>
		<!-- multiple쉬프트 누르고 여러개 선택 가능 -->
		</select>
	</p>
	<p>
		<select name="foodArray" multiple>
		</select>
	</p>
	<p>
		<p><input type="checkbox" class="chkClass" id="hobby1" name="hobby" value="sports">
			<label for="hobby1" >스포츠</label>
		</p>
		<p><input type="checkbox" class="chkClass" id="hobby2" name="hobby" value="music">
			<label for="hobby2">음악</label>
		</p>
		<p><input type="checkbox" class="chkClass" id="hobby3" name="hobby" value="movie">
			<label for="hobby3">영화</label>
		</p>
	</p>
	<p>
		<p><input type="checkbox" class="chkClass" id="job1" name="job" value="developer" checked>
			<label for="job1" >개발자</label>
		</p>
		<p><input type="checkbox" class="chkClass" id="job2" name="job" value="musician">
			<label for="job2">음악인</label>
		</p>
		<p><input type="checkbox" class="chkClass" id="job3" name="job" value="actor">
			<label for="job3">배우</label>
		</p>
	</p>
	<p><button type="submit">Date타입 처리(자바빈즈)</button></p>
</form>