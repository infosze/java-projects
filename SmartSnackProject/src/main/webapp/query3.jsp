<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Slágertermékek</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h2>Adott év vagy hónap slágertermékei automatánként</h2>

	<div class="container">
		<form action="query3">
			<label for="year">Év megadása: </label> 
			<input type="text"
				name="year" placeholder="2021" required
				pattern="[0-9]{4}" title="négy számjegy (pl. 2021)"><br>
			<br> 
			<label for="month">Hónap megadása (opcionális): </label> 
			<input
				type="text" name="month" placeholder="03" pattern="[0-9]{2}"
				title="két számjegy (pl. 01 vagy 12)"><br>
			<br> <input type="submit" value="Mehet"><br><br>
		</form>
	</div>
	<div>
		<table border="1"> 
			<tr>
				<th>Az automata azonosítója</th>
				<th>Irányítószám</th>
				<th>Település</th>
				<th>Cím</th>
				<th>A termék azonosítója</th>
				<th>A termék neve</th>
				<th>A fogyott mennyiség</th>
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