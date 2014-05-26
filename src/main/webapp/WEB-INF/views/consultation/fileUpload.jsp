<%-- 
    Document   : fileUpload
    Created on : May 23, 2014, 11:02:50 AM
    Author     : Ccortina_Mac
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<div class="row">
    <form id="formUploadFile" method="POST" role ="form" enctype="multipart/form-data" >
        <div class="form-group">
            <label for="file2">Seleccionar un archivo para subir:</label>
            <input class="form-control" name="file2" id="file2" type="file"/>
        </div>
        <button class="btn btn-primary" id="btnUploadFile" value="Subir">Subir</button>
    </form>
</div>
<div class="row"><strong>Doble click en el archivo para abrirlo.</strong></div>
<div class="row">
    <table id="tblConsultationPatientDocuments" class="hover row-border">
        <thead>
            <tr>
                <th>Documento</th>
                <th>Eliminar</th>
            </tr>
        </thead>
    </table>
</div>
    
