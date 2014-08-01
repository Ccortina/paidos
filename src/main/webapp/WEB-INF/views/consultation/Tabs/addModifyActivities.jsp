<%-- 
    Document   : addModifyActivities
    Created on : May 16, 2014, 11:42:34 AM
    Author     : Ccortina_Mac
--%>

<!-- Add new activity modal -->
<div class="modal fade" id="modalAddNewActivity" tabindex="-1" role="dialog" aria-labelledby="modalAddNewActivityLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Agregar una actividad</h4>
			</div>
                    <div class="modal-body">
                        <form role="form" id="newActivityForm" modelAttribute="activity" action="./addNewActivity" method="post" >
                            <div class="form-group">
                                <label for="txtActivityDescription">Actividad</label>
                                <input type="text" class="form-control inputNormal" name="activity" placeholder="Descripcion de la actividad" />
                            </div>
                            <div class="form-group">
                                <label for="txtActivityCost">Costo</label>
                                <input type="text" class="form-control inputDecimal" name="activityCost" placeholder="Precio de la actividad" />
                            </div>
                            <div class="form-group">
                                <label for="selectActivityType">Tipo</label>
                                <select class="form-control" name="idActivityType">
                                    <option value="1">Externa</option>
                                    <option value="2">Consultorio</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="consultationDefault" value="1" /> Agregar en cada consulta
                                </label>
                            </div>
                            <input type="hidden" name="idVaccine" id="newActivityIdVaccine"/>
                        </form>
                        <div class="form-group">
                            <table id="tblNewActivityVaccine">
                                <thead>
                                    <tr>
                                        <th>IdVacuna</th>
                                        <th>Vacuna</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                            <input type="button" value="Guardar" class="btn btn-primary" onclick="addActivity();"/>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                        
                    </div><!-- Modal body -->
                </div><!-- Modal content -->
        </div>
</div>

<!-- Modify activity -->
<div class="modal fade" id="modalEditActivity" tabindex="-1" role="dialog" aria-labelledby="modalEditActivity" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Modificiar Actividad</h4>
			</div>
                    <div class="modal-body">
                        <form role="form" id="editActivityForm" modelAttribute="activity" action="./editActivity" method="post" >
                            <input type="hidden" name="idActivity">
                            <div class="form-group">
                                <label for="txtActivityDescription">Actividad</label>
                                <input type="text" class="form-control" name="activity" placeholder="Descripcion de la actividad" />
                            </div>
                            <div class="form-group">
                                <label for="txtActivityCost">Costo</label>
                                <input type="text" class="form-control inputDecimal" name="activityCost" placeholder="Precio de la actividad" />
                            </div>
                            <div class="form-group">
                                <label for="selectActivityType">Tipo</label>
                                <select class="form-control" name="idActivityType">
                                    <option value="1">Externa</option>
                                    <option value="2">Consultorio</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="consultationDefault" value="1" /> Agregar en cada consulta
                                </label>
                            </div>
                            <input type="hidden" name="idVaccine" id="editActivityIdVaccine" />
                        </form>
                        <div class="row">
                            <div class="col-sm-3 col-sm-offset-7">
                                <input type="button" class="btn btn-primary" value="Mostrar todas las vacunas" onclick="resetEAVSearch();">
                            </div>
                        </div>
                        <div class="row">
                            <table id="tblEditActivityVaccine" class="hover row-border">
                                <thead>
                                    <tr>
                                        <th>IdVacuna</th>
                                        <th>Vacuna</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <input type="button" value="Guardar" class="btn btn-primary" onclick="editActivity();"/>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                    </div><!-- Modal body -->
                </div><!-- Modal content -->
        </div>
</div>

<!--Modal edit selected activity, this modal will edit only temporarily the activities, for the consultation -->
<div class="modal fade" id="modalEditSelectedActivity" tabindex="-1" role="dialog" aria-labelledby="modalEditActivity" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Modificiar Actividad</h4>
			</div>
                    <div class="modal-body">
                        <form role="form" id="editSelectedActivityForm" modelAttribute="activity" method="post" >
                            <div class="form-group">
                                <label for="txtActivityDescription">Actividad</label>
                                <input type="text" class="form-control" name="activity" placeholder="Descripcion de la actividad" disabled="true"/>
                            </div>
                            <div class="form-group">
                                <label for="txtActivityCost">Costo</label>
                                <input type="text" class="form-control inputDecimal" name="activityCost" placeholder="Precio de la actividad"/>
                            </div>
                            <div class="form-group">
                                <label for="selectActivityType">Tipo</label>
                                <select class="form-control" name="idActivityType" disabled="true">
                                    <option value="1">Externa</option>
                                    <option value="2">Consultorio</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="checkbox-inline">
                                    <input type="checkbox" name="includeInBill"/> Incluir en el recibo
                                </label>
                            </div>
                        </form>
                        <input type="button" value="Guardar" class="btn btn-primary" onclick="editSelectedActivity();"/>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                    </div><!-- Modal body -->
                </div><!-- Modal content -->
        </div>
</div>