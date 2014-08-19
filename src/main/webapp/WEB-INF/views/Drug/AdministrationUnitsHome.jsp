<%-- 
    Document   : AdministrationUnitsHome
    Created on : Aug 14, 2014, 11:37:41 PM
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

<c:url var="drugAdminstrationUnitJs" value="/resources/js/Drug/AdministrationUnit.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div classs="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Medicamentos - Unidades de administración</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <ul id="drugAUTabMenu" class="nav nav-tabs">
                              <li class="active"><a href="#tabMain" data-toggle="tab">Unidades</a></li>
                              <li><a href="#tabNew" data-toggle="tab">Nuevo</a></li>
                              <li><a href="#tabModify" data-toggle="tab">Modificar</a></li>
                              <li><a href="#tabAdditionalInfo" data-toggle="tab">Informacion Relacionada</a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="tabMain" class="tab-pane active">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblAU">
                                                <thead>
                                                    <th>Unidades</th>
                                                    <th>Activo</th>
                                                </thead>
                                            </table> 
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Nueva unidad de administracion" onclick="newAU()" />
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Modificar unidad de administracion" onclick="modifyAU()" />
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Informacion Asociada" onclick="additionalInfo()" />
                                        </div>
                                    </div>
                                </div>
                                <div id="tabNew" class="tab-pane">
                                    <form role="form" id="formNewAU">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputNewAU">Unidad</label>
                                                    <input type="text" class="form-control inputNormal" id="inputNewAU" placeholder="Unidad" name="administrationUnit"/>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label>
                                                        <input id="inputNewAUActive" type="checkbox" name="active" checked>
                                                        Activo
                                                    </label>
                                                </div>   
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                                </div>
                                                <div class="col-sm-2">
                                                    <input type="button" class="btn btn-danger" value="Cancelar" onclick="cancel()"/>
                                                </div>
                                            </div>
                                       </div>
                                    </div>
                                    </form>
                                </div>
                                <div id="tabModify" class="tab-pane">
                                    <form role="form" id="formModifyAU">
                                    <input type="hidden" id="inputIdAU" value=""/>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputModifyAU">Unidad</label>
                                                    <input type="text" class="form-control inputNormal" id="inputModifyAU" placeholder="Unidad" name="administrationUnit"/>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label>
                                                        <input id="inputModifyAUActive" type="checkbox" name="active" />
                                                        Activo
                                                    </label>
                                                </div>   
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                                </div>
                                                <div class="col-sm-2">
                                                    <input type="button" class="btn btn-danger" value="Cancelar" onclick="cancel()"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                                <div id="tabAdditionalInfo" class="tab-pane">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <h3>Medicamentos asociados con la unidad de administracion</h3>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblAdditionalInfo">
                                                <thead>
                                                    <th>Medicamento</th>
                                                    <th>Concentracion</th>
                                                    <th>Unidad de Administracion</th>
                                                    <th>Criterio para calculo de dosis</th>
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

<script src="${drugAdminstrationUnitJs}" type="text/javascript"></script>