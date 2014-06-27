/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    //Diagnostics Table Initialization
    
        
        initializeDiagnosticsTable();
        
        initializeTreatmentsTable();
        initializeDrugsTable();
        initializeCommercialNamesTable();
        
        //The diagnostics added for the consultation
        initializeConsultationDiagnosticsTable();
        
        //Add add row function to 'agregar' button
        //addDiagnosticRow( diagnostic,treatment,drug,commercialName,diagnosticId,TreatmentId,DrugId,CommercialNameId)
        $('#addDiagnosticRowButton').on('click',function(){
            
            var sD = checkNotUndefined($("#diagnosticsTable").DataTable().row('.selected').data());
            var sT = checkNotUndefined($("#treatmentsTable").DataTable().row('.selected').data());
            var sM = checkNotUndefined($("#drugsTable").DataTable().row('.selected').data());
            var sC = checkNotUndefined($("#commercialNamesTable").DataTable().row('.selected').data()); 
            
            //All are used
            if(sD && sT && sM && sC){
                addDiagnosticRow($("#diagnosticsTable").DataTable().row('.selected').data()["cie10"]["diagnostic"],
                                    $("#treatmentsTable").DataTable().row('.selected').data()[0]["treatment"],
                                        $("#drugsTable").DataTable().row('.selected').data()[0]["drug"] + " , " + 
                                                $("#drugsTable").DataTable().row('.selected').data()[0]["drugPresentationId"]["presentation"],
                                            $("#commercialNamesTable").DataTable().row('.selected').data()["commercialName"],
                                                $("#diagnosticsTable").DataTable().row('.selected').data(),
                                                    $("#treatmentsTable").DataTable().row('.selected').data(),
                                                        $("#drugsTable").DataTable().row('.selected').data(),
                                                            $("#commercialNamesTable").DataTable().row('.selected').data());
            }else if(!sD && !sT && sM && !sC){
                addDiagnosticRow($(" --- ",
                                    " --- ",
                                        $("#drugsTable").DataTable().row('.selected').data()["drug"] + " , " +
                                                $("#drugsTable").DataTable().row('.selected').data()["drugPresentationId"]["presentation"],
                                            " --- ",
                                                "",
                                                    "",
                                                        $("#drugsTable").DataTable().row('.selected').data(),
                                                            ""));  
            }else if(!sD && !sT && sM && sC){
                addDiagnosticRow(" --- ",
                                    " --- ",
                                        $("#drugsTable").DataTable().row('.selected').data()["drug"] + " , " + 
                                                $("#drugsTable").DataTable().row('.selected').data()["drugPresentationId"]["presentation"],
                                            $("#commercialNamesTable").DataTable().row('.selected').data()["commercialName"],
                                                "",
                                                    "",
                                                        $("#drugsTable").DataTable().row('.selected').data(),
                                                            $("#commercialNamesTable").DataTable().row('.selected').data());    
            }else if(sD && !sT && !sM && !sC){
                addDiagnosticRow($("#diagnosticsTable").DataTable().row('.selected').data()["cie10"]["diagnostic"],                                          
                                    " --- ",
                                        " --- ",
                                            " --- ",
                                                $("#diagnosticsTable").DataTable().row('.selected').data(),
                                                   "",
                                                        "",
                                                            "");
            }else if(sD && sT && !sM && !sC){
                addDiagnosticRow($("#diagnosticsTable").DataTable().row('.selected').data()["cie10"]["diagnostic"],
                                    $("#treatmentsTable").DataTable().row('.selected').data()[0]["treatment"],
                                        " --- ",
                                            " --- ",
                                                $("#diagnosticsTable").DataTable().row('.selected').data(),
                                                    $("#treatmentsTable").DataTable().row('.selected').data(),
                                                        "",
                                                            "");
            }else if(sD && sT && sM && !sC){
                addDiagnosticRow($("#diagnosticsTable").DataTable().row('.selected').data()["cie10"]["diagnostic"],
                                    $("#treatmentsTable").DataTable().row('.selected').data()[0]["treatment"],
                                        $("#drugsTable").DataTable().row('.selected').data()[0]["drug"] + " , " +
                                                $("#drugsTable").DataTable().row('.selected').data()[0]["drugPresentationId"]["presentation"],
                                            " --- ",
                                                $("#diagnosticsTable").DataTable().row('.selected').data(),
                                                    $("#treatmentsTable").DataTable().row('.selected').data(),
                                                        $("#drugsTable").DataTable().row('.selected').data(),
                                                            "");
            }else{
                displayWarningAlert("Falta informacion.");     
            } 
        });

        //Add behaviour to "generar receta" button. 
        $('#generatePrescriptionButton').on('click',function(){
            generatePrescription();
        });
        
        
        //Reset the diagnostic wizar on modal close
        $('#modalDiagnostic').on('hidden.bs.modal', function (e) {
            resetDiagnosticWizard();
        });
});

function checkNotUndefined(value){
    if(typeof value === 'undefined'){
        return false;
    }else{
        return true;
    }
}


//Add a row to the diagnostics table for the consultation
function addDiagnosticRow(diagnostic,treatment,drug,commercialName,id1,id2,id3,id4){
    var addedRowindex =$("#consultationDiagnosticsTable").DataTable().row.add([diagnostic + " , " +
                                                                treatment + " , " +
                                                                drug + " , " +
                                                                commercialName,id1,id2,id3,id4,
                                                                "<button type='button' class='btn btn-danger' onclick='deleteDiagnosticRow(this)'>\n\
                                                                    Eliminar</button>"]).draw().index();
    
    $("#consultationDiagnosticsTable").DataTable().rows().data().each(function(value,index){
        //No Drug info  
        console.log(value[3]);
        if(value[3] !== ""){
            if(checkNotUndefined(value[3][0])){
                if(id3 !== ""){
                    if(checkNotUndefined(id3[0])){
                        value[3][0]['drugList'].foreach(function(entry){
                            if(entry['idDrug'] === id3[0]['idDrug']){
                                var row = $("#consultationDiagnosticsTable").DataTable().row(addedRowindex);
                                $(row).css({"background-color":"#FF6961"});
                                $(row).addClass("vpExpired");
                            }
                        });
                    }else{
                        value[3][0]['drugList'].foreach(function(entry){
                            if(entry['idDrug'] === id3['idDrug']){
                                var row = $("#consultationDiagnosticsTable").DataTable().row(addedRowindex);
                                $(row).css({"background-color":"#FF6961"});
                                $(row).addClass("vpExpired");
                            }
                        });
                    }
                }
            }else{
                if(id3 !== ""){
                    if(checkNotUndefined(id3[0])){
                        value[3]['drugList'].foreach(function(entry){
                            if(entry['idDrug'] === id3[0]['idDrug']){
                                var row = $("#consultationDiagnosticsTable").DataTable().row(addedRowindex);
                                $(row).css({"background-color":"#FF6961"});
                                $(row).addClass("vpExpired");
                            }
                        });
                    }else{
                        value[3]['drugList'].foreach(function(entry){
                            if(entry['idDrug'] === id3['idDrug']){
                                var row = $("#consultationDiagnosticsTable").DataTable().row(addedRowindex);
                                $(row).css({"background-color":"#FF6961"});
                                $(row).addClass("vpExpired");
                            }
                        });
                    }
                }
            }
        }
    });
    resetDiagnosticWizard();
                                                    
}

//Delete the row in the Consultation diagnositc table 
function deleteDiagnosticRow(row){
    $("#consultationDiagnosticsTable").DataTable().row($(row).parent().parent()[0]).remove().draw(false);
}

function initializeDiagnosticsTable(){

    $("#diagnosticsTable").DataTable({
        "bSort":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":"/demo/consultation/getDiagnostics"
        },
        "columns":[
            {"data":"cie10.diagnostic"},
            {"data":"lastUsed",
                "visible":false}
        ],
        "initComplete":function(settings,json){
            var table = $('#diagnosticsTable').DataTable();
            
            $('#diagnosticsTable tbody').on( 'click', 'tr', function (e) {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $('#selectedDiagnosticInput').val("");
                    $('#selectedTreatmentInput').val("");
                    $('#selectedDrugInput').val("");
                    $('#selectedCommercialNameInput').val("");
                    $('#treatmentsTable').DataTable().ajax.reload();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $('#selectedDiagnosticInput').val(table.row('.selected').data()["cie10"]["diagnostic"]);
                    $('#treatmentsTable').DataTable().ajax.reload();
                    $('#selectedTreatmentInput').val("");
                    $('#selectedDrugInput').val("");
                    $('#selectedCommercialNameInput').val("");
                }
            });
        }
    });
}

//Initialize the trreatment table
function initializeTreatmentsTable(){
    
    $("#treatmentsTable").DataTable({
        "bSort":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":"/demo/consultation/getTreatmentByDiagnostic",
            "data":function ( d ) {
                var table = $('#diagnosticsTable').DataTable();
                if(typeof table.row('.selected').data() === 'undefined' ){
                    d.diagnosticId = -1;
                }else{
                    d.diagnosticId = table.row('.selected').data()["cie10"]["idCIE10"];
                } 
            }
        },
        "columns":[{
            "render": function ( data, type, full, meta ) {
                if(typeof full[0] === 'undefined'){
                    return full['treatment'];
                }else{
                    return full[0]['treatment'];
                }
            }}
        ],
        "initComplete":function(settings,json){
            var table = $('#treatmentsTable').DataTable();
            
            $('#treatmentsTable tbody').on( 'click', 'tr', function (e) {
                var required = $('#diagnosticsTable').DataTable().row('.selected').data();
                
                if(typeof required == 'undefined' ){
                    displayWarningAlert("Debe seleccionar por lo menos un diagnostico primero");
                }{
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                        $('#selectedTreatmentInput').val("");
                        $('#selectedDrugInput').val("");
                        $('#selectedCommercialNameInput').val("");
                        $('#drugsTable').DataTable().ajax.reload();
                    }else{
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                        var selectedTreatment;
                        if(typeof table.row('.selected').data()[0] === 'undefined'){
                            selectedTreatment = table.row('.selected').data()["treatment"];
                        }else{
                            selectedTreatment = table.row('.selected').data()[0]["treatment"];
                        }
                        $('#selectedTreatmentInput').val(selectedTreatment);
                        $('#drugsTable').DataTable().ajax.reload();
                        $('#selectedDrugInput').val("");
                        $('#selectedCommercialNameInput').val("");
                    }
                }
            });
        }          
    });
}

function initializeDrugsTable(){
    $("#drugsTable").DataTable({
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
            "url":"/demo/consultation/getDrugsByTreatment",
            "data":function ( d ) {
                var table = $('#treatmentsTable').DataTable();
                if(typeof table.row('.selected').data() === 'undefined' ){
                    d.treatmentId = -1;
                }else{
                    if(typeof table.row('.selected').data()[0] === 'undefined'){
                        d.treatmentId = table.row('.selected').data()["idTreatment"];
                    }else{
                        d.treatmentId = table.row('.selected').data()[0]["idTreatment"];
                    }
                } 
            }
        },
        "columns":[{
            "render": function ( data, type, full, meta ) {
                if(typeof full[0] === 'undefined'){
                    return full['drug'];
                }else{
                    return full[0]['drug'];
                }
            }},
            {"render": function ( data, type, full, meta ) {
                if(typeof full[0] === 'undefined'){
                    return full['drug'];
                }else{
                    return full[0].drugPresentationId.presentation;
                }
            }}    
        ],
        "initComplete":function(settings,json){
            var table = $('#drugsTable').DataTable();
            
            $('#drugsTable tbody').on( 'click', 'tr', function (e) {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $('#selectedDrugInput').val("");
                    $('#selectedCommercialNameInput').val("");
                    $('#commercialNamesTable').DataTable().ajax.reload();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    var selectedDrug;
                                        
                    if(typeof table.row('.selected').data()[0] === 'undefined'){
                        selectedDrug = table.row('.selected').data()["drug"];
                    }else{
                        selectedDrug = table.row('.selected').data()[0]["drug"];
                    }
                    $('#selectedDrugInput').val(selectedDrug);
                    $('#commercialNamesTable').DataTable().ajax.reload();
                    $('#selectedCommercialNameInput').val("");
                }
            });
            
        },
        "createdRow": function( row, data, dataIndex ) {
            if(typeof data[0] === 'undefined'){
                var  alergicDrug = $("#tblConsultationPatientAlergicDrug tr:contains("+data['drug']+")");
            }else{
                var  alergicDrug = $("#tblConsultationPatientAlergicDrug tr:contains("+data[0]['drug']+")");
            }
            if(alergicDrug.length > 0){
                $(row).css({"background-color":"#FDFD96"});
                $(row).addClass("vpSuspended");
            }
        }        
    });
}

function initializeCommercialNamesTable(){

    $("#commercialNamesTable").DataTable({
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
            "url":"/demo/consultation/getDrugsCommercialNames",
            "data":function ( d ) {
                var table = $('#drugsTable').DataTable();
                if(typeof table.row('.selected').data() === 'undefined' ){
                    d.drugId = -1;
                }else{
                    if(typeof table.row('.selected').data()[0] === 'undefined'){
                        d.drugId = table.row('.selected').data()["idDrug"];
                    }else{
                        d.drugId = table.row('.selected').data()[0]["idDrug"];
                    }
                } 
            }
        },
        "columns":[{
            "render": function ( data, type, full, meta ) {
                if(typeof full[0] === 'undefined'){
                    return full['commercialName'];
                }else{
                    return full[0]['commercialName'];
                }
            }}    
        ],
        "initComplete":function(settings,json){
            var table = $('#commercialNamesTable').DataTable();
            
            $('#commercialNamesTable tbody').on( 'click', 'tr', function (e) {
                var required = $('#drugsTable').DataTable().row('.selected').data();
                if(typeof  required === 'undefined'){
                    displayWarningAlert("Debe seleccionar por lo menos un medicamento primero");
                }else{
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                        $('#selectedCommercialNamesInput').val("");
                    }else{
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                        var selectedCommercialName;
                        if(typeof table.row('.selected').data()[0] === 'undefined'){
                            selectedCommercialName = table.row('.selected').data()["commercialName"];
                        }else{
                            selectedCommercialName = table.row('.selected').data()[0]["commercialName"];
                        }
                        $('#selectedCommercialNamesInput').val(selectedCommercialName);
                    }
                }
            });
        }
    });
}

function initializeConsultationDiagnosticsTable(){
    $("#consultationDiagnosticsTable").dataTable({
        "bSort":false,
        "bFilter":false,
        "bInfo":false,
        "bPaginate":false,
        "aoColumns":[
            null,
            {"bVisible":false},
            {"bVisible":false},
            {"bVisible":false},
            {"bVisible":false},
            null
        ]
    });
    
}


function resetDiagnosticWizard(){
    var tblD =$('#diagnosticsTable').DataTable();
    var tblT =$('#treatmentsTable').DataTable();
    var tblM =$("#drugsTable").DataTable();
    var tblC =$("#commercialNamesTable").DataTable();
    
    tblD.$('tr.selected').removeClass('selected');
    tblT.$('tr.selected').removeClass('selected');
    tblM.$('tr.selected').removeClass('selected');
    tblC.$('tr.selected').removeClass('selected');
    
    tblD.ajax.reload();
    tblT.ajax.reload();
    tblM.ajax.reload();
    tblC.ajax.reload();
    $("#tabsConsultationDiagnostic a[href='#tabDiagnosticAndTreatments']").tab('show');
}

//Get all the members of the diagnostic table and process them
//Read each row of the table and get the data
function generatePrescription(){
    var table = $("#consultationDiagnosticsTable").dataTable();
    var cell,commName;
    var rows = table.fnGetNodes(); //Get all the TR nodes of the table
    var dose = 0.0, r1="";
    
    //Clean text area of the prescription
    $("#consultationPrescription").val("");
    var prescriptionHeader = $("#consultationDoctor").val() + "\t\t\t"+ getCurrentDate()+"\n\n\n";
    $("#consultationPrescription").val(prescriptionHeader);
    
    //Check Weight is not empty or 0.
    console.log($("#txtConsultationWeight").val());
    if( $("#txtConsultationWeight").val() !== '0' )
    {
        //Iterate trought nodes to get data
        for(var i=0;i<rows.length;i++)
        {
            cell = table.fnGetData(rows[i],3);
            commName = table.fnGetData(rows[i],4);
            
            //Check if the cell cointain more than 1 object
            if(cell.length>1){
                cell =cell[0];
            }
            
            //Check if dose is based on weight or age
            switch(cell["doseCalculationCriteriaId"]["idDoseCalculationCriteria"])
            {
                case 1: //Weight
                    dose = (($("#weight").val() * cell["drugDoseId"][0]["dose"])
                                /cell["dailyFrequency"]/cell["concentration"]);
                                
                    r1 = dose + " " + cell["administrationUnitId"]["administrationUnit"] +
                            " cada " + 24/cell["dailyFrequency"] + " hora(s) por ";       
                break;
                case 2: //Age
                    //Age uses the dose to calculate the schedule, there can be multiple
                    //doses based on different ages.
                    if( cell["drugDoseId"].length > 1){
                    //Sort the ages from min to max
                        var ages = bubbleSort(cell["drugDoseId"]);
                    
                        for(var i=0; i < ages.length; i++){
                            if($("#age").val() <= ages[i]["age"]){
                                dose = 24/ages[i]["dose"];
                                break;
                            }
                        }
                    }else{
                        dose = 24/cell["drugDoseId"][0]["dose"];
                    }
                      r1 = cell["dailyFrequency"] + " " + cell["administrationUnitId"]["administrationUnit"] +
                            " cada " + dose + " hora(s) por " ;
                    
                break;
                default: //NaN
                      r1 = cell["administrationUnitId"]["administrationUnit"] +
                           " cada " + 24/cell["dailyFrequency"] + " hora(s) por "; 
            }
            //The prescription
            $("#consultationPrescription").val($("#consultationPrescription").val() + (i+1) + ".-" + commName["commercialName"] + 
                                                    " , Presentacion: " + cell["drugPresentationId"]["presentation"] + 
                                                    "\n" + cell["applicationMethodId"]["applicationMethod"] + " " +
                                                    r1 +
                                                    cell["treatmentDays"] + " dia(s).\n Horario de administracion: " + 
                                                    cell["applicationSchedule"] + ".\n\n");
            if(cell["notes"] !== ""){
                $("#consultationPrescription").val($("#consultationPrescription").val() + "Observaciones: " + cell["notes"] + ".\n\n");
            }                                
            
        }

        $('#consultationTabMenu a[href="#receta"]').tab('show');
    } else {
        //Alert and stop process if weigth is 0
        $("#sideBarAlert").html("<div class='alert alert-danger alert-dismissable'>\n\
                                    <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>\n\
                                    <strong>Peligro!</strong> El peso del paciente no puede ser 0.\n\
                                    </div>");
        
    }    
    
    if($("#tblMeasuresConsultation").DataTable().data().lenth > 0){
        $("#consultationPrescription").val($("#consultationPrescription").val() + "\n Medidas:\n");

        $("#tblMeasuresConsultation").DataTable().data().each(function(d){
            $("#consultationPrescription").val($("#consultationPrescription").val() + d[0] + " " + d[1] + " " + d[2] +" \n");
        });
    }
    
}

function calculateDrugPrescription(weight,dose,frequency,concentration){
    return ((weight*dose)/frequency/concentration);
}

function bubbleSort(list){
   var  swaps = 0,endIndex = 0,len = list.length - 1,hasSwap = true;
 
    for (var i = 0; i < len; i++) {

        hasSwap = false;

        for (var j = 0, swapping, endIndex = len - i; j < endIndex; j++) {

            if (list[j]["age"] > list[j + 1]["age"]) {

                swapping = list[j];

                list[j] = list[j + 1];
                list[j + 1] = swapping;

                swaps++;
                hasSwap = true;
            };
        };

        if (!hasSwap) {
            break;
        }
    }
    
    return list;
}

function checkUndefined(test){
    if(typeof test === 'undefined'){
        return true;
    }else{
        return false;
    }
}