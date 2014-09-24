<%-- 
    Document   : ConsultationHistory
    Created on : Sep 7, 2014, 6:30:03 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-sm-12">
        <table id="tblConsultationHistory"  class="row-border hover">
            <thead>
                <th>Fecha</th>
                <th>Resumen</th>
                <th>Estatus</th>
            </thead>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <div class="row">
            <div class="col-sm-2">
                <div class="form-group">
                    <label for="inputCHAge">Edad</label>
                    <input type="text" class="form-control" id="inputCHAge" name="age" >
                </div>
            </div>
            <div class="col-sm-1">
                <div class="form-group">
                    <label for="inputCHWeight">Peso</label>
                    <input type="text" class="form-control" id="inputCHWeight" name="weight" >
                </div>
            </div>
            <div class="col-sm-1">
                <div class="form-group">
                    <label for="inputCHSize">Talla</label>
                    <input type="text" class="form-control" id="inputCHSize" name="size" >
                </div>
            </div>
            <div class="col-sm-1">
                <div class="form-group">
                    <label for="inputCHPC">PC</label>
                    <input type="text" class="form-control" id="inputCHPC" name="pc" >
                </div>
            </div>
            <div class="col-sm-1">
                <div class="form-group">
                    <label for="inputCHTA">TA</label>
                    <input type="text" class="form-control" id="inputCHTA" name="ta" >
                </div>
            </div>
            <div class="col-sm-1">
                <div class="form-group">
                    <label for="inputCHTemperature">Temperatura</label>
                    <input type="text" class="form-control" id="inputCHTemperature" name="temperature" >
                </div>
            </div>
            <div class="col-sm-3">
                <div class="form-group">
                    <label for="inputCHType">Tipo de consulta</label>
                    <select class="form-control" id="inputCHType" name="type" >
                        <c:forEach items="${consultationTypes}" var="type">
                            <option value="${type.idConsultationType}"><c:out value="${type.type}" /></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-sm-2">
                <button type="button" class='btn btn-primary' data-toggle="modal" data-target="#modalNewConsultation">Agregar</button>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-10">
        <div class="form-group">
            <label for="inputCHPEEA">PEEA</label>
            <textarea  class="form-control" id="inputCHPEEA" name="peea" ></textarea>
        </div>
    </div>
    <div class="col-sm-2">
        <button type="button" class='btn btn-primary' onclick="modifyConsultation()">Modificar</button>
    </div>
</div>
<div class="row">
    <div class="col-sm-10">
        <div class="form-group">
            <label for="inputCHEF">Examen Fisico</label>
            <textarea  class="form-control" id="inputCHEF" name="ef" ></textarea>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-10">
        <div class="form-group">
            <label for="inputCHAbstract">Resumen</label>
            <textarea class="form-control" id="inputCHAbstract" name="abstract" ></textarea>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-10">
        <div class="form-group">
            <label for="inputCHPrescription">Receta</label>
            <textarea class="form-control" id="inputCHPrescription" name="prescription" ></textarea>
        </div>
    </div>
</div>


<div class="modal fade" id="modalModifyConsultation" tabindex="-1" data-backdrop="static" data-keyboard="false" role="dialog" aria-labelledby="modalAddNewConsultation" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Agregar Consulta</h4>
      </div>
        <form id="formModifyConsultation">
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputCHMPatient">Paciente</label>
                        <input type='text' class="form-control" id="inputCHMPatient" name="patient" disabled />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="inputCHMBirthday">Fecha de Nacimiento</label>
                        <input type='text' class="form-control" id="inputCHMBirthday" name="birthday" disabled />
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="inputCHMAge">Edad</label>
                        <input type='text' class="form-control" id="inputCHMAge" name="age" disabled />
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMPN">Folio</label>
                        <input type='text' class="form-control" id="inputCHMPN" name="prescriptionNumber" disabled />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMDate">Fecha</label>
                        <input type='text' class="form-control" id="inputCHMDate" name="date" />
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="inputCHMMotive">Motivo</label>
                        <input type='text' class="form-control" id="inputCHMMotive" name="motive" />
                    </div>
                </div>
                <div class="col-sm-4">
                    <label for="inputCHMType">Tipo de consulta</label>
                    <select class="form-control" id="inputCHMType" name="type" >
                        <c:forEach items="${consultationTypes}" var="type">
                            <option value="${type.idConsultationType}"><c:out value="${type.type}" /></option>
                        </c:forEach>
                    </select>
                </div>  
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMWeight">Peso</label>
                        <input type='text' class="form-control partMBMI inputDecimal" id="inputCHMWeight" name="weight" />
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMSize">Talla (cm)</label>
                        <input type='text' class="form-control partMBMI inputDecimal" id="inputCHMSize" name="size" />
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMPC">PC (cm)</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHMPC" name="pc" />
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMBMI">Masa</label>
                        <input type='text' class="form-control" id="inputCHMBMI" name="bmi" readonly/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMTA">TA</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHMTA" name="ta" >
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMTA2">--</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHMTA2" name="ta2" >
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMTAAverage">/</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHMTAAverage" name="taaverage" >
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHMTemperature">Temperatura (C)</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHMTemperature" name="temperature" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-5">
                    <div class="form-group">
                        <label for="inputCHMPEEA">PEEA</label>
                        <textarea  class="form-control" id="inputCHMPEEA" name="peea" ></textarea>
                    </div>
                </div>
                <div class="col-sm-5">
                    <div class="form-group">
                        <label for="inputCHMEF">Examen Fisico</label>
                        <textarea  class="form-control" id="inputCHMEF" name="ef" ></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-5">
                    <div class="form-group">
                        <label for="inputCHMAbstract">Resumen</label>
                        <textarea class="form-control" id="inputCHMAbstract" name="abstract" ></textarea>
                    </div>
                </div>
                <div class="col-sm-5">
                    <div class="form-group">
                        <label for="inputCHMPrescription">Receta</label>
                        <textarea class="form-control" id="inputCHMPrescription" name="prescription" ></textarea>
                    </div>
                </div>
            </div>
        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        <button type="bsubmit" class="btn btn-primary" >Guardar</button>
      </div>
    </form>
    </div>
  </div>
</div>

<div class="modal fade" id="modalNewConsultation" tabindex="-1" data-backdrop="static" data-keyboard="false" role="dialog" aria-labelledby="modalAddNewConsultation" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Agregar Consulta</h4>
      </div>
        <form role="form" id="formNewConsultation">
        <div class="modal-body">
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputCHNPatient">Paciente</label>
                        <input type='text' class="form-control" id="inputCHNPatient" name="patient" value="${patient.firstName} ${patient.fatherLastName} ${patient.motherLastName}" disabled />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="inputCHNBirthday">Fecha de Nacimiento</label>
                        <input type='text' class="form-control" id="inputCHNBirthday" name="birthday" value="${birthday}" disabled />
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="inputCHNAge">Edad</label>
                        <input type='text' class="form-control" id="inputCHNAge" name="age" value="${age[0]}A ${age[1]}M ${age[2]}D" disabled />
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHNPN">Folio</label>
                        <input type='text' class="form-control" id="inputCHNPN" name="prescriptionNumber" value="${staff.presciptionNumber}" disabled />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <div class="form-group">
                        <label for="inputCHNDate">Fecha</label>
                        <input type='text' class="form-control" id="inputCHNDate" name="date" />
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label for="inputCHNMotive">Motivo</label>
                        <input type='text' class="form-control" id="inputCHNMotive" name="motive" />
                    </div>
                </div>
                <div class="col-sm-4">
                    <label for="inputCHMType">Tipo de consulta</label>
                    <select class="form-control" id="inputCHMType" name="type" >
                        <c:forEach items="${consultationTypes}" var="type">
                            <option value="${type.idConsultationType}"><c:out value="${type.type}" /></option>
                        </c:forEach>
                    </select>
                </div> 
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHNWeight">Peso</label>
                        <input type='text' class="form-control partNBMI inputDecimal" id="inputCHNWeight" name="weight" />
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHNSize">Talla (cm)</label>
                        <input type='text' class="form-control partNBMI inputDecimal" id="inputCHNSize" name="size" />
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHNPC">PC (cm)</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHNPC" name="pc" />
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHNBMI">Masa</label>
                        <input type='text' class="form-control" id="inputCHNBMI" name="bmi" readonly />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHNTA">TA</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHNTA" name="ta" >
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHNTA2">--</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHNTA2" name="ta2" >
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHNTAAverage">/</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHNTAAverage" name="taaverage" >
                    </div>
                </div>
                <div class="col-sm-2">
                    <div class="form-group">
                        <label for="inputCHNTemperature">Temperatura (C)</label>
                        <input type='text' class="form-control inputDecimal" id="inputCHNTemperature" name="temperature" >
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-5">
                    <div class="form-group">
                        <label for="inputCHNPEEA">PEEA</label>
                        <textarea  class="form-control" id="inputCHNPEEA" name="peea" ></textarea>
                    </div>
                </div>
                <div class="col-sm-5">
                    <div class="form-group">
                        <label for="inputCHNEF">Examen Fisico</label>
                        <textarea  class="form-control" id="inputCHNEF" name="ef" ></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-5">
                    <div class="form-group">
                        <label for="inputCHNAbstract">Resumen</label>
                        <textarea class="form-control" id="inputCHNAbstract" name="abstract" ></textarea>
                    </div>
                </div>
                <div class="col-sm-5">
                    <div class="form-group">
                        <label for="inputCHNPrescription">Receta</label>
                        <textarea class="form-control" id="inputCHNPrescription" name="prescription" ></textarea>
                    </div>
                </div>
            </div>
            
        </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        <button type="submit" class="btn btn-primary" >Guardar</button>
      </div>
      </form>
    </div>
  </div>
</div>