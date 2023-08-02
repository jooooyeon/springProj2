<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.4.min.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="card">
   <div class="card-header">
      <h3 class="card-title">목록</h3>
   </div>

   <div class="card-body">
      <div id="example1_wrapper" class="dataTables_wrapper dt-bootstrap4">
         <div class="row">
            <div class="col-sm-12">
               <table id="example1" class="table table-bordered table-striped dataTable dtr-inline" aria-describedby="example1_info">
                  <thead>
                     <tr>
                        <th onclick="event.cancelBubble=true">상품번호</th>
                        <th onclick="event.cancelBubble=true">상품명</th>
                        <th onclick="event.cancelBubble=true">가격</th>
                     </tr>
                  </thead>
                  <tbody>
                  <!-- data : List<Item3VO>
                  	
                   -->
                     <c:forEach var="item3VO" items="${data}" varStatus="stat">
                        <tr>
                           <td>${item3VO.itemId}</td>
                           <td><a href="/item/multiDetail?itemId=${item3VO.itemId}">${item3VO.itemName}</td>
                           <td><fmt:formatNumber  value="${item3VO.price}" pattern="#,###"/></td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </div>
         </div>

      </div>
   </div>
</div>

<script>
  $(function () {
    $("#example1").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');

  });
</script>