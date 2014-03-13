<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../Includes/header.jsp"/>

<!-- Javascript for the ajax calls -->
<c:url var="consultationAjax" value="/resources/js/consultationAjax.js" />

<!-- Files for data tables function -->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.css" />

<!-- Files for the offcanvas function of bootstrap -->
<c:url var="offcanvasJs" value="/resources/js/offcanvas.js" />
<c:url var="offcanvasCss" value="/resources/CSS/offcanvas.css" />

<script src="${dataTablesJS}" type="text/javascript"></script>

<link href="${dataTablesCSS}" rel="stylesheet" />

<script src="${offcanvasJs}" type="text/javascript"></script>

<link href="${offcanvasCss}" rel="stylesheet" />

<!-- Make modal diagnostic bigger -->
<style type="text/css">
	#modalDiagnostic .modal-dialog
	{
		width:60%;
	}
	
	.row_selected
	{
    	color:red;
	}‹
</style>
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
                </div>
            </div>
            <div class="row">    
                <!-- The row for the age of the patient -->
                <div class="col-sm-12">
                    <strong>Apodo :</strong>
                    ${patient.nickname}
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
                    <button type="button" class="btn btn-primary">Guardar</button>
                </div>
                <div class="col-sm-6">
                    <button type="button" class="btn btn-danger">Cancelar</button>
                </div>
            </div>    
        </div><!--/span-->
        
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
                                <form:form role="form" id="appointmentData">
                                    <div class="col-sm-1">
                                        <label for="weight">Peso(Kg):</label>
                                    </div>
                                    <div class="col-sm-1">
                                        <input class="form-control input-sm" id="weight" type="text" value="${appointment.weigth}" />
                                    </div>
                                    <div class="col-sm-1">
                                        <label for="size">Talla(cm):</label>
                                    </div>
                                    <div class="col-sm-1">
                                        <input class="form-control input-sm" id="size" type="text" value="${appointment.size}" />
                                    </div>
                                    <div class="col-sm-1">
                                        <label for="pc">PC(cm):</label>
                                    </div>
                                    <div class="col-sm-1">
                                        <input class="form-control input-sm" id="pc" type="text" value="${appointment.pc}" />
                                    </div>
                                    <div class="col-sm-1">
                                        <label for="ta">TA:</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input class="form-control input-sm" id="ta" type="text" 
                                                        value="${appointment.ta} / ${appointment.ta2} - ${appointment.averageTa}" />
                                    </div>                              
                                    <div class="col-sm-2">
                                        <label for="temperature">Temperatura:</label>
                                    </div>
                                    <div class="col-sm-1">
                                        <input class="form-control input-sm" id="temperature" type="text" value="${appointment.temperature}" />
                                    </div> 
                                </form:form><!-- form -->
                            </div><!-- row -->
                        </div><!-- panel body -->
                    </div><!-- panel -->
                    <div id="ajaxMessage"><!-- The ajax response -->
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="nav nav-tabs">
                              <li class="active"><a href="#generales" data-toggle="tab">Generales</a></li>
                              <li><a href="#antecedentes" data-toggle="tab">Antecedentes</a></li>
                              <li><a href="#documentos" data-toggle="tab">Documentos</a></li>
                              <li><a href="#graficas" data-toggle="tab">Graficas</a></li>
                              <li><a href="#inmunizaciones" data-toggle="tab">Inmunizaciones</a></li>
                              <li><a href="#labGabinetes" data-toggle="tab">Lab. Gabinete</a></li>
                              <li><a href="#medidas" data-toggle="tab">Medidas</a></li>
                              <li><a href="#peeaef" data-toggle="tab">PEEA/E.F</a></li>
                              <li><a href="#diagnostico" data-toggle="tab">Diagnostico</a></li>
                              <li><a href="#receta" data-toggle="tab">Receta</a></li>
                              <li><a href="#actividades" data-toggle="tab">Actividades</a></li>
                              <li><a href="#resumen" data-toggle="tab">Resumen</a></li>
                            </ul>

                            <div class="tab-content">
                                <div id="generales" class="tab-pane active">
                                        <jsp:include page="generalsDiv.jsp"/>
                                </div>
                                <div id="antecedentes" class="tab-pane">
                                        <jsp:include page="backgroundDiv.jsp"/>
                                </div>
                                <div id="documentos" class="tab-pane">documentos</div>
                                <div id="graficas" class="tab-pane">graficas</div>
                                <div id="inmunizaciones" class="tab-pane">inmunizaciones</div>
                                <div id="labGabinetes" class="tab-pane">laboratorio gabinete</div>
                                <div id="medidas" class="tab-pane">medidas</div>
                                <div id="peeaef" class="tab-pane">peea ef</div>
                                <div id="diagnostico" class="tab-pane">
                                        <jsp:include page="diagnosticDiv.jsp"/>
                                </div>
                                <div id="receta" class="tab-pane">receta</div>
                                <div id="actividades" class="tab-pane">actividades</div>
                                <div id="resumen" class="tab-pane">resumen</div>
                            </div><!-- tab-content div -->
                        </div><!-- col for the tabs  -->
                    </div>
                </div><!-- col -->
            </div><!-- span row-->
        </div><!-- main column -->
    </div><!-- main div of the container -->
</div><!--/.container-->

<script src="${consultationAjax}" type="text/javascript"></script>

<!-- The style for the nav pills -->
<style type="text/css">
    .red .active a,
    .red .active a:hover {
        background-color: red;
    }
</style>