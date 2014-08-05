<%-- 
    Document   : NewDrug
    Created on : Aug 5, 2014, 3:02:18 PM
    Author     : Carlos Cortina
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<form role="role" id="formNewDrug">
    <div class="row" >
        <div class="col-sm-12">
            <ul id="newDrugTabMenu" class="nav nav-tabs">
                <li class="active"><a href="#tabMainNewDrug" data-toggle="tab">Informacion general</a></li>
            </ul>
            <div class="tab-content">
                <div id="tabMainNewDrug" class="tab-pane active">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="inputNewDrugDrug">Medicamento</label>
                                <input type="text" class="form-control inputNormal" id="inputNewDrugDrug" placeholder="Medicamento" name="drug"/>
                            </div>
                            <div class="form-group">
                                <label for="inputNewDrugPresentation">Presentacion</label>
                                <select class="form-control" id="inputNewDrugPresentation" name="drugPresentationId">
                                    <c:forEach var="drugPresentations" items="${presentations}">
                                        <option value="${presentations.drugPresentationId}"><c:out value="${presentations.presentation}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputNewDrugApplicationMethod">Modo de administracion</label>
                                <select class="form-control" id="inputNewDrugApplicationMethod" name="applicationMethodId">
                                    <c:forEach var="applicationMethods" items="${applicationMethod}">
                                        <option value="${applicationMethod.idApplicationMethod}"><c:out value="${applicationMethod.applicationMethod}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputNewDrugConcentration">Concentracion</label>
                                <input type="text" class="form-control inputNormal" id="inputNewDrugConcentration" placeholder="Concentracion" name="concentration"/>
                            </div>
                            <div class="form-group">
                                <label for="inputNewDrugAdministrationUnit">Unidad de administracion</label>
                                <select class="form-control" id="inputNewDrugAdministrationUnit" name="administrationUnitId">
                                    <c:forEach var="administrationUnit" items="${administrationUnits}">
                                        <option value="${administrationUnit.idAdministrationUnit}"><c:out value="${administrationUnit.administrationUnit}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputNewDrugDailyFrequency">Frecuencia diaria</label>
                                <input type="text" class="form-control inputNormal" id="inputNewDrugDailyFrequency" placeholder="Frecuencia diaria" name="dailyFrequency"/>
                            </div>
                            <div class="form-group">
                                <label for="inputNewDrugTreatmentDays">Dias de tratamientos</label>
                                <input type="text" class="form-control inputNormal" id="inputNewDrugDailyFrequency" placeholder="Dias de tratamiento" name="treatmentDays"/>
                            </div>
                            <div class="form-group">
                                <label for="inputNewDrugApplicationSchedule">Horario de aplicacion</label>
                                <input type="text" class="form-control inputNormal" id="inputNewDrugApplicationSchedule" placeholder="Horario de aplicacion" name="applicationSchedule"/>
                            </div>
                            <div class="form-group">
                                <label>
                                    <input id="inputNewDrugActive" type="checkbox" name="active" checked>
                                    Activo
                                </label>
                            </div>   
                        </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="inputNewDrugDoseCalculationCriteria">Criterio dosis</label>
                                <select class="form-control" id="inputNewDrugDoseCalculationCriteria" name="doseCalculationCriteriaId">
                                    <c:forEach var="doseCalculationCriterias" items="${doseCalculationCriteria}">
                                        <option value="${doseCalculationCriteria.idDoseCalculationCriteria}"><c:out value="${doseCalculationCriteria.criteria}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>   
                </div>
            </div>
        </div>        
    </div>
</form>