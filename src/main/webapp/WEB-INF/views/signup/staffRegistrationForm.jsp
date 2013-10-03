<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message var="msgAllFieldsRequired" code="newUserRegistration.message.allFieldsRequired" />

<html>
	<head>
  		<title>PaidosDemo: <c:out value="${pageTitle}"/> </title>
    		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
        
        	<c:url var="cssUrl" value="/resources/CSS/bootstrap.min.css"/>
        	<c:url var="jsUrl" value="/resources/js/bootstrap.min.js"/>
        	<c:url var="jqueryUrl" value="/resources/js/jQuery.js"/>
        	<link href="${cssUrl}" rel="stylesheet"/>
        	<link href="${jsUrl}" rel="stylesheet"/>
        	<link href="${jqueryUrl}" rel="stylesheet"/>
        	
        	<style>
        	  	body 
        	  	{
           	 		padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
          	 	}
        	</style>
	</head>
    <body>
    	<c:set var="pageTitle" value="Staff Registration" scope="request"/>
		<div class="container">
        	<h1 id="title"><c:out value="${pageTitle}"/></h1>
		
			<form:form action="./new" method="post" modelAttribute="form">
			
				<!-- Display global errors -->
				<form:errors path="*">
					<div class="alert alert-danger">
						<spring:message code = "error.global" />
					</div>
				</form:errors>
				
				<div class="form-group">
	    			<label for="username" class="col-lg-2 control-label">Username</label>
	    			<div class="col-lg-10">
	    				<form:input id="username" path="username" cssClass="form-control" placeholder="Username" cssStyle="width: 30%"/>
	    			</div>
	    			<div>
			    		<form:errors path="username">
			    			<div class="alert alert-danger"><form:errors path="username" htmlEscape="false" /></div>
			    		</form:errors>
		    		</div>
	    		</div>
	    		<div class="form-group">
	    			<label for="password" class="col-lg-2 control-label">Password</label>
	    			<div class="col-lg-10">	
	    				<form:password id="password" class="form-control" placeholder="Password" path="password" cssStyle="width: 30%"/>
	    			</div>
	    		</div>
	    		<div>
		    		<form:errors path="password">
		    			<div class="alert alert-danger"><form:errors path="password" htmlEscape="false" /></div>
		    		</form:errors>
	    		</div>
	    		<div class="form-group">
	    			<label for="confirmPassword" class="col-lg-2 control-label">Confirm Password</label>
	    			<div class="col-lg-10">	
	    				<form:password id="confirmPassword" path="confirmPassword" class="form-control" placeholder="Password" cssStyle="width: 30%"/>
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label for="email" class="col-lg-2 control-label">Email</label>
	    			<div class="col-lg-10">	
	    				<form:input id="email" path="email" class="form-control" placeholder="Email" cssStyle="width: 30%"/>
	    			</div>
	    		</div>
	    		<div>
		    		<form:errors path="email">
		    			<div class="alert alert-danger"><form:errors path="email" htmlEscape="false" /></div>
		    		</form:errors>
	    		</div>
	    		<div class="form-group">
	    			<label for="firstName" class="col-lg-2 control-label">FirstName</label>
	    			<div class="col-lg-10">	
	    				<form:input id="firstName" path="firstName" class="form-control" placeholder="First Name" cssStyle="width: 30%"/>
	    			</div>
	    		</div>
	    		<div>
		    		<form:errors path="firstName">
		    			<div class="alert alert-danger"><form:errors path="firstName" htmlEscape="false" /></div>
		    		</form:errors>
	    		</div>
	    		<div class="form-group">
	    			<label for="lastName" class="col-lg-2 control-label">Last Name</label>
	    			<div class="col-lg-10">	
	    				<form:input id="lastName" path="lastName" class="form-control" placeholder="Last Name" cssStyle="width: 30%"/>
	    			</div>
	    		</div>
	    		<div>
		    		<form:errors path="lastName">
		    			<div class="alert alert-danger"><form:errors path="lastName" htmlEscape="false" /></div>
		    		</form:errors>
	    		</div>
	    		<div class="form-group">
	    			<label for="phone" class="col-lg-2 control-label">Phone</label>
	    			<div class="col-lg-10">	
	    				<form:input id="phone" path="phone" class="form-control" placeholder="Phone" cssStyle="width: 30%"/>
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label for="cellPhone" class="col-lg-2 control-label">Cellphone</label>
	    			<div class="col-lg-10">	
	    				<form:input id="cellPhone" path="cellPhone" class="form-control" placeholder="CellPhone" cssStyle="width: 30%"/>
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label for="professionalNumber" class="col-lg-2 control-label">ProfessionalNumber</label>
	    			<div class="col-lg-10">	
	    				<form:input id="professionalNumber" path="professionalNumber" class="form-control" placeholder="Cedula Profesional" cssStyle="width: 30%"/>
	    			</div>
	    		</div>
	    		<div>
		    		<form:errors path="professionalNumber">
		    			<div class="alert alert-danger"><form:errors path="professionalNumber" htmlEscape="false" /></div>
		    		</form:errors>
		    	</div>	
	    		<div class="form-group">
	    			<div class="col-lg-offset-2 col-lg10">
	        			<button id="submit" class="btn btn-default" name="submit" type="submit">Register User</button>
	        		</div>
	        	</div>
			</form:form>
		</div>
	</body>
</html>