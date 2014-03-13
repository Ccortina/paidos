<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fieldset disabled>
	<div class="row">
		<div class="col-sm-6">
			<div class="row">
				<div class="col-sm-6">
					<div class="from-group">
						<label for="mother">Madre :</label> <input
							class="form-control input-sm" id="mother" type="text"
							value="${mother.firstName} ${mother.secondName} ${mother.fatherLastName} ${mother.motherLastName}" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6">
					<div class="from-group">
						<label for="father">Padre :</label> <input
							class="form-control input-sm" id="father" type="text"
							value="${father.firstName} ${father.secondName} ${father.fatherLastName} ${father.motherLastName}" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6">
					<div class="from-group">
						<label for="ginecologyst">Ginecologo :</label> <input
							class="form-control input-sm" id="ginecologyst" type="text"
							value="Aqui va el nombre del ginecologo" />
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="row">
				<div class="col-sm-12">
					<label for="sibilings">
						Hermanos(Doble click abre el expediente) 
					</label>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<table id="sibilingsTable">
							<thead>
								<tr>
									<th>Nombre</th>
									<th>Edad</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Prueba1</td>
									<td>Numeros</td>
								</tr>
								<tr>
									<td>Prueba2</td>
									<td>Numeros</td>
								</tr>
								<tr>
									<td>Prueba3</td>
									<td>Numeros</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="perinatalBackground">A. Perinatales :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="perinatalBackground" type="text" value="${record.perinatalBackground}" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="development">Desarrollo :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="development" type="text" value="${record.developmentBackground}" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="surgicalHistory">A. Quirúrgicos :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="surgicalHistory" type="text" value="${record.surgicalHistory}" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="hereditaryAndFamilyBackground">A. H. y Fam. :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="hereditaryAndFamilyBackground" type="text" value="${record.hereditaryAndFamilyBackground}" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="app">A.P.P. :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="app" type="text" value="${record.pathologicalBackgorund}" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="alergicBackground">Alérgicos :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="alergicBackground" type="text" value="${record.alergicBackground }" />
						</div>
					</div>
				</div>
				<div class="row">
					<form:form role ="form" id="generalsPerBackNoPatForm" modelAttribute="perBackNoPat">
						<div class="form-group">
							<div class="col-sm-1">
								<label for="">A.P. no P. :</label>
							</div>
							<div class="col-sm-2">
								<label for="birthweigth">Peso (Kg):</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="birthweight" type="text" path="birthweight" />
							</div>
							<div class="col-sm-1">
								<label for="birthsize">Talla (cm):</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="birthsize" type="text" path="birthsize" />
							</div>
							<div class="col-sm-1">
								<label for="headCircumference">PC (cm):</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="headCircumference" type="text" path="headCircumference" />
							</div>
							<div class="col-sm-2">
								<label for="birthMethod">T. Nacimiento:</label>
							</div>
							<div class="col-sm-1">
								<form:input class="form-control input-sm" id="birthMethod" type="text" path="birthMethod" />
							</div>
						</div>
					</form:form>	
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-12">
							Aqui va una tabla
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</fieldset>