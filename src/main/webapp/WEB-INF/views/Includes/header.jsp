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
				<a class="navbar-brand" href="#">Paidos Demo</a>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<div class="collapse navbar-collapse">
					<c:choose>
						<c:when test="${authenticated}">
							<ul class="nav navbar-nav">
								<li class="dropdown">
									<a class="dropdown-toggle" data-toggle="dropdown" href="#">
										Patients <span class="caret"></span>
									</a>
									<ul class="dropdown-menu">
										<li><a href="${contextPath}/patients/home">Pacientes</a></li>
										<li><a href="${contextPath}/relatives/home">Familiares</a></li>
									</ul>
								</li>
								<li><a href="#">Link</a></li>
							</ul>
						</c:when>
					</c:choose>
					<c:url var="logoutUrl" value="/logout" />
					<ul class="nav navbar-nav navbar-right">
						<c:choose>
							<c:when test="${authenticated}">
								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#">Configuration <span
										class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="${contextPath}/signup/form">Register New Users</a></li>
									</ul>
								</li>
								<li class="navbar-text"><div>
										Welcome
										<sec:authentication property="principal.username" />
									</div></li>
								<li><a href="${logoutUrl}">Logout</a></li>
							</c:when>
							<c:otherwise>
								<c:url var="loginUrl" value="/login/form" />
								<li class="active"><a href="${loginUrl}">Login</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>	
	</nav>