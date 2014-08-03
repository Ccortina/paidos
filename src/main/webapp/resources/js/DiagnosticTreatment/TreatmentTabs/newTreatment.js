/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeDiagnosticTable();
    initializeAsociatedDiagnosticTable();
    initializeDrugTable();
    initializeDrugAsociatedDrugTable();
});

function initializeDiagnosticTable(){
    $("#tblDiagnostic").DataTable({
        "bSort":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "deferRender": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
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
        "paging": false,
        "info":false,
        "deferRender": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/diagnostictreatment/getDrugsByUser",
        "columns":[
            {"data":"idDrug","visible":false},
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
            {"data":"idDrug","visible":false},
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

function atachTreatmentDiagnostic(){
    var data = $("#tblDiagnostic").DataTable().row('.selected').data();
    
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