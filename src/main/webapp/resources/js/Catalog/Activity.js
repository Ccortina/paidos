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
        displayWarningAlert("No se ha seleccionado una actividad");
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
        "ajax":"/demo/catalogs/getActivity",
        "columns":[
            {"data":"activity"},
            {"data":"activityCost"},
            {"data":"idActivityType.type"},
            {"render":function(data,type,row){
                if(row['idVaccine'] === null){
                    return ("");
                }else{
                    return (row['idVaccine']["vaccine"]);
                }
            }},
            {"render":function(data,type,row){
                if(row['consultationDefault'] === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }},
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
            "url":"/demo/catalogs/getActivityRelatedInfo",
            "data":function(){
                return ({id:$("#tblMain").DataTable().row('.selected').data()["idActivity"]});
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
            },
            cost: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]+(\.[0-9][0-9]?)?$/i,
                        message: 'Formato invalido'
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
            cost: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]+(\.[0-9][0-9]?)?$/i,
                        message: 'Formato invalido'
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
    data.push({name:"cost",value:$("#inputNewItemCost").val()});
    data.push({name:"type",value:$("#inputNewItemType").val()});
    data.push({name:"include",value:$("#inputNewItemInclude").prop('checked')});
    if(checkNotUndefined($("#tblVaccine1").DataTable().row('.selected').data())){
        data.push({name:"vaccine",value:$("#tblVaccine1").DataTable().row('.selected').data()["idVaccine"]});
    }else{
        data.push({name:"vaccine",value:""});
    }
    
    $.ajax({
        url:"/demo/catalogs/saveNewActivity",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado correctamente");
            $("#tblMain").DataTable().ajax.reload();
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
        $("#inputModifyItem").val(row["activity"]);
        $("#inputModifyItemCost").val(row["activityCost"]);
        $("#inputModifyItemType option").each(function(){
            if($(this).val() == row["idActivityType"]["idActivityType"]){
                $(this).attr('selected','selected');
            }
        });
        
        if(row["active"] == 1 ){
            $("#inputModifyItemActive").prop('checked',true);
        }else{
            $("#inputModifyItemActive").prop('checked',false);
        }
        
        if(row["consultationDefault"] == 1 ){
            $("#inputModifyItemInclude").prop('checked',true);
        }else{
            $("#inputModifyItemInclude").prop('checked',false);
        }
        $("#inputIdItem").val(row["idActivity"]);
        
        var table = $("#tblVaccine").DataTable();
        if(row["idVaccine"] != null){
            table.column(0).search("-"+row["idVaccine"]["idVaccine"]+"-",false,false).draw();
            table.rows().eq( 0 ).each( function (idx) {
            var row2 = table.row( idx ).data();
            if ( row2["idVaccine"] === row["idVaccine"]["idVaccine"] ) {
                $(table.row( idx ).node()).addClass( 'selected' );
            }
        } );
        }

        $('#mainTabMenu a[href="#tabModify"]').tab('show');
    }else{
        displayWarningAlert("No ha seleccionado una Actividad");
    }
}

function saveModifyItem(){
    var data = [];
    data.push({name:"idItem",value:$("#inputIdItem").val()});
    data.push({name:"active",value:$("#inputNewItemActive").prop('checked')});
    data.push({name:"itemName",value:$("#inputNewItem").val()});
    data.push({name:"cost",value:$("#inputNewItemCost").val()});
    data.push({name:"type",value:$("#inputNewItemType").val()});
    data.push({name:"include",value:$("#inputNewItemInclude").prop('checked')});
    if(checkNotUndefined($("#tblMain").DataTable().row('.selected').data())){
        data.push({name:"vaccine",value:$("#tblVaccine").DataTable().row('.selected').data()["idVaccine"]});
    }else{
        data.push({name:"vaccine",value:""});
    }
    
    $.ajax({
        url:"/demo/catalogs/saveModifyActivity",
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
    
    $("#tblVaccine").DataTable({
        "ordering":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/catalogs/getVaccine",
        "columns":[
            {"render":function(data,type,row){
                return ("-"+row['idVaccine']+"-");
            },"visible":false},
            {"data":"vaccine"}
        ],
        "initComplete":function(settings,json){
            $('#tblVaccine tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblVaccine').DataTable();
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

function initializeVaccineList1(){
    
    $("#tblVaccine1").DataTable({
        "ordering":false,
        "scrollY": "200px",
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
            $('#tblVaccine1 tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblVaccine1').DataTable();
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







