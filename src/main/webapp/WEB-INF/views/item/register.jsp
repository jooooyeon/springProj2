<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- <link rel="stylesheet" href="/resources/css/bootstrap.min.css"/> -->
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<script type="text/javascript">
$(function(){
	$("#price").on("change", function(){
		let str = $(this).val()
		
		//정규식. 0~9사이의 숫자로 시작(^)해서, (무조건 1개 이상)이 있고, 숫자로 끝남($)
		//숫자 체크 정규식
		let check = /^[0-9]+$/;
		
		console.log("str : "+str);
		
		//! : 정규식을 통과하지 못하면
		if(!check.test(str)){
			alert("숫자만 입력 가능합니다");
			$(this).val("0");
			$(this).focus();
		}
	})
})





</script>
	
<div class="bd-example">
	<form:form modelAttribute="itemVO" class="row g-3" id="frm01" name="frm01" action="/item/registerPost" method="post"
		enctype="multipart/form-data">

		<div class="col-md-6">
			<form:label path="itemName" class="form-label">상품명
				<!-- BindingResult bResult에 들어있는 오류중에 itemName과 관련된 오류 메시지를 출력 -->
				<code><form:errors path="itemName" /> </code>
			</form:label> 
			<form:input path="itemName" class="form-control" />
		</div>
		<div class="col-md-6">
			<form:label path="price" class="form-label">가격
				<code><form:errors path="price" /> </code>
			</form:label> 
			<!-- <input type="number"  class="form-control" id="price" name="price" required /> -->
			<form:input path="price"  class="form-control"/>
		</div>
		<div class="col-md-6 col-file">
			<label for="picture" class="form-label">파일</label>
			<!-- 파일 추가버튼 초록색 -->
			 <a href="#" class="btn btn-success btn-circle btn-sm" id="plus">
			  <i class="fas fa-check"></i>
			</a>
			<!-- 파일 삭제버튼 빨간색 -->
			 <a href="#" class="btn btn-danger btn-circle btn-sm" id="minus"> 
			 <i class="fas fa-trash"></i>
			</a>
			 <input type="file" class="form-control" id="picture" name="picture">
			<div id="imgShow"></div>
		</div>
		<div class="col-md-6">
			<form:label path="description" class="form-label">개요</form:label>
			<form:textarea rows="3" cols="5" path="description"  />
		</div>
		<div class="col-12">
			<button type="button" id="btnRegister" class="btn btn-primary">등록</button>
			<button type="reset" class="btn btn-primary">초기화</button>
			<a href="/item/list" class="btn btn-primary">상품목록</a>
      </div>
          
	</form:form>
	<div class="descendants" style="width:500px;">
		<p class="first">p(자식)
			<span>span(자손)</span>
		</p>
		<p class="second">p(자식)
			<span>span(자손)</span>
		</p>
	</div>
	<button id="btnTest">테스트</button>
</div>
<script type="text/javascript">
	CKEDITOR.replace("description");
</script>
<script type="text/javascript">
	//테스트
	$("#btnTest").on("click",function(){
		//선택된 요소의 주식중에서 모든 <span>요소를 반환
		//$(".descendants").find("span").css({"color":"red","border":"2px solid red"})
		
		//자식클래스중에서 클래스의 이름이 first인 요소를 찾음
		//$(".descendants").find(".first").css({"color":"red","border":"2px solid red"})
		
		//자식중에서 <div>,<li>,클래스 이름이 second인 요소를 선택
		$(".descendants").find("div,li, .second").css({"color":"red","border":"2px solid red"})
		
		
		//.children("p.first") : 자손중에서 클래스이름이 first인 모든 p요소를 반환
		$(".descendants").children("p.first").css({"color":"green","border":"2px solid green"})
		//.children("p.second") : 자손중에서 클래스이름이 second인 모든 p요소를 반환
		$(".descendants").children("p.second").css({"color":"blue","border":"2px solid blue"})
		
		//p, p 선택된 요소의 자식이 실제로 누구인지 보여줌
		let tree = $(".descendants").children().map(function(){
			return this.tagName;}).get().join(", ");
		
		//append는 하위 마지막 요소로 추가
		//after는 다음요소로 추가
		$(".descendants").after("<h2>"+tree+"</h2>");
		
	})

	$(function() {
		//form요소를 선택
		let formObj = $("#frm01");

		$("#btnRegister").on("click", function() {
			let len = $("input[Type='file']").length;
			console.log("len",len)
			
			if(len>1){
				formObj.attr("action", "/item/registerPost2");
				
			}else{
				//<form action="/item/registerPost" method="post"
				formObj.attr("action", "/item/registerPost");
			}
				formObj.attr("method", "post");
				formObj.submit();
			
		});

		//이미지 미리보기 시작///////////////////////////////
		$("#picture").on("change", handleImg);

		function handleImg(e) {
			let files = e.target.files;
			let fileArr = Array.prototype.slice.call(files);
			fileArr.forEach(function(f) {
				if (!f.type.match("image.*")) {
					alert("이미지만 미리보기가 가능합니다.")
					return;
				}

				let reader = new FileReader();
				reader.onload = function(e) {
					let img_html = "<img src=\"" + e.target.result
							+ "\" style='width:50%;' />";
					$("#imgShow").html(img_html);
				}

				reader.readAsDataURL(f);
			});

		}
		//이미지 미리보기 끝///////////////////////////////
		//카운터 변수
		let counter = 1;
		
		//파일 추가
		$("#plus").on("click",function(){
		//클래스가 col-file인 요소의 마지막 자식 요소로 추가 
		//			 <input type="file" class="form-control" id="picture" name="picture">
		//	<div id="imgShow"></div>
		if(counter <2){		
			$(".col-file").append("<input type='file' class='form-control' id='picture2' name='picture2' />");
			$(".con-file").append("<div id='imgShow2'><.div>");
			counter++;
		}
		//	<form class="row g-3" id="frm01" name="frm01" action="/item/registerPost2" 
		$("#frm01").attr("action","/item/registerPost2")
	});
		
		//파일삭제
		$("#minus").on("click",function(){
			//클래스가 col-file인 요소의 이름이 inpu
			if(counter==2){
				$(".col-file").children().last().remove();
				$(".col-file").children().last().remove();
				counter--;
			}
			//	<form class="row g-3" id="frm01" name="frm01" action="/item/registerPost" 
			$("#frm01").attr("action","/item/registerPost")
		});
});
</script>
