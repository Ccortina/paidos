<%-- 
    Document   : activities
    Created on : May 28, 2014, 9:44:44 PM
    Author     : Ccortina_Mac
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<div class="row">
    <div id="divConsultationActivities" class="col-sm-6">
        <table id="tblActivities" class="hover cell-border">
            <thead>
                <tr>
                    <th>Actividad</th>
                    <th>Tipo</th>
                    <th>Costo</th>
                </tr>
            </thead>
        </table>
    </div>
    <div id="divConsultationSelectedActivities" class="col-sm-6">
        <table id="tblSelectedActivities" class="hover cell-border">
            <thead>
                <tr>
                    <th>Actividad</th>
                    <th>Costo</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
        </table>
    </div> <!-- Selected activities Div -->
    </div> <!-- Actividades div Row -->
    <div class="row">
    <div class="col-sm-6">
        <input type="button" class="btn btn-primary" value="Agregar" onclick="addSelectedAcitivitiesRow()"/>
        <a data-toggle="modal" href="#modalAddNewActivity" class="btn btn-primary">Nuevo</a>
        <a data-toggle="modal" href="#modalEditActivity" class="btn btn-primary" onclick="editSelectedActivity();">Modificar</a>
        <jsp:include page="addModifyActivities.jsp" />
    </div>
    <div class="col-sm-6">
    </div>
</div>