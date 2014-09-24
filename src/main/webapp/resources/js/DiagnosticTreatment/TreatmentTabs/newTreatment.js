/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeAsociatedDiagnosticTable();
    initializeDrugAsociatedDrugTable();
    initializeNewTreatmentForm();
    initializeDrugTable();
});

function initializeDiagnosticTable(){
    $("#tblDiagnostic").DataTable({
        "bSort":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar",
            "info": "Mostrando pagina _PAGE_ de _PAGES_",
            "paginate": {
                "previous": "Anterior",
                "next": "Siguiente"
            },
            "lengthMenu": "Mostrar _MENU_ resultados"
        },
        "ajax":{
            "url":"/demo/diagnostictreatment/getCieByUser"
        },
        "columns":[
            {"data":"cieCode"},
            {"data":"diagnostic"}
        ],
        "initComplete":function(settings,json){
            var table = $('#tblDiagnostic').DataTable();
            
            $('#tblDiagnostic tbody').on( 'click', 'tr', function (e) {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }
    });
}

function initializeAsociatedDiagnosticTable(){
    $("#tblAsociatedDiagnostic").DataTable({
        "bSort":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "columns":[
            {"data":"cieCode"},
            {"data":"diagnostic"}
        ],
        "initComplete":function(settings,json){
            var table = $('#tblAsociatedDiagnostic').DataTable();
            
            $('#tblAsociatedDiagnostic tbody').on( 'click', 'tr', function (e) {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }
    });
}

function initializeDrugTable(){
    $("#tblDrug").DataTable({
        "ordering":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar",
            "info": "Mostrando pagina _PAGE_ de _PAGES_",
            "paginate": {
                "previous": "Anterior",
                "next": "Siguiente"
            },
            "lengthMenu": "Mostrar _MENU_ resultados"
        },
        "ajax":"/demo/diagnostictreatment/getDrugs",
        "columns":[
            {"render":function(data,row,full){
                    return ("-"+full["idDrug"]+"-");
            },"visible":false},
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"}
        ],
        "initComplete":function(settings,json){

            $('#tblDrug tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblDrug').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
                
            });         
        }        
    });
}

function initializeDrugAsociatedDrugTable(){
    $("#tblAsociatedDrug").DataTable({
        "ordering":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "columns":[
            {"render":function(data,row,full){
                    return ("-"+full["idDrug"]+"-");
            },"visible":false},
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"}
        ],
        "initComplete":function(settings,json){

            $('#tblAsociatedDrug tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblAsociatedDrug').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
                
            });         
        }
    });
}

function initializeNewTreatmentForm(){
    $('#formNewTreatment').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            treatment: {
                validators: {
                    notEmpty: {
                        message: 'El nombre del tratamiento no puede estar vacio'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveNewTreatment();
    });
}

function saveNewTreatment(){
    var data = [];
    data.push({name:"treatment",value:$("#inputNewTreatmentTreatment").val()});
    data.push({name:"directions",value:$("#inputNewTreatmentDirections").val()});
    data.push({name:"active",value:$('#inputNewTreatmentActive').is(':checked')});
    //Collect Diagnostic data
    var cont = 0;
    $("#tblAsociatedDiagnostic").DataTable().rows().data().each(function(value,index){
        data.push({name:"diagnostic"+cont,value:value["idCIE10"]});
        cont++;
    });
    data.push({name:"diagnosticCont",value:cont});
    
    cont = 0;
    $("#tblAsociatedDrug").DataTable().rows().data().each(function(value,index){
        data.push({name:"drug"+cont,value:value["idDrug"]});
        cont++;
    });
    data.push({name:"drugCont",value:cont});
    
    $.ajax({
        url:"/demo/diagnostictreatment/saveNewTreatment",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado el tratamiento correctamente");
            $("#tblTreatment").DataTable().ajax.reload();
            $("#tblAsociatedDiagnostic").DataTable().clear().draw();
            $("#tblAsociatedDrug").DataTable().clear().draw();
            $("#formNewTreatment").data('bootstrapValidator').resetForm();
            clearFormInputTextFields("formNewTreatment");
            $("#inputNewTreatmentDirections").val();
            $("#tblDiagnostic").DataTable().ajax.reload();
            $("#tblDrug").DataTable().ajax.reload();
            $('#treatmentTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function atachTreatmentDiagnostic(){
    var data = $("#tblDiagnostic").DataTable().row(".selected").data();

    if(checkNotUndefined(data)){
        $("#tblAsociatedDiagnostic").DataTable().row.add(data).draw();
        $("#tblDiagnostic").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un diagnostico");
    }
}

function detachTreatmentDiagnostic(){
    var data = $("#tblAsociatedDiagnostic").DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $("#tblDiagnostic").DataTable().row.add(data).draw();
        $("#tblAsociatedDiagnostic").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un diagnostico");
    }
}

function atachTreatmentDrug(){
    var data = $("#tblDrug").DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $("#tblAsociatedDrug").DataTable().row.add(data).draw();
        $("#tblDrug").DataTable().row('.selected').remove().draw();
        //checkIncompatibilities();
    }else{
        displayWarningAlert("No se ha seleccionado un medicamento");
    }
}

function detachTreatmentDrug(){
    var data = $("#tblAsociatedDrug").DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $("#tblDrug").DataTable().row.add(data).draw();
        $("#tblAsociatedDrug").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un medicamento");
    }
}

function checkIncompatibilities(incompatibilities){
    var rows = [];
    var table = $("#tblDrug").DataTable();
    $("#tblAsociatedDrug").DataTable().rows().data().each(function ( value, index ) {
        $.ajax({
            url:"/demo/diagnostictreatment/getDrugIncompatibility",
            data:{idDrug:value["idDrug"]},
            success:function(response,textStatus,jqXHR){
                $.each(response,function(index,value){     
                    table.rows().data().each(function( val, idx){
                        if(val["idDrug"] === value["idDrug"]){
                            rows.push(idx);
                        }
                    });
                    
                });
            },
            error:function(response){
                displayDangerAlert("Ha ocurrido un error: "+response);
            }
        });
    });
    $.each(rows,function(index,value){
        $(table.row(value)).addClass("vpExpired");
                //.node().to$().css({"background-color":"#FF6961"});
    });
}