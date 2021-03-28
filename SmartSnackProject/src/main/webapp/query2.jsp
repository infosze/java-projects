<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - Meghibásodott automaták</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h2>Meghibásodott automaták:</h2>
	<%
	// Set refresh, autoload time as 10 seconds
	response.setIntHeader("Refresh", 10);
	%>
	<div>
		<table>
			<tr>
				<th>Azonosító</th>
				<th>Irányítószám</th>
				<th>Település</th>
				<th>Cím</th>
			</tr>
			<c:forEach var="automat" items="${ automatList}">
				<tr>
					<td><c:out value="${ automat.machineId}" /></td>
					<td><c:out value="${ automat.zipCode}" /></td>
					<td><c:out value="${ automat.county}" /></td>
					<td><c:out value="${ automat.address}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>