<%-- 
    Document   : NewUser
    Created on : Nov 9, 2014, 10:09:26 PM
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../../Includes/header.jsp"/>

<script type="text/javascript">
    var contextPath='<%=request.getContextPath()%>';
</script>

<c:url var="bvCSS" value="/resources/CSS/BootstrapValidator/bootstrapValidator.min.css" />
<c:url var="bvJs" value="/resources/js/BootstrapPlugins/BootstrapValidator/bootstrapValidator.min.js" />

<c:url var="bootboxJs" value="/resources/js/BootstrapPlugins/Bootbox/bootbox.min.js" />

<c:url var="utilityJs" value="/resources/js/Utility/UtilityMethods.js" />

<c:url var="newUserJs" value="/resources/js/Administration/User/NewUser.js" />

<link href="${bvCSS}" rel="stylesheet" />
<!DOCTYPE html>

<div class="container-fluid">
    <div class="row">
        <form id="formNewUser">
        <div class="col-sm-6">
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Nombre de Usuario</label>
                        <input type="text" class="form-control" name="userName" placeholder="Nombre de usuario"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Contraseña</label>
                        <input type="password" class="form-control" name="password"  autocomplete="off"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Repetir Contraseña</label>
                        <input type="password" class="form-control" name="confirm_password" autocomplete="off"/>
                    </div>
                </div>
            </div>        
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" class="form-control" placeholder="Email" name="email"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Rol o Puesto:</label>
                        <select name="role" class="form-control">
                            <c:forEach items="${roles}" var="rol">
                                <option value="${rol.idRole}">${rol.role}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" class="form-control" placeholder="Nombre" name="firstName"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Apellido</label>
                        <input type="text" class="form-control" placeholder="Apellido" name="lastName"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Telefono</label>
                        <input type="text" class="form-control" placeholder="Telefono" name="phone"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Celular</label>
                        <input type="text" class="form-control" placeholder="Celular" name="cellPhone"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Cedula Profesional</label>
                        <input type="text" class="form-control" placeholder="Cedula" name="professionalNumber"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Folio recetas</label>
                        <input type="text" class="form-control" placeholder="No. Prescripcion" name="prescriptionNumber" value="0"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label>Folio recibos</label>
                        <input type="text" class="form-control" placeholder="No. de Recibo" name="receiptsNumber" value="0"/>
                    </div>
                </div>
            </div>
        </div>
        </form>
    </div>
</div>

<script src="${newUserJs}" type="text/javascript"></script>
<script src="${bvJs}" type="text/javascript"></script>
<script src="${bootboxJs}" type="text/javascript"></script> 
<script src="${utilityJs}" type="text/javascript"></script>