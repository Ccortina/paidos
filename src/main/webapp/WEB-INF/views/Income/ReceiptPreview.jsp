<%-- 
    Document   : ReceiptPreview
    Created on : Oct 2, 2014, 11:48:32 AM
    Author     : Carlos Cortina
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../Includes/header.jsp"/>

<div class="row">
    <div class="col-sm-offset-3 col-sm-6">
        <div class="row">
            ${name}
        </div>
        <div class="row">
            RFC: ${rfc}
        </div>
        <div class="row">
            Neto Recibido: $ ${net}
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
