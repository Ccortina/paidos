<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<form:form role ="form" id="perBackNoPatForm" method="post" modelAttribute="perBackNoPat" action="/demo/savePerBackNoPat">
				<form:hidden path="idPerinatalBackground" />
				<div class="panel panel-info">
                                    <div class="panel-heading">Infórmacion del paciente</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label for="backBirthweight">Peso al nacer :</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backBirthweight" path="birthweight" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backBirthsize" >Talla(cm) :</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backBirthsize" path="birthsize" />	
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label for="backHeadCircumference" >Perimetro cefálico(cm) :</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backHeadCircumference" path="headCircumference" />
                                                </div>
                                            </div>
                                            <div class="col-sm-3">     
                                                <div class="form-group">
                                                    <label for="backBirthMethod" >Tipo Nacimiento :</label>
                                                    <form:select class="form-control onChange" path="birthMethod" id="backBirthMethod" items="${birthmethods}" itemValue="idBirthMethod" itemLabel="birthMethod" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                                <div class="col-sm-1">
                                                        <strong>Apgar </strong>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label for="backApgar1Minute">1 Minuto :</label>
                                                        <form:input class="form-control input-sm onChange inputDecimal" id="backApgar1Minute" path="apgar1Minute" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label for="backApgar5Minute">5 Minutos :</label>
                                                        <form:input class="form-control input-sm onChange inputDecimal" id="backApgar5Minute" path="apgar5Minute" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label for="backApgar10Minute">10 Minutos :</label>
                                                        <form:input class="form-control input-sm onChange inputDecimal" id="backApgar10Minute" path="apgar10Minute" />
                                                    </div>
                                                </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <strong>Alimentación al seno</strong>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="from-group">
                                                    <label for="backBreastFeed"> Meses</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backBreastFeed" path="breastFeed" />
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label for="backSupplemented">Complementado con</label>
                                                    <form:input class="form-control input-sm onChange" id="backSupplemented" type="text" path="supplemented" />
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label for="backSupplementedAt">a los</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backSupplementedAt" path="supplementedAt" />
                                                </div>
                                            </div>
                                            <div class="col-sm-1">
                                                    <strong>Meses</strong>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <strong>Ablactación</strong>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backWeaning"> Meses</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backWeaning" path="weaning" />
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <label for="backCurrentlyEats">Actualmente come</label>
                                                    <form:input class="form-control input-sm onChange" id="backCurrentlyEats" type="text" path="currentlyEats" />
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label for="backIntolerance">Intolerancia</label>
                                                    <form:input class="form-control input-sm onChange" id="backIntolerance" type="text" path="intolerance" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
				</div>
				<div class="panel panel-info">
                                    <div class="panel-heading">Información de la Madre (al nacer el paciente)</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backMotherAge">Edad</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backMotherAge" path="motherAge" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backGestationNumber">Gestas</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backGestationNumber" path="gestationNumber" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backBirths">Partos</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backBirths" path="births" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backAbortions">Abortos</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backAbortions" path="abortions" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backCesareanNumber">Cesáreas</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backCesareanNumber" path="cesareanNumber" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
				</div>
				<div class="panel panel-info">
                                    <div class="panel-heading">Información de su desarrollo (en numero de meses)</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backFollowsObjects">Sigue Objetos:</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backFollowsObjects" path="followsObjects" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backSmiles">Sonríe:</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backSmiles" path="smiles" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backCrawls">Gatea:</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backCrawls"  path="crawls" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backStandsUp">Se para:</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backStandsUp" path="standsUp" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backDisyllabics">Bisílabos:</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="bacDisyllabics" path="disyllabics" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backHoldsHead">Sostiene cabeza:</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backHoldsHead" path="holdsHead" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backRolls">Se rueda:</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backRolls" path="rolls" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backSitsDown">Se sienta:</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backSitsDown" path="sitsDown" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backWanders">Deambula:</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backWanders" path="wanders" />
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label for="backSphincterControl">Control de esfínter (V):</label>
                                                    <form:input class="form-control input-sm onChange inputDecimal" id="backSphincterControl" path="sphincterControl" />
                                                </div>
                                            </div> 
                                        </div>
                                    </div>
				</div>	
				<div class="panel panel-info">
					<div class="panel-heading">Datos positivos</div>
					<div class="panel-body">
						<form:textarea id="backPositiveFacts" path="positiveFacts" class="form-control onChange" rows="5" />
					</div>
				</div>
                                        <br>
			</form:form>						
		</div>
		<div id="alergicos" class="tab-pane">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form id="alergicBackgroundForm" role="form" method="post" modelAttribute="record" action="/demo/saveAlergicBackground">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                        <form:textarea id="backAlergicBackground" class="form-control input-sm onChange" rows="10" path="alergicBackground"/>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                        <div class="panel panel-info">
                            <div class="panel-heading">Medicamentos</div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <table id="tblConsultationPatientAlergicDrug" class="hover row-border">
                                            <thead>
                                                <tr>
                                                    <th>Medicamento</th>
                                                </tr>
                                            </thead>
                                        </table>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="row">
                                            <div class="sol-sm-12">
                                                <a data-toggle="modal" href="#modalConsultationPatientAddAD" class="btn btn-primary" >Agregar</a>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="sol-sm-12">
                                                <input type="button" class="btn btn-danger" value="Quitar" onclick="submitDeleteAD();"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
		</div>	
		<div id="patologicos" class="tab-pane">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form id="pathologicalBackgroundForm" role="form" method="post" modelAttribute="record" action="/demosavePathologicalBackground">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                            <form:textarea class="form-control input-sm onChange" rows="10" path="pathologicalBackgorund"/>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
		</div>
		<div id="perinatales" class="tab-pane">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form id="perinatalBackgroundForm" role="form" method="post" modelAttribute="record" action="/demosavePerinatalBackground">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                            <form:textarea class="form-control input-sm onChange" rows="10" path="perinatalBackground"/>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
		</div>
		<div id="desarrollo" class="tab-pane">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form id="developmentBackgroundForm" role="form" method="post" modelAttribute="record" action="/demo/saveDevelopmentBackground">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                            <form:textarea class="form-control input-sm onChange" rows="10" path="developmentBackground" />
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
		</div>
		<div id="quirurgicos" class="tab-pane">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form id="surgicalHistoryForm" role="form" method="post" modelAttribute="record" action="/demo/saveSurgicalHistory">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                        <form:textarea class="form-control onChange" rows="10" path="surgicalHistory"/>
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
                            <form:form id="hereditaryAndFamilyBackgroundForm" role="form" method="post" modelAttribute="record" action="/demo/saveHereditaryAndFamilyBackground">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                        <form:textarea class="form-control onChange" rows="10" path="hereditaryAndFamilyBackground"/>
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
                            <form:form id="othersForm" role="form" method="post" modelAttribute="record" action="/demo/saveOthers">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                        <form:textarea class="form-control onChange" rows="10" path="others"/>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
		</div>
        </div><!-- Tab content -->
</div>

<div class="modal fade" id="modalConsultationPatientAddAD" tabindex="-1" role="dialog" aria-labelledby="modalConsultationPatientAddAD" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                    <h4 class="modal-title">Agregar medicamentos alérgicos para el paciente</h4>
            </div>
            <div class="modal-body">
                <table id="tblPADAvaibleDrug" class="hover row-border">
                    <thead>
                        <tr>
                            <th>Medicamento</th>
                            <th>Presentación</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-primary" value="Agregar" onclick="submitAddAD();"/>
                    </div>
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-danger" data-dismiss="modal" value="Cancelar" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>