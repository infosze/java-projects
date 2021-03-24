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
	<div class="container">
	<form action="refills">
		<label>Kezdő dátum megadása</label><br>
		<input type="date" name="startDate"><br>
		
		<label>Záró dátum megadása</label><br>
		<input type="date" name="endDate"><br>
		<input type="submit" value="Mehet">  
	</form>
	</div>
	<div>
		<table border="1">
			<tr>
				<th>Azonosító</th>
				<th>Ország</th>
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
					<td><c:out value="${ product_machineList.get(4)}" /></td>
				</tr>
			</c:forEach>
		</table>
	
	</div>
	
</body>
</html>