/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initializeNewDrugCommercialNameTable(){
    $("#tblNewDrugCommercialName").DataTable({
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
            var table = $('#tblNewDrugCommercialName').DataTable();
            
            $('#tblNewDrugCommercialName tbody').on( 'click', 'tr', function (e) {
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

function loadNewModifyCommercialNameForms(){
    $("#formNewCommercialName").bootstrapValidator({
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
        addCommercialName();
    });
    
    $("#formModifyCommercialName").bootstrapValidator({
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
        modifyCommercialName();
    });
}



function addCommercialName(){
    var name = $("#inputNewCommercialName").val();
    $('#tblNewDrugCommercialName').DataTable().row.add({"commercialName":name}).draw();
    clearFormInputTextFields("formNewCommercialName");
    $('#modalNewCommercialName').modal('hide');
}

function removeCommercialName(){
    $('#tblNewDrugCommercialName').DataTable().row('.selected').remove().draw();
}

function loadModifyDrugCN(){
    var row = $('#tblNewDrugCommercialName').DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $('#modalModifyCommercialName').modal('show');
        $("#inputModifyCommercialName").val(row.commercialName);
        
    }else{
        displayWarningAlert("No ha seleccionado un Nombre comercial");
    }
}

function modifyCommercialName(){
    $('#tblNewDrugCommercialName').DataTable().row('.selected').data({"commercialName":$("#inputModifyCommercialName").val()}).draw();
    $('#modalModifyCommercialName').modal('hide');
}


