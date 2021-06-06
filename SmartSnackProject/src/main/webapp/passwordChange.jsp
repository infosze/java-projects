<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>${resource.getString("changePassword")}</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
			<h1>${resource.getString("changePassword")}</h1>
		<div class="container">
			<form action="passwordChange" method="post"> 
				<label>${resource.getString("name")}</label>
				<h3>${loggedInUser }</h3>
<!-- 				<input type="text" name="name" required="required"><br>  -->
				<label>${resource.getString("newPw")}</label><br>
				<input type="password" name="newPassword" required="required"><br>
				<input type="submit" value=${resource.getString("modify")}>
			</form>
			<c:choose>
				<c:when test="${successed > -1}">
			<c:choose>
				<c:when test="${successed > 0}">
					<h3>${resource.getString("success")}</h3>
				</c:when>
				<c:otherwise>
					<h3>${resource.getString("unsuccess")}</h3>
				</c:otherwise>
			</c:choose>
				</c:when>
			</c:choose>
		</div>
</body>
</html>