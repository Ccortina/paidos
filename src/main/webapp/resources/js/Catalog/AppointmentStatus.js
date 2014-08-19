/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeMainTable();
    initializeNewItemForm();
    initializeModifyItemForm();
});

function newItem(){
    $('#mainTabMenu a[href="#tabNew"]').tab('show');
}

function additionalInfo(){
    if(checkNotUndefined($("#tblMain").DataTable().row('.selected').data())){
        if (  $.fn.DataTable.isDataTable( "#tblAdditionalInfo" ) ) {
          $('#tblAdditionalInfo').DataTable().ajax.reload();
        }else{
            initializeAdditionalInfoTable();
        }
        $('#mainTabMenu a[href="#tabAdditionalInfo"]').tab('show');
    }else{
        displayWarningAlert("No se ha seleccionado un Estatus");
    }
}

function initializeMainTable(){
    $("#tblMain").DataTable({
        "ordering":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/catalogs/getAppointmentStatus",
        "columns":[
            {"data":"status"},
            {"render":function(data,type,row){ 
                if(row['active'] === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }}
        ],
        "initComplete":function(settings,json){
            $('#tblMain tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblMain').DataTable();
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
            "url":"/demo/catalogs/getAppointmentStatusRelatedInfo",
            "data":function(){
                return ({id:$("#tblMain").DataTable().row('.selected').data()["idAppointmentStatus"]});
            }
        },
        "columns":[
            {"data":"date"},
            {"data":"startTime"},
            {"data":"idPatient.fatherLastName"},
            {"data":"idPatient.motherLastName"},
            {"data":"idPatient.firstName"}
        ]
    });
}

function initializeNewItemForm(){
        
    $("#formNewItem").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            itemName: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveNewItem();
    });
}

function initializeModifyItemForm(){   
    $("#formModifyItem").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            itemName: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveModifyItem();
    });
}

function cancel(){
    $('#mainTabMenu a[href="#tabMain"]').tab('show');
}

function saveNewItem(){
    var data = [];
    data.push({name:"active",value:$("#inputNewItemActive").prop('checked')});
    data.push({name:"itemName",value:$("#inputNewItem").val()});
    
    $.ajax({
        url:"/demo/catalogs/saveNewAppointmentStatus",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado correctamente");
            $("#tblMain").DataTable().ajax.reload();
            $('#mainTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function modifyItem(){
    var row = $("#tblMain").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#inputModifyItem").val(row["status"]);
        if(row["active"] == 1 ){
            $("#inputModifyItemActive").prop('checked',true);
        }else{
            $("#inputModifyItemActive").prop('checked',false);
        }
        $("#inputIdItem").val(row["idAppointmentStatus"]);
        
        $('#mainTabMenu a[href="#tabModify"]').tab('show');
    }else{
        displayWarningAlert("No ha seleccionado un tipo de nacimiento");
    }
}

function saveModifyItem(){
    var data = [];
    data.push({name:"active",value:$("#inputModifyItemActive").prop('checked')});
    data.push({name:"itemName",value:$("#inputModifyItem").val()});
    data.push({name:"idItem",value:$("#inputIdItem").val()});
    
    $.ajax({
        url:"/demo/catalogs/saveModifyAppointmentStatus",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha modificado correctamente");
            $("#tblMain").DataTable().ajax.reload();
            $('#mainTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}







