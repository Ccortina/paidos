/* 
* @Author Carlos Alfonso Cortina Arce
* @Email carlos.a.cortina@gmail.com
*/

var oTable;
var frequentDiagnosticTable;
var json;
var rowSelectedData;
var diagnosticsTable;
var treatmentsTable = false;
var commercialNamesTable = false;
var selectedDiagnosticId;
var reload;
var drugsTable = false;

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
        
        $('#diagTabPane').bootstrapWizard({'tabClass':'nav nav-pills'});
        
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
        "aaSorting": [[ 1, "desc" ]],
        "iDeferLoading": 57,
        "sAjaxSource":"./diagnostics",
        "aoColumns":[
             {"mDataProp":"diagnostic"},
             {"mDataProp":"lastUsed",
                 "bVisible":false}
             ]
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
                rowSelectedData = diagnosticsTable.fnGetData( this );
                if(!treatmentsTable){
                    initializeTreatmentsTable(rowSelectedData["idCIE10"]);
                    addRowSelectionTreatments();
                    $('#diagTabPane').bootstrapWizard('next');
                }else{
                    treatmentsTable.fnClearTable();
                    destroyTreatmentsTable("treatmentsDiv");
                    initializeTreatmentsTable(rowSelectedData["idCIE10"]);
                    addRowSelectionTreatments();
                    $('#diagTabPane').bootstrapWizard('next');
                }
            }
        });
}

function initializeTreatmentsTable(id){
    treatmentsTable = $('#treatmentsTable').dataTable( {
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
                     ]
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
                    rowSelectedData = treatmentsTable.fnGetData( this );
                    if(!drugsTable){
                        initializeDrugsTable(rowSelectedData[0]["idTreatment"]);
                        addRowSelectionDrugs();
                        $('#diagTabPane').bootstrapWizard('next');
                    }
                    else
                    {
                        destroyDrugsTable("drugsDiv");
                        initializeDrugsTable(rowSelectedData[0]["idTreatment"]);
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
                             ]
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
                    rowSelectedData = drugsTable.fnGetData( this );
                    if(!commercialNamesTable){
                        initializeCommercialNamesTable(rowSelectedData[0]["idDrug"]);
                        addRowSelectionCommercialNamesTable();
                        $('#diagTabPane').bootstrapWizard('next');
                    }
                    else
                    {
                       commercialNamesTable.fnClearTable();
                       destroyCommercialNamesTable("commercialNamesTab");
                       initializeCommercialNamesTable(rowSelectedData[0]["idDrug"]);
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
                "bSort":true,
                "sAjaxSource":"./drugsCommercialNames",
                "fnServerParams": function ( aoData ) { aoData.push( {name:"drugId",value:id} ); },
                "aoColumns":[{"mDataProp":"commercialName"}]
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
                    rowSelectedData = commercialNamesTable.fnGetData( this );
                    }
                
        });
}

function destroyCommercialNamesTable(id){
    $('#'+id).html("<div class='row'><table id='commercialNamesTable'><thead>\n\
                    <tr><th>Medicamento</th></tr></thead><tbody>\n\
                    </tbody></table></div>");
}