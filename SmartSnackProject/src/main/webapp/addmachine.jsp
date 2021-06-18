<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Smart Snack - ${resource.getString("machines")} ${resource.getString("add")}  </title>
<%@ include file="WEB-INF/meta.jsp"%>
</head>

<body>
	<%@ include file="WEB-INF/navbar.jsp"%>
	

	<form action='MachineSaveServlet' method='post'> 
       
        <table>  
      	  <tr><td>${resource.getString("machineTypeId")}</td><td><input type='text' name='machineTypeId'/></td></tr>
      	  <tr><td>${resource.getString("country")}</td><td><input type='text' name='country'  /> </td></tr>
		  <tr><td>${resource.getString("zipcode")}</td><td><input type='text' name='zipCode' /> </td></tr>
       	  <tr><td>${resource.getString("city")}</td><td><input type='text' name='city' /> </td></tr>
          <tr><td>${resource.getString("address")}</td><td><input type='text' name='address' /> </td></tr>
        
          <tr><td colspan='2'><input type='submit' value=${resource.getString("save")} ></td></tr>
        </table>
    </form>

</body>
</html>