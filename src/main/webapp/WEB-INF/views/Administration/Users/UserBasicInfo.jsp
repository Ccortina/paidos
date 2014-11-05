<%-- 
    Document   : UserBasicInfo
    Created on : Oct 25, 2014, 7:33:12 PM
    Author     : Carlos Cortina
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-sm-6">
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Nombre de Usuario</label>
                    <input type="text" class="form-control" value="${loggedUser.username}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control" value="${loggedUser.email}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Puesto</label>
                    <input type="text" class="form-control" value="${loggedUser.idRole.role}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Nombre</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.name}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Apellido</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.lastName}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Telefono</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.phone}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Celular</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.cellPhone}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Cedula Profesional</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.professionalNumber}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Folio recetas</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.presciptionNumber}" readonly/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <label>Folio recibos</label>
                    <input type="text" class="form-control" value="${loggedUser.idStaffMember.receiptNumber}" readonly/>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="row">
            <div class="col-sm-6">
                <button type="button" class="btn btn-primary" onclick="modifyUser();">Modificar</button>
            </div>
        </div>
    </div>            
</div>
