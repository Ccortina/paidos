/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeTreatmentTable();
});

function initializeTreatmentTable(){
    $("#tblTreatment").DataTable({
        "bSort":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/diagnostictreatment/getTreatmentsByUser",
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
            $('#tblTreatment tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblTreatment').DataTable();
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


