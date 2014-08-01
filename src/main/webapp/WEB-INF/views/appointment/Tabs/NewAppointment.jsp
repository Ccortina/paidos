<%-- 
    Document   : AppointmentTab
    Created on : Jul 26, 2014, 11:31:36 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-4">
        <div class="row">
            <div class="col-sm-12">
                <table id="tblAppointmentsListNewApp" class="hover row-border">
                    <thead>
                        <th>Hora</th>
                        <th>Paciente</th>
                        <th>Motivo</th>
                        <th>Estatus</th>
                    </thead>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <table id="tblPatientsNewApp" class="hover row-border">
                    <thead>
                        <th>Nombre</th>
                        <th>Fecha Nacimiento</th>
                        <th>Sexo</th>
                    </thead>
                </table>
                
            </div>
        </div>
    </div>
    <div class="col-sm-3">
        <form role="form" id="formNewAppointment">
          <input type="hidden" id="inputIdPatientNewApp" name="idPatient" value=""/>  
          <div class="form-group">
            <label for="inputDatNewApp">Fecha de la cita:</label>
            <input type="text" class="form-control inputDate" id="inputDatNewApp" placeholder="fecha" name="date"/>
          </div>
          <div class="form-group">
            <label for="inputTimeNewAppApp">Hora:</label>
            <input type="text" class="form-control inputTime" id="inputTimeNewApp" placeholder="hh:mm" name="startTime"/>
          </div>
          <div class="form-group">
            <label for="inputMotiveNewApp">Motivo:</label>
            <input type="text" class="form-control" id="inputMotivNewApp" placeholder="Motivo" name="motive"/>
          </div>
          <div class="checkbox" >
            <label>
              <input type="checkbox" name="immunization" id="inpuInmunizationNewApp"> Inmunizacion
            </label>
          </div>
          <div class="form-group">
            <label for="selectStatusNewAppApp">Estatus:</label>
            <select class="form-control" id="selectStatusNewApp" name="idStatus">
                <c:forEach var="stat" items="${appointmentStatus}">
                    <option value="${stat.idAppointmentStatus}"><c:out value="${stat.status}" /></option>
                </c:forEach>
            </select>
          </div>  
          <div class="form-group">
            <label for="inputNotesNewApp">Notas:</label>
            <textarea class="form-control" id="inputNotesNewApp" placeholder="Notas" name="notes"></textarea>
          </div>
          <div class="form-group">
            <label for="inputDoctorNewApp">Doctor :</label>
            <select class="form-control" id="inputDoctorNewApp" name="idDoctor">
                <c:forEach var="doctor" items="${doctors}">
                    <option value="${doctor.idUser}"><c:out value="${doctor.idStaffMember.name} ${doctor.idStaffMember.lastName}" /></option>
                </c:forEach>
            </select>
          </div>    
          <button type="submit" class="btn btn-primary">Guardar</button>
          </form>
    </div>
    <div class="col-sm-3">
        <div class="row">
            <div class="col-sm-12">
                <table id="tblConsultationMotivesNewApp" class="row-border hover">
                    <thead>
                        <th>lastUsed</th>
                        <th>Motivo</th>
                    </thead>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <form role="form" id="formNewAppointmentAdditionalInfo">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="inputWeightNewApp">Peso (Kg):</label>
                            <input type="text" class="form-control inputDecimal" id="inputWeightNewApp" placeholder="Peso" name="weight"/>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="inputSizeNewApp">Talla (cm):</label>
                            <input type="text" class="form-control inputDecimal" id="inputTimeNewApp" placeholder="Talla" name="size"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="inputPcNewApp">PC (cm):</label>
                            <input type="text" class="form-control inputDecimal" id="inputMotiveNewApp" placeholder="PC" name="pc"/>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label for="inputTempNewApp">Temperatura (c):</label>
                            <input type="text" class="form-control inputDecimal" id="inputTempNewApp" placeholder="Temperatura" name="temperature"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <div class="col-sm-1">
                            <label for="inputPcNewApp">TA:</label>
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
