<%-- 
    Document   : measures
    Created on : May 27, 2014, 6:01:46 PM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-10">
        <table id="tblMeasuresConsultation" class="hover row-border">
            <thead>
                <tr>
                    <th>Medida</th>
                    <th>Valor</th>
                    <th>Unidad</th>
                    <th>idMeasure</th>
                    <th>include</th>
                </tr>
            </thead>
        </table>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <div class="col-sm-12">
                <a href="#modalMeasuresAdd" data-toggle="modal" class="btn btn-primary" >Agregar</a>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <input type="button" class="btn btn-danger" value="Eliminar" onclick="deleteMeasureConsultationRow();"/>
            </div>
        </div>
    </div>
</div>


<!--Modal for adding a measure to the consult-->
<div id="modalMeasuresAdd" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Agregar una medida</h4>
      </div>
      <div class="modal-body">
          <form role="form" id="formMeasuresAdd">
              <div class="row">
                  <div class="form-group">
                    <label for="idMeasure">Medida:</label>  
                    <input type="hidden" name="idMeasure" />
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-6">
                      <table id="tblMeasureCatalog" class="hover row-border">
                          <thead>
                              <tr>
                                  <th>Medida</th>
                              </tr>
                          </thead>
                      </table>
                  </div>
                  <div class="col-sm-2">
                      <a href="#modalMeasuresInnerModal" data-toggle="modal" class="btn btn-primary" >Nueva Medida</a>
                  </div>
              </div>
              <div class="row">
                  <div class="col-sm-6">
                      <div class="form-group">
                        <label for="measureValue">Valor</label>
                        <input type="text" name="measureValue" class="form-control inputNormal" />
                      </div>
                  </div>
                  <div class="col-sm-3">
                      <div class="form-group">
                        <label for="units">Unidades</label>
                        <input type="text" name="units" class="form-control" disabled/>
                      </div>
                  </div>
              </div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="row">
              <div class="col-sm-2">
                  <button type="button" class="btn btn-primary"  onclick="saveMeasureValue();">Guardar</button>  
              </div>
              <div class="col-sm-2">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>  
              </div>
          </div>
          <div class="row">
              <div id="modalDivlLaboratoryTestEditAjaxResponse"></div>
          </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Modal for editing a laboratory test-->
<div id="modalMeasuresInnerModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Nueva medida</h4>
      </div>
      <div class="modal-body">
          <form role="form" id="formMeasureNew">
              <div class="row">
                  <div class="form-group">
                    <label for="measure">Medida:</label>  
                    <input type="text" name="measure" class="form-control inputNormal"/>
                  </div>
              </div>
              <div class="row">
                  <div class="form-group">
                    <label for="units">Unidades</label>
                    <input type="text" name="units" class="form-control inputNormal" />
                  </div>
              </div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="row">
              <div class="col-sm-2">
                  <button type="button" class="btn btn-primary"  onclick="saveNewMeasure();">Guardar</button>  
              </div>
              <div class="col-sm-2">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>  
              </div>
          </div>
          <div class="row">
              <div id="modalDivlLaboratoryTestEditAjaxResponse"></div>
          </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
