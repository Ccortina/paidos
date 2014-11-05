<%-- 
    Document   : UserModify
    Created on : Oct 25, 2014, 8:26:56 PM
    Author     : Carlos Cortina
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <form id="formModifyUser">
    <div class="col-sm-6">
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Nombre de Usuario</label>
                    <input type="text" class="form-control" value="${loggedUser.username}" disabled/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Contraseña</label>
                    <input type="password" class="form-control" name="npassword" value="" autocomplete="off"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Repetir Contraseña</label>
                    <input type="password" class="form-control" name="rpassword" value="" autocomplete="off"/>
                </div>
            </div>
        </div>        
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control" value="${loggedUser.email}" name="email"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <button type="button" class="btn btn-primary" onclick="modifyUserData()">Guardar</button>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Nombre</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.name}" name="firstName"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Apellido</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.lastName}" name="lastName"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Telefono</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.phone}" name="phone"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Celular</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.cellPhone}" name="cellPhone"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Cedula Profesional</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.professionalNumber}" name="professionalNumber"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Folio recetas</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.presciptionNumber}" name="prescriptionNumber"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Folio recibos</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.receiptNumber}" name="receiptsNumber"/>
                </div>
            </div>
        </div>
    </div>
    </form>
</div>
