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
var selectedDiagnosticId;
var reload;

$.fn.dataTableExt.oApi.fnReloadAjax = function ( oSettings, sNewSource, fnCallback, bStandingRedraw )
{
    if ( typeof sNewSource != 'undefined' && sNewSource != null )
    {
        oSettings.sAjaxSource = sNewSource;
    }
    this.oApi._fnProcessingDisplay( oSettings, true );
    var that = this;
    var iStart = oSettings._iDisplayStart;
    var aData = [];

    this.oApi._fnServerParams( oSettings, aData );

    oSettings.fnServerData( oSettings.sAjaxSource, aData, function(json) {
        /* Clear the old information from the table */
        that.oApi._fnClearTable( oSettings );

        /* Got the data - add it to the table */
        var aData =  (oSettings.sAjaxDataProp !== "") ?
            that.oApi._fnGetObjectDataFn( oSettings.sAjaxDataProp )( json ) : json;

        for ( var i=0 ; i<aData.length ; i++ )
        {
            that.oApi._fnAddData( oSettings, aData[i] );
        }

        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
        that.fnDraw();

        if ( typeof bStandingRedraw != 'undefined' && bStandingRedraw === true )
        {
            oSettings._iDisplayStart = iStart;
            that.fnDraw( false );
        }

        that.oApi._fnProcessingDisplay( oSettings, false );

        /* Callback user function - for event handlers etc */
        if ( typeof fnCallback == 'function' && fnCallback != null )
        {
            fnCallback( oSettings );
        }
    }, oSettings );
}

$(document).ready(function(){
        $('#sibilingsTable tbody tr').dblclick(function(e){
                alert( "Handler for .dblclick() called." );
        });

        oTable = $('#sibilingsTable').dataTable({
                "bFilter":false,
                "bPaginate":false,
                "bSort":false
        });

        frequentDiagnosticTable = $('#fdTable').dataTable( {
                "bSort":false,
                "sAjaxSource":"./frequentDiagnostics",
        "aoColumns":[
                     {"mDataProp":"idCIE10"
                         ,"bVisible":false},   
                     {"mDataProp":"diagnostic"}
                     ]
    } );

        diagnosticsTable = $('#diagnosticsTable').dataTable( {
                "bSort":false,
                "sAjaxSource":"./diagnostics",
        "aoColumns":[
                     {"mDataProp":"idCIE10"
                         ,"bVisible":false},   
                     {"mDataProp":"diagnostic"}
                     ]
    } );

        $('#fdTable tbody').on( 'click', 'tr', function (e) {
                if ( $(this).hasClass('row_selected') ) {
            $(this).removeClass('row_selected');
        }
        else {
            frequentDiagnosticTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
            rowSelectedData = frequentDiagnosticTable.fnGetData( this );
            alert(rowSelectedData["idCIE10"]);
        }
        } );

        $('#diagnosticsTable tbody').on( 'click', 'tr', function (e) {
                if ( $(this).hasClass('row_selected') ) {
        $(this).removeClass('row_selected');
        }
        else {
                diagnosticsTable.$('tr.row_selected').removeClass('row_selected');
        $(this).addClass('row_selected');
        rowSelectedData = frequentDiagnosticTable.fnGetData( this );
        if(!treatmentsTable){
                initializeTreatmentsTable(rowSelectedData["idCIE10"]);
        }else{
                treatmensTable.fnReloadAjax();
        }
        }
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

function fnGetSelectedRow(oTableLocal)
{
        return alert(oTableLocal.$('tr.row_selected'));
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

function initializeTreatmentsTable(id){
        treatmentsTable = $('#treatmentsTable').dataTable( {
                "bSort":false,
                "sAjaxSource":"./diagnosticTreatment",
        "aoColumns":[
                     {"mDataProp":"idTreatment"
                         ,"bVisible":false},   
                     {"mDataProp":"treatment"}
                     ],
        "fnServerParams": function ( aoData ) {
                         aoData.push( {name:"diagnosticId",value:id} );
                                        }             
    });
}
