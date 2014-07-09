<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../Includes/header.jsp"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

		<div class="container">
			<nav class="navbar navbar-default" role="navigation">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
   					<ul class="nav navbar-nav">
   						<li><a href="./new">Agregar Familiar</a></li>
   					</ul>
   				</div>
			</nav>
				<c:if test="${empty relatives}">
					<div class="alert alert-error">
						<strong>No hay familiares registrados</strong>
					</div>
				</c:if>	
				<c:if test="${empty addedRelative}">
					<div class="alert alert-success">
						<strong>${addedRelative}</strong>
					</div>
				</c:if>
				<table class="table table-condensed">
					<tr>
						<th>A. Paterno</th>
						<th>A. Materno</th>
						<th>Nombre</th>
						<th>RFC</th>
						<th>Celular</th>
					</tr>
					<c:forEach var="relative" items="${relatives}">
						<tr>
							<td>${relative.fatherLastName}</td>
							<td>${relative.motherLastName}</td>
							<td>${relative.firstName} ${patient.secondName}</td>
							<td>${relative.rfc}</td>
							<td>${relative.cellPhone}</td>
						</tr>
					</c:forEach>
				</table>
		</div>
	</body>
</html>