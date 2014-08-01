/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initiliazeAppointmentFormValidation();
    initializeConsultationMotiveTableModifyApp();
    
    //Load today appointments
    $('a[href="#tabList"]').on('shown.bs.tab', function (e) {
        $("#inputDateModifyApp").val(moment().format("DD/MM/YYYY"));
        $('#formModifyAppointment').data('bootstrapValidator').validateField('date');
    });

});

function initializeDoctorAppointments(){
    $("#tblAppointmentsList").DataTable({
        "scrollY": "150px",
        "ordering":false,
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            url:"/demo/appointment/getDoctorAppointmentsList",
            data:{
                start:function(){return ($("#inputDateModifyApp").val());}}
            },
        "columns":[
            {"data":"startTime"},
            {"render":function(data,row,full){
                return (full['idPatient']['firstName']+" "+full['idPatient']['fatherLastName']);
            }},
            {"data":"motive"},
            {"data":"idStatus.status"}
        ],
        "initComplete":function(settings,json){

            $('#tblAppointmentsList tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblAppointmentsList').DataTable();
                if ( !$(this).hasClass('selected') ) {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    //Load appointment data
                    loadAppointmentData();
                }   
            });
        }
    });
}

function replaceNull(value){
    if(typeof value === 'undefined' || value === ''){
        return '';
    }else{
        return value;
    }
}

function checkNotUndefined(value){
    if(typeof value === 'undefined'){
        return false;
    }else{
        return true;
    }
}