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
        $('#sibilingsTable tbody tr').dblclick(function(e){
                alert( "Handler for .dblclick() called." );
        });

        oTable = $('#sibilingsTable').dataTable({
                "bFilter":false,
                "bPaginate":false,
                "bSort":false
        });

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
        
        initalizeDrugsNoAssociationCommercialNameTable();
        
        addRowSelectionDrugsNoAssociationTable();
        
        $('#modalDiagnostic').on('hidden.bs.modal', function (e) {
            resetDiagnosticWizard();
        });
});

function ajaxCall(ID){
        $.ajax({
                        url:$('#'+ID).attr("action"),
                        data: $('#'+ID).serializeObject(),
                        type:"POST",
                success:function(response){
                        $('#ajaxMessage').html(response);
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
};

function initializeDiagnosticsTable(){
    diagnosticsTable = $('#diagnosticsTable').dataTable( {
        "sDom":'<"top"f>rt<"bottom"lip><"clear">',
        "aaSorting": [[ 1, "desc" ]],
        "iDeferLoading": 57,
        "sAjaxSource":"./diagnostics",
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

function initializeTreatmentsTable(id){
    treatmentsTable = $('#treatmentsTable').dataTable( {
        "sDom":'<"top"f>rt<"bottom"lip><"clear">',
        "bSort":false,
        "sAjaxSource":"./diagnosticTreatment",
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

function addRowSelectionTreatments(){
     $('#treatmentsTable tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('row_selected')){
                $(this).removeClass('row_selected');
            }
            else
                {
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
                        destroyDrugsTable("drugsDiv");
                        initializeDrugsTable(selectedTreatmentData[0]["idTreatment"]);
                        addRowSelectionDrugs();
                        $('#diagTabPane').bootstrapWizard('next');
                    }
                }
        });
}

function destroyTreatmentsTable(id){
    $('#'+id).html("<div class='row'><table id='treatmentsTable'><thead>\n\
                    <tr><th>Id</th><th>Tratamiento</th></tr></thead><tbody>\n\
                    </tbody></table></div>");
}

function initializeDrugsTable(id){
        drugsTable = $('#drugsTable').dataTable( {
            "sDom":'<"top"f>rt<"bottom"lip><"clear">',
            "bSort":true,
            "sAjaxSource":"./drugsByTreatment",
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
        "sAjaxSource":"./drugsCommercialNames",
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
            {"bVisible":false}
        ]
    });
    
}

//Add a row to the diagnostics table for the consultation
//
function addDiagnosticRow(diagnostic,treatment,drug,commercialName,id1,id2,id3,id4){
    $("#consultationDiagnosticsTable").dataTable().fnAddData([diagnostic + " , " +
                                                                treatment + " , " +
                                                                drug + " , " +
                                                                commercialName,id1,id2,id3,id4]);
    resetDiagnosticWizard();
                                                    
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
        "sAjaxSource":"./getAllDrugs",
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
        "sAjaxSource":"./drugsCommercialNames",
        "fnServerParams": function ( aoData ) { aoData.push( {name:"drugId",value:drugId} ); },
        "aoColumns":[{"mData":"commercialName"}]
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
    var cell;
    var rows = table.fnGetNodes(); //Get all the TR nodes of the table
    var dose = 0.0;
    
    //Check Weight is not empty or 0.
    
    
    
    //Iterate trought nodes to get data
    for(var i=0;i<rows.length;i++)
    {
        cell = table.fnGetData(rows[i],3);
        console.log(cell);
        //Check if dose is based on weight or age
        switch(cell[doseCalculationCriteriaId][idDoseCalculationCriteria])
        {
            case 1: //Weight
                
            break;
            case 2: //Age
            break;
            default: //NaN
        }
        //Calculate the correct dose for the patient
        dose = calculateDrugPrescription($('#weight').val(),cell["drugDoseId"][0]["dose"],
                                            cell["dailyFrequency"],cell["concentration"]);
                                            
    }
}

function calculateDrugPrescription(weight,dose,frequency,concentration){
    return ((weight*dose)/frequency/concentration);
}