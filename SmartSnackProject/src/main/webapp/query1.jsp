<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - Termék kifogyott</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h1>Kifogyott min. 3 termékkategória:</h1>
	<%
	// Set refresh, autoload time as 10 seconds
	response.setIntHeader("Refresh", 10);
	//             System.out.println(" Refreshed..."); // TODO test. fix it later.
	%>
</body>

</html>