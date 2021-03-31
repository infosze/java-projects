<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Smart Snack - Feltöltések</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>
<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h2>Idősáv megadásával megmutatja, mely gépek voltak feltöltve, sorrendben legelől a legtöbb termékkel feltöltött gép</h2>
	<div class="choice">
	<form action="refills">
		<label>Kezdő dátum:</label>
		<input type="date" name="startDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01">
		<label>Záró dátum:</label>
		<input type="date" name="endDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"><br>
		<input type="submit" value="Mehet"> 
	</form>
	</div>
	<c:choose>
	<c:when test="${startDate != null}">
		<h2>${ startDate} - ${ endDate}</h2>
		<c:choose>
	<c:when test="${islistEmpty == true}">
		<h2>A megadott időintervallumban nem volt feltöltés.</h2>
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
		
		</c:when>
	</c:choose>	
	
</body>
</html>