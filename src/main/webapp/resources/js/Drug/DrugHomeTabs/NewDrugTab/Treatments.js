/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeTreatmentsTable();
    initializeTreatmentsAssociatedTable();
});

function initializeTreatmentsTable(){
    $("#tblTreatments").DataTable({
        "bSort":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/drug/getTreatmentsByUser",
        "columns":[
            {"data":"treatment"}
        ],
        "initComplete":function(settings,json){
            $('#tblTreatments tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblTreatments').DataTable();
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

function initializeTreatmentsAssociatedTable(){
    $("#tblTreatmentsAssociated").DataTable({
        "bSort":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "columns":[
            {"data":"treatment"}
        ],
        "initComplete":function(settings,json){
            $('#tblTreatmentsAssociated tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblTreatmentsAssociated').DataTable();
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

function addTreatment(){
    var row = $("#tblTreatments").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#tblTreatmentsAssociated").DataTable().row.add(row).draw();
        $("#tblTreatments").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un tratamiento");
    }
}

function removeTreatment(){
    var row = $("#tblTreatmentsAssociated").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#tblTreatments").DataTable().row.add(row).draw();
        $("#tblTreatmentsAssociated").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un tratamiento");
    }
}