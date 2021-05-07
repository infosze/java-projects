<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar">

<!-- 		path változó létrehozása az aktuális oldal elérési útjával.  -->
	<c:set var="path" scope="session" value="${pageContext.request.requestURI}" />

	<c:choose>
		<c:when test="${loggedInUser == null}">

			<a href="index.jsp"><i class="fas fa-home">&nbsp;</i>${resource.getString("kezdőlap")}</a>
			<a href="login.jsp"><i class="fas fa-sign-in-alt">&nbsp;</i>${resource.getString("login")}</a>
		</c:when>
		<c:otherwise>
			<a href="tasks.jsp"><i class="fas fa-tasks">&nbsp;</i>${resource.getString("reports")}</a>
			<a href="logout" style="float: right"><i
				class="fas fa-sign-out-alt">&nbsp;</i>${resource.getString("kijelentkezés")}</a>
			<a style="float: right"><i class="far fa-user">&nbsp;</i> ${name } </a>
		</c:otherwise>
	</c:choose>

	<form action="language" method="get">
		<input type="submit" style="float: right" name="language" value="Magyar" />
		 <input type="submit" style="float: right" name="language" value="English" />
	</form>

</div>
