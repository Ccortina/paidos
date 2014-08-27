/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeMainTable();
    initializeNewItemForm();
    initializeModifyItemForm();
    initializeVaccineList();
    initializeVaccineList1();
    initializeEquivalentVaccineList();
    initializeEquivalentVaccineList1();
    $('#mainTabMenu a[href="#tabRelatedInfo"]').on('show.bs.tab', function (e) {
        if (  $.fn.DataTable.isDataTable( "#tblAdditionalInfo" ) ) {
          $('#tblAdditionalInfo').DataTable().columns.adjust().draw();
        }
    });
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
        $('#mainTabMenu a[href="#tabRelatedInfo"]').tab('show');
    }else{
        displayWarningAlert("No se ha seleccionado una inmunizacion");
    }
}

function additionalInfo2(){
    if(checkNotUndefined($("#tblMain").DataTable().row('.selected').data())){
        if (  $.fn.DataTable.isDataTable( "#tblPatientWOVaccine" ) ) {
          $('#tblPatientWOVaccine').DataTable().ajax.reload();
        }else{
            initializePatientsWithoutVaccineTable();
        }
        $('#mainTabMenu a[href="#tabPatientWOVaccine"]').tab('show');
    }else{
        displayWarningAlert("No se ha seleccionado una inmunizacion");
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
        "ajax":"/demo/catalogs/getAllVacine",
        "columns":[
            {"data":"vaccine"},
            {"data":"idVaccineType.type"},
            {"data":"yearApply"},
            {"data":"monthApply"},
            {"data":"dayApply"},
            {"render":function(data,type,row){ 
                if(parseInt( row[ 'active' ] ) === 1){
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
            "url":"/demo/catalogs/getVaccineRelatedInfo",
            "data":function(){
                return ({idVaccine:$("#tblMain").DataTable().row('.selected').data()["idVaccine"]});
            }
        },
        "columns":[
            {"data":"patient.fatherLastName"},
            {"data":"patient.motherLastName"},
            {"data":"patient.firstName"},
            {"data":"vaccine.vaccine"}
        ]
    });
}

function initializePatientsWithoutVaccineTable(){
        $("#tblPatientWOVaccine").DataTable({
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
            "url":"/demo/catalogs/getPatientWOVaccine",
            "data":function(){
                return ({idVaccine:$("#tblMain").DataTable().row('.selected').data()["idVaccine"]});
            }
        },
        "columns":[
            {"data":"fatherLastName"},
            {"data":"motherLastName"},
            {"data":"firstName"},
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
            },
            itemAppYear: {
                validators: {
                    integer: {
                        message: 'No es un formato valido [0 - 9]'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            itemAppMonth: {
                validators: {
                    integer: {
                        message: 'No es un formato valido [0 - 9]'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            itemAppDay: {
                validators: {
                    integer: {
                        message: 'No es un formato valido [0 - 9]'
                    },
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
            },
            itemAppYear: {
                validators: {
                    integer: {
                        message: 'No es un formato valido [0 - 9]'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            itemAppMonth: {
                validators: {
                    integer: {
                        message: 'No es un formato valido [0 - 9]'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            itemAppDay: {
                validators: {
                    integer: {
                        message: 'No es un formato valido [0 - 9]'
                    },
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
    data.push({name:"itemAppYear",value:$("#inputNewYear").val()});
    data.push({name:"itemAppMonth",value:$("#inputNewMonth").val()});
    data.push({name:"itemAppDay",value:$("#inputNewDay").val()});
    data.push({name:"type",value:$("#inputNewItemType").val()});
    data.push({name:"multiple",value:$("#inputNewItemMultiple").prop('checked')});
    
    var cont = 0;
    $("#tblEquivalentImmunization").DataTable().rows().data().each(function(value, index){
        data.push({name:"eq"+cont,value:value["idVaccine"]});
        cont++;
    });
    data.push({name:"cont",value:cont});
    
    $.ajax({
        url:"/demo/catalogs/saveNewVaccine",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado correctamente");
            $("#tblMain").DataTable().ajax.reload();
            $("#tblAvaibleImmunization").DataTable().ajax.reload();
            $("#tblEquivalentImmunization").DataTable().clear().draw();
            $("#formNewItem").data('bootstrapValidator').resetForm();
            clearFormInputTextFields("formNewItem");
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
        $("#tblAvaibleImmunizationM").DataTable().ajax.reload();
        $("#tblEquivalentImmunizationM").DataTable().clear().draw();
        
        $("#inputModifyItem").val(row["vaccine"]);
        $("#inputModifyYear").val(row["yearApply"]);
        $("#inputModifyMonth").val(row["monthApply"]);
        $("#inputModifyDay").val(row["dayApply"]);
        
        $("#inputModifyItemType option").each(function(){
            if(parseInt( $(this).val() ) === row["idVaccineType"]["idVaccineType"]){
                $(this).attr('selected','selected');
            }
        });
        
        if(parseInt( row["active"] ) === 1 ){
            $("#inputModifyItemActive").prop('checked',true);
        }else{
            $("#inputModifyItemActive").prop('checked',false);
        }
        
        if(parseInt( row["active"] ) === 1 ){
            $("#inputModifyItemMultiple").prop('checked',true);
        }else{
            $("#inputModifyItemMultiple").prop('checked',false);
        }
 
        $("#inputIdItem").val(row["idVaccine"]);
        var table = $("#tblAvaibleImmunizationM").DataTable();
        
        $.each(row["vaccineList"], function(idx, item) {
            var index = table.rows().eq( 0 ).filter( function (rowIdx) {
                return table.cell( rowIdx, 0 ).data() === item["idVaccine"] ? true : false;
            } );
            $("#tblEquivalentImmunizationM").DataTable().row.add(table.row(index[0]).data()).draw();
            $("#tblAvaibleImmunizationM").DataTable().row(table.row(index[0]).node()).remove().draw(); 
        });

        $('#mainTabMenu a[href="#tabModify"]').tab('show');
    }else{
        displayWarningAlert("No ha seleccionado una Actividad");
    }
}

function saveModifyItem(){
    var data = [];
    data.push({name:"active",value:$("#inputModifyItemActive").prop('checked')});
    data.push({name:"itemName",value:$("#inputModifyItem").val()});
    data.push({name:"itemAppYear",value:$("#inputModifyYear").val()});
    data.push({name:"itemAppMonth",value:$("#inputModifyMonth").val()});
    data.push({name:"itemAppDay",value:$("#inputModifyDay").val()});
    data.push({name:"type",value:$("#inputModifyItemType").val()});
    data.push({name:"inputIdItem",value:$("#inputIdItem").val()});
    data.push({name:"multiple",value:$("#inputModifyItemMultiple").prop('checked')});
    
    var cont = 0;
    $("#tblEquivalentImmunizationM").DataTable().rows().data().each(function(value, index){
        data.push({name:"eq"+cont,value:value["idVaccine"]});
        cont++;
    });
    data.push({name:"cont",value:cont});
    
    $.ajax({
        url:"/demo/catalogs/saveModifyVaccine",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha modificado correctamente");
            
            $("#tblMain").DataTable().ajax.reload();
            $("#formModifyItem").data('bootstrapValidator').resetForm();
            clearFormInputTextFields("formModifyItem");
            $('#mainTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function initializeVaccineList(){
    
    $("#tblAvaibleImmunization").DataTable({
        "ordering":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/catalogs/getVaccine",
        "columns":[
            {"data":"vaccine"}
        ],
        "initComplete":function(settings,json){
            $('#tblAvaibleImmunization tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblAvaibleImmunization').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    table.column(0).search('').draw();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }
    });
}

function initializeEquivalentVaccineList(){
    
    $("#tblEquivalentImmunization").DataTable({
        "ordering":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "columns":[
            {"data":"vaccine"}
        ],
        "initComplete":function(settings,json){
            $('#tblEquivalentImmunization tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblEquivalentImmunization').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    table.column(0).search('').draw();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }
    });
}

function addEquivalent(){
    var row = $("#tblAvaibleImmunization").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#tblEquivalentImmunization").DataTable().row.add(row).draw();
        $("#tblAvaibleImmunization").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado una Inmunizacion");
    }
}

function removeEquivalent(){
    var row = $("#tblEquivalentImmunization").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#tblAvaibleImmunization").DataTable().row.add(row).draw();
        $("#tblEquivalentImmunization").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado una Inmunizacion");
    }
}

function initializeVaccineList1(){
    
    $("#tblAvaibleImmunizationM").DataTable({
        "ordering":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/catalogs/getVaccine",
        "columns":[
            {"data":"idVaccine","visible":true},
            {"data":"vaccine"}
        ],
        "initComplete":function(settings,json){
            $('#tblAvaibleImmunizationM tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblAvaibleImmunizationM').DataTable();
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

function initializeEquivalentVaccineList1(){
    
    $("#tblEquivalentImmunizationM").DataTable({
        "ordering":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "columns":[
            {"data":"vaccine"}
        ],
        "initComplete":function(settings,json){
            $('#tblEquivalentImmunizationM tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblEquivalentImmunizationM').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    table.column(0).search('').draw();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }
    });
}

function addEquivalentM(){
    var row = $("#tblAvaibleImmunizationM").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#tblEquivalentImmunizationM").DataTable().row.add(row).draw();
        $("#tblAvaibleImmunizationM").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado una Inmunizacion");
    }
}

function removeEquivalentM(){
    var row = $("#tblEquivalentImmunizationM").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#tblAvaibleImmunizationM").DataTable().row.add(row).draw();
        $("#tblEquivalentImmunizationM").DataTable().row('.selected').remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado una Inmunizacion");
    }
}


