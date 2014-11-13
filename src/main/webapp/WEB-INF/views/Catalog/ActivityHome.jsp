<%-- 
    Document   : ActivityHome
    Created on : Aug 16, 2014, 6:10:28 PM
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

<c:url var="activityJs" value="/resources/js/Catalog/Activity.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div classs="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Actividades</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <ul id="mainTabMenu" class="nav nav-tabs">
                              <li class="active"><a href="#tabMain" data-toggle="tab">Actividades</a></li>
                              <sec:authorize access="hasAnyRole('Doctor','Catalogos_Actividades_2')">
                              <li><a href="#tabNew" data-toggle="tab">Nuevo</a></li>
                              </sec:authorize>
                              <sec:authorize access="hasAnyRole('Doctor','Catalogos_Actividades_4')">
                              <li><a href="#tabModify" data-toggle="tab">Modificar</a></li>
                              </sec:authorize>
                              <li><a href="#tabAdditionalInfo" data-toggle="tab">Informacion Relacionada</a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="tabMain" class="tab-pane active">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblMain">
                                                <thead>
                                                    <th>Actividad</th>
                                                    <th>Costo</th>
                                                    <th>Tipo</th>
                                                    <th>Inmunización</th>
                                                    <th>Incluir en consulta</th>
                                                    <th>Activo</th>
                                                </thead>
                                            </table> 
                                        </div>
                                    </div>
                                    <div class="row">
                                        <sec:authorize access="hasAnyRole('Doctor','Catalogos_Actividades_2')">
                                        <div class="col-sm-3">
                                            <input type="button" class="btn btn-primary" value="Nueva Actividad" onclick="newItem()" />
                                        </div>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole('Doctor','Catalogos_Actividades_4')">
                                        <div class="col-sm-3">
                                            <input type="button" class="btn btn-primary" value="Modificar Actividad" onclick="modifyItem()" />
                                        </div>
                                        </sec:authorize>
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Informacion Asociada" onclick="additionalInfo()" />
                                        </div>
                                    </div>
                                </div>
                                <sec:authorize access="hasAnyRole('Doctor','Catalogos_Actividades_2')">
                                <div id="tabNew" class="tab-pane">
                                    <form role="form" id="formNewItem">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputNewItem">Actividad</label>
                                                    <input type="text" class="form-control inputNormal" id="inputNewItem" placeholder="Actividad" name="itemName"/>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputNewItemCost">Costo</label>
                                                    <input type="text" class="form-control inputNormal" id="inputNewItemCost" placeholder="Costo" name="cost"/>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputNewItemType">Tipo</label>
                                                    <select class="form-control" id="inputNewItemType" name="type">
                                                        <c:forEach var="type" items="${activitiesType}">
                                                            <option value="${type.idActivityType}"><c:out value="${type.type}" /></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label>
                                                        <input id="inputNewItemInclude" type="checkbox" name="include">
                                                        Incluir actividad en cada nueva consulta
                                                    </label>
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
                                                <div class="form-group">
                                                    <table id="tblVaccine1" class="hover row-border activityVaccineList">
                                                        <thead>
                                                            <th>Vacuna asociada</th>
                                                        </thead>
                                                    </table>
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
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('Doctor','Catalogos_Actividades_4')">
                                <div id="tabModify" class="tab-pane">
                                    <form role="form" id="formModifyItem">
                                    <input type="hidden" id="inputIdItem" value=""/>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputModifyItem">Actividad</label>
                                                    <input type="text" class="form-control inputNormal" id="inputModifyItem" placeholder="Actividad" name="itemName"/>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputModifyItemCost">Costo</label>
                                                    <input type="text" class="form-control inputNormal" id="inputModifyItemCost" placeholder="Costo" name="cost"/>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputModifyItemType">Tipo</label>
                                                    <select class="form-control" id="inputModifyItemType" name="type">
                                                        <c:forEach var="type" items="${activitiesType}">
                                                            <option value="${type.idActivityType}"><c:out value="${type.type}" /></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label>
                                                        <input id="inputModifyItemInclude" type="checkbox" name="include">
                                                        Incluir actividad en cada nueva consulta
                                                    </label>
                                                </div>   
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label>
                                                        <input id="inputModifyItemActive" type="checkbox" name="active" />
                                                        Activo
                                                    </label>
                                                </div>   
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <table id="tblVaccine" class="hover row-border activityVaccineList">
                                                        <thead>
                                                            <th>id</th>
                                                            <th>Vacuna asociada</th>
                                                        </thead>
                                                    </table>
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
                                </sec:authorize>
                                <div id="tabAdditionalInfo" class="tab-pane">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <h3>Consultas que tienen asociada la actividad</h3>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblAdditionalInfo">
                                                <thead>
                                                    <th>Fecha</th>
                                                    <th>hora</th>
                                                    <th>A. Paterno</th>
                                                    <th>A. Materno</th>
                                                    <th>Nombre</th>
                                                    <th>Actividad</th>
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

<script src="${activityJs}" type="text/javascript"></script>
