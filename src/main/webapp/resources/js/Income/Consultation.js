/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var fields = ["cash","check","card","other"];
var fieldsA = ["check","card","other"];
var prType="";
var paymentId="";
var relativeId="";
var tpId="";

$(document).ready(function(){
    initializeCCATable();
    initializeConsultationActivityTable();
    initializePaymentFormValidator();
    initializeReceiptFormValidator();
  
    $("#paymentModal").on("shown.bs.modal",function(){
        $('#tblConsultationActivity').DataTable().columns.adjust().draw();
    });
    
    $("#receiptModal").on("shown.bs.modal",function(){
        clearFormInputTextFields('formReceiptPayer');
        $("#formReceiptPayer [name='receiptTotal']").val($("#paymentTotal").val());
        $("#formReceiptPayer [name='isr']").val("0.0");
        $("#formReceiptPayer [name='receiptSum']").val($("#paymentTotal").val());
        $("#formReceiptPayer [name='receiptNumber']").val(
                $('#tblConsultationCostAbstract').DataTable().row('.selected').data()["consultationPatient"]["idDoctor"]["receiptNumber"]);
        $("#receiptStatus").html(prType);
        
    });
    
    $("#formReceiptPayer [name='retention']").on('change',function(){
        var total = parseFloat($("#formReceiptPayer [name='receiptSum']").val());
        if($(this).is(':checked')){
            $("#formReceiptPayer [name='isr']").val(total*.1);
            $("#formReceiptPayer [name='receiptTotal']").val(total-(total*.1));
        }else{
            $("#formReceiptPayer [name='isr']").val("0.0");
            $("#formReceiptPayer [name='receiptTotal']").val(total);
        }
    });
    
    $("#formPayment [name='paymentType']").on("change",function(){
        if(parseInt($(this).val()) === 1){
            $('#formPayment').bootstrapValidator('enableFieldValidators', "paymentTotal",true,"greaterThan");
            $('#formPayment').bootstrapValidator('enableFieldValidators', "paymentTotal",false,"lessThan");
        }else{
            $('#formPayment').bootstrapValidator('enableFieldValidators', "paymentTotal",false,"greaterThan");
            $('#formPayment').bootstrapValidator('enableFieldValidators', "paymentTotal",true,"lessThan");
        }
        
        $('#formPayment').data('bootstrapValidator').resetForm();
    });
    
    $(".changeV").on("change",function(){
        var form = $('#formPayment').data('bootstrapValidator');
        $('#paymentTotal').val("0.0");
        $('#paymentChange').val("0.0");
        //Recalculate the paymentTotal
        fields.forEach(function(value){
            var input = $('#formPayment [name="'+ value +'"]');
            if(form.isValidField(value)){
                if(!isNaN(parseFloat(input.val()))){
                    $('#paymentTotal').val( parseFloat(input.val()) + parseFloat($('#paymentTotal').val()));
                }else{
                    input.val("0.0");
                }
            }
        });
        
        //Recalculate the change
        var pT = parseFloat($('#paymentTotal').val());
        var iT = parseFloat($('#inputTotal').val());
        if( pT >= iT ){
            $("#paymentChange").val(pT-iT);
        }
        
        //Check if the changed field needs additional info
        fieldsA.forEach(function(value){
            var input = $('#formPayment [name="'+ value +'"]');
            if(form.isValidField(value)){
                var isEmpty = input.val() == '' || input.val() == "0.0";
                var name = value+"D";
                $('#formPayment').bootstrapValidator('enableFieldValidators', name,!isEmpty);
                form.revalidateField(name);
            }
        });
        
        form.revalidateField("paymentTotal");
    });
    
    
});

function initializeCCATable(){
    
    $('#tblConsultationCostAbstract').DataTable({
        "scrollY": "300px",
        "order":[0,'desc'],
        //"deferRender": true, Not compatible with select
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
            url:"/demo/income/getConsultationCostAbstract"
        },
        "columns":[
            {"render":function(row, data, dataIndex){
                return moment(dataIndex.consultationDate).format("DD/MM/YYYY");
            }},
            {"data":"consultationPatient.fatherLastName"},
            {"data":"consultationPatient.motherLastName"},
            {"data":"consultationPatient.firstName"},
            {"data":"idConsultationPaymentStatus.estatus"},
            {"data":"total"},
            {"data":"rest"}
        ],
        "initComplete":function(settings,json){

            $('#tblConsultationCostAbstract tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblConsultationCostAbstract').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }   
            });
        },
        "createdRow": function( row, data, dataIndex ) {
            
            switch(data.idConsultationPaymentStatus.idConsultationPaymentEstatus){
                case 1:
                    $(row).css({'background-color':'#FF2A4A'});//Not Paid
                    $(row).addClass("vpExpired");
                    break;
                case 2:
                    $(row).css({'background-color':'#feffa3'});//Patialy Paid
                    $(row).addClass("vpSuspended");
                    break;
                case 3:
                    $(row).css({'background-color':'#77fda0'});//Fully paid
                    $(row).addClass("vpNormal");
                    break;   
            }
        }
    });
}

function openPaymentModal(){
    var row = $('#tblConsultationCostAbstract').DataTable().row('.selected').data();
    var total = 0.0, cTotal = 0.0, eTotal = 0.0;
    
    if(checkNotUndefined(row)){
        if(parseInt(row["idConsultationPaymentStatus"]["idConsultationPaymentEstatus"]) === 3 ){
            displayWarningAlert("La consulta ya esta liquidada");
        }else{
            $('#tblConsultationActivity').DataTable().clear();
            row["activities"].forEach(function(entry){
                $('#tblConsultationActivity').DataTable().row.add(entry).draw();    //Add the consultation activity to the table
                //if(entry.includeInBill === 1){
                if(parseInt(entry.activity.idActivityType.idActivityType) === 3){
                    eTotal += entry.cost;   //External activity total
                }else{
                    cTotal += entry.cost;   //Consultory activity total
                }
                //}
            });
            //total = eTotal + cTotal;
            
            $("#inputTotal").val( row["rest"] );
            $("#inputTotalConsultory").val(cTotal);
            $("#inputTotalExternal").val(eTotal);
            $("#paymentModal").modal('show');
            
                        
            //Load relatives table for the receipt
            if ( ! $.fn.DataTable.isDataTable( '#tblRelatives' ) ) {
              initializeRelativesTable();
            }else{
                $('#tblRelatives').DataTable().ajax.reload();
            }
            //Load third party payer for the receipt
            if ( ! $.fn.DataTable.isDataTable( '#tblThirdPartyPayers' ) ) {
              initializeThirdPartyPayersTable();
            }else{
                $('#tblThirdPartyPayers').DataTable().ajax.reload();
            }
        }
    }else{
        displayWarningAlert("No se ha seleccionado una consulta para pagar");
    }
}

//Table that will have the activities of the consultation
function initializeConsultationActivityTable(){
    
    $('#tblConsultationActivity').DataTable({
        "scrollY": "100px",
        "scrollCollapse": true,
        "paging": false,
        "info": false,
        "searching": false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "columns":[
            {"data":"activity.activity"},
            {"data":"cost"},
            {"render":function(data,type,row){ 
                if(row['includeInBill'] === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }}
        ]
    });
}

//Bootstrap validator iniazilitazion for the payment
function initializePaymentFormValidator(){
    $('#formPayment').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            cash: {
                validators: {
                    numeric: {
                        message: 'Entrada invalida'
                    }
                }
            },
            check: {
                validators: {
                    numeric: {
                        message: 'Entrada invalida'
                    }
                }
            },
            card: {
                validators: {
                    numeric: {
                        message: 'Entrada invalida'
                    }
                }
            },
            other: {
                validators: {
                    numeric: {
                        message: 'Entrada invalida'
                    }
                }
            },
            checkD: {
                enabled: false,
                validators: {
                    numeric: {
                        message: 'Entrada invalida'
                    },
                    notEmpty: {
                        message: 'Este campo es Obligatorio'
                    }
                }
            },
            cardD: {
                enabled: false,
                validators: {
                    creditCard: {
                        message: 'Numero de tarjeta no valida'
                    },
                    notEmpty: {
                        message: 'Este campo es obligatorio'
                    }
                }
            },
            otherD: {
                enabled: false,
                validators: {
                    numeric: {
                        message: 'Entrada invalida'
                    },
                    notEmpty: {
                        message: 'Este campo es Obligatorio'
                    }
                }
            },
            paymentTotal: {
                validators:{
                    greaterThan: {
                        inclusive: true,
                        message: "El valor debe cubrir el total",
                        value: function(value, validator, $field) {
                            return parseFloat($("#inputTotal").val());
                        }
                    },
                    lessThan: {
                        enabled:false,
                        inclusive: false,
                        message: "El valor debe ser menor que el total",
                        value: function(value, validator, $field) {
                            return parseFloat($("#inputTotal").val());
                        }
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        savePayment();
    });
}

function savePayment(){
    var data =[];
    //Save if its total or partial payment
    data.push({name:"paymentType",value:$("#formPayment [name='paymentType']:checked").val()});
    if(parseInt($("#formPayment [name='paymentType']:checked").val()) === 1){
        prType = "Liquidacion";
    }else{
        prType= "Pago Parcial";
    }
    //cash data
    data.push({name:"cash",value:$("#formPayment [name='cash']").val()});
    //check
    data.push({name:"check",value:$("#formPayment [name='check']").val()});
    //check digits
    data.push({name:"checkD",value:$("#formPayment [name='checkD']").val()});
    //card
    data.push({name:"card",value:$("#formPayment [name='card']").val()});
    //card digits
    data.push({name:"cardD",value:$("#formPayment [name='cardD']").val()});
    //other
    data.push({name:"other",value:$("#formPayment [name='other']").val()});
    //other description
    data.push({name:"otherD",value:$("#formPayment [name='otherD']").val()});
    data.push({name:"notes",value:$("#formPayment [name='notes']").val()});
    //Consultation cost abstract id
    data.push({name:"ccaId",value:$("#tblConsultationCostAbstract").DataTable().row('.selected').data()["idConsultationCostAbstract"]});
    
    $.ajax({
        url:"/demo/income/savePayment",
        data:data,
        success:function(response){
            paymentId=response;
            $("#paymentModal").modal('hide');
            $("#receiptModal").modal('show');
        },
        error:function(){
            
        }
    });
    
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
                var id = $("#tblConsultationCostAbstract").DataTable().row('.selected').data()["consultationPatient"]["idPatient"];
                var data = [];
                data.push({name:"idPatient",value:id});
                return data;
            }
        },
        "columns":[
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
        $("#formReceiptPayer").data('bootstrapValidator').resetForm();
        $("#formReceiptPayer [name='payerName']").val(row.relative.firstName + " " +
            row.relative.fatherLastName + " " + row.relative.motherLastName);
        $("#formReceiptPayer [name='street']").val(row.relative.street);
        $("#formReceiptPayer [name='colony']").val(row.relative.colony);
        $("#formReceiptPayer [name='city']").val(row.relative.city);
        $("#formReceiptPayer [name='state']").val(row.relative.state);
        $("#formReceiptPayer [name='zip']").val(row.relative.cp);
        $("#formReceiptPayer [name='country']").val(row.relative.country);
        $("#formReceiptPayer [name='rfc']").val(row.relative.rfc);
        $("#formReceiptPayer [name='date']").val(moment().format("DD/MM/YYYY"));
        
        var activities = $('#tblConsultationCostAbstract').DataTable().row('.selected').data()["activities"];
        var concept = $("#formReceiptPayer [name='concept']");
        activities.forEach(function(value){
            if(value.includeInBill === 1){
                concept.val(concept.val() +" "+ value.activity.activity+ " \n");
            }
        });
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
        $("#formReceiptPayer").data('bootstrapValidator').resetForm();
        $("#formReceiptPayer [name='payerName']").val(row.name);
        $("#formReceiptPayer [name='street']").val(row.street);
        $("#formReceiptPayer [name='colony']").val(row.colony);
        $("#formReceiptPayer [name='city']").val(row.city);
        $("#formReceiptPayer [name='state']").val(row.state);
        $("#formReceiptPayer [name='zip']").val(row.zip);
        $("#formReceiptPayer [name='country']").val(row.country);
        $("#formReceiptPayer [name='rfc']").val(row.rfc);
        $("#formReceiptPayer [name='date']").val(moment().format("DD/MM/YYYY")); 
        
        var activities = $('#tblConsultationCostAbstract').DataTable().row('.selected').data()["activities"];
        var concept = $("#formReceiptPayer [name='concept']");
        activities.forEach(function(value){
            if(value.includeInBill === 1){
                concept.val(concept.val() +" "+ value.activity.activity+ " \n");
            }
        });
        relativeId= "";
        tpId=row.idThirdPartyPayer;
        $("#thirdPartyPayersModal").modal('hide');
    }else{
        displayWarningAlert("No ha seleccionado un familiar");
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
    var row =$("#tblConsultationCostAbstract").DataTable().row('.selected').data();
    
    var data = $('#formReceiptPayer').serializeArray();
    data.push({name:"payment",value:paymentId});
    data.push({name:"relative",value:relativeId});
    data.push({name:"thirdPayer",value:tpId});
    data.push({name:"ccaId",value:row.idConsultationCostAbstract});
    data.push({name:"ret",value:$("#formReceiptPayer [name='retention']").prop('checked')});
    
    $.ajax({
        url:"/demo/income/saveReceipt",
        data:data,
        success:function(response){
            window.open('/demo/income/receiptPreview/'+response);
            $("#receiptModal").modal('hide');
            $('#tblConsultationCostAbstract').DataTable().ajax.reload();
        },
        error:function(){
            
        }
    });
}

function showAllCCA(){
    $('#tblConsultationCostAbstract').DataTable({
        "scrollY": "300px",
        "order":[0,'desc'],
        "destroy":true,
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
            url:"/demo/income/getAllCCA"
        },
        "columns":[
            {"render":function(row, data, dataIndex){
                return moment(dataIndex.consultationDate).format("DD/MM/YYYY");
            }},
            {"data":"consultationPatient.fatherLastName"},
            {"data":"consultationPatient.motherLastName"},
            {"data":"consultationPatient.firstName"},
            {"data":"idConsultationPaymentStatus.estatus"},
            {"data":"total"},
            {"data":"rest"}
        ],
        "initComplete":function(settings,json){

            $('#tblConsultationCostAbstract tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblConsultationCostAbstract').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }   
            });
        },
        "createdRow": function( row, data, dataIndex ) {
            
            switch(data.idConsultationPaymentStatus.idConsultationPaymentEstatus){
                case 1:
                    $(row).css({'background-color':'#FF2A4A'});//Not Paid
                    $(row).addClass("vpExpired");
                    break;
                case 2:
                    $(row).css({'background-color':'#feffa3'});//Patialy Paid
                    $(row).addClass("vpSuspended");
                    break;
                case 3:
                    $(row).css({'background-color':'#77fda0'});//Fully paid
                    $(row).addClass("vpNormal");
                    break;   
            }
        }
    });
}