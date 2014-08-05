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
        "paging": true,
        "info":false,
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
            "url":"/demo/diagnostictreatment/getCieByUser"
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

function removeCie(){
    var cie = $("#tblCie").DataTable().row('.selected').data();
    
    if(checkNotUndefined(cie)){
        $.ajax({
            url:"/demo/diagnostictreatment/removeCieFromUserCatalog",
            data:{idCie:cie["idCIE10"]},
            type: "POST",
            success:function(response){
                //Reload the patient table
                displaySuccessAlert("Se ha eliminado exitosamente el diagnostico");
                $("#tblCie").DataTable().row('.selected').remove().draw();
            },
            error: function(data, status, error) {
                displayDangerAlert(" No se ha eliminad el diagnostico");
            }
        });
    }else{
        displayWarningAlert("No se ha sleccionado un Diagnóstico");
    }
}

function getAdditionalInfo(){
    var cie = $("#tblCie").DataTable().row('.selected').data();
    
    if(checkNotUndefined(cie)){
        if(! $.fn.DataTable.isDataTable( '#tblAdditionalInfo' ) ){
            
            $('#tblAdditionalInfo').DataTable({
                "scrollY": "500px",
                "scrollCollapse": true,
                "paging": false,
                "info":false,
                "language": {
                    "emptyTable": "No hay informacion en la tabla.",
                    "search": "Buscar"
                },
                "ajax":{
                    "url":"/demo/diagnostictreatment/getConsultationByCie",
                    "data":function(){
                        return ({idCie:$("#tblCie").DataTable().row('.selected').data()["idCIE10"]})
                    }
                },
                "columns":[
                    {"data":"idAppointment.date"},
                    {"data":"idAppointment.idPatient.fatherLastName"},
                    {"render":function(data,row,full){
                            return (replaceNull(full["idAppointment"]["idPatient"]["motherLastName"]));
                    }},
                    {"render":function(data,row,full){
                            return (full["idAppointment"]["idPatient"]["firstName"] +" " +
                                    replaceNull(full["idAppointment"]["idPatient"]["secondName"]));
                    }}
                ]
            });
            $('#diagnosticTabMenu a[href="#tabAdditionalInfo"]').tab('show');
        }else{
            $('#tblAdditionalInfo').DataTable().ajax.reload();
            $('#diagnosticTabMenu a[href="#tabAdditionalInfo"]').tab('show');
        }
    }else{
        displayWarningAlert("No se ha sleccionado un Diagnóstico");
    }
}