<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page session="true" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Smart Snack - ${resource.getString("settings")}</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>
<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h1 style="text-transform: uppercase">${resource.getString("settings")}</h1>
	
	<div class="container">
		<a href="machineGenerator"> 1.
			${resource.getString("setting1_title")}</a> <br> <br>
		<a href="firstReportGenerator"> 2.
			${resource.getString("setting2_title")}</a> <br> <br>
		<a href="reguralReportGenerator"> 3.
			${resource.getString("setting3_title")}</a> <br> <br>
	</div>
</body>
</html>