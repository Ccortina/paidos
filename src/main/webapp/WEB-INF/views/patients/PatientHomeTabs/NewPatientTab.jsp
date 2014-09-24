<%-- 
    Document   : NewPatientTab
    Created on : Jul 24, 2014, 11:41:10 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-sm-6">
        <form role="form" id="formNewPatient">
          <div class="form-group">
            <label for="inputNewPatientName">Nombre</label>
            <input type="text" class="form-control inputNormal" id="inputNewPatientName" placeholder="Nombre" name="fName">
          </div>
          <div class="form-group">
            <label for="inputNewPatientFLName">A. Paterno</label>
            <input type="text" class="form-control inputNormal" id="inputNewPatientFLName" placeholder="A. Paterno" name="flName">
          </div>
          <div class="form-group">
            <label for="inputNewPatientMLName">A. Materno</label>
            <input type="text" class="form-control inputNormal" id="inputNewPatientMLName" placeholder="A. Materno" name="mlName">
          </div>
          <div class="form-group">
            <label for="inputNewPatientBirthday">Fecha de Nacimiento</label>
            <input type="text" class="form-control inputDate" id="inputNewPatientBirthday" placeholder="dd/mm/aaaa" name="birthday">
          </div>
          <div class="form-group">
            <label for="inputNewPatientGender">Sexo</label>
            <select class="form-control" id="inputNewPatientGender" name="gender">
                <c:forEach var="gender" items="${genders}">
                    <option value="${gender.idGender}"><c:out value="${gender.gender}" /></option>
                </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label for="inputNewPatientCurp">CURP</label>
            <input type="text" class="form-control inputCurp" id="inputNewPatientCurp" placeholder="CURP" name="curp">
          </div>
          <div class="form-group">
            <label for="inputNewPatientNotes">Notas</label>
            <textarea class="form-control" id="inputNewPatientNotes" rows="3" name="notes"></textarea>
          </div>
          <div class="form-group">
            <label for="inputNewPatientMDoctor">Doctor de cabecera</label>
            <select class="form-control" id="inputNewPatientMDoctor" name="doctor">
                <c:forEach var="doctor" items="${doctors}">
                    <option value="${doctor.idUser}"><c:out value="${doctor.idStaffMember.name} ${doctor.idStaffMember.lastName}" /></option>
                </c:forEach>
            </select>
          </div>  
            <!--<input type="button" class="btn btn-primary" onclick="addNewPatient()" value="Guardar"></button>-->
            <button type="submit" class="btn btn-primary">Guardar</button>
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
                      <table id="tblRelatives" class="hover row-border">
                          <thead>
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
                        <select class="form-control" id="selectNewPatientRRelationship" name="idRelationship">
                            <c:forEach items="${relationshipType}" var="type">
                                <option value="${type.idRelationship}">${type.relationship}</option>
                            </c:forEach>
                        </select>
                      </div>
                  </div>
                  <div class="col-sm-5">
                    <input type="button" value="Asociar" class="btn btn-primary" onclick="addPatientRelativeRelationship()">
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-12">
                        <table id="tblSelectedRelatives" clas="row-border hover">
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
                    <input type="button" value="Desasociar" class="btn btn-danger" onclick="deletePatientRelativeRelationship()">
                  </div>
              </div>
          </div>
        </div>
    </div>
</div>