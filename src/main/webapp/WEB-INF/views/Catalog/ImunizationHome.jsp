<%-- 
    Document   : ImunizationHome
    Created on : Aug 15, 2014, 4:36:21 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>

<jsp:include page="../Includes/header.jsp"/>

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

<c:url var="immunizationJs" value="/resources/js/Catalog/Imunization.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div classs="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Inmunizaciones</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <ul id="mainTabMenu" class="nav nav-tabs">
                              <li class="active"><a href="#tabMain" data-toggle="tab">Inmunizaciones</a></li>
                              <sec:authorize access="hasAnyRole('Doctor','Catalogos_Inmunizaciones_2')">
                              <li><a href="#tabNew" data-toggle="tab">Nuevo</a></li>
                              </sec:authorize>
                              <sec:authorize access="hasAnyRole('Doctor','Catalogos_Inmunizaciones_4')">
                              <li><a href="#tabModify" data-toggle="tab">Modificar</a></li>
                              </sec:authorize>
                              <li><a href="#tabRelatedInfo" data-toggle="tab">Informacion asociadad</a></li>
                              <li><a href="#tabPatientWOVaccine" data-toggle="tab">Pacientes sin la inmunizacion</a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="tabMain" class="tab-pane active">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblMain">
                                                <thead>
                                                    <th>Inmunizacion</th>
                                                    <th>Tipo</th>
                                                    <th>Año</th>
                                                    <th>Mes</th>
                                                    <th>Dia</th>
                                                    <th>Activa</th>
                                                </thead>
                                            </table> 
                                        </div>
                                    </div>
                                    <div class="row">
                                        <sec:authorize access="hasAnyRole('Doctor','Catalogos_Inmunizaciones_2')">
                                        <div class="col-sm-3">
                                            <input type="button" class="btn btn-primary" value="Nueva Inmunizacion" onclick="newItem()" />
                                        </div>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole('Doctor','Catalogos_Inmunizaciones_4')">
                                        <div class="col-sm-3">
                                            <input type="button" class="btn btn-primary" value="Modificar Inmunizacion" onclick="modifyItem()" />
                                        </div>
                                        </sec:authorize>
                                        <div class="col-sm-3">
                                            <input type="button" class="btn btn-primary" value="Informacion asociada" onclick="additionalInfo()" />
                                        </div>
                                        <div class="col-sm-3">
                                            <input type="button" class="btn btn-primary" value="Pacinetes sin la inmunizacion" onclick="additionalInfo2()" />
                                        </div>
                                    </div>
                                </div>
                                <sec:authorize access="hasAnyRole('Doctor','Catalogos_Inmunizaciones_2')">
                                <div id="tabNew" class="tab-pane">
                                    <form role="form" id="formNewItem">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputNewItem">Inmunizacion</label>
                                                    <input type="text" class="form-control inputNormal" id="inputNewItem" placeholder="Nombre Inmunizacion" name="itemName"/>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputNewItemType">Tipo</label>
                                                    <select class="form-control" id="inputNewItemType" name="type">
                                                        <c:forEach var="type" items="${inmmunizationType}">
                                                            <option value="${type.idvaccineType}"><c:out value="${type.type}" /></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <lable>Aplicar</lable>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="form-group">
                                                        <label for="inputNewYear">Año</label>
                                                        <input type="text" class="form-control" id="inputNewYear" placeholder="A" name="itemAppYear" value="0"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="form-group">
                                                        <label for="inputNewMonth">Mes</label>
                                                        <input type="text" class="form-control" id="inputNewMonth" placeholder="M" name="itemAppMonth" value="0"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="form-group">
                                                        <label for="inputNewDay">Dia</label>
                                                        <input type="text" class="form-control" id="inputNewDay" placeholder="D" name="itemAppDay" value="0"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>
                                                            <input id="inputNewItemMultiple" type="checkbox" name="multiple">
                                                            Aplicacion multiple
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label>
                                                        <input id="inputNewItemActive" type="checkbox" name="active" checked>
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
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <table id="tblAvaibleImmunization" class="hover row-border">
                                                    <thead>
                                                        <th>Inmunizacion</th>
                                                    </thead>
                                                </table>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-2"><input type="button" class="btn btn-primary" value="Agregar" onclick="addEquivalent()"></div>
                                                <div class="col-sm-2"><input type="button" class="btn btn-danger" value="Quitar" onclick="removeEquivalent()"></div>
                                            </div>
                                            <div class="row">
                                                <table id="tblEquivalentImmunization" class="hover row-border">
                                                    <thead>
                                                        <th>Vacunas equivalentes</th>
                                                    </thead>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('Doctor','Catalogos_Inmunizaciones_4')">
                                <div id="tabModify" class="tab-pane">
                                    <form role="form" id="formModifyItem">
                                    <input type="hidden" id="inputIdItem" value=""/>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputModifyItem">Inmunizacion</label>
                                                    <input type="text" class="form-control inputNormal" id="inputModifyItem" placeholder="Nombre Inmunizacion" name="itemName"/>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputModifyItemType">Tipo</label>
                                                    <select class="form-control" id="inputModifyItemType" name="type">
                                                        <c:forEach var="type" items="${inmmunizationType}">
                                                            <option value="${type.idvaccineType}"><c:out value="${type.type}" /></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <lable>Aplicar</lable>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="form-group">
                                                        <label for="inputModifyYear">Año</label>
                                                        <input type="text" class="form-control" id="inputModifyYear" placeholder="A" name="itemAppYear" value="0"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="form-group">
                                                        <label for="inputModifyMonth">Mes</label>
                                                        <input type="text" class="form-control" id="inputModifyMonth" placeholder="M" name="itemAppMonth" value="0"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="form-group">
                                                        <label for="inputModifyDay">Dia</label>
                                                        <input type="text" class="form-control" id="inputModifyDay" placeholder="D" name="itemAppDay" value="0"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="form-group">
                                                        <label>
                                                            <input id="inputModifyItemMultiple" type="checkbox" name="multiple">
                                                            Aplicacion multiple
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label>
                                                        <input id="inputModifyItemActive" type="checkbox" name="active" checked>
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
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <table id="tblAvaibleImmunizationM" class="hover row-border">
                                                    <thead>
                                                        <th>id</th>
                                                        <th>Inmunizacion</th>
                                                    </thead>
                                                </table>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-2"><input type="button" class="btn btn-primary" value="Agregar" onclick="addEquivalentM()"></div>
                                                <div class="col-sm-2"><input type="button" class="btn btn-danger" value="Quitar" onclick="removeEquivalentM()"></div>
                                            </div>
                                            <div class="row">
                                                <table id="tblEquivalentImmunizationM" class="hover row-border">
                                                    <thead>
                                                        <th>Vacunas equivalentes</th>
                                                    </thead>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                                </sec:authorize>
                                <div id="tabRelatedInfo" class="tab-pane">
                                    <h4>Pacientes que tienen asociada la vacuna</h4>
                                    <table id="tblAdditionalInfo" class="hover row-border">
                                        <thead>
                                            <th>Paterno</th>
                                            <th>Materno</th>
                                            <th>Nombre</th>
                                            <th>Inmunizacion</th>
                                        </thead>
                                    </table>
                                </div>
                                <div id="tabPatientWOVaccine" class="tab-pane">
                                    <h4>Pacientes que no tienen asociada la vacuna</h4>
                                    <table id="tblPatientWOVaccine" class="hover row-border">
                                        <thead>
                                            <th>Paterno</th>
                                            <th>Materno</th>
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

<script src="${immunizationJs}" type="text/javascript"></script>