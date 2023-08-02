<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
function chk(){
	let memPass = $("#memPass").val();
	let memPassConfirm = $("#memPassConfirm").val();
	
	if(memPassConfirm==""){
		alert("비밀번호 확인을 작성해주세요");
		$("#memPassConfirm").focus();//깜빡깜빡
		
		//submit이 안됨
		return false;
	}
	if(memPass != memPassConfirm){
		alert("비밀번호를 다시 확인해주세요"); //글자가 선택됨
		return false;
	}
	//submit 실행
	return true;
}

</script>
<div class="card o-hidden border-0 shadow-lg my-5">
	<div class="card-body p-0">
		<!-- Nested Row within Card Body -->
		<div class="row">
			<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
			<div class="col-lg-7">
				<div class="p-5">
					<div class="text-center">
						<h1 class="h4 text-gray-900 mb-4">회원 상세</h1>
					</div>
					<form class="user" onsubmit="return chk()">
					
						<div class="form-group row">
							<div class="col-sm-6 mb-3 mb-sm-0">
								<input type="text" class="form-control form-control-user"
									name="memId" id="memId" value="${data.memId}" readonly placeholder="회원아이디">
							</div>
							<div class="col-sm-6">
								<input type="text" class="form-control form-control-user"
									name="memName" id="memName" value="${data.memName}" readonly placeholder="회원명">
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-sm-6 mb-3 mb-sm-0">
								<input type="text" class="form-control form-control-user"
									name="memJob" id="memJob" value="${data.memJob}" readonly placeholder="직업"/>
							</div>
							<div class="col-sm-6">
								<input type="text" class="form-control form-control-user"
									name="memLike" id="memLike" value="${data.memLike}" readonly placeholder="취미"/>
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-sm-6 mb-3 mb-sm-0">
								<input type="text" class="form-control form-control-user"
									name="memZip" id="memZip" value="${data.memZip}" readonly placeholder="우편번호"/>
							</div>
							<div class="col-sm-6" >
								<button type="button" id="aSearch" 
								class="btn btn-primary btn-user btn-block disabled" >
								우편번호 검색 </button>
							</div>
						</div>
							
						<div class="form-group row">
							<div class="col-sm-12">
								<input type="text" class="form-control form-control-user"
									name="memAdd1" id="memAdd1" value="${data.memAdd1}" readonly placeholder="주소">
							</div>
						</div>
						
						<div class="form-group row">	
							<div class="col-sm-12" >
								<input type="text" class="form-control form-control-user"
									name="memAdd2" id="memAdd2" value="${data.memAdd2}" readonly placeholder="상세주소">
							</div>
						</div>
						
						<div class="form-group row">
							<div class="col-sm-6 mb-3 mb-sm-0">
								<input type="password" class="form-control form-control-user"
									name="memPass" id="memPass" value="${data.memPass}" 
									placeholder="Password" readonly >
							</div>
							<div class="col-sm-6">
								<input type="password" class="form-control form-control-user"
									id="memPassConfirm"  placeholder="Repeat Password" readonly >
							</div>
						</div>
						<hr />
						<!-- ////////////////////////////////////////////일반모드 시작//////////////////////////////////////////// -->
						<div id="div1" class="form-group row">
							<div class="col-sm-6" >
								<button type="button" id="edit" class="btn btn-primary btn-user btn-block " >
								변경</button>
							</div>
							<div class="col-sm-6" >
								<button type="button" id="delete" class="btn btn-primary btn-user btn-block " >
								삭제</button>
							</div>
						</div>
						<!-- ////////////////////////////////////////////일반모드 끝//////////////////////////////////////////// -->
						<!-- /////////////////////////////(변경버튼을 누르면)////변경모드 시작//////////////////////////////////////////// -->
						<div id="div2" class="form-group row" style="display: none" >
							<div class="col-sm-6" >
								<button type="submit" class="btn btn-primary btn-user btn-block " >
								확인</button>
							</div>
							<div class="col-sm-6" >
								<!-- 파라미터 : memId=a001 -->
								<a href="/member/detail?memId=${param.memId}" type="button" 
									class="btn btn-primary btn-user btn-block " >
								취소</a>
							</div>
						</div>
						<a href="/member/list" class="btn btn-primary btn-user btn-block">
               			      회원목록 </a>
						<!-- ////////////////////////////////////////////변경모드 끝//////////////////////////////////////////// -->
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
//document가 모두 로딩된 후에 실행
$(function(){
	//변경 버튼 클릭 -> 변경 모드로 전환
	$("#edit").on("click",function(){
		$("#div1").css("display","none"); //일반모드
		$("#div2").css("display","flex"); //변경모드
		
		//입력란 활성화
		$(".form-control-user").removeAttr("readOnly");
		//우편번호 검색 버튼 활성화
		//전 : class="btn btn-primary btn-user btn-block disabled"
		//후 : class="btn btn-primary btn-user btn-block"
		$("#aSearch").removeClass("disabled");
		//태그 style <...style ='color:red'  -> 객체.css("color","green")
		//태그 속성     <... action='/list' 	   -> 객체.attr("action","/write")
										// -> 객체.prop("action","/write")
										
		$(".user").attr("action","/member/updatePost"); //속성을 추가해줌
		$(".user").attr("method","post");
		//기본키는 비활성화
		$("#memId").attr("readOnly",true);
		//다음 우편번호 검색 API 사용
		$("#aSearch").on("click", function(){
			new daum.Postcode({
				//다음창에서 검색이 완료되면 콜백함수에 의해 결과 데이터가 data객체로 들어옴
				oncomplete:function(data){
					$("#memZip").val(data.zonecode);
					$("#memAdd1").val(data.address);
					$("#memAdd2").val(data.buildingName);
				}
				
			}).open();
		});
		
	});//end edit
	
	//삭제버튼 클릭
	$("#delete").on("click",function(){
		$(".user").attr("action","/member/deletePost"); 
		$(".user").attr("method","post"); 
		
		
		//ture(1), false(0)
		let result = confirm("삭제하시겠습니까?");
		
		$("#memPassConfirm").removeAttr("readonly");

		
		console.log("result : " + result)
		
		if(result > 0){//true
			$(".user").submit();
		}else{ //false
			alert("삭제가 취소되었습니다.")
			$("#memPassConfirm").val("");
			$("#memPassConfirm").attr("readOnly",true);
		
		}
	})
	
	
	
});
</script>

