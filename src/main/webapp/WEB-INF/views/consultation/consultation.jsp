<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../Includes/header.jsp"/>

<!-- Javascript for the ajax calls -->
<c:url var="consultationAjax" value="/resources/js/consultationAjax.js" />
<c:url var="laboratorytestJS" value="/resources/js/ConsultationTabs/LaboratoryTest.js" />
<c:url var="measuresJS" value="/resources/js/ConsultationTabs/Measures.js" />
<c:url var="documentsJS" value="/resources/js/ConsultationTabs/FileUpload.js" />
<c:url var="backgroundJS" value="/resources/js/ConsultationTabs/Background.js" />
<c:url var="activitiesJS" value="/resources/js/ConsultationTabs/Activities.js" />
<c:url var="diagnosticJS" value="/resources/js/ConsultationTabs/Diagnostic.js" />
<c:url var="graphsJS" value="/resources/js/ConsultationTabs/Graph.js" />
<c:url var="inmunizationJS" value="/resources/js/ConsultationTabs/Inmunization.js" />

<!-- Files for data tables function -->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />
<c:url var="dtModCSS" value="/resources/CSS/DataTables/datatables.mod.css" />

<!-- Files for the offcanvas function of bootstrap -->
<c:url var="offcanvasJs" value="/resources/js/offcanvas.js" />
<c:url var="offcanvasCss" value="/resources/CSS/offcanvas.css" />

<!-- File for bootstrapWizard -->
<c:url var="wizardJs" value="/resources/js/jquery.bootstrap.wizard.min.js" />

<!-- Files for validation bootstrap -->
<c:url var="validationJs" value="/resources/js/bootstrapValidator.min.js" />
<c:url var="validationCSS" value="/resources/CSS/bootstrapValidator.min.css" />

<!-- Files for confirmation bootstrap -->
<c:url var="confirmationJs" value="/resources/js/confirmationBootstrap.js" />

<!-- Files for Jqplot -->
<c:url var="jqplotJs" value="/resources/js/jquery.jqplot.min.js" />
<c:url var="jqplotJsonJs" value="/resources/js/jqplot.json2.min.js" />
<c:url var="jqplotCSS" value="/resources/CSS/jquery.jqplot.min.css" />

<!-- Files for datePicker -->
<c:url var="momentJs" value="/resources/js/BootstrapPlugins/Datepicker/moment.js" />
<c:url var="datePickerJs" value="/resources/js/BootstrapPlugins/Datepicker/bootstrap-datetimepicker.min.js" />
<c:url var="datePickerCSS" value="/resources/CSS/bootstrap-datetimepicker.min.css" />
<c:url var="datePickerESJs" value="/resources/js/BootstrapPlugins/Datepicker/bootstrap-datetimepicker.es.js" />

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<script src="${dataTablesJS}" type="text/javascript"></script>

<link href="${dataTablesCSS}" rel="stylesheet" />

<link href="${dtModCSS}" rel="stylesheet" />

<script src="${offcanvasJs}" type="text/javascript"></script>

<link href="${offcanvasCss}" rel="stylesheet" />

<script src="${wizardJs}" type="text/javascript"></script>

<script src="${validationJs}" type="text/javascript"></script>

<script src="${confirmationJs}" type="text/javascript"></script>

<script src="${jqplotJs}" type="text/javascript"></script>

<link href="${jqplotCSS}" rel="stylesheet" />

<script src="${jqplotJsonJs}" type="text/javascript"></script>

<input type="hidden" id="consultationDoctorId" value="${doctor.idUser}">
<input type="hidden" id="consultationDoctor" value="${doctor.idStaffMember.name} ${doctor.idStaffMember.lastName}">
<input type="hidden" id="consultationPatientName" value="${patient.firstName} ${patient.secondName} ${patient.fatherLastName} ${patient.motherLastName}">

<!-- Main div container , centers everything-->
<div class="container">

      <!-- Main div of the container -->
    <div class="row row-offcanvas row-offcanvas-right">

        <!-- The sidebar -->
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="row ">
                <!-- The row for the image of the patient -->
                <div class="col-sm-12">
                    <img data-src="" alt="Imagen no disponible" />
                </div>
            </div>
            <div class="row">
                <!-- The row of the name for the patient  -->
                <div class="col-sm-12">
                    <strong>Paciente :</strong>
                    ${patient.firstName} ${patient.secondName} ${patient.fatherLastName} ${patient.motherLastName}
                </div>
            </div>
            <div class="row">
                <!-- The row for the age of the patient -->
                <div class="col-sm-12">
                    <strong>Edad :</strong>
                    ${age[0]} A ${age[1]} M ${age[2]} D
                    <input type="hidden" id="age" value="${age[0]}" />
                </div>
            </div>
            <div class="row">    
                <!-- The row for the age of the patient -->
                <div class="col-sm-12">
                    <strong>Apodo :</strong>
                    ${patient.nickName}
                </div>
            </div>
            <div class="row">    
                <!-- The row for the bith date -->
                <div class="col-sm-12">
                    <strong>Fecha Nacimiento :</strong>
                    ${birthday}
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <button type="button" class="btn btn-primary" onclick="saveConsultation();">Guardar</button>
                </div>
                <div class="col-sm-6">
                    <button type="button" class="btn btn-danger">Cancelar</button>
                </div>
            </div>   
            <div id="sideBarAlert">
            </div>    
        </div><!-- End side Bar-->
        
        <!-- Main column -->
        <div class="col-xs-12 col-sm-9">
            <!-- Normal use of rows -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <strong>Motivo :</strong>

                                </div>
                                <div class="col-sm-6">
                                    <strong>Fecha :</strong>
                                    ${date}
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
                    <div id="ajaxMessage"><!-- The ajax response -->
                    </div>
                    <!-- Danger alert -->
                    
                    <div class="alert alert-danger alert-dismissable" style="display: none">
                      <strong>Advertencia!</strong>
                      <div id="alertDangerMessage"></div>
                    </div>
                    <!--Succes alert-->
                    <div class="alert alert-success alert-dismissable" style="display: none">
                      <strong>Excelente!</strong>
                      <div id="alertSuccessMessage"></div>
                    </div>
                    <div class="alert alert-warning alert-dismissable">
                      <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                      <strong>Advertencia!</strong> La informacion se guarda automaticamente al hacer cambios. A excepcion
                      del diagnostico,receta y datos de consulta.
                    </div>
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
                              <li><a href="#actividades" data-toggle="tab">Actividades</a></li>
                            </ul>

                            <div class="tab-content">
                                <div id="generales" class="tab-pane active"><jsp:include page="Tabs/generals.jsp"/></div>
                                <div id="antecedentes" class="tab-pane"><jsp:include page="Tabs/background.jsp"/></div>
                                <div id="documentos" class="tab-pane"><jsp:include page="Tabs/fileUpload.jsp" /></div>
                                <div id="graficas" class="tab-pane"><jsp:include page="Tabs/graphs.jsp" /></div>
                                <div id="inmunizaciones" class="tab-pane"><jsp:include page="Tabs/inmunization.jsp" /></div>
                                <div id="labGabinetes" class="tab-pane"><jsp:include page="Tabs/laboratory.jsp" /></div>
                                <div id="medidas" class="tab-pane"><jsp:include page="Tabs/measures.jsp" /></div>
                                <div id="peeaef" class="tab-pane"><jsp:include page="Tabs/peea.jsp" /></div>
                                <div id="diagnostico" class="tab-pane"><jsp:include page="Tabs/diagnostic.jsp"/></div>
                                <div id="receta" class="tab-pane">
                                    <textarea id="consultationPrescription" class="form-control" rows="10"></textarea>
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
<script src="${momentJs}" type="text/javascript"></script>

<link href="${datePickerCSS}" rel="stylesheet" />

<script src="${datePickerJs}" type="text/javascript"></script>

<script src="${datePickerESJs}" type="text/javascript"></script>

<script src="${inputmaskJs}" type="text/javascript"></script>

<script src="${inputmaskDateJs}" type="text/javascript"></script>

<script src="${inputmaskRegexJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>

<!-- System behaviuor -->

<script src="${consultationAjax}" type="text/javascript"></script>

<script src="${laboratorytestJS}" type="text/javascript"></script>

<script src="${measuresJS}" type="text/javascript"></script>

<script src="${documentsJS}" type="text/javascript"></script>

<script src="${backgroundJS}" type="text/javascript"></script>

<script src="${activitiesJS}" type="text/javascript"></script>

<script src="${diagnosticJS}" type="text/javascript"></script>

<script src="${inmunizationJS}" type="text/javascript"></script>

<script src="${graphsJS}" type="text/javascript"></script>



