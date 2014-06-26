<%-- 
    Document   : home
    Created on : May 20, 2014, 4:46:38 PM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<c:url var="fullcalendarJs" value="/resources/js/JQueryPlugins/Fullcalendar/fullcalendar.min.js" />
<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />
<c:url var="fullcalendarCss" value="/resources/CSS/FullCalendar/fullcalendar.css" />
<c:url var="appointmentJs" value="/resources/js/Appointment/doctorAppointmentHome.js" />
<c:url var="fullcalendarLangJS" value="/resources/js/JQueryPlugins/Fullcalendar/es.js" />

<jsp:include page="../Includes/header.jsp"/>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=MacRoman">
        <title>Citas</title>
        <script src="${momentJs}" type="text/javascript"></script>
        <script src="${fullcalendarJs}" type="text/javascript"></script>
        <link href="${fullcalendarCss}" rel="stylesheet" />
        <script src="${appointmentJs}" type="text/javascript"></script>
        <script src="${fullcalendarLangJS}" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div id='calendar'></div>
            <br>
            <div class="row">
                <div class="col-sm-1" style="background-color:rgb(221, 221, 221);">___</div>
                <div class="col-sm-11"><p>Completa</p></div>    
            </div>
            <div class="row">
                <div class="col-sm-1" style="background-color:rgb(58, 135, 173);">___</div>
                <div class="col-sm-11"><p>Confirmada</p></div>    
            </div>
            <div class="row">
                <div class="col-sm-1" style="background-color:rgb(91, 170, 58);">___</div>
                <div class="col-sm-11"><p>En proceso</p></div>    
            </div>
            <div class="row">
                <div class="col-sm-1" style="background-color:rgb(237, 237, 59);">___</div>
                <div class="col-sm-11"><p>Horario reservado,Hay que Llamarle,No confirmada,No contesta,Se dejo recado</p></div>    
            </div>
            <div class="row">
                <div class="col-sm-1" style="background-color:rgb(224, 71, 71);">___</div>
                <div class="col-sm-11"><p>No se presento, Cancelada</p></div>    
            </div>
            <div class="row">
                <div class="col-sm-1" style="background-color:rgb(65, 204, 187);">___</div>
                <div class="col-sm-11"><p>Programada</p></div>    
            </div>
        </div>
    </body>
</html>
