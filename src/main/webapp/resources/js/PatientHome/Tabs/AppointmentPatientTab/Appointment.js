/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeConsultationMotiveTable();
    initiliazeAppointmentFormValidation();
    $('a[href="#tabAppointment"]').on('shown.bs.tab', function (e) {
        $("#inputDateApp").val(moment().format("DD/MM/YYYY"));
        $('#formAppointment').data('bootstrapValidator').validateField('date');
    });
});

function initializeConsultationMotiveTable(){
    
    $("#tblConsultationMotives").DataTable({
        "scrollY": "150px",
        "order": [ 0, 'desc' ],
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/patients/getConsultationMotives",
        "columns":[
            {"data":"lastUsed","visible":false},
            {"data":"motive"}
        ],
        "initComplete":function(settings,json){

            $('#tblConsultationMotives tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblConsultationMotives').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $("#inputMotiveApp").val('');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#inputMotiveApp").val(table.row('.selected').data()['motive']);
                    $("#formAppointment").data('bootstrapValidator').revalidateField('motive');
                }   
            });
        }
    });
}

function initiliazeAppointmentFormValidation(){
    $('#formAppointment').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            date: {
                validators: {
                    notEmpty: {
                        message: 'La fecha de la cita no puede estar vacia'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'No es una fecha valida'
                    }
                }
            },
            startTime: {
                validators: {
                    notEmpty: {
                        message: 'La hora no puede estar vacia'
                    },
                    regexp: {
                        regexp: /^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/i,
                        message: 'Formato debe ser HH:MM 24Hrs'
                    }
                }
            },
            motive: {
                validators: {
                    notEmpty: {
                        message: 'El motivo no puede estar vacio'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveAppointment();
    }).on('success.field.bv', '[name="date"]', function(e, data) {
            
            if ( !$.fn.DataTable.isDataTable( '#tblAppointmentsDay' ) ) {
                initializeappointmentDayTable();
            }else{
                $("#tblAppointmentsDay").DataTable().ajax.reload(); 
            }
    });
}

function initializeappointmentDayTable(){
  
    $("#tblAppointmentsDay").DataTable({
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
            data:{start:function(){return $("#inputDateApp").val()}}
        },
        "columns":[
            {"data":"startTime"},
            {"render":function(data,row,full){
                return (full['idPatient']['firstName']+" "+replaceNull(full['idPatient']['secondName'])
                            +" "+full['idPatient']['fatherLastName']+" "+replaceNull(full['idPatient']['motherLastName']));
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
        }
    });
}

function appointPatient(){
    
    if(checkNotUndefined($("#tblPatients").DataTable().row('.selected').data())){
        var patient = $("#tblPatients").DataTable().row('.selected').data();
        $("#inputIdPatientApp").val(patient['idPatient']);
        $("#inputPatientNameApp").val(patient['firstName']+" "+patient['fatherLastName']);
        $("#inputPatientBirthdayApp").val(patient['birthday']);
        $("option",$("#inputDoctorApp")).each(function(){
           if(this.value === $("#globalUser").val()){this.selected=true;}
        });
        if ( !$.fn.DataTable.isDataTable( '#tblRelativesApp' ) ) {
            initializePatientRelativeAppointmentTable();
        }else{
            $("#tblRelativesApp").DataTable().ajax.reload(); 
        }
        if ( !$.fn.DataTable.isDataTable( '#tblPatientInmunizationApp' ) ) {
            initializePatientInmunizationAppTable();
        }else{
            $("#tblPatientInmunizationApp").DataTable().ajax.reload(); 
        }    
        //tblAppointmentsHistory
        if ( !$.fn.DataTable.isDataTable( '#tblAppointmentsHistory' ) ) {
            initializeAppointmentHistoryTable();
        }else{
            $("#tblAppointmentsHistory").DataTable().ajax.reload(); 
        }
        //tblConsultationHistory
        if ( !$.fn.DataTable.isDataTable( '#tblConsultationHistory' ) ) {
            initializeConsultationHistoryTable();
        }else{
            $("#tblConsultationHistory").DataTable().ajax.reload(); 
        }
        $('#patientTabMenu a[href="#tabAppointment"]').tab('show');
    }else{
        displayWarningAlert("No se ha seleccionado un paciente");
    }
}

function saveAppointment(){

    var patient = $('#tblPatients').DataTable().row('.selected').data();
    if(checkNotUndefined(patient)){
        var data = [];
        var inputs = $('#formAppointment :input');
        var extraInputs = $('#formAppointmentAdditionalInfo :input');

        //Collect form data
        inputs.each(function() {
            if(this.name === "immunization"){
                data.push({name:this.name,value:$(this).prop('checked')});
            }else{
                data.push({name:this.name,value:$(this).val()});    
            }

        });
        extraInputs.each(function(){
           data.push({name:this.name,value:$(this).val()}); 
        });
        //console.log(data);
        //Send to controller
        $.ajax({
            url:"/demo/patients/savePatientAppointment",
            data:data,
            type: "POST",
            success:function(response){
                //Reload the patient table
                $("#tblAppointmentsDay").DataTable().ajax.reload();
                $("#formAppointment").data('bootstrapValidator').resetForm();
                $('#patientTabMenu a[href="#tabMain"]').tab('show');
            },
            error: function(data, status, error) {
                displayDangerAlert("error");
            }
        });
    }else{
        displayWarningAlert('No ha selccionado un paciente');
    }
    
}

function replaceNull(value){
    if(typeof value === 'undefined' || value === ''){
        return '';
    }else{
        return value;
    }
}