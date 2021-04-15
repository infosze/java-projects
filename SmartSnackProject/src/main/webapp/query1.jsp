<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - Termék kifogyott</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h2>Kifogyott minimum 3 termékkategória:</h2>
	<%
	// Set refresh, autoload time as 10 seconds
	response.setIntHeader("Refresh", 10);
	%>
	<div>
		<c:choose>
	<c:when test="${islistEmpty == true}">
		<h2>Minden automata feltöltött.</h2>
		</c:when>
			<c:otherwise>
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
		</c:otherwise>
	</c:choose>	
	</div>
</body>

<div class="container">
	<a href="tasks.jsp">Vissza</a>
	</div>

</html>