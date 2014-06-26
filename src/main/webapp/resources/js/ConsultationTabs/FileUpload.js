/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

    initializePatientDocumentsTable();
    
    uploadFile();    
});

function uploadFile(){
    
    $("#formUploadFile").on('submit',function(e){
       var oMyForm = new FormData();
       oMyForm.append("file",file2.files[0]);
        
       $.ajax({
            url:'/demo/consultation/uploadFile',
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
       "ajax":"/demo/consultation/getPatientDocument",
       "columns":[
           {"data":"name"}
       ],
       "initComplete": function(settings,json){
            var table = $('#tblConsultationPatientDocuments').DataTable();
            
            $('#tblConsultationPatientDocuments tbody').on( 'click', 'tr', function (e) {
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

function deleteFile(){
    var row = $("#tblConsultationPatientDocuments").DataTable().row('.selected').data(); 

    if(typeof row == 'undefined'){
        displayDangerAlert("No se ha seleccionado un documento para eliminar.");
    }else{
        $.ajax({
            url:'/demo/consultation/deletePatientDocument',
            data:{ 'file':row['name']},
            success:function(msg){
                $("#tblConsultationPatientDocuments").DataTable().ajax.reload();
            },
            error:function(jqXHR,textStatus,errorThrown){
                displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
            }
                    
        });
    }
}

function openFile(){
    var row = $("#tblConsultationPatientDocuments").DataTable().row('.selected').data(); 

    if(typeof row == 'undefined'){
        displayDangerAlert("No se ha seleccionado un documento para abrir.");
    }else{
        $.ajax({
            url:'/demo/consultation/openPatientDocument',
            data:{ 'file':row['name']},
            success:function(msg){
                $("#tblConsultationPatientDocuments").DataTable().ajax.reload();
            },
            error:function(jqXHR,textStatus,errorThrown){
                displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
            }
        });
    }
}

