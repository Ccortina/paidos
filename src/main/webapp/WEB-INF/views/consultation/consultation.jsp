<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../Includes/header.jsp"/>

<!-- Javascript for the ajax calls -->
<c:url var="consultationAjax" value="/resources/js/Consultation/consultationAjax.js" />
<c:url var="backgroundJS" value="/resources/js/Consultation/ConsultationTabs/Background.js" />
<c:url var="documentsJS" value="/resources/js/Consultation/ConsultationTabs/File.js" />
<c:url var="inmunizationJS" value="/resources/js/Consultation/ConsultationTabs/Inmunization.js" /> 
<c:url var="laboratorytestJS" value="/resources/js/Consultation/ConsultationTabs/LaboratoryTest.js" />
<c:url var="measuresJS" value="/resources/js/Consultation/ConsultationTabs/Measures.js" />
<c:url var="diagnosticJS" value="/resources/js/Consultation/ConsultationTabs/Diagnostic.js" />
<c:url var="activitiesJS" value="/resources/js/Consultation/ConsultationTabs/Activities.js" />
<c:url var="graphsJS" value="/resources/js/Consultation/ConsultationTabs/Graph.js" />

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

<c:url var="uploader1Js" value="/resources/js/JQueryPlugins/UploadFile/jquery.ui.widget.js" />
<c:url var="uploader2Js" value="/resources/js/JQueryPlugins/UploadFile/jquery.fileupload.js" />
<c:url var="uploader3Js" value="/resources/js/JQueryPlugins/UploadFile/jquery.iframe-transport.js" />
<c:url var="uploaderCss" value="/resources/CSS/uploader/jquery.fileupload.css" />
<!-- Utilities -->
<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dataTablesModCSS}" rel="stylesheet" />

<link href="${datePickerCSS}" rel="stylesheet" />

<link href="${uploaderCss}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<input type="hidden" id="assignedDoctor" value="${doctor}">
<input type="hidden" id="patientAge" value="${age[0]}-${age[1]}-${age[2]}">
<input type="hidden" id="prescriptionCounter" value="${prescriptionCounter}">
<input type="hidden" id="consultationPatientName" value="${patient.firstName} ${patient.fatherLastName} ${patient.motherLastName}">

<!-- Main div container , centers everything-->
<div class="container-fluid">
    <!-- Main div of the container -->
    <div class="row">
        <!-- Main column -->
        <div class="col-sm-12 main">
            <!-- Normal use of rows -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    foto   
                                </div>
                                <div class="col-sm-7">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group-sm">
                                                <label>Paciente</label>
                                                <input type="text" class="form-control" value="${patient.firstName} ${patient.fatherLastName} ${patient.motherLastName}" disabled />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group-sm">
                                                <label>Edad</label>
                                                <input type="text" class="form-control" value="${age[0]} A ${age[1]} M ${age[2]} D " disabled />
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group-sm">
                                                <label>Fecha Nacimiento</label>
                                                <input type="text" class="form-control" value="${birthday}" disabled />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">    
                                            <div class="form-group-sm">
                                                <label>Motivo</label>
                                                <input type="text" class="form-control" value="${appointment.motive}" disabled />
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group-sm">
                                                <label>Fecha</label>
                                                <input type="text" class="form-control" value="${date}" disabled />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-primary" onclick="saveConsultation();">Guardar</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-danger" onclick="cancelConsultation();">Cancelar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- panel body -->
                    </div><!-- panel -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <!-- Data obtained from the examination previous to the appointment-->
                                    <form role="form" id="formConsultationBasicData">
                                        <div class="row">
                                            <div class="col-md-2">
                                                <label for="weight">Peso(Kg):</label>
                                                <input class="form-control input-sm inputDecimal" name="weigth" id="txtConsultationWeight" value="${appWeight}"/>
                                            </div>
                                            <div class="col-md-2">
                                                <label for="temperature">Temperatura:</label>
                                                <input class="form-control input-sm inputDecimal" name="temperature" value="${appTemperature}"/>
                                            </div>
                                            <div class="col-md-2">
                                                <label for="size">Talla(cm):</label>
                                                <input class="form-control input-sm inputDecimal" name="size" value="${appSize}"/>
                                            </div>
                                            <div class="col-md-1">
                                                <label for="ta">TA:</label>
                                                <input class="form-control input-sm inputDecimal" name="ta" value="${appTA}"/>
                                            </div>
                                            <div class="col-md-1">
                                                <label for="ta2">/</label>
                                                <input class="form-control input-sm inputDecimal" name="ta2" value="${appTA2}"/>
                                            </div>
                                            <div class="col-md-1">
                                                <label for="taaverage">-</label>
                                                <input class="form-control input-sm inputDecimal" name="taaverage" value="${appTAA}"/>
                                            </div>
                                            <div class="col-md-2">
                                                <label for="pc">PC(cm):</label>
                                                <input class="form-control input-sm inputDecimal" name="pc" value="${appPC}"/>
                                            </div>
                                        </div>
                                    </form><!-- form -->
                                </div><!-- col -->
                            </div><!-- row -->
                        </div><!-- panel body -->
                    </div><!-- panel -->
                    <div class="row">
                        <div class="col-md-12">
                            <ul id="consultationTabMenu" class="nav nav-tabs">
                              <li class="active"><a href="#generales" data-toggle="tab">Generales</a></li>
                              <li><a href="#antecedentes" data-toggle="tab">Antecedentes</a></li>
                              <li><a href="#documentos" data-toggle="tab">Documentos</a></li>
                              <li><a href="#graficas" data-toggle="tab">Graficas</a></li>
                              <li><a href="#inmunizaciones" data-toggle="tab">Inmunizaciones</a></li>
                              <li><a href="#labGabinetes" data-toggle="tab">Lab. Gabinete</a></li>
                              <li><a href="#medidas" data-toggle="tab">Medidas</a></li>
                              <li><a href="#peeaef" data-toggle="tab">PEEA/E.F</a></li>
                              <li id="consultationDiagnosticTab" ><a href="#diagnostico" data-toggle="tab">Diagnostico</a></li>
                              <li><a href="#receta" data-toggle="tab">Receta</a></li>
                              <li><a href="#resumen" data-toggle="tab">Resumen</a></li>
                              <li><a href="#actividades" data-toggle="tab">Actividades</a></li>
                            </ul>

                            <div class="tab-content">
                                <div id="generales" class="tab-pane active"><jsp:include page="Tabs/generals.jsp"/></div>
                                <div id="antecedentes" class="tab-pane"><jsp:include page="Tabs/BackgroundTab.jsp"/></div>
                                <div id="documentos" class="tab-pane"><jsp:include page="Tabs/Files.jsp"/></div>
                                <div id="graficas" class="tab-pane"><jsp:include page="Tabs/graphs.jsp"/></div>
                                <div id="inmunizaciones" class="tab-pane"><jsp:include page="Tabs/inmunization.jsp"/></div>
                                <div id="labGabinetes" class="tab-pane"><jsp:include page="Tabs/LaboratoryTest.jsp"/></div>
                                <div id="medidas" class="tab-pane"><jsp:include page="Tabs/Measures.jsp"/></div>
                                <div id="peeaef" class="tab-pane"><jsp:include page="Tabs/peea.jsp"/></div>
                                <div id="diagnostico" class="tab-pane"><jsp:include page="Tabs/Diagnostic.jsp"/></div>
                                <div id="resumen" class="tab-pane">
                                    <div class="row">
                                        <div class="col-sm-9">
                                            <textarea id="consultationAbstract" class="form-control" rows="15"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div id="receta" class="tab-pane">
                                    <div class="row">
                                        <div class="col-sm-9">
                                            <textarea id="consultationPrescription" class="form-control" rows="15"></textarea>
                                        </div>
                                        <div class="col-sm-3">
                                            <div class="form-group-sm">
                                                <label>Folio:</label>
                                                <input type="text" id="consultationPrescriptionNumber" class="form-control inputDecimal" value="${prescriptionCounter}" disabled="true" />
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="row">
                                        <div class="col-sm-9">
                                            <div class="form-group-sm">
                                                <label>Notas:</label>
                                                <textarea id="consultationPrescriptionNotes" class="form-control inputTextArea" rows="5"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="actividades" class="tab-pane"><jsp:include page="Tabs/activities.jsp"/></div>
                            </div><!-- tab-content div -->
                        </div><!-- col for the tabs  -->
                    </div>
                </div><!-- col -->
            </div><!-- span row-->
        </div><!-- main column -->
    </div><!-- main div of the container -->
</div><!--/.container-->

<!-- Plugins and tools -->
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

<!-- System behavior -->

<script src="${consultationAjax}" type="text/javascript"></script>
<script src="${backgroundJS}" type="text/javascript"></script>
<script src="${documentsJS}" type="text/javascript"></script>
<script src="${inmunizationJS}" type="text/javascript"></script>
<script src="${laboratorytestJS}" type="text/javascript"></script>
<script src="${measuresJS}" type="text/javascript"></script>
<script src="${diagnosticJS}" type="text/javascript"></script>
<script src="${activitiesJS}" type="text/javascript"></script>
<script src="${graphsJS}" type="text/javascript"></script>



