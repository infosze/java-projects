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
	<c:choose>
	<c:when test="${islistEmpty == true}">
		<h2>Minden automata működik.</h2>
		</c:when>
		<c:otherwise>
		<table>
			<tr>
				<th>Automata azonosító</th>
				<th>Irányítószám</th>
				<th>Település</th>
				<th>Cím</th>
			</tr>
			<c:forEach var="product_machineList" items="${ product_machineList}">
				<tr>
					<td><c:out value="${ product_machineList.get(0)}" /></td>
					<td><c:out value="${ product_machineList.get(1)}" /></td>
					<td><c:out value="${ product_machineList.get(2)}" /></td>
					<td><c:out value="${ product_machineList.get(3)}" /></td>
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