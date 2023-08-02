<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<!-- Bootstrap core JavaScript-->
<script src="/resources/sbadmin2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/resources/sbadmin2/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/resources/sbadmin2/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="/resources/sbadmin2/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/resources/sbadmin2/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="/resources/sbadmin2/js/demo/datatables-demo.js"></script>
<!-- Custom fonts for this template -->
<link href="/resources/sbadmin2/vendor/fontawesome-free/css/all.min.css"
   rel="stylesheet" type="text/css">
<link
   href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
   rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link
   href="/resources/sbadmin2/vendor/datatables/dataTables.bootstrap4.min.css"
   rel="stylesheet">


<!-- //////////////////////////내용 시작///////////////////////////////// -->

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Tables</h1>
<p class="mb-4">
   DataTables is a third party plugin that is used to generate the demo
   table below. For more information about DataTables, please visit the <a
      target="_blank" href="https://datatables.net">official DataTables
      documentation</a>.
</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
   <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
   </div>
   <div class="card-body">
      <div class="table-responsive">
         <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
            
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
                              style="width: 116.2px;">번호</th>
                           <th class="sorting" tabindex="0" aria-controls="dataTable"
                              rowspan="1" colspan="1"
                              aria-label="상품명: activate to sort column ascending"
                              style="width: 182.2px;">상품명</th>
                           <th class="sorting" tabindex="0" aria-controls="dataTable"
                              rowspan="1" colspan="1"
                              aria-label="가격: activate to sort column ascending"
                              style="width: 80.2px;">가격</th>
                           <th class="sorting" tabindex="0" aria-controls="dataTable"
                              rowspan="1" colspan="1"
                              aria-label="이미지 다운로드 : activate to sort column ascending"
                              style="width: 30.2px;">이미지 다운로드</th>
                        </tr>
                     </thead>
                     <tbody>
                     <!-- data : List<ItemVO> -->
                     <c:forEach var="itemVO" items="${data}" varStatus="stat">
                        <tr class="odd">
                           <td class="sorting_1">${itemVO.rnum}</td>
                           <td><a href="/item/detail?itemId=${itemVO.itemId}">${itemVO.itemName}</a></td>
                           <td>${itemVO.price}</td>
                           <td><img class="clsImg" data-picture-url="${itemVO.pictureUrl}" src="/resources/sbadmin2/img/file.jpg" style="width:30px;cursor:pointer;"></td>
                        </tr>
                        </c:forEach>
                     </tbody>
                  </table>
               </div>
            </div>
         </div>
         <a href="/item/register" class="btn btn-primary btn-icon-split">
                <span class="icon text-white-50">
                    <i class="fas fa-flag"></i>
                </span>
                <span class="text">상품등록</span>
            </a>
      </div>
   </div>
</div>
<iframe id="ifrm" name="ifrm" style="display:none;"></iframe>
<script type="text/javascript">
$(function(){
   $(".clsImg").on("click",function(){
	   //this : 클릭한 디스크 아이콘에 해당되는 바로 그<img src...요소
	   //data-picture_url="/2023/05/08/29b763d5-9e20-4d44-8163-1ec2586e0700_z2.jpg"
	   //<img class="clsImg" data-picture-url="/2023/05/08/29b763d5-9e20-4d44-8163-1ec2586e0700_z2.jpg" src="/resources/sbadmin2/img/file.jpg" style="width: 30px; cursor: pointer" ;="">

      let filename = $(this).data("pictureUrl"); // 이렇게 가져와짐(/2023/05/08/acda671d-9ab7-4359-b17a-eaf64ce8ddcd_P1236.jpg)
      
      console.log("filename : " + filename);
      
      //<iframe id="ifrm" name="ifrm" style="display:none;"></iframe>
      let vIfrm = document.getElementById("ifrm");
      vIfrm.src = "/download?filename="+filename;
      
   });
});
</script>
<!-- /////////////////////////////내용 끝/////////////////////////// -->