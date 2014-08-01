<%-- 
    Document   : home
    Created on : May 20, 2014, 4:46:38 PM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />
<c:url var="dtModCSS" value="/resources/CSS/DataTables/datatables.mod.css" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="fullcalendarJs" value="/resources/js/JQueryPlugins/Fullcalendar/fullcalendar.min.js" />
<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />
<c:url var="fullcalendarCss" value="/resources/CSS/FullCalendar/fullcalendar.css" />
<c:url var="appointmentJs" value="/resources/js/Appointment/doctorAppointmentHome.js" />
<c:url var="appointmentDoctorJs" value="/resources/js/Appointment/appointment.js" />
<c:url var="fullcalendarLangJS" value="/resources/js/JQueryPlugins/Fullcalendar/es.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="appointmentJS" value="/resources/js/Appointment/Tabs/NewAppointment.js" />
<c:url var="relativesJS" value="/resources/js/Appointment/Tabs/Relatives.js" />
<c:url var="inmunizationJS" value="/resources/js/Appointment/Tabs/Inmunization.js" />
<c:url var="appListJS" value="/resources/js/Appointment/Tabs/AppointmentsList.js" />
<c:url var="modAppJS" value="/resources/js/Appointment/Tabs/ModifyAppointment.js" />

<link href="${fullcalendarCss}" rel="stylesheet" />
<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />
<link href="${bvCSS}" rel="stylesheet" />
<input type='hidden' id='idUser' value=''/>

<sec:authorize access="hasAnyRole('Administrador','Doctor')">
    <input type="hidden" id="userrole" value="Doctor"/>
</sec:authorize>
<sec:authorize access="hasRole('Asistente')">
    <input type="hidden" id="userrole" value="Asistente"/>
</sec:authorize>
    
<div class="container">
    <div id='calendar'>    
    </div>
</div>

<script src="${momentJs}" type="text/javascript"></script>
<script src="${fullcalendarJs}" type="text/javascript"></script>
<script src="${appointmentJs}" type="text/javascript"></script>
<script src="${fullcalendarLangJS}" type="text/javascript"></script>
<script src="${bootboxJs}" type="text/javascript"></script>
<script src="${dataTablesJS}" type="text/javascript"></script>
<script src="${appointmentJS}" type="text/javascript"></script>
<script src="${relativesJS}" type="text/javascript"></script>
<script src="${inmunizationJS}" type="text/javascript"></script>
<script src="${bvJs}" type="text/javascript"></script>
<script src="${appListJS}" type="text/javascript"></script>
<script src="${inputmaskJs}" type="text/javascript"></script>
<script src="${inputmaskDateJs}" type="text/javascript"></script>
<script src="${inputmaskRegexJs}" type="text/javascript"></script>
<script src="${modAppJS}" type="text/javascript"></script>