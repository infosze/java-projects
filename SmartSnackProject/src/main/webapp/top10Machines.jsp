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
	<h2>Adott időszakban a legnagyobb forgalmat bonyolító automaták listája (Top 10)</h2>

	<div class="choice">
		<form action="top10Machines" method="get">
			<label for="startDate">Kezdő dátum: </label> <input type="date"
				name="startDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"> 
				<label for="endDate">Záró dátum: </label> <input type="date"
				name="endDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"><br>
				<input type="submit" value="Mehet">
			
		</form>
	</div>



<!-- 	<div class="container"> -->
		<table>
			<tr>
				<th>Sorszám</th>
				<th>Automata azonosító</th>
				<th>Forgalom (Ft)</th>
				
			</tr>
				<c:forEach var="machine" items="${machineList}">
					<tr>
						<td><c:out value="${machine.get(0)}" /></td>
						<td><c:out value="${machine.get(1)}" /></td>
						<td><c:out value="${machine.get(2)}" /></td>
						
					</tr>
				</c:forEach>
		</table>
<!-- 	</div> -->


</body>
</html>