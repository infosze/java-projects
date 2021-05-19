<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Smart Snack – ${resource.getString("refills")}</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>
<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h2>${resource.getString("query6_title")}</h2>
	<div class="choice">
	<form action="refills">
		<label>${resource.getString("startdate")}:</label>
		<input type="date" name="startDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01">
		<label>${resource.getString("enddate")}:</label>
		<input type="date" name="endDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"><br>
		<input type="submit" value=${resource.getString("submit")}> 
	</form>
	</div>
	<c:choose>
	<c:when test="${startDate != null}">
		<h2>${ startDate} - ${ endDate}</h2>
		<c:choose>
	<c:when test="${islistEmpty == true}">
		<h2>${resource.getString("query6_OK_message")}</h2>
		</c:when>
		<c:otherwise>
		<table>
			<tr>
				<th>${resource.getString("machineId")}</th>
				<th>${resource.getString("zipcode")}</th>
				<th>${resource.getString("city")}</th>
				<th>${resource.getString("address")}</th>
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
	<div class="container">
	<a href="tasks.jsp">${resource.getString("back")}</a>
	</div>
</body>
</html>