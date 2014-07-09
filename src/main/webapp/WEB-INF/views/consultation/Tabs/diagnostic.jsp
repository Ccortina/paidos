<div class="panel panel-info">
	<div class="panel-heading">Diagnósticos,tratamientos y medicamentos incluidos en la consulta</div>
	<div class="panel-body">
		<div class="row">
                    <div class="col-sm-12">
                        <!-- This table shows the current diagnostics added to the consultation -->
                        <table id="consultationDiagnosticsTable">
                            <thead>
                                <tr>
                                    <th>Descripción</th>    <!-- 0 -->
                                    <th>Diagnostic</th>     <!-- 1 -->
                                    <th>Treatment</th>      <!-- 2 -->
                                    <th>Drug</th>           <!-- 3 -->
                                    <th>CommercialName</th> <!-- 4 -->
                                    <th>Eliminar</th>       <!-- 5 -->
                                </tr>
                            </thead>
                        </table>
                        <br>    
                    </div>
		</div>
		<div class="row">
			<div class="col-sm-2">
				<a data-toggle="modal" href="#modalDiagnostic" class="btn btn-primary">Agregar Diagnostico</a>
			</div>
                        <div class="col-sm-3">
				<a data-toggle="modal" href="#modalDrugWithoutAssociation" class="btn btn-primary">Medicamento sin asociación</a>
			</div>
			<div class="col-sm-2">
				<button type="button" id="generatePrescriptionButton" class="btn btn-primary">Generar receta</button>
			</div>
		</div>
	</div>
</div>

<!-- The modal for the diagnostic -->
<div class="modal fade" id="modalDiagnostic" tabindex="-1" role="dialog" aria-labelledby="modalDiagnosticLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Agregar Diagnostico</h4>
                </div>
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist" id="tabsConsultationDiagnostic">
                        <li class="active"><a href="#tabDiagnosticAndTreatments" role="tab" data-toggle="tab">Diagnósticos y Tratamientos</a></li>
                        <li><a href="#tabDrugsAndNames" role="tab" data-toggle="tab">Medicamentos</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tabDiagnosticAndTreatments">
                            <div class="row">
                                <div class="col-sm-6">
                                    <table id="diagnosticsTable" class="row-border hover">
                                            <thead>
                                                <tr>
                                                    <th>Diagnostico</th>
                                                    <th>Ultima fecha</th>
                                                </tr>	
                                            </thead>
                                    </table>
                                </div>
                                <div class="col-sm-6">
                                    <table id="treatmentsTable" class="hover row-border">
                                        <thead>
                                            <tr>
                                                <th>Tratamiento</th>
                                                <th>CIE10IdList</th>
                                            </tr>	
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="tabDrugsAndNames">
                            <div class="row">
                                <div class="col-sm-6">
                                    <table id="drugsTable" class="hover row-border">
                                        <thead>
                                                <tr>
                                                    <th>Medicamento</th>
                                                    <th>Presentación</th>
                                                    <th>TreatmentIdList</th>
                                                </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div class="col-sm-6">
                                    <table id="commercialNamesTable" class="hover row-border">
                                        <thead>
                                            <tr>
                                                <th>Medicamento</th>
                                                <th>drugId</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                <div class="modal-footer">
                    <div class="row">
                        <div class="col-sm-6">
                                <button type="button" id="addDiagnosticRowButton" class="btn btn-primary" >Agregar</button>
                        </div>
                        <div class="col-sm-6">	
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div> <!-- Close Modal Footer -->
            </div><!-- End modal  content -->
	</div>
</div><!-- End modal diagnostic -->

<!-- Modal for adding a drug without association -->
<div class="modal fade" id="modalDrugWithoutAssociation" tabindex="-1" role="dialog" aria-labelledby="modalDiagnosticLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                    <h4 class="modal-title">Agregar Medicamento</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-8">
                        <table id="tblDWADrugs" class="hover row-border">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Presentación</th>
                                    <th>Aplicación</th>
                                    <th>Unidad</th>
                                </tr>
                            </thead>
                        </table>
                    </div>  
                    <div class="col-sm-4">
                        <table id="tblDWADrugsCommercialName" class="hover row-border">
                            <thead>
                                <tr>
                                    <th>Nombre Comercial</th>
                                    <th>drugId</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>    
            <div class="modal-footer">
                <div class="row">
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-primary" onclick="addDiagnosticRowDWA();" >Agregar</button>
                    </div>
                    <div class="col-sm-6">	
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- End for modal add drug without association -->
