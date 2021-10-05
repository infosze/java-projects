<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - ${resource.getString("machines")} ${resource.getString("edit")}  </title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	

	<form action='MachineEditServlet2' method='post'> 
      <input type='hidden' name='machineId' value="${ machine.getMachineId()}"/>
        <table>  
        	<tr><td>${resource.getString("machineId")}</td><td>  ${ machine.getMachineId()}</td></tr>
        	<tr><td>${resource.getString("machineTypeId")}</td><td><input type='text' required name='machineTypeId' value='${ machine.getMachineTypeId()}' /></td></tr>
       	    <tr><td>${resource.getString("country")}</td><td><input type='text' required name='country' value="${ machine.getCoutry()}" /> </td></tr>
      	    <tr><td>${resource.getString("zipcode")}</td><td><input type='text' required name='zipCode' value="${ machine.getZipCode()}" /> </td></tr>
            <tr><td>${resource.getString("city")}</td><td><input type='text' required name='city' value="${ machine.getCity()}" /> </td></tr>
            <tr><td>${resource.getString("address")}</td><td><input type='text' required name='address' value="${ machine.getAddress()}" /> </td></tr>
            
            <tr><td colspan='2'><button type='submit' class="button" value=${resource.getString("edit")} >${resource.getString("edit")}</button></td></tr>
        </table>
    </form>
    <div class="container">
	<a href="MachineViewServlet">${resource.getString("back")}</a>
	</div>

</body>
</html>
