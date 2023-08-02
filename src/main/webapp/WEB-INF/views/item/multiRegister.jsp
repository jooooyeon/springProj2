<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<div class="card card-primary">
	<div class="card-header">
		<h3 class="card-title">상품 등록</h3>
	</div>
	<!-- /.card-header -->
	<!-- form start
	요청URI : /item/multiRegisterPost
	요청 파라미터 : {itemname=초코파이,price=10000,description=상세내용&pictures=파일객체}
	요청방식 : post
	 -->

	<form id="frm01" action="/item/multiRegisterPost" method="post" enctype="multipart/form-data">
		<div class="card-body">
			<div class="form-group">
				<label for="itemName">상품명</label> <input type="text"
					class="form-control" id="itemName" name="itemName"
					placeholder="상품명을 입력해 주세요" required />
			</div>
			<div class="form-group">
				<label for="price">가격</label> <input type="number"
					class="form-control" id="price" name="price"
					placeholder="가격을 입력해 주세요" required />
			</div>
			<div class="form-group">
				<label for="description">상세설명</label>
				<textarea rows="3" clos="5" class="form-control" id="description"
					name="description"></textarea>
			</div>
			<div class="form-group">
				<label for="pictures">상품 이미지</label>
				<div class="input-group">
					<div class="custom-file">
						<input type="file" class="custom-file-input" id="pictures"
							name="pictures" multiple /> <label class="custom-file-label"
							for="pictures">Choose file</label>
					</div>
				</div>
			</div>
		</div>
		<!-- /.card-body -->

		<div class="card-footer">
			<button type="button" id="btnRegister" class="btn btn-primary">상품 등록</button>
			<a href="/item/list" class="btn btn-primary">상품 목록</a>
		</div>
	</form>
</div>
<script type="text/javascript">
	//name값을 넣어주면 됨
	CKEDITOR.replace("description");
</script>
<script>
$(function(){
	let formObj = $("#frm01");
	
	$("#btnRegister").on("click",function(){
		formObj.attr("action","/item/multiRegisterPost");	
		formObj.attr("method","post");
		formObj.submit();
	});
});
</script>