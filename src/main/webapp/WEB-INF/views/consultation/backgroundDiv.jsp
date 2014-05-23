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
			<form:form role ="form" id="perBackNoPatForm" method="post" modelAttribute="perBackNoPat" action="./consultation/savePerBackNoPat">
				<form:hidden path="idPerinatalBackground" />
				<div class="panel panel-info">
                                    <div class="panel-heading">Infórmacion del paciente</div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label for="backBirthweight">Peso al nacer :</label>
                                                    <form:input class="form-control input-sm onChange" id="backBirthweight" type="number" path="birthweight" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backBirthsize" >Talla(cm) :</label>
                                                    <form:input class="form-control input-sm onChange" id="backBirthsize" type="number" path="birthsize" />	
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label for="backHeadCircumference" >Perimetro cefálico(cm) :</label>
                                                    <form:input class="form-control input-sm onChange" id="backHeadCircumference" type="number" path="headCircumference" />
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
                                                        <form:input class="form-control input-sm onChange" id="backApgar1Minute" type="number" min="0" path="apgar1Minute" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label for="backApgar5Minute">5 Minutos :</label>
                                                        <form:input class="form-control input-sm onChange" id="backApgar5Minute" type="number" min="0" path="apgar5Minute" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <label for="backApgar10Minute">10 Minutos :</label>
                                                        <form:input class="form-control input-sm onChange" id="backApgar10Minute" type="number" min="0" path="apgar10Minute" />
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
                                                    <form:input class="form-control input-sm onChange" id="backBreastFeed" type="number" min="0" path="breastFeed" />
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
                                                    <label for="backSupplementedAt">a los</strong>
                                                    <form:input class="form-control input-sm onChange" id="backSupplementedAt" type="number" path="supplementedAt" />
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
                                                    <form:input class="form-control input-sm onChange" id="backWeaning" type="text" min="0" path="weaning" />
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
                                                    <form:input class="form-control input-sm onChange" id="backMotherAge" type="number" min="0" path="motherAge" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backGestationNumber">Gestas</label>
                                                    <form:input class="form-control input-sm onChange" id="backGestationNumber" type="number" min="0" path="gestationNumber" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backBirths">Partos</label>
                                                    <form:input class="form-control input-sm onChange" id="backBirths" type="number" path="births" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backAbortions">Abortos</label>
                                                    <form:input class="form-control input-sm onChange" id="backAbortions" type="number" path="abortions" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backCesareanNumber">Cesáreas</label>
                                                    <form:input class="form-control input-sm onChange" id="backCesareanNumber" type="number" path="cesareanNumber" />
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
                                                    <form:input class="form-control input-sm onChange" id="backFollowsObjects" type="number" path="followsObjects" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backSmiles">Sonríe:</label>
                                                    <form:input class="form-control input-sm onChange" id="backSmiles" type="number" path="smiles" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backCrawls">Gatea:</label>
                                                    <form:input class="form-control input-sm onChange" id="backCrawls" type="number" path="crawls" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backStandsUp">Se para:</label>
                                                    <form:input class="form-control input-sm onChange" id="backStandsUp" type="number" path="standsUp" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backDisyllabics">Bisílabos:</label>
                                                    <form:input class="form-control input-sm onChange" id="bacDisyllabics" type="number" path="disyllabics" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backHoldsHead">Sostiene cabeza:</label>
                                                    <form:input class="form-control input-sm onChange" id="backHoldsHead" type="number" path="holdsHead" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backRolls">Se rueda:</label>
                                                    <form:input class="form-control input-sm onChange" id="backRolls" type="number" path="rolls" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backSitsDown">Se sienta:</label>
                                                    <form:input class="form-control input-sm onChange" id="backSitsDown" type="number" path="sitsDown" />
                                                </div>
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-group">
                                                    <label for="backWanders">Deambula:</label>
                                                    <form:input class="form-control input-sm onChange" id="backWanders" type="number" path="wanders" />
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div class="form-group">
                                                    <label for="backSphincterControl">Control de esfínter (V):</label>
                                                    <form:input class="form-control input-sm onChange" id="backSphincterControl" type="number" path="sphincterControl" />
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
                            <form:form id="alergicBackgroundForm" role="form" method="post" modelAttribute="record" action="./consultation/saveAlergicBackground">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                        <form:textarea id="backAlergicBackground" class="form-control onChange" rows="10" path="alergicBackground"/>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
		</div>	
		<div id="patologicos" class="tab-pane">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form id="pathologicalBackgroundForm" role="form" method="post" modelAttribute="record" action="./consultation/savePathologicalBackground">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                            <form:textarea class="form-control onChange" rows="10" path="pathologicalBackgorund"/>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
		</div>
		<div id="perinatales" class="tab-pane">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form id="perinatalBackgroundForm" role="form" method="post" modelAttribute="record" action="./consultation/savePerinatalBackground">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                            <form:textarea class="form-control onChange" rows="10" path="perinatalBackground"/>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
		</div>
		<div id="desarrollo" class="tab-pane">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form id="developmentBackgroundForm" role="form" method="post" modelAttribute="record" action="./consultation/saveDevelopmentBackground">
                                <form:hidden path="idRecord" />
                                <div class="row">
                                    <div class="col-sm-9">
                                            <form:textarea class="form-control onChange" rows="10" path="developmentBackground" />
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
		</div>
		<div id="quirurgicos" class="tab-pane">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <form:form id="surgicalHistoryForm" role="form" method="post" modelAttribute="record" action="./consultation/saveSurgicalHistory">
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
                            <form:form id="hereditaryAndFamilyBackgroundForm" role="form" method="post" modelAttribute="record" action="./consultation/saveHereditaryAndFamilyBackground">
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
                            <form:form id="othersForm" role="form" method="post" modelAttribute="record" action="./consultation/saveOthers">
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
		</div>
</div>