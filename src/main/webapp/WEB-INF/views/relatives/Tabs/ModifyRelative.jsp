<%-- 
    Document   : ModifyRelative
    Created on : Aug 1, 2014, 1:37:07 AM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<form id="formModifyRelative" >
<input type="hidden" value="" id="inputModifyRelativePatient" name="idRelative" />
<div class="row">
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputModifyRelativeFirstName">Primer nombre</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeFirstName" placeholder="Primer Nombre" name="firstName"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputModifyRelativeFatherLastName">Apellido paterno</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeFatherLastName" placeholder="Apellido Paterno" name="fatherLastName"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputModifyRelativeMotherLastName">Apellido materno</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeMotherLastName" placeholder="Apellido Materno" name="motherLastName"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeOccupation">Ocupacion</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeOccupation" placeholder="Ocupacion" name="occupation"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeRFC">RFC</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeRFC" placeholder="RFC" name="rfc"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeCURP">CURP</label>
            <input type="text" class="form-control inputCurp" id="inputModifyRelativeCURP" placeholder="CURP" name="curp"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeHomePhone">Telefono</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeHomePhone" placeholder="Telefono" name="homePhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeOfficePhone">Telefono de Oficina</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeOfficePhone" placeholder="Telefono de oficina" name="officePhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeExt">Ext.</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeExt" placeholder="Extension" name="officeExt"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeCellPhone">Celular</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeCellphone" placeholder="Celular" name="cellPhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeOtherPhone">Otro telefono</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeOtherPhone" placeholder="Otro telefono" name="otherPhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeEmail">Email</label>
            <input type="text" class="form-control inputEmail" id="inputModifyRelativeEmail" placeholder="Email" name="email"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputModifyRelativeStreet">Calle</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeStreet" placeholder="Calle" name="street"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputModifyRelativeNumber">Numero</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeNumber" placeholder="Numero" name="number"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputModifyRelativeColony">Colonia</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeColony" placeholder="Colonia" name="colony"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputModifyRelativeCP">Cp</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeCP" placeholder="Cp" name="cp"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeCellPhone">Celular</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeCellphone" placeholder="Celular" name="cellPhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeOtherPhone">Otro telefono</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeOtherPhone" placeholder="Otro telefono" name="otherPhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeEmail">Email</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeEmail" placeholder="Email" name="email"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeReligion">Religion</label>
            <select class="form-control" id="inputModifyRelativeReligionApp" name="religion">
                <c:forEach var="religion" items="${religions}">
                    <option value="${religion.idReligion}"><c:out value="${religion.religion}" /></option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeNotes">Notas</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeNotes" placeholder="Notas" name="notes"/>
        </div>
    </div>            
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeRecommendedBy">Email</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeRecommendedBy" placeholder="Recomendado por" name="recommendedBy"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputModifyRelativeGinecologist">Ginecologo</label>
            <input type="text" class="form-control inputNormal" id="inputModifyRelativeGinecologist" placeholder="Ginecologo" name="ginecologist"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-2">
        <button type="submit" class="btn btn-primary">Guardar</button>
    </div>
</div>    
</form>
