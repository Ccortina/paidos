<%-- 
    Document   : ImmunizationHome
    Created on : Aug 19, 2014, 5:28:37 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>

<jsp:include page="../Includes/header.jsp"/>

<!-- Files for data tables function-->
<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.min.css" />

<c:url var="momentJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment.min.js" />
<c:url var="momentRangeJs" value="/resources/js/JQueryPlugins/Fullcalendar/moment-range.js" />

<c:url var="inputmaskJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.js" />
<c:url var="inputmaskDateJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.date.extensions.js" />
<c:url var="inputmaskRegexJs" value="/resources/js/JQueryPlugins/InputMask/jquery.inputmask.regex.extensions.js" />

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="immunizationHomeJs" value="/resources/js/PatientHome/ImmunizationHome.js" />

<link href="${dataTablesCSS}" rel="stylesheet" />
<link href="${dtModCSS}" rel="stylesheet" />

<link href="${bvCSS}" rel="stylesheet" />

<div class="container-fluid">
    <div classs="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Pacientes - Inmunizaciones</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="tblImmunization" class="hover row-border">
                                <thead>
                                    <th>A. Paterno</th>
                                    <th>A. Materno</th>
                                    <th>Nombre</th>
                                    <th>F. Nacimiento</th>
                                    <th>Inmunizacion</th>
                                    <th>Tipo</th>
                                    <th>F. Programada</th>
                                    <th>A - M - D</th>
                                    <th>F. Aplicada</th>
                                    <th>S V A</th>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <form id="formFilters">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="checkbox">
                                        <label>
                                          <input type="checkbox" id="checkProgrammedDatesRange"  name="checkProgrammedDatesRange"> Entre rango de fechas programadas
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6"><label>Desde</label><input type="text" class="form-control" id="inputProgrammedDateStart" name="inputProgrammedDateStart"/></div>
                                <div class="col-sm-6"><label>Hasta</label><input type="text" class="form-control" id="inputProgrammedDateEnd" name="inputProgrammedDateEnd"/></div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="checkbox">
                                        <label>
                                          <input type="checkbox" id="checkByInmunization" name="checkByInmunization"> Por Inmunizacion
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <label>Inmunizacion</label>
                                    <select class="form-control" id="selectVaccine" name="doctor">
                                        <option value="0">Cualquiera</option>
                                        <c:forEach var="vaccine" items="${vaccines}">
                                            <option value="${vaccine.idVaccine}"><c:out value="${vaccine.vaccine}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-6">
                                    <label>Lote</label>
                                    <input type="text" class="form-control" id="inputBatch" />
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="row">
                                        <div class="col-sm-2"><input type="checkbox" class="radio" value="1" name="fooby[1][]" /></div>
                                        <div class="col-sm-10"><label>Solo no aplicadas</label></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="row">
                                        <div class="col-sm-2"><input type="checkbox" class="radio" value="2" name="fooby[1][]" /></div>
                                        <div class="col-sm-10"><label>Solo aplicadas</label></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="checkbox">
                                        <label>
                                          <input type="checkbox" id="checkAgesRange" name="checkAgesRange"> Entre rangos de edad de aplicacion
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="row">
                                        <label>Desde</label>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="Años" class="form-control" id="inputAgeBeginYear" name="inputAgeBeginYear" value="0"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="Meses" class="form-control" id="inputAgeBeginMonth" name="inputAgeBeginMonth" value="0"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="Dias" class="form-control" id="inputAgeBeginDay" name="inputAgeBeginDay" value="0"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="row">
                                        <label>Hasta</label>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="Años" class="form-control" id="inputAgeEndYear" name="inputAgeEndYear" value="0"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="Meses" class="form-control" id="inputAgeEndMonth" name="inputAgeEndMonth" value="0"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="Dias" class="form-control" id="inputAgeEndDay" name="inputAgeEndDay" value="0"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" id="checkBirthdayRange" name="checkBirthdayRange">Entre rango de fechas nacimiento
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6"><label>Desde</label><input type="text" class="form-control" id="inputBirthdayStart" name="inputBirthdayStart"/></div>
                                <div class="col-sm-6"><label>Hasta</label><input type="text" class="form-control" id="inputBirthdayEnd" name="inputBirthdayEnd"/></div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="row"><br></div>
                            <div class="row">
                                <div class="checkbox">
                                    <label>
                                      <input type="checkbox" id="checkBytype" name="checkBytype"> Por tipo
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <select class="form-control" id="selectVaccineType" name="doctor">
                                    <option value="0">Cualquiera</option>
                                    <c:forEach var="vaccineType" items="${vaccineTypes}">
                                        <option value="${vaccineType.idvaccineType}"><c:out value="${vaccineType.type}" /></option>
                                    </c:forEach>
                                </select>
                            </div> 
                        </div>
                        <div class="col-sm-4">
                            <div class="row">
                                <br>
                            </div>
                            <div class="row">
                                <br>
                            </div>
                            <div class="row">
                                <div class="col-sm-2">
                                    <button type="submit" class="btn btn-primary" >Filtrar</button>
                                </div>
                                <div class="col-sm-2">
                                    <input type="button" class="btn btn-danger" value="Quitar filtros" onclick="quitFilters()"/>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    </form>    
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

<script src="${dataTablesJS}" type="text/javascript"></script>

<script src="${momentJs}" type="text/javascript"></script>
<script src="${momentRangeJs}" type="text/javascript"></script>

<script src="${inputmaskJs}" type="text/javascript"></script>
<script src="${inputmaskDateJs}" type="text/javascript"></script>
<script src="${inputmaskRegexJs}" type="text/javascript"></script>

<script src="${bvJs}" type="text/javascript"></script>

<script src="${bootboxJs}" type="text/javascript"></script>

<script src="${utilityJs}" type="text/javascript"></script>

<script src="${immunizationHomeJs}" type="text/javascript"></script>
