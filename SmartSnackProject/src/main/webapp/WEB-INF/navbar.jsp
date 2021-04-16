<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar">

	<c:choose>
		<c:when test="${loggedInUser == null}">
		<c:choose>
		<c:when test="${resource==null }">
			<a href="index.jsp"><i class="fas fa-home"></i> Kezdőlap</a>
			<a href="login.jsp"><i class="fas fa-sign-in-alt"></i> Bejelentkezés</a>
			</c:when>
			<c:otherwise>
			<a href="index.jsp"><i class="fas fa-home"></i> ${resource.getString("kezdőlap")}</a>
			<a href="login.jsp"><i class="fas fa-sign-in-alt"></i> ${resource.getString("bejelentkezés")}</a>
		</c:otherwise>
		</c:choose>
		</c:when>
		<c:otherwise>
		<c:choose>
		<c:when test="${resource==null }">
			<a href="tasks.jsp"><i class="fas fa-tasks"></i> Jelentések</a>
			<a href="logout" style="float:right"><i class="fas fa-sign-out-alt"></i> Kijelentkezés</a>
			<a style="float:right"><i class="far fa-user"></i> ${name }  </a>
			</c:when>
			<c:otherwise>
			<a href="tasks.jsp"><i class="fas fa-tasks"></i> ${resource.getString("jelentések")}</a>
			<a href="logout" style="float:right"><i class="fas fa-sign-out-alt"></i> ${resource.getString("kijelentkezés")}</a>
			<a style="float:right"><i class="far fa-user"></i> ${name }  </a>
			</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

</div>  