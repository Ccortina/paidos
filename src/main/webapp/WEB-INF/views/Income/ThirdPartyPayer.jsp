<%-- 
    Document   : ThirdPartyPayer
    Created on : Nov 13, 2014, 5:52:42 PM
    Author     : Carlos
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
<c:url var="momentRangeJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment-range.js" />

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="tppHomeJs" value="/resources/js/Income/ThirdPartyPayer.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Terceros pagadores</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <ul id="mainTabMenu" class="nav nav-tabs">
                              <li class="active"><a href="#tabMain" data-toggle="tab">Terceros pagadores</a></li>
                              <sec:authorize access="hasAnyRole('Doctor','Ingresos_Pagadores Terceros_2')">
                              <li><a href="#tabNew" data-toggle="tab">Nuevo</a></li>
                              </sec:authorize>
                              <sec:authorize access="hasAnyRole('Doctor','Ingresos_Pagadores Terceros_4')">
                              <li><a href="#tabModify" data-toggle="tab">Modificar</a></li>
                              </sec:authorize>
                            </ul>
                            
                            <div class="tab-content">
                                <div id="tabMain" class="tab-pane active">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblThirdPartyPayers">
                                                <thead>
                                                    <th>Nombre</th>
                                                    <th>RFC</th>
                                                </thead>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <sec:authorize access="hasAnyRole('Doctor','Ingresos_Pagadores Terceros_2')">
                                        <div class="col-sm-offset-1 col-sm-2">
                                            <button type="button" class="btn btn-primary" onclick="newTPP();">Nuevo Tercer pagador</button>
                                        </div>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole('Doctor','Ingresos_Pagadores Terceros_4')">
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-primary" onclick="modifyTPP();">Modificar Tercer pagador</button>
                                        </div>
                                        </sec:authorize>
                                        <sec:authorize access="hasAnyRole('Doctor','Ingresos_Pagadores Terceros_8')">
                                        <div class="col-sm-2">
                                            <button type="button" class="btn btn-danger" onclick="suspendTPP();">Desactivar Tercer pagador</button>
                                        </div>
                                        </sec:authorize>
                                    </div>
                                </div>
                                <sec:authorize access="hasAnyRole('Doctor','Ingresos_Pagadores Terceros_2')">
                                <div id="tabNew" class="tab-pane ">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <form id="formNewTPP">
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Nombre:</label>
                                                            <input type="text" class="form-control" name="name" placeholder="Nombre" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Calle y numero:</label>
                                                            <input type="text" class="form-control" name="street" placeholder="Calle" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Colonia:</label>
                                                            <input type="text" class="form-control" name="colony" placeholder="Colonia" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Ciudad:</label>
                                                            <input type="text" class="form-control" name="city" placeholder="Ciudad" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Estado:</label>
                                                            <input type="text" class="form-control" name="state" placeholder="Estado" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Codigo postal:</label>
                                                            <input type="text" class="form-control" name="zip" placeholder="Codigo postal" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Pais:</label>
                                                            <input type="text" class="form-control" name="country" placeholder="Pais" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>RFC:</label>
                                                            <input type="text" class="form-control" name="rfc" placeholder="rfc" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <button type="submit" class="btn btn-primary">Guadar</button>
                                                    </div> 
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                </sec:authorize>
                                <sec:authorize access="hasAnyRole('Doctor','Ingresos_Pagadores Terceros_4')">
                                <div id="tabModify" class="tab-pane ">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <form id="formEditTPP">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <input type="hidden" name="idThirdPartyPayer" />
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Nombre:</label>
                                                            <input type="text" class="form-control" name="name" placeholder="Nombre" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Calle y numero:</label>
                                                            <input type="text" class="form-control" name="street" placeholder="Calle" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Colonia:</label>
                                                            <input type="text" class="form-control" name="colony" placeholder="Colonia" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Ciudad:</label>
                                                            <input type="text" class="form-control" name="city" placeholder="Ciudad" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Estado:</label>
                                                            <input type="text" class="form-control" name="state" placeholder="Estado" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Codigo postal:</label>
                                                            <input type="text" class="form-control" name="zip" placeholder="Codigo postal" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <div class="form-group">
                                                            <label>Pais:</label>
                                                            <input type="text" class="form-control" name="country" placeholder="Pais" />
                                                        </div>
                                                    </div> 
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-4">
                                                        <div class="form-group">
                                                            <label>RFC:</label>
                                                            <input type="text" class="form-control" name="rfc" placeholder="rfc" />
                                                        </div>
                                                    </div>
                                                    <div class=" col-sm-2">
                                                        <div class="form-group">
                                                            <label>Activo:</label>
                                                            <input type="checkbox" class="form-control" name="active"/>
                                                        </div>
                                                    </div>  
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-offset-3 col-sm-6">
                                                        <button type="submit" class="btn btn-primary">Guadar</button>
                                                    </div> 
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                </sec:authorize>
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
<script src="${momentRangeJs}" type="text/javascript"></script>

<script src="${inputmaskJs}" type="text/javascript"></script>
<script src="${inputmaskDateJs}" type="text/javascript"></script>
<script src="${inputmaskRegexJs}" type="text/javascript"></script>

<script src="${bvJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>

<script src="${utilityJs}" type="text/javascript"></script>

<script src="${tppHomeJs}" type="text/javascript"></script>
