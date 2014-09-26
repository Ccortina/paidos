<%-- 
    Document   : Consultation
    Created on : Sep 24, 2014, 3:22:13 PM
    Author     : Carlos Cortina
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>

<jsp:include page="../Includes/header.jsp"/>

<!-- Files for data tables function-->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />
<c:url var="dtModCSS" value="/resources/CSS/DataTables/datatables.mod.css" />

<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="consultationJs" value="/resources/js/Income/Consultation.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="row">
    <div class="col-sm-12">
        <table id="tblConsultationCostAbstract" class="row-border hover">
            <thead>
                <th>Fecha</th>
                <th>A. Paterno</th>
                <th>A. Materno</th>
                <th>Nombre</th>
                <th>Estatus</th>
                <th>Total</th>
                <th>Resto</th>
            </thead>
        </table>
    </div>
</div>

<script src="${dataTablesJS}" type="text/javascript"></script>

<script src="${momentJs}" type="text/javascript"></script>

<script src="${bvJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>

<script src="${utilityJs}" type="text/javascript"></script>

<script src="${consultationJs}" type="text/javascript"></script>