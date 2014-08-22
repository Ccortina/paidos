/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    moment.locale('es');
    
    initializeMainTable();
    initializeNewItemForm();
    initializeModifyItemForm();
    
    /*$('.inputDate').inputmask("dd/mm/yyyy",{"oncleared": function(){
                        $("#"+$(this).closest("form").attr('id')).data('bootstrapValidator').revalidateField($(this).attr('name'));
                    }});*/
});

function newItem(){
    $('#mainTabMenu a[href="#tabNew"]').tab('show');
}

function initializeMainTable(){
    $("#tblMain").DataTable({
        "ordering":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/catalogs/getHolyday",
        "columns":[
            {"render":function(data,type,row){ 
                return moment(row["date"]).format("DD , MMMM , YYYY");
            }},
            {"data":"holyday"}
        ],
        "initComplete":function(settings,json){
            $('#tblMain tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblMain').DataTable();
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

function initializeNewItemForm(){
        
    $("#formNewItem").bootstrapValidator({
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
            itemDate:{
                validators:{
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'Formato invalido dd/mm/aaaa'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }   
                }  
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveNewItem();
    });
}

function initializeModifyItemForm(){   
    $("#formModifyItem").bootstrapValidator({
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
            itemDate:{
                validators:{
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'Formato invalido dd/mm/aaaa'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }  
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveModifyItem();
    });
}

function cancel(){
    $('#mainTabMenu a[href="#tabMain"]').tab('show');
}

function saveNewItem(){
    var data = [];
    data.push({name:"itemDate",value:$("#inputNewItemDate").val()});
    data.push({name:"itemName",value:$("#inputNewItem").val()});
    
    $.ajax({
        url:"/demo/catalogs/saveNewHolyday",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha agregado correctamente");
            $("#tblMain").DataTable().ajax.reload();
            $('#mainTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function modifyItem(){
    var row = $("#tblMain").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#inputModifyItem").val(row["holyday"]);
        $("#inputIdItem").val(row["idHolydays"]);
        $("#inputModifyItemDate").val(moment(row["date"]).format("DD/MM/YYYY"));
        
        $('#mainTabMenu a[href="#tabModify"]').tab('show');
    }else{
        displayWarningAlert("No ha seleccionado un tipo de nacimiento");
    }
}

function saveModifyItem(){
    var data = [];
    data.push({name:"itemDate",value:$("#inputModifyItemDate").val()});
    data.push({name:"itemName",value:$("#inputModifyItem").val()});
    data.push({name:"idItem",value:$("#inputIdItem").val()});
    
    $.ajax({
        url:"/demo/catalogs/saveModifyHolyday",
        data:data,
        success:function(response,textStatus,jqXHR){
            displaySuccessAlert("Se ha modificado correctamente");
            $("#tblMain").DataTable().ajax.reload();
            $('#mainTabMenu a[href="#tabMain"]').tab('show');
        },
        error:function(response){
            displayDangerAlert("Ha ocurrido un error: "+response);
        }
    });
}

function deleteItem(){
    var row = $("#tblMain").DataTable().row('.selected').data();
    var data = [];
    data.push({name:"idItem",value:row["idHolydays"]});
    
    if(checkNotUndefined(row)){
        var box = bootbox.confirm("<strong>Advertencia!</strong>Esta seguro de eliminar esta vacuna programada?", function(result) {
                    if(result){
                        $.ajax({
                            url:"/demo/catalogs/deleteHolyday",
                            data:data,
                            success:function(response,textStatus,jqXHR){
                                displaySuccessAlert("Se ha borrado correctamente");
                                $("#tblMain").DataTable().ajax.reload();
                                $('#mainTabMenu a[href="#tabMain"]').tab('show');
                            },
                            error:function(response){
                                displayDangerAlert("Ha ocurrido un error: "+response);
                            }
                        });
                    }
                  });
        box.find('.modal-content').css({'color': '#8a6d3b','background-color': '#fcf8e3','border-color': '#faebcc'});
    }else{
        displayWarningAlert("No se ha seleccionado un dia festivo");
    }
}






