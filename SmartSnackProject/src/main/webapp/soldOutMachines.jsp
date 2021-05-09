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
	// Set refresh, autoload time as 60 seconds
	response.setIntHeader("Refresh", 60);
	%>
	<div>
		<c:choose>
	<c:when test="${islistEmpty == true}">
		<h2>Minden automata fel van töltve.</h2>
		</c:when>
			<c:otherwise>
		<table>
			<tr>
				<th>${resource.getString("machineId")}</th>
				<th>${resource.getString("country")}</th>
				<th>${resource.getString("zipcode")}</th>
				<th>${resource.getString("city")}</th>
				<th>${resource.getString("address")}</th>
				<th>${resource.getString("productId")}</th>
			</tr>
				<c:forEach var="machine" items="${machineList}">
					<tr>
						<td><c:out value="${machine.get(0)}" /></td>
						<td><c:out value="${machine.get(1)}" /></td>
						<td><c:out value="${machine.get(2)}" /></td>
						<td><c:out value="${machine.get(3)}" /></td>
						<td><c:out value="${machine.get(4)}" /></td>
						<td><c:out value="${machine.get(5)}" /></td>
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