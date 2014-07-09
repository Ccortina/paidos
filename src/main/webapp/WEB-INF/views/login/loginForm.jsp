<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="pageTitle" value="Please Login" scope="request"/>

<jsp:include page="../Includes/header.jsp"/>

    <body>
    	<c:url value="/login" var="loginUrl"/>
		<div class="jumbotron">
		<div class="container">
			<form action="${loginUrl}" method="post">
		    		<c:if test="${param.error != null}">
		        		<div class="alert alert-danger">
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
		        	<button id="submit" class="btn btn-default" name="submit" type="submit">Login</button>
			</form>
		</div>
		</div>
	</body>
</html>