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

    <script src="${jqueryUrl}" type="text/javascript"></script>
    <script src="${jsUrl}" type="text/javascript"></script>

    <link href="${cssUrl}" rel="stylesheet" />

    <style>
        body {
            padding-top: 60px;
            /* 60px to make the container go all the way to the bottom of the topbar */
        }

        .loader {
            position: fixed;
            left: 0px;
            top: 0px;
            width: 100%;
            height: 100%;
            z-index: 9999;
            background: url('${pageContext.request.contextPath}/resources/images/loading_1.gif') 50% 50% no-repeat rgb(249,249,249);
            opacity: .5;
        }
    </style>
</head>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<sec:authorize access="authenticated" var="authenticated" />

<body>
    <div class="loader" id='loadingmessage' style='display:none'></div>
    <nav class="navbar-wrapper navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${contextPath}">Paidos Demo</a>
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <div class="collapse navbar-collapse">
                    <sec:authorize access="isAuthenticated()">
                        <ul class="nav navbar-nav">
                            <sec:authorize access="hasAnyRole('Doctor','Modulo_Ingresos_1')">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        Ingresos<span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${contextPath}/income/consultations">Consultas</a></li>
                                        <li><a href="${contextPath}/income/payment">Pagos</a></li>
                                        <li><a href="${contextPath}/income/receipt">Recibos</a></li>
                                    </ul>
                                </li>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole('Doctor','Modulo_Pacientes_1')">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        Pacientes<span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <sec:authorize access="hasAnyRole('Doctor','Pacientes_Pacientes_1')">
                                            <li><a href="${contextPath}/patients/home">Pacientes</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Pacientes_Familiares_1')">
                                            <li><a href="${contextPath}/relatives/home">Familiares</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Pacientes_Citas_1')">
                                            <li><a href="${contextPath}">Citas</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Pacientes_Inmunizaciones_1')">
                                            <li role="presentation" class="divider"></li>
                                            <li><a href="${contextPath}/patients/immunizationHome">Inmunizaciones</a></li>
                                            </sec:authorize>
                                    </ul>
                                </li>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole('Doctor','Modulo_Diagnosticos_1')">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        Diagnosticos/Tratamientos<span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_CIE_1')">
                                            <li><a href="${contextPath}/diagnostictreatment/cie10Home">Diagnosticos CIE10</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Diagnosticos_1')">
                                            <li><a href="${contextPath}/diagnostictreatment/diagnosticHome">Diagnosticos</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Tratamientos_1')">
                                            <li><a href="${contextPath}/diagnostictreatment/treatmentHome">Tratamientos</a></li>
                                            </sec:authorize>

                                        <li role="presentation" class="divider"></li>
                                        <li role="presentation" class="dropdown-header">Medicamentos</li>
                                            <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Medicamentos_1')">
                                            <li><a href="${contextPath}/drug/drugHome">Medicamentos</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Presentaciones_1')">
                                            <li><a href="${contextPath}/drug/drugPresentationHome">Presentaciones</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Modos de administracion_1')">
                                            <li><a href="${contextPath}/drug/drugApplicationMethodHome">Modos de Aplicacion</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Unidades de Administracion_1')">
                                            <li><a href="${contextPath}/drug/drugAdministrationunitHome">Unidades de Administración</a></li>
                                            </sec:authorize>
                                    </ul>
                                </li>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole('Doctor','Modulo_Catalogos_1')">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        Catalogos<span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <sec:authorize access="hasAnyRole('Doctor','Catalogos_Inmunizaciones_1')">
                                            <li><a href="${contextPath}/catalogs/imunizationHome">Inmunizaciones</a></li>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole('Doctor','Catalogos_Laboratorio y Gabinete_1')">
                                            <li><a href="${contextPath}/catalogs/laboratoryHome">Lab. y gabinete</a></li>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole('Doctor','Catalogos_Tipos de Nacimiento_1')">
                                            <li><a href="${contextPath}/catalogs/birthMethodHome">Tipos de Nacimientos</a></li>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole('Doctor','Catalogos_Parentescos_1')">
                                            <li><a href="${contextPath}/catalogs/relationshipHome">Parentescos</a></li>
                                        </sec:authorize>

                                        <li role="presentation" class="divider"></li>

                                        <sec:authorize access="hasAnyRole('Doctor','Catalogos_Medidas de Consulta_1')">
                                            <li><a href="${contextPath}/catalogs/consultationMeasureHome">Consultas - Medidas</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Catalogos_Inmunizaciones_1')">
                                            <li><a href="${contextPath}/catalogs/activityHome">Actividades</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Catalogos_Actividades_1')">
                                            <li><a href="${contextPath}/catalogs/holydayHome">Dias inhabiles</a></li>
                                            </sec:authorize>
                                        <li role="presentation" class="divider"></li>
                                            <sec:authorize access="hasAnyRole('Doctor','Catalogos_Estatus Citas_1')">
                                            <li><a href="${contextPath}/catalogs/appointmentStatusHome">Estatus cita</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Catalogos_Categoria de Documentos_1')">
                                            <li><a href="${contextPath}/catalogs/documentHome">Categoria de documentos</a></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Catalogos_Inmunizaciones_1')">
                                            <li role="presentation" class="divider"></li>
                                            </sec:authorize>
                                            <sec:authorize access="hasAnyRole('Doctor','Catalogos_Directorio_1')">
                                            <li><a href="${contextPath}/catalogs/directoryHome">Directorio</a></li>
                                            </sec:authorize>
                                        <li role="presentation" class="divider"></li>
                                    </ul>
                                </li>
                            </sec:authorize>
                            <sec:authorize access="hasAnyRole('Doctor','Modulo_Reportes_1')">
                                <li><a href="${contextPath}/reports">Reportes</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasRole('Doctor')">
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        Administracion<span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${contextPath}/administration/userHome">Usuario</a></li>
                                        <li><a href="${contextPath}/administration/newUser">Registrar Nuevo Usuario</a></li>
                                        <li><a href="${contextPath}/administration/permissionsHome">Permisos</a></li>
                                    </ul>
                                </li>
                            </sec:authorize>
                        </ul>
                    </sec:authorize>

                    <ul class="nav navbar-nav navbar-right">
                        <sec:authorize access="isAuthenticated()">    
                            <li class="navbar-text">
                                <div>
                                    <sec:authentication property="principal.username" />
                                </div>
                            </li>
                            <li><a href="<c:url value="/j_spring_security_logout" />">Cerrar Sesion</a></li>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <c:url var="loginUrl" value="/login" />
                            <li class="active"><a href="${loginUrl}">Iniciar Sesion</a></li>
                            </sec:authorize>
                    </ul>
                </div>
            </div>
        </div>	
    </nav>
            