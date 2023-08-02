<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<!-- ${serverTime}는 HomeController.java의  model.addAttribute("serverTime", formattedDate );에서 왔음 -->
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
