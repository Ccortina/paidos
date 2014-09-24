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
        <div class="row">
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
        <div class="row">
            <div class="panel panel-info">
                    <div class="panel-heading">Medicamentos</div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-6">
                                <table id="tblConsultationPatientAlergicDrug" class="hover row-border">
                                    <thead>
                                        <tr>
                                            <th>Medicamento</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="sol-sm-12">
                                        <a data-toggle="modal" href="#modalConsultationPatientAddAD" class="btn btn-primary" >Agregar</a>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="sol-sm-12">
                                        <input type="button" class="btn btn-danger" value="Quitar" onclick="submitDeleteAD();"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <div class="col-sm-12">
                <input type="button" class="btn btn-primary" value="Guardar" onclick="saveForm('alergicBackgroundForm')">
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modalConsultationPatientAddAD" tabindex="-1" role="dialog" aria-labelledby="modalConsultationPatientAddAD" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                    <h4 class="modal-title">Agregar medicamentos alérgicos para el paciente</h4>
            </div>
            <div class="modal-body">
                <table id="tblPADAvaibleDrug" class="hover row-border">
                    <thead>
                        <tr>
                            <th>Medicamento</th>
                            <th>Presentación</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-primary" value="Agregar" onclick="submitAddAD();"/>
                    </div>
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-danger" data-dismiss="modal" value="Cancelar" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>