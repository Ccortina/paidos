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
                                    $("#treatmentsTable").DataTable().row('.selected').data()["treatment"],
                                        $("#drugsTable").DataTable().row('.selected').data()["drug"] + " , " + 
                                                $("#drugsTable").DataTable().row('.selected').data()["drugPresentationId"]["presentation"],
                                            $("#commercialNamesTable").DataTable().row('.selected').data()["commercialName"],
                                                $("#diagnosticsTable").DataTable().row('.selected').data(),
                                                    $("#treatmentsTable").DataTable().row('.selected').data(),
                                                        $("#drugsTable").DataTable().row('.selected').data(),
                                                            $("#commercialNamesTable").DataTable().row('.selected').data());
            }else if(sD && sT && sM && !sC){
                addDiagnosticRow($("#diagnosticsTable").DataTable().row('.selected').data()["cie10"]["diagnostic"],
                                    $("#treatmentsTable").DataTable().row('.selected').data()["treatment"],
                                        $("#drugsTable").DataTable().row('.selected').data()["drug"] + " , " +
                                                $("#drugsTable").DataTable().row('.selected').data()["drugPresentationId"]["presentation"],
                                            " --- ",
                                                $("#diagnosticsTable").DataTable().row('.selected').data(),
                                                    $("#treatmentsTable").DataTable().row('.selected').data(),
                                                        $("#drugsTable").DataTable().row('.selected').data(),
                                                            "");
            }else if(sD && sT && !sM && !sC){
                addDiagnosticRow($("#diagnosticsTable").DataTable().row('.selected').data()["cie10"]["diagnostic"],
                                    $("#treatmentsTable").DataTable().row('.selected').data()["treatment"],
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
        
        $("#consultationPrescriptionNumber").val((parseInt($("#prescriptionCounter").val())+1));
});

function checkNotUndefined(value){
    if(typeof value === 'undefined'){
        return false;
    }else{
        return true;
    }
}

function checkNotEmptyString(value){
    if(value === ""){
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
                    //Reset all the tables
                    $('#treatmentsTable').DataTable().column(1).search('',false,false).draw();
                    $('#drugsTable').DataTable().column(2).search('',false,false).draw();
                    $('#commercialNamesTable').DataTable().column(1).search('',false,false).draw();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    var diagnosticId = table.row(this).data()["cie10"]["idCIE10"];
                    $('#treatmentsTable').DataTable().column(1).search("-"+diagnosticId+"-",false,false).draw();
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
        "ajax":"/demo/consultation/getTreatments",
        "columns":[
            {"data":"treatment"},
            {"render":function(data,type,row){
                    var list=[];
                    row['cie10List'].forEach(function(entry){
                        list.push("-"+entry["idCIE10"]+"-");
                    });
                    return list;
            },
                "visible":false}
        ],
        "initComplete":function(settings,json){
            $('#treatmentsTable tbody').on( 'click', 'tr', function (e) {
                var required = $('#diagnosticsTable').DataTable().row('.selected').data();
                
                if(typeof required === 'undefined' ){
                    displayWarningAlert("Debe seleccionar por lo menos un diagnostico primero");
                }else{
                    var table = $('#treatmentsTable').DataTable();
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                        //Reset Drugs and commercial names table
                        $('#drugsTable').DataTable().column(2).search('',false,false).draw();
                        $('#commercialNamesTable').DataTable().column(1).search('',false,false).draw();
                    }else{
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                        var treatmentId = table.row(this).data()["idTreatment"];
                        $('#drugsTable').DataTable().column(2).search("-"+treatmentId+"-",false,false).draw();
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
        "ajax":"/demo/consultation/getDrugs",
        "columns":[
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"},
            {"render":function(data,type,row){
                var list=[];
                    row['treatmentList'].forEach(function(entry){
                        list.push("-"+entry["idTreatment"]+"-");
                    });
                    return list;
            },
            "visible":false
            }
        ],
        "initComplete":function(settings,json){

            $('#drugsTable tbody').on( 'click', 'tr', function (e) {
                var required = $('#treatmentsTable').DataTable().row('.selected').data();
                if(typeof required === 'undefined' ){
                    displayWarningAlert("Debe seleccionar por lo menos un tratamiento primero");
                }else{
                    var table = $('#drugsTable').DataTable();
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                        $('#commercialNamesTable').DataTable().column(1).search('',false,false).draw();
                    }else{
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                        var drugId = table.row(this).data()["idDrug"];
                        $('#commercialNamesTable').DataTable().column(1).search(drugId,false,false).draw();

                    }
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
        "ajax":"/demo/consultation/getDrugsCommercialNames",
        "columns":[
            {"data":"commercialName"},
            {"data":"drugId.idDrug"}
        ],
        "initComplete":function(settings,json){

            $('#commercialNamesTable tbody').on( 'click', 'tr', function (e) {
                var required = $('#drugsTable').DataTable().row('.selected').data();
                if(typeof  required === 'undefined'){
                    displayWarningAlert("Debe seleccionar por lo menos un medicamento primero");
                }else{
                    var table = $('#commercialNamesTable').DataTable();
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
        "ajax":"/demo/consultation/getDrugs",
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
                    $('#tblDWADrugsCommercialName').DataTable().column(1).search('',false,false).draw();
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    var drugId = table.row(this).data()["idDrug"];
                    $('#tblDWADrugsCommercialName').DataTable().column(1).search(drugId,false,false).draw();
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
        "ajax":"/demo/consultation/getDrugsCommercialNames",
        "columns":[
            {"data":"commercialName"},
            {"data":"drugId.idDrug",
                "visible":false}
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
    
    $('#treatmentsTable').DataTable().column(1).search('',false,false).draw();
    $('#drugsTable').DataTable().column(2).search('',false,false).draw();
    $('#commercialNamesTable').DataTable().column(1).search('',false,false).draw();
    
    
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
    var prescriptionHeader = $("#assignedDoctor").val() + "\t\t\t"+ getCurrentDate()+"\n\n\n";
    $("#consultationPrescription").val(prescriptionHeader);
    
    //Check Weight is not empty or 0.
    if( checkNotEmptyString($("#txtConsultationWeight").val()) && $("#txtConsultationWeight").val() > 0 )
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
                    dose = (($("#txtConsultationWeight").val() * cell["drugDoseList"][0]["dose"])
                                /cell["dailyFrequency"]/cell["concentration"]);
                                
                    r1 = dose + " " + cell["administrationUnitId"]["administrationUnit"] +
                            " cada " + 24/cell["dailyFrequency"] + " hora(s) por ";       
                break;
                case 2: //Age
                    //Age uses the dose to calculate the schedule, there can be multiple
                    //doses based on different ages.
                    if( cell["drugDoseList"].length > 1){
                    //Sort the ages from min to max
                        var ages = bubbleSort(cell["drugDoseList"]);
                    
                        for(var i=0; i < ages.length; i++){
                            if($("#age").val() <= ages[i]["age"]){
                                dose = 24/ages[i]["dose"];
                                break;
                            }
                        }
                    }else{
                        dose = 24/cell["drugDoseList"][0]["dose"];
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
        displayWarningAlert("El peso del paciente no puede ser 0.");            
        
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