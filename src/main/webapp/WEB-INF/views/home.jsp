<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
  		<title>PaidosDemo: <c:out value="${pageTitle}"/> </title>
    		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
        
        	<c:url var="cssUrl" value="/resources/CSS/bootstrap.min.css"/>
        	<link href="${cssUrl}" rel="stylesheet"/>
        	<style>
        	  	body 
        	  	{
           	 		padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
          	 	}
        	</style>
	</head>
    <body>
    	<c:set var="pageTitle" value="Please Login" scope="request"/>
    	<c:url value="/login" var="loginUrl"/>
		<div class="jumbotron">
		<div class="container">
        	<c:if test="${message != null}">
            	<div class="alert alert-success" id="message"><c:out value="${message}"/></div>
        	</c:if>
        	<h1 id="title"><c:out value="${pageTitle}"/></h1>
		
			<form action="${loginUrl}" method="post">
		    		<c:if test="${param.error != null}">
		        		<div class="alert alert-error">
		            		Failed to login.
		            		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
		              			Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
		            		</c:if>
		        		</div>
		    		</c:if>
		    		<c:if test="${param.logout != null}">
		        		<div class="alert alert-success">
		            		You have been logged out.
		        		</div>
		    		</c:if>
		    		<div class="form-group">
		    			<label for="username">Username</label>
		    			<input type="text" id="username" name="username" class="form-control" placeholder="Username"/>
		    		</div>
		    		<div class="form-group">
		    			<label for="password">Password</label>
		    			<input type="password" id="password" name="password" class="form-control" placeholder="Password"/>
		    		</div>
		        	<buttom id="submit" class="btn btn-default" name="submit" type="submit">Login</buttom>
			</form>
		</div>
		</div>
	</body>
</html>