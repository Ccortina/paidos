/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    //initializeDiagnosticTable2();
    //initializeAsociatedDiagnosticTable2();
    //initializeDrugTable2();
    //initializeDrugAsociatedDrugTable2();
    initializeNewTreatmentForm2();
});

function initializeDiagnosticTable2(){
    $("#tblDiagnostic2").DataTable({
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
            "url":"/demo/diagnostictreatment/getAvaibleDiagnosticByTreatment",
            "data":function(){
                    return {idTreatment:$("#tblTreatment").DataTable().row('.selected').data()["idTreatment"]};
                }
        },
        "columns":[
            {"data":"cieCode"},
            {"data":"diagnostic"}
        ],
        "initComplete":function(settings,json){
            var table = $('#tblDiagnostic2').DataTable();
            
            $('#tblDiagnostic2 tbody').on( 'click', 'tr', function (e) {
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

function initializeAsociatedDiagnosticTable2(){
    $("#tblAsociatedDiagnostic2").DataTable({
        "bSort":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{"url":"/demo/diagnostictreatment/getDiagnosticByTreatment",
                "data":function(){
                    return {idTreatment:$("#tblTreatment").DataTable().row('.selected').data()["idTreatment"]};
                }},
        "columns":[
            {"data":"cieCode"},
            {"data":"diagnostic"}
        ],
        "initComplete":function(settings,json){
            var table = $('#tblAsociatedDiagnostic2').DataTable();
            
            $('#tblAsociatedDiagnostic2 tbody').on( 'click', 'tr', function (e) {
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

function initializeDrugTable2(){
    $("#tblDrug2").DataTable({
        "ordering":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "deferRender": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{"url":"/demo/diagnostictreatment/getAvaibleDrugsByTreatment",
                "data":function(){
                    return {idTreatment:$("#tblTreatment").DataTable().row('.selected').data()["idTreatment"]};
                }},
        "columns":[
            {"render":function(data,row,full){
                    return ("-"+full["idDrug"]+"-");
            },"visible":false},
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"}
        ],
        "initComplete":function(settings,json){

            $('#tblDrug2 tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblDrug2').DataTable();
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

function initializeDrugAsociatedDrugTable2(){
    $("#tblAsociatedDrug2").DataTable({
        "ordering":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{"url":"/demo/diagnostictreatment/getDrugsByTreatment",
                "data":function(){
                    return {idTreatment:$("#tblTreatment").DataTable().row('.selected').data()["idTreatment"]};
                }},
        "columns":[
            {"render":function(data,row,full){
                    return ("-"+full["idDrug"]+"-");
            },"visible":false},
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"}
        ],
        "initComplete":function(settings,json){

            $('#tblAsociatedDrug2 tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblAsociatedDrug2').DataTable();
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

function initializeNewTreatmentForm2(){
    $('#formModifyTreatment').bootstrapValidator({
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
        saveModifyTreatment();
    });
}

function saveModifyTreatment(){
    var data = [];
    data.push({name:"treatment",value:$("#inputModifyTreatmentTreatment").val()});
    data.push({name:"directions",value:$("#inputModifyTreatmentDirections").val()});
    data.push({name:"active",value:$('#inputModifyTreatmentActive').is(':checked')});
    data.push({name:"idTreatment",value:$("#tblTreatment").DataTable().row('.selected').data()['idTreatment']});
    //Collect Diagnostic data
    var cont = 0;
    $("#tblAsociatedDiagnostic2").DataTable().rows().data().each(function(value,index){
        data.push({name:"diagnostic"+cont,value:value["idCIE10"]});
        cont++;
    });
    data.push({name:"diagnosticCont",value:cont});
    
    cont = 0;
    $("#tblAsociatedDrug2").DataTable().rows().data().each(function(value,index){
        data.push({name:"drug"+cont,value:value["idDrug"]});
        cont++;
    });
    data.push({name:"drugCont",value:cont});
    
    $.ajax({
        url:"/demo/diagnostictreatment/saveModifyTreatment",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha modificado el tratamiento correctamente");
            $("#tblTreatment").DataTable().ajax.reload();
            $("#tblAsociatedDiagnostic2").DataTable().clear().draw();
            $("#tblAsociatedDrug2").DataTable().clear().draw();
            $("#formModifyTreatment").data('bootstrapValidator').resetForm();
            clearFormInputTextFields("formModifyTreatment");
            $("#inputModifyTreatmentDirections").val();
            $("#tblDiagnostic2").DataTable().ajax.reload();
            $("#tblDrug2").DataTable().ajax.reload();
            $('#treatmentTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function atachTreatmentDiagnostic2(){
    var data = $("#tblDiagnostic2").DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $("#tblAsociatedDiagnostic2").DataTable().row.add(data).draw();
        $("#tblDiagnostic2").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un diagnostico");
    }
}

function detachTreatmentDiagnostic2(){
    var data = $("#tblAsociatedDiagnostic2").DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $("#tblDiagnostic2").DataTable().row.add(data).draw();
        $("#tblAsociatedDiagnostic2").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un diagnostico");
    }
}

function atachTreatmentDrug2(){
    var data = $("#tblDrug2").DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $("#tblAsociatedDrug2").DataTable().row.add(data).draw();
        $("#tblDrug2").DataTable().row('.selected').remove().draw();
        //checkIncompatibilities();
    }else{
        displayWarningAlert("No se ha seleccionado un medicamento");
    }
}

function detachTreatmentDrug2(){
    var data = $("#tblAsociatedDrug2").DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $("#tblDrug2").DataTable().row.add(data).draw();
        $("#tblAsociatedDrug2").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un medicamento");
    }
}

function loadTreatmentData(){

    var selectedRow = $("#tblTreatment").DataTable().row('.selected').data();
    
    if(checkNotUndefined(selectedRow)){
        //Load basic data
        $("#inputModifyTreatmentTreatment").val(selectedRow["treatment"]);
        $("#inputModifyTreatmentDirections").val(selectedRow["directions"]);
        
        //load diagnostic table
        
        if ( ! $.fn.DataTable.isDataTable( '#tblAsociatedDiagnostic2' ) ) {
            initializeAsociatedDiagnosticTable2();
        }else{
            $("#tblAsociatedDiagnostic2").DataTable().clear().draw();
        }
        
        if ( ! $.fn.DataTable.isDataTable( '#tblDiagnostic2' ) ) {
            initializeDiagnosticTable2();
        }else{
            $("#tblDiagnostic2").DataTable().ajax.reload();
        }

        if ( ! $.fn.DataTable.isDataTable( '#tblDrug2' ) ) {
            initializeDrugTable2();
        }else{
            
            $("#tblDrug2").DataTable().ajax.reload();
        }
        if ( ! $.fn.DataTable.isDataTable( '#tblAsociatedDrug2' ) ) {
            initializeDrugAsociatedDrugTable2();
        }else{
            $("#tblAsociatedDrug2").DataTable().ajax.reload();
        }
        initializeNewTreatmentForm2();
    }else{
        displayWarningAlert("No se ha seleccionado un tratamiento");
    }
}