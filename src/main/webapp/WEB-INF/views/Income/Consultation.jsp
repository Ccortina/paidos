<%-- 
    Document   : Consultation
    Created on : Sep 24, 2014, 3:22:13 PM
    Author     : Carlos Cortina
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<c:url var="consultationJs" value="/resources/js/Income/Consultation.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="row">
    <div class="col-sm-3">
        <butto type="button" class="btn btn-primary" onclick="showAllCCA()">Mostrar todas las consultas</butto>
    </div>
</div>
</div>
<div class="row">
    <div class="col-sm-12">
        <table id="tblConsultationCostAbstract" class="row-border hover">
            <thead>
                <th>Fecha</th>
                <th>A. Paterno</th>
                <th>A. Materno</th>
                <th>Nombre</th>
                <th>Estatus</th>
                <th>Total</th>
                <th>Resto</th>
            </thead>
        </table>
    </div>
</div>
<sec:authorize access="hasAnyRole('Doctor','Ingresos_Consultas_2')">
<div class="row">
    <div class="col-sm-3">
        <button type="button" class="btn btn-primary" onclick="openPaymentModal();">Pagar/Liquidar</button>
    </div>
</div>
</sec:authorize>    

<script src="${dataTablesJS}" type="text/javascript"></script>

<script src="${momentJs}" type="text/javascript"></script>

<script src="${bvJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>

<script src="${utilityJs}" type="text/javascript"></script>

<script src="${consultationJs}" type="text/javascript"></script>

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
              <table id="tblConsultationActivity" class="row-border">
                  <thead>
                    <tr>  
                        <th>Actividad</th>
                        <th>Costo</th>
                        <th>Incluir en recibo</th>
                    </tr>
                  </thead>
              </table>
          </div>   
          <div class="row">
              <div class="col-sm-4">
                  <div class="form-group">
                      <label>Total:</label>
                      <input type="text" class="form-control" id="inputTotal" readonly="true" />
                  </div>
              </div>
              <div class="col-sm-4">
                  <div class="form-group">
                      <label>Consultorio:</label>
                      <input type="text" class="form-control" id="inputTotalConsultory" readonly="true" />
                  </div>
              </div>
              <div class="col-sm-4">
                  <div class="form-group">
                      <label>Externas:</label>
                      <input type="text" class="form-control" id="inputTotalExternal" readonly="true" />
                  </div>
              </div>
          </div>
          <hr>
          <div class="row">
              <div class="col-sm-6">
                  <div class="radio">
                      <label>
                          <input type="radio" name="paymentType" value="1" checked>
                          Liquidacion
                      </label>
                  </div>
              </div>
              <div class="col-sm-6">
                  <div class="radio">
                      <label>
                          <input type="radio" name="paymentType" value="2">
                          Pago Parcial
                      </label>
                  </div>
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
        <button type="submit" class="btn btn-primary">Guardar pago</button>
      </div>
      </form>  
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal receipt -->
<div class="modal fade" id="receiptModal" data-backdrop="static" data-keyboard="false" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h4 class="modal-title">Recibo</h4>
            </div>
            <form id="formReceiptPayer">
            <div class="modal-body">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                  <li class="active"><a href="#client" role="tab" data-toggle="tab">Recibo</a></li>
                  <li><a href="#payment" role="tab" data-toggle="tab">Pago</a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane active" id="client">
                        <div class="row">
                            <div class="col-sm-8">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>A nombre de:</label>
                                            <input type="text" class="form-control" name="payerName"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Calle:</label>
                                            <input type="text" class="form-control" name="street"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Col./Frac.:</label>
                                            <input type="text" class="form-control" name="colony"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Mpio./Del.:</label>
                                            <input type="text" class="form-control" name="city"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label>Estado:</label>
                                            <input type="text" class="form-control" name="state"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label>CP:</label>
                                            <input type="text" class="form-control" name="zip"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label>Pais:</label>
                                            <input type="text" class="form-control" name="country"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>RFC:</label>
                                            <input type="text" class="form-control" name="rfc"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Concepto:</label>
                                            <textarea name="concept" class="form-control" cols="10"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label>Fecha:</label>
                                            <input type="text" class="form-control" name="date" readonly="true"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#realtivesModal">Familiar</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#thirdPartyPayersModal">Terceros pagadores</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <hr>
                                    <button type="submit" class="btn btn-primary">Guardar recibo</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane" id="payment">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="row">
                                    <div class="col-sm-offset-3 col-sm-6">
                                        <h2 id="receiptStatus"></h2>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <label>Cantidad</label>
                                                    <input type="text" name="receiptSum" class="form-control" readonly="true" value="0.0"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" name="retention"> Ret.
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-sm-8">
                                                <div class="from-group">
                                                    <label>ISR</label>
                                                    <input type="text" name="isr" class="form-control" readonly="true" value="0.0"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group">
                                                    <label>Total</label>
                                                    <input type="text" name="receiptTotal" class="form-control" readonly="true" value="0.0"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="row">
                                            <div class="form-group">
                                                <label>Folio</label>
                                                <input type="text" name="receiptNumber" class="form-control" readonly="true" value="0.0"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <label>Notas:</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <textarea name="notes" cols="10" class="form-control"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
            </div> 
            </form>    
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal relative -->
<div class="modal fade" id="realtivesModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h4 class="modal-title">Familiares</h4>
            </div>
            <div class="modal-body">
                <table class="row-border hover" id="tblRelatives">
                    <thead>
                        <th>Apellido paterno</th>
                        <th>Apellido materno</th>
                        <th>Nombre</th>
                        <th>RFC</th>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" onclick="selectRelative()">Seleccionar</button>
            </div> 
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal relative -->
<div class="modal fade" id="thirdPartyPayersModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h4 class="modal-title">Terceros pagadores</h4>
            </div>
            <div class="modal-body">
                <table class="row-border hover" id="tblThirdPartyPayers">
                    <thead>
                        <th>Nombre</th>
                        <th>RFC</th>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" onclick="selectThirdPartyPayer()">Seleccionar</button>
            </div> 
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->