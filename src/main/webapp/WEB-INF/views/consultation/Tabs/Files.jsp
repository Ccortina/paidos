<%-- 
    Document   : fileUpload
    Created on : May 23, 2014, 11:02:50 AM
    Author     : Ccortina_Mac
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<div class="row">
    <div class="col-sm-8">
        <table id="tblConsultationPatientDocuments" class="hover row-border">
            <thead>
                <th>Fecha</th>
                <th>Categoria</th>
                <th>Descripcion</th>
            </thead>
        </table>
    </div>
    <div class="col-sm-4">
        <div class="row">
            <button class="btn btn-primary" data-toggle="modal" data-target="#modalUploadFileStart">Agregar</button>
        </div>
        <div class="row">
            <button type="button" class="btn btn-primary" onclick="loadFileData()">Modificar</button>
        </div>
        <div class="row">
            <button type="button" class="btn btn-danger" onclick="deleteDocument()">Borrar</button>
        </div>
        <div class="row">
            <button type="button" class="btn btn-primary" onclick="openDocument()">Mostrar</button>
        </div>
    </div>
</div>

<div class="modal fade" id="modalUploadFileStart" tabindex="-1" role="dialog" aria-labelledby="modalUploadFileStart" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Subir Documento</h4>
      </div>
        <div class="modal-body">
            <div class="row">
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Seleccionar archivo...</span>
                    <!-- The file input field used as target for the file upload widget -->
                    <input name="file" id="inputUF" type="file" multiple=""/>
                </span>
            </div>
            <div class="row">
                <br>
                <div id="progress">
                    <div class="bar" style="width: 0%;"></div>
                </div>
            </div>
            <div class="row">
                <div id="filesUploaded" class="files"></div>
            </div>
        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="modalUploadFileEnd" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="modalUploadFile" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Subir Documento</h4>
      </div>
        <form id="formUploadFile"  >
          <div class="modal-body">
                  <div class="row" id="divUploadFileMessage" style="color:#468847;background-color:#dff0d8;border-color:#d6e9c6" >
                      <h5>El Archivo se ha subido con exito, llene la informacion relacionada</h5>
                  </div>
                  <div class="row">
                      <div class="col-sm-6">
                          <div class="form-group">
                            <label for="inputUFDate">Fecha</label>
                            <input type="text" class="form-control" id="inputUFDate" placeholder="Fecha" name="date" />
                        </div>
                      </div>
                      <div class="col-sm-6">
                          <div class="form-group">
                            <label for="inputUFCategory">Tipo</label>
                            <select class="form-control" id="inputUFCategory" name="category" >
                                <c:forEach items="${documentCategories}" var="type">
                                    <option value="${type.idDocumentCategory}"><c:out value="${type.category}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-12">
                          <div class="form-group">
                            <label for="inputUFDescription">Descripcion</label>
                            <input type="text" class="form-control" id="inputUFDescription" placeholder="Descripcion" name="description" />
                        </div>
                      </div>
                  </div>
                  <div class="row">              
                      <div class="col-sm-12">
                          <div class="form-group">
                            <label for="inputUFNotes">Notas</label>
                            <textarea id="inputUFNotes" name="notes" rows="4" cols="50"></textarea>
                        </div>
                      </div>
                  </div>
          </div>
        </form>  
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="uploadFile()">Guardar</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="modalModifyFile" tabindex="-1" role="dialog" aria-labelledby="modalModifyFile" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Modificar Documento</h4>
      </div>
        <form id="formModifyFile"  >
          <input type="hidden" id="inputMUFid" name="idDocument"/>
          <div class="modal-body">
                  <div class="row">
                      <div class="col-sm-6">
                          <div class="form-group">
                            <label for="inputMUFDate">Fecha</label>
                            <input type="text" class="form-control" id="inputMUFDate" placeholder="Fecha" name="date" />
                        </div>
                      </div>
                      <div class="col-sm-6">
                          <div class="form-group">
                            <label for="inputMUFCategory">Tipo</label>
                            <select class="form-control" id="inputMUFCategory" name="category" >
                                <c:forEach items="${documentCategories}" var="type">
                                    <option value="${type.idDocumentCategory}"><c:out value="${type.category}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-12">
                          <div class="form-group">
                            <label for="inputMUFDescription">Descripcion</label>
                            <input type="text" class="form-control" id="inputMUFDescription" placeholder="Descripcion" name="description" />
                        </div>
                      </div>
                  </div>
                  <div class="row">              
                      <div class="col-sm-12">
                          <div class="form-group">
                            <label for="inputMUFNotes">Notas</label>
                            <textarea id="inputMUFNotes" name="notes" rows="4" cols="50"></textarea>
                        </div>
                      </div>
                  </div>
          </div>
        </form>  
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>  
        <button type="button" class="btn btn-primary" onclick="modifyFile()">Guardar</button>
      </div>
    </div>
  </div>
</div>