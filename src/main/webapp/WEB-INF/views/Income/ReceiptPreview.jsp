<%-- 
    Document   : ReceiptPreview
    Created on : Oct 2, 2014, 11:48:32 AM
    Author     : Carlos Cortina
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="cssUrl" value="/resources/CSS/bootstrap.min.css" />
<c:url var="jsUrl" value="/resources/js/bootstrap.min.js" />
<c:url var="jqueryUrl" value="/resources/js/jquery-2-1.0.3.js" />

<script src="${jqueryUrl}" type="text/javascript"></script>
<script src="${jsUrl}" type="text/javascript"></script>

<link href="${cssUrl}" rel="stylesheet" />

<div class="row">
    <div class="col-sm-offset-3 col-sm-6">
        <div class="row">
            ${name}
        </div>
        <div class="row">
            RFC: ${rfc}
        </div>
        <div class="row">
            ${net}
        </div>
        <div class="row">
            ${totalStr}
        </div>
        <div class="row">
            ${address}
        </div>
        <div class="row">
            ${address2}
        </div>
        <div class="row">
            ${patient}
        </div>
        <div class="row">
            ${concept}
        </div>
        <div class="row">
            ${date}
        </div>
    </div>
</div>
