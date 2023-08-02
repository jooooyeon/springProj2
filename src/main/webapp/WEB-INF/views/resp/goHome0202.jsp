<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Home0202</h2>
<!-- 타일즈가 적용되지 않도록 하여 새 창을 띄워보자 -->
<button id="btnOpenWin" onclick="fn_owin()">새창(/resp/goHome0206)</button>
<script type="text/javascript">
	function fn_owin(){
		let btnOpenWin = document.getElementById("btnOpenWin");
		//window.open("/resp/goHome0206","owin","scrollbars=yes,toolbar=yes,location=yes,resizable=yes,status=yes,menubar=yes,resizable=yes,width=100,height=100,left=0,top=0,")
		window.open("/resp/goHome0206","owin","width=400,height=300")
	}
</script>