<div class="panel panel-info">
	<div class="panel-heading">Diagnósticos,tratamientos y medicamentos incluidos en la consulta</div>
	<div class="panel-body">
		<div class="row">
                    <div class="col-sm-12">
                        <table id="consultationDiagnosticsTable">
                            <thead>
                                <tr>
                                    <th>Descripcion</th>
                                </tr>
                            </thead>
                        </table>
                        <br>    
                    </div>
		</div>
		<div class="row">
			<div class="col-sm-2">
				<a data-toggle="modal" href="#modalDiagnostic" class="btn btn-primary">Agregar</a>
			</div>
			<div class="col-sm-2">
				<button type="button" class="btn btn-danger">Quitar</button>
			</div>
			<div class="col-sm-3">
				<button type="button" class="btn btn-primary">Medicamento sin asociaciones</button>
			</div>
			<div class="col-sm-2 col-sm-offset-3">
				<button type="button" class="btn btn-primary">Generar receta</button>
			</div>
		</div>
	</div>
</div>

<!-- The modal for the diagnostic -->
<div class="modal fade" id="modalDiagnostic" tabindex="-1" role="dialog" aria-labelledby="modalDiagnosticLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Agregar Diagnostico</h4>
			</div>
			<div id="diagTabPane" class="modal-body"> 
			
                            <ul class="nav nav-tabs">
                              <li class="active"><a href="#diagnosticos" data-toggle="tab">Diagnósticos</a></li>
                              <li><a href="#treatmentsDiv" data-toggle="tab">Tratamientos</a></li>
                              <li><a href="#drugsDiv" data-toggle="tab">Medicamentos</a></li>
                              <li><a href="#commercialNamesTab" data-toggle="tab">Nombres Comerciales</a></li>
                            </ul>

                            <div class="tab-content"><br>
                                <div id="diagnosticos" class="tab-pane active">
                                    <div class="row">
                                        <table id="diagnosticsTable">
                                                <thead>
                                                    <tr>
                                                        <th>Diagnostico</th>
                                                        <th>Ultima fecha</th>
                                                    </tr>	
                                                </thead>
                                                <tbody>
                                                </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div id="treatmentsDiv" class="tab-pane">
                                    <div class="row">
                                        <table id="treatmentsTable">
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Tratamiento</th>
                                                </tr>	
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                    </div>
                                </div>
                                <div id="drugsDiv" class="tab-pane">
                                    <div class="row">
                                        <table id="drugsTable">
                                                <thead>
                                                        <tr>
                                                            <th>Medicamento</th>
                                                            <th>Presentacion</th>
                                                        </tr>
                                                </thead>
                                                <tbody></tbody>
                                        </table>
                                    </div>
                                </div>
                                <div id="commercialNamesTab" class="tab-pane">
                                    <div class="row">
                                        <table id="commercialNamesTable">
                                                <thead>
                                                        <tr>
                                                            <th>Medicamento</th>
                                                        </tr>
                                                </thead>
                                                <tbody></tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            
			</div><!-- Final de pestañas y su contenido -->
			<div class="modal-footer">
                            <div class="row">
                                <div class="col-sm-8">
                                        <fieldset disabled>
                                            <form class="form-horizontal" role="form">
                                                <div class="form-group">
                                                    <label for="selectedDiagnosticInput" class="col-sm-2 control-label">Diagnóstico</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" id="selectedDiagnosticInput" placeholder="Diagnóstico Seleccionado"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="selectedTreatmentInput" class="col-sm-2 control-label">Tratamiento</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" id="selectedTreatmentInput" placeholder="Tratamiento Seleccionado"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="selectedDrugInput" class="col-sm-2 control-label">Medicamento</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" id="selectedDrugInput" placeholder="Medicamento Seleccionado"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="selectedCommercialNamesInput" class="col-sm-2 control-label">Nombre comercial</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" id="selectedCommercialNamesInput" placeholder="Nombre comercial Seleccionado"/>
                                                    </div>
                                                </div>
                                            </form>
                                        </fieldset>
                                </div>
                                <div class="col-sm-4">
                                    <div class="row">
                                        <div class="col-sm-12">
                                                <button type="button" class="btn btn-primary">Agregar</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <br>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">	
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
			</div> <!-- Close Modal Footer -->
		</div><!-- End modal content -->
	</div>
</div>