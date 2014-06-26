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
<c:url var="appointmentJs" value="/resources/js/Appointment/appointment.js" />

<jsp:include page="../Includes/header.jsp"/>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=MacRoman">
        <title>Citas</title>
        <script src="${momentJs}" type="text/javascript"></script>
        <script src="${fullcalendarJs}" type="text/javascript"></script>
        <link href="${fullcalendarCss}" rel="stylesheet" />
        <script src="${appointmentJs}" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div id='calendar'></div>
        </div>
    </body>
</html>
