/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function initializeConsultationMotiveTableModifyApp(){
    
    $("#tblConsultationMotivesModifyApp").DataTable({
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

            $('#tblConsultationMotivesModifyApp tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblConsultationMotivesModifyApp').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $("#inputMotiveModifyApp").val('');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#inputMotiveModifyApp").val(table.row('.selected').data()['motive']);
                    $("#formModifyAppointment").data('bootstrapValidator').revalidateField('motive');
                }   
            });
        }
    });
}

function initiliazeAppointmentFormValidation(){
    $('#formModifyAppointment').bootstrapValidator({
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
    }).on('success.field.bv', '[name="date"]', function(e, data) {
        if ( !$.fn.DataTable.isDataTable( '#tblAppointmentsList' ) ) {
            initializeDoctorAppointments()
        }else{
            $("#tblAppointmentsList").DataTable().ajax.reload(); 
        }
    });
}

function loadAppointmentData(){
    var appointment = $('#tblAppointmentsList').DataTable().row('.selected').data();
    
    var inputs = $('#formModifyAppointment :input');
    var extra = $('#formModifyAppointmentAdditionalInfo :input');
    
    $("#inputIdPatientModifyApp").val(appointment['idPatient']['idPatient']);
    //Collect form data
    inputs.each(function() {
        if($(this).prop("name") === 'idStatus'){
            $("#selectStatusModifyApp option").each(function(){
                if($(this).val() === appointment['idStatus']['idStatus']){
                    $(this).attr('selected',true);
                }
            });
        }else if ($(this).prop("name") === 'idDoctor'){
            $("#inputDoctorModifyApp option").each(function(){
                if($(this).val() === appointment['idDoctor']['idUser']){
                    $(this).attr('selected',true);
                }
            });
        }else if($(this).prop("name") === 'immunization'){
            if($(this).val()){
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
    
    extra.each(function() {
        $(this).val(appointment[$(this).prop("name")]);   
    });
    
    //Reset form
    $("#formModifyAppointment").data('bootstrapValidator').resetForm();
    //Deselect appointment
    //$("#tblAppointmentsList").DataTable().$('tr.selected').removeClass('selected');
    $("#tblConsultationMotivesModifyApp").DataTable().$('tr.selected').removeClass('selected');
    
    
}

function modifyAppointment(){

    var idAppointment = $('#tblAppointmentsList').DataTable().row('.selected').data();
    
    if(checkNotUndefined(idAppointment)){
        var data = [];
        var inputs = $('#formModifyAppointment :input');
        var extraInputs = $('#formModifyAppointmentAdditionalInfo :input');

        //Collect form data
        inputs.each(function() {
            if(this.name === "inmunization"){
                data.push({name:this.name,value:$(this).prop('checked')});
            }else{
                data.push({name:this.name,value:$(this).val()});    
            }

        });
        extraInputs.each(function(){
           data.push({name:this.name,value:$(this).val()}); 
        });
        
        data.push({name:'idAppointment',value:idAppointment['idAppointment']});
        //console.log(data);
        //Send to controller
        $.ajax({
            url:"/demo/appointment/modifyAppointment",
            data:data,
            type: "POST",
            success:function(response){
                //Reload the patient table
                $("#tblAppointmentsList").DataTable().ajax.reload();
            },
            error: function(data, status, error) {
                displayDangerAlert("error");
            }
        });
    }else{
        displayWarningAlert('No se ha seleccionado una cita');
    }
    
}

