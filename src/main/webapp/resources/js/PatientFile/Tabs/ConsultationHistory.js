/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $('.inputDecimal').inputmask('Regex',{regex:"[0-9]+(\.[0-9][0-9]?)?"});
    intializeNewConsultationForm();
    intializeModifyConsultationForm();
    
    initializeConsultationHistoryTable();
    $('a[href="#consultas"]').on('shown.bs.tab', function (e){
        $('#tblConsultationHistory').DataTable().columns.adjust().draw();
    });
    
    $(".partNBMI").on("change",function(){
        var weight = $("#inputCHNWeight").val();
        var size = $("#inputCHNSize").val();
        if(checkNotEmptyString(weight) && checkNotEmptyString(size)){
            $("#inputCHNBMI").val((weight/size/size)*10000);
        }
    });
    
    $(".partMBMI").on("change",function(){
        var weight = $("#inputCHMWeight").val();
        var size = $("#inputCHMSize").val();
        if(checkNotEmptyString(weight) && checkNotEmptyString(size)){
            $("#inputCHMBMI").val((weight/size/size)*10000);
        }
    });
    
    //Reset forms on modal show
    $("#modalModifyConsultation").on('show.bs.modal',function(){
        $("#formModifyConsultation").data('bootstrapValidator').resetForm();
    });
    
    $("#modalNewConsultation").on('show.bs.modal',function(){
        $("#formNewConsultation").data('bootstrapValidator').resetForm();
    });
});

function initializeConsultationHistoryTable(){
    $('#tblConsultationHistory').DataTable({
        "scrollY": "150px",
        "order":[0,'desc'],
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            url:"/demo/patients/getConsultationsByPatientFile"
        },
        "columns":[
            {"render":function(data,row,full){
                    return (moment(full['idAppointment']['date']).format('DD/MM/YYYY'));
            }},
            {"data":"abstract1"},
            {"data":"idAppointment.idStatus.status"}
        ],
        "initComplete":function(settings,json){

            $('#tblConsultationHistory tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblConsultationHistory').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    loadConsultationDetails();
                }   
            });
        },
        "createdRow": function( row, data, dataIndex ) {
            switch(data.idAppointment.idStatus.status){
                case 'Completa':
                    $(row).css({'background-color':'#5BAA3A'});//vpNormal
                    $(row).addClass("vpNormal");
                    break;
                case 'Cancelada':
                    $(row).css({'background-color':'#E04747'});
                    $(row).addClass("vpExpired");
                    break;    
            }
        }
    });
}

function loadConsultationDetails(){
    var row = $('#tblConsultationHistory').DataTable().row('.selected').data();
    
    $("#inputCHAge").val(getAge(row["idAppointment"]["idPatient"]["birthday"],row["idAppointment"]["date"]));
    $("#inputCHWeight").val(row["weigth"]);
    $("#inputCHSize").val(row["size"]);
    $("#inputCHPC").val(row["pc"]);
    $("#inputCHTA").val(row["taAverage"]);
    $("#inputCHTemperature").val(row["temperature"]);
    $("option",$("#inputCHType")).each(function(){
       if(this.value == row['type']['idConsultationType']){this.selected=true;}
    });
    $("#inputCHPEEA").val(row["peea"]);
    $("#inputCHEF").val(row["ef"]);
    $("#inputCHAbstract").val(row["abstract1"]);
    $("#inputCHPrescription").val(row["prescription"]);
}

function modifyConsultation(){
    
    var row = $('#tblConsultationHistory').DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#inputCHMMotive").val(row["idAppointment"]["motive"]);
        $("#inputCHMAge").val(getAge(row["idAppointment"]["idPatient"]["birthday"]));
        $("#inputCHMBirthday").val(row["idAppointment"]["idPatient"]["birthday"]);
        $("#inputCHMWeight").val(row["weigth"]);
        $("#inputCHMSize").val(row["size"]);
        $("#inputCHMBMI").val(row["bmi"]);
        $("#inputCHMPC").val(row["pc"]);
        $("#inputCHMTA").val(row["ta"]);
        $("#inputCHMTA2").val(row["ta2"]);
        $("#inputCHMTAverage").val(row["taAverage"]);
        $("#inputCHMTemperature").val(row["temperature"]);
        $("option",$("#inputCHMType")).each(function(){
           if(this.value == row['type']['idConsultationType']){this.selected=true;}
        });
        $("#inputCHMPEEA").val(row["peea"]);
        $("#inputCHMEF").val(row["ef"]);
        $("#inputCHMAbstract").val(row["abstract1"]);
        $("#inputCHMPrescription").val(row["prescription"]);
        $("#inputCHMPN").val(row["prescriptionNumber"]);
        $("#inputCHMDate").val(moment(row["idAppointment"]["date"]).format("DD/MM/YYYY"));
        $("#inputCHMPatient").val(row["idAppointment"]["idPatient"]["firstName"]+" "+
                row["idAppointment"]["idPatient"]["fatherLastName"]+" "+
                row["idAppointment"]["idPatient"]["motherLastName"]);
        $("#modalModifyConsultation").modal("show");
    }else{
        displayWarningAlert("No se ha seleccionado una consulta");
    }
}

function intializeNewConsultationForm(){
    var birthday = $("#inputCHNBirthday").val();
    console.log(birthday);
    $("#inputCHNBirthday").val(moment(birthday).format("DD/MM/YYYY"));
    $("#inputCHNDate").val(moment().format("DD/MM/YYYY"));
    
    $('#formNewConsultation').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            date: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'No es una fecha valida dd/mm/aaaa'
                    }
                }
            },
            peea: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            abstract: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveNewConsultation();
    });
}

function intializeModifyConsultationForm(){

    $('#formModifyConsultation').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            date: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'No es una fecha valida dd/mm/aaaa'
                    }
                }
            },
            peea: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            abstract: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveModifyConsultation();
    });
}

function saveNewConsultation(){

    $.ajax({
            url:"/demo/patients/saveNewConsultation",
            data:$('#formNewConsultation').serialize(),
            type: "POST",
            success:function(response){
                //Reload the patient table
                displaySuccessAlert("Se ha guardado correctamente");
                $("#tblConsultationHistory").DataTable().ajax.reload();
                clearFormInputTextFields("formNewConsultation");
                $("#modalNewConsultation").modal("hide");
            },
            error: function(data, status, error) {
                displayDangerAlert("Error"+error);
                console.error(error);
            }
        });
}

function saveModifyConsultation(){
    var row = $('#tblConsultationHistory').DataTable().row('.selected').data();
    var data = $('#formModifyConsultation').serializeArray();
    data.push({name:"idConsultation",value:row["idConsultation"]});
    
    $.ajax({
            url:"/demo/patients/saveModifyConsultation",
            data:data,
            type: "POST",
            success:function(response){
                //Reload the patient table
                displaySuccessAlert("Se ha guardado correctamente");
                $("#tblConsultationHistory").DataTable().ajax.reload();
                clearFormInputTextFields("formModifyConsultation");
                $("#modalModifyConsultation").modal("hide");
            },
            error: function(data, status, error) {
                displayDangerAlert("Error"+error);
                console.error(error);
            }
        });
}

function getAge(fromdate, todate){
    if(todate) todate= new Date(todate);
    else todate= new Date();

    var age= [], fromdate= new Date(fromdate),
    y= [todate.getFullYear(), fromdate.getFullYear()],
    ydiff= y[0]-y[1],
    m= [todate.getMonth(), fromdate.getMonth()],
    mdiff= m[0]-m[1],
    d= [todate.getDate(), fromdate.getDate()],
    ddiff= d[0]-d[1];

    if(mdiff < 0 || (mdiff=== 0 && ddiff<0))--ydiff;
    if(mdiff<0) mdiff+= 12;
    if(ddiff<0){
        fromdate.setMonth(m[1]+1, 0);
        ddiff= fromdate.getDate()-d[1]+d[0];
        --mdiff;
    }
    if(mdiff> 0){ 
        age.push(mdiff+ ' M ');
    }else{
        age.push('0 M ');
    }
    if(ddiff> 0){
        age.push(ddiff+ ' D ');
    }else{
        age.push(ddiff+ '0 D ');
    }
    if(ydiff> 0){
        age.push(ydiff+ ' A ');
    }else{
        age.push('0 A ');
    }
      
    return age.join('');
}

