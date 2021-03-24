<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - Lekérdezések</title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	<h1>LEKÉRDEZÉSEK</h1>
	<a href="query1"> 1. Három kifogyott termék</a>
	<br>
	<a href="query2"> 2. Meghibásodott automaták</a>
	<br>
	<a href="popularProducts.jsp"> 3. Az adott év vagy hónap slágertermékei automatánként</a>
	<br>
	<a href="top10Machines.jsp"> 4. A legnagyobb forgalmat bonyolító, top 10 automata listája</a>
	<br>
	<a href="runOutOfCoins.jsp"> 5. Mely automaták nem tudtak visszaadni</a>
	<br>
	<a href="refills.jsp"> 6. Automatánként történt feltöltések</a>
</body>

</html>