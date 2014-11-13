<%-- 
    Document   : TreatmentHome
    Created on : Aug 2, 2014, 3:52:49 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>

<jsp:include page="../../Includes/header.jsp"/>

<!-- Files for data tables function-->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />
<c:url var="dtModCSS" value="/resources/CSS/DataTables/datatables.mod.css" />

<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="treatmentHomeJs" value="/resources/js/DiagnosticTreatment/TreatmentHome.js" />
<c:url var="newTreatmentJs" value="/resources/js/DiagnosticTreatment/TreatmentTabs/newTreatment.js" />
<c:url var="modifyTreatmentJs" value="/resources/js/DiagnosticTreatment/TreatmentTabs/modifyTreatment.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div classs="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Tratamientos</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <ul id="treatmentTabMenu" class="nav nav-tabs">
                              <li class="active"><a href="#tabMain" data-toggle="tab">Tratamientos</a></li>
                              <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Tratamientos_2')">
                              <li><a href="#tabNew" data-toggle="tab">Nuevo</a></li>
                              </sec:authorize>
                              <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Tratamientos_4')">
                              <li><a href="#tabModify" data-toggle="tab">Modificar</a></li>
                              </sec:authorize>
                              <li><a href="#tabAdditionalInfo" data-toggle="tab">Informacion asociada</a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="tabMain" class="tab-pane active">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblTreatment">
                                                <thead>
                                                    <th>Tratamiento</th>
                                                    <th>Activo</th>
                                                    <th>idCie</th>
                                                </thead>
                                            </table> 
                                        </div>
                                    </div>
                                    <div class="row">
                                        <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Tratamientos_2')">
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Agregar Nuevo Tratamiento" onclick="newTreatment()" />
                                        </div>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Tratamientos_4')">
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Modificar Tratamiento" onclick="modifyTreatment()" />
                                        </div>
                                        </sec:authorize>
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Informacion Asociada" onclick="getAdditionalInfo()" />
                                        </div>
                                    </div>
                                </div>
                                <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Tratamientos_2')">
                                <div id="tabNew" class="tab-pane">
                                    <jsp:include page="Tabs/NewTreatment.jsp"></jsp:include>
                                </div>
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('Doctor','Diagnosticos_Tratamientos_2')">
                                <div id="tabModify" class="tab-pane">
                                    <jsp:include page="Tabs/ModifyTreatment.jsp"></jsp:include>
                                </div>
                                </sec:authorize>
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

<script src="${treatmentHomeJs}" type="text/javascript"></script>
<script src="${newTreatmentJs}" type="text/javascript"></script>
<script src="${modifyTreatmentJs}" type="text/javascript"></script>
