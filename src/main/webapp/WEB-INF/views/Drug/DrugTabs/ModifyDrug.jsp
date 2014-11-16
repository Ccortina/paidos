<%-- 
    Document   : NewDrug
    Created on : Aug 5, 2014, 3:02:18 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form role="role" id="formModifyDrug">
    <div class="row" >
        <div class="col-sm-12">
            <ul id="modifyDrugTabMenu" class="nav nav-tabs">
                <li class="active"><a href="#tabMainModifyDrug" data-toggle="tab">Informacion general</a></li>
                <li><a href="#tabModifyCommercialName" data-toggle="tab">Nombres comerciales</a></li>
                <li><a href="#tabModifyIncompatibilities" data-toggle="tab">Incompatibilidades</a></li>
                <li><a href="#tabModifyTreatments" data-toggle="tab">Tratamientos</a></li>
            </ul>
            <div class="tab-content">
                <div id="tabMainModifyDrug" class="tab-pane active">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label for="inputModifyDrugDrug">Medicamento</label>
                                <input type="text" class="form-control inputNormal" id="inputModifyDrugDrug" placeholder="Medicamento" name="drug"/>
                            </div>
                            <div class="form-group">
                                <label for="inputModifyDrugPresentation">Presentacion</label>
                                <select class="form-control" id="inputModifyDrugPresentation" name="drugPresentationId">
                                    <c:forEach var="presentations" items="${drugPresentations}">
                                        <option value="${presentations.drugPresentationId}"><c:out value="${presentations.presentation}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputModifyDrugApplicationMethod">Modo de administracion</label>
                                <select class="form-control" id="inputModifyDrugApplicationMethod" name="applicationMethodId">
                                    <c:forEach var="applicationMethod" items="${applicationMethods}">
                                        <option value="${applicationMethod.idApplicationMethod}"><c:out value="${applicationMethod.applicationMethod}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputModifyDrugConcentration">Concentracion</label>
                                <input type="text" class="form-control inputNormal" id="inputModifyDrugConcentration" placeholder="Concentracion" name="concentration"/>
                            </div>
                            <div class="form-group">
                                <label for="inputModifyDrugAdministrationUnit">Unidad de administracion</label>
                                <select class="form-control" id="inputModifyDrugAdministrationUnit" name="administrationUnitId">
                                    <c:forEach var="administrationUnit" items="${administrationUnits}">
                                        <option value="${administrationUnit.idAdministrationUnit}"><c:out value="${administrationUnit.administrationUnit}" /></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputModifyDrugDailyFrequency">Frecuencia diaria</label>
                                <input type="text" class="form-control inputNormal" id="inputModifyDrugDailyFrequency" placeholder="Frecuencia diaria" name="dailyFrequency"/>
                            </div>
                            <div class="form-group">
                                <label for="inputModifyDrugTreatmentDays">Dias de tratamientos</label>
                                <input type="text" class="form-control inputNormal" id="inputModifyDrugTreatmentDays" placeholder="Dias de tratamiento" name="treatmentDays"/>
                            </div>
                            <div class="form-group">
                                <label for="inputModifyDrugApplicationSchedule">Horario de aplicacion</label>
                                <input type="text" class="form-control inputNormal" id="inputModifyDrugApplicationSchedule" placeholder="Horario de aplicacion" name="applicationSchedule"/>
                            </div>
                            <div class="form-group">
                                <label>
                                    <input id="inputModifyDrugActive" type="checkbox" name="active" >
                                    Activo
                                </label>
                            </div>   
                        </div>
                        <div class="col-sm-4">
                            <div class="row">
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <label for="inputModifyDrugDoseCalculationCriteria">Criterio dosis</label>
                                        <select class="form-control" id="inputModifyDrugDoseCalculationCriteria" name="doseCalculationCriteriaId">
                                            <c:forEach var="doseCalculationCriteria" items="${doseCalculationCriterias}">
                                                <option value="${doseCalculationCriteria.idDoseCalculationCriteria}"><c:out value="${doseCalculationCriteria.criteria}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputModifyDrugDose">Dosis</label>
                                        <table class="row-border hover drugDoseTable" id="tblModifyDrugDose">
                                            <thead>
                                                <th>Edad</th>
                                                <th>Dosis</th>
                                            </thead>
                                        </table>    
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-primary" data-toggle="modal" onclick="loadModifyDrugDoseModal()" value="Nuevo"/>
                                        </div>                                       
                                    </div>
                                    <!--<div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-primary" onclick="modifyDose()" value="Modificar"/>
                                        </div>
                                    </div>-->
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-danger" onclick="removeModifyDose()" value="Quitar"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label for="inputModifyDrugNotes">Observaciones</label>
                                    <textarea class="form-control" id="inputModifyDrugNotes" placeholder="Observaciones" name="notes" rows="10"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-2">                       
                        </div>
                    </div>   
                </div>
                <div id="tabModifyCommercialName" class="tab-pane">
                    <div class="row">
                        <div class="col-sm-10">
                            <table class="hover row-border" id="tblModifyDrugCommercialName">
                                <thead>
                                    <th>Nombres comerciales asociados al medicamento</th>
                                </thead>
                            </table>
                        </div>
                        <div class="col-sm-2">
                            <div class="row">
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalModifyNewCommercialName">Nuevo</button>
                            </div>
                            <div class="row">
                                <button type="button" class="btn btn-primary" onclick="loadModifyModifyDrugCN()" >Modificar</button>
                            </div>
                            <div class="row">
                                <input type="button" class="btn btn-danger" value="Borrar" onclick="modifyRemoveCommercialName()"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="tabModifyIncompatibilities" class="tab-pane">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="row">
                                <div class="col-sm-12">
                                    <table id="tblModifyIncompatibilityDrugList" class="hover row-border">
                                        <th>Medicamento</th>
                                        <th>Presentacion</th>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-10">
                                    <table id="tblModifyIncompatibilityRisk" class="hover row-border">
                                        <thead>
                                            <th>Medicamento</th>
                                            <th>Riesgo (Aplica para todas las presentaciones)</th>
                                        </thead>
                                    </table>
                                </div>
                                <div class="col-sm-2">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-primary" value="Agregar" onclick="modifyLoadNewRiskModal()" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-primary" value="Modificar" onclick="modifyLoadModifyRiskModal()" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="button" class="btn btn-danger" value="Quitar" onclick="modifyRemoveRisk()" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="row">
                                <div class="col-sm-10">
                                    <table id="tblModifyIncompatibilityCommercialName" class="hover row-border">
                                        <thead>
                                            <th>Nombres Comerciales</th>
                                            <th>IdDrug</th>
                                        </thead>
                                    </table>
                                </div>
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-primary" value="Agregar" onclick="modifyAddIncompatibility()" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-10">
                                    <table id="tblModifyIncompatibles" class="hover row-border">
                                        <thead>
                                            <th>Nombres comerciales incompatibles</th>
                                        <thead>
                                    </table>
                                </div>
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-danger" value="Quitar" onclick="modifyRemoveIncompatibility()" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="tabModifyTreatments" class="tab-pane">
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


<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalModifyDoseByWeight" aria-hidden="true" id="modalModifyDoseByWeight">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <form role="form" id="formModifyDoseByWeight">
        <div class="modal-header">
            <h4 class="modal-title">Agregar nueva dosis</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputModifyDoseDose">Dosis</label>
                        <input type="text" class="form-control inputDecimal" id="inputModifyDoseDose" placeholder="Dosis" name="dose"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="addModifyDoseByWeight()">Agregar</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        </div>
        </form>
    </div>
  </div>
</div>

<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalModifyDoseByAge" aria-hidden="true" id="modalModifyDoseByAge">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <form role="form" id="formModifyDoseByAge">
        <div class="modal-header">
            <h4 class="modal-title">Agregar nueva dosis</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputModifyDoseCriteria">Criterio</label>
                        <input type="text" class="form-control inputInteger" id="inputModifyDoseCriteria" placeholder="Criterio" name="criteria2"/>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputModifyDoseDoseByAge">Dosis</label>
                        <input type="text" class="form-control inputDecimal" id="inputModifyDoseDoseByAge" placeholder="Dosis" name="dose2"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="addModifyDoseByAge()">Agregar</button>
            <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        </div>
        </form>
    </div>
  </div>
</div>

<!-- Modal for adding a new commercial name  -->
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalModifyNewCommercialName" aria-hidden="true" id="modalModifyNewCommercialName">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <form role="form" id="formModifyNewCommercialName">
        <div class="modal-header">
            <h4 class="modal-title">Agregar nuevo nombre comercial</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label for="inputModifyNewCommercialName">Nombre comercial</label>
                        <input type="text" class="form-control inputNormal" id="inputModifyNewCommercialName" placeholder="Nombre comercial" name="commercialName"/>
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

<!-- modal for modifying a commercial name -->
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalModifyModifyCommercialName" aria-hidden="true" id="modalModifyModifyCommercialName">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <form role="form" id="formModifyModifyCommercialName">
        <div class="modal-header">
            <h4 class="modal-title">Modificar nombre comercial</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label for="inputModifyModifyCommercialName">Nombre comercial</label>
                        <input type="text" class="form-control inputNormal" id="inputModifyModifyCommercialName" placeholder="Nombre comercial" name="commercialName"/>
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

<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalModifyNewRisk" aria-hidden="true" id="modalModifyNewRisk">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <form role="form" id="formModifyNewRisk">
        <div class="modal-header">
            <h4 class="modal-title">Agregar nuevo riesgo</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label for="inputModifyRisk">Riesgo</label>
                        <textarea class="form-control" id="inputModifyNewRisk" placeholder="Riesgos" name="risk"></textarea>
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

<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalModifyModifyRisk" aria-hidden="true" id="modalModifyModifyRisk">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <form role="form" id="formModifyModifyRisk">
        <div class="modal-header">
            <h4 class="modal-title">Modificar riesgo</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label for="inputModifyModifyRisk">Riesgo</label>
                        <textarea class="form-control" id="inputModifyModifyRisk" placeholder="Riesgos" name="risk"></textarea>
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