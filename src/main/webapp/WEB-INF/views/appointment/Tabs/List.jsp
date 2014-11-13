<%-- 
    Document   : List
    Created on : Jul 28, 2014, 7:59:10 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-12">
        <ul id="appointmentTabMenu" class="nav nav-tabs">
          <li class="active"><a href="#tabMain" data-toggle="tab">Hoy</a></li>
          <li><a href="#tabAppointments" data-toggle="tab">Detalles</a></li>
          <sec:authorize access="hasAnyRole('Doctor','Pacientes_Citas_2')">
          <li><a href="#tabNewAppointment" data-toggle="tab">Nueva</a></li>
          </sec:authorize>
        </ul>
        <div class="tab-content">
            <div id="tabMain" class="tab-pane active"><jsp:include page="AppointmentMain.jsp" /></div>
            <div id="tabAppointments" class="tab-pane"><jsp:include page="AppointmentsList.jsp" /></div>
            <sec:authorize access="hasAnyRole('Doctor','Pacientes_Citas_2')">
            <div id="tabNewAppointment" class="tab-pane"><jsp:include page="NewAppointment.jsp" /></div>
            </sec:authorize>
        </div>
</div>