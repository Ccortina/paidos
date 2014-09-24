/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var filePath;

$(document).ready(function() {

    initializePatientDocumentsTable();
    initializeUploadForm();
    initializeModifyForm();
    
    $("#inputUFDate").val(moment().format("DD/MM/YYYY"));
    
    $('#modalUploadFile').modal({
      backdrop: 'static',
      keyboard: false
    });
    
    $('#inputUF').fileupload({
        url:'/demo/consultation/uploadFile',
        dataType: 'text',
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        },
        done:function (e, data){
            //console.log(e);
            if(data["result"] === "FAE"){
                displayDangerAlert("Este archivo ya existe");
            }else{
                filePath = data["result"];
                $('#modalUploadFileEnd').modal('show');
                $('#modalUploadFileStart').modal('hide');
            }
        
        },
        fail:function (e, data){
            //console.log( e);
            //console.log( data);
        }
    }); 
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

function initializeModifyForm(){
    $('#formModifyFile').bootstrapValidator({
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
        modifyFile();
    });
}

function uploadFile(){
    var data=[];

    data.push({name:"date",value:$("#inputUFDate").val()});   
    data.push({name:"category",value:$("#inputUFCategory").val()});
    data.push({name:"description",value:$("#inputUFDescription").val()});   
    data.push({name:"notes",value:$("#inputUFNotes").val()});
    data.push({name:"path",value:filePath});
    $.ajax({
        url:'/demo/consultation/uploadFileAdditionalInfo',
        data:data,
        dataType: 'text',
        type:'POST',
        success: function(data, textStatus, jqXHR)
        {
            displaySuccessAlert("El archivo a sido guardado correctamente");
            $("#tblConsultationPatientDocuments").DataTable().ajax.reload();
            $('#modalUploadFileEnd').modal('hide');
        },
        error: function(data, textStatus, jqXHR){
            displayDangerAlert("Ha habido un error en la operacion");
            console.log(data);
            console.log(jqXHR);
            console.log(textStatus);
        }
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
           {"data":"date"},
           {"data":"idDocumentCategory.category"},
           {"data":"description"}],
       "initComplete": function(settings,json){
            $('#tblConsultationPatientDocuments tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblConsultationPatientDocuments').DataTable();
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

function deleteDocument(){
    var row = $("#tblConsultationPatientDocuments").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
       bootbox.confirm("Esta seguro de borrar este archivo?", function(result) {
            if(result){
                $.ajax({
                    url:'/demo/consultation/deletePatientDocument',
                    dataType: 'text',
                    data:({idDocument:row["idDocuments"]}),
                    success:function(msg){
                        $("#tblConsultationPatientDocuments").DataTable().ajax.reload();
                    }
                });
            }
        });    
    }else{
        displayWarningAlert("No se ha seleccionado un documento");
    }
        
}

function openDocument(){
    var row = $("#tblConsultationPatientDocuments").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        var myWindow = window.open("/demo/consultation/openFile?idDocument="+row["idDocuments"], "Descargar archivo", "width=200, height=100");
    }else{
        displayWarningAlert("No se ha seleccionado un documento");
    }
}

function loadFileData(){
    var row = $("#tblConsultationPatientDocuments").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#inputMUFDate").val(moment(row["date"]).format("DD/MM/YYYY")); 
        $("#inputMUFDescription").val(row["description"]);  
        $("#inputMUFNotes").val(row["notes"]);
        $("#inputMUFid").val(row["idDocuments"]);
        
        $("option",$("#inputMUFCategory")).each(function(){
           if(this.value == row['idDocumentCategory']['idDocumentCategory']){this.selected=true;}
        });
        $('#modalModifyFile').modal('show');
    }else{
        displayWarningAlert("No se ha seleccionado un documento");
    }    
}

function modifyFile(){
    var data=[];

    data.push({name:"date",value:$("#inputUFDate").val()});   
    data.push({name:"category",value:$("#inputUFCategory").val()});
    data.push({name:"description",value:$("#inputUFDescription").val()});   
    data.push({name:"notes",value:$("#inputUFNotes").val()});

    $.ajax({
        url:'/demo/consultation/modifyFileAdditionalInfo',
        data:$("#formModifyFile").serialize(),
        dataType: 'text',
        type:'POST',
        success: function(data, textStatus, jqXHR)
        {
            displaySuccessAlert("El archivo a sido modificado correctamente");
            $("#tblConsultationPatientDocuments").DataTable().ajax.reload();
            $('#modalModifyFile').modal('hide');
        },
        error: function(data, textStatus, jqXHR){
            displayDangerAlert("Ha habido un error en la operacion");
            console.log(data);
            console.log(jqXHR);
            console.log(textStatus);
        }
    });    
}


