<%-- 
    Document   : ModifyPatientTab
    Created on : Jul 25, 2014, 11:31:55 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-sm-6">
        <form role="form" id="formModifyPatient">
          <input type="hidden" id="hiddenModifyIdPatient" name="idPatient" />  
          <div class="form-group">
            <label for="inputModifyPatientName">Nombre</label>
            <input type="text" class="form-control inputNormal" id="inputModifyPatientName" placeholder="Nombre" name="fName">
          </div>
          <div class="form-group">
            <label for="inputModifyPatientSName">S. Nombre</label>
            <input type="text" class="form-control inputNormal" id="inputModifyPatientSName" placeholder="S. Nombre" name="sName">
          </div>
          <div class="form-group">
            <label for="inputModifyPatientFLName">A. Paterno</label>
            <input type="text" class="form-control inputNormal" id="inputModifyPatientFLName" placeholder="A. Paterno" name="flName">
          </div>
          <div class="form-group">
            <label for="inputModifyPatientMLName">A. Materno</label>
            <input type="text" class="form-control inputNormal" id="inputModifyPatientMLName" placeholder="A. Materno" name="mlName">
          </div>
          <div class="form-group">
            <label for="inputModifyPatientBirthday">Fecha de Nacimiento</label>
            <input type="text" class="form-control inputDate" id="inputModifyPatientBirthday" placeholder="dd/mm/aaaa" name="birthday">
          </div>
          <div class="form-group">
            <label for="inputModifyPatientGender">Sexo</label>
            <select class="form-control" id="inputModifyPatientGender" name="gender">
                <option value="masculino">Masculino</option>
                <option value="femenino">Femenino</option>
                <option value="indefinido">Indefinido</option>
            </select>
          </div>
          <div class="form-group">
            <label for="inputModifyPatientCurp">CURP</label>
            <input type="text" class="form-control inputCurp" id="inputModifyPatientCurp" placeholder="CURP" name="curp">
          </div>
          <div class="form-group">
            <label for="inputModifyPatientNotes">Notas</label>
            <textarea class="form-control" id="inputModifyPatientNotes" rows="3" name="notes"></textarea>
          </div>
          <div class="form-group">
            <label for="inputModifyPatientMDoctor">Doctor de cabecera</label>
            <select class="form-control" id="inputModifyPatientMDoctor" name="doctor">
                <c:forEach var="doctor" items="${doctors}">
                    <option value="${doctor.idStaffMember.idStaffMember}"><c:out value="${doctor.idStaffMember.name} ${doctor.idStaffMember.lastName}" /></option>
                </c:forEach>
            </select>
          </div>  
            <!--<input type="button" class="btn btn-primary" onclick="addNewPatient()" value="Guardar"></button>-->
            <input type="button" class="btn btn-primary" value="Guardar" onclick="saveModifiyPatient()"/>
        </form>
    </div>
    <div class="col-sm-6">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">Asociar familiares con el paciente</h4>
          </div>
          <div class="panel-body">
              <div class="row">
                  <div class="col-sm-12">
                      <table id="tblRelatives2" class="hover row-border">
                          <thead>
                            <th>idRelative</th>
                            <th>Paterno</th>
                            <th>Materno</th>
                            <th>Nombre</th>
                            <th>RFC</th>
                            <th>Telefono</th>
                          </thead>
                      </table>
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-4">
                      <div class="form-group">
                        <label for="selectNewPatientRRelationship">Relación</label>
                        <select class="form-control" id="selectModifyPatientRRelationship" name="idRelationship">
                            <c:forEach items="${relationshipType}" var="type">
                                <option value="${type.idRelationship}">${type.relationship}</option>
                            </c:forEach>
                        </select>
                      </div>
                  </div>
                  <div class="col-sm-5">
                    <input type="button" value="Asociar" class="btn btn-primary" onclick="addPatientRelativeRelationshipM()">
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-12">
                        <table id="tblSelectedRelatives2" clas="row-border hover">
                            <thead>
                                <tr>
                                    <th>Paterno</th>
                                    <th>Materno</th>
                                    <th>Nombre</th>
                                    <th>RFC</th>
                                    <th>Relación</th>
                                </tr>
                            </thead>
                        </table>
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-12">
                    <input type="button" value="Desasociar" class="btn btn-danger" onclick="deletePatientRelativeRelationshipM()">
                  </div>
              </div>
          </div>
        </div>
    </div>
</div>