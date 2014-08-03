<%-- 
    Document   : NewTreatment
    Created on : Aug 2, 2014, 4:34:06 PM
    Author     : Carlos Cortina
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-12">
        <h3>Agregar nuevo tratamiento</h3>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <ul id="newTreatmentTabMenu" class="nav nav-tabs">
          <li class="active"><a href="#tabMainNewTreatment" data-toggle="tab">Informacion general</a></li>
          <li><a href="#tabDiagnosticNewTreatment" data-toggle="tab">Diagnosticos</a></li>
          <li><a href="#tabMedecineNewTreatment" data-toggle="tab">Medicamentos</a></li>
        </ul>
        <div class="tab-content">
            <div id="tabMainNewTreatment" class="tab-pane active">
                <div class="row">
                    <div class="col-sm-12">
                        <h4>Informacion general</h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <form role="form" id="formNewTreatment">
                            <div class="form-group">
                                <label for="inputNewTreatmentTreatment">Tratamiento</label>
                                <input type="text" class="form-control inputNormal" id="inputNewTreatmentTreatment" placeholder="Tratamiento" name="treatment"/>
                            </div>
                            <div class="form-group">
                                <label for="inputNewTreatmentDirections">Indicaciones</label>
                                <textarea class="form-control" id="inputNewTreatmentDirections" placeholder="Indicaciones" name="directions"></textarea>
                            </div>
                            <div class="form-group">
                                <label>
                                    <input type="checkbox" name="active" checked>
                                    Activo
                                </label>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div id="tabDiagnosticNewTreatment" class="tab-pane">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-12">
                                <h4>Diagnósticos disponibles</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="hover row-border" id="tblDiagnostic">
                                    <thead>
                                        <th>Clave Cie</th>
                                        <th>Diagnóstico</th>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <input type="button" value="Asociar" class="btn btn-primary" onclick="atachTreatmentDiagnostic()"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" value="Desasociar" class="btn btn-danger" onclick="detachTreatmentDiagnostic()"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <h4>Diagnósticos asociados al tratamiento</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="hove row-border" id="tblAsociatedDiagnostic">
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
            <div id="tabMedecineNewTreatment" class="tab-pane">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="row">
                            <div class="col-sm-12">
                                <h4>Medicamentos disponibles</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="hover row-border" id="tblDrug">
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
                                <input type="button" value="Asociar" class="btn btn-primary" onclick="atachTreatmentDrug()"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="button" value="Desasociar" class="btn btn-danger" onclick="detachTreatmentDrug()"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <h4>Medicamentos asociados al tratamiento</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="hove row-border" id="tblAsociatedDrug">
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
