<%-- 
    Document   : AppointmentPatientTab
    Created on : Jul 26, 2014, 11:11:42 PM
    Author     : Carlos Cortina
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<div class="row">
    <div class="col-sm-12">
        <form class="form-inline" role="form">
            Paciente:
            <div class="form-group">
                <label class="sr-only" for="inputPatientNameApp">Paciente</label>
                <input type="text" class="form-control" id="inputPatientNameApp" disabled />
            </div>
            Fecha de Nacimiento:
            <div class="form-group">
                <label class="sr-only" for="inputPatientBirthdayApp">Email address</label>
                <input type="text" class="form-control" id="inputPatientBirthdayApp" disabled />
            </div>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <ul id="patientTabMenu" class="nav nav-tabs">
          <li class="active"><a href="#tabAppointmentApp" data-toggle="tab">Cita</a></li>
          <li><a href="#tabRelatives" data-toggle="tab">Familia</a></li>
          <li><a href="#tabImunizations" data-toggle="tab">Inmunizaciones</a></li>
          <li><a href="#tabAppointments" data-toggle="tab">Citas</a></li>
          <li><a href="#tabConsults" data-toggle="tab">Consultas</a></li>
          <li><a href="#tabMeasures" data-toggle="tab">Medidas</a></li>
        </ul>
        <div class="tab-content">
            <div id="tabAppointmentApp" class="tab-pane active"><jsp:include page="AppointmentPatientTab/AppointmentTab.jsp" /></div>
            <div id="tabRelatives" class="tab-pane"><jsp:include page="AppointmentPatientTab/RelativesTab.jsp" /></div>
            <div id="tabImunizations" class="tab-pane"><jsp:include page="AppointmentPatientTab/InmunizationTab.jsp" /></div>
            <div id="tabAppointments" class="tab-pane"><jsp:include page="AppointmentPatientTab/AppointmentsHistory.jsp" /></div>
            <div id="tabConsults" class="tab-pane"><jsp:include page="AppointmentPatientTab/ConsultationHistory.jsp" /></div>
            <div id="tabMeasures" class="tab-pane"><jsp:include page="AppointmentPatientTab/MeasuresHistory.jsp" /></div>
        </div>
</div>
