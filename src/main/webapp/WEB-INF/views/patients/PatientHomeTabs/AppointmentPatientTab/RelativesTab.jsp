<%-- 
    Document   : RelativesTab
    Created on : Jul 27, 2014, 9:48:53 PM
    Author     : Carlos Cortina
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-12">
        <table id="tblRelativesApp" class="hover row-border" >
            <thead>
                <th>Apellido Paterno</th>
                <th>Apellido Materno</th>
                <th>Nombre</th>
                <th>Relacion</th>
            </thead>
        </table>
    </div>
</div>
<form role="form" id="formRelativeDataApp">
<div class="row">
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeOcupationApp">Ocupacion</label>
            <input type="text" class="form-control" id="inputRelativeOcupationApp" placeholder="Ocupacion" name="ocupation" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeRfcApp">RFC</label>
            <input type="text" class="form-control" id="inputRelativeRfcApp" placeholder="RFC" name="rfc" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeCurpApp">CURP</label>
            <input type="text" class="form-control" id="inputRelativeCurpApp" placeholder="CURP" name="curp" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeEmailApp">Email</label>
            <input type="text" class="form-control" id="inputRelativeEmailApp" placeholder="Email" name="email" readonly="true"/>
        </div>
    </div>   
</div>
<div class="row">
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeTelephoneApp">Telefono de casa</label>
            <input type="text" class="form-control" id="inputRelativeTelephoneApp" placeholder="Telefono" name="homePhone" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeTelephone2App">Telefono de oficina</label>
            <input type="text" class="form-control" id="inputRelativeTelephone2App" placeholder="Telefono" name="officePhone" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeCellphonepApp">Celular</label>
            <input type="text" class="form-control" id="inputRelativeCellphoneApp" placeholder="Celular" name="cellPhone" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeExtensionpApp">Extension</label>
            <input type="text" class="form-control" id="inputRelativeExtensionApp" placeholder="Extension" name="officeExt" readonly="true"/>
        </div>
    </div>  
</div>
<div class="row">
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeTelphone3App">Telefono otro</label>
            <input type="text" class="form-control" id="inputRelativeTelephone3App" placeholder="Telfono" name="otherPhone" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeReligionApp">Religion</label>
            <select class="form-control" id="inputRelativeReligionApp" name="religion">
                <c:forEach var="religion" items="${religions}">
                    <option value="${religion.idReligion}"><c:out value="${religion.religion}" /></option>
                </c:forEach>
            </select>
        </div>
    </div> 
</div>
<div class="row">
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeStreeApp">Calle</label>
            <input type="text" class="form-control" id="inputRelativeStreetApp" placeholder="Calle" name="street" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeNumberApp">Numero</label>
            <input type="text" class="form-control" id="inputRelativeNumberApp" placeholder="Numero" name="number" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeColonyApp">Colonia</label>
            <input type="text" class="form-control" id="inputRelativeColonyApp" placeholder="Colonia" name="colony" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeCpApp">CP</label>
            <input type="text" class="form-control" id="inputRelativeCpApp" placeholder="CP" name="cp" readonly="true"/>
        </div>
    </div>  
</div>
<div class="row">
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeCityApp">Municipio</label>
            <input type="text" class="form-control" id="inputRelativeCityApp" placeholder="Ciudad" name="city" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeStateApp">Estado</label>
            <input type="text" class="form-control" id="inputRelativeStateApp" placeholder="Estado" name="state" readonly="true"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputRelativeCountryApp">Pais</label>
            <input type="text" class="form-control" id="inputRelativeCountryApp" placeholder="Pais" name="country" readonly="true"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <div class="form-group">
            <label for="inputRelativeNotesApp">Notas</label>
            <textarea class="form-control" id="inputRelativeNotesApp" placeholder="Notas" name="notes" readonly="true"></textarea>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputRelativeRecommendedApp">Recomendado por</label>
            <input type="text" class="form-control" id="inputRelativeRecommendedApp" placeholder="Recomendado" name="recommendedBy" readonly="true"/>
        </div>
    </div>
</div>                
</form>