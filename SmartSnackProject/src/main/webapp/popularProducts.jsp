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

	<div class="choice">
		<form action="popularProducts" method="get">
			<label for="year">Év:</label> 
			<input type="text" name="year" placeholder="2021" required pattern="[0-9]{4}"
				title="négy számjegy (pl. 2021)"> 
			<label for="month">Hónap (opcionális):</label> 
			<input type="text" name="month" placeholder="03" pattern="[0-9]{2}"
				title="két számjegy (pl. 01 vagy 12)"><br>
			<input type="submit" value="Mehet">
			<br>
		</form>
	</div>

<c:choose>
	<c:when test="${year != null}">
		<h2>${ year} ${ month}</h2>
		
		<table>
			<tr>
				<th>Automata azonosító</th>
				<th>Irányítószám</th>
				<th>Település</th>
				<th>Cím</th>
				<th>A termék azonosítója</th>
				<th>A termék neve</th>
				<th>A fogyott mennyiség</th>
			</tr>
				<c:forEach var="product" items="${productList}">
					<tr>
						<td><c:out value="${product.get(0)}" /></td>
						<td><c:out value="${product.get(1)}" /></td>
						<td><c:out value="${product.get(2)}" /></td>
						<td><c:out value="${product.get(3)}" /></td>
						<td><c:out value="${product.get(4)}" /></td>
						<td><c:out value="${product.get(5)}" /></td>
						<td><c:out value="${product.get(6)}" /></td>
					</tr>
				</c:forEach>
		</table>
		
	</c:when>
	</c:choose>	


</body>
</html>