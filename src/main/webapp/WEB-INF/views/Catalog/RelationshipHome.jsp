<%-- 
    Document   : RelationshipHome
    Created on : Aug 15, 2014, 8:42:19 PM
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

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="relationshipJs" value="/resources/js/Catalog/Relationship.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div classs="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Parentescos</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <ul id="mainTabMenu" class="nav nav-tabs">
                              <li class="active"><a href="#tabMain" data-toggle="tab">Parentescos</a></li>
                              <li><a href="#tabNew" data-toggle="tab">Nuevo</a></li>
                              <li><a href="#tabModify" data-toggle="tab">Modificar</a></li>
                              <li><a href="#tabAdditionalInfo" data-toggle="tab">Informacion Relacionada</a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="tabMain" class="tab-pane active">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblMain">
                                                <thead>
                                                    <th>Parentesco</th>
                                                    <th>Activo</th>
                                                </thead>
                                            </table> 
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <input type="button" class="btn btn-primary" value="Nuevo parentesco" onclick="newItem()" />
                                        </div>
                                        <div class="col-sm-3">
                                            <input type="button" class="btn btn-primary" value="Modificar parentesco" onclick="modifyItem()" />
                                        </div>
                                        <div class="col-sm-2">
                                            <input type="button" class="btn btn-primary" value="Informacion Asociada" onclick="additionalInfo()" />
                                        </div>
                                    </div>
                                </div>
                                <div id="tabNew" class="tab-pane">
                                    <form role="form" id="formNewItem">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputNewItem">Parentesco</label>
                                                    <input type="text" class="form-control inputNormal" id="inputNewItem" placeholder="parentesco" name="itemName"/>
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
                                    </div>
                                    </form>
                                </div>
                                <div id="tabModify" class="tab-pane">
                                    <form role="form" id="formModifyItem">
                                    <input type="hidden" id="inputIdItem" value=""/>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="inputModifyItem">Parentesco</label>
                                                    <input type="text" class="form-control inputNormal" id="inputModifyItem" placeholder="Parentesco" name="itemName"/>
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
                                            <h3>Familiares asociados con el parentesco</h3>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="row-border hover" id="tblAdditionalInfo">
                                                <thead>
                                                    <th>A. Paterno</th>
                                                    <th>A. Materno</th>
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

<script src="${relationshipJs}" type="text/javascript"></script>