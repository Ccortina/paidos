/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeConsultationMotiveNewAppTable();
    initiliazeNewAppointmentFormValidation();
    initializeNewAppointmentPatientsTable();
    
    $('a[href="#tabNewAppointment"]').on('shown.bs.tab', function (e) {
        $("#inputDatNewApp").val(moment().format("DD/MM/YYYY"));
        $('#formNewAppointment').data('bootstrapValidator').validateField('date');
        $('#tblPatientsNewApp').DataTable().columns.adjust().draw();
    });
});

function initializeConsultationMotiveNewAppTable(){
    
    $("#tblConsultationMotivesNewApp").DataTable({
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

            $('#tblConsultationMotivesNewApp tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblConsultationMotivesNewApp').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                    $("#inputMotivNewApp").val('');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#inputMotivNewApp").val(table.row('.selected').data()['motive']);
                    $("#formNewAppointment").data('bootstrapValidator').revalidateField('motive');
                }   
            });
        }
    });
}

function initiliazeNewAppointmentFormValidation(){
    $('#formNewAppointment').bootstrapValidator({
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
        saveNewAppointment();
    }).on('success.field.bv', '[name="date"]', function(e, data) {    
        if ( !$.fn.DataTable.isDataTable( '#tblAppointmentsListNewApp' ) ) {
            initializeAppointmentDayTable();
        }else{
            $("#tblAppointmentsListNewApp").DataTable().ajax.reload(); 
        }
    });
}

function initializeAppointmentDayTable(){
  
    $("#tblAppointmentsListNewApp").DataTable({
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
                start:function(){return ($("#inputDatNewApp").val());}}
            },
        "columns":[
            {"data":"startTime"},
            {"render":function(data,row,full){
                    return (full['idPatient']['firstName']+" "+replaceNull(full['idPatient']['secondRow'])
                            +" "+full['idPatient']['fatherLastName']+" "+replaceNull(full['idPatient']['motherLastName']));
            }},
            {"data":"motive"},
            {"data":"idStatus.status"}
        ]
    });
}

function initializeNewAppointmentPatientsTable(){
    $('#tblPatientsNewApp').DataTable({
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/appointment/getPatients",
        "columns":[
            {"render":function(data,type,row){
                    return (row['firstName']+" "+replaceNull(row['secondRow'])
                            +" "+row['fatherLastName']+" "+replaceNull(row['motherLastName']));
            }},
            {"data":"birthday"},
            {"data":"sex.gender"}
        ],
        "initComplete":function(settings,json){
            $('#tblPatientsNewApp tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblPatientsNewApp').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#inputIdPatientNewApp").val(table.row('.selected').data()['idPatient']);
                }   
            });
        }
    });
}

function saveNewAppointment(){

    var data = [];
    var inputs = $('#formNewAppointment :input');
    var extraInputs = $('#formNewAppointmentAdditionalInfo :input');
    
    if(checkNotUndefined($("#tblPatientsNewApp").DataTable().row('.selected').data())){
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

        //Send to controller
        $.ajax({
            url:"/demo/appointment/saveAppointment",
            data:data,
            type: "POST",
            success:function(response){
                //Reload the patient table
                $("#inputTimeNewApp").val('');
                $("#inputMotivNewApp").val('');
                
                $("#tblAppointmentsListNewApp").DataTable().ajax.reload();
                $("#tblAppointmentsList").DataTable().ajax.reload();
                $("#formNewAppointment").data('bootstrapValidator').resetForm();
                $('#tblPatientsNewApp').DataTable().$('tr.selected').removeClass('selected');
                $('#tblConsultationMotivesNewApp').DataTable().$('tr.selected').removeClass('selected');
            },
            error: function(data, status, error) {
                displayDangerAlert("error");
            }
        });
    }else{   
        displayWarningAlert("No se ha seleccionado un paciente");
        $("#formNewAppointment").data('bootstrapValidator').resetForm();
    }
    
}

function displayDangerAlert(message){
    var box = bootbox.alert("<center><strong>Advertencia!</strong>"+message+"</center>");
    box.find('.modal-content').css({'color': '#a94442','background-color': '#f2dede','border-color': '#ebccd1'});
    window.setTimeout(function(){bootbox.hideAll();}, 2000);
}

function displayWarningAlert(message){
    var box = bootbox.alert("<center><strong>Advertencia! </strong>"+message+"</center>");
    box.find('.modal-content').css({'color':'#8a6d3b','background-color':'#fcf8e3','border-color':'#faebcc'});
    window.setTimeout(function(){bootbox.hideAll();}, 2000);
}
