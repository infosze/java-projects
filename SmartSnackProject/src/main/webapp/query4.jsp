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

	<div class="container">
		<form action="query4" method="get">
			<label for="startDate">Kezdő dátum megadása: </label> <input type="date"
				name="startDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"><br> <br> <label
				for="endDate">Záró dátum megadása: </label> <input type="date"
				name="endDate" placeholder="2021-01-01" required pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
				title="Év, hónap, nap ebben a formátumban: 2021-01-01"><br> <br> 
				<input type="submit" value="Mehet"><br>
			<br>
		</form>
	</div>



	<div class="container">
		<table border="1">
			<tr>
				<th>Sorszám</th>
				<th>Az automata azonosítója</th>
				<th>Az automata forgalma (Ft)</th>
				
			</tr>
				<c:forEach var="machine" items="${machineList}">
					<tr>
						<td><c:out value="${machine.get(0)}" /></td>
						<td><c:out value="${machine.get(1)}" /></td>
						<td><c:out value="${machine.get(2)}" /></td>
						
					</tr>
				</c:forEach>
		</table>
	</div>


</body>
</html>