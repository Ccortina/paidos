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
                <li><a href="#tabCommercialName" data-toggle="tab">Nombres comerciales</a></li>
                <li><a href="#tabIncompatibilities" data-toggle="tab">Incompatibilidades</a></li>
                <li><a href="#tabTreatments" data-toggle="tab">Tratamientos</a></li>
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
                                <input type="text" class="form-control inputNormal" id="inputNewDrugTreatmentDays" placeholder="Dias de tratamiento" name="treatmentDays"/>
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
                <div id="tabCommercialName" class="tab-pane">
                    <div class="row">
                        <div class="col-sm-10">
                            <table class="hover row-border" id="tblNewDrugCommercialName">
                                <thead>
                                    <th>Nombres comerciales asociados al medicamento</th>
                                </thead>
                            </table>
                        </div>
                        <div class="col-sm-2">
                            <div class="row">
                                <input type="button" class="btn btn-primary" onclick="loadNewCommercialNameModal()" value="Nuevo"/>
                            </div>
                            <div class="row">
                                <input type="button" class="btn btn-primary" value="Modificar" />
                            </div>
                            <div class="row">
                                <input type="button" class="btn btn-danger" value="Borrar" />
                            </div>
                        </div>
                    </div>
                </div>
                <div id="tabIncompatibilities" class="tab-pane">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="row">
                                <div class="col-sm-12">
                                    <table id="tblIncompatibilityDrugList" class="hover row-border">
                                        <th>Medicamento</th>
                                        <th>Presentacion</th>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-10">
                                    <table id="tblIncompatibilityRisk" class="hover row-border">
                                        <thead>
                                            <th>Medicamento</th>
                                            <th>Riesgo (Aplica para todas las presentaciones)</th>
                                        </thead>
                                    </table>
                                </div>
                                <div class="col-sm-2">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-primary" value="Agregar" onclick="loadNewRiskModal()" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-danger" value="Quitar" onclick="removeRisk()" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="row">
                                <div class="col-sm-10">
                                    <table id="tblIncompatibilityCommercialName" class="hover row-border">
                                        <thead>
                                            <th>Nombres Comerciales</th>
                                            <th>IdDrug</th>
                                        </thead>
                                    </table>
                                </div>
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-primary" value="Agregar" onclick="addIncompatibility()" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-10">
                                    <table id="tblIncompatibles" class="hover row-border">
                                        <thead>
                                            <th>Nombres comerciales incompatibles</th>
                                        <thead>
                                    </table>
                                </div>
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-danger" value="Quitar" onclick="removeIncompatibility()" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="tabTreatments" class="tab-pane">
                    <div class="row">
                        <div class="col-sm-10">
                            <table id="tblTreatments" class="hover row-border">
                                <thead>
                                    <th>Tratamientos disponibles</th>
                                </thead>
                            </table>
                        </div>
                        <div class="col-sm-2">
                            <input type="button" class="btn btn-primary" value="Agregar" onclick="addTreatment()"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-10">
                            <table id="tblTreatmentsAssociated" class="hover row-border">
                                <thead>
                                    <th>Tratamientos asociados con el medicamento</th>
                                </thead>
                            </table>
                        </div>
                        <div class="col-sm-2">
                            <input type="button" class="btn btn-danger" value="Quitar" onclick="removeTreatment()" />
                        </div>
                    </div>
                </div>
            </div>
        </div>        
    </div>
    <div class="row">
        <div class="col-sm-2">
            <input type="submit" class="btn btn-primary" value="Guardar"/>
        </div>
        <div class="col-sm-2">
            <input type="button" class="btn btn-danger" onclick="cancel()" value="Cancelar"/>
        </div>
    </div>
</form>


<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="modalNewDose" aria-hidden="true" id="modalNewDose">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <form role="form" id="formNewDose">
        <div class="modal-header">
            <h4 class="modal-title">Agregar nueva dosis</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputNewDoseCriteria">Criterio</label>
                        <input type="text" class="form-control" id="inputNewDoseCriteria" placeholder="Criterio" name="criteria"/>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputNewDoseDose">Dosis</label>
                        <input type="text" class="form-control" id="inputNewDoseDose" placeholder="Dosis" name="dose"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary">Agregar</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        </div>
        </form>
    </div>
  </div>
</div>

<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalNewCommercialName" aria-hidden="true" id="modalNewCommercialName">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <form role="form" id="formNewCommercialName">
        <div class="modal-header">
            <h4 class="modal-title">Agregar nuevo nombre comercial</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label for="inputNewCommercialName">Nombre comercial</label>
                        <input type="text" class="form-control inputNormal" id="inputNewCommercialName" placeholder="Nombre comercial" name="commercialName"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary">Agregar</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        </div>
        </form>
    </div>
  </div>
</div>

<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalNewRisk" aria-hidden="true" id="modalNewRisk">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <form role="form" id="formNewRisk">
        <div class="modal-header">
            <h4 class="modal-title">Agregar nuevo riesgo</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label for="inputNewRisk">Riesgo</label>
                        <textarea class="form-control" id="inputNewRisk" placeholder="Riesgos" name="risk"></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary">Agregar</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        </div>
        </form>
    </div>
  </div>
</div>