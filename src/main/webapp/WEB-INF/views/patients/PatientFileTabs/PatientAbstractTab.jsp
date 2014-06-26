<%-- 
    Document   : PatientAbstractTab
    Created on : May 18, 2014, 1:28:18 PM
    Author     : Ccortina_Mac
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>

<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <br>
            <div class="form-group">
                <div class="col-sm-2">
                        <label class="" for="perinatalBackground">A. Perinatales :</label>
                </div>
                <div class="col-sm-10">
                        <input class="form-control input-sm" id="perinatalBackground" type="text" value="${record.perinatalBackground}" />
                </div>
            </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="" for="development">Desarrollo :</label>
                    </div>
                    <div class="col-sm-10">
                        <input class="form-control input-sm" id="development" type="text" value="${record.developmentBackground}" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="" for="surgicalHistory">A. Quirúrgicos :</label>
                    </div>
                    <div class="col-sm-10">
                        <input class="form-control input-sm" id="surgicalHistory" type="text" value="${record.surgicalHistory}" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="" for="hereditaryAndFamilyBackground">A. H. y Fam. :</label>
                    </div>
                    <div class="col-sm-10">
                        <input class="form-control input-sm" id="hereditaryAndFamilyBackground" type="text" value="${record.hereditaryAndFamilyBackground}" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="" for="app">A.P.P. :</label>
                    </div>
                    <div class="col-sm-10">
                        <input class="form-control input-sm" id="app" type="text" value="${record.pathologicalBackgorund}" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label class="" for="alergicBackground">Alérgicos :</label>
                    </div>
                    <div class="col-sm-10">
                        <input class="form-control input-sm" id="alergicBackground" type="text" value="${record.alergicBackground }" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <table id="tblPatientAbstractPrescription">
                        <thead>
                            <tr>
                                <th>Fecha</th>
                                <th>Receta</th>
                            </tr>
                        </thead>
                    </table>
                    
                </div>
            </div>
    </div>
</div>
