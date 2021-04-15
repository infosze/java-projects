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
	<h1>JELENTÉSEK</h1>
	<%
	// Set refresh, autoload time as 10 seconds
	response.setIntHeader("Refresh", 10);
	%> 

<div class="container">
	<a href="query1"> 1. Három termékből kifogyott automaták</a>&nbsp;&nbsp;
	
	<c:choose>
	<c:when test="${isEmptyQ1 == true}">
		<span style="color:blue; font-size:24px">[ Minden automata rendben. ]</span>
		</c:when>
		<c:otherwise>
		<span style="color:red; font-size:24px">[ ${listSizeQ1} db! ]</span>
		</c:otherwise>
		</c:choose>
	
	<br><br>
	<a href="query2"> 2. Meghibásodott automaták</a>&nbsp;&nbsp;
	
	<c:choose>
	<c:when test="${isEmptyQ2 == true}">
		<span style="color:blue; font-size:24px">[ Minden automata rendben. ]</span>
		</c:when>
		<c:otherwise>
		<span style="color:red; font-size:24px">[ ${listSizeQ2} db! ]</span>
		</c:otherwise>
		</c:choose>
	
	<br><br>
	<a href="popularProducts.jsp"> 3. Az adott év vagy hónap slágertermékei automatánként</a>
	<br><br>
	<a href="top10Machines.jsp"> 4. A legnagyobb forgalmat bonyolító, top 10 automata listája</a>
	<br><br>
	<a href="runOutOfCoins.jsp"> 5. Visszaadni nem tudó automaták</a>
	<br><br>
	<a href="refills.jsp"> 6. Feltöltött automaták</a>
	</div>
	
</body>
</html>