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

<!-- Files for data tables function-->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesToolsJS" value="/resources/js/BootstrapPlugins/DataTables/dataTables.tableTools.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />
<c:url var="dataTablesToolsCSS" value="/resources/CSS/DataTables/dataTables.tableTools.min.css" />
<c:url var="dtModCSS" value="/resources/CSS/DataTables/datatables.mod.css" />

<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="reportsJs" value="/resources/js/Reports/ReportsHome.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dataTablesToolsCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="col-sm-4">
                <div class="form-group">
                    <label>Reporte:</label>
                    <select id="selectReport" class="form-control">
                        <option value="1">Numero de consultas mensuales</option>
                        <option value="2">Uso de diagnosticos</option>
                        <option value="3">Relacion Consultas</option>
                        <option value="4">Ingresos</option>
                        <option value="5">Relacion de recibos</option>
                        <option value="6">Resumen global ingresos</option>
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
        <div class="row forHiding" id="divTableReportsOptions">
            <form id="formDateRange">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label>Desde:</label>
                        <input type="text" class="form-control" id="inputBeginingDate" name="start" />
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label>Hasta:</label>
                        <input type="text" class="form-control" id="inputEndingDate" name="end" />
                    </div>
                </div>
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-primary">Ver Resultados</button>
                </div>
            </form>
        </div>
        <div class="row forHiding" id="divReport4Options">
            <form id="formOptionR4">
                <div class="col-sm-2">
                <div class="radio">
                    <label>
                        <input type="radio" name="radioOptionR4" value="1" checked>
                        Detalles
                    </label>
                </div>
                </div>
                <div class="col-sm-2">
                <div class="radio">
                    <label>
                        <input type="radio" name="radioOptionR4" value="2">
                        Totales
                    </label>
                </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="row forHiding" id="divTable3">
    <div class="col-sm-12">
        <table id="tblReport3"></table>
    </div>
</div>
<div class="row forHiding" id="divTable4">
    <div class="col-sm-12">
        <table id="tblReport4"></table>
    </div>
</div>
<div class="row forHiding" id="divTable41">
    <div class="col-sm-12">
        <table id="tblReport41"></table>
    </div>
</div>
<div class="row forHiding" id="divTable5">
    <div class="col-sm-12">
        <table id="tblReport5" class="row-border hover"></table>
    </div>
</div>
<div class="row forHiding" id="divTable6">
    <div class="col-sm-12">
        <table id="tblReport6" class="row-border hover"></table>
    </div>
</div>
<div class="row forHiding" id="divGraph">
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

<script src="${dataTablesJS}" type="text/javascript"></script>
<script src="${dataTablesToolsJS}" type="text/javascript"></script>

<script src="${momentJs}" type="text/javascript"></script>

<script src="${bvJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>

<script src="${utilityJs}" type="text/javascript"></script>

<script src="${reportsJs}" type="text/javascript"></script>