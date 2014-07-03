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
        
        initializeDWADrugsTable();
        initializeDWADrugsCommercialNameTable();
        
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
            }else if(sD && sT && !sM && !sC){
                addDiagnosticRow($("#diagnosticsTable").DataTable().row('.selected').data()["cie10"]["diagnostic"],
                                    $("#treatmentsTable").DataTable().row('.selected').data()[0]["treatment"],
                                        " --- ",
                                            " --- ",
                                                $("#diagnosticsTable").DataTable().row('.selected').data(),
                                                    $("#treatmentsTable").DataTable().row('.selected').data(),
                                                        "",
                                                            "");
            }else if(sD && !sT && !sM && !sC){
                addDiagnosticRow($("#diagnosticsTable").DataTable().row('.selected').data()["cie10"]["diagnostic"],                                          
                                    " --- ",
                                        " --- ",
                                            " --- ",
                                                $("#diagnosticsTable").DataTable().row('.selected').data(),
                                                   "",
                                                        "",
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

function addDiagnosticRowDWA(){
    var sM = checkNotUndefined($("#tblDWADrugs").DataTable().row('.selected').data());
    var sC = checkNotUndefined($("#tblDWADrugsCommercialName").DataTable().row('.selected').data());
    
    if(sM && !sC){
        addDiagnosticRow($(" --- ",
                            " --- ",
                                $("#tblDWADrugs").DataTable().row('.selected').data()["drug"] + " , " +
                                        $("#tblDWADrugs").DataTable().row('.selected').data()["drugPresentationId"]["presentation"],
                                    " --- ",
                                        "",
                                            "",
                                                $("#tblDWADrugs").DataTable().row('.selected').data(),
                                                    ""));
    }else if(sM && sC){
        addDiagnosticRow(" --- ",
                            " --- ",
                                $("#tblDWADrugs").DataTable().row('.selected').data()["drug"] + " , " + 
                                        $("#tblDWADrugs").DataTable().row('.selected').data()["drugPresentationId"]["presentation"],
                                    $("#tblDWADrugsCommercialName").DataTable().row('.selected').data()["commercialName"],
                                        "",
                                            "",
                                                $("#tblDWADrugs").DataTable().row('.selected').data(),
                                                    $("#tblDWADrugsCommercialName").DataTable().row('.selected').data());
    }else if(!sM && sC){
        displayWarningAlert("Falta informacion.");
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
        if(value[3] !== ""){
            if(checkNotUndefined(value[3][0])){
                if(id3 !== ""){
                    if(checkNotUndefined(id3[0])){
                        if(entry['idDrug'] === id3[0]['idDrug']){
                            $.ajax({
                                url:"/demo/consultation/checkDrugIncompatibility",
                                data:{drugId1:value[3][0]['idDrug'],drugId2:id3[0]['idDrug']},
                                type:"POST",
                                error:function(jqXHR,textStatus,errorThrown){
                                    displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
                                },
                                success:function(response,status,jqXHR ){
                                    console.log(response);
                                    if(response){
                                        var row = $("#consultationDiagnosticsTable").DataTable().row(addedRowindex);
                                        $(row).css({"background-color":"#FF6961"});
                                        $(row).addClass("vpExpired");
                                    }
                                }
                            });
                        }          
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
                    $('#treatmentsTable').DataTable().ajax.reload();
                    $(this).removeClass('selected');
                    
                    table = $('#drugsTable').DataTable();
                    table.$('tr.selected').removeClass('selected');
                    table = $('#commercialNamesTable').DataTable();
                    table.$('tr.selected').removeClass('selected');
                    
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $('#treatmentsTable').DataTable().ajax.reload();
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
        "columns":[
            {"data":"treatment"}
        ],
        "initComplete":function(settings,json){
            var table = $('#treatmentsTable').DataTable();
            
            $('#treatmentsTable tbody').on( 'click', 'tr', function (e) {
                var required = $('#diagnosticsTable').DataTable().row('.selected').data();
                
                if(typeof required === 'undefined' ){
                    displayWarningAlert("Debe seleccionar por lo menos un diagnostico primero");
                }{
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                        $('#drugsTable').DataTable().ajax.reload();
                    }else{
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                        $('#drugsTable').DataTable().ajax.reload();
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
                    d.treatmentId = table.row('.selected').data()["idTreatment"];   
                } 
            }
        },
        "columns":[
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"}    
        ],
        "initComplete":function(settings,json){
            var table = $('#drugsTable').DataTable();
            
            $('#drugsTable tbody').on( 'click', 'tr', function (e) {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $('#commercialNamesTable').DataTable().ajax.reload();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $('#commercialNamesTable').DataTable().ajax.reload();

                }
            });
            
        },
        "createdRow": function( row, data, dataIndex ) {

            var  alergicDrug = $("#tblConsultationPatientAlergicDrug tr:contains("+data['drug']+")");

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
                    d.drugId = table.row('.selected').data()["idDrug"];
  
                } 
            }
        },
        "columns":[
            {"data":"commercialName"}    
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
                    }else{
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
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

function initializeDWADrugsTable(){
    $("#tblDWADrugs").DataTable({
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
            "data":{"treatmentId":"-1"}
        },
        "columns":[
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"},
            {"data":"applicationSchedule"},
            {"data":"administrationUnitId.administrationUnit"}
        ],
        "initComplete":function(settings,json){
            var table = $('#tblDWADrugs').DataTable();
            
            $('#tblDWADrugs tbody').on( 'click', 'tr', function (e) {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $('#tblDWADrugsCommercialName').DataTable().ajax.reload();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $('#tblDWADrugsCommercialName').DataTable().ajax.reload();
                }
            });
            
        },
        "createdRow": function( row, data, dataIndex ) {

            var  alergicDrug = $("#tblConsultationPatientAlergicDrug tr:contains("+data['drug']+")");

            if(alergicDrug.length > 0){
                $(row).css({"background-color":"#FDFD96"});
                $(row).addClass("vpSuspended");
            }
        }        
    });
}

function initializeDWADrugsCommercialNameTable(){
    $("#tblDWADrugsCommercialName").DataTable({
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
                var table = $('#tblDWADrugs').DataTable();
                if(typeof table.row('.selected').data() === 'undefined' ){
                    d.drugId = -1;
                }else{
                    d.drugId = table.row('.selected').data()["idDrug"];
  
                } 
            }
        },
        "columns":[
            {"data":"commercialName"}    
        ],
        "initComplete":function(settings,json){
            var table = $('#tblDWADrugsCommercialName').DataTable();
            
            $('#tblDWADrugsCommercialName tbody').on( 'click', 'tr', function (e) {
                var required = $('#tblDWADrugs').DataTable().row('.selected').data();
                if(typeof  required === 'undefined'){
                    displayWarningAlert("Debe seleccionar por lo menos un medicamento primero");
                }else{
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