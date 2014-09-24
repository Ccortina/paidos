<%-- 
    Document   : Pathological
    Created on : Jun 8, 2014, 3:17:08 PM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-10">
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form id="pathologicalBackgroundForm" class="changeForm" role="form" method="POST" modelAttribute="record" action="/demo/savePathologicalBackground">
                    <form:hidden path="idRecord" />
                    <div class="row">
                        <div class="col-sm-12">
                                <form:textarea class="form-control onChange" rows="10" path="pathologicalBackgorund"/>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <div class="col-sm-12">
                <input type="button" class="btn btn-primary" value="Guardar" onclick="saveForm('pathologicalBackgroundForm');" />
            </div>
        </div>
    </div>
</div>
