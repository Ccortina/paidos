/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeNewDrugDoseTable();
    initializeNewDrugCommercialNameTable();
    initializeNewDrugForm();
    $('#modalNewDose').on('hidden.bs.modal', function (e) {
        $("#formNewDose").data('bootstrapValidator').destroy();
    });
});

function initializeNewDrugDoseTable(){
    $("#tblNewDrugDose").DataTable({
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
            {"data":"criteria"},
            {"data":"dose"}
        ],
        "initComplete":function(settings,json){
            var table = $('#tblNewDrugDose').DataTable();
            
            $('#tblNewDrugDose tbody').on( 'click', 'tr', function (e) {
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

function loadNewDrugDoseModal(){
    
    //Check wich criteria is going to be used
    var criteria = $("#inputNewDrugDoseCalculationCriteria").val();
    
    switch(criteria){
        case "1":
            initializeNewDoseFormWeight();
            break;
        case "2":
            initializeNewDoseFormAge();
            break;
        case"3":
            break;    
    }
    
    clearFormInputTextFields("formNewDose");
}

function initializeNewDoseFormWeight(){
    var table = $("#tblNewDrugDose").DataTable();
    
    //Check if theres already a dose
    if(table.rows().data().length >= 1){
        displayWarningAlert("Solo puede haber una dosis en el peso");
    }else{
        $("#inputNewDoseCriteria").prop( "disabled", true );
        
        $("#formNewDose").bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                dose: {
                    validators: {
                        notEmpty: {
                            message: 'La dosis no puede estar vacia'
                        },
                        regexp: {
                            regexp: /^[0-9]+(\.[0-9][0-9]?)?$/i,
                            message: 'Formato invalido [0-9].[0-9][09]'
                        }
                    }
                }
            },
            submitButtons: 'button[type="submit"]'
        }).on('success.form.bv', function(e) {
            e.preventDefault();
            addDose();
        });
            $('#modalNewDose').modal('show');  
    }
}

function initializeNewDoseFormAge(){
    $("#formNewDose").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            dose: {
                validators: {
                    notEmpty: {
                        message: 'La dosis no puede estar vacia'
                    },
                    regexp: {
                        regexp: /^[0-9]+(\.[0-9][0-9]?)?$/i,
                        message: 'Formato invalido [0-9].[0-9][09]'
                    }
                }
            },
            criteria: {
                validators: {
                    notEmpty: {
                        message: 'No puede estar vacia'
                    },
                    regexp: {
                        regexp: /^[0-9]+(\.[0-9][0-9]?)?$/i,
                        message: 'Formato invalido [0-9].[0-9][09]'
                    }
                }
            }
        },
    submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        addDose();
    });
    $("#inputNewDoseCriteria").prop( "disabled", false );
    $('#modalNewDose').modal('show');  
}


function addDose(){
    var data={};
    data["criteria"]=$("#inputNewDoseCriteria").val();
    data["dose"] = $("#inputNewDoseDose").val();
    data["idCalculationCriteria"] = $("#inputNewDrugDoseCalculationCriteria").val();
    
    $("#inputNewDrugDoseCalculationCriteria").prop( "disabled", true );
    
    $("#tblNewDrugDose").DataTable().row.add(data).draw();
    
    $('#modalNewDose').modal('hide');
}

function removeDose(){
    var table = $("#tblNewDrugDose").DataTable();
    
    if( checkNotUndefined(table.row('.selected').data()) ){
        table.row('.selected').remove().draw();
        if(table.rows().data().length === 0){
            $("#inputNewDrugDoseCalculationCriteria").prop( "disabled", false );
        }
    }else{
        displayWarningAlert("No ha seleccionado una medida");
    }
}

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

function loadNewCommercialNameModal(){
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
    
    $('#modalNewCommercialName').modal('show');
}



function addCommercialName(){
    var name = $("#inputNewCommercialName").val();
    $('#tblNewDrugCommercialName').DataTable().row.add({"commercialName":name}).draw();
    clearFormInputTextFields("formNewCommercialName");
    $("#formNewCommercialName").data('bootstrapValidator').destroy();
    $('#modalNewCommercialName').modal('hide');
}

function initializeNewDrugForm(){
    $("#formNewDrug").bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                drug: {
                    validators: {
                        notEmpty: {
                            message: 'El nombre del medicamento no puede estar vacio'
                        }
                    }
                },
                concentration: {
                    validators: {
                        notEmpty: {
                            message: 'La concentracion del medicamento no puede estar vacio'
                        },
                        regexp: {
                            regexp: /^[0-9]+(\.[0-9][0-9]?)?$/i,
                            message: 'Formato invalido [0-9].[0-9][09]'
                        }
                    }
                },
                dailyFrequency: {
                    validators: {
                        notEmpty: {
                            message: 'La frecuencia del medicamento no puede estar vacio'
                        }
                    },
                    regexp: {
                        regexp: /^[0-9]+$/i,
                        message: 'Formato invalido [0-9] una o mas veces'
                    }
                },
                treatmentDays: {
                    validators: {
                        notEmpty: {
                            message: 'Los dias de tratamienot para el medicamento no puede estar vacio'
                        },
                        regexp: {
                            regexp: /^[0-9]+$/i,
                            message: 'Formato invalido [0-9] una o mas veces'
                        }
                    }
                },
                applicationSchedule: {
                    validators: {
                        notEmpty: {
                            message: 'El horario de aplicacion para el medicamento no puede estar vacio'
                        }
                    }
                }
            },
            submitButtons: 'button[type="submit"]'
        }).on('success.form.bv', function(e) {
            e.preventDefault();
            saveNewDrug();
        });
}

function cancel(){
    $('#drugTabMenu a[href="#tabMain"]').tab('show');
}

function saveNewDrug(){
    var data = [];
    
    $('#formNewDrug :input').each(function(){
        if(this.name == 'active'){
            data.push({name:this.name,value:$(this).prop('checked')});
        }else{
            data.push({name:this.name,value:$(this).val()});
        }
    });
    var doseCont = 0;
    $("#tblNewDrugDose").DataTable().rows().data().each(function(rdata,rindex){
        data.push({name:"dose"+doseCont,value:rdata["dose"]});
        data.push({name:"criteria"+doseCont,value:rdata["criteria"]});
        doseCont++;
    });
    data.push({name:"doseCont",value:doseCont});
    
    var cnCont = 0;
    $("#tblNewDrugCommercialName").DataTable().rows().data().each(function(rdata,rindex){
        data.push({name:"commercialName"+cnCont,value:rdata["commercialName"]});
        cnCont++;
    });
    data.push({name:"cnCont",value:cnCont});
    
    var iCont = 0;
    $("#tblIncompatibles").DataTable().rows().data().each(function(rdata,rindex){
        data.push({name:"incompatibleCN"+iCont,value:rdata["idcommercialName"]});
        iCont++;
    });
    data.push({name:"iCont",value:iCont});
    
    var rCont = 0;
    $("#tblIncompatibilityRisk").DataTable().rows().data().each(function(rdata,rindex){
        data.push({name:"risk"+rCont,value:rdata["risk"]});
        data.push({name:"riskDrug"+rCont,value:rdata["idDrug"]});
        rCont++;
    });
    data.push({name:"rCont",value:rCont});
    
    var tCont = 0;
    $("#tblTreatmentsAssociated").DataTable().rows().data().each(function(rdata,rindex){
        data.push({name:"treatment"+tCont,value:rdata["idTreatment"]});
        tCont++;
    });
    data.push({name:"tCont",value:tCont});

    $.ajax({
        url:"/demo/drug/saveNewDrug",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado el medicamento correctamente");
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}