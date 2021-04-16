<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page session="true" %> --%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - Lekérdezések</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>
<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h1 style="text-transform:uppercase">${resource.getString("reports")}</h1>
	<%
	// Set refresh, autoload time as 10 seconds
	response.setIntHeader("Refresh", 10);
	%> 

<div class="container">
	<a href="query1"> 1. ${resource.getString("query1_title")}</a>&nbsp;&nbsp;
	
	<c:choose>
	<c:when test="${isEmptyQ1 == true}">
		<span style="color:blue; font-size:24px">[ ${resource.getString("query1_okmessage")} ]</span>
		</c:when>
		<c:otherwise>
		<span style="color:red; font-size:24px">[ ${listSizeQ1} ${resource.getString("db")}! ]</span>
		</c:otherwise>
		</c:choose>
	
	<br><br>
	<a href="query2"> 2. ${resource.getString("query2_title")}</a>&nbsp;&nbsp;
	
	<c:choose>
	<c:when test="${isEmptyQ2 == true}">
		<span style="color:blue; font-size:24px">[ ${resource.getString("query1_okmessage")} ]</span>
		</c:when>
		<c:otherwise>
		<span style="color:red; font-size:24px">[ ${listSizeQ2} ${resource.getString("db")}! ]</span>
		</c:otherwise>
		</c:choose>
	
	<br><br>
	<a href="popularProducts.jsp"> 3. ${resource.getString("query3_title")}</a>
	<br><br>
	<a href="top10Machines.jsp"> 4. ${resource.getString("query4_title")}</a>
	<br><br>
	<a href="runOutOfCoins.jsp"> 5. ${resource.getString("query5_title")}</a>
	<br><br>
	<a href="refills.jsp"> 6. ${resource.getString("query6_title")}</a>
	</div>
	
</body>
</html>