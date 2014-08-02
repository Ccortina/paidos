<%-- 
    Document   : NewRelative
    Created on : Jul 31, 2014, 10:21:34 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<form id="formNewRelative" >
<div class="row">
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputNewRelativeFirstName">Primer nombre</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeFirstName" placeholder="Primer Nombre" name="firstName"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputNewRelativeSecondName">Segundo nombre</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeSecondName" placeholder="Segundo Nombre" name="secondName"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputNewRelativeFatherLastName">Apellido paterno</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeFatherLastName" placeholder="Apellido Paterno" name="fatherLastName"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputNewRelativeMotherLastName">Apellido materno</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeMotherLastName" placeholder="Apellido Materno" name="motherLastName"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeOccupation">Ocupacion</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeOccupation" placeholder="Ocupacion" name="occupation"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeRFC">RFC</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeRFC" placeholder="RFC" name="rfc"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeCURP">CURP</label>
            <input type="text" class="form-control inputCurp" id="inputNewRelativeCURP" placeholder="CURP" name="curp"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeHomePhone">Telefono</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeHomePhone" placeholder="Telefono" name="homePhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeOfficePhone">Telefono de Oficina</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeOfficePhone" placeholder="Telefono de oficina" name="officePhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeExt">Ext.</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeExt" placeholder="Extension" name="officeExt"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeCellPhone">Celular</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeCellphone" placeholder="Celular" name="cellPhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeOtherPhone">Otro telefono</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeOtherPhone" placeholder="Otro telefono" name="otherPhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeEmail">Email</label>
            <input type="text" class="form-control inputEmail" id="inputNewRelativeEmail" placeholder="Email" name="email"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputNewRelativeStreet">Calle</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeStreet" placeholder="Calle" name="street"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputNewRelativeNumber">Numero</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeNumber" placeholder="Numero" name="number"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputNewRelativeColony">Colonia</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeColony" placeholder="Colonia" name="colony"/>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="form-group">
            <label for="inputNewRelativeCP">Cp</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeCP" placeholder="Cp" name="cp"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeCellPhone">Celular</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeCellphone" placeholder="Celular" name="cellPhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeOtherPhone">Otro telefono</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeOtherPhone" placeholder="Otro telefono" name="otherPhone"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeEmail">Email</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeEmail" placeholder="Email" name="email"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeReligion">Religion</label>
            <select class="form-control" id="inputRelativeReligionApp" name="religion">
                <c:forEach var="religion" items="${religions}">
                    <option value="${religion.id}"><c:out value="${religion.religion}" /></option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeNotes">Notas</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeNotes" placeholder="Notas" name="notes"/>
        </div>
    </div>            
</div>
<div class="row">
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeRecommendedBy">Email</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeRecommendedBy" placeholder="Recomendado por" name="recommendedBy"/>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="form-group">
            <label for="inputNewRelativeGinecologist">Ginecologo</label>
            <input type="text" class="form-control inputNormal" id="inputNewRelativeGinecologist" placeholder="Ginecologo" name="ginecologist"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-2">
        <button type="submit" class="btn btn-primary">Guardar</button>
    </div>
</div>    
</form>