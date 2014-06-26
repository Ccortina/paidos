<%-- 
    Document   : laboratory
    Created on : May 26, 2014, 4:11:58 PM
    Author     : Ccortina_Mac
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>

<style>
    .datepicker{z-index:1151;}
</style>

<div class="row">
    <div class="col-sm-10">
        <table id="tblLaboratoryTestsPatientData" class="hover row-border">
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
            <div class="col-sm-12">
                <a data-toggle="modal" href="#modalLaboratoryTestAdd" class="btn btn-primary" >Agregar</a>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <a data-toggle="modal" href="#modalLaboratoryTestEdit" class="btn btn-primary" >Modificar</a>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <input type="button" class="btn btn-danger" value="Eliminar" onclick="deleteLaboratoryTest();"/>
            </div>
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
      <div class="modal-body">
          <form role="form" id="formLaboratoryTestAdd">
              <div class="row">
                  <div class="form-group">
                    <label for="date">Fecha:</label>  
                    <input type="text" name="date" class="inputDate"/>
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
                    <textarea name="testResult" class="form-control inputTextArea" rows="5" cols="80" ></textarea>
                  </div>
              </div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="row">
              <div class="col-sm-2">
                  <button type="button" class="btn btn-primary" onclick="saveAddLaboratoryTestResult();">Guardar</button>
              </div>
              <div class="col-sm-2">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>  
              </div>
          </div>
          <div class="row">
              <div id="modalDivlLaboratoryTestAddAjaxResponse"></div>
          </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Modal for editing a laboratory test-->
<div id="modalLaboratoryTestEdit" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Modificar Estudio de Laboratorio</h4>
      </div>
      <div class="modal-body">
          <form role="form" id="formLaboratoryTestEdit">
              <div class="row">
                  <div class="form-group">
                    <label for="date">Fecha:</label>  
                    <input type="text" name="date" class="inputDate"/>
                  </div>
              </div>
              <div class="row">
                  <input type="hidden" name="idLaboratoryTestResult" />
                  <input type="hidden" name="idLaboratoryTest"/>
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
                    <textarea name="result" class="form-control" rows="5" cols="80" ></textarea>
                  </div>
              </div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="row">
              <div class="col-sm-2">
                  <button type="button" class="btn btn-primary" onclick="editPatientLT();">Guardar</button>  
              </div>
              <div class="col-sm-2">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>  
              </div>
          </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->