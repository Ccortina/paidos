/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeAppointmentHistoryTable();
    initializeConsultationMotiveTable();
    initiliazeAppointmentFormValidation();
    
    $('a[href="#citas"]').on('shown.bs.tab', function (e) {
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
            url:"/demo/patients/getAppointmentsFileByPatient"
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

            $('#tblAppointmentsHistory tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblAppointmentsHistory').DataTable();
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
                    $(row).addClass("vpNormal");
                    break;
                case 'Cancelada':
                    $(row).css({'background-color':'#E04747'});
                    $(row).addClass("vpExpired");
                    break;    
            }
        },
    });
}

function loadModifyAppointment(){
    var appointment = $('#tblAppointmentsHistory').DataTable().row('.selected').data();
    if(checkNotUndefined(appointment)){

        var inputs = $('#formAppointment :input');
        
        $("#inputIdPatientModifyApp").val(appointment['idPatient']['idPatient']);
        //Collect form data
        inputs.each(function() {
            if($(this).prop("name") === 'idStatus'){
                $("#selectStatusModifyApp option").each(function(){
                    if(parseInt($(this).val()) === appointment['idStatus']['idAppointmentStatus']){
                        $(this).prop('selected',true);
                    }
                });
            }else if ($(this).prop("name") == 'idDoctor'){
                $("#inputDoctorModifyApp option").each(function(){
                    if(parseInt($(this).val()) === appointment['idDoctor']['idUser']){
                        $(this).prop('selected',true);
                    }
                });
            }else if($(this).prop("name") === "immunization"){
                if(parseInt(appointment["immunization"]) === 1){
                    $(this).prop('checked',true);
                }else{
                    $(this).prop('checked',false);
                }
            }else if($(this).prop("name") === 'date'){
                $(this).val(moment(appointment[$(this).prop("name")]).format('DD/MM/YYYY'));
            }else if($(this).prop("name") === 'idPatient'){
                $(this).val(appointment['idPatient']['idPatient']);
            }else if($(this).prop("name") === 'startTime'){
                var timeSplit = appointment[$(this).prop("name")].split(':');
                $(this).val(timeSplit[0]+":"+timeSplit[1]);
            }else{
                $(this).val(appointment[$(this).prop("name")]);
            }
        });

        //Reset form
        $("#formAppointment").data('bootstrapValidator').resetForm();
        $("#modalModifyAppointment").modal('show');
    }else{
        displayWarningAlert("No se ha seleccionado una cita");
    }
    
}

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
        modifyAppointment();
    });
}

function modifyAppointment(){

    var idAppointment = $('#tblAppointmentsHistory').DataTable().row('.selected').data();
    
    if(checkNotUndefined(idAppointment)){
        var data = [];
        var inputs = $('#formAppointment :input');

        //Collect form data
        inputs.each(function() {
            if(this.name === "inmunization"){
                data.push({name:this.name,value:$(this).prop('checked')});
            }else{
                data.push({name:this.name,value:$(this).val()});    
            }

        });
        
        data.push({name:'idAppointment',value:idAppointment['idAppointment']});

        //Send to controller
        $.ajax({
            url:"/demo/patients/modifyAppointment",
            data:data,
            type: "POST",
            success:function(response){
                //Reload the patient table
                displaySuccessAlert("la operacion se ha realizado con exito");
                $("#tblAppointmentsHistory").DataTable().ajax.reload();
                $("#modalModifyAppointment").modal('hide');
            },
            error: function(data, status, error) {
                displayDangerAlert("error");
            }
        });
    }else{
        displayWarningAlert('No se ha seleccionado una cita');
    }
    
}

function deleteAllAppointments(){
    
    bootbox.confirm("Esta seguro de realizar esta operacion?", function(result) {
        if(result){
            $.ajax({
                url:"/demo/patients/deleteSystemProgrammedAppointments",
                type: "POST",
                success:function(response){
                    //Reload the patient table
                    displaySuccessAlert("la operacion se ha realizado con exito");
                    $("#tblAppointmentsHistory").DataTable().ajax.reload();
                },
                error: function(data, status, error) {
                    displayDangerAlert("error");
                }
            });
        }
    });
}