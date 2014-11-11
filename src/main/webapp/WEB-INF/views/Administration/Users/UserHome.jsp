<%-- 
    Document   : UserHome
    Created on : Oct 25, 2014, 3:44:27 PM
    Author     : Carlos Cortina
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../../Includes/header.jsp"/>

<script type="text/javascript">
    var contextPath='<%=request.getContextPath()%>';
</script>

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="administrationJs" value="/resources/js/Administration/User/UserHome.js" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <ul class="nav nav-tabs" role="tablist" id="userTabs">
                <li class="active"><a href="#basicUserInfo" role="tab" data-toggle="tab">Usuario</a></li>
                <li><a href="#editUserInfo" role="tab" data-toggle="tab">Modificar</a></li>
            </ul>
            <div class="tab-content" >
                <div class="tab-pane active" id="basicUserInfo"><jsp:include page="UserBasicInfo.jsp" /></div>
                <div class="tab-pane" id="editUserInfo"><jsp:include page="UserModify.jsp" /></div>
            </div>
        </div>
    </div>
</div>

<script src="${administrationJs}" type="text/javascript"></script>
<script src="${bvJs}" type="text/javascript"></script>
<script src="${bootboxJs}" type="text/javascript"></script> 
<script src="${utilityJs}" type="text/javascript"></script>
