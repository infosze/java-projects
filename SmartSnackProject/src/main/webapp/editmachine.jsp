<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - editmachine  </title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	

	<form action='MachineEditServlet2' method='post'> 
        <input type='hidden' name='machineId' value="${ machine.getMachineId()}"/>
        <table>  
        <tr><td>MachineType:</td><td><input type='text' name='machineTypeId' value="${ machine.getMachineTypeId()}"/></td></tr>
        <tr><td>Country:</td><td><input type='text' name='country' value="${ machine.getCoutry()}" /> </td></tr>
        <tr><td>ZipCode:</td><td><input type='text' name='zipCode' value="${ machine.getZipCode()}" /> </td></tr>
        <tr><td>City:</td><td><input type='text' name='city' value="${ machine.getCity()}" /> </td></tr>
        <tr><td>Address:</td><td><input type='text' name='address' value="${ machine.getAddress()}" /> </td></tr>
        
       <tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>
      </table>
        </form>

</body>
</html>