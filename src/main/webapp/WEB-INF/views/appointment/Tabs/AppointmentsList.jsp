<%-- 
    Document   : AppointmentsList
    Created on : Jul 29, 2014, 1:16:32 AM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />
<c:url var="dtModCSS" value="/resources/CSS/DataTables/datatables.mod.css" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="fullcalendarJs" value="/resources/js/JQueryPlugins/Fullcalendar/fullcalendar.min.js" />
<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />
<c:url var="fullcalendarCss" value="/resources/CSS/FullCalendar/fullcalendar.css" />
<c:url var="appointmentJs" value="/resources/js/Appointment/doctorAppointmentHome.js" />
<c:url var="appointmentDoctorJs" value="/resources/js/Appointment/appointment.js" />
<c:url var="fullcalendarLangJS" value="/resources/js/JQueryPlugins/Fullcalendar/es.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="flotJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.min.js" />
<c:url var="navigateJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.navigate.min.js" />
<c:url var="tooltipJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.tooltip.min.js" />
<c:url var="navControlJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.navigationControl.js" />
<c:url var="axisLabelsJs" value="/resources/js/JQueryPlugins/Flot/jquery.flot.axislabels.js" />

<c:url var="newAppointmentJs" value="/resources/js/Appointment/Tabs/NewAppointment.js" />
<c:url var="relativesJS" value="/resources/js/Appointment/Tabs/Relatives.js" />
<c:url var="inmunizationJS" value="/resources/js/Appointment/Tabs/Inmunization.js" />
<c:url var="appListJS" value="/resources/js/Appointment/Tabs/AppointmentsList.js" />
<c:url var="modAppJS" value="/resources/js/Appointment/Tabs/ModifyAppointment.js" />
<c:url var="mainAppJS" value="/resources/js/Appointment/Tabs/AppointmentMain.js" />

<link href="${fullcalendarCss}" rel="stylesheet" />
<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />
<link href="${bvCSS}" rel="stylesheet" />
<input type='hidden' id='idUser' value=''/>

<sec:authorize access="hasAnyRole('Administrador','Doctor')">
    <input type="hidden" id="userrole" value="Doctor"/>
</sec:authorize>
<sec:authorize access="hasRole('Asistente')">
    <input type="hidden" id="userrole" value="Asistente"/>
</sec:authorize>
    
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
          <sec:authorize access="hasAnyRole('Doctor','Pacientes_Citas_4')">
          <button type="submit" class="btn btn-primary">Modificar</button>
          </sec:authorize>
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

<script src="${momentJs}" type="text/javascript"></script>
<script src="${fullcalendarJs}" type="text/javascript"></script>
<script src="${appointmentJs}" type="text/javascript"></script>
<script src="${fullcalendarLangJS}" type="text/javascript"></script>
<script src="${bootboxJs}" type="text/javascript"></script>
<script src="${dataTablesJS}" type="text/javascript"></script>
<script src="${newAppointmentJs}" type="text/javascript"></script>
<script src="${relativesJS}" type="text/javascript"></script>
<script src="${inmunizationJS}" type="text/javascript"></script>
<script src="${bvJs}" type="text/javascript"></script>

<script src="${flotJs}" type="text/javascript"></script>
<script src="${navigateJs}" type="text/javascript"></script>
<script src="${tooltipJs}" type="text/javascript"></script>
<script src="${navControlJs}" type="text/javascript"></script>
<script src="${axisLabelsJs}" type="text/javascript"></script>

<script src="${mainAppJS}" type="text/javascript"></script>
<script src="${appListJS}" type="text/javascript"></script>
<script src="${inputmaskJs}" type="text/javascript"></script>
<script src="${inputmaskDateJs}" type="text/javascript"></script>
<script src="${inputmaskRegexJs}" type="text/javascript"></script>
<script src="${modAppJS}" type="text/javascript"></script>

