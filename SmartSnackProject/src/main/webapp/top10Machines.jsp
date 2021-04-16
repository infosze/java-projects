<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Legforgalmasabb automaták</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h2>${resource.getString("query4_title")}</h2>

	<div class="choice">
		<form action="top10Machines" method="get">
			<label for="startDate">${resource.getString("startdate")}: </label> 
			<input type="date" name="startDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"> 
				<label for="endDate">${resource.getString("enddate")}: </label> 
				<input type="date" name="endDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"><br>
				<input type="submit" value=${resource.getString("submit")}>
			
		</form>
	</div>
	
<c:choose>
	<c:when test="${startDate != null}">
		<h2>${ startDate} - ${ endDate}</h2>
		
		<table>
			<tr>
				<th>${resource.getString("num")}</th>
				<th>${resource.getString("machineId")}</th>
				<th>${resource.getString("zipcode")}</th>
				<th>${resource.getString("city")}</th>
				<th>${resource.getString("address")}</th>
				<th>${resource.getString("turnover")}</th>
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
	
		</c:when>
	</c:choose>	
	
	<div class="container">
	<a href="tasks.jsp">${resource.getString("back")}</a>
	</div>

</body>
</html>