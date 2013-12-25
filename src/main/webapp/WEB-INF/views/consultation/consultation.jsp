<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../Includes/header.jsp"/>

<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.css" />


<script src="${dataTablesJS}" type="text/javascript"></script>

<link href="${dataTablesCSS}" rel="stylesheet" />

<!-- Make modal diagnostic bigger -->
<style type="text/css">
	#modalDiagnostic .modal-dialog
	{
		width:60%;
	}
	
	.row_selected
	{
    	color:red;
	}​
</style>
<!-- Main div , wrapper -->
<div id="wrapper">
    
    <!-- SideBar, the persistent data of the pacient -->
    <div id="sidebar-wrapper">
        <div class="row ">
            <!-- The row for the image of the patient -->
            <div class="col-md-12">
                <img data-src="" alt="Imagen no disponible" />
            </div>
        </div>
        <div class="row">
            <!-- The row of the name for the patient  -->
            <div class="col-md-12">
                Paciente:
                ${patient.firstName} ${patient.secondName} ${patient.fatherLastName} ${patient.motherLastName}
            </div>
        </div>
        <div class="row">
            <!-- The row for the age of the patient -->
            <div class="col-md-12">
                Edad:
                ${age[0]} A ${age[1]} M ${age[2]} D
            </div>
        </div>
        <div class="row">    
            <!-- The row for the age of the patient -->
            <div class="col-md-12">
                Apodo:
                ${patient.nickname}
            </div>
        </div>
        <div class="row">    
            <!-- The row for the bith date -->
            <div class="col-md-12">
                Fecha Nacimiento:
                ${birthday}
            </div>
        </div>
    </div>
    <div id="content-wrapper">
        <div class="container">
            <div class="row">
                <fieldset>
                    <div class="row">
                        <div class="col-md-6">
                            Motivo:
                            
                        </div>
                        <div class="col-md-6">
                            Fecha:
                            ${date}
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>
</div>