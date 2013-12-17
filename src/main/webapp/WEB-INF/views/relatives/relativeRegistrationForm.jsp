<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../Includes/header.jsp"/>
<c:set var="pageTitle" value="Registrar PAciente" scope="request"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

		<div class="container">
			<nav class="navbar navbar-default" role="navigation">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
   					<ul class="nav navbar-nav">
   						<li><a href="./new">Agregar Familiar</a></li>
   					</ul>
   				</div>
			</nav>
		</div>
		<div class="container">
		
			<form:form action="./add" method="post" modelAttribute="form">
			
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
		    				<form:input id="secondName" path="secondName" cssClass="form-control" placeholder="Segundo Nombre"/>
		    			</div>
		    			<div>
				    		<form:errors path="secondName">
				    			<div class="alert alert-danger"><form:errors path="secondName" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="fatherLastName" class="col-lg-2 control-label">Primer Apellido</label>
		    			<div class="col-lg-10">
		    				<form:input id="fatherLastName" path="fatherLastName" cssClass="form-control" placeholder="Primer Apellido"/>
		    			</div>
		    			<div>
				    		<form:errors path="fatherLastName">
				    			<div class="alert alert-danger"><form:errors path="fatherLastName" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="motherLastName" class="col-lg-2 control-label">Segundo Apellido</label>
		    			<div class="col-lg-10">
		    				<form:input id="motherLastName" path="motherLastName" cssClass="form-control" placeholder="Segundo Apellido"/>
		    			</div>
		    			<div>
				    		<form:errors path="motherLastName">
				    			<div class="alert alert-danger"><form:errors path="motherLastName" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="curp" class="col-lg-2 control-label">CURP</label>
		    			<div class="col-lg-10">
		    				<form:input id="curp" path="curp" cssClass="form-control" placeholder="CURP"/>
		    			</div>
		    			<div>
				    		<form:errors path="curp">
				    			<div class="alert alert-danger"><form:errors path="curp" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="occupation" class="col-lg-2 control-label">Ocupacion</label>
		    			<div class="col-lg-10">
		    				<form:input id="occupation" path="occupation" cssClass="form-control" placeholder="Ocupacion"/>
		    			</div>
		    			<div>
				    		<form:errors path="occupation">
				    			<div class="alert alert-danger"><form:errors path="occupation" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="rfc" class="col-lg-2 control-label">RFC</label>
		    			<div class="col-lg-10">
		    				<form:input id="rfc" path="rfc" cssClass="form-control" placeholder="RFC"/>
		    			</div>
		    			<div>
				    		<form:errors path="rfc">
				    			<div class="alert alert-danger"><form:errors path="rfc" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="homePhone" class="col-lg-2 control-label">Telefono de Casa</label>
		    			<div class="col-lg-10">
		    				<form:input id="homePhone" path="homePhone" cssClass="form-control" placeholder="Telefono de Casa"/>
		    			</div>
		    			<div>
				    		<form:errors path="homePhone">
				    			<div class="alert alert-danger"><form:errors path="homePhone" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="officePhone" class="col-lg-2 control-label">Telefono de Oficina</label>
		    			<div class="col-lg-10">
		    				<form:input id="officePhone" path="officePhone" cssClass="form-control" placeholder="Telefono de Oficina"/>
		    			</div>
		    			<div>
				    		<form:errors path="officePhone">
				    			<div class="alert alert-danger"><form:errors path="officePhone" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="officeExt" class="col-lg-2 control-label">Extension</label>
		    			<div class="col-lg-10">
		    				<form:input id="officeExt" path="officeExt" cssClass="form-control" placeholder="Extension"/>
		    			</div>
		    			<div>
				    		<form:errors path="officeExt">
				    			<div class="alert alert-danger"><form:errors path="officeExt" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="cellPhone" class="col-lg-2 control-label">Celular</label>
		    			<div class="col-lg-10">
		    				<form:input id="cellPhone" path="cellPhone" cssClass="form-control" placeholder="Celular"/>
		    			</div>
		    			<div>
				    		<form:errors path="cellPhone">
				    			<div class="alert alert-danger"><form:errors path="cellPhone" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="otherPhone" class="col-lg-2 control-label">Otro Telefono</label>
		    			<div class="col-lg-10">
		    				<form:input id="otherPhone" path="otherPhone" cssClass="form-control" placeholder="Otro Telefono"/>
		    			</div>
		    			<div>
				    		<form:errors path="otherPhone">
				    			<div class="alert alert-danger"><form:errors path="otherPhone" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="email" class="col-lg-2 control-label">Email</label>
		    			<div class="col-lg-10">
		    				<form:input id="email" path="email" cssClass="form-control" placeholder="Email"/>
		    			</div>
		    			<div>
				    		<form:errors path="email">
				    			<div class="alert alert-danger"><form:errors path="email" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="religion" class="col-lg-2 control-label">Religion</label>
		    			<div class="col-lg-10">
		    				<form:input id="religion" path="religion" cssClass="form-control" placeholder="Religion"/>
		    			</div>
		    			<div>
				    		<form:errors path="religion">
				    			<div class="alert alert-danger"><form:errors path="religion" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="notes" class="col-lg-2 control-label">Notas</label>
		    			<div class="col-lg-10">
		    				<form:input id="notes" path="notes" cssClass="form-control" placeholder="Notas"/>
		    			</div>
		    			<div>
				    		<form:errors path="notes">
				    			<div class="alert alert-danger"><form:errors path="notes" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="cp" class="col-lg-2 control-label">Codigo Postal</label>
		    			<div class="col-lg-10">
		    				<form:input id="cp" path="cp" cssClass="form-control" placeholder="CodigoPstal"/>
		    			</div>
		    			<div>
				    		<form:errors path="cp">
				    			<div class="alert alert-danger"><form:errors path="cp" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="street" class="col-lg-2 control-label">Calle</label>
		    			<div class="col-lg-10">
		    				<form:input id="street" path="street" cssClass="form-control" placeholder="Calle"/>
		    			</div>
		    			<div>
				    		<form:errors path="street">
				    			<div class="alert alert-danger"><form:errors path="street" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="number" class="col-lg-2 control-label">Numero</label>
		    			<div class="col-lg-10">
		    				<form:input id="number" path="number" cssClass="form-control" placeholder="Numero"/>
		    			</div>
		    			<div>
				    		<form:errors path="number">
				    			<div class="alert alert-danger"><form:errors path="number" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="colony" class="col-lg-2 control-label">Colonia</label>
		    			<div class="col-lg-10">
		    				<form:input id="colony" path="colony" cssClass="form-control" placeholder="Colonia"/>
		    			</div>
		    			<div>
				    		<form:errors path="colony">
				    			<div class="alert alert-danger"><form:errors path="colony" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="city" class="col-lg-2 control-label">Ciudad</label>
		    			<div class="col-lg-10">
		    				<form:input id="city" path="city" cssClass="form-control" placeholder="Ciudad"/>
		    			</div>
		    			<div>
				    		<form:errors path="city">
				    			<div class="alert alert-danger"><form:errors path="city" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="state" class="col-lg-2 control-label">Estado</label>
		    			<div class="col-lg-10">
		    				<form:input id="state" path="state" cssClass="form-control" placeholder="Estado"/>
		    			</div>
		    			<div>
				    		<form:errors path="state">
				    			<div class="alert alert-danger"><form:errors path="state" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="country" class="col-lg-2 control-label">Pais</label>
		    			<div class="col-lg-10">
		    				<form:input id="country" path="country" cssClass="form-control" placeholder="Pais"/>
		    			</div>
		    			<div>
				    		<form:errors path="country">
				    			<div class="alert alert-danger"><form:errors path="country" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="ginecologist" class="col-lg-2 control-label">Ginecologo</label>
		    			<div class="col-lg-10">
		    				<form:input id="ginecologist" path="ginecologist" cssClass="form-control" placeholder="Ginecologo"/>
		    			</div>
		    			<div>
				    		<form:errors path="ginecologist">
				    			<div class="alert alert-danger"><form:errors path="ginecologist" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	<div class="form-group">
			    		<label for="recommendedBy" class="col-lg-2 control-label">Recomendado</label>
		    			<div class="col-lg-10">
		    				<form:input id="recommendedBy" path="recommendedBy" cssClass="form-control" placeholder="Recomendado"/>
		    			</div>
		    			<div>
				    		<form:errors path="recommendedBy">
				    			<div class="alert alert-danger"><form:errors path="recommendedBy" htmlEscape="false" /></div>
				    		</form:errors>
			    		</div>
			    	</div>
			    	
			    	<div class="form-group">
	    			<div class="col-lg-offset-2 col-lg10">
	        			<button id="submit" class="btn btn-default" name="submit" type="submit">Guardar</button>
	        		</div>
	        	</div>
		    	</form:form>
	    	</div>
	</body>
</html>