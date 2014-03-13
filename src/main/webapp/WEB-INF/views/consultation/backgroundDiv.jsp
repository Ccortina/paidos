<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row">
	<ul class="nav nav-pills">
		<li class="active"><a href="#pnpatologicos" data-toggle="tab">No patológicos</a></li>
		<li><a href="#alergicos" data-toggle="tab">Alérgicos</a></li>
		<li><a href="#patologicos" data-toggle="tab">Patológicos</a></li>
		<li><a href="#perinatales" data-toggle="tab">Perinatales</a></li>
		<li><a href="#desarrollo" data-toggle="tab">Desarrollo</a></li>
		<li><a href="#quirurgicos" data-toggle="tab">Quirúrgicos</a></li>
		<li><a href="#hereditarios" data-toggle="tab">Hereditarios y Familiares</a></li>
		<li><a href="#otros" data-toggle="tab">Otros</a></li>
	</ul>
</div>
<div class="row">
	<div class="tab-content">
		<div id="pnpatologicos" class="tab-pane active">
			<form:form role ="form" id="perBackNoPatForm" method="post" modelAttribute="perBackNoPat" action="./savePerBackNoPat">
				<form:hidden path="idPerinatalBackground" />
				<div class="panel panel-info">
					<div class="panel-heading">Infórmacion del paciente</div>
					<div class="panel-body">
							<div class="row">
								<div class="col-sm-3">
									<div class="row">
										<div class ="col-sm-12">
											<label for="birthweight">Peso al nacer :</label>
											<form:input class="form-control input-sm" id="birthweight" type="text" path="birthweight" />
										</div>
									</div>
								</div>
									<div class="col-sm-2">
										<div class="row">
											<div class="col-sm-6">
												<label for="birthsize" >Talla(cm) :</label>
											</div>
											<div class="col-sm-6">
												<form:input class="form-control input-sm" id="birthsize" type="text" path="birthsize" />
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="row">
											<div class="col-sm-8">
												<label for="headCircumference" >Perimetro cefálico(cm) :</label>
											</div>
											<div class="col-sm-4">
												<form:input class="form-control input-sm" id="headCircumference" type="text" path="headCircumference" />
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="row">
											<div class="col-sm-7">
												<label for="birthMethod" >Tipo Nacimiento :</label>
											</div>
											<div class="col-sm-5">
												<form:input class="form-control input-sm" id="birthMethod" type="text" path="birthMethod" />
											</div>
										</div>
									</div>				
							</div>
							<div class="row">
								<div class="col-sm-1">
									<label>Apgar </label>
								</div>
								<div class="col-sm-3">
									<div class="row">
										<div class="col-sm-6">
											<label for="apgar1Minute">1 Minuto :</label>
										</div>
										<div class="col-sm-6">
											<form:input class="form-control input-sm" id="apgar1Minute" type="text" path="apgar1Minute" />
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="row">
										<div class="col-sm-6">
											<label for="apgar5Minute">5 Minutos :</label>
										</div>
										<div class="col-sm-6">
											<form:input class="form-control input-sm" id="apgar5Minute" type="text" path="apgar5Minute" />
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="row">
										<div class="col-sm-6">
											<label for="apgar10Minute">10 Minutos :</label>
										</div>
										<div class="col-sm-6">
											<form:input class="form-control input-sm" id="apgar10Minute" type="number" path="apgar10Minute" />
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label for="breastFeed">Alimentación al seno</label>
								</div>
								<div class="col-sm-2">
									<div class="row">
										<div class="col-sm-6">
											<form:input class="form-control input-sm" id="breastFeed" type="text" path="breastFeed" />
										</div>
										<div class="col-sm-6">
											<label for=""> Meses</label>
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="row">
										<div class="col-sm-6">
											<label for="supplemented">Complementado con</label>
										</div>
										<div class="col-sm-6">
											<form:input class="form-control input-sm" id="supplemented" type="text" path="supplemented" />
										</div>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="row">
										<div class="col-sm-6">
											<label for="supplementedAt">a los</label>
										</div>
										<div class="col-sm-6">
											<form:input class="form-control input-sm" id="supplementedAt" type="number" path="supplementedAt" />
										</div>
									</div>
								</div>
								<div class="col-sm-1">
									<label for="">Meses</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label for="weaning" >Ablactación</label>
								</div>
								<div class="col-sm-2">
									<div class="row">
										<div class="col-sm-6">
											<form:input class="form-control input-sm" id="weaning" type="text" path="weaning" />
										</div>
										<div class="col-sm-6">
											<label for=""> Meses</label>
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="row">
										<div class="col-sm-6">
											<label for="currentlyEats">Actualmente come</label>
										</div>
										<div class="col-sm-6">
											<form:input class="form-control input-sm" id="currentlyeats" type="text" path="currentlyEats" />
										</div>
									</div>
								</div>
								<div class="col-sm-4">
									<div class="row">
										<div class="col-sm-6">
											<label for="intolerance">Intolerancia</label>
										</div>
										<div class="col-sm-6">
											<form:input class="form-control input-sm" id="intolerance" type="text" path="intolerance" />
										</div>
									</div>
								</div>
							</div>
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">Información de la Madre (al nacer el paciente)</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-sm-1 col-sm-offset-1">
								<label for="motherAge">Edad</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="motherAge" type="text" path="motherAge" />
							</div>
							<div class="col-sm-1">
								<label for="gestationNumber">Gestas</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="gestationNumber" type="text" path="gestationNumber" />
							</div>
							<div class="col-sm-1">
								<label for="births">Partos</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="births" type="number" path="births" />
							</div>
							<div class="col-sm-1">
								<label for="abortions">Abortos</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="abortions" type="number" path="abortions" />
							</div>
							<div class="col-sm-1">
								<label for="cesareanNumber">Cesáreas</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="cesareanNumber" type="number" path="cesareanNumber" />
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">Información de su desarrollo (en numero de meses)</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-sm-2">
								<label for="followsObjects">Sigue Objetos:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="followsObjects" type="number" path="followsObjects" />
							</div>
							<div class="col-sm-1">
								<label for="smiles">Sonríe:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="smiles" type="number" path="smiles" />
							</div>
							<div class="col-sm-1">
								<label for="crawls">Gatea:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="crawls" type="number" path="crawls" />
							</div>
							<div class="col-sm-1">
								<label for="standsUp">Se para:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="standsUp" type="number" path="standsUp" />
							</div>
							<div class="col-sm-1">
								<label for="disyllabics">Bisílabos:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="disyllabics" type="number" path="disyllabics" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								<label for="holdsHead">Sostiene cabeza:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="holdsHead" type="number" path="holdsHead" />
							</div>
							<div class="col-sm-1">
								<label for="rolls">Se rueda:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="rolls" type="number" path="rolls" />
							</div>
							<div class="col-sm-1">
								<label for="sitsDown">Se sienta:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="sitsDown" type="number" path="sitsDown" />
							</div>
							<div class="col-sm-1">
								<label for="wanders">Deambula:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="wanders" type="number" path="wanders" />
							</div>
							<div class="col-sm-2">
								<label for="sphincterControl">Control esfínter (V):</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="sphincterControl" type="number" path="sphincterControl" />
							</div>
						</div>
					</div>
				</div>	
				<div class="panel panel-info">
					<div class="panel-heading">Datos positivos</div>
					<div class="panel-body">
						<form:textarea id="positiveFacts" path="positiveFacts" class="form-control" rows="5" />
					</div>
				</div>
				<div class="col-sm-2 col-sm-offset-10">
					<input type="button" class="btn btn-primary" value="Guardar Cambios" onclick="ajaxCall('perBackNoPatForm');" />
				</div>
				<div class="row"></div>
			</form:form>						
		</div>
		<div id="alergicos" class="tab-pane">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form id="alergicBackgroundForm" role="form" method="post" modelAttribute="record" action="./saveAlergicBackground">
						<form:hidden path="idRecord" />
						<div class="row">
							<div class="col-sm-9">
								<form:textarea id="alergicBackground" class="form-control" rows="10" path="alergicBackground"/>
							</div>
							<div class="col-sm-3">
								<input type="button" class="btn btn-primary" value="Guardar Cambios" onclick="ajaxCall('alergicBackgroundForm');"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>	
		<div id="patologicos" class="tab-pane">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form id="pathologicalBackgroundForm" role="form" method="post" modelAttribute="record" action="./savePathologicalBackground">
						<form:hidden path="idRecord" />
						<div class="row">
							<div class="col-sm-9">
								<form:textarea class="form-control" rows="10" path="pathologicalBackgorund"/>
							</div>
							<div class="col-sm-3">
								<input type="button" class="btn btn-primary" onclick="ajaxCall('pathologicalBackgroundForm');" value="Guardar Cambios"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div id="perinatales" class="tab-pane">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form id="perinatalBackgroundForm" role="form" method="post" modelAttribute="record" action="./savePerinatalBackground">
						<form:hidden path="idRecord" />
						<div class="row">
							<div class="col-sm-9">
								<form:textarea class="form-control" rows="10" path="perinatalBackground"/>
							</div>
							<div class="col-sm-3">
								<input type="button" class="btn btn-primary" onclick="ajaxCall('perinatalBackgroundForm');" value="Guardar Cambios"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div id="desarrollo" class="tab-pane">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form id="developmentBackgroundForm" role="form" method="post" modelAttribute="record" action="./saveDevelopmentBackground">
						<form:hidden path="idRecord" />
						<div class="row">
							<div class="col-sm-9">
								<form:textarea class="form-control" rows="10" path="developmentBackground" />
							</div>
							<div class="col-sm-3">
								<input type="button" class="btn btn-primary" onclick="ajaxCall('developmentBackgroundForm');" value="Guardar Cambios"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div id="quirurgicos" class="tab-pane">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form id="surgicalHistoryForm" role="form" method="post" modelAttribute="record" action="./saveSurgicalHistory">
						<form:hidden path="idRecord" />
						<div class="row">
							<div class="col-sm-9">
								<form:textarea class="form-control" rows="10" path="surgicalHistory"/>
							</div>
							<div class="col-sm-3">
								<input type="button" class="btn btn-primary" onclick="ajaxCall('surgicalHistoryForm');" value="Guardar Cambios"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
            <!-- Tab for hereditary background -->
		<div id="hereditarios" class="tab-pane">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form id="hereditaryAndFamilyBackgroundForm" role="form" method="post" modelAttribute="record" action="./saveHereditaryAndFamilyBackground">
						<form:hidden path="idRecord" />
						<div class="row">
							<div class="col-sm-9">
								<form:textarea class="form-control" rows="10" path="hereditaryAndFamilyBackground"/>
							</div>
							<div class="col-sm-3">
								<input type="button" class="btn btn-primary" onclick="ajaxCall('hereditaryAndFamilyBackgroundForm');" value="Guardar Cambios"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
            <!-- Tab of other backgrounds -->
		<div id="otros" class="tab-pane">
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form id="othersForm" role="form" method="post" modelAttribute="record" action="./saveOthers">
						<form:hidden path="idRecord" />
						<div class="row">
							<div class="col-sm-9">
								<form:textarea class="form-control" rows="10" path="others"/>
							</div>
							<div class="col-sm-3">
								<input type="button" class="btn btn-primary" onclick="ajaxCall('othersForm');" value="Guardar Cambios"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		</div>
</div>