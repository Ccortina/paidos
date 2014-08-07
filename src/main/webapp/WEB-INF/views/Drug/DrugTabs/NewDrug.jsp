<%-- 
    Document   : NewDrug
    Created on : Aug 5, 2014, 3:02:18 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                                    <c:forEach var="presentations" items="${drugPresentations}">
                                        <option value="${presentations.drugPresentationId}"><c:out value="${presentations.presentation}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputNewDrugApplicationMethod">Modo de administracion</label>
                                <select class="form-control" id="inputNewDrugApplicationMethod" name="applicationMethodId">
                                    <c:forEach var="applicationMethod" items="${applicationMethods}">
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
                        <div class="col-sm-4">
                            <div class="row">
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <label for="inputNewDrugDoseCalculationCriteria">Criterio dosis</label>
                                        <select class="form-control" id="inputNewDrugDoseCalculationCriteria" name="doseCalculationCriteriaId">
                                            <c:forEach var="doseCalculationCriteria" items="${doseCalculationCriterias}">
                                                <option value="${doseCalculationCriteria.idDoseCalculationCriteria}"><c:out value="${doseCalculationCriteria.criteria}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputNewDrugDose">Dosis</label>
                                        <table class="row-border hover" id="tblNewDrugDose">
                                            <thead>
                                                <th>Regla</th>
                                                <th>Dosis</th>
                                            </thead>
                                        </table>    
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-primary" data-toggle="modal" onclick="loadNewDrugDoseModal()" value="Nuevo"/>
                                        </div>                                       
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-primary" onclick="modifyDose()" value="Modificar"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-danger" onclick="removeDose()" value="Quitar"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label for="inputNewDrugNotes">Observaciones</label>
                                    <textarea class="form-control" id="inputNewDrugNotes" placeholder="Observaciones" name="notes" rows="10"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-2">                       
                        </div>
                    </div>   
                </div>
            </div>
        </div>        
    </div>
    <div class="row">
        <div class="col-sm-2">
            <input type="button" class="btn btn-primary" onclick="saveNewDrug()" value="Guardar"/>
        </div>
        <div class="col-sm-2">
            <input type="button" class="btn btn-danger" onclick="cancel()" value="Cancelar"/>
        </div>
    </div>
</form>


<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="modalNewDose" aria-hidden="true" id="modalNewDose">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <div class="modal-header">
            <h4 class="modal-title">Agregar nueva dosis</h4>
        </div>
        <div class="modal-body">
            <form role="form" id="formNewDose">
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputNewDoseCriteria">Criterio</label>
                        <input type="text" class="form-control inputDecimal" id="inputNewDoseCriteria" placeholder="Criterio" name="criteria"/>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputNewDoseDose">Dosis</label>
                        <input type="text" class="form-control inputDecimal" id="inputNewDoseDose" placeholder="Dosis" name="dose"/>
                    </div>
                </div>
            </div>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="addDose()">Agregar</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        </div>
    </div>
  </div>
</div>