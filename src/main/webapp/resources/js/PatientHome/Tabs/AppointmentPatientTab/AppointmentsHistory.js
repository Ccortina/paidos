/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){

    $('a[href="#tabAppointments"]').on('shown.bs.tab', function (e) {
        $('#tblAppointmentsHistory').DataTable().columns.adjust().draw();
    });
});

function initializeAppointmentHistoryTable(){

 $('#tblAppointmentsHistory').DataTable({
        "scrollY": "300px",
        "order":[0,'asc'],
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            url:"/demo/patients/getAppointmentsByPatient",
            data:{patient:function(){return $("#inputIdPatientApp").val();}}
        },
        "columns":[
            {"render":function(data,row,full){return(moment(full['date']).format('DD/MM/YYYY'));}},
            {"data":"startTime"},
            {"data":"idStatus.status"},
            {"render":function(data,type,row){ 
                if(row['immunization']){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }},
            {"data":"motive"}
        ],
        "initComplete":function(settings,json){

            $('#tblAppointmentsDay tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblAppointmentsDay').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }   
            });
        },
        "createdRow": function( row, data, dataIndex ) {
            switch(data.idStatus.status){
                case 'Completa':
                    $(row).css({'background-color':'#5BAA3A'});
                    break;
                case 'Cancelada':
                    $(row).css({'background-color':'#E04747'});
                    break;    
            }
        },
    });
}


