<%-- 
    Document   : ModifyTreatment
    Created on : Aug 4, 2014, 3:46:19 PM
    Author     : Carlos Cortina
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-12">
        <h3>Modificar tratamiento</h3>
    </div>
</div>
<form role="form" id="formModifyTreatment">
<div class="row">
    <div class="col-sm-12">
        <ul id="newTreatmentTabMenu" class="nav nav-tabs">
          <li class="active"><a href="#tabMainModifyTreatment" data-toggle="tab">Informacion general</a></li>
          <li><a href="#tabDiagnosticModifyTreatment" data-toggle="tab">Diagnosticos</a></li>
          <li><a href="#tabMedecineModifyTreatment" data-toggle="tab">Medicamentos</a></li>
        </ul>
        <div class="tab-content">
            <div id="tabMainModifyTreatment" class="tab-pane active">
                <div class="row">
                    <div class="col-sm-12">
                        <h4>Informacion general</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        
                        <div class="form-group">
                            <label for="inputModifyTreatmentTreatment">Tratamiento</label>
                            <input type="text" class="form-control inputNormal" id="inputModifyTreatmentTreatment" placeholder="Tratamiento" name="treatment"/>
                        </div>
                        <div class="form-group">
                            <label for="inputModifyTreatmentDirections">Indicaciones</label>
                            <textarea class="form-control" id="inputModifyTreatmentDirections" placeholder="Indicaciones" name="directions"></textarea>
                        </div>
                        <div class="form-group">
                            <label>
                                <input id="inputModifyTreatmentActive" type="checkbox" name="active" checked>
                                Activo
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div id="tabDiagnosticModifyTreatment" class="tab-pane">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-12">
                                <h4>Diagnósticos disponibles</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="hover row-border" id="tblDiagnostic2">
                                    <thead>
                                        <th>Clave Cie</th>
                                        <th>Diagnóstico</th>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <input type="button" value="Asociar" class="btn btn-primary" onclick="atachTreatmentDiagnostic2()"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" value="Desasociar" class="btn btn-danger" onclick="detachTreatmentDiagnostic2()"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <h4>Diagnósticos asociados al tratamiento</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="hove row-border" id="tblAsociatedDiagnostic2">
                                    <thead>
                                        <th>Clave Cie</th>
                                        <th>Diagnóstico</th>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>        
            </div>
            <div id="tabMedecineModifyTreatment" class="tab-pane">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-12">
                                <h4>Medicamentos disponibles</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="hover row-border" id="tblDrug2">
                                    <thead>
                                        <th>idDrug</th> 
                                        <th>Medicamento</th>
                                        <th>Presentacion</th>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <input type="button" value="Asociar" class="btn btn-primary" onclick="atachTreatmentDrug2()"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" value="Desasociar" class="btn btn-danger" onclick="detachTreatmentDrug2()"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <h4>Medicamentos asociados al tratamiento</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="hove row-border" id="tblAsociatedDrug2">
                                    <thead>
                                        <th>idDrug</th> 
                                        <th>Medicamento</th>
                                        <th>Presentacion</th>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-2">
        <button type="submit" class="btn btn-primary" >Guardar</button>
    </div>
    <div class="col-sm-2">
        <input type="button" value="Cancelar" class="btn btn-danger" />
    </div>
</div>
</form>
    
