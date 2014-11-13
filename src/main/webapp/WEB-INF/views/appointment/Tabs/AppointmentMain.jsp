<%-- 
    Document   : AppointmentMain
    Created on : Sep 4, 2014, 10:42:09 AM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-4">
        <div class="row">
            <div class="col-sm-12">
                <div class="row"> <!--The options -->
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label>Mes</label>
                            <select id="selectMonth" class="form-control">
                                <option value="0">Enero</option>
                                <option value="1">Febrero</option>
                                <option value="2">Marzo</option>
                                <option value="3">Abril</option>
                                <option value="4">Mayo</option>
                                <option value="5">Junio</option>
                                <option value="6">Julio</option>
                                <option value="7">Agosto</option>
                                <option value="8">Septiembre</option>
                                <option value="9">Octubre</option>
                                <option value="10">Noviembre</option>
                                <option value="11">Diciembre</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label>Año</label>
                            <select id="selectYear" class="form-control"></select>
                        </div>
                    </div>
                </div>
                <div class="row"> <!-- the chart -->
                    <div id="graphPlaceholder" style="width:500px;height:500px"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="row">
            <form id="formMainDate">
                <input type="text" id="inputMainDate" name="date"/>
            </form>
        </div>
        <div class="row">
            <table id="tblMain" class="hover row-border">
                <thead>
                    <th>Hora</th>
                    <th>Paciente</th>
                    <th>Motivo</th>
                    <th>Estatus</th>
                </thead>
            </table>
        </div>
        <div class="row">
            <sec:authorize access="hasAnyRole('Doctor')">
            <div class="col-sm-3">
                <button type="button" class="btn btn-primary" onclick="consult()">Ir a consulta</button>
            </div>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('Doctor','Pacientes_Pacientes_4')">
            <div class="col-sm-2">
                <button type="button" class="btn btn-primary" onclick="patientFile()">Ir al expediente</button>
            </div>
            </sec:authorize>
        </div>
    </div>
    <div class="col-sm-4">
        
    </div>
</div>