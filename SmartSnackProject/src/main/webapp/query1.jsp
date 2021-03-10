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


	<div>
		<table border="1">
			<tr>
				<th>Azonosító</th>
				<th>Irányítószám</th>
				<th>Megye</th>
				<th>Cím</th>
			</tr>
			<c:forEach var="automata" items="${list}" varStatus="i">
				<tr>
					<td><c:out value="${automata.machineId }" /></td>
					<td><c:out value="${automata.zipCode }" /></td>
					<td><c:out value="${automata.county }" /></td>
					<td><c:out value="${automata.address }" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>