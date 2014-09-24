<%-- 
    Document   : LaboratoryTest
    Created on : Jun 9, 2014, 10:26:54 PM
    Author     : Ccortina_Mac
--%>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>

<div class="row">
    <div class="col-sm-10">
        <table id="tblLaboratoryTestsPatientData" class="row-border hover">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Estudio</th>
                    <th>Resultado</th>
                </tr>
            </thead>
        </table>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <a data-toggle="modal" href="#modalLaboratoryTestAdd" class="btn btn-primary" >Agregar</a>
        </div>
        <div class="row">
            <button type="button" class="btn btn-primary" onclick="loadModifyLabTest()">Modificar</button>
        </div>
        <div class="row">
            <button type="button" class="btn btn-danger" onclick="deleteLabTest()">Borrar</button>
        </div>
    </div>
</div>

<!--Modal for adding a laboratory test-->
<div id="modalLaboratoryTestAdd" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Agregar Estudio de Laboratorio</h4>
      </div>
      <form role="form" id="formLaboratoryTestAdd">
      <div class="modal-body">
              <div class="row">
                  <div class="form-group">
                    <label for="date">Fecha:</label>  
                    <input type="text" name="date" class="datepicker"/>
                  </div>
              </div>
              <div class="row">
                  <input type="hidden" name="idLaboratoryTest" />
                  <table id="tblLaboratoryTests" class="hover row-border">
                      <thead>
                          <tr>
                              <th>id</th>
                              <th>Estudio</th>
                          </tr>
                      </thead>
                  </table>
              </div>
              <div class="row">
                  <div class="form-group">
                    <label for="testResult">Resultados</label>
                    <textarea name="result" class="form-control" rows="5" cols="80" ></textarea>
                  </div>
              </div>
      </div>
      <div class="modal-footer">
          <div class="row">
              <div class="col-sm-2">
                  <button type="submit" class="btn btn-primary">Guardar</button>
              </div>
              <div class="col-sm-2">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>  
              </div>
          </div>
      </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Modal for editing a laboratory test-->
<div id="modalModifyLabTest" class="modal fade">
  <div class="modal-dialog ">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Modificar Estudio de Laboratorio</h4>
      </div>
      <form role="form" id="formLaboratoryTestEdit">  
      <div class="modal-body">
          <div class="row">
              <div class="form-group">
                <label for="date">Fecha:</label>  
                <input type="text" name="date" class="form-group"/>
              </div>
          </div>
          <div class="row">
              <table id="tblEditLaboratoryTests" class="hover row-border">
                  <thead>
                      <tr>
                          <th>id</th>
                          <th>Estudio</th>
                      </tr>
                  </thead>
              </table>
          </div>
          <div class="row">
              <div class="form-group">
                <label for="testResult">Resultados</label>
                <textarea name="result" class="form-control onChange3" rows="5" cols="80" ></textarea>
              </div>
          </div>         
      </div>
      <div class="modal-footer">
          <div class="row">
              <div class="col-sm-2">
                  <button type="submit" class="btn btn-primary">Guardar</button>  
              </div>
              <div class="col-sm-2">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>  
              </div>
          </div>
      </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->