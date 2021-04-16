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
	<h2>${resource.getString("query3_title")}</h2>

	<div class="choice">
		<form action="popularProducts" method="get">
			<label for="year">${resource.getString("year")}:</label> 
			<input type="text" name="year" placeholder="2021" required pattern="[0-9]{4}"
				title=${resource.getString("explanation1")}> 
			<label for="month">${resource.getString("optional_month")}:</label> 
			<input type="text" name="month" placeholder="03" pattern="[0-9]{2}"
				title=${resource.getString("explanation2")}><br>
			<input type="submit" value=${resource.getString("submit")}>
			<br>
		</form>
	</div>

<c:choose>
	<c:when test="${year != null}">
		<h2>${ year} ${ month}</h2>
		
		<table>
			<tr>
				<th>${resource.getString("machineId")}</th>
				<th>${resource.getString("zipcode")}</th>
				<th>${resource.getString("city")}</th>
				<th>${resource.getString("address")}</th>
				<th>${resource.getString("productId")}</th>
				<th>${resource.getString("productname")}</th>
				<th>${resource.getString("quantity")}</th>
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
	
<div class="container">
	<a href="tasks.jsp">${resource.getString("back")}</a>
	</div>

</body>
</html>