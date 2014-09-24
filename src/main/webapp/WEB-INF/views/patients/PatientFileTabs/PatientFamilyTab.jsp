<%-- 
    Document   : PatientFamilyTab
    Created on : May 18, 2014, 2:32:33 PM
    Author     : Ccortina_Mac
--%>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-12">
    <div class="row">
        <div class="col-sm-10">
            <table id="tblPatientRelativesList" class="row-border hover">
                <thead>
                    <tr>
                        <th>Apellido Paterno</th>
                        <th>Apellido Materno</th>
                        <th>Nombre</th>
                        <th>Relacion</th>
                        <th>Desasociar</th>
                    </tr>
                </thead>
            </table>
        </div>
        <div class="col-sm-2">
            <div class="row">
                <div class="col-sm-12">

                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <button class="btn btn-primary" data-toggle="modal" data-target="#modalPatientFamilyAddRelative">Agregar</button>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-sm-12">

                </div>
            </div>
        </div>
    </div>
    <form id="formModifyRelative">
    <input type="hidden" id="hiddenIdRelative" name="idRelative"value=""/>    
    <div class="row">
        <div class="col-sm-3">
            <div class="form-group">
                <label for="inputNewRelativeFirstName">Primer nombre</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeFirstName" placeholder="Primer Nombre" name="firstName" />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="inputModifyRelativeFatherLastName">Apellido paterno</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeFatherLastName" placeholder="Apellido Paterno" name="fatherLastName" />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="inputModifyRelativeMotherLastName">Apellido materno</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeMotherLastName" placeholder="Apellido Materno" name="motherLastName" />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="selectPatientFamilyRelationship">Relaci—n</label>
                <select class="form-control" id="selectPatientFamilyRelationship" name="idRelationship" >
                    <c:forEach items="${relationshipType}" var="type">
                        <option value="${type.idRelationship}">${type.relationship}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeOccupation">Ocupacion</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeOccupation" placeholder="Ocupacion" name="occupation" />
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeRFC">RFC</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeRFC" placeholder="RFC" name="rfc" />
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeCURP">CURP</label>
                <input type="text" class="form-control inputCurp" id="inputModifyRelativeCURP" placeholder="CURP" name="curp" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeHomePhone">Telefono</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeHomePhone" placeholder="Telefono" name="homePhone" />
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeOfficePhone">Telefono de Oficina</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeOfficePhone" placeholder="Telefono de oficina" name="officePhone" />
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeExt">Ext.</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeExt" placeholder="Extension" name="officeExt" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeCellPhone">Celular</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeCellphone" placeholder="Celular" name="cellPhone" />
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
                <input type="text" class="form-control inputEmail" id="inputModifyRelativeEmail" placeholder="Email" name="email" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <div class="form-group">
                <label for="inputModifyRelativeStreet">Calle</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeStreet" placeholder="Calle" name="street" />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="inputModifyRelativeNumber">Numero</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeNumber" placeholder="Numero" name="number" />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="inputModifyRelativeColony">Colonia</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeColony" placeholder="Colonia" name="colony" />
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <label for="inputModifyRelativeCP">Cp</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeCP" placeholder="Cp" name="cp" />
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeReligion">Religion</label>
                <select class="form-control" id="inputRelativeReligionApp" name="religion" >
                    <c:forEach items="${religionType}" var="type">
                        <option value="${type.idReligion}"><c:out value="${type.religion}" /></option>
                    </c:forEach>    
                </select>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeNotes">Notas</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeNotes" placeholder="Notas" name="notes" />
            </div>
        </div>   
    </div>
    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeRecommendedBy">Recomendado por</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeRecommendedBy" placeholder="Recomendado por" name="recommendedBy" />
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label for="inputModifyRelativeGinecologist">Ginecologo</label>
                <input type="text" class="form-control inputNormal" id="inputModifyRelativeGinecologist" placeholder="Ginecologo" name="ginecologist" />
            </div>
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-primary">Guardar</button>
        </div>
    </div>
    </form>
    </div>
</div>

<!-- Add relative Modal -->
<div class="modal fade" id="modalPatientFamilyAddRelative" tabindex="-1" role="dialog" aria-labelledby="modalAddRelativeLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                    <h4 class="modal-title">Agregar un familiar</h4>
            </div>
            <div class="modal-body">
                    <div class="row">
                        <table id="tblPatientFamilyAllRelatives" clas="row-border hover">
                            <thead>
                                <tr>
                                    <th>Paterno</th>
                                    <th>Materno</th>
                                    <th>Nombre</th>
                                    <th>RFC</th>
                                    <th>Telefono</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="form-group">
                                <label for="selectAddPatientFamilyRelationship">Relaci—n</label>
                                <select class="form-control" id="selectAddPatientFamilyRelationship" name="idRelationship">
                                    <c:forEach var="relationship" items="${relationshipType}">
                                        <option value="${relationship.idRelationship}"><c:out value="${relationship.relationship}" /></option>
                                    </c:forEach>    
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <input type="button" value="Asociar" class="btn btn-primary" onclick="addPatientRelative()" data-dismiss="modal">
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <table id="tblPatientFamilyPatientRelatives" clas="row-border hover">
                            <thead>
                                <tr>
                                    <th>Paterno</th>
                                    <th>Materno</th>
                                    <th>Nombre</th>
                                    <th>RFC</th>
                                    <th>Relaci—n</th>
                                </tr>
                            </thead>
                        </table>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                    </div>
            </div>
        </div>
    </div>
</div>