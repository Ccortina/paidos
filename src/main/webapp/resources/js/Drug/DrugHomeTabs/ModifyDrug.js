/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeModifyDrugDoseTable();
});

function loadDrugData( data ){

    $("#tblModifyDrugDose").DataTable().clear().draw();
    setFormTextElement( "drug", data.drug );
    setFormSelectElement( "drugPresentationId", data.drugPresentationId.drugPresentationId );
    setFormSelectElement( "applicationMethodId", data.applicationMethodId.idApplicationMethod);
    setFormSelectElement( "administrationUnitId", data.administrationUnitId.idAdministrationUnit);
    setFormTextElement( "concentration", data.concentration);
    setFormTextElement( "treatmentDays", data.treatmentDays);
    setFormTextElement( "applicationSchedule", data.applicationSchedule);
    setFormTextElement( "dailyFrequency", data.dailyFrequency);
    $("#formModifyDrug [name='active']").prop("checked", data.active == 1);
    setFormSelectElement( "doseCalculationCriteriaId", data.doseCalculationCriteriaId.idDoseCalculationCriteria );
    $("#formModifyDrug [name='doseCalculationCriteriaId']").prop("disabled", true);
    data.drugdoseList.forEach(function( value, index ){
        $("#tblModifyDrugDose").DataTable().row.add( value ).draw();
    });
    setFormTextElement( "notes", data.notes);
    
    initializeModifyDrugCommercialNameTable( data.idDrug);
    initializeModifyIncompatibilityDrugListTable( data.idDrug );
    initializeModifyIncompatibilityCommercialNamesTable( data.idDrug );
    intializeModifyIncompatibleDrugsTable( data.idDrug );
    initializeModifyIncompatibilityRiskTable( data.idDrug );
    
}

function setFormTextElement( name, value ){
    $("#formModifyDrug [name='"+ name +"']").val(value);
}

function setFormSelectElement( name, value){
    $("#formModifyDrug [name='"+ name +"'] option").each(function( i ){
        if($(this).val() == value){
            $(this).prop("selected",true);
        }
    });   
}

function initializeModifyDrugDoseTable(){
    $("#tblModifyDrugDose").DataTable({
        "ordering":false,
        "destroy":true,
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
            {"data":"age"},
            {"data":"dose"}
        ],
        "initComplete":function(settings,json){
            var table = $('#tblModifyDrugDose').DataTable();
            
            $('#tblModifyDrugDose tbody').on( 'click', 'tr', function (e) {
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

function loadModifyDrugDoseModal(){
    
    //Check wich criteria is going to be used
    var criteria = $("#inputModifyDrugDoseCalculationCriteria").val();
    
    switch(criteria){
        case "4":
            var table = $("#tblModifyDrugDose").DataTable();
            //Check if theres already a dose
            if(table.rows().data().length >= 1){
                displayWarningAlert("Solo puede haber una dosis en el peso");
            }else{ 
                $('#modalModifyDoseByWeight').modal('show');
            }
            break;
        case "5":
            $('#modalModifyDoseByAge').modal('show'); 
            break;
        case"6":
            break;    
    }
    
    clearFormInputTextFields("formModifyDose");
}

function addModifyDoseByWeight(){
    var data={};
    data["age"]="";
    data["dose"] = $("#inputModifyDoseDose").val();
    data["idCalculationCriteria"] = $("#inputModifyDrugDoseCalculationCriteria").val();
    
    if(checkNotEmptyString(data.dose)){
        $("#inputModifyDrugDoseCalculationCriteria").prop( "disabled", true );
    
        $("#tblModifyDrugDose").DataTable().row.add(data).draw();
    
        $('#modalModifyDoseByWeight').modal('hide');
    }
}

function addModifyDoseByAge(){
    var data={};
    data["age"]=$("#inputModifyDoseCriteria").val();
    data["dose"] = $("#inputModifyDoseDoseByAge").val();
    data["idCalculationCriteria"] = $("#inputModifyDrugDoseCalculationCriteria").val();
    
    if(checkNotEmptyString(data.criteria) && checkNotEmptyString(data.dose)){
        $("#inputModifyDrugDoseCalculationCriteria").prop( "disabled", true );

        $("#tblModifyDrugDose").DataTable().row.add(data).draw();

        $('#modalModifyDoseByAge').modal('hide');
    }
}

function removeModifyDose(){
    var table = $("#tblModifyDrugDose").DataTable();
    
    if( checkNotUndefined(table.row('.selected').data()) ){
        table.row('.selected').remove().draw();
        if(table.rows().data().length === 0){
            $("#inputModifyDrugDoseCalculationCriteria").prop( "disabled", false );
        }
    }else{
        displayWarningAlert("No ha seleccionado una medida");
    }
}


