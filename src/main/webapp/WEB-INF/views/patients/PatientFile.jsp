<%-- 
    Document   : PatientFile
    Created on : May 18, 2014, 12:22:07 AM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../Includes/header.jsp"/>

<!-- Files for data tables function -->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />
<c:url var="dataTablesModCSS" value="/resources/CSS/DataTables/datatables.mod.css" />

<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="patientFileJS" value="/resources/js/PatientFile/${jsFile}" />

<c:url var="uploader1Js" value="/resources/js/JQueryPlugins/UploadFile/jquery.ui.widget.js" />
<c:url var="uploader2Js" value="/resources/js/JQueryPlugins/UploadFile/jquery.fileupload.js" />
<c:url var="uploader3Js" value="/resources/js/JQueryPlugins/UploadFile/jquery.iframe-transport.js" />
<c:url var="uploaderCss" value="/resources/CSS/uploader/jquery.fileupload.css" />

<!-- Tabs -->
<c:url var="patientBackgroundJS" value="/resources/js/PatientFile/Tabs/background.js" />
<c:url var="documentsJS" value="/resources/js/PatientFile/Tabs/file.js" />
<c:url var="laboratoryJS" value="/resources/js/PatientFile/Tabs/laboratoryTest.js" />
<c:url var="graphsJS" value="/resources/js/PatientFile/Tabs/Graphs.js" />
<c:url var="immunizationJS" value="/resources/js/PatientFile/Tabs/Inmunization.js" />
<c:url var="consultationJS" value="/resources/js/PatientFile/Tabs/ConsultationHistory.js" />
<c:url var="appointmentJS" value="/resources/js/PatientFile/Tabs/AppointmentHistory.js" />

<!-- Utilities -->
<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dataTablesModCSS}" rel="stylesheet" />

<link href="${datePickerCSS}" rel="stylesheet" />

<link href="${uploaderCss}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<!-- Main div container , centers everything-->
<input type="hidden" id="hiddenPatientFileId" value="${patient.idPatient}">
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2"> <!-- primer columna foto -->
            <div class="row">
                <div class="col-sm-12">
                    foto
                </div>
            </div>
        </div><!-- primer columna foto -->
        <div class="col-sm-3"><!-- segunda columna familia -->
            <div class="row">
                <!-- The row of the name for the patient  -->
                <div class="col-sm-12">
                    <strong>Paciente : </strong>
                    ${patient.firstName} ${patient.fatherLastName} ${patient.motherLastName}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <strong>Madre : </strong>
                    ${mother.firstName} ${mother.fatherLastName} ${mother.motherLastName}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <strong>Padre : </strong>
                    ${father.firstName} ${father.fatherLastName} ${father.motherLastName}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <strong>Ginecólogo : </strong>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <strong>Inactivo : </strong>
                </div>
            </div>    
        </div><!-- segunda columna familia -->
        <div class="col-sm-5"><!-- tercer columna hermanos -->
            <div class="row">
                <div class="col-sm-4">
                    <strong>Sexo : </strong>
                    ${patient.sex.gender}
                </div>
                <div class="col-sm-4">
                    <strong>F. Nac. : </strong>
                    ${birthday}
                </div>
                <div class="col-sm-4">
                    <strong>Edad : </strong>
                    ${age[0]} A ${age[1]} M ${age[2]} D
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <table id="tblSibilings">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Fecha Nacimiento</th>
                                <th>Edad</th>
                            </tr>   
                        </thead>
                    </table>
                </div>
            </div>
        </div><!-- tercer columna hermanos -->
        <div class="col-sm-2"><!--Cuarta columna botones -->
            <div class="row">
                <div class="col-sm-12">
                    <sec:authorize access="hasAnyRole('Doctor')"> 
                    <div class="row">
                        <div class="col-sm-12">
                            <input type="button" class="btn btn-primary" value="Consultar" onclick="consultPatient();"/>
                        </div>
                    </div>
                    </sec:authorize>
                    <div class="row">
                        <div class="col-sm-12">
                            <input type="button" class="btn btn-danger" value="Cerrar" onclick="closeFile()"/>
                        </div>
                    </div>
                </div>
            </div>
        </div><!--Cuarta columna botones -->
    </div><!-- First row -->
    <div class="row">
        <div class="col-sm-12">
            <ul id="patientFileTabMenu" class="nav nav-tabs">
              <li class="active"><a href="#resumen" data-toggle="tab">Resumen</a></li>
              <li><a href="#familia" data-toggle="tab">Familia</a></li>
              <li><a href="#antecedentes" data-toggle="tab">Antecedentes</a></li>
              <li><a href="#documentos" data-toggle="tab">Documentos</a></li>
              <li><a href="#graficas" data-toggle="tab">Graficas</a></li>
              <li><a href="#inmunizaciones" data-toggle="tab">Inmunizaciones</a></li>
              <li><a href="#labGabinetes" data-toggle="tab">Lab. Gabinete</a></li>
              <li><a href="#consultas" data-toggle="tab">Consultas Previas</a></li>
              <li><a href="#citas" data-toggle="tab">Citas</a></li>
            </ul>
            
            <div class="tab-content">
                <div id="resumen" class="tab-pane active"> <jsp:include page="PatientFileTabs/PatientAbstractTab.jsp" /></div>
                <div id="familia" class="tab-pane"><jsp:include page="PatientFileTabs/PatientFamilyTab.jsp" /> </div>
                <div id="antecedentes" class="tab-pane"><jsp:include page="PatientFileTabs/PatientBackgroundTab.jsp" /></div>
                <div id="documentos" class="tab-pane"><jsp:include page="PatientFileTabs/Files.jsp" /> </div>
                <div id="graficas" class="tab-pane"><jsp:include page="PatientFileTabs/Graphs.jsp" /></div>
                <div id="inmunizaciones" class="tab-pane"><jsp:include page="PatientFileTabs/inmunization.jsp" /></div>
                <div id="labGabinetes" class="tab-pane"><jsp:include page="PatientFileTabs/LaboratoryTest.jsp" /></div>
                <div id="consultas" class="tab-pane"><jsp:include page="PatientFileTabs/ConsultationHistory.jsp" /></div>
                <div id="citas" class="tab-pane"><jsp:include page="PatientFileTabs/AppointmentHistory.jsp" /></div>
            </div><!-- tab-content div -->
        </div>
    </div>        
</div><!--/.container-->


<!-- modal for current appointments -->
<div class="modal fade" id="modalAvaibleAppointment" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="modalAvaibleAppointment" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Citas disponibles</h4>
      </div>
        <div class="modal-body">
            <div class="row">
                <table id="tblAvaibleAppointments" class="hover row-border">
                    <thead>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Motivo</th>
                        <th>Estatus</th>
                    </thead>
                </table>
            </div>
        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary"  onclick="continueConsultation()">Consultar</button>
        <button type="button" class="btn btn-primary"  onclick="goNewConsultation()">Consulta sin cita previa</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>

<script src="${dataTablesJS}" type="text/javascript"></script>

<script src="${uploader1Js}" type="text/javascript"></script>
<script src="${uploader2Js}" type="text/javascript"></script>
<script src="${uploader3Js}" type="text/javascript"></script>

<script src="${momentJs}" type="text/javascript"></script>

<script src="${inputmaskJs}" type="text/javascript"></script>
<script src="${inputmaskDateJs}" type="text/javascript"></script>
<script src="${inputmaskRegexJs}" type="text/javascript"></script>

<script src="${bvJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>

<script src="${utilityJs}" type="text/javascript"></script>

<script src="${patientFileJS}" type="text/javascript"></script>
<script src="${patientBackgroundJS}" type="text/javascript"></script>
<script src="${documentsJS}" type="text/javascript"></script>
<script src="${laboratoryJS}" type="text/javascript"></script>
<script src="${graphsJS}" type="text/javascript"></script>
<script src="${immunizationJS}" type="text/javascript"></script>
<script src="${consultationJS}" type="text/javascript"></script>
<script src="${appointmentJS}" type="text/javascript"></script>