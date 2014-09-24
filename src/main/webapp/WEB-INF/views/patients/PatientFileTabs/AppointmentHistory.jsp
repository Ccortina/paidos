<%-- 
    Document   : AppointmentHistory
    Created on : Sep 8, 2014, 1:38:57 AM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-12">
        <table id="tblAppointmentsHistory" class="hover row-border">
            <thead>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Estatus</th>
                <th>Inmunizacion</th>
                <th>Motivo</th>
            </thead>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-sm-2">
        <button type="button" class="btn btn-primary" onclick="loadModifyAppointment()">Modificar</button>
    </div>
    <div class="col-sm-4">
        <button type="button" class="btn btn-danger" onclick="deleteAllAppointments()">Borrar citas programadas automaticamente por inmunizaciones</button>
    </div>
</div>

<div class="modal fade" id="modalModifyAppointment" tabindex="-1" data-backdrop="static" data-keyboard="false" role="dialog" aria-labelledby="modalModifyAppointment" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Modificar Cita</h4>
      </div>
        <form role="form" id="formAppointment">
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-6">
                    
                      <div class="form-group">
                        <label for="inputDateApp">Citas de la fecha:</label>
                        <input type="text" class="form-control inputDate" id="inputDateApp" placeholder="fecha" name="date"/>
                      </div>
                      <div class="form-group">
                        <label for="inputTimeApp">Hora:</label>
                        <input type="text" class="form-control inputTime" id="inputTimeApp" placeholder="hh:mm" name="startTime"/>
                      </div>
                      <div class="form-group">
                        <label for="inputMotiveApp">Motivo:</label>
                        <input type="text" class="form-control" id="inputMotiveApp" placeholder="Motivo" name="motive"/>
                      </div>
                      <div class="checkbox">
                        <label>
                          <input type="checkbox" name="immunization"> Inmunizacion
                        </label>
                      </div>
                      <div class="form-group">
                        <label for="selectStatusApp">Estatus:</label>
                        <select class="form-control" id="selectStatusApp" name="idStatus">
                            <c:forEach var="stat" items="${appointmentStatus}">
                                <option value="${stat.idAppointmentStatus}"><c:out value="${stat.status}" /></option>
                            </c:forEach>
                        </select>
                      </div>  
                      <div class="form-group">
                        <label for="inputNotesApp">Notas:</label>
                        <textarea class="form-control" id="inputNotesApp" placeholder="Notas" name="notes"></textarea>
                      </div>
                      <div class="form-group">
                        <label for="inputDoctorApp">Doctor :</label>
                        <select class="form-control" id="inputDoctorApp" name="idDoctor">
                            <c:forEach var="doctor" items="${doctors}">
                                <option value="${doctor.idUser}"><c:out value="${doctor.idStaffMember.name} ${doctor.idStaffMember.lastName}" /></option>
                            </c:forEach>
                        </select>
                      </div>    
                </div>
                <div class="col-sm-6">
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="tblConsultationMotives" class="row-border hover">
                                <thead>
                                    <th>lastUsed</th>
                                    <th>Motivo</th>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="inputWeightApp">Peso (Kg):</label>
                                        <input type="text" class="form-control inputDecimal" id="inputWeightApp" placeholder="Peso" name="weight"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="inputSizeApp">Talla (cm):</label>
                                        <input type="text" class="form-control inputDecimal" id="inputTimeApp" placeholder="Talla" name="size"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="inputPcApp">PC (cm):</label>
                                        <input type="text" class="form-control inputDecimal" id="inputMotiveApp" placeholder="PC" name="pc"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="inputTempApp">Temperatura (c):</label>
                                        <input type="text" class="form-control inputDecimal" id="inputTempApp" placeholder="Temperatura" name="temperature"/>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        <button type="submit" class="btn btn-primary" >Guardar</button>
      </div>
      </form>
    </div>
  </div>
</div>