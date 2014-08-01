<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<div class="row">
		<div class="col-sm-6">
			<div class="row">
				<div class="col-sm-6">
					<div class="from-group">
						<label for="mother">Madre :</label> <input
							class="form-control input-sm" id="mother" type="text"
                                                        value="${mother.firstName} ${mother.secondName} ${mother.fatherLastName} ${mother.motherLastName}" disabled="true"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6">
					<div class="from-group">
						<label for="father">Padre :</label> <input
							class="form-control input-sm" id="father" type="text"
							value="${father.firstName} ${father.secondName} ${father.fatherLastName} ${father.motherLastName}" disabled="true"/>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6">
					<div class="from-group">
						<label for="ginecologyst">Ginecologo :</label> <input
							class="form-control input-sm" id="ginecologyst" type="text"
							value="Aqui va el nombre del ginecologo" disabled="true"/>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="sibilingsTable" class="hover row-border">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Edad</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <input type="button" value="Abrir expediente" class="btn btn-primary" onclick="openSibilingFile();" />
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
							<input class="form-control input-sm" id="perinatalBackground" type="text" value="${record.perinatalBackground}" disabled="true"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="development">Desarrollo :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="development" type="text" value="${record.developmentBackground}" disabled="true"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="surgicalHistory">A. Quirúrgicos :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="surgicalHistory" type="text" value="${record.surgicalHistory}" disabled="true"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="hereditaryAndFamilyBackground">A. H. y Fam. :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="hereditaryAndFamilyBackground" type="text" value="${record.hereditaryAndFamilyBackground}" disabled="true"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="app">A.P.P. :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="app" type="text" value="${record.pathologicalBackgorund}" disabled="true"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-2">
							<label class="" for="alergicBackground">Alérgicos :</label>
						</div>
						<div class="col-sm-10">
							<input class="form-control input-sm" id="alergicBackground" type="text" value="${record.alergicBackground }" disabled="true"/>
						</div>
					</div>
				</div>
				<div class="row">
                                    <form:form role ="form" id="generalsPerBackNoPatForm" modelAttribute="perBackNoPat">
                                        <div class="col-sm-2">
                                                <strong>A.P. no P. :</strong>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-2">
                                                <label for="birthweigth">Peso (Kg):</label>
                                                <form:input class="form-control input-sm" id="birthweight" type="text" path="birthweight" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-2">
                                                    <label for="birthsize">Talla (cm):</label>
                                                    <form:input class="form-control input-sm" id="birthsize" type="text" path="birthsize" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-2">
                                                <label for="headCircumference">PC (cm):</label>
                                                <form:input class="form-control input-sm" id="headCircumference" type="text" path="headCircumference" disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-2">
                                                <label for="birthMethod">T. Nacimiento:</label>
                                                <form:input class="form-control input-sm" id="birthMethod" type="text" path="birthMethod.birthMethod" disabled="true"/>
                                            </div>
                                        </div>
                                    </form:form>	
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-sm-12">
							<table id="tblConsultationDiagnosticAbstract">
                                                            <thead>
                                                                <tr>
                                                                    <th></th>
                                                                </tr>
                                                            </thead>
                                                            
                                                        </table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</fieldset>