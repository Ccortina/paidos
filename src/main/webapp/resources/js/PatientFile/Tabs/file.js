/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {

    initializePatientDocumentsTable();
    
    uploadFile();    
});

function initializeUploadForm(){
    $('#formUploadFile').bootstrapValidator({
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
            description: {
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
        uploadFile();
    });
}

function uploadFile(){
    
    $("#formUploadFile").on('submit',function(e){
       var oMyForm = new FormData();
       oMyForm.append("file",file2.files[0]);
        
       $.ajax({
            url:'/demo/patients/uploadFile',
            data:oMyForm,
            dataType:'text',
            processData: false,
            contentType: false,
            cache: false,
            type:'POST',
            success: function(data, textStatus, jqXHR)
            {
                $("#tblConsultationPatientDocuments").DataTable().ajax.reload();
            }
        });
        
        e.preventDefault();
    });
}

function initializePatientDocumentsTable(){
    
    $("#tblConsultationPatientDocuments").DataTable({
       "bSort":false,
       "searching":false,
       "scrollY": "200px",
       "scrollCollapse": true,
       "paging": false,
       "info":false,
       "ajax":"/demo/patients/getPatientDocument",
       "columns":[
           {"data":"name"}],
       "initComplete": function(settings,json){
            $('#tblConsultationPatientDocuments tbody tr').dblclick(function(e){
                openDocument($("#tblConsultationPatientDocuments").DataTable().row(this).data()["name"]);
                
            });
       }
    });
}

function deleteDocument(button){
    var table = $("#tblConsultationPatientDocuments").DataTable();
    var file = table.row($(button).parent().parent()[0]).data()["name"];
    $.ajax({
        url:'/demo/patients/deletePatientDocument',
        data:{ 'file':file},
        success:function(msg){
            table.ajax.reload();
        }
    });
}

function openDocument(file){
    $.ajax({
        url:'/demo/patients/openPatientDocument',
        data:{ 'file':file}
    });
}


