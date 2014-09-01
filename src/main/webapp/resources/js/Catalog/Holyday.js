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
            {"data":"holyday"},
            {"data":"day"},
            {"data":"mont"}
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
            month:{
                validators:{
                    integer: {
                        message: 'Solo numeros enteros'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    between: {
                        min: 1,
                        max: 12,
                        message:"Valor minimo 1 - max 12"
                    }
                }  
            },
            day:{
                validators:{
                    integer: {
                        message: 'Solo numeros enteros'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    lessThan:{
                        inclusive:true,
                        value: function(value, validator, $field) {
                            var month = parseInt($("#inputNewItemMonth").val());
                            if(month === 4 || month === 6 || month === 9 || month === 11){
                                return 30;
                            }else{
                                if(month === 2){
                                    return 28;
                                }else{
                                    return 31;
                                }
                            }
                        },
                        message: "Este dia noes valido para este mes"
                    }
                }  
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('keyup', '[name="month"]', function() {
        $("#formNewItem").data('bootstrapValidator').revalidateField('day');
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
            month:{
                validators:{
                    integer: {
                        message: 'Solo numeros enteros'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    between: {
                        min: 1,
                        max: 12
                    }
                }  
            },
            day:{
                validators:{
                    integer: {
                        message: 'Solo numeros enteros'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    lessThan:{
                        inclusive:true,
                        value: function(value, validator, $field) {
                            var month = parseInt($("#inputModifyItemMonth").val());
                            if(month === 4 || month === 6 || month === 9 || month === 11){
                                return 30;
                            }else{
                                if(month === 2){
                                    return 28;
                                }else{
                                    return 31;
                                }
                            }
                        },
                        message: "Este dia noes valido para este mes"
                    }
                }  
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('keyup', '[name="month"]', function() {
        $("#formModifyItem").data('bootstrapValidator').revalidateField('day');
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
    data.push({name:"month",value:$("#inputNewItemMonth").val()});
    data.push({name:"day",value:$("#inputNewItemDay").val()});
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
        $("#inputModifyItemMonth").val(row["mont"]);
        $("#inputModifyItemDay").val(row["day"]);
        
        $('#mainTabMenu a[href="#tabModify"]').tab('show');
    }else{
        displayWarningAlert("No ha seleccionado un tipo de nacimiento");
    }
}

function saveModifyItem(){
    var data = [];
    data.push({name:"idItem",value:$("#inputIdItem").val()});
    data.push({name:"month",value:$("#inputModifyItemMonth").val()});
    data.push({name:"day",value:$("#inputModifyItemDay").val()});
    data.push({name:"itemName",value:$("#inputModifyItem").val()});
    
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






