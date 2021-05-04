<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar">

	<c:choose>
		<c:when test="${loggedInUser == null}">

			<a href="index.jsp"><i class="fas fa-home"></i>${resource.getString("kezdőlap")}</a>
			<a href="login.jsp"><i class="fas fa-sign-in-alt"></i>${resource.getString("login")}</a>
		</c:when>
		<c:otherwise>
			<a href="tasks.jsp"><i class="fas fa-tasks"></i>${resource.getString("reports")}</a>
			<a href="logout" style="float: right"><i class="fas fa-sign-out-alt"></i>${resource.getString("kijelentkezés")}</a>
			<a style="float: right"><i class="far fa-user"></i> ${name } </a>
		</c:otherwise>
	</c:choose>

		
		<form action="language" method="get">
			<input type="submit" style="float: right" name="language" value="Magyar" /> 
			<input type="submit" style="float: right" name="language" value="English" />
		</form>
		
<%-- 		<a href="language" style="float: right"><i class="fas fa-home"></i>${resource.getString("hungary")}</a> --%>
<%-- 		<a href="language" style="float: right"><i class="fas fa-sign-in-alt"></i>${resource.getString("english")}</a> --%>
		

</div>
