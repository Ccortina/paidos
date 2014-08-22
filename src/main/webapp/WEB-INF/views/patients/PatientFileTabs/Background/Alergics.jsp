<%-- 
    Document   : Alergics
    Created on : Jun 8, 2014, 3:00:40 PM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-10">
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form id="alergicBackgroundForm" class="changeForm" role="form" method="POST" modelAttribute="record" action="/demo/saveAlergicBackground">
                    <form:hidden path="idRecord" />
                    <div class="row">
                        <div class="col-sm-9">
                            <form:textarea id="backAlergicBackground" class="form-control" rows="10" path="alergicBackground"/>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <div class="col-sm-12">
                <input type="button" class="btn btn-primary" value="Guardar" onclick="saveForm('alergicBackgroundForm')">
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <input type="button" id="btnalergicBackgroundFormCancelEditForm" class="btn btn-danger" value="Cancelar" onclick="disableForms();">
            </div>
        </div>
    </div>
</div>
