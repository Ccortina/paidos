/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeModifyNewRiskForm();
});

function initializeModifyIncompatibilityDrugListTable( drug ){
    data = [{name:"drugId",value:drug}];
    
    $("#tblModifyIncompatibilityDrugList").DataTable({
        "ordering":false,
        "scrollY": "200px",
        "destroy":true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":contextPath+"/drug/getAvaibleDrugsforDrug",
            "data":function(){
                return data;
            }},
        "columns":[
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"}
        ],
        "initComplete":function(settings,json){
            $('#tblModifyIncompatibilityDrugList tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblModifyIncompatibilityDrugList').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $('#tblModifyIncompatibilityCommercialName').DataTable().column(1).search('',false,false).draw();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    var drugId = table.row(this).data()["idDrug"];
                    $('#tblModifyIncompatibilityCommercialName').DataTable().column(1).search(drugId,false,false).draw();

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

function initializeModifyIncompatibilityCommercialNamesTable( drug ){
    data = [{name:"drugId",value:drug}];
    
    $("#tblModifyIncompatibilityCommercialName").DataTable({
        "ordering":false,
        "scrollY": "150px",
        "destroy":true,
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":contextPath+"/drug/getAvaibleDrugsCommercialNames",
            "data":function(){
                return data;
            }},
        "columns":[
            {"data":"commercialName"},
            {"data":"drugId.idDrug","visible":false}
        ],
        "initComplete":function(settings,json){

            $('#tblModifyIncompatibilityCommercialName tbody').on( 'click', 'tr', function (e) {
                //To slect a commercial name , a drug must have been selected
                var required = $('#tblModifyIncompatibilityDrugList').DataTable().row('.selected').data();
                if(typeof  required === 'undefined'){
                    displayWarningAlert("Debe seleccionar por lo menos un medicamento primero");
                }else{
                    var table = $('#tblModifyIncompatibilityCommercialName').DataTable();
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

function intializeModifyIncompatibleDrugsTable( drug ){
    data = [{name:"drugId",value:drug}];
    
    $("#tblModifyIncompatibles").DataTable({
        "ordering":false,
        "scrollY": "200px",
        "destroy":true,
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":contextPath+"/drug/getIncompatibleDrugsCommercialNames",
            "data":function(){
                return data;
            }},
        "columns":[
            {"data":"commercialName"}
        ],
        "initComplete":function(settings,json){
            $('#tblModifyIncompatibles tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblModifyIncompatibles').DataTable();
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

function initializeModifyIncompatibilityRiskTable( drug ){
    data = [{name:"drugId",value:drug}];
    
    $("#tblModifyIncompatibilityRisk").DataTable({
        "ordering":false,
        "destroy":true,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":contextPath+"/drug/getDrugRisks",
            "data":function(){
                return data;
            }},
        "columns":[
            {"data":"drug1.drug"},
            {"data":"risk"}
        ],
        "initComplete":function(settings,json){
            $('#tblModifyIncompatibilityRisk tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblModifyIncompatibilityRisk').DataTable();
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

function modifyAddIncompatibility(){
    var row = $("#tblModifyIncompatibilityCommercialName").DataTable().row('.selected');
    
    if(checkNotUndefined(row.data())){
        $("#tblModifyIncompatibles").DataTable().row.add(row.data()).draw();
        $("#tblModifyIncompatibilityCommercialName").DataTable().row(row.node()).remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un nombre comercial");
    }
    
}

function modifyRemoveIncompatibility(){
    var row = $("#tblModifyIncompatibles").DataTable().row('.selected');
    
    if(checkNotUndefined(row)){
        $("#tblModifyIncompatibilityCommercialName").DataTable().row.add(row.data()).draw();
        $("#tblModifyIncompatibles").DataTable().row(row.node()).remove().draw();
    }else{
        displayWarningAlert("No se ha seleccionado un nombre comercial");
    }
    
}

function modifyLoadNewRiskModal(){
    if(checkNotUndefined($("#tblModifyIncompatibilityDrugList").DataTable().row('.selected').data())){
        $('#modalModifyNewRisk').modal('show');
    }else{
        displayWarningAlert("No se ha seleccionado un medicamento"); 
    }    
}

function initializeModifyNewRiskForm(){
    $("#formModifyNewRisk").bootstrapValidator({
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
        modifyAddRisk();
    });
    
    $("#formModifyModifyRisk").bootstrapValidator({
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
        modifyModifyRisk();
    });
}

function modifyAddRisk(){
    var data = [];
    data.drug1 = $("#tblModifyIncompatibilityDrugList").DataTable().row('.selected').data();
    data.risk = $("#inputModifyNewRisk").val();
    
    $("#tblModifyIncompatibilityRisk").DataTable().row.add(data).draw();
    $("#tblModifyIncompatibilityDrugList").DataTable().row('.selected').remove().draw();    
    $('#modalModifyNewRisk').modal('hide');
}

function modifyRemoveRisk(){
    var data = $("#tblModifyIncompatibilityRisk").DataTable().row('.selected').data();
    $("#tblModifyIncompatibilityRisk").DataTable().row('.selected').remove().draw();
    $("#tblModifyIncompatibilityDrugList").DataTable().row.add(data.drug1).draw();
}

function modifyLoadModifyRiskModal(){
    var data = $("#tblModifyIncompatibilityRisk").DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $('#modalModifyModifyRisk').modal('show');
    }else{
        displayWarningAlert("No se ha seleccionado un riesgo"); 
    }  
}

function modifyModifyRisk(){
    var originalData = $("#tblModifyIncompatibilityRisk").DataTable().row('.selected').data();
    if(checkNotUndefined(originalData)){
        originalData["risk"]=$("#inputModifyModifyRisk").val();
        $('#tblModifyIncompatibilityRisk').DataTable().row('.selected').data(originalData).draw();
        
        $("#modalModifyModifyRisk").modal('hide'); 
    }else{
        displayWarningAlert("No se ha seleccionado un riesgo"); 
    }
}

