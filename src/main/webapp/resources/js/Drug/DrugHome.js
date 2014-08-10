/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeDrugTable();
    
    $('.inputDecimal').inputmask('Regex',{regex:"[0-9]+(\.[0-9][0-9]?)?"});                                        
    
});

function initializeDrugTable(){
    $("#tblDrug").DataTable({
        "ordering":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/drug/getDrugByUser",
        "columns":[
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"},
            {"data":"administrationUnitId.administrationUnit"},
            {"data":"applicationMethodId.applicationMethod"},
            {"data":"doseCalculationCriteriaId.criteria"},
            {"render":function(data,type,row){ 
                if(row['active'] === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }}
        ],
        "initComplete":function(settings,json){
            $('#tblDrug tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblDrug').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        },
        "createdRow": function( row, data, dataIndex ) {
            if(data.active !== 1){
                $(row).css({"background-color":"#FDFD96"});
            }
        }
    });
}

function newDrug(){
    $('#drugTabMenu a[href="#tabNew"]').tab('show');
}