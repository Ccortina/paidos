<%-- 
    Document   : Perinatals
    Created on : Jun 8, 2014, 3:38:08 PM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-10">
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form id="perinatalBackgroundForm" class="changeForm" role="form" method="post" modelAttribute="record" action="/demo/savePerinatalBackground">
                    <form:hidden path="idRecord" />
                    <div class="row">
                        <div class="col-sm-12">
                                <form:textarea class="form-control onChange" rows="10" path="perinatalBackground"/>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <div class="col-sm-12">
                <input type="button" class="btn btn-primary" value="Guardar" onclick="saveForm('perinatalBackgroundForm');" />
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
            </div>
        </div>
    </div>
</div>
