<%-- 
    Document   : GraphsCanvas
    Created on : Sep 14, 2014, 4:30:28 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../Includes/header.jsp"/>

<!-- Javascript needed for graphics -->
<c:url var="navControlCSS" value="/resources/CSS/Flot/iconfontsFico.css" /> 
<c:url var="flotJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.min.js" />
<c:url var="navigateJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.navigate.min.js" />
<c:url var="tooltipJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.tooltip.min.js" />
<c:url var="navControlJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.navigationControl.js" />
<c:url var="axisLabelsJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.axislabels.js" />

<c:url var="graphsJs" value="/resources/js/Graphs/Graphs.js" />

<link href="${navControlCSS}" rel="stylesheet" />
<style type="text/css">
        .icon {
            vertical-align: middle;
        }
</style>

<input type="hidden" id="graphType" value="${graphType}" />
<h1 id="graphTitle"></h1>
<br>
<div class="row">
    <div class="col-sm-4">
        <h3>Paciente:</h3> <h4>${patient}</h4>
    </div>
    <div class="col-sm-4">
        <h3>Sexo :</h3> <h4>${gender}</h4>
    </div>
</div>
<div id="tooltip">
</div>
<div class="row">
    <div class="col-sm-8 col-sm-offset-2">
    <div id="graphPlaceholder" style="width:800px;height:800px">
    </div>
</div>
    

<script src="${flotJs}" type="text/javascript"></script>
<script src="${navigateJs}" type="text/javascript"></script>
<script src="${tooltipJs}" type="text/javascript"></script>
<script src="${navControlJs}" type="text/javascript"></script>
<script src="${axisLabelsJs}" type="text/javascript"></script>
<script src="${graphsJs}" type="text/javascript"></script>