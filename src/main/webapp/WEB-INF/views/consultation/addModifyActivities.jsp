<%-- 
    Document   : addModifyActivities
    Created on : May 16, 2014, 11:42:34 AM
    Author     : Ccortina_Mac
--%>

<!-- Add new activity modal -->
<div class="modal fade" id="modalAddNewActivity" tabindex="-1" role="dialog" aria-labelledby="modalDiagnosticLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Agregar una actividad</h4>
			</div>
                    <div class="modal-body">
                        <form role="form" id="newActivityForm" modelAttribute="activity" action="./addNewActivity" method="post" >
                            <div class="form-group">
                                <label for="txtActivityDescription">Actividad</label>
                                <input type="text" class="form-control" name="activity" placeholder="Descripcion de la actividad" />
                            </div>
                            <div class="form-group">
                                <label for="txtActivityCost">Costo</label>
                                <input type="number" min="0" class="form-control" name="activityCost" placeholder="Precio de la actividad" />
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
                            <div class="form-group">
                                <input type="hidden" name="idVaccine" id="newActivityIdVaccine"/>
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
                            <input type="button" value="Guardar" class="btn btn-primary" onclick="ajaxCall('newActivityForm');"/>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                        </form>
                    </div><!-- Modal body -->
                </div><!-- Modal content -->
        </div>
</div>

<!-- Modify activity -->
<div class="modal fade" id="modalEditActivity" tabindex="-1" role="dialog" aria-labelledby="modalDiagnosticLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Modificiar una Actividad</h4>
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
                                <input type="number" min="0" class="form-control" name="activityCost" placeholder="Precio de la actividad" />
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
                            <div class="form-group">
                                <input type="hidden" name="idVaccine" id="editActivityIdVaccine" />
                                <table id="tblEditActivityVaccine">
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
                            <input type="button" value="Guardar" class="btn btn-primary" onclick="ajaxCall('editActivityForm');"/>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                        </form>
                    </div><!-- Modal body -->
                </div><!-- Modal content -->
        </div>
</div>
