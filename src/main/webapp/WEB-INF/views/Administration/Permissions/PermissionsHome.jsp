<%-- 
    Document   : PermissionsHome
    Created on : Nov 10, 2014, 12:31:17 PM
    Author     : Carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="../../Includes/header.jsp"/>

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="permissionsJs" value="/resources/js/Administration/Permissions/PermissionsHome.js" />

<div class="container-fluid">
    <form id="formPermissions">
    <div class="row">
        <div class="col-sm-12">
            <div class="row">
                <div class="col-sm-offset-10 col-sm-2">
                    <button type="button" class="btn btn-primary" onclick="savePermissions();" >Actualizar Permisos</button>
                </div>
            </div>
            <br>
            <c:forEach items="${permissions}" var="module">
                <c:if test="${module.module == 'Modulo'}">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">${module.permission}</h3>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" class="moduleMonitor" name="${module.idPermission}"> Activo
                                        </label>
                                    </div>
                                </div>
                                <div class="panel-body" id="panelBody${module.idPermission}">
                                    <c:forEach items="${permissions}" var="permission">
                                        <c:if test="${permission.module == module.permission}">
                                            <div class="row">
                                                <div class="col-sm-4">
                                                    ${permission.permission}
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" class="monitorOP" name="${permission.idPermission}_1"> Visible
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" class="monitorOP" name="${permission.idPermission}_2"> Agregar
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" class="monitorOP" name="${permission.idPermission}_4"> Modificar
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" class="monitorOP" name="${permission.idPermission}_8"> Borar
                                                        </label>
                                                    </div>
                                                </div>        
                                            </div>        
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
    </form>
</div>

<script src="${bootboxJs}" type="text/javascript"></script> 
<script src="${permissionsJs}" type="text/javascript"></script> 
<script src="${utilityJs}" type="text/javascript"></script>

<script type="text/javascript">
    var contextPath='<%=request.getContextPath()%>';
    var permissionsSum = [];
    <c:forEach items="${permissions}" var="permission">
        <c:if test="${permission.module == 'Modulo'}" >
            checkModulePermission( '<c:out value="${permission.idPermission}" />',
                                    <c:out value="${permission.value}" />);
        </c:if>
        permissionsSum['<c:out value="${permission.idPermission}" />'] =<c:out value="${permission.value}" /> ;
        setCurrentPermissions(<c:out value="${permission.idPermission}" />,
                                <c:out value="${permission.value}" />);
    </c:forEach>
</script>
