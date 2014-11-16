/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    loadModifyNewModifyDrugCNForm();
    
    $('#modalModifyNewCommercialName').on('show.bs.modal', function (e) {
        $('#formModifyNewCommercialName').bootstrapValidator('resetForm', true);
    });
});

function initializeModifyDrugCommercialNameTable( drug ){
    var data = [{name:"drugId",value:drug}];
    
    $("#tblModifyDrugCommercialName").DataTable({
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
            "url":contextPath+"/drug/getDrugCommercialNames",
            "data":function(){
                return data;
            }
        },
        "columns":[
            {"data":"commercialName"}
        ],
        "initComplete":function(settings,json){
            
            $('#tblModifyDrugCommercialName tbody').on( 'click', 'tr', function (e) {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    $('#tblModifyDrugCommercialName').DataTable().$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }
    });
}

function loadModifyNewModifyDrugCNForm(){
    
    $("#formModifyNewCommercialName").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            commercialName: {
                validators: {
                    notEmpty: {
                        message: 'El nombre puede estar vacio'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        modifyAddCommercialName();
    });
    
    $("#formModifyModifyCommercialName").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            commercialName: {
                validators: {
                    notEmpty: {
                        message: 'El nombre puede estar vacio'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        modifyModifyCommercialName();
    });
}

function modifyAddCommercialName(){
    var name = $("#inputModifyNewCommercialName").val();
    $('#tblModifyDrugCommercialName').DataTable().row.add({"commercialName":name}).draw();
    clearFormInputTextFields("formModifyNewCommercialName");
    $('#modalModifyNewCommercialName').modal('hide');
}

function loadModifyModifyDrugCN(){
    var row = $('#tblModifyDrugCommercialName').DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $('#modalModifyModifyCommercialName').modal('show');
        $("#inputModifyModifyCommercialName").val(row.commercialName);
        
    }else{
        displayWarningAlert("No ha seleccionado un Nombre comercial");
    }
}

function modifyRemoveCommercialName(){
    $('#tblModifyDrugCommercialName').DataTable().row('.selected').remove().draw();
}

function modifyModifyCommercialName(){
    $('#tblModifyDrugCommercialName').DataTable().row('.selected').data({"commercialName":$("#inputModifyModifyCommercialName").val()}).draw();
    $('#modalModifyModifyCommercialName').modal('hide');
}
