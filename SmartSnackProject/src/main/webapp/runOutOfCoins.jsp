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
	<div class="choice">
	<form action="runOutOfCoins">
		<label>Dátum:</label>
		<input type="date" name="date" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"><br>
		<input type="submit" value="Mehet"> 
	</form>
	</div>
	<c:choose>
	<c:when test="${date != null}">
		<h2>${ date}</h2>
		<c:choose>
	<c:when test="${islistEmpty == true}">
		<h2>A megadott dátumon, minden automata vissza tudott adni.</h2>
		</c:when>
			<c:otherwise>
		<table>
			<tr>
				<th>Automata azonosító</th>
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
				</tr>
			</c:forEach>
		</table>
		</c:otherwise>
	</c:choose>	
		</c:when>
	</c:choose>	
	
	
</body>
</html>