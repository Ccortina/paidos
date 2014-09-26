<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en"></html>
<head>
<title>Paidos Demo <c:out value="${pageTitle}" />
</title>

<meta http-equiv="content-type" content="text/html;charset=utf-8" />

<c:url var="cssUrl" value="/resources/CSS/bootstrap.min.css" />
<c:url var="jsUrl" value="/resources/js/bootstrap.min.js" />
<c:url var="jqueryUrl" value="/resources/js/jquery-2-1.0.3.js" />
<c:url var="inputMask" value="/resources/js//JQueryPlugins/jquery.inputmask.js" />

<script src="${jqueryUrl}" type="text/javascript"></script>
<script src="${jsUrl}" type="text/javascript"></script>

<link href="${cssUrl}" rel="stylesheet" />

<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
</head>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<sec:authorize access="authenticated" var="authenticated" />

<body>
    <nav class="navbar-wrapper navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                    <a class="navbar-brand" href="${contextPath}">Paidos Demo</a>
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <div class="collapse navbar-collapse">
                    <sec:authorize access="isAuthenticated()">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        Ingresos<span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${contextPath}/income/consultations">Consultas</a></li>
                                    <li><a href="${contextPath}/income/payment">Pagos</a></li>
                                    <li><a href="${contextPath}/income/recepit">Recibos</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        Pacientes<span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${contextPath}/patients/home">Pacientes</a></li>
                                    <li><a href="${contextPath}/relatives/home">Familiares</a></li>
                                    <li><a href="${contextPath}">Citas</a></li>
                                    <li role="presentation" class="divider"></li>
                                    <li><a href="${contextPath}/patients/immunizationHome">Inmunizaciones</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        Diagnosticos/Tratamientos<span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${contextPath}/diagnostictreatment/cie10Home">Diagnosticos CIE10</a></li>
                                    <li><a href="${contextPath}/diagnostictreatment/diagnosticHome">Diagnosticos</a></li>
                                    <li><a href="${contextPath}/diagnostictreatment/treatmentHome">Tratamientos</a></li>
                                    <li role="presentation" class="divider"></li>
                                    <li role="presentation" class="dropdown-header">Medicamentos</li>
                                    <li><a href="${contextPath}/drug/drugHome">Medicamentos</a></li>
                                    <li><a href="${contextPath}/drug/drugPresentationHome">Presentaciones</a></li>
                                    <li><a href="${contextPath}/drug/drugApplicationMethodHome">Modos de Aplicacion</a></li>
                                    <li><a href="${contextPath}/drug/drugAdministrationunitHome">Unidades de Administración</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        Catalogos<span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="${contextPath}/catalogs/imunizationHome">Inmunizaciones</a></li>
                                    <li><a href="${contextPath}/catalogs/laboratoryHome">Lab. y gabinete</a></li>
                                    <li><a href="${contextPath}/catalogs/birthMethodHome">Tipos de Nacimientos</a></li>
                                    <li><a href="${contextPath}/catalogs/relationshipHome">Parentescos</a></li>
                                    <li role="presentation" class="divider"></li>
                                    <li><a href="${contextPath}/catalogs/consultationMeasureHome">Consultas - Medidas</a></li>
                                    <li><a href="${contextPath}/catalogs/activityHome">Actividades</a></li>
                                    <li><a href="${contextPath}/catalogs/holydayHome">Dias inhabiles</a></li>
                                    <li role="presentation" class="divider"></li>
                                    <li><a href="${contextPath}/catalogs/appointmentStatusHome">Estatus cita</a></li>
                                    <li><a href="${contextPath}/catalogs/documentHome">Categoria de documentos</a></li>
                                    <li role="presentation" class="divider"></li>
                                    <li><a href="${contextPath}/catalogs/directoryHome">Directorio</a></li>
                                    <li role="presentation" class="divider"></li>
                                    <li><a href="${contextPath}/catalogs/userHome">Usuario</a></li>
                                </ul>
                            </li>
                            <li><a href="${contextPath}/reports">Reportes</a></li>
                        </ul>
                    </sec:authorize>

                    <ul class="nav navbar-nav navbar-right">
                        <sec:authorize access="hasRole('Administrador')">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">Configuration <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${contextPath}/signup/form">Register New Users</a></li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">    
                            <li class="navbar-text">
                                <div>
                                    <sec:authentication property="principal.username" />
                                </div>
                            </li>
                            <li><a href="${logoutUrl}">Logout</a></li>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <c:url var="loginUrl" value="/login" />
                            <li class="active"><a href="${loginUrl}">Login</a></li>
                        </sec:authorize>
                    </ul>
                </div>
            </div>
        </div>	
    </nav>