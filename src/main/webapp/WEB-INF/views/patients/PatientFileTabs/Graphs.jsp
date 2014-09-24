<%-- 
    Document   : Graphs
    Created on : Sep 13, 2014, 11:14:20 PM
    Author     : Carlos Cortina
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-10">
        <table id="tblConsultationMeasure" class="hover row-border">
            <thead>
                <th>Fecha</th>
                <th>Edad</th>
                <th>Peso (Kg)</th>
                <th>Talla (cm)</th>
                <th>PC (cm)</th>
                <th>TA</th>
                <th>Temp</th>
                <th>Masa</th>
            </thead>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-sm-5"> <!-- Left Column -->
        <div class="row">
            <div class="col-sm-12">
                <br>
                <button type="button" class="btn btn-primary" onclick="graph('1');">Peso para la edad (0 a 36 Meses)</button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <br>
                <button type="button" class="btn btn-primary" onclick="graph('2');">Talla para la edad (0 a 36 Meses)</button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <br>
                <button type="button" class="btn btn-primary" onclick="graph('3');">Peso para la talla (0 a 36 Meses)</button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <br>
                <button type="button" class="btn btn-primary" onclick="graph('4');">Circ. Cefalica para la edad (0 a 36 Meses)</button>
            </div>
        </div>
    </div>
    <div class="col-sm-5"> <!-- right Column -->
        <div class="row">
            <div class="col-sm-12">
                <br>
                <button type="button" class="btn btn-primary" onclick="graph('5');">Peso para la estatura</button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <br>
                <button type="button" class="btn btn-primary" onclick="graph('6');">Peso para la edad (2 a 20 años)</button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <br>
                <button type="button" class="btn btn-primary" onclick="graph('7');">Estatura para la edad (2 a 20 años)</button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <br>
                <button type="button" class="btn btn-primary" onclick="graph('8');">IMC para la edad (2 a 20 años)</button>
            </div>
        </div>
    </div>
</div>
