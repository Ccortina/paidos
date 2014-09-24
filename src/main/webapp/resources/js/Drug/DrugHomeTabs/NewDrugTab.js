/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeNewDrugDoseTable();
    initializeNewDrugCommercialNameTable();
    initializeNewDrugForm();
    loadNewModifyCommercialNameForms();
    //initializeNewDoseFormWeight();
    //initializeNewDoseFormAge();
    
    $('.inputDecimal').inputmask('Regex',{regex:"[0-9]+(\.[0-9][0-9]?)?"});
    $('.inputInteger').inputmask('Regex',{regex:"[0-9]+"});
    
    
    $('#modalNewCommercialName').on('show.bs.modal', function (e) {
        $('#formNewCommercialName').bootstrapValidator('resetForm', true);
    });
    $('#modalModifyCommercialName').on('show.bs.modal', function (e) {
        $('#formModifyCommercialName').bootstrapValidator('resetForm', true);
    });
});

function initializeNewDrugDoseTable(){
    $("#tblNewDrugDose").DataTable({
        "ordering":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "searching":false,
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
        case "4":
            var table = $("#tblNewDrugDose").DataTable();
            //Check if theres already a dose
            if(table.rows().data().length >= 1){
                displayWarningAlert("Solo puede haber una dosis en el peso");
            }else{ 
                $('#modalNewDoseByWeight').modal('show');
            }
            break;
        case "5":
            $('#modalNewDoseByAge').modal('show'); 
            break;
        case"6":
            break;    
    }
    
    clearFormInputTextFields("formNewDose");
}

function initializeNewDoseFormWeight(){
    
        /*$("#formNewDoseByWeight").bootstrapValidator({
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
            addDoseByWeight();
        });*/
        $("#formNewDoseByWeight").bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                dose: {
                    validators: {
                        notEmpty: {
                            message: 'Este campo no puede estar vacio'
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
            addDoseByWeight();
        });
}

function initializeNewDoseFormAge(){
    /*$("#formNewDoseByAge").bootstrapValidator({
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
                        message: 'La edad no puede estar vacia'
                    },
                    integer: {
                        message: 'Formato invalido [0-9]'
                    }
                }
            }
        },
    submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        addDoseByAge();
    }); */
    $("#formNewDoseByAge").bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                criteria2:{
                    validators:{
                        notEmpty: {
                            message: 'Este campo no puede estar vacio'
                        },
                        integer: {
                            message: 'Formato invalido [0-9]'
                        }
                    }
                },
                dose2: {
                    validators: {
                        notEmpty: {
                            message: 'Este campo no puede estar vacio'
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
            addDoseByAge();
        });
}

function addDoseByWeight(){
    var data={};
    data["criteria"]="";
    data["dose"] = $("#inputNewDoseDose").val();
    data["idCalculationCriteria"] = $("#inputNewDrugDoseCalculationCriteria").val();
    
    if(checkNotEmptyString(data.dose)){
        $("#inputNewDrugDoseCalculationCriteria").prop( "disabled", true );
    
        $("#tblNewDrugDose").DataTable().row.add(data).draw();
    
        $('#modalNewDoseByWeight').modal('hide');
    }
}

function addDoseByAge(){
    var data={};
    data["criteria"]=$("#inputNewDoseCriteria").val();
    data["dose"] = $("#inputNewDoseDoseByAge").val();
    data["idCalculationCriteria"] = $("#inputNewDrugDoseCalculationCriteria").val();
    
    if(checkNotEmptyString(data.criteria) && checkNotEmptyString(data.dose)){
        $("#inputNewDrugDoseCalculationCriteria").prop( "disabled", true );

        $("#tblNewDrugDose").DataTable().row.add(data).draw();

        $('#modalNewDoseByAge').modal('hide');
    }
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
    var criteria = $("#inputNewDrugDoseCalculationCriteria").val();
    
    //if theres no criteria for the dose
    var table = $("#tblNewDrugDose").DataTable();
    //Check if theres already a dose
    if(table.rows().data().length >= 1 || parseInt(criteria)===6){

        $('#formNewDrug :input').each(function(){
            if(this.name == 'active'){
                data.push({name:this.name,value:$(this).prop('checked')});
            }else{
                data.push({name:this.name,value:$(this).val()});
            }
        });

        data.push({name:"doseCalculationCriteriaId",value:criteria});
        
        var doseCont = 0;
        $("#tblNewDrugDose").DataTable().rows().data().each(function(rdata,rindex){
            data.push({name:"dose"+doseCont,value:rdata["dose"]});
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
    }else{
        displayWarningAlert("Debe haber por lo menos una dosis");
    }
    
}