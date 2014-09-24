<%-- 
    Document   : List
    Created on : Jul 28, 2014, 7:59:10 PM
    Author     : Carlos Cortina
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-12">
        <ul id="appointmentTabMenu" class="nav nav-tabs">
          <li class="active"><a href="#tabMain" data-toggle="tab">Hoy</a></li>
          <li><a href="#tabAppointments" data-toggle="tab">Detalles</a></li>
          <li><a href="#tabNewAppointment" data-toggle="tab">Nueva</a></li>
        </ul>
        <div class="tab-content">
            <div id="tabMain" class="tab-pane active"><jsp:include page="AppointmentMain.jsp" /></div>
            <div id="tabAppointments" class="tab-pane"><jsp:include page="AppointmentsList.jsp" /></div>
            <div id="tabNewAppointment" class="tab-pane"><jsp:include page="NewAppointment.jsp" /></div>
        </div>
</div>