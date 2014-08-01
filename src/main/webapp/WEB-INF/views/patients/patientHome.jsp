<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../Includes/header.jsp"/>

<!-- Files for data tables function -->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />
<c:url var="dtModCSS" value="/resources/CSS/DataTables/datatables.mod.css" />

<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />

<c:url var="patientHomeJS" value="/resources/js/PatientHome/patientHome.js" />
<c:url var="newPatientTabJS" value="/resources/js/PatientHome/Tabs/newPatientTab.js" />
<c:url var="modifyPatientTabJS" value="/resources/js/PatientHome/Tabs/ModifyPatientTab.js" />
<c:url var="appointmentJS" value="/resources/js/PatientHome/Tabs/AppointmentPatientTab/Appointment.js" />
<c:url var="relativesJS" value="/resources/js/PatientHome/Tabs/AppointmentPatientTab/Relatives.js" />
<c:url var="inmunizationJS" value="/resources/js/PatientHome/Tabs/AppointmentPatientTab/Inmunization.js" />
<c:url var="appointmentsHistoryJS" value="/resources/js/PatientHome/Tabs/AppointmentPatientTab/AppointmentsHistory.js" />
<c:url var="consultationHistoryJS" value="/resources/js/PatientHome/Tabs/AppointmentPatientTab/ConsultationHistory.js" />
<c:url var="measuresHistoryJS" value="/resources/js/PatientHome/Tabs/AppointmentPatientTab/MeasuresHistory.js" />

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />


<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="globalUser" value="${user}"/>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <ul id="patientTabMenu" class="nav nav-tabs">
                      <li class="active"><a href="#tabMain" data-toggle="tab">Principal</a></li>
                      <li><a href="#tabNew" data-toggle="tab">Nuevo</a></li>
                      <li><a href="#tabEdit" data-toggle="tab">Modificar</a></li>
                      <li><a href="#tabAppointment" data-toggle="tab">Cita</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tabMain" class="tab-pane active">
                            <div class="row">
                                <div class="col-sm-2">
                                    <input type='button' id="btngetAllPatients" value='Todos los pacientes' class="btn btn-primary" onclick='getAllPatients()'/>
                                </div>
                                <div class="col-sm-2">
                                    <select class="form-control" id="selectFilterPatientsByDoctor" name="doctor">
                                        <c:forEach var="doctor" items="${doctors}">
                                            <option value="${doctor.idStaffMember.idStaffMember}"><c:out value="${doctor.idStaffMember.name} ${doctor.idStaffMember.lastName}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-1">
                                    <input type='button' value='Filtrar' class="btn btn-primary" onclick='filterPatientsByDoctor()'/>
                                </div>
                                <div class="col-sm-2">
                                    <input type='button' value='Quitar Filtro' class="btn btn-danger" onclick='quitFilter()'/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <table id="tblPatients" class="hover row-border">
                                        <thead>
                                            <th>Nombre</th>
                                            <th>S. Nombre</th>
                                            <th>A. Paterno</th>
                                            <th>A. Materno</th>
                                            <th>Fecha Nacimiento</th>
                                            <th>Sexo</th>
                                            <th>Activo</th>
                                            <th>idDoctor</th>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <input type='button' value='Modificar Paciente' class="btn btn-primary" onclick='modifyPatient()'/>
                                </div>
                                <div class="col-sm-2">
                                    <input type='button' value='Agendar Cita' class="btn btn-primary" onclick='appointPatient()'/>
                                </div>
                                <div class="col-sm-2">
                                    <input type='button' value='Ver Expediente' class="btn btn-primary" onclick='openPatientFile()'/>
                                </div>
                                <div class="col-sm-2">
                                    <input type='button' value='Deshabilitar Paciente' class="btn btn-danger" onclick='deletePatient()'/>
                                </div>
                            </div>
                        </div>
                        <div id="tabNew" class="tab-pane"><jsp:include page="PatientHomeTabs/NewPatientTab.jsp" /></div>
                        <div id="tabEdit" class="tab-pane"><jsp:include page="PatientHomeTabs/ModifyPatientTab.jsp" /></div>
                        <div id="tabAppointment" class="tab-pane"><jsp:include page="PatientHomeTabs/AppointmentPatientTab.jsp" /></div>
                    </div>
                </div>
            </div>
        </div>
	</body>
</html>

<script src="${dataTablesJS}" type="text/javascript"></script>

<script src="${momentJs}" type="text/javascript"></script>

<script src="${patientHomeJS}" type="text/javascript"></script>
<script src="${newPatientTabJS}" type="text/javascript"></script>
<script src="${modifyPatientTabJS}" type="text/javascript"></script>
<script src="${appointmentJS}" type="text/javascript"></script>
<script src="${relativesJS}" type="text/javascript"></script>
<script src="${inmunizationJS}" type="text/javascript"></script>
<script src="${appointmentsHistoryJS}" type="text/javascript"></script>
<script src="${consultationHistoryJS}" type="text/javascript"></script>
<script src="${measuresHistoryJS}" type="text/javascript"></script>

<script src="${inputmaskJs}" type="text/javascript"></script>
<script src="${inputmaskDateJs}" type="text/javascript"></script>
<script src="${inputmaskRegexJs}" type="text/javascript"></script>

<script src="${bvJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>