/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
    initializeLaboratoryTestsPatientData();
    
    initializeLaboratoryTestsList();
    
    initializeEditLaboratoryTestsList();
    
    initializeModifyForm();
    
    initializeAddForm();
    
    $('#formLaboratoryTestAdd [name=date]').val(moment().format("DD/MM/YYYY"));
});

function initializeLaboratoryTestsPatientData(){
    var patient = $("#consultationPatientId").val();
    
    $("#tblLaboratoryTestsPatientData").DataTable({
        "scrollY":"200px",
        "paging": false,
        "info":false,
        "ajax": {
            url:"/demo/consultation/getLaboratoryTestsPatientData",
            data:{"patient":patient}
        },
        "columns":[
            {"data":"date"},
            {"data":"idLaboratoryTest.laboratoryTest"},
            {"data":"result"}
            ],
        "language": {
            "emptyTable": "No hay informacion en la tabla."
        },
        "initComplete":function(settings,json){
            var table = $("#tblLaboratoryTestsPatientData").DataTable();
            $("#tblLaboratoryTestsPatientData").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }    
    });
    
}

function initializeLaboratoryTestsList(){
    
    $("#tblLaboratoryTests").DataTable({
        "scrollY":"200px",
        "paging": false,
        "info":false,
        "ajax":"/demo/consultation/getLaboratoryTests",
        "columns":[
            {"data":"idLaboratoryTest",
                "visible":false},
            {"data":"laboratoryTest"}],
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search":"Buscar"},
        "initComplete":function(settings,json){
            var table = $("#tblLaboratoryTests").DataTable();
            
            $("#tblLaboratoryTests").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#formLaboratoryTestAdd input[name=idLaboratoryTest]").val(table.row(this).data()["idLaboratoryTest"]);
                }
            });
        }
    });
}

function initializeEditLaboratoryTestsList(){
    
    $("#tblEditLaboratoryTests").DataTable({
        "scrollY":"200px",
        "paging": false,
        "info":false,
        "ajax":"/demo/consultation/getLaboratoryTests",
        "columns":[
            {"render":function(data,type,row){
                return ("-"+row["idLaboratoryTest"]+"-");
            },"visible":false},
            {"data":"laboratoryTest"}],
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search":"Buscar"},
        "initComplete":function(settings,json){
            var table = $("#tblEditLaboratoryTests").DataTable();
            
            $("#tblEditLaboratoryTests").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#formLaboratoryTestEdit input[name=idLaboratoryTest]").val(table.row(this).data()["idLaboratoryTest"]).trigger('change');
                }
            });
        }
    });
}

function initializeAddForm(){
    $('#formLaboratoryTestAdd').bootstrapValidator({
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
            result: {
                validators: {
                    notEmpty: {
                        message: 'Este campo ni puede estar vacio'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveAddLaboratoryTestResult();
    });
}

function saveAddLaboratoryTestResult(){
    $.ajax({
        url:"/demo/consultation/saveLaboratoryTestResult",
        data: $('#formLaboratoryTestAdd').serializeObject(),
        type:"POST",
        success:function(response){
            $("#tblLaboratoryTestsPatientData").DataTable().ajax.reload();
            displaySuccessAlert("La operacion se ha realizado correctamente");
            $("#formLaboratoryTestAdd").data('bootstrapValidator').resetForm();
            clearFormInputTextFields("formLaboratoryTestAdd");
            $("#modalLaboratoryTestAdd").modal('hide');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido la operacion: "+response);
        }
    });
}

function deleteLabTest(){
    var row = $("#tblLaboratoryTestsPatientData").DataTable().row('.selected').data()["idLaboratoryTestResult"];
    
    if(checkNotUndefined(row)){
        bootbox.confirm("Esta seguro de borrar este estudio?", function(result) {
                if(result){ 
                    $.ajax({
                        url:"/demo/consultation/deleteLaboratoryTestResult",
                        data: {'idResult':row},
                        type:"POST",
                        success:function(response){
                            $("#tblLaboratoryTestsPatientData").DataTable().ajax.reload();
                        }
                    });
                }
        });
    }else{
        displayWarningAlert("No se ha seleccionado un estudio");
    }
    
}

function loadModifyLabTest(){
    var row = $("#tblLaboratoryTestsPatientData").DataTable().row('.selected').data();
    if(checkNotUndefined(row)){
        $("#formLaboratoryTestEdit [name=date]").val(moment(row["date"]).format("DD/MM/YYYY"));
        $("#formLaboratoryTestEdit [name=result]").val(row["result"]);
        $("#tblEditLaboratoryTests").DataTable().column( 0 ).search("-"+row["idLaboratoryTest"]["idLaboratoryTest"]+"-").draw();
    }else{
        displayWarningAlert("No se ha seleccionado un estudio");
    }
    
    
    $("#modalModifyLabTest").modal('show');
}

function initializeModifyForm(){
    $('#formLaboratoryTestEdit').bootstrapValidator({
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
            result: {
                validators: {
                    notEmpty: {
                        message: 'Este campo ni puede estar vacio'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveModify();
    });
}

function saveModify(){
    var row = $('#tblLaboratoryTestsPatientData').DataTable().row('.selected').data();
    var data = $('#formLaboratoryTestEdit').serializeArray();
    data.push({name:"idLaboratoryTestResult",value:row["idLaboratoryTestResult"]});
    
    $.ajax({
        url:"/demo/consultation/editLaboratoryTestResult",
        data: data,
        type:"POST",
        success:function(response){
            $("#formLaboratoryTestEdit").data('bootstrapValidator').resetForm();
            clearFormInputTextFields("formLaboratoryTestEdit");
            displaySuccessAlert("La operacion se ha realizado correctamente");
            $("#tblLaboratoryTestsPatientData").DataTable().ajax.reload();
            $("#modalModifyLabTest").modal('hide');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido la operacion: "+response);
        }
    });
}