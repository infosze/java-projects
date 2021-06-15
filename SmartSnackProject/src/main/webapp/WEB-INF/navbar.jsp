<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar">

	<c:set var="pagePath" scope="session" value="${pageContext.request.requestURI}" />

	<c:choose>
		<c:when test="${loggedInUser == null}">

			<a href="index.jsp"><i class="fas fa-home">&nbsp;</i>${resource.getString("homepage")}</a>
			<a href="login.jsp"><i class="fas fa-sign-in-alt">&nbsp;</i>${resource.getString("login")}</a>
		</c:when>
		<c:otherwise>
			<a href="tasks.jsp"><i class="fas fa-tasks">&nbsp;</i>${resource.getString("reports")}</a>
			<a href="MachineViewServlet"><i class="far fa-list-alt">&nbsp;</i>${resource.getString("machines")}</a>
			
			<a href="logout" style="float: right"><i
				class="fas fa-sign-out-alt">&nbsp;</i>${resource.getString("logout")}</a>
			<a href="passwordChange.jsp" style="float: right"><i class="far fa-user">&nbsp;</i> ${loggedInUser } </a>
			<a href="settings.jsp" style="float: right"><i class="fas fa-cog">&nbsp;</i>${resource.getString("settings")}</a>
		</c:otherwise>
	</c:choose>

	<form action="language" method="get">
		<input type="submit" style="float: right" name="language" value="Magyar" />
		 <input type="submit" style="float: right" name="language" value="English" />
	</form>

</div>
