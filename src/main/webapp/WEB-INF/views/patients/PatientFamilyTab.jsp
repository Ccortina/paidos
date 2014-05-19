<%-- 
    Document   : PatientFamilyTab
    Created on : May 18, 2014, 2:32:33 PM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<br>
<div class="row">
    <div class="col-sm-10">
        <table id="tblPatientRelativesList" class="row-border hover">
            <thead>
                <tr>
                    <th>RelativeId</th>
                    <th>Nombre</th>
                    <th>Relacion</th>
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
                <a data-toggle="modal" href="#modalPatientFamilyAddRelative" class="btn btn-primary">Agregar</a>  
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12">
                <input type="button" value="Modificar" class="btn btn-primary">  
            </div>
        </div>
    </div>
</div>
<br>
<div class="row">
    <div class="col-sm-12">
        <form role ="form" id="formPatientFamilyDisplay" method="post">
            <div class="row">
                <div class="col-sm-2">
                   <div class="form-group">
                        <label for="ocupation">Ocupacion : </label>
                        <input type="text" name="occupation" class="form-control input-sm" disabled/>
                    </div> 
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="rfc">RFC : </label>
                        <input type="text" name="rfc" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="curp">CURP : </label>
                        <input type="text" name="curp" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="email">Email : </label>
                        <input type="text" name="email" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="religion">Religion : </label>
                        <input type="text" name="religion" class="form-control input-sm" disabled/>
                    </div>
                </div>
            </div><!-- End 1st row -->
            <div class="row"><!-- Begin 2nd row-->
                <div class="col-sm-2">
                   <div class="form-group">
                        <label for="homePhone">Tel. Casa : </label>
                        <input type="number" name="homePhone" class="form-control input-sm" disabled/>
                    </div> 
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="officePhone">Tel. Oficina : </label>
                        <input type="number" name="officePhone" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="officeExt">Ext : </label>
                        <input type="number" name="officeExt" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="cellPhone">Celular : </label>
                        <input type="number" name="cellPhone" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="otherPhone">Otro : </label>
                        <input type="number" name="otherPhone" class="form-control input-sm" disabled/>
                    </div>
                </div>
            </div><!-- End 2nd row -->
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="street">Calle : </label>
                        <input type="text" name="street" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="number">Numero Casa : </label>
                        <input type="number" name="number" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="colony">Colonia : </label>
                        <input type="text" name="colony" class="form-control input-sm" disabled/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="city">Municipio : </label>
                        <input type="text" name="city" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="state">Estado : </label>
                        <input type="number" name="state" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="country">Pais : </label>
                        <input type="text" name="country" class="form-control input-sm" disabled/>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="cp">C—digo Postal : </label>
                        <input type="number" name="cp" class="form-control input-sm" disabled/>
                    </div>
                </div>
            </div><!-- row -->
            <div class="row">
                <div class="col-sm-10">
                    <div class="form-group">
                        <label for="notes">Notas : </label>
                        <textarea name="notes" class="form-control" rows="2" disabled></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-10">
                    <div class="form-group">
                        <label for="recommendedBy">Recomendado por : </label>
                        <input type="text" name="recommendedBy" class="form-control input-sm" disabled/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- Add relative Modal -->
<div class="modal fade" id="modalPatientFamilyAddRelative" tabindex="-1" role="dialog" aria-labelledby="modalAddRelativeLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Agregar un familiar</h4>
			</div>
                    <div class="modal-body">
                        <form role="form" id="formPatientFamilyAddRelative" action="../addPatientFamilyRelative" method="post" >
                            <input type="hidden" id="hiddenPatienFamilySelectedRelative" name="idRelative">
                            <input type="hidden" id="hiddenPatientFamilyId" name="idPatient"  value="${patient.id}">
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
                            <div class="row">
                                <div class="col-sm-5">
                                    <div class="form-group">
                                        <label for="selectPatientFamilyRelationship">Relaci—n</label>
                                        <select class="form-control" id="selectPatientFamilyRelationship" name="idRelationship">
                                            <c:forEach items="${relationshipType}" var="type">
                                                <option value="${type.id}">${type.relationship}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-5">
                                    <input type="button" value="Asociar" class="btn btn-primary" onclick="ajaxCall('formPatientFamilyAddRelative');">
                                </div>
                            </div>
                            <br>
                            <table id="tblPatientFamilyPatientRelatives" clas="row-border hover">
                                <thead>
                                    <tr>
                                        <th>id</th>
                                        <th>Paterno</th>
                                        <th>Materno</th>
                                        <th>Nombre</th>
                                        <th>RFC</th>
                                        <th>Relaci—n</th>
                                        <th>Desasociar</th>
                                    </tr>
                                </thead>
                            </table>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                        </form>
                    </div><!-- Modal body -->
                </div><!-- Modal content -->
        </div>
</div>

