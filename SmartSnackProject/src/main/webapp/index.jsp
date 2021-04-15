<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - Kezdőlap</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>

	<form action="language" method="get">
		<input type="submit" name="language" value="Magyar" />
		<input type="submit" name="language" value="English" />
	</form>

	<h1>Smart Snack</h1>
	<c:choose>
		<c:when test="${resource == null}">
			<h2>Jelentések ital és snack kiadó automatákról</h2>
		</c:when>
		<c:otherwise>
			<h2>${resource.getString("index_text") }</h2>
		</c:otherwise>
	</c:choose>

</body>
</html>
