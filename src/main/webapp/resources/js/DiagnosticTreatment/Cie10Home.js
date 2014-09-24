/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeCieTable();
});

function initializeCieTable(){
    $('#tblCie').DataTable({
        "scrollY": "500px",
        "scrollCollapse": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar",
            "lengthMenu": "Mostrar _MENU_ resultados por pagina",
             "paginate": {
                  "next": "Siguiente",
                  "previous": "Anterior"
              }
        },
        "ajax":{
            "url":"/demo/diagnostictreatment/getAllCie"
        },
        "columns":[
            {"data":"cieCode"},
            {"data":"diagnostic"}
        ],
        "initComplete": function(settings, json) {
            $('#tblCie tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblCie').DataTable();
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

function addCie(){
    var cie = $("#tblCie").DataTable().row('.selected').data();
    
    if(checkNotUndefined(cie)){
        $.ajax({
            url:"/demo/diagnostictreatment/addCieToUserCatalog",
            data:{idCie:cie["idCIE10"]},
            type: "POST",
            success:function(response){
                //Reload the patient table
                displaySuccessAlert("Se ha agregado exitosamente el diagnostico");
                $("#tblCie").DataTable().row('.selected').remove().draw();
            },
            error: function(data, status, error) {
                displayDangerAlert(" No se ha agregado el diagnostico");
            }
        });
    }else{
        displayWarningAlert("No se ha sleccionado un Diagnóstico");
    }
}
