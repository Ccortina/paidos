<%-- 
    Document   : NoPathological
    Created on : Jun 5, 2014, 10:26:57 PM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-10">
        <form:form role ="form" id="perBackNoPatForm" class="changeForm" method="get" modelAttribute="perBackNoPat" action="/demo/savePerBackNoPat">
            <form:hidden path="idPerinatalBackground" />
            <div class="panel panel-info">
                <div class="panel-heading">Inf—rmacion del paciente</div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backBirthweight">Peso al nacer :</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backBirthweight" path="birthweight" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backBirthsize" >Talla(cm) :</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backBirthsize" path="birthsize" />	
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <label for="backHeadCircumference" >Perimetro cef‡lico(cm) :</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backHeadCircumference" path="headCircumference" />
                            </div>
                        </div>
                        <div class="col-sm-2">     
                            <div class="form-group">
                                <label for="backBirthMethod" >Tipo Nacimiento :</label>
                                <form:select class="form-control " path="birthMethod" id="backBirthMethod" items="${birthMethods}" itemValue="idBirthMethod" itemLabel="birthMethod" />
                            </div>
                        </div>				
                    </div>
                    <div class="row">
                        <div class="col-sm-1">
                            <strong>Apgar </strong>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backApgar1Minute">1 Minuto :</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backApgar1Minute" path="apgar1Minute" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backApgar5Minute">5 Minutos :</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backApgar5Minute" path="apgar5Minute" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backApgar10Minute">10 Minutos :</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backApgar10Minute" path="apgar10Minute" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <strong>Alimentaci—n al seno</strong>
                        </div>
                        <div class="col-sm-2">
                            <div class="from-group">
                                <label for="backBreastFeed"> Meses</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backBreastFeed" path="breastFeed" />
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="backSupplemented">Complementado con</label>
                                <form:input class="form-control input-sm " id="backSupplemented" type="text" path="supplemented" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backSupplementedAt">a los</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backSupplementedAt" path="supplementedAt" />
                            </div>
                        </div>
                        <div class="col-sm-1">
                            <strong>Meses</strong>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <strong>Ablactaci—n</strong>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <label for="backWeaning"> Meses</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backWeaning" path="weaning" />
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="backCurrentlyEats">Actualmente come</label>
                                <form:input class="form-control input-sm " id="backCurrentlyEats" type="text" path="currentlyEats" />
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <label for="backIntolerance">Intolerancia</label>
                                <form:input class="form-control input-sm " id="backIntolerance" type="text" path="intolerance" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-info">
                <div class="panel-heading">Informaci—n de la Madre (al nacer el paciente)</div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backMotherAge">Edad</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backMotherAge" path="motherAge" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backGestationNumber">Gestas</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backGestationNumber" path="gestationNumber" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backBirths">Partos</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backBirths" path="births" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backAbortions">Abortos</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backAbortions" path="abortions" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backCesareanNumber">Ces‡reas</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backCesareanNumber" path="cesareanNumber" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-info">
                <div class="panel-heading">Informaci—n de su desarrollo (en numero de meses)</div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backFollowsObjects">Sigue Objetos:</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backFollowsObjects" path="followsObjects" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backSmiles">Sonr’e:</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backSmiles" path="smiles" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backCrawls">Gatea:</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backCrawls"  path="crawls" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backStandsUp">Se para:</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backStandsUp" path="standsUp" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backDisyllabics">Bis’labos:</label>
                                <form:input class="form-control input-sm  inputDecimal" id="bacDisyllabics" path="disyllabics" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backHoldsHead">Sostiene cabeza:</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backHoldsHead" path="holdsHead" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backRolls">Se rueda:</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backRolls" path="rolls" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backSitsDown">Se sienta:</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backSitsDown" path="sitsDown" />
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="backWanders">Deambula:</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backWanders" path="wanders" />
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <label for="backSphincterControl">Control de esf’nter (V):</label>
                                <form:input class="form-control input-sm  inputDecimal" id="backSphincterControl" path="sphincterControl" />
                            </div>
                        </div> 
                    </div>
                </div>
            </div>	
            <div class="panel panel-info">
                <div class="panel-heading">Datos positivos</div>
                <div class="panel-body">
                    <form:textarea id="backPositiveFacts" path="positiveFacts" class="form-control " rows="5" />
                </div>
            </div>
            <br>
        </form:form>
    </div>
    <div class='col-sm-2'>
        <div class="row">
            <div class="col-sm-12">
                <input type='button' class="btn btn-primary" onclick="saveForm('perBackNoPatForm')" value='Guardar' />
            </div>
        </div>
        <div class="row">
            <div class='col-sm-12'>
            </div>
        </div>
    </div>
</div>
