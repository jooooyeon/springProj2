<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- model.addAttribute("data",memberVOlist); -->
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
//document 내의 모든 요소가 로딩 된 후에 실행
$(function(){
	$("#size").on("change", function(){
		// /member/list?size=10
		$("#frm").submit();
	});
});
</script>
<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">회원 정보 목록</h1>
<p class="mb-4">회원의 정보 목록입니다.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">회원목록</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
				<!-- {size=10, currentPage-1} -->
				<!-- action 생략 : /list
					 method 생략 : get -->
				<form name="frm" id="frm" action="/member/list" method="get">
				<div class="row">
					<div class="col-sm-12 col-md-6">
						<div class="dataTables_length" id="dataTable_length">
							<label>Size 
							<!-- size=10 : param -->
							<select name="size" id="size"
								aria-controls="dataTable"
								class="custom-select custom-select-sm form-control form-control-sm"><option
										value="10"
											<c:if test="${param.size=='10' }">selected</c:if>
										>10</option>
									<option value="25" 
											<c:if test="${param.size=='25' }">selected</c:if>
									>25</option>
									<option value="50"
											<c:if test="${param.size=='50' }">selected</c:if>
									>50</option>
									<option value="100"
											<c:if test="${param.size=='100' }">selected</c:if>
									>100</option></select> entries
							</label>
						</div>
					</div>
					<div class="col-sm-12 col-md-6">
						<div id="dataTable_filter" class="dataTables_filter">
							<label>Search:
								<!-- list?size=10&cond=memJob&keyword=주부 -->
								<select name="cond" id="cond"
									aria-controls="dataTable"
									class="custom-select custom-select-sm form-control form-control-sm">
									<option value="">전체</option>
									<option value="memId"
										<c:if test="${param.cond=='memId' }">selected</c:if>
									>회원아이디</option>
									<option value="memName"
										<c:if test="${param.cond=='memName' }">selected</c:if>
									>회원명</option>
									<option value="memJob"
										<c:if test="${param.cond=='memJob' }">selected</c:if>
									>직업</option>
									<option value="memLike"
										<c:if test="${param.cond=='memLike' }">selected</c:if>
									>취미</option>
								</select>
							</label>
							<label>
								<input type="search" name="keyword"
									class="form-control form-control-sm" 
									placeholder="검색어를 입력하세요"
									aria-controls="dataTable" value="${param.keyword }"/>
							</label>
							<label>
								<button type="submit" class="btn btn-primary btn-icon-split btn-sm">
									<span class="icon text-white-50">
										<i class="fas fa-flag"></i>
									</span>
									 <span class="text">검색</span>
									
								</button>
							</label>
						</div>
					</div>
				</div>
				</form>
				<div class="row">
					<div class="col-sm-12">
						<table class="table table-bordered dataTable" id="dataTable"
							width="100%" cellspacing="0" role="grid"
							aria-describedby="dataTable_info" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting sorting_asc" tabindex="0"
										aria-controls="dataTable" rowspan="1" colspan="1"
										aria-sort="ascending"
										aria-label="번호: activate to sort column descending"
										style="width: 264.797px;">번호</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="이름: activate to sort column ascending"
										style="width: 421.328px;">이름</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="아이디: activate to sort column ascending"
										style="width: 194.406px;">아이디</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="직업: activate to sort column ascending"
										style="width: 97.8594px;">직업</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="취미: activate to sort column ascending"
										style="width: 184.547px;">취미</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable"
										rowspan="1" colspan="1"
										aria-label="마일리지: activate to sort column ascending"
										style="width: 167.062px;">마일리지</th>
								</tr>
							</thead>
							<tbody>
							<!-- 페이징 처리 전 : model.addAttribute("data",memberVOList);
										data : List<MemberVO>
										
								  페이징 처리 후 : model.addAttribute("data",ArticlePage  객체);
										data.content : List<MemberVO>
								stat.index : 0부터 1씩 증가
								stat.count : 1부터 1씩증가
							 -->
							<c:forEach var="memberVO" items="${data.content}" varStatus="stat">
			                        <tr 
			                           <c:if test="${stat.count%2==0}">class="even"</c:if>
			                           <c:if test="${stat.count%2==1}">class="odd"</c:if>
			                        >
										<td class="sorting_1">${memberVO.rnum}</td>
										<td>
   									<%-- <a href="/member/detail?memId=${memberVO.memId}" class="small"> ${memberVO.memName}</a> --%>
										<a href="/member/detail?${memberVO.memId}" class="small"> ${memberVO.memName}</a>
										</td>
										<td>${memberVO.memId}</td>
										<td>${memberVO.memJob}</td>
										<td>${memberVO.memLike}</td>
										<td><fmt:formatNumber type="number" pattern="#,###" value="${memberVO.memMileage}"/></td>
									<%-- 	<td>${memberVO.memMileage}</td> --%>
									</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-5">
						<div class="dataTables_info" id="dataTable_info" role="status"
							aria-live="polite">
							<!-- Showing 시작행 to 종료행 of 전체 행 수 entries
								종료행 : currentPage * size 
								시작행 : 종료행  - (size -1)
								전체행수 : total
								
							 -->
							<!-- data.currentPage = articlePage.currentPage //data가 곧 articlePage이다-->
							<!-- 종료행 : currentPage * size  -->
							<c:set var="endRow" value="${data.currentPage * data.size }" />
							<!-- 시작행 : 종료행 - (size -1) -->
							<c:set var="startRow" value="${endRow - (data.size - 1)}" />
							<!-- 전체 행 수 : total -->
							<c:set var="total" value="${data.total}" />
							<!-- http://localhost/member/list?currentPage=2 -->
							
							<!-- Showing 1 to 1 of 1 entries -->
							<c:if test="${endRow>total}">
								<c:set var="endRow" value="${total}" />
							</c:if>
							Showing ${startRow } to ${endRow } of ${total } entries
							</div>
					</div>
					<!-- ///////////////////////페이징영역시작////////////////////////////// -->
					<div class="col-sm-12 col-md-7">
						<div class="dataTables_paginate paging_simple_numbers"
							id="dataTable_paginate">
							<ul class="pagination">
								<li class="paginate_button page-item previous 
									<c:if test='${data.startPage<6}'>disabled</c:if>
								"
									id="dataTable_previous">
									<a href="/member/list?currentPage=${data.startPage-5}&size=${param.size}&cond=${param.cond}&keyword=${param.keyword}"
									aria-controls="dataTable" data-dt-idx="0" tabindex="0"
									class="page-link">Previous</a></li>
								<!-- articlePage객체의 멤버변수
									startPage : 시작 페이지 번호
									endPage : 종료 페이지 번호
									
									param : currentPage = 2
								 -->
								 <!-- EL 태그 정리
								 		== : eq(equal)
								 		!= : ne(not equal)
								 		<  : lt(less than)
								 		>  : gt(greater than)
								 		<= : le(less equal)
								 		>= : ge(greater equal)
								  -->
									<!-- 하단 액티브 이게 현재 번호에 색깔 넣어주는거 -->
								<c:forEach var="pNo" begin="${data.startPage}" end="${data.endPage}">
								<li class="paginate_button page-item 
									<c:if test='${param.currentPage==pNo}'>active</c:if>
								">
									<!-- 하단 검색하고 그대로 냅두는거? -->
								<a href="/member/list?currentPage=${pNo}&size=${param.size}&cond=${param.cond}&keyword=${param.keyword}"
									aria-controls="dataTable" data-dt-idx="${pNo}" tabindex="0"
									class="page-link">${pNo}</a></li>
								</c:forEach>
						
									<!-- 하단 더이상 뒤로 못넘어가게 넥스트버튼 -->
								<li class="paginate_button page-item next 
									<c:if test='${data.endPage == data.totalPages}'>disabled</c:if>
								" id="dataTable_next"><a
									href="/member/list?currentPage=${data.startPage+5}&size=${param.size}&cond=${param.cond}&keyword=${param.keyword} "
									aria-controls="dataTable" data-dt-idx="7" tabindex="0"
									class="page-link">Next</a></li>
							</ul>
						</div>
					</div>
					<!-- ///////////////////////페이징영역끝////////////////////////////// -->
				</div>
			</div>
		</div>
	</div>
</div>

