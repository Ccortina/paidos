<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../Includes/header.jsp"/>
<c:set var="pageTitle" value="Registrar PAciente" scope="request"/>

<c:url var="flexigrid" value="/resources/js/flexigrid.js" />
<c:url var="flexigridCss" value="/resources/CSS/flexigrid.css" />
<link href="${flexigridCss}" rel="stylesheet" />
<script src="${flexigrid}" type="text/javascript" ></script>

<script type="text/javascript">
 				$(".flexme").flexigrid();
 </script>

<spring:message var="msgAllFieldsRequired" code="newUserRegistration.message.allFieldsRequired" />

		<div class="container">
			<nav class="navbar navbar-default" role="navigation">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
   					<ul class="nav navbar-nav">
   						<li><a href="new">Agregar Paciente</a></li>
   						<li><a href="#">Expediente</a></li>
   					</ul>
   				</div>
			</nav>	
		</div>
		<div class="container">
			<h1 id="title"><c:out value="${pageTitle}"/></h1>
		
			<form:form action="./addNew" method="post" modelAttribute="form">
			
				<!-- Display global errors -->
				<form:errors path="*">
					<div class="alert alert-danger">
						<spring:message code = "error.global" />
					</div>
				</form:errors>
				
				<div class="form-group">
	    			<label for="firstName" class="col-lg-2 control-label">Primer Nombre</label>
	    			<div class="col-lg-10">
	    				<form:input id="firstName" path="firstName" cssClass="form-control" placeholder="Primer Nombre"/>
	    			</div>
	    			<div>
			    		<form:errors path="firstName">
			    			<div class="alert alert-danger"><form:errors path="firstName" htmlEscape="false" /></div>
			    		</form:errors>
		    		</div>
	    		</div>
	    		<div class="form-group">
	    			<label for="secondName" class="col-lg-2 control-label">Segundo Nombre</label>
	    			<div class="col-lg-10">	
	    				<form:input id="secondName" class="form-control" placeholder="Segundo Nombre" path="secondName"/>
	    			</div>
	    		</div>
	    		<div>
		    		<form:errors path="secondName">
		    			<div class="alert alert-danger"><form:errors path="secondName" htmlEscape="false" /></div>
		    		</form:errors>
	    		</div>
	    		<div class="form-group">
	    			<label for="fatherLastName" class="col-lg-2 control-label">Apellido Paterno</label>
	    			<div class="col-lg-10">	
	    				<form:input id="fatherLastName" path="fatherLastName" class="form-control" placeholder="Apellido Paterno"/>
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label for="motherLastName" class="col-lg-2 control-label">Apellido Materno</label>
	    			<div class="col-lg-10">	
	    				<form:input id="motherLastName" path="motherLastName" class="form-control" placeholder="Apellido Materno"/>
	    			</div>
	    		</div>
	    		<div>
		    		<form:errors path="motherLastName">
		    			<div class="alert alert-danger"><form:errors path="motherLastName" htmlEscape="false" /></div>
		    		</form:errors>
	    		</div>
	    		<div class="form-group">
	    			<label for="nickname" class="col-lg-2 control-label">Apodo</label>
	    			<div class="col-lg-10">	
	    				<form:input id="nickname" path="nickname" placeholder="Apodo" class="form-control"/>
	    			</div>
	    		</div>
	    		<div>
		    		<form:errors path="nickname">
		    			<div class="alert alert-danger"><form:errors path="nickname" htmlEscape="false" /></div>
		    		</form:errors>
	    		</div>
	    		<div class="form-group">
	    			<label for="sex" class="col-lg-2 control-label">Sexo</label>
	    			<div class="col-lg-10">	
	    				<form:select id="sex" path="sex" class="form-control" >
	    					<form:option value="masculino">Maculino</form:option>
	    					<form:option value="femenino">Femenino</form:option>
	    				</form:select>
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label for="birthday" class="col-lg-2 control-label">Cumpleaños</label>
	    			<div class="col-lg-10">	
	    				<form:input id="birthday" path="birthday" class="form-control" placeholder="Cumpleaños" />
	    			</div>
	    		</div>
	    		<div class="form-group">
	    			<label for="relatives" class="col-lg-2 control-label">Relatives</label>
	    			<div class="col-lg-10">	
	    				
	    			</div>
	    		</div>
			</form:form>
		</div>
	</body>
</html>			