/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeIncompatibilityDrugListTable();
    initializeIncompatibilityCommercialNamesTable();
    initializeIncompatibilityRiskTable();
    intializeIncompatibleDrugsTable();
});

function initializeIncompatibilityDrugListTable(){
    $("#tblIncompatibilityDrugList").DataTable({
        "ordering":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/drug/getDrugByUser",
        "columns":[
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"}
        ],
        "initComplete":function(settings,json){
            $('#tblIncompatibilityDrugList tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblIncompatibilityDrugList').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $('#tblIncompatibilityCommercialName').DataTable().column(1).search('',false,false).draw();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    var drugId = table.row(this).data()["idDrug"];
                    $('#tblIncompatibilityCommercialName').DataTable().column(1).search(drugId,false,false).draw();

                }
            });
        },
        "createdRow": function( row, data, dataIndex ) {
            if(data.active !== 1){
                $(row).css({"background-color":"#FDFD96"});
            }
        }
    });
}

function initializeIncompatibilityRiskTable(){
    $("#tblIncompatibilityRisk").DataTable({
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
            {"data":"drug"},
            {"data":"risk"}
        ],
        "initComplete":function(settings,json){
            $('#tblIncompatibilityRisk tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblIncompatibilityRisk').DataTable();
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

function initializeIncompatibilityCommercialNamesTable(){
    $("#tblIncompatibilityCommercialName").DataTable({
        "ordering":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/drug/getDrugsCommercialNames",
        "columns":[
            {"data":"commercialName"},
            {"data":"drugId.idDrug","visible":false}
        ],
        "initComplete":function(settings,json){

            $('#tblIncompatibilityCommercialName tbody').on( 'click', 'tr', function (e) {
                var required = $('#tblIncompatibilityDrugList').DataTable().row('.selected').data();
                if(typeof  required === 'undefined'){
                    displayWarningAlert("Debe seleccionar por lo menos un medicamento primero");
                }else{
                    var table = $('#tblIncompatibilityCommercialName').DataTable();
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                    }else{
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                }
            });
        }
    });
}

function intializeIncompatibleDrugsTable(){
    $("#tblIncompatibles").DataTable({
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
            {"data":"commercialName"}
        ],
        "initComplete":function(settings,json){
            $('#tblIncompatibles tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblIncompatibles').DataTable();
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

function addIncompatibility(){
    var row = $("#tblIncompatibilityCommercialName").DataTable().row('.selected');
    if(checkNotUndefined(row)){
        $("#tblIncompatibles").DataTable().row.add(row.data()).draw();
        $("#tblIncompatibilityCommercialName").DataTable().row(row.node()).remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un nombre comercial");
    }
    
}

function removeIncompatibility(){
    var row = $("#tblIncompatibles").DataTable().row('.selected');
    
    if(checkNotUndefined(row)){
        $("#tblIncompatibilityCommercialName").DataTable().row.add(row.data()).draw();
        $("#tblIncompatibles").DataTable().row(row.node()).remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un nombre comercial");
    }
    
}

function loadNewRiskModal(){
    if(checkNotUndefined($("#tblIncompatibilityDrugList").DataTable().row('.selected').data())){
        $("#formNewRisk").bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                risk: {
                    validators: {
                        notEmpty: {
                            message: 'El riesgo no puede estar vacio'
                        }
                    }
                }
            },
            submitButtons: 'button[type="submit"]'
        }).on('success.form.bv', function(e) {
            e.preventDefault();
            addRisk();
        });
        
        clearFormInputTextFields("formNewRisk");
        $('#modalNewRisk').modal('show');
    }else{
        displayWarningAlert("No se ha seleccionado un medicamento"); 
    }
    
}
function addRisk(){
    var data=$("#tblIncompatibilityDrugList").DataTable().row('.selected').data();
    data["risk"] = $("#inputNewRisk").val();
    
    $("#tblIncompatibilityRisk").DataTable().row.add(data).draw();
    
    $("#formNewRisk").data('bootstrapValidator').destroy();
    
    $('#modalNewRisk').modal('hide');
}