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
      <form role="form" id="formMeasuresAdd">  
      <div class="modal-body">          
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
                    <input type="text" name="measureValue" class="form-control" />
                  </div>
              </div>
              <div class="col-sm-3">
                  <div class="form-group">
                    <label for="units">Unidades</label>
                    <input type="text" name="units" class="form-control" disabled/>
                  </div>
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
<div id="modalMeasuresInnerModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Nueva medida</h4>
      </div>
      <form role="form" id="formMeasureNew">  
      <div class="modal-body">
            <div class="row">
                <div class="form-group">
                    <label for="inputNewItem">Medida</label>
                    <input type="text" class="form-control" id="inputNewMeasure" placeholder="Medida" name="itemName"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="inputNewItemUnit">Unidad(Ej. cm,Kg.)</label>
                    <input type="text" class="form-control" id="inputNewMeasureUnit" placeholder="Unidad" name="unit"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label>
                        <input id="inputNewMeasureInclude" type="checkbox" name="include" checked>
                        Incluir al generar receta durante las consultas
                    </label>
                </div>   
            </div>
            <div class="row">
                <div class="form-group">
                    <label>
                        <input id="inputNewMeasureActive" type="checkbox" name="active" checked>
                        Activo
                    </label>
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
