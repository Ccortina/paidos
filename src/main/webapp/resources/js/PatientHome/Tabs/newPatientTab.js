/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeRelativesTable();
    initializeSelectedRelativesTable();
    
    initializeNewPatientFormValidation();
    
    $('a[href="#tabNew"]').on('shown.bs.tab', function (e) {
        $("#tblRelatives").DataTable().columns.adjust().draw();
    });
});

function initializeRelativesTable(){
    $('#tblRelatives').DataTable({
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/patients/getAllRelatives",
        "columns":[
            {"data":"fatherLastName"},
            {"data":"motherLastName"},
            {"data":"firstName"},
            {"data":"rfc"},
            {"data":"homePhone"}],
        "initComplete": function(settings, json) {
            $('#tblRelatives tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblRelatives').DataTable();
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

function initializeSelectedRelativesTable(){
    $('#tblSelectedRelatives').DataTable({
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "columns":[
            {"data":"fatherLastName"},
            {"data":"motherLastName"},
            {"data":"firstName"},
            {"data":"rfc"},
            {"data":"relationship"}
        ],
        "initComplete": function(settings, json) {
            $('#tblSelectedRelatives tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblSelectedRelatives').DataTable();
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

function initializeNewPatientFormValidation(){
    $('#formNewPatient').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            fName: {
                validators: {
                    notEmpty: {
                        message: 'El nombre del paciente no puede estar vacio'
                    }
                }
            },
            flName: {
                validators: {
                    notEmpty: {
                        message: 'El apellido paterno del paciente no puede estar vacio'
                    }
                }
            },
            birthday: {
                validators: {
                    notEmpty: {
                        message: 'La fecha de nacimiento no puede estar vacia'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'No es una fecha valida'
                    }
                }
            }    
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        addNewPatient();
    });
}

function addPatientRelativeRelationship(){
    var data = $("#tblRelatives").DataTable().row('.selected').data();
    $("#tblRelatives").DataTable().row('.selected').remove().draw();
    data.idRelationship = $("#selectNewPatientRRelationship").val();
    data.relationship = getSelectedText("selectNewPatientRRelationship");

    $('#tblSelectedRelatives').DataTable().row.add(data).draw();
}

function addNewPatient(){
    var data = [];
    var inputs = $('#formNewPatient :input');
    //Collect form data
    inputs.each(function() {
        data.push({name:this.name,value:$(this).val()});
    });
    
    //Collect relative and relationship type
    var count = 0;
    $('#tblSelectedRelatives').DataTable().rows().data().each(function(val,rIndex){
        count++;
        data.push({name:"idRelative"+count,value:val['idRelative']});
        data.push({name:"idRelationship"+count,value:val['idRelationship']});  
    });
    data.push({name:"relativesCounter",value:count});
    //Send to controller
    $.ajax({
        url:"/demo/patients/addNewPatient",
        data:data,
        type: "POST",
        success:function(response){
            //Reload the patient table
            $("#tblPatients").DataTable().ajax.reload();
            $("#formNewPatient").data('bootstrapValidator').resetForm();
            $('#formNewPatient :input[type=text]').each(function(){
                $(this).val('');
            });
            $('#patientTabMenu a[href="#tabMain"]').tab('show');
        },
        error: function(data, status, error) {
            displayDangerAlert("error");
        }
    });
    
}

function deletePatientRelativeRelationship(){
    //return relative to previous table
    var data =$("#tblSelectedRelatives").DataTable().row('.selected').data();
    $('#tblRelatives').DataTable().row.add(data).draw();
    $("#tblSelectedRelatives").DataTable().row('.selected').remove().draw();
    
}