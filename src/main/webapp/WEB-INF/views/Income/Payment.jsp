<%-- 
    Document   : Payment
    Created on : Oct 5, 2014, 10:08:20 PM
    Author     : Carlos Cortina
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>

<jsp:include page="../Includes/header.jsp"/>

<!-- Files for data tables function-->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />
<c:url var="dtModCSS" value="/resources/CSS/DataTables/datatables.mod.css" />

<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="paymentJs" value="/resources/js/Income/Payment.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="row">
    <div class="col-sm-12">
        <table id="tblPayment" class="row-border hover">
            <thead>
                <th>Fecha</th>
                <th>Paciente</th>
                <th>Pago</th>
                <th>Cambio</th>
                <th>Notas</th>
                <th>Tipo</th>
            </thead>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-sm-3">
        <button type="button" class="btn btn-primary" onclick="showDetails();">Detalles</button>
    </div>
    <div class="col-sm-3">
        <button type="button" class="btn btn-danger" onclick="cancel();">Cancelar</button>
    </div>
    <div class="col-sm-3">
        <button type="button" class="btn btn-primary" onclick="specialPayment();">Pago especial</button>
    </div>
</div>

<script src="${dataTablesJS}" type="text/javascript"></script>

<script src="${momentJs}" type="text/javascript"></script>

<script src="${bvJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>

<script src="${utilityJs}" type="text/javascript"></script>

<script src="${paymentJs}" type="text/javascript"></script>

<!-- PaymentModal -->
<div class="modal fade" id="paymentModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Pago</h4>
      </div>
      <form id="formPayment">  
      <div class="modal-body">  
          <div class="row">
              <div class="col-sm-offset-3 col-sm-6">
                  <h4 id="paymentTypeTitle"></h4>
              </div>
          </div>
          <div class="row">
              <div class="col-sm-12">
                  <div class="row">
                      <div class="col-sm-6">
                          <div class="form-group">
                            <label>Efectivo:</label>
                            <input type="text" class="form-control changeV" name="cash" id="inputPaymentCash" value="0.0"/>
                          </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-6">
                          <div class="form-group">
                              <label>Cheque:</label>
                              <input type="text" class="form-control changeV" name="check" id="inputPaymentCheck" value="0.0"/>
                          </div>
                      </div>
                      <div class="col-sm-6">
                          <div class="form-group">
                              <label>Digitos:</label>
                              <input type="text" class="form-control" name="checkD" id="inputPaymentCheckD"/>
                          </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-6">
                          <div class="form-group">
                              <label>Tarjeta:</label>
                              <input type="text" class="form-control changeV" name="card" id="inputPaymentCard" value="0.0"/>
                          </div>
                      </div>
                      <div class="col-sm-6">
                          <div class="form-group">
                              <label>Digitos:</label>
                              <input type="text" class="form-control" name="cardD" id="inputPaymentCardD"/>
                          </div>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-6">
                          <div class="form-group">
                              <label>Otro:</label>
                              <input type="text" class="form-control changeV" name="other" id="inputPaymentOther" value="0.0"/>
                          </div>
                      </div>
                      <div class="col-sm-6">
                          <div class="form-group">
                              <label>Descripcion:</label>
                              <input type="text" class="form-control" name="otherD" id="inputPaymentOtherD"/>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
          <hr>    
          <div class="row">
              <div class="col-sm-6">
                  <div class="form-group">
                      <label>Total pago:</label>
                      <input type="text" class="form-control" name="paymentTotal" id="paymentTotal" value="0.0" readonly="true"/>
                  </div>
              </div>
              <div class="col-sm-6">
                  <div class="form-group">
                      <label>Cambio:</label>
                      <input type="text" class="form-control" name="change" id="paymentChange" value="0.0" readonly="true"/>
                  </div>
              </div>
          </div>
          <div class="row">
              <div class="col-sm-12">
                  <div class="row">
                      <div class="col-sm-12">
                        <label>Notas:</label>
                      </div>
                  </div>
                  <div class="row">
                      <div class="col-sm-12">
                        <textarea name="notes" cols="20" ></textarea>
                      </div>
                  </div>
              </div>
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
      </div>
      </form>  
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
