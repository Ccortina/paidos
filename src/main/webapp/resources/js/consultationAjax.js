/* 
* @Author Carlos Alfonso Cortina Arce
* @Email carlos.a.cortina@gmail.com
*/

var oTable;
var frequentDiagnosticTable;
var json;
var selectedDiagnosticData;
var selectedTreatmentData;
var selectedDrugData;
var selectedCommercialNamesData;
var diagnosticsTable;
var treatmentsTable = false;
var commercialNamesTable = false;
var reload;
var drugsTable = false;
var drugDose;

$(document).ready(function(){

        initializeSibilingsTable();

        //Diagnostics Table Initialization
        initializeDiagnosticsTable();

        //Diagnostics table  adding row selection
        addRowSelectionDiagnosticsTable();
        
        //Initilize the Div as a wizard
        $('#diagTabPane').bootstrapWizard({'tabClass':'nav nav-pills'});
        
        //The diagnostics added for the consultation
        initializeConsultationDiagnosticsTable();
        
        //Add add row function to 'agregar' button
        $('#addDiagnosticRowButton').on('click',function(){
            addDiagnosticRow(selectedDiagnosticData["diagnostic"],
                                selectedTreatmentData[0]["treatment"],
                                    selectedDrugData[0]["drug"] + " , " + selectedDrugData[0]["drugPresentationId"]["presentation"],
                                        selectedCommercialNamesData["commercialName"],
                                            selectedDiagnosticData,
                                                selectedTreatmentData,
                                                    selectedDrugData,
                                                        selectedCommercialNamesData);
        });
        
        //Add row with the drug without association at the diagnostic table 
        //diagnostic,treatment,drug,commercialName,id1,id2,id3,id4
        $('#addDrugNoAssociationRowButton').on('click',function(){
            addDiagnosticRow("---","---",selectedDrugData["drug"] + " , " + selectedDrugData["drugPresentationId"]["presentation"],
                                        selectedCommercialNamesData["commercialName"],
                                            "","",
                                                selectedDrugData,
                                                    selectedCommercialNamesData);
        });
        
        //Add behaviour to "generar receta" button. 
        $('#generatePrescriptionButton').on('click',function(){
            generatePrescription();
        });
        
        initalizeDrugsNoAssociationTable();
        
        //initalizeDrugsNoAssociationCommercialNameTable();
        
        addRowSelectionDrugsNoAssociationTable();
        
        //Reset the diagnostic wizar on modal close
        $('#modalDiagnostic').on('hidden.bs.modal', function (e) {
            resetDiagnosticWizard();
        });
        
        initializeActivitiesList();
        
        initializeSelectedActivitiesList();
        
        initializeActivityVaccineList("tblNewActivityVaccine");
        
        initializeActivityVaccineList("tblEditActivityVaccine");
        
        initializePatientDocumentsTable();
        
        addSaveOnChangeToForm();
        
        //Documents
        uploadFile();
        
        //Charts
        initializeGraphsPatientDataTable();
        
        saveOnchangeEditConsultationData();
        
        $("#modalGraph").on('shown.bs.modal',drawGraph);

});

//Sibilings table
function initializeSibilingsTable(){
    var idPatient=$("#consultationPatientId").val();

    $('#sibilingsTable').DataTable({
        "searching":false,
        "info":false,
        "ordering":false,
        "paging":false,
        "ajax":{
            "url":"./consultation/getConsultationSibilings",
            "data":{"idpatient":idPatient}},
        "columns":[
            {"data":"fullName"}
        ],
        "initComplete":function(settings,json){
            $('#sibilingsTable tbody tr').dblclick(function(e){
                
                var idSibiling = $("#sibilingsTable").DataTable().row(this).data()["idPatient"]["id"];
                openSibilingFile(idSibiling);
            });
        }
    });
}


//Sibilings table
function initializeConsultationDiagnosticAbstractTable(){
    var idPatient=$("#consultationPatientId").val();

    $('#tblConsultationDiagnosticAbstract').DataTable({
        "info":false,
        "ordering":false,
        "paging":false,
        "ajax":{
            "url":"./consultation/getConsultationSibilings",
            "data":{"idpatient":idPatient}},
        "columns":[
            {"data":"fullName"}
        ],
        "initComplete":function(settings,json){
            $('#sibilingsTable tbody tr').dblclick(function(e){
                
                var idSibiling = $("#sibilingsTable").DataTable().row(this).data()["idPatient"]["id"];
                openSibilingFile(idSibiling);
            });
        }
    });
}

function addSaveOnChangeToForm(){
    $( ".onChange" ).change(function() {
        ajaxCall($(this).closest('form').attr('id'));
        
    });
}
//Add a row to the diagnostics table for the consultation
//
function addDiagnosticRow(diagnostic,treatment,drug,commercialName,id1,id2,id3,id4){
    $("#consultationDiagnosticsTable").dataTable().fnAddData([diagnostic + " , " +
                                                                treatment + " , " +
                                                                drug + " , " +
                                                                commercialName,id1,id2,id3,id4,
                                                                "<button type='button' class='btn btn-danger' onclick='deleteDiagnosticRow(this)'>\n\
                                                                    Eliminar</button>"]);
    resetDiagnosticWizard();
                                                    
}

//Delete the row in the Consultation diagnositc table 
function deleteDiagnosticRow(row){
    $("#consultationDiagnosticsTable").dataTable().fnDeleteRow($(row).parent().parent()[0]);
}

//Makes an ajax Call wiht the id of the table
function ajaxCall(ID){
        $.ajax({
                        url:$('#'+ID).attr("action"),
                        data: $('#'+ID).serializeObject(),
                        type:"POST",
                success:function(response){
                        if( response === "function"){
                            $('#tblActivities').DataTable().ajax.reload();
                        }else{
                            $('#ajaxMessage').html(response);
                        }
                }
                });	
}

//This function converts a form in a json acceptedString
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
    console.log(o);
};

function initializeDiagnosticsTable(){
    diagnosticsTable = $('#diagnosticsTable').dataTable( {
        "sDom":'<"top"f>rt<"bottom"lip><"clear">',
        "aaSorting": [[ 1, "desc" ]],
        "iDeferLoading": 57,
        "sAjaxSource":"./consultation/diagnostics",
        "aoColumns":[
             {"mDataProp":"diagnostic"},
             {"mDataProp":"lastUsed",
                 "bVisible":false}
             ],
        "oLanguage": {
            "sSearch": "Buscar:",
            "sLengthMenu": "Mostrando _MENU_ resultados",
            "sInfo": "Mostrando entradas de _START_ a _END_ de un total de _TOTAL_",
            "oPaginate": {
                "sPrevious": "Anterior",
                "sNext": "Siguiente"
            }
        }     
    } );
}

function addRowSelectionDiagnosticsTable(){
    $('#diagnosticsTable tbody').on( 'click', 'tr', function (e) {
            if ( $(this).hasClass('row_selected') ) {
                $(this).removeClass('row_selected');
            }
            else 
            {
                diagnosticsTable.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                selectedDiagnosticData = diagnosticsTable.fnGetData( this );
                $('#selectedDiagnosticInput').val(selectedDiagnosticData["diagnostic"]);
                if(!treatmentsTable){
                    initializeTreatmentsTable(selectedDiagnosticData["idCIE10"]);
                    addRowSelectionTreatments();
                    $('#diagTabPane').bootstrapWizard('next');
                }else{
                    treatmentsTable.fnClearTable();
                    destroyTreatmentsTable("treatmentsDiv");
                    initializeTreatmentsTable(selectedDiagnosticData["idCIE10"]);
                    addRowSelectionTreatments();
                    $('#diagTabPane').bootstrapWizard('next');
                }
            }
        });
}

//Initialize the trreatment table
function initializeTreatmentsTable(id){
    treatmentsTable = $('#treatmentsTable').dataTable( {
        "sDom":'<"top"f>rt<"bottom"lip><"clear">',
        "bSort":false,
        "sAjaxSource":"./consultation/diagnosticTreatment",
        "fnServerParams": function ( aoData ) { aoData.push( {name:"diagnosticId",value:id} ); },
        "aoColumns": [
                     {   "bVisible": false,
                         "mRender": function(data,type,full){
                            return data.idTreatment;}
                     },
                     { "mRender": function(data,type,full){
                             return full[0].treatment;
                     } }
                     ],
        "oLanguage": {
            "sSearch": "Buscar:",
            "sLengthMenu": "Mostrando _MENU_ resultados",
            "sInfo": "Mostrando entradas de _START_ a _END_ de un total de _TOTAL_",
            "oPaginate": {
                "sPrevious": "Anterior",
                "sNext": "Siguiente"
            }
        }
    });
}

//Add the cpability of selecting a row in the table of treatments
function addRowSelectionTreatments(){
     $('#treatmentsTable tbody').on( 'click', 'tr', function (e){
            //If a row is already selected
            if ( $(this).hasClass('row_selected')){
                $(this).removeClass('row_selected');
            }
            else
                {
                    //If there's no selected row already.
                    treatmentsTable.$('tr.row_selected').removeClass('row_selected');
                    $(this).addClass('row_selected');
                    selectedTreatmentData = treatmentsTable.fnGetData( this );
                    $('#selectedTreatmentInput').val(selectedTreatmentData[0]["treatment"]);
                    if(!drugsTable){
                        initializeDrugsTable(selectedTreatmentData[0]["idTreatment"]);
                        addRowSelectionDrugs();
                        $('#diagTabPane').bootstrapWizard('next');
                    }
                    else
                    {
                        //Reset and redrwa the table
                        destroyDrugsTable("drugsDiv");
                        initializeDrugsTable(selectedTreatmentData[0]["idTreatment"]);
                        addRowSelectionDrugs();
                        $('#diagTabPane').bootstrapWizard('next');
                    }
                }
        });
}

//Reset the inner html of the table to prevent any residual code ot he previous 
//table
function destroyTreatmentsTable(id){
    $('#'+id).html("<div class='row'><table id='treatmentsTable'><thead>\n\
                    <tr><th>Id</th><th>Tratamiento</th></tr></thead><tbody>\n\
                    </tbody></table></div>");
}

function initializeDrugsTable(id){
        drugsTable = $('#drugsTable').dataTable( {
            "sDom":'<"top"f>rt<"bottom"lip><"clear">',
            "bSort":true,
            "sAjaxSource":"./consultation/drugsByTreatment",
            "fnServerParams": function ( aoData ) { aoData.push( {name:"treatmentId",value:id} ); },
            "aoColumns": [
                         { "mRender": function(data,type,full){
                                 return data.drug;
                         } },
                         { "mRender": function(data,type,full){
                                return full[0].drugPresentationId.presentation;
                         } }
                         ],
            "oLanguage": {
                "sSearch": "Buscar:",
                "sLengthMenu": "Mostrando _MENU_ resultados",
                "sInfo": "Mostrando entradas de _START_ a _END_ de un total de _TOTAL_",
                "oPaginate": {
                    "sPrevious": "Anterior",
                    "sNext": "Siguiente"
                }
            }
    });
}

function addRowSelectionDrugs(){
     $('#drugsTable tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('row_selected')){
                $(this).removeClass('row_selected');
            }
            else
                {
                    drugsTable.$('tr.row_selected').removeClass('row_selected');
                    $(this).addClass('row_selected');
                    selectedDrugData = drugsTable.fnGetData( this );
                    $('#selectedDrugInput').val(selectedDrugData[0]["drug"] + " , " + selectedDrugData[0]["drugPresentationId"]["presentation"] );
                    if(!commercialNamesTable){
                        initializeCommercialNamesTable(selectedDrugData[0]["idDrug"]);
                        addRowSelectionCommercialNamesTable();
                        $('#diagTabPane').bootstrapWizard('next'); //This line makes the wizard jump to the next section automatically
                    }
                    else
                    {
                       commercialNamesTable.fnClearTable();
                       destroyCommercialNamesTable("commercialNamesTab");
                       initializeCommercialNamesTable(selectedDrugData[0]["idDrug"]);
                       addRowSelectionCommercialNamesTable();
                       $('#diagTabPane').bootstrapWizard('next');
                    }
                }
        });
}

function destroyDrugsTable(id){
    $('#'+id).html("<div class='row'><table id='drugsTable'><thead>\n\
                    <tr><th>Id</th><th>Medicamento</th></tr></thead><tbody>\n\
                    </tbody></table></div>");
}

function initializeCommercialNamesTable(id){
    commercialNamesTable = $('#commercialNamesTable').dataTable( {
        "sDom":'<"top"f>rt<"bottom"lip><"clear">',
        "bSort":true,
        "sAjaxSource":"./consultation/drugsCommercialNames",
        "fnServerParams": function ( aoData ) { aoData.push( {name:"drugId",value:id} ); },
        "aoColumns":[{"mDataProp":"commercialName"}],
        "oLanguage": {
            "sSearch": "Buscar:",
            "sLengthMenu": "Mostrando _MENU_ resultados",
            "sInfo": "Mostrando entradas de _START_ a _END_ de un total de _TOTAL_",
            "oPaginate": {
                "sPrevious": "Anterior",
                "sNext": "Siguiente"
            }
        }
    });
}

function addRowSelectionCommercialNamesTable(){
     $('#commercialNamesTable tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('row_selected')){
                $(this).removeClass('row_selected');
            }
            else
                {
                    commercialNamesTable.$('tr.row_selected').removeClass('row_selected');
                    $(this).addClass('row_selected');
                    selectedCommercialNamesData = commercialNamesTable.fnGetData( this );
                    $('#selectedCommercialNamesInput').val(selectedCommercialNamesData["commercialName"]);
                    }
                
        });
}

function destroyCommercialNamesTable(id){
    $('#'+id).html("<div class='row'><table id='commercialNamesTable'><thead>\n\
                    <tr><th>Medicamento</th></tr></thead><tbody>\n\
                    </tbody></table></div>");
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

function initalizeDrugsNoAssociationTable(){
    $("#drugsNoAssociationTable").dataTable({
        "sDom":'<"top"f>rt<"bottom"lip><"clear">',
        "bSort":false,
        "oLanguage": {
            "sSearch": "Buscar:",
            "sLengthMenu": "Mostrando _MENU_ resultados",
            "sInfo": "Mostrando entradas de _START_ a _END_ de un total de _TOTAL_",
            "oPaginate": {
                "sPrevious": "Anterior",
                "sNext": "Siguiente"
            }
        },
        "sAjaxSource":"./consultation/getAllDrugs",
        "aoColumns": [
                     { "mData":"drug" },
                     { "mData":"drugPresentationId.presentation"},
                     { "mData":"applicationMethodId.applicationMethod"},
                     { "mData":"doseCalculationCriteriaId.criteria"}
                     ]
    });
}

function addRowSelectionDrugsNoAssociationTable(){
    var oTable = $('#drugsNoAssociationTable').dataTable();
    
    $('#drugsNoAssociationTable tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('row_selected')){
                $(this).removeClass('row_selected');
            }
            else
                {
                   oTable.$('tr.row_selected').removeClass('row_selected');
                   $(this).addClass('row_selected');
                   selectedDrugData = oTable.fnGetData( this );
                   destroyDrugsNoAssociationCommercialNameTable();
                   initalizeDrugsNoAssociationCommercialNameTable(selectedDrugData["idDrug"]);
                   addRowSelectionDrugsNoAssociationCommercialNameTable();
                }
        });
}

function initalizeDrugsNoAssociationCommercialNameTable(drugId){
    $("#drugsNoAssociationCommercialNameTable").dataTable({
        "bSort":false,
        "bFilter":false,
        "bInfo":false,
        "bPaginate":false,
        "sAjaxSource":"./consultation/drugsCommercialNames",
        "fnServerParams": function ( aoData ) { aoData.push( {name:"drugId",value:drugId} ); },
        "aoColumns":[{"mData":"commercialName"}],
        "oLanguage": { 
            "sSearch": "Buscar:",
            "sEmptyTable": "No hay informacion en la tabla."}
    });
}

function addRowSelectionDrugsNoAssociationCommercialNameTable(){
    var oTable = $('#drugsNoAssociationCommercialNameTable').dataTable();
    
    $('#drugsNoAssociationCommercialNameTable tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('row_selected')){
                $(this).removeClass('row_selected');
            }
            else
                {
                    oTable.$('tr.row_selected').removeClass('row_selected');
                    $(this).addClass('row_selected');
                    selectedCommercialNamesData = oTable.fnGetData( this );
                    }
                
        });
}

function destroyDrugsNoAssociationCommercialNameTable(){
     $('#drugsNoAssociationCommercialNameDiv').html("<div class='col-sm-12'>\n\
                                                        <table id='drugsNoAssociationCommercialNameTable'>\n\
                                                            <thead><tr><th>Nombre Comercial</th></tr></thead>\n\
                                                        </table>\n\
                                                      </div>");
}

function resetDiagnosticWizard(){
    $('#diagTabPane').bootstrapWizard('first');
    $('#selectedDiagnosticInput').val("");
    $('#selectedTreatmentInput').val("");
    $('#selectedDrugInput').val("");
    $('#selectedCommercialNamesInput').val("");
    selectedDiagnosticData = "";
    selectedTreatmentData = "";
    selectedDrugData = "";
    selectedCommercialNamesData = "";
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
    if( $("#weight").val() !== '0' )
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

//Activities section of the div

function initializeActivitiesList()
{   
    $('#tblActivities').dataTable({
        "scrollY": "200px",
        "bPaginate": false,
        "bInfo": false,
        "aaSorting": [[ 2, "asc" ]],
        "sAjaxSource":"./consultation/getAllActivities",
        "aoColumns":[
            {"mData":"activity"},
            {"mData":"idActivityType.type"},
            {"mData":"activityCost"}
        ],
        "initComplete": function(settings, json) {
            for( var i=0; i < json["aaData"].length; i++  ){
                
                //Add a delete button for to the objects
                json["aaData"][i].deleteButton = "<button type='button' class='btn btn-danger' onclick='deleteSelectedActivitiescRow(this)'>\n\
                                                      Eliminar</button>";
                
                //Add the activities that are default to the selectedactivities list
                if( json["aaData"][i]["consultationDefault"] === 1){
                    $("#tblSelectedActivities").dataTable().fnAddData(json["aaData"][i]);
                }
            }
            addRowSelectionActivitiesList();
        },
        "oLanguage": { 
            "sSearch": "Buscar:",
            "sEmptyTable": "No hay informacion en la tabla."}
    });
}

function addRowSelectionActivitiesList(){
    var table = $('#tblActivities').dataTable();
    
    $('#tblActivities tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('row_selected')){
                $(this).removeClass('row_selected');
            }
            else
                {
                    table.$('tr.row_selected').removeClass('row_selected');
                    $(this).addClass('row_selected');
                }
        });
}

function initializeSelectedActivitiesList()
{
    $('#tblSelectedActivities').dataTable({
        "bSort":false,
        "sScrollY": "200px",
        "bScrollCollapse": true,
        "bPaginate": false,
        "bFilter": false,
        "bInfo": false,
        "aoColumns":[
            {"mData":"activity"},
            {"mData":"activityCost"},
            {"mData":"deleteButton"}
        ],
        "oLanguage": {
            "sEmptyTable": "No hay informacion en la tabla."}
    });
}

function deleteSelectedActivitiescRow(row){
    $("#tblSelectedActivities").dataTable().fnDeleteRow($(row).parent().parent()[0]);
}

function addSelectedAcitivitiesRow(){
    var table = $("#tblSelectedActivities").DataTable();
    
    table.row.add($('#tblActivities').DataTable().row('.row_selected').data()).draw();
}

function initializeActivityVaccineList(tbl)
{
    $('#'+tbl).DataTable({
        "bSort":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "ajax":"./consultation/getAllVaccines",
        "columns":[
            {"data":"idVaccine",
                "visible":false},
            {"data":"vaccine"}
        ],
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search":"Buscar"},
        "initComplete": function(settings, json) {
            addRowSelectionActivityVaccineList(tbl);
        }
    });
}

function addRowSelectionActivityVaccineList(tbl){
    var table = $('#'+tbl).DataTable();
    
    $('#'+tbl+' tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('selected')){
                $(this).removeClass('selected');
                $('[name="idVaccine"]','#'+tbl).val(null);
            }
            else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    if(tbl === "tblEditActivityVaccine"){
                        $('#editActivityIdVaccine').val(table.row(this).data()["idVaccine"]);
                    }else{
                        $('#newActivityIdVaccine').val(table.row(this).data()["idVaccine"]);
                    }
                }
        });
}


function saveConsultation(){
    //Save appointment data

    
}

function editSelectedActivity(){
    var activity = $('#tblActivities').DataTable().row('.row_selected').data();
    
    $.each(activity, function(name, val){

    $el = $('[name="'+name+'"]','#editActivityForm');
    //console.log($('#tblEditActivityVaccine').DataTable().rows().column(0).data());
    
    if( $el.is('select') ){
        $("option",$el).each(function(){
            if(this.value === val ){ this.selected = true; }
        });
    }else{
        switch($el.attr("type")){
        case 'checkbox':
            $el.attr('checked', 'checked');
            break;
        case 'radio':
            $el.filter('[value="'+val+'"]').attr('checked', 'checked');
            break;
        default:
            if( name === "idVaccine"){
              if(val !== null){
                  $el.val(val["idVaccine"]);
              }else{
                  $el.val(null);
              }  
            }else{
                $el.val(val);
            }
            
        }
    }
    });
    
}

function getCurrentDate(){
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();

    if(dd<10) {
        dd='0'+dd;
    } 

    if(mm<10) {
        mm='0'+mm;
    } 

    today = mm+'/'+dd+'/'+yyyy;
    return today;
}

function openSibilingFile(idSibiling){
    var url= "patients/file/"+idSibiling;
    window.open(url);
}

//Section: Documents Tab
function uploadFile(){
    
    $("#formUploadFile").on('submit',function(e){
       var oMyForm = new FormData();
       oMyForm.append("file",file2.files[0]);
        
       $.ajax({
            url:'./consultation/uploadFile',
            data:oMyForm,
            dataType:'text',
            processData: false,
            contentType: false,
            cache: false,
            type:'POST',
            success: function(data, textStatus, jqXHR)
            {
                $("#tblConsultationPatientDocuments").DataTable().ajax.reload();
            }
        });
        
        e.preventDefault();
    });
    //$("#formUploadFile").submit();
}

function initializePatientDocumentsTable(){
    var idPatient=$("#consultationPatientId").val();
    
    $("#tblConsultationPatientDocuments").DataTable({
       "bSort":false,
       "searching":false,
       "scrollY": "200px",
       "scrollCollapse": true,
       "paging": false,
       "info":false,
       "ajax":{
           url:"./consultation/getPatientDocument",
           data:{"idPatient":idPatient}},
       "columns":[
           {"data":"name"},
           {"data":"deleteBtn"}],
       "initComplete": function(settings,json){
            $('#tblConsultationPatientDocuments tbody tr').dblclick(function(e){
                openDocument($("#tblConsultationPatientDocuments").DataTable().row(this).data()["name"]);
                
            });
       }
    });
}

function deleteDocument(button){
    var table = $("#tblConsultationPatientDocuments").DataTable();
    var file = table.row($(button).parent().parent()[0]).data()["name"];
    $.ajax({
        url:'./consultation/deletePatientDocument',
        data:{ 'file':file},
        success:function(msg){
            table.ajax.reload();
        }
    });
}

function openDocument(file){
    $.ajax({
        url:'./consultation/openPatientDocument',
        data:{ 'file':file}
    });
}

//Section:Charts/Graphs

function initializeGraphsPatientDataTable(){
    var idPatient=$("#consultationPatientId").val();
    
    $("#tblGraphsPastConsultation").DataTable({
       "bSort":false,
       "scrollY": "200px",
       "scrollCollapse": true,
       "paging": false,
       "info":false,
       "ajax":{
           url:"./consultation/getGraphPatientData",
           data:{"patient":idPatient}},
       "columns":[
            {"data":"idConsultation",
                "visible": false},
            {"data":"date"},
            {"data":"age"},
            {"data":"weight"},
            {"data":"size"},
            {"data":"pc"},
            {"data":"ta"},
            {"data":"ta2"},
            {"data":"taaverage"},
            {"data":"temperature"},
            {"data":"imc"}],
        "initComplete":function(settings,json){
            var table = $("#tblGraphsPastConsultation").DataTable();
            $("#tblGraphsPastConsultation").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }
    });
}

function saveOnchangeEditConsultationData(){
    $( ".onChange2" ).change(function() {
        
        var s = $("#inputGraphSize").val(); 
        var w = $("#inputGraphWeight").val();
        $("#inputGraphIMC").val(w / ((s/100)*(s/100)));
        
        ajaxCall("formGraphEdit");
        $.ajax({
            url:$('#formGraphEdit').attr("action"),
            data: $('#formGraphEdit').serializeObject(),
            type:"POST",
            success:function(response){
                $("#tblGraphsPastConsultation").DataTable().ajax.reload();
            }
         });
    });
}

function loadGraphPacientData(){
    
    var selectedRow = $("#tblGraphsPastConsultation").DataTable().row('.selected').data();
    console.log(selectedRow);
    $.each(selectedRow, function(name, val){

    $el = $('[name="'+name+'"]','#formGraphEdit');

    $el.val(val);
 

    });
}

function drawGraph(graph){
    var idPatient=$("#consultationPatientId").val();
    var patientName = $("#consultationPatientName").val();
    
    $.jqplot('divChart',  [[[1, 2],[3,5.12],[5,13.1],[7,33.6],[9,85.9],[11,219.9]]]); 
    
    var url;
    switch(graph){
        case 1:
            url = "./consultation/graph1";
            break;
            case 2:
                break;
                case 3:
                    break;
                    case 4:
                        break;
                        case 5:
                            break;
                            case 6:
                                break;
    }
    
    $.ajax({
        type:"POST",
        url:url,
        data: { 'patient':idPatient },
        dataType:"json",
        success:function(response){
            console.log("algo");
            console.log(response);
        },
        error: function (xhr, ajaxOptions, thrownError) {
        alert(xhr.status);
        alert(thrownError);
      }
    });
    
}