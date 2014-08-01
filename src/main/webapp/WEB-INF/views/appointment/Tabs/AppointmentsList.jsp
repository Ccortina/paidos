<%-- 
    Document   : AppointmentsList
    Created on : Jul 29, 2014, 1:16:32 AM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-4">
        <table id="tblAppointmentsList" class="hover row-border">
            <thead>
                <th>Hora</th>
                <th>Paciente</th>
                <th>Motivo</th>
                <th>Estatus</th>
            </thead>
        </table>
    </div>
    <div class="col-sm-4">
        <form role="form" id="formModifyAppointment">
          <input type="hidden" id="inputIdPatientModifyApp" name="idPatient" value=""/>  
          <div class="form-group">
            <label for="inputDateModifyApp">Citas de la fecha:</label>
            <input type="text" class="form-control inputDate" id="inputDateModifyApp" placeholder="fecha" name="date"/>
          </div>
          <div class="form-group">
            <label for="inputTimeModifyApp">Hora:</label>
            <input type="text" class="form-control inputTime" id="inputTimeModifyApp" placeholder="hh:mm" name="startTime"/>
          </div>
          <div class="form-group">
            <label for="inputMotiveModifyApp">Motivo:</label>
            <input type="text" class="form-control" id="inputMotiveModifyApp" placeholder="Motivo" name="motive"/>
          </div>
          <div class="checkbox">
            <label>
              <input type="checkbox" name="immunization"> Inmunizacion
            </label>
          </div>
          <div class="form-group">
            <label for="selectStatusModifyApp">Estatus:</label>
            <select class="form-control" id="selectStatusModifyApp" name="idStatus">
                <c:forEach var="stat" items="${appointmentStatus}">
                    <option value="${stat.idAppointmentStatus}"><c:out value="${stat.status}" /></option>
                </c:forEach>
            </select>
          </div>  
          <div class="form-group">
            <label for="inputNotesModifyApp">Notas:</label>
            <textarea class="form-control" id="inputNotesModifyApp" placeholder="Notas" name="notes"></textarea>
          </div>
          <div class="form-group">
            <label for="inputDoctorApp">Doctor :</label>
            <select class="form-control" id="inputDoctorModifyApp" name="idDoctor">
                <c:forEach var="doctor" items="${doctors}">
                    <option value="${doctor.idUser}"><c:out value="${doctor.idStaffMember.name} ${doctor.idStaffMember.lastName}" /></option>
                </c:forEach>
            </select>
          </div>    
          <button type="submit" class="btn btn-primary">Modificar</button>
          </form>
    </div>
    <div class="col-sm-3">
        <div class="row">
            <div class="col-sm-12">
                <table id="tblConsultationMotivesModifyApp" class="row-border hover">
                    <thead>
                        <th>lastUsed</th>
                        <th>Motivo</th>
                    </thead>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <form role="form" id="formModifyAppointmentAdditionalInfo">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="inputWeightModifyApp">Peso (Kg):</label>
                            <input type="text" class="form-control inputDecimal" id="inputWeightModifyApp" placeholder="Peso" name="weight"/>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="inputSizeModifyApp">Talla (cm):</label>
                            <input type="text" class="form-control inputDecimal" id="inputTimeModifyApp" placeholder="Talla" name="size"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="inputPcModifyApp">PC (cm):</label>
                            <input type="text" class="form-control inputDecimal" id="inputMotiveModifyApp" placeholder="PC" name="pc"/>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="inputTempModifyApp">Temperatura (c):</label>
                            <input type="text" class="form-control inputDecimal" id="inputTempModifyApp" placeholder="Temperatura" name="temperature"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-sm-1">
                            <label for="inputPcApp">TA:</label>
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control inputDecimal" id="inputTA1App" placeholder="ta" name="ta"/>
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control inputDecimal" id="inputTA2App" placeholder="ta" name="ta2"/>
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control inputDecimal" id="inputTA3App" placeholder="ta" name="taAverage"/>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
