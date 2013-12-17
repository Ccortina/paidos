<div class="panel panel-info">
	<div class="panel-heading">Diagnósticos,tratamientos y medicamentos incluidos en la consulta</div>
	<div class="panel-body">
		<div class="row">
			El diagnostico
		</div>
		<div class="row">
			<div class="col-sm-1">
				<a data-toggle="modal" href="#modalDiagnostic" class="btn btn-primary">Agregar</a>
			</div>
			<div class="col-sm-1">
				<button type="button" class="btn btn-danger">Quitar</button>
			</div>
			<div class="col-sm-3">
				<button type="button" class="btn btn-primary">Medicamento sin asociaciones</button>
			</div>
			<div class="col-sm-2 col-sm-offset-5">
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
			<div class="modal-body">
			
				<ul class="nav nav-tabs">
				  <li class="active"><a href="#frecuentes" data-toggle="tab">Diagnósticos frecuentes</a></li>
				  <li><a href="#diagnosticos" data-toggle="tab">Diagnósticos</a></li>
				  <li><a href="#tratamientos" data-toggle="tab">Tratamientos</a></li>
				  <li><a href="#medicamentos" data-toggle="tab">Medicamentos</a></li>
				  <li><a href="#comerciales" data-toggle="tab">Nombres Comerciales</a></li>
				</ul>
				
				<div class="tab-content">
					<div id="frecuentes" class="tab-pane active">
						<jsp:include page="frequentDisgnostics.jsp"/>
					</div>
					<div id="diagnosticos" class="tab-pane">
						<jsp:include page="diagnosticsDiv.jsp"/>
					</div>
					<div id="tratamientos" class="tab-pane">
						<jsp:include page="treatmentsDiv.jsp"/>
					</div>
					<div id="medicamentos" class="tab-pane"></div>
					<div id="comerciales" class="tab-pane"></div>
				</div>
			
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-sm-9">
						<fieldset disabled>
						<legend>Seleccion que se agregara a la consulta</legend>
							<form>
							</form>
						</fieldset>
					</div>
					<div class="col-sm-3">
						<div class="row">
							<div class="col-sm-12">
								<button type="button" class="btn btn-primary">Agregar</button>
          					</div>
          				</div>	
          				<div class="row">
          					<div class="col-sm-12">	
          						<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>