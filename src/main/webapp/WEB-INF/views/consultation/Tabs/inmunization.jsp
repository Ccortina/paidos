<%-- 
    Document   : inmunization
    Created on : Jun 15, 2014, 7:04:41 PM
    Author     : Ccortina_Mac
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="col-sm-10">
                <table id="tblConsultationPatientInmunization" class="hover row-border">
                    <thead>
                        <tr>
                            <th>Inmunizacion</th>
                            <th>Tipo</th>
                            <th>Fecha Prog.</th>
                            <th>Edad Req.</th>
                            <th>Edad Ap.</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="col-sm-2">
                <div class="row">
                    <div class="col-sm-12"><a data-toggle="modal" href="#modalConsultationPatientInmunization" class="btn btn-primary">Programar</a></div>
                </div>
                <div class="row">
                    <div class="col-sm-12"><a data-toggle="modal" href="#modalConsultationPatientEditPV" class="btn btn-primary" >Modificar</a></div>
                </div>
                <div class="row">
                    <div class="col-sm-12"><a data-toggle="modal" href="#modalConsultationPatientAddPV" class="btn btn-primary" >Agregar</a></div>
                </div>
                <div class="row">
                    <div class="col-sm-12"><input type="button" class="btn btn-danger" value="Borrar" onclick="deletePV();"/></div>
                </div>
                <div class="row">
                    <div class="col-sm-12"><a data-toggle="modal" href="#modalConsultationPatientSuspendPV" class="btn btn-primary" >Suspender Vencidas</a></div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modalConsultationPatientInmunization" tabindex="-1" role="dialog" aria-labelledby="modalConsultationPatientInmunization" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                    <h4 class="modal-title">Programar Inmunizaciones</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-9">
                        <h2>Programaci—n de Inmunizaciones</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-9">
                        <h3>${patient.firstName} ${patient.fatherLastName} ${patient.motherLastName}</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-9">
                        <h4>Criterios para el manejo de fechas</h4>
                    </div>
                </div>
                <form role="form" id="formConsultationProgramVaccineOptions" method="POST" action="/demo/consultation/programVaccines">    
                <div class="row">
                    <div class="col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="a" checked="true"/>
                                No programar fechas en sabados
                            </label>
                        </div><!-- /checkbox -->
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="b" checked="true"/>
                                No programar fechas en domingos
                            </label>    
                        </div><!-- /checkbox -->
                    </div> 
                </div>
                <div class="row">
                    <div class="col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="c" checked="true"/>
                                No programar fechas en dias no habiles
                            </label>    
                        </div><!-- /checkbox -->
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="d" checked="true"/>
                                No modificar vacunas editadas y/o modificadas
                            </label>
                        </div><!-- /checkbox -->
                    </div> 
                </div>
                <div class="row">
                    <div class="col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="e" checked="true"/>
                                Suspender vacunas cuya fecha de aplicacion haya pasado
                            </label>
                        </div><!-- /checkbox -->
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="f" checked="true"/>
                                No reprogramar citas de vacunas editadas y/o modificadas
                            </label>
                        </div><!-- /input-group -->
                    </div> 
                </div> 
                </form>    
            </div>    
            <div class="modal-footer">
                <div class="row">
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-primary" value="Programar" onclick="ajaxCall('formConsultationProgramVaccineOptions');" data-dismiss="modal"/>
                    </div>
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-danger" data-dismiss="modal" value="Cancelar" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
                    
<!-- Modal Edit Programmed Vaccines -->
<div class="modal fade" id="modalConsultationPatientEditPV" tabindex="-1" role="dialog" aria-labelledby="modalConsultationPatientEditPV" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                    <h4 class="modal-title">Modificar Vacuna Programada</h4>
            </div>
            <div class="modal-body">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        Informaci—n general de la vacuna
                    </div>
                    <div class="panel-body">
                        <form role="form" id="formEditPVVaccineSection">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>Vacuna</label>
                                        <input type="text" class="form-control" name="vaccine" placeholder="Vacuna" readonly="true"/>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>Tipo de Vacuna</label>
                                        <input type="text" class="form-control" name="idVaccineType" placeholder="Tipo Vacuna" readonly="true"/>
                                    </div>    
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label>A–o para aplicaci—n</label>
                                        <input type="text" class="form-control" name="yearApply" placeholder="A–o de apliacion" readonly="true"/>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label>Mes para aplicaci—n</label>
                                        <input type="text" class="form-control" name="monthApply" placeholder="Mes de aplicacion" readonly="true"/>
                                    </div>    
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label>D’a para aplicaci—n</label>
                                        <input type="text" class="form-control" name="dayApply" placeholder="Dia de aplicacion" readonly="true"/>
                                    </div>    
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <form role="form" id="formEditPVPatientVaccineSection" method="POST" action="editProgrammedVaccine">
                    <input type="hidden" name="pvvaccine" />
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            Informaci—n particular de la vacuna para el paciente
                        </div>
                        <div class="panel-body">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Fecha programada de aplicaci—n</label>
                                        <input type="text" class="form-control inputDate" name="programedDate" placeholder="Fecha programada" />
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Fecha de aplicaci—n</label>
                                            <input type="text" class="form-control inputDate" name="applicationDate" placeholder="Fecha de aplicacion"/>
                                        </div>    
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label>Nombre</label>
                                            <input type="text" class="form-control inputNormal" name="name" placeholder="Nombre"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label>Lote</label>
                                            <input type="text" class="form-control inputNormal " name="batch" placeholder="Lote"/>
                                        </div>    
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label>Fecha de vencimiento</label>
                                            <input type="text" class="form-control inputDate" name="expirationDate" placeholder="Fecha de vencimiento"/>
                                        </div>    
                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            Informaci—n sobre la suspensi—n de la vacuna
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-12">
                                     <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="suspended" onclick="checkSuspendedVaccine();"/>
                                            Vacuna suspendida
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label>Observaciones</label>
                                        <input type="text" class="form-control inputNormal" name="notes" placeholder="Observaciones"/>
                                    </div> 
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>    
            <div class="modal-footer">
                <div class="row">
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-primary" value="Guardar" onclick="submitEditPV();"/>
                    </div>
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-danger" data-dismiss="modal" value="Cancelar" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal Add Programmed Vaccines -->
<div class="modal fade" id="modalConsultationPatientAddPV" tabindex="-1" role="dialog" aria-labelledby="modalConsultationPatientAddPV" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                    <h4 class="modal-title">Agregar Vacuna Programada</h4>
            </div>
            <div class="modal-body">
                <table id="tblPVAvaibleVaccine" class="hover row-border">
                    <thead>
                        <tr>
                            <th>Vacuna</th>
                            <th>Tipo</th>
                            <th>Fecha aplicaci—n</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-primary" value="Agregar" onclick="submitAddPV();"/>
                    </div>
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-danger" data-dismiss="modal" value="Cancelar" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal suspend programmed vaccine -->
<div class="modal fade" id="modalConsultationPatientSuspendPV" tabindex="-1" role="dialog" aria-labelledby="modalConsultationPatientSuspendPV" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                    <h4 class="modal-title">Suspender vacunas vencidas</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <table id="tblPVExpiredVaccine" class="hover row-border">
                            <thead>
                                <tr>
                                    <th>Inmunizaciones vencidas</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-10">
                        <div class="form-group">
                            <label>Motivo de la suspensi—n</label>
                            <input type="text" class="form-control inputNormal" id="inputPVSuspensionNotes" placeholder="Motivo"/>
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="row">
                            <div class="col-sm-12">
                                <input type="button" class="btn btn-primary" value="Agregar" onclick="suspendPV();"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <input type="button" class="btn btn-danger" value="Eliminar" onclick="retrivePV();"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table id="tblPVSuspendedVaccine" class="hover row-border">
                            <thead>
                                <tr>
                                    <th>Inmunizaciones suspendidas</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-sm-6">
                        <input type="button" class="btn btn-danger" data-dismiss="modal" value="Cancelar" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>