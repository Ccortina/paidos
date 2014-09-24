<%-- 
    Document   : PatientBackgroundTab
    Created on : May 18, 2014, 12:22:05 PM
    Author     : Ccortina_Mac
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<div class="row">
    <ul class="nav nav-pills">
            <li class="active"><a href="#pnpatologicos" data-toggle="tab">No patológicos</a></li>
            <li><a href="#alergicos" data-toggle="tab">Alérgicos</a></li>
            <li><a href="#patologicos" data-toggle="tab">Patológicos</a></li>
            <li><a href="#perinatales" data-toggle="tab">Perinatales</a></li>
            <li><a href="#desarrollo" data-toggle="tab">Desarrollo</a></li>
            <li><a href="#quirurgicos" data-toggle="tab">Quirúrgicos</a></li>
            <li><a href="#hereditarios" data-toggle="tab">Hereditarios y Familiares</a></li>
            <li><a href="#otros" data-toggle="tab">Otros</a></li>
    </ul>
    
    <div class="tab-content">
        <div id="pnpatologicos" class="tab-pane active"><jsp:include page="Background/NoPathological.jsp"/></div>
        <div id="alergicos" class="tab-pane"><jsp:include page="Background/Alergics.jsp"/></div>
        <div id="patologicos" class="tab-pane"><jsp:include page="Background/Pathological.jsp"/></div>
        <div id="perinatales" class="tab-pane"><jsp:include page="Background/Perinatals.jsp"/></div>
        <div id="desarrollo" class="tab-pane"><jsp:include page="Background/Development.jsp"/></div>
        <div id="quirurgicos" class="tab-pane"><jsp:include page="Background/Surgical.jsp"/></div>
        <div id="hereditarios" class="tab-pane"><jsp:include page="Background/Hereditary.jsp"/></div>
        <div id="otros" class="tab-pane"><jsp:include page="Background/Others.jsp"/></div> 
    </div>
</div>

