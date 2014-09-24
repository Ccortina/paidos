<%-- 
    Document   : ReportsHome
    Created on : Sep 16, 2014, 8:00:49 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../Includes/header.jsp"/>

<c:url var="flotJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.min.js" />
<c:url var="navigateJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.navigate.min.js" />
<c:url var="tooltipJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.tooltip.min.js" />
<c:url var="navControlJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.navigationControl.js" />
<c:url var="axisLabelsJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.axislabels.js" />
<c:url var="categoriesJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.categories.min.js" />

<c:url var="reportsJs" value="/resources/js/Reports/ReportsHome.js" />

<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="col-sm-4">
                <div class="form-group">
                    <label>Reporte:</label>
                    <select id="selectReport" class="form-control">
                        <option value="1">Numero de consultas mensuales</option>
                        <option value="2">Uso de diagnosticos</option>
                        <option value="3"></option>
                        <option value="4"></option>
                        <option value="5"></option>
                        <option value="6"></option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row forHiding" id="divReport1Options">
            <div class="col-sm-4" >
                <div class="form-group">
                    <label>Año</label>
                    <select id="selectGraph1Year" class="form-control selectYear"></select>
                </div>
            </div>
            <div class="col-sm-4">
                <button type="button" class="btn btn-primary" onclick="renderReport1()">Mostrar Reporte</button>
            </div>
        </div>
        <div class="row"></div>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <div id="tooltip"></div>
        <div id="graphPlaceholder" style="width:600px;height:600px"></div>
    </div>
</div>

<script src="${flotJs}" type="text/javascript"></script>
<script src="${navigateJs}" type="text/javascript"></script>
<script src="${tooltipJs}" type="text/javascript"></script>
<script src="${navControlJs}" type="text/javascript"></script>
<script src="${axisLabelsJs}" type="text/javascript"></script>
<script src="${categoriesJs}" type="text/javascript"></script>

<script src="${reportsJs}" type="text/javascript"></script>