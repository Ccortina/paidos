/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeDrugPresentationTable();
    initializeNewDPForm();
    initializeModifyDPForm();
});

function newDrugP(){
    $('#drugPresentationTabMenu a[href="#tabNew"]').tab('show');
}

function additionalInfo(){
    if(checkNotUndefined($("#tblDrugP").DataTable().row('.selected').data())){
        if (  $.fn.DataTable.isDataTable( "#tblAdditionalInfo" ) ) {
          $('#tblAdditionalInfo').DataTable().ajax.reload();
        }else{
            initializeAdditionalInfoTable();
        }
        $('#drugPresentationTabMenu a[href="#tabAdditionalInfo"]').tab('show');
    }else{
        displayWarningAlert("No se ha seleccionado una presentacion");
    }
}

function initializeDrugPresentationTable(){
    $("#tblDrugP").DataTable({
        "ordering":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/drug/getDrugPresentation",
        "columns":[
            {"data":"presentation"},
            {"render":function(data,type,row){ 
                if(row['active'] === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }}
        ],
        "initComplete":function(settings,json){
            $('#tblDrugP tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblDrugP').DataTable();
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
            "url":"/demo/drug/getDrugPresentationRelatedInfo",
            "data":function(){
                return ({dpId:$("#tblDrugP").DataTable().row('.selected').data()["drugPresentationId"]});
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

function initializeNewDPForm(){
        
    $("#formNewDP").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            presentation: {
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
        saveNewDP();
    });
}

function initializeModifyDPForm(){   
    $("#formModifyDP").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            presentation: {
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
        saveModifyDP();
    });
}

function cancel(){
    $('#drugPresentationTabMenu a[href="#tabMain"]').tab('show');
}

function saveNewDP(){
    var data = [];
    data.push({name:"active",value:$("#inputNewDPActive").prop('checked')});
    data.push({name:"presentation",value:$("#inputNewDP").val()});
    
    $.ajax({
        url:"/demo/drug/saveNewDrugPresentation",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado la presentacion correctamente");
            $("#tblDrugP").DataTable().ajax.reload();
            $('#drugPresentationTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function modifyDrugP(){
    var row = $("#tblDrugP").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#inputModifyDP").val(row["presentation"]);
        if(row["active"] == "1" ){
            $("#inputModifyDPActive").prop('checked',true);
        }else{
            $("#inputModifyDPActive").prop('checked',false);
        }
        $("#inputIdPresentation").val(row["drugPresentationId"]);
        
        $('#drugPresentationTabMenu a[href="#tabModify"]').tab('show');
    }else{
        displayWarningAlert("No ha seleccionado una presentacion para modificar");
    }
}

function saveModifyDP(){
    var data = [];
    data.push({name:"active",value:$("#inputModifyDPActive").prop('checked')});
    data.push({name:"presentation",value:$("#inputModifyDP").val()});
    data.push({name:"idPresentation",value:$("#inputIdPresentation").val()});
    
    $.ajax({
        url:"/demo/drug/saveModifyDrugPresentation",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha modificado la presentacion correctamente");
            $("#tblDrugP").DataTable().ajax.reload();
            $('#drugPresentationTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

