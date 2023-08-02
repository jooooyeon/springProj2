//document를 모두 로딩한 후에 실행
$(function(){
	//<input type="button" class="btn btn-primary" value="등록"
	//				id="CheckAddProduct" />
	$("#CheckAddProduct").on("click",function(){
		//<input type="text" name="productId" class="form-control" required />
		let productId = $("input[name='productId']").val();
		//<input type="text" name="pname" class="form-control" required />
		let pname = $("input[name='pname']").val();
		//<input type="number" name="unitPrice" class="form-control" required />
		let unitPrice = $("input[name='unitPrice']").val();
		//<input type="number" name="unitsInStock" class="form-control" />
		//const : 상수(항상 값이 동일한 수)
		let unitsInStock = $("input[name='unitsInStock']").val();
		
		let jsonData = {"productId":productId,"pname":pname
					   ,"unitPrice":unitPrice,"unitsInStock":unitsInStock};
		
		console.log("jsonData : " + JSON.stringify(jsonData));
		
		//상품 아이디 체크
		//1) 첫글자는 P로 시작. 2) 숫자를 조합하여 5~12자까지 입력 가능
		//[0-9] : 0~9 사이의 한 숫자 
		let regExpProductId = /^P[0-9]{4,11}$/;
		if(!check(regExpProductId, productId
				, "[상품 아이디]\nP와 숫자를 조합하여 5~12자까지 입력해주세요.\n첫 글자는 P로 시작해주세요")){
			return false;
		}
		
		//상품명 체크
		//1) 4~12자까지 입력 가능 => pname.length 처리
		if(pname.length < 4 || pname.length > 12){
			alert("[상품명]\n최소 4자에서 최대 12자까지 입력해주세요");
			return false;
		}
		
		//상품 가격 체크
		//1) 숫자만 입력 가능 => unitPrice.length가 0보다 커야하고, isNaN(unitPrice) 문자가 아니어야 함
		if(unitPrice.length == 0 || isNaN(unitPrice)){
			alert("[가격]\n숫자만 입력해주세요");
			return false;
		}
		
		//음수 체크
		if(unitPrice < 0){
			alert("[가격]\n음수는 입력할 수 없습니다.");
			return false;
		}
		
		//2) 소수점 둘째자리까지 입력 가능. 120000.35
		// (?:y) : 그룹들의 집합에 대한 예외를 표현
		let regExpUnitPrice = /^\d+(?:[.]?[\d]?[\d]?)?$/;
		if(!check(regExpUnitPrice, unitPrice, "[가격]\n소수점 둘째 자리까지만 입력해주세요")){
			return false;
		}
		
		//상태 체크 테스트 시작/////////////////
		//<input type="radio" id="condition1" name="condition" value="New">
		//<input type="radio" id="condition2" name="condition" value="Old">
		//<input type="radio" id="condition3" name="condition" value="Refurbished">
		let counter = 0;
		//radio 요소가 3개여서 3회 반복
		$("input[name='condition']").each(function(index){
			//this : 반복을 하면서 focus된 바로 그 radio 요소 1개
			if($(this).is(":checked")){
				counter = counter + 1;
			}
		});
		
		console.log("counter : " + counter);
		//선택이 하나도 안되었다면..
		if(counter<1){
			alert("상태를 선택해주세요");
			return;
		}
		//상태 체크 테스트 끝/////////////////
		
		//재고 수 체크
		//숫자만 입력 가능 => isNaN 함수 사용
		let regExpInStock = /^[0-9]+$/;
		if(!regExpInStock.test(unitsInStock)){
			alert("[재고 수]\n숫자만 입력해주세요");
			return false;
		}
		
		$("form[name='newProduct']").submit();
	});//end onclick
	
	//regExp : 정규식 / value : test할 대상 값 / msg : 메시지
	function check(regExp, value, msg){
		if(regExp.test(value)){//통과했다면
			return true;
		}
		//통과하지 못했다면
		alert(msg);
		return false;
	}
	
});