<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../Includes/header.jsp"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

		<div class="container">
			<nav class="navbar navbar-default" role="navigation">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
   					<ul class="nav navbar-nav">
   						<li><a href="./new">Agregar Paciente</a></li>
   						<li><a href="#">Expediente</a></li>
   					</ul>
   				</div>
			</nav>	
				<c:if test="${empty patients}">
					<div class="alert alert-warning">
						No hay pacientes registrados
					</div>
				</c:if>
				<table class="table table-condensed">
					<tr>
						<th>A. Paterno</th>
						<th>A. Materno</th>
						<th>Nombre</th>
						<th>Fecha Nacimiento</th>
						<th>Sexo</th>
						<th>Inactivo</th>
					</tr>
					<c:forEach var="patient" items="${patients}">
						<tr>
							<td>${patient.fatherLastName}</td>
							<td>${patient.motherLastName}</td>
							<td>${patient.firstName} ${patient.secondName}</td>
							<td>${patient.birthday}</td>
							<td>${patient.sex}</td>
							<td>${patient.active}</td>
						</tr>
					</c:forEach>
				</table>
		</div>
	</body>
</html>