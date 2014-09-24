/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeTableMeasuresCatalog();
    initializeNewMeasureForm();
    initializeTableMeasureConsultation();
    
    initializeAddMeasureForm();
    
});

function initializeTableMeasureConsultation(){
    $("#tblMeasuresConsultation").DataTable({
        "scrollY":"200px",
        "paging": false,
        "searching":false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla."},
        "columns":[
            null,
            null,
            null,
            {"visible":false},
            {"visible":false}
        ],
        "initComplete":function(){
            var table = $("#tblMeasuresConsultation").DataTable();
            
            $("#tblMeasuresConsultation").on( 'click', 'tr', function (e){
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

function initializeTableMeasuresCatalog(){
    $("#tblMeasureCatalog").DataTable({
        "scrollY":"100px",
        "paging": false,
        "searching":false,
        "info":false,
        "ajax":"/demo/consultation/getMeasuresCatalog",
        "columns":[
            {"data":"measure"}
            ],
        "language": {
            "emptyTable": "No hay informacion en la tabla."},
        "initComplete":function(settings,json){
            var table = $("#tblMeasureCatalog").DataTable();
            
            $("#tblMeasureCatalog").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#formMeasuresAdd input[name=units]").val(table.row(this).data()["units"]);
                }
            });
        }
    });
}

function initializeAddMeasureForm(){
    $('#formMeasuresAdd').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            measureValue: {
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
        saveMeasureValue();
    });
}

function saveMeasureValue(){
    var row = $("#tblMeasureCatalog").DataTable().row('.selected').data(); 
    
    if(!checkNotUndefined(row)){
        displayDangerAlert(" No se ha seleccionado una medida");
        $("#formMeasuresAdd").data('bootstrapValidator').resetForm();
    }else{
        $("#formMeasuresAdd").data('bootstrapValidator').resetForm();
        $("#tblMeasuresConsultation").DataTable().row.add([
            row["measure"],
            $("#formMeasuresAdd input[name=measureValue]").val(),
            row["units"],
            row["idMeasures"],
            row["includePrescription"]
        ]).draw();
        $("#modalMeasuresAdd").modal('hide');
        
    }
}

function deleteMeasureConsultationRow(){
    var row = $("#tblMeasuresConsultation").DataTable().row('.selected').data();
    if(!checkNotUndefined(row)){
        displayDangerAlert(" No se ha seleccionado una medida para elminar.");
    }else{
        var index = $("#tblMeasuresConsultation").DataTable().row('.selected').index();
        $("#tblMeasuresConsultation").DataTable().row(index).remove().draw();
    }
}

function initializeNewMeasureForm(){
        
    $("#formMeasureNew").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            itemName: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            unit: {
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
        saveNewMeasure();
    });
}

function saveNewMeasure(){
    var data = [];
    data.push({name:"active",value:$("#inputNewMeasureActive").prop('checked')});
    data.push({name:"itemName",value:$("#inputNewMeasure").val()});
    data.push({name:"unit",value:$("#inputNewMeasureUnit").val()});
    data.push({name:"include",value:$("#inputNewMeasureInclude").prop('checked')});
    
    $.ajax({
        url:"/demo/consultation/saveNewMeasure",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("La operacion fue exitosa");
            $("#tblMeasureCatalog").DataTable().ajax.reload();
            clearFormInputTextFields("formMeasureNew");
            $("#modalMeasuresInnerModal").modal('hide');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
    
}