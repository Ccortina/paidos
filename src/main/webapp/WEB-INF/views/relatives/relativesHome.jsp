<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

<c:url var="relativeHomeJS" value="/resources/js/RelativeHome/RelativeHome.js" />
<c:url var="newRelativeTabJS" value="/resources/js/RelativeHome/NewRelativeTab.js" />
<c:url var="modifyRelativeTabJS" value="/resources/js/RelativeHome/ModifyRelativeTab.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <ul id="relativesTabMenu" class="nav nav-tabs">
              <li class="active"><a href="#tabMain" data-toggle="tab">Familiares</a></li>
              <sec:authorize access="hasAnyRole('Doctor','Pacientes_Familiares_2')">
              <li><a href="#tabNew" data-toggle="tab">Nuevo</a></li>
              </sec:authorize>
              <sec:authorize access="hasAnyRole('Doctor','Pacientes_Familiares_4')">
              <li><a href="#tabEdit" data-toggle="tab">Modificar</a></li>
              </sec:authorize>
            </ul>
            <div class="tab-content">
                <div id="tabMain" class="tab-pane active">
                    <div class="row">
                        <div class="col-sm-12">
                            <table class="hover row-border" id="tblRelative">
                                <thead>
                                    <th>Apellido Paterno</th>
                                    <th>Apellido Materno</th>
                                    <th>Nombre</th>
                                    <th>Activo</th>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="button" value="Nuevo Familiar" onclick="newRelative()" class="btn btn-primary" />
                        </div>
                        <div class="col-sm-2">
                            <input type="button" value="Modificar Familiar" onclick="modifyRelative()" class="btn btn-primary" />
                        </div>
                    </div>
                </div>
                <sec:authorize access="hasAnyRole('Doctor','Pacientes_Familiares_2')">
                <div id="tabNew" class="tab-pane"><jsp:include page="Tabs/NewRelative.jsp" /></div>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('Doctor','Pacientes_Familiares_4')">
                <div id="tabEdit" class="tab-pane"><jsp:include page="Tabs/ModifyRelative.jsp" /></div>
                </sec:authorize>
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

<script src="${relativeHomeJS}" type="text/javascript"></script>
<script src="${newRelativeTabJS}" type="text/javascript"></script>
<script src="${modifyRelativeTabJS}" type="text/javascript"></script>

