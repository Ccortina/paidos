/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeNewDrugDoseTable();
});

function initializeNewDrugDoseTable(){
    $("#tblNewDrugDose").DataTable({
        "ordering":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "deferRender": true,
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
    var table = $("#tblNewDrugDose").DataTable();
    
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
}

function initializeNewDoseFormWeight(){
    var table = $("#tblNewDrugDose").DataTable();
    
    //Check if theres already a dose
    if(table.rows().data().length === 1){
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
                        }
                    }
                }
            }
        });
        $('#modalNewDose').modal('show');
    }
}

function initializeNewDoseFormAge(){
    //Check if theres previous criterias
    
}


function addDose(){
    var data=[];
    data.push({name:"criteria",value:$("#inputNewDoseCriteria").val()});
    data.push({name:"dose",value:$("#inputNewDoseDose").val()});
    data.push({name:"idCalculationCriteria",value:$("#inputNewDrugDoseCalculationCriteria").val()});
    
    if($("#inputNewDrugDoseCalculationCriteria").val() === 1){
        
    }
        
    $("#tblNewDrugDose").DataTable().row.add(data).draw();
    
}