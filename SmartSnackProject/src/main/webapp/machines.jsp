<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" /> --%>
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
		<h3>${resource.getString("machines")}</h3>
		<br> <br> <a href="addmachine.jsp"> + ${resource.getString("machines")} ${resource.getString("add")}</a> <br>
		<br>
	</div>
	<div>
<form>
			<c:choose>
				<c:when test="${!machines.isEmpty()}">

				<script>
   				 function ConfirmDelete()
   				 {
   					return confirm("${resource.getString("confirm_delete")}");
  				 }
				</script>
				
					<table>
						<tr>
							<th>${resource.getString("machineId")}</th>
							<th>${resource.getString("machineTypeId")}</th>
							<th>${resource.getString("country")}</th>
							<th>${resource.getString("zipcode")}</th>
							<th>${resource.getString("city")}</th>
							<th>${resource.getString("address")}</th>
							<th>${resource.getString("edit")}</th>
							<th>${resource.getString("delete")}</th>
						</tr>
						<c:forEach var="machine" items="${machines}">
							<tr>
								<td><c:out value="${ machine.getMachineId()}" /></td>
								<td><c:out value="${ machine.getMachineTypeId()}" /></td>
								<td><c:out value="${ machine.getCoutry()}" /></td>
								<td><c:out value="${ machine.getZipCode()}" /></td>
								<td><c:out value="${ machine.getCity()}" /></td>
								<td><c:out value="${ machine.getAddress()}" /></td>
								
								<td><a href="editmachine?machineId=${ machine.getMachineId()}"><button class="button" type="button" > ${resource.getString("edit")}</button></a></td>
								<td><button class="button" type="submit" formaction="MachineDeleteServlet?machineId=${ machine.getMachineId()}" formmethod="POST" onclick="return ConfirmDelete();">${resource.getString("delete")}</button></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<%-- 				<h2>${resource.getString("machines")}</h2> --%>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
</body>
</html>