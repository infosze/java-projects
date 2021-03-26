<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Smart Snack - Kifogyott érme</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>
<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h2>Év, hónap, nap pontosan beállított dátumon megadja, hogy mely automaták nem tudtak visszaadni</h2>
	<div class="task">
	<form action="query5">
		<label>Dátum:</label>
		<input type="date" name="date" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"><br>
		<input type="submit" value="Mehet"> 
	</form>
	</div>
	<div class="spreadsheet">
		<table border="1">
			<tr>
				<th>Automata azonosító</th>
				<th>Ország</th>
				<th>Irányítószám</th>
				<th>Település</th>
				<th>Cím</th>
			</tr>
			<c:forEach var="machine" items="${ machineList}">
				<tr>
					<td><c:out value="${ machine.get(0)}" /></td>
					<td><c:out value="${ machine.get(1)}" /></td>
					<td><c:out value="${ machine.get(2)}" /></td>
					<td><c:out value="${ machine.get(3)}" /></td>
					<td><c:out value="${ machine.get(4)}" /></td>
				</tr>
			</c:forEach>
		</table>
	
	</div>
	
</body>
</html>