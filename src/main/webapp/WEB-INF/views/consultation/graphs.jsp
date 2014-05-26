<%-- 
    Document   : graphs
    Created on : May 25, 2014, 1:28:36 PM
    Author     : Ccortina_Mac
--%>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>

<div class="row">
    <div class="col-sm-10">
    <table id="tblGraphsPastConsultation" class="hover row-border">
        <thead>
            <tr>
                <th>Id</th>
                <th>Fecha</th>
                <th>Edad</th>
                <th>Peso</th>
                <th>Talla(cm)</th>
                <th>PC(cm)</th>
                <th>TA</th>
                <th>-</th>
                <th>/</th>
                <th>Temp</th>
                <th>Masa</th>
            </tr>        
        </thead>
        <tbody></tbody>
    </table>
    </div>
    <div class="col-sm-2">
        <a data-toggle="modal" href="#modalGraphEdit" class="btn btn-primary" onclick="loadGraphPacientData();">Modificar</a>
    </div>
</div>
<div class="row">
    <div class="col-sm-3">
        <a data-toggle="modal" href="#modalGraph" class="btn btn-primary" >Peso para la edad(0 a 36 Meses)</a>
    </div>
    <div class="col-sm-3">
    </div>
    <div class="col-sm-3">
    </div>
    <div class="col-sm-3">
    </div>
</div>
<div class="row">
    <div class="col-sm-3">
    </div>
    <div class="col-sm-3">
    </div>
    <div class="col-sm-3">
    </div>
    <div class="col-sm-3">
    </div>
</div>
<!-- Modal for editing a row -->
<div id="modalGraphEdit" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Editar informaci—n</h4>
      </div>
      <div class="modal-body">
          <div class="row">
              <form role="form" id="formGraphEdit" method="POST" action="./consultation/editGraphPatientData">
                  <input type="hidden" name="idConsultation" path="idConsultation" value="" />
                  <div class="row">
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">Fecha:</label>
                              <input type="text" class="form-control" name="date" disabled/>
                          </div>
                      </div>
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">Edad:</label>
                              <input type="text" class="form-control" name="age" disabled/>
                          </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">Peso (Kg):</label>
                              <input type="number" min="0" class="form-control onChange2" name="weight" id="inputGraphWeight"/>
                          </div>
                      </div>
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">Talla (cm):</label>
                              <input type="number" min="0" class="form-control onChange2" name="size" id="inputGraphSize"/>
                          </div>
                      </div>
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">PC (cm):</label>
                              <input type="number" min="0" class="form-control onChange2" name="pc"/>
                          </div>
                      </div>
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">Masa :</label>
                              <input type="number" min="0" class="form-control onChange2" name="imc" id="inputGraphIMC"/>
                          </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">TA :</label>
                              <input type="number" min="0" class="form-control onChange2" name="ta"/>
                          </div>
                      </div>
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">-</label>
                              <input type="number" min="0" class="form-control onChange2" name="ta2"/>
                          </div>
                      </div>
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">/</label>
                              <input type="number" min="0" class="form-control onChange2" name="taaverage"/>
                          </div>
                      </div>
                      <div class="col-sm-3">
                          <div class="form-group">
                              <label for="">Temperatura :</label>
                              <input type="number" min="0" class="form-control onChange2" name="temperature"/>
                          </div>
                      </div>
                  </div>
              </form>
              
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!--Modal for the Chart-->
<div id="modalGraph" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="GraphModalTitle">Modal title</h4>
      </div>
      <div class="modal-body">
          <div id="divChart" style="height:400px;width:300px;"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->