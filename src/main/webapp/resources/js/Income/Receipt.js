/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var relativeId;
var tpId;

$(document).ready(function(){
    initializeCPRTable();
    initializeReceiptFormValidator();
    initializeThirdPartyPayersTable();
    
    $("#realtivesModal").on('shown.bs.modal',function(){
        var table = $('#tblRelatives').DataTable();
        var index = table.column( 0 ).data().indexOf( relativeId );

        $(table.row( index ).node()).addClass("selected");

    });
    
    $("#thirdPartyPayersModal").on('shown.bs.modal',function(){
        console.log("tp id: "+ tpId)
        var table = $('#tblThirdPartyPayers').DataTable();
        var index = table.column( 0 ).data().indexOf( tpId );
        console.log(index);
        $(table.row( index ).node()).addClass("selected");

    });
});

function initializeCPRTable(){
    
    $('#tblReceipt').DataTable({
        "scrollY": "300px",
        "order":[0,'desc'],
        "scrollCollapse": true,
        "paging": true,
        "info": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar",
            "lengthMenu": "Mostrar _MENU_ resultados por pagina",
             "paginate": {
                  "next": "Siguiente",
                  "previous": "Anterior"
              }
        },
        "ajax":{
            url:"/demo/income/getConsultationPaymentReceipt"
        },
        "columns":[
            {"render":function(row, data, dataIndex){
                return moment(dataIndex.date).format("DD/MM/YYYY");
            }},
            {"data":"receiptNumber"},
            {"data":"payerName"},
            {"render":function(r, dataIndex, row){
                    return (row.total + row.isr);
            }},
            {"render":function(data,type,row){ 
                if(row['retention'] === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }},
            {"data":"total"},
            {"data":"idConsultatioPaymentReceiptType.type"}
        ],
        "initComplete":function(settings,json){

            $('#tblReceipt tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblReceipt').DataTable();
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

function printReceipt(){
    var row = $("#tblReceipt").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        window.open('/demo/income/receiptPreview/'+row.idConsultationPaymentReceipt);
    }else{
        displayWarningAlert("No ha seleccionado un recibo");
    }   
}

function editReceipt(){
    var row = $('#tblReceipt').DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        console.log(row);
        if(row.idThirdPartyPayer === null){
            relativeId= row.idRelative.idRelative;
            tpId="";
        }else{
            relativeId= "";
            tpId=row.idThirdPartyPayer.idThirdPartyPayer;
        }
        
        if ( ! $.fn.DataTable.isDataTable( '#tblRelatives' ) ) {
            initializeRelativesTable();
        }else{
            $("#tblRelatives").DataTable().ajax.reload();  
        }

        $("#formReceiptPayer [name='payerName']").val(row.payerName);
        $("#formReceiptPayer [name='street']").val(row.street);
        $("#formReceiptPayer [name='colony']").val(row.colony);
        $("#formReceiptPayer [name='city']").val(row.city);
        $("#formReceiptPayer [name='state']").val(row.state);
        $("#formReceiptPayer [name='zip']").val(row.zip);
        $("#formReceiptPayer [name='country']").val(row.country);
        $("#formReceiptPayer [name='rfc']").val(row.rfc);
        $("#formReceiptPayer [name='concept']").val(row.concept);
        $("#formReceiptPayer [name='date']").val(moment(row.date).format("DD/MM/YYYY"));
        $("#formReceiptPayer [name='concept']").val(row.concept);
        $("#formReceiptPayer [name='receiptSum']").val(row.isr + row.total);
        if(row.retention == 1){
            $("#formReceiptPayer [name='retention']").attr("checked",true);
        }else{
            $("#formReceiptPayer [name='retention']").attr("checked",false);
        }
        $("#formReceiptPayer [name='isr']").val(row.isr);
        $("#formReceiptPayer [name='receiptTotal']").val(row.total);
        $("#formReceiptPayer [name='receiptNumber']").val(row.receiptNumber);
        
        $('#receiptModal').modal('show');
    }else{
        displayWarningAlert("No ha seleccionado un recibo");
    }
}

function initializeRelativesTable(){
    
    $('#tblRelatives').DataTable({
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info": false,
        "searching": false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        ajax:{
            url:"/demo/income/getPatientRelatives",
            data:function(){
                var id = $("#tblReceipt").DataTable().row('.selected').data()["patient"]["idPatient"];
                var data = [];
                data.push({name:"idPatient",value:id});
                return data;
            }
        },
        "columns":[
            {"data":"relative.idRelative","visible":false},
            {"data":"relative.fatherLastName"},
            {"data":"relative.motherLastName"},
            {"data":"relative.firstName"},
            {"data":"relative.rfc"}
        ],
        "initComplete":function(settings,json){

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

function initializeThirdPartyPayersTable(){
    
    $('#tblThirdPartyPayers').DataTable({
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info": false,
        "searching": false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        ajax:"/demo/income/getThirdPartyPayer",
        "columns":[
            {"data":"idThirdPartyPayer","visible":false},
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
        }
    });
}

function selectRelative(){
    var row = $("#tblRelatives").DataTable().row('.selected').data(); 
    
    if(checkNotUndefined(row)){
        //$("#formReceiptPayer").data('bootstrapValidator').resetForm();
        $("#formReceiptPayer [name='payerName']").val(row.relative.firstName + " " +
            row.relative.fatherLastName + " " + row.relative.motherLastName);
        $("#formReceiptPayer [name='street']").val(row.relative.street);
        $("#formReceiptPayer [name='colony']").val(row.relative.colony);
        $("#formReceiptPayer [name='city']").val(row.relative.city);
        $("#formReceiptPayer [name='state']").val(row.relative.state);
        $("#formReceiptPayer [name='zip']").val(row.relative.cp);
        $("#formReceiptPayer [name='country']").val(row.relative.country);
        $("#formReceiptPayer [name='rfc']").val(row.relative.rfc);
        
        relativeId= row.relative.idRelative;
        tpId="";
        $("#realtivesModal").modal('hide');
    }else{
        displayWarningAlert("No ha seleccionado un familiar");
    }
}

function selectThirdPartyPayer(){
    var row = $("#tblThirdPartyPayers").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        //$("#formReceiptPayer").data('bootstrapValidator').resetForm();
        $("#formReceiptPayer [name='payerName']").val(row.name);
        $("#formReceiptPayer [name='street']").val(row.street);
        $("#formReceiptPayer [name='colony']").val(row.colony);
        $("#formReceiptPayer [name='city']").val(row.city);
        $("#formReceiptPayer [name='state']").val(row.state);
        $("#formReceiptPayer [name='zip']").val(row.zip);
        $("#formReceiptPayer [name='country']").val(row.country);
        $("#formReceiptPayer [name='rfc']").val(row.rfc);
        
        relativeId= "";
        tpId=row.idThirdPartyPayer;
        $("#thirdPartyPayersModal").modal('hide');
    }else{
        displayWarningAlert("No ha seleccionado un pagador");
    }
}

function initializeReceiptFormValidator(){
    $('#formReceiptPayer').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            payerName: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            street: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            colony: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            city: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            state: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            rfc: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            concept: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveReceipt();
    });
}

function saveReceipt(){
    var row =$("#tblReceipt").DataTable().row('.selected').data();
    
    var data = $('#formReceiptPayer').serializeArray();
    data.push({name:"relative",value:relativeId});
    data.push({name:"thirdPayer",value:tpId});
    data.push({name:"cprId",value:row.idConsultationPaymentReceipt});
    
    $.ajax({
        url:"/demo/income/updateReceipt",
        data:data,
        success:function(response){
            window.open('/demo/income/receiptPreview/'+response);
            $("#receiptModal").modal('hide');
        },
        error:function(){
            
        }
    });
}