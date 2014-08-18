/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeDrugAUTable();
    initializeNewAUForm();
    initializeModifyAUForm();
});

function newAU(){
    $('#drugAUTabMenu a[href="#tabNew"]').tab('show');
}

function additionalInfo(){
    if(checkNotUndefined($("#tblAU").DataTable().row('.selected').data())){
        if (  $.fn.DataTable.isDataTable( "#tblAdditionalInfo" ) ) {
          $('#tblAdditionalInfo').DataTable().ajax.reload();
        }else{
            initializeAdditionalInfoTable();
        }
        $('#drugAUTabMenu a[href="#tabAdditionalInfo"]').tab('show');
    }else{
        displayWarningAlert("No se ha seleccionado una metodo");
    }
}

function initializeDrugAUTable(){
    $("#tblAU").DataTable({
        "ordering":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/drug/getDrugAdministrationUnit",
        "columns":[
            {"data":"administrationUnit"},
            {"render":function(data,type,row){ 
                if(row['active'] === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }}
        ],
        "initComplete":function(settings,json){
            $('#tblAU tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblAU').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
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

function initializeAdditionalInfoTable(){
        $("#tblAdditionalInfo").DataTable({
        "ordering":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":"/demo/drug/getDrugAdministrationUnitRelatedInfo",
            "data":function(){
                return ({auId:$("#tblAU").DataTable().row('.selected').data()["idAdministrationUnit"]});
            }
        },
        "columns":[
            {"data":"drug"},
            {"data":"concentration"},
            {"data":"administrationUnitId.administrationUnit"},
            {"data":"doseCalculationCriteriaId.criteria"}
        ]
    });
}

function initializeNewAUForm(){
        
    $("#formNewAU").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            administrationUnit: {
                validators: {
                    notEmpty: {
                        message: 'La presentacion no puede estar vacia'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveNewAU();
    });
}

function initializeModifyAUForm(){   
    $("#formModifyAU").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            administrationUnit: {
                validators: {
                    notEmpty: {
                        message: 'La presentacion no puede estar vacia'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveModifyAU();
    });
}

function cancel(){
    $('#drugAUTabMenu a[href="#tabMain"]').tab('show');
}

function saveNewAU(){
    var data = [];
    data.push({name:"active",value:$("#inputNewAUActive").prop('checked')});
    data.push({name:"administrationUnit",value:$("#inputNewAU").val()});
    
    $.ajax({
        url:"/demo/drug/saveNewDrugAdministrationUnit",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado la presentacion correctamente");
            $("#tblAU").DataTable().ajax.reload();
            $('#drugAUTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function modifyAU(){
    var row = $("#tblAU").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#inputModifyAU").val(row["administrationUnit"]);
        if(row["active"] == "1" ){
            $("#inputModifyAUActive").prop('checked',true);
        }else{
            $("#inputModifyAUActive").prop('checked',false);
        }
        $("#inputIdAU").val(row["idAdministrationUnit"]);
        
        $('#drugAUTabMenu a[href="#tabModify"]').tab('show');
    }else{
        displayWarningAlert("No ha seleccionado una presentacion para modificar");
    }
}

function saveModifyAU(){
    var data = [];
    data.push({name:"active",value:$("#inputModifyAUActive").prop('checked')});
    data.push({name:"administrationUnit",value:$("#inputModifyAU").val()});
    data.push({name:"idAdministrationUnit",value:$("#inputIdAU").val()});
    
    $.ajax({
        url:"/demo/drug/saveModifyDrugAdministrationUnit",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha modificado la presentacion correctamente");
            $("#tblAU").DataTable().ajax.reload();
            $('#drugAUTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

