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

function newTreatment(){
    $('#treatmentTabMenu a[href="#tabNew"]').tab('show');
    
    $("#tblAsociatedDiagnostic").DataTable().clear().draw();
    $("#tblAsociatedDrug").DataTable().clear().draw();
    $("#inputNewTreatmentTreatment").val();
    $("#inputNewTreatmentDirections").val();
    $("#formNewTreatment").data('bootstrapValidator').resetForm();
    
    if ( ! $.fn.DataTable.isDataTable( '#tblDiagnostic' ) ) {
        initializeDiagnosticTable();
    }else{
        $("#tblDiagnostic").DataTable().ajax.reload();
    }
    if ( ! $.fn.DataTable.isDataTable( '#tblDrug' ) ) {
        initializeDrugTable();
    }else{
        $("#tblDrug").DataTable().ajax.reload();
    }
}

function modifyTreatment(){
    $('#treatmentTabMenu a[href="#tabModify"]').tab('show');
    loadTreatmentData();
}

function getAdditionalInfo(){
    var cie = $("#tblTreatment").DataTable().row('.selected').data();
    
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
                    "url":"/demo/diagnostictreatment/getConsultationByTreatment",
                    "data":function(){
                        return ({idTreatment:$("#tblTreatment").DataTable().row('.selected').data()["idTreatment"]});
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
            $('#treatmentTabMenu a[href="#tabAdditionalInfo"]').tab('show');
        }else{
            $('#tblAdditionalInfo').DataTable().ajax.reload();
            $('#treatmentTabMenu a[href="#tabAdditionalInfo"]').tab('show');
        }
    }else{
        displayWarningAlert("No se ha sleccionado un tratamiento");
    }
}

function removeTreatment(){
    var row = $("#tblTreatment").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        bootbox.dialog({
          message: "Esta seguro de eliminar este tratamiento?",
          title: "Confirmacion",
          buttons: {
            success: {
              label: "Si!",
              className: "btn-primary",
              callback: function() {
                $.ajax({
                    url:"/demo/diagnostictreatment/removeTreatment",
                    data:{idTreatment:row["idTreatment"]},
                    success:function(response,textStatus,jqXHR){
                        displaySuccessAlert("Se ha eliminado el tratamiento correctamente");
                        $("#tblTreatment").DataTable().row('.selected').remove().draw();
                    },
                    error:function(response){
                        displayDangerAlert("Ha ocurrido un error: "+response);
                    }
                });
              }
            },
            danger: {
              label: "No!",
              className: "btn-danger",
              callback: function() {
              }
            }
          }
        });
    }else{
        displayWarningAlert("No se ha seleccionado un tratamiento");
    }
}