<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="pageTitle" value="RegistrationFormOk" />

<jsp:include page="../Includes/header.jsp"/>

<html>
	<head>
  		<title>PaidosDemo: <c:out value="${pageTitle}"/> </title>
    		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
        
        	<c:url var="cssUrl" value="/resources/CSS/bootstrap.min.css"/>
        	<c:url var="jsUrl" value="/resources/js/bootstrap.min.js"/>
        	<c:url var="jqueryUrl" value="/resources/js/jquery-2-1.0.3.js" />
        	<link href="${cssUrl}" rel="stylesheet"/>
        	<style>
        	  	body 
        	  	{
           	 		padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
          	 	}
        	</style>
        
	</head>
	<body>
		<div class="container">
			<div class="alert alert-success">
				<p>
					Se ha registrado correctamente el usuario. Puedes hacer <a href="#" class="alert-link">LogOut</a> de la cuenta actual,
					Y entrar a la nueva cuenta.
				</p>
			</div>
		</div>
	</body>
</html>
 