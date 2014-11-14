/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeThirdPartyPayersTable();
    initializeNewTPPForm();
    initializeEditTPPForm();
});

function newTPP(){
    $('#mainTabMenu a[href="#tabNew"]').tab('show');
}

function modifyTPP(){
    $('#mainTabMenu a[href="#tabModify"]').tab('show');
    loadTPP();
}

function initializeThirdPartyPayersTable(){
    
    $('#tblThirdPartyPayers').DataTable({
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info": false,
        "searching": false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        ajax:"/demo/income/getAllThirdPartyPayer",
        "columns":[
            {"data":"name"},
            {"data":"rfc"}
        ],
        "initComplete":function(settings,json){

            $('#tblThirdPartyPayers tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblThirdPartyPayers').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }   
            });
        },
        "createdRow":function(row, data, dataIndex){
            if(data.active !== 1){
                $(row).css({"background-color":"#FDFD96"});
                $(row).addClass("vpSuspended");
            }
        }
    });
}

function initializeNewTPPForm(){
    $("#formNewTPP").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            name:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            street:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            colony:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            city:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            country:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            state:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            zip:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            rfc:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    },
                    regexp:{
                        regexp: "^([A-ZÑa-zñ\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Za-z|\d]{3})$",
                        message:"El RFC no es valido"
                    }
                }
            }    
        },
        submitButtons: 'button[type="submit"]'
    })
    .on('success.form.bv', function(e) {
        e.preventDefault();
        saveNewTPP();
    });
    
}

function initializeEditTPPForm(){
    $("#formEditTPP").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            name:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            street:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            colony:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            city:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            country:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            state:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            zip:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            rfc:{
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    },
                    regexp:{
                        regexp: "^([A-ZÑa-zñ\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Za-z|\d]{3})$",
                        message:"El RFC no es valido"
                    }
                }
            }    
        },
        submitButtons: 'button[type="submit"]'
    })
    .on('success.form.bv', function(e) {
        e.preventDefault();
        saveModifyTPP();
    });
    
}

function loadTPP(){
    var row = $("#tblThirdPartyPayers").DataTable().row(".selected").data();
    
    if( checkNotUndefined(row) ){
        $("#formEditTPP :input").each(function(index,value){
            $(this).val(row[$(this).prop("name")]);
        });
        
        $("#formEditTPP [name='active']").prop("checked",parseInt(row["active"]) === 1);
    }else{
        displayWarningAlert("No ha seleccionado un Tercer pagador");
    }
}

function saveNewTPP(){
    $.ajax({
        url:"/demo/income/saveNewTPP",
        type: 'POST',
        data:$("#formNewTPP").serialize(),
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado correctamente");
            $("#tblThirdPartyPayers").DataTable().ajax.reload();
            $("#formNewTPP").data('bootstrapValidator').resetForm();
            clearFormInputTextFields("formNewTPP");
            $('#mainTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function saveModifyTPP(){
    $.ajax({
        url:"/demo/income/saveModifyTPP",
        type: 'POST',
        data:$("#formEditTPP").serializeArray(),
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha modificado correctamente");
            $("#tblThirdPartyPayers").DataTable().ajax.reload();
            $("#formEditTPP").data('bootstrapValidator').resetForm();
            clearFormInputTextFields("formEditTPP");
            $('#mainTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function suspendTPP(){
    var row = $("#tblThirdPartyPayers").DataTable().row(".selected").data();
    console.log(row["idThirdPartyPayer"]);
    var data =[];
    
    if( checkNotUndefined(row) ){
        bootbox.confirm("Desea suspender a "+ row['name'] +"?", function(result) {
            if(result){
                data.push({name:"id",value:row["idThirdPartyPayer"]});
                $.ajax({
                    url:"/demo/income/suspendTPP",
                    type: 'POST',
                    data:data,
                    success:function(response,textStatus,jqXHR){
                        displaySuccessAlert("Se ha suspendido correctamente");
                        $("#tblThirdPartyPayers").DataTable().ajax.reload();
                    },
                    error:function(response){
                        displayDangerAlert("Ha ocurrido un error: "+response);
                    }
                });
            }
        });
    }else{
        displayWarningAlert("No ha seleccionado un Tercer pagador");
    }
}