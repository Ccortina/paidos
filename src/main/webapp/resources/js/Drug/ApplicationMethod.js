/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeDrugAMTable();
    initializeNewAMForm();
    initializeModifyAMForm();
});

function newAM(){
    $('#drugAMTabMenu a[href="#tabNew"]').tab('show');
}

function additionalInfo(){
    if(checkNotUndefined($("#tblAM").DataTable().row('.selected').data())){
        if (  $.fn.DataTable.isDataTable( "#tblAdditionalInfo" ) ) {
          $('#tblAdditionalInfo').DataTable().ajax.reload();
        }else{
            initializeAdditionalInfoTable();
        }
        $('#drugAMTabMenu a[href="#tabAdditionalInfo"]').tab('show');
    }else{
        displayWarningAlert("No se ha seleccionado una metodo");
    }
}

function initializeDrugAMTable(){
    $("#tblAM").DataTable({
        "ordering":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/drug/getDrugApplicationMethod",
        "columns":[
            {"data":"applicationMethod"},
            {"render":function(data,type,row){ 
                if(parseInt(row['active']) === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }}
        ],
        "initComplete":function(settings,json){
            $('#tblAM tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblAM').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        },
        "createdRow": function( row, data, dataIndex ) {
            if(parseInt(data.active) !== 1){
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
            "url":"/demo/drug/getDrugApplicationMethodRelatedInfo",
            "data":function(){
                return ({dpId:$("#tblAM").DataTable().row('.selected').data()["idApplicationMethod"]});
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

function initializeNewAMForm(){
        
    $("#formNewAM").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            applicationMethod: {
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
        saveNewAM();
    });
}

function initializeModifyAMForm(){   
    $("#formModifyAM").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            applicationMethod: {
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
        saveModifyAM();
    });
}

function cancel(){
    $('#drugAMTabMenu a[href="#tabMain"]').tab('show');
}

function saveNewAM(){
    var data = [];
    data.push({name:"active",value:$("#inputNewAMActive").prop('checked')});
    data.push({name:"applicationMethod",value:$("#inputNewAM").val()});
    
    $.ajax({
        url:"/demo/drug/saveNewDrugApplicationMethod",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado la presentacion correctamente");
            $("#tblAM").DataTable().ajax.reload();
            $('#drugAMTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function modifyAM(){
    var row = $("#tblAM").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#inputModifyAM").val(row["applicationMethod"]);
        if(row["active"] == "1" ){
            $("#inputModifyAMActive").prop('checked',true);
        }else{
            $("#inputModifyAMActive").prop('checked',false);
        }
        $("#inputIdAM").val(row["idApplicationMethod"]);
        
        $('#drugAMTabMenu a[href="#tabModify"]').tab('show');
    }else{
        displayWarningAlert("No ha seleccionado una presentacion para modificar");
    }
}

function saveModifyAM(){
    var data = [];
    data.push({name:"active",value:$("#inputModifyAMActive").prop('checked')});
    data.push({name:"applicationMethod",value:$("#inputModifyAM").val()});
    data.push({name:"idApplicationMethod",value:$("#inputIdAM").val()});
    
    $.ajax({
        url:"/demo/drug/saveModifyDrugApplicationMethod",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha modificado la presentacion correctamente");
            $("#tblAM").DataTable().ajax.reload();
            $('#drugAMTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}



