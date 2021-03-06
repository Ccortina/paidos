<%-- 
    Document   : DrugHome
    Created on : Aug 5, 2014, 2:05:55 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>

<jsp:include page="../Includes/header.jsp"/>

<!-- Files for data tables function-->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />

<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="drugHomeJs" value="/resources/js/Drug/DrugHome.js" />
<c:url var="newDrugJs" value="/resources/js/Drug/DrugHomeTabs/NewDrugTab.js" />
<c:url var="newDrugIncompatibilityJs" value="/resources/js/Drug/DrugHomeTabs/NewDrugTab/Incompatibility.js" />
<c:url var="newDrugTreatmentsJs" value="/resources/js/Drug/DrugHomeTabs/NewDrugTab/Treatments.js" />
<c:url var="newDrugCNJs" value="/resources/js/Drug/DrugHomeTabs/NewDrugTab/CommercialName.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div classs="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Medicamentos</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <ul id="drugTabMenu" class="nav nav-tabs">
                              <li class="active"><a href="#tabMain" data-toggle="tab">Medicamentos</a></li>
                              <li><a href="#tabNew" data-toggle="tab">Nuevo</a></li>
                              <li><a href="#tabModify" data-toggle="tab">Modificar</a></li>
                              <li><a href="#tabAdditionalInfo" data-toggle="tab">Informacion Relacionada</a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="tabMain" class="tab-pane active">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblDrug">
                                                <thead>
                                                    <th>Medicamento</th>
                                                    <th>Presentación</th>
                                                    <th>Unidad</th>
                                                    <th>Modo Administracion</th>
                                                    <th>Criterio</th>
                                                    <th>Activo</th>
                                                </thead>
                                            </table> 
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Nuevo medicamento" onclick="newDrug()" />
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Modificar medicamento" onclick="modifyDrug()" />
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-danger" value="Borrar medicamento" onclick="deleteDrug()" />
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Informacion Asociada" onclick="additionalInfo()" />
                                        </div>
                                    </div>
                                </div>
                                <div id="tabNew" class="tab-pane"><jsp:include page="DrugTabs/NewDrug.jsp" ></jsp:include></div>
                                <div id="tabModify" class="tab-pane"><jsp:include page="DrugTabs/ModifyDrug.jsp" ></jsp:include></div>
                                <div id="tabAdditionalInfo" class="tab-pane">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <h3>Consultas que tienen asociado el diagnóstico</h3>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblAdditionalInfo">
                                                <thead>
                                                    <th>Fecha</th>
                                                    <th>Apellido Paterno</th>
                                                    <th>Apellido Materno</th>
                                                    <th>Nombre</th>
                                                </thead>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script src="${dataTablesJS}" type="text/javascript"></script>

<script src="${momentJs}" type="text/javascript"></script>

<script src="${inputmaskJs}" type="text/javascript"></script>
<script src="${inputmaskDateJs}" type="text/javascript"></script>
<script src="${inputmaskRegexJs}" type="text/javascript"></script>

<script src="${bvJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>

<script src="${utilityJs}" type="text/javascript"></script>

<script src="${drugHomeJs}" type="text/javascript"></script>
<script src="${newDrugIncompatibilityJs}" type="text/javascript"></script>
<script src="${newDrugTreatmentsJs}" type="text/javascript"></script>
<script src="${newDrugCNJs}" type="text/javascript"></script>
<script src="${newDrugJs}" type="text/javascript"></script>
