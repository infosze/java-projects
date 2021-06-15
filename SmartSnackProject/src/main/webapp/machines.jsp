<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - ${resource.getString("machines") }</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	
	<div class="container">
	<h3> ${resource.getString("machines")} </h3>
	<br><br>
	<a href="addmachine.jsp">  + Automata hozzáadása</a>
	<br><br>
	</div>


	<div>
		<c:choose>
			<c:when test="${!machines.isEmpty()}">

				<table>
					<tr>
						<th>machineId</th>
						<th>machineType</th>
						<th>country</th>
						<th>zipcode</th>
						<th>city</th>
						<th>address</th>
						<th>edit</th>
						<th>delete</th>
					</tr>
					<c:forEach var="machine" items="${machines}">
						<tr>
							<td><c:out value="${ machine.getMachineId()}" /></td>
							<td><c:out value="${ machine.getMachineTypeId()}" /></td>
							<td><c:out value="${ machine.getCoutry()}" /></td>
							<td><c:out value="${ machine.getZipCode()}" /></td>
							<td><c:out value="${ machine.getCity()}" /></td>
							<td><c:out value="${ machine.getAddress()}" /></td>
							<td><a href="editmachine?machineId=${ machine.getMachineId()}">edit</a></td> 
							<td><a href="MachineDeleteServlet?machineId=${ machine.getMachineId()}">delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
<%-- 				<h2>${resource.getString("machines")}</h2> --%>
			</c:otherwise>
		</c:choose>

	</div>


</body>
</html>