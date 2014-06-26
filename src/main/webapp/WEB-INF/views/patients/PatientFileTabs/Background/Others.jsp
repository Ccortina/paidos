<%-- 
    Document   : Others
    Created on : Jun 9, 2014, 10:22:58 AM
    Author     : Ccortina_Mac
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-10">
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form id="othersForm" role="form" class="changeForm" method="POST" modelAttribute="record" action="/demo/saveOthers">
                    <form:hidden path="idRecord" />
                    <div class="row">
                        <div class="col-sm-12">
                            <form:textarea class="form-control onChange" rows="10" path="others"/>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <div class="col-sm-12">
                <input type="button" class="btn btn-primary" value="Modificar" onclick="enableForm('othersForm');">
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <input type="button" id="btnothersFormCancelEditForm" class="btn btn-danger" value="Cancelar" onclick="disableForms();">
            </div>
        </div>
    </div>
</div>