<%-- 
    Document   : fileUpload
    Created on : May 23, 2014, 11:02:50 AM
    Author     : Ccortina_Mac
--%>

<%@page contentType="text/html" pageEncoding="MacRoman"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<div class="row">
    <form id="formUploadFile" method="POST" role ="form" enctype="multipart/form-data" >
        <div class="form-group">
            <label for="file2">Seleccionar un archivo para subir:</label>
            <input class="form-control btn btn-primary" name="file2" id="file2" type="file"/>
        </div>
        <button class="btn btn-primary" id="btnUploadFile" value="Subir">Subir</button>
    </form>
</div>
<div class="row">
    <div class="col-sm-7">
        <table id="tblConsultationPatientDocuments" class="hover row-border">
            <thead>
                <tr>
                    <th>Documento</th>
                </tr>
            </thead>
        </table>
    </div>
    <div class="col-sm-3">
        <div class="row">
            <div class="col-sm-12">
                <input type="button" onclick="uploadFile()" value="Subir"/>
                <!--<button class="btn btn-primary" data-toggle="modal" data-target="#modalUploadFile">Subir documento</button>-->
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <input type="button" class="btn btn-primary" value="Abrir" onclick="openFile();">
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <input type="button" class="btn btn-danger" value="Eliminar" onclick="deleteFile();">
            </div>
        </div>
    </div>
</div>

<!--
<form id="formUploadFile">
<div class="modal fade" id="modalUploadFile" tabindex="-1" role="dialog" aria-labelledby="modalUploadFile" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Subir Documento</h4>
      </div>
      <div class="modal-body">
              <div class="row">
                  <div class="col-sm-6">
                      <div class="form-group">
                        <label for="inputUFDate">Fecha</label>
                        <input type="text" class="form-control" id="inputUFDate" placeholder="Fecha" name="date" />
                    </div>
                  </div>
                  <div class="col-sm-6">
                      <div class="form-group">
                        <label for="inputUFCategory">Fecha</label>
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
                        <textarea id="inputUFNotes" name="notes"></textarea>
                    </div>
                  </div>
              </div>
                <div class="row">
                    <input class="form-control btn btn-primary" name="path" id="inputUFPath" type="file"/>
                </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        <button type="submit" class="btn btn-primary" onclick="uploadFile()">Guardar</button>
      </div>
    </div>
  </div>
</div>
</form> -->