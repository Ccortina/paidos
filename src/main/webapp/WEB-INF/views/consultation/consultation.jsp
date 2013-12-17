<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../Includes/header.jsp"/>

<c:url var="dataTablesJS" value="/resources/js/jquery.dataTables.min.js" />
<c:url var="dataTablesCSS" value="/resources/CSS/jquery.dataTables.css" />


<script src="${dataTablesJS}" type="text/javascript"></script>

<link href="${dataTablesCSS}" rel="stylesheet" />

<!-- Make modalDiagnostic bigger -->
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

<!-- The Patients data -->
<div class="container">
	<div class="row">
		<div class="col-md-2">
			<div class="from-group">
				<label for="date">Motivo :</label>
				<input class="form-control input-sm" id="motive" type="text" value="Motivo va aqui" disabled />
			</div>
		</div>
		<div class="col-md-2 col-md-offset-8">
			<div class="from-group">
				<label for="date">Fecha :</label>
				<input class="form-control input-sm" id="date" type="text" value="${date}" disabled />
			</div>
		</div>
	</div>
	<fieldset disabled>

		<legend>Datos del Paciente</legend>

		<div class="row">
			<!-- Display information Column -->
			<div class="col-md-10">
				<div class="row">
					<!-- Left column of the grid Row1-->
					<div class="col-md-6">
						<div class="from-group">
							<label for="date">Paciente :</label>
							<input class="form-control input-sm" id="name" type="text" 
								value="${patient.firstName} ${patient.secondName} ${patient.fatherLastName} ${patient.motherLastName}" />
						</div>
					</div>
					<!-- Right column of the grid Row1 -->
					<div class="col-md-6">
						<div class="from-group">
							<label for="date">Apodo :</label>
							<input class="form-control input-sm" id="nickname" type="text" value="${patient.nickname}" />
						</div>
					</div>
				</div>
				<div class="row">
					<!-- Left column of the grid Row2 -->
					<div class="col-md-6">
						<div class="from-group">
							<label for="age">Edad :</label>
							<input class="form-control input-sm" id="age" type="text" value="${age[0]} A ${age[1]} M ${age[2]} D " />
						</div>
					</div>
					<!-- Right column of the grid Row2-->
                                        <div class="col-md-6">
						<div class="from-group">
							<label for="bithday">Fecha Nacimiento :</label>
							<input class="form-control input-sm" id="birthday" type="text" 
								value="${birthday}" />
						</div>
					</div>        
				</div>
				<div class="row">
					<!-- Just one column -->
					<div class="col-md-2 col-md-offset-1">
						<div class="from-group">
							<label for="weight">Peso(Kg) :</label>
							<input class="form-control input-sm" id="weight" type="text" value="${appointment.weigth}" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="from-group">
							<label for="size">Talla (cm) :</label>
							<input class="form-control input-sm" id="size" type="text" value="${appointment.size}" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="from-group">
							<label for="pc">PC (cm) :</label>
							<input class="form-control input-sm" id="pc" type="text" value="${appointment.pc}" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="from-group">
							<label for="ta">TA :</label>
							<input class="form-control input-sm" id="ta" type="text" 
								value="${appointment.ta} / ${appointment.ta2} - ${appointment.averageTa}" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="from-group">
							<label for="temperature">Temperatura :</label>
							<input class="form-control input-sm" id="temperature" type="text" value="${appointment.temperature}" />
						</div>
					</div>
				</div>
			</div>
			<!-- Buttons Column -->
			<div class="col-md-2">
			</div>
		</div>
	</fieldset>
	<div id="ajaxMessage">
	</div>
	<h3>Resumen</h3>
	<div class="row">
		<div class="col-md-12">
			<ul class="nav nav-tabs">
			  <li class="active"><a href="#generales" data-toggle="tab">Generales</a></li>
			  <li><a href="#antecedentes" data-toggle="tab">Antecedentes</a></li>
			  <li><a href="#documentos" data-toggle="tab">Documentos</a></li>
			  <li><a href="#graficas" data-toggle="tab">Graficas</a></li>
			  <li><a href="#inmunizaciones" data-toggle="tab">Inmunizaciones</a></li>
			  <li><a href="#labGabinetes" data-toggle="tab">Lab. Gabinete</a></li>
			  <li><a href="#medidas" data-toggle="tab">Medidas</a></li>
			  <li><a href="#peeaef" data-toggle="tab">PEEA/E.F</a></li>
			  <li><a href="#diagnostico" data-toggle="tab">Diagnostico</a></li>
			  <li><a href="#receta" data-toggle="tab">Receta</a></li>
			  <li><a href="#actividades" data-toggle="tab">Actividades</a></li>
			  <li><a href="#resumen" data-toggle="tab">Resumen</a></li>
			</ul>
			
			<div class="tab-content">
				<div id="generales" class="tab-pane active">
					<jsp:include page="generalsDiv.jsp"/>
				</div>
				<div id="antecedentes" class="tab-pane">
					<jsp:include page="backgroundDiv.jsp"/>
				</div>
				<div id="documentos" class="tab-pane">documentos</div>
				<div id="graficas" class="tab-pane">graficas</div>
				<div id="inmunizaciones" class="tab-pane">inmunizaciones</div>
				<div id="labGabinetes" class="tab-pane">laboratorio gabinete</div>
				<div id="medidas" class="tab-pane">medidas</div>
				<div id="peeaef" class="tab-pane">peea ef</div>
				<div id="diagnostico" class="tab-pane">
					<jsp:include page="diagnosticDiv.jsp"/>
				</div>
				<div id="receta" class="tab-pane">receta</div>
				<div id="actividades" class="tab-pane">actividades</div>
				<div id="resumen" class="tab-pane">resumen</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var oTable;
	var frequentDiagnosticTable;
	var json;
	var rowSelectedData;
	var diagnosticsTable;
	var treatmentsTable = false;
	var selectedDiagnosticId;
	var reload;
	
	$.fn.dataTableExt.oApi.fnReloadAjax = function ( oSettings, sNewSource, fnCallback, bStandingRedraw )
	{
	    if ( typeof sNewSource != 'undefined' && sNewSource != null )
	    {
	        oSettings.sAjaxSource = sNewSource;
	    }
	    this.oApi._fnProcessingDisplay( oSettings, true );
	    var that = this;
	    var iStart = oSettings._iDisplayStart;
	    var aData = [];

	    this.oApi._fnServerParams( oSettings, aData );

	    oSettings.fnServerData( oSettings.sAjaxSource, aData, function(json) {
	        /* Clear the old information from the table */
	        that.oApi._fnClearTable( oSettings );

	        /* Got the data - add it to the table */
	        var aData =  (oSettings.sAjaxDataProp !== "") ?
	            that.oApi._fnGetObjectDataFn( oSettings.sAjaxDataProp )( json ) : json;

	        for ( var i=0 ; i<aData.length ; i++ )
	        {
	            that.oApi._fnAddData( oSettings, aData[i] );
	        }

	        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
	        that.fnDraw();

	        if ( typeof bStandingRedraw != 'undefined' && bStandingRedraw === true )
	        {
	            oSettings._iDisplayStart = iStart;
	            that.fnDraw( false );
	        }

	        that.oApi._fnProcessingDisplay( oSettings, false );

	        /* Callback user function - for event handlers etc */
	        if ( typeof fnCallback == 'function' && fnCallback != null )
	        {
	            fnCallback( oSettings );
	        }
	    }, oSettings );
	}
	
	$(document).ready(function(){
		$('#sibilingsTable tbody tr').dblclick(function(e){
			alert( "Handler for .dblclick() called." );
		});
		
		oTable = $('#sibilingsTable').dataTable({
			"bFilter":false,
			"bPaginate":false,
			"bSort":false
		});
		
		frequentDiagnosticTable = $('#fdTable').dataTable( {
			"bSort":false,
			"sAjaxSource":"./frequentDiagnostics",
	        "aoColumns":[
	                     {"mDataProp":"idCIE10"
	                    	 ,"bVisible":false},   
	                     {"mDataProp":"diagnostic"}
	                     ]
	    } );
		
		diagnosticsTable = $('#diagnosticsTable').dataTable( {
			"bSort":false,
			"sAjaxSource":"./diagnostics",
	        "aoColumns":[
	                     {"mDataProp":"idCIE10"
	                    	 ,"bVisible":false},   
	                     {"mDataProp":"diagnostic"}
	                     ]
	    } );
		
		$('#fdTable tbody').on( 'click', 'tr', function (e) {
			if ( $(this).hasClass('row_selected') ) {
	            $(this).removeClass('row_selected');
	        }
	        else {
	            frequentDiagnosticTable.$('tr.row_selected').removeClass('row_selected');
	            $(this).addClass('row_selected');
	            rowSelectedData = frequentDiagnosticTable.fnGetData( this );
	            alert(rowSelectedData["idCIE10"]);
	        }
		} );
		
		$('#diagnosticsTable tbody').on( 'click', 'tr', function (e) {
			if ( $(this).hasClass('row_selected') ) {
            	$(this).removeClass('row_selected');
        	}
        	else {
        		diagnosticsTable.$('tr.row_selected').removeClass('row_selected');
            	$(this).addClass('row_selected');
            	rowSelectedData = frequentDiagnosticTable.fnGetData( this );
            	if(!treatmentsTable){
            		initializeTreatmentsTable(rowSelectedData["idCIE10"]);
            	}else{
            		treatmensTable.fnReloadAjax();
            	}
        	}
		});
	});
	
	function ajaxCall(ID){
		$.ajax({
				url:$('#'+ID).attr("action"),
				data: $('#'+ID).serializeObject(),
				type:"POST",
		        success:function(response){
		        	$('#ajaxMessage').html(response);
		        }
			});	
	}
	
	function fnGetSelectedRow(oTableLocal)
	{
		return alert(oTableLocal.$('tr.row_selected'));
	}
	
	//This function converts a form in a json acceptedString
	$.fn.serializeObject = function()
	{
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name] !== undefined) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
	
	function initializeTreatmentsTable(id){
		treatmentsTable = $('#treatmentsTable').dataTable( {
			"bSort":false,
			"sAjaxSource":"./diagnosticTreatment",
	        "aoColumns":[
	                     {"mDataProp":"idTreatment"
	                    	 ,"bVisible":false},   
	                     {"mDataProp":"treatment"}
	                     ],
	        "fnServerParams": function ( aoData ) {
	                         aoData.push( {name:"diagnosticId",value:id} );
	        				}             
	    });
	}
</script>