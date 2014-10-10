/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
    addFormValidator();
    
    $("#selectReport").on("change",function(){
        showReport();
    });

    for (var i = new Date().getFullYear(); i > 1900; i--)
    {
        $(".selectYear").append($('<option />').val(i).html(i));
    }
    
    initializeDateRangeForm();
    
    $(".forHiding").hide();
    $("#divReport1Options").show();
});

function showReport(){
    var type = $("#selectReport").val();
    
    switch(type){
        case '1':
            $(".forHiding").hide();
            $("#divReport1Options").show();
            $("#divGraph").show();
            break;
        case '2':
            $(".forHiding").hide();
            $("#divGraph").show();
            renderReport2();
            break;
        case '3':
            $("#formDateRange").data('bootstrapValidator').resetForm();
            $(".forHiding").hide();
            $("#divTableReportsOptions").show();
            $("#divTable3").show();
            break;
        case '4':
            $("#formDateRange").data('bootstrapValidator').resetForm();
            $(".forHiding").hide();
            $("#divTableReportsOptions").show();
            $("#divTable4").show();
            $("#divReport4Options").show();
            break;
        case '5':
            $("#formDateRange").data('bootstrapValidator').resetForm();
            $(".forHiding").hide();
            $("#divTableReportsOptions").show();
            $("#divTable5").show();
            break;   
    }
}

function renderReport1(){
    var data = [];
    data.push({name:"year",value:$("#selectGraph1Year").val()});
    
    $.ajax({
            url: "/demo/reports/getConsultsOfMonthByYear",
            type: "POST",
            data: data,
            success:function(response){
                var data = [];
                
                //Format data
                for(var day in response){
                    data.push([parseInt(day),parseInt(response[day])]);
                }
                
		$.plot($("#graphPlaceholder"),[
                    {color:"#c0def0",label:"Consultas",data:data}
                ],
                {
                    legend:{
                        position:"ne"
                    },
                    series: {
                        bars: {
                            show: true,
                            barWidth: 0.6,
                            align: "center"
                        },
                        highlightColor:"#6897bb"
                    },
                    grid:{
                        hoverable:true
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: "%y Consultas",
                        shifts: {
                                x: -60,
                                y: 25
                        }
                    },
                    xaxis:{
                        tickSize: 1
                    },
                    yaxis:{
                        tickSize: 2
                    },
                    zoom: {
                        interactive: true
                    },
                    pan: {
                        interactive: true
                    },
                    navigationControl: {
                        homeRange: {xmin:0,xmax:12,
                            ymin:0,ymax:20},
                        position: { left: "60px", top: "20px" },
                        display: "block",
                        buttons:"basic"
                    },
                    axisLabels: {
                        show: true
                    },
                    xaxes: [{
                        axisLabel: "Mes"
                    }],
                    yaxes: [{
                        position: 'left',
                        axisLabel: "Consultas"
                        
                    }]
                });
            },
            error: function(data, status, error) {
                console.error(error);
            }
        });
}

function renderReport2(){ 
    $.ajax({
            url: "/demo/reports/getDiagnosticsUse",
            type: "POST",
            success:function(response){
                var data = [];
                var diagnosticsNames = {};
                
                //Format data
                for(var diagnostic in response){
                    data.push([response[diagnostic]["diagnositc"]["cieCode"],
                                parseInt(response[diagnostic]["count"])]);
                    diagnosticsNames[response[diagnostic]["diagnositc"]["cieCode"]]=response[diagnostic]["diagnositc"]["diagnostic"];        
                }
                
                data.sort(compare);
                
		$.plot($("#graphPlaceholder"),[data],
                {
                    series: {
                        bars: {
                            show: true,
                            barWidth: 0.6,
                            align: "center"
                        }
                    },
                    xaxis: {
                        mode: "categories",
                        tickLength: 0,
                        max:5
                    },
                    grid:{
                        hoverable:true
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: function(label, xval, yval, flotItem){
                            return "%y Consultas de "+diagnosticsNames[xval]; 
                        },
                        shifts: {
                                x: -60,
                                y: 25
                        }
                    },
                    zoom: {
                        interactive: true
                    },
                    pan: {
                        interactive: true
                    },
                    navigationControl: {
                        homeRange: {xmin:0,xmax:10,
                            ymin:0,ymax:300},
                        position: { left: "480px", top: "20px" },
                        display: "block"
                    },
                });
            },
            error: function(data, status, error) {
                console.error(error);
            }
        });
}

function initializeDateRangeForm(){
    $("#formDateRange").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            start:{
                validators:{
                    date:{
                        format:"DD/MM/YYYY",
                        message:"No es una fecha valida"
                    }
                }
            },
            end:{
                validators:{
                    date:{
                        format:"DD/MM/YYYY",
                        message:"No es una fecha valida"
                    },
                    validateDateRange:{
                        firstField:"inputBeginingDate",
                        message:"Esta fecha no puede ser menor"
                    }
                }
            } 
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        renderTableReports();
    });
}

function renderTableReports(){
    
    switch($("#selectReport").val()){
        case '3':   //Relacion Consultas
            renderReport3();
            break;
        case '4':   //Ingresos
            renderReport4();
            break;
        case '5':   //Ingresos
            renderReport5();
            break; 
    }
}

function renderReport3(){
    $("#tblReport3").append('<tfoot><tr><td></td><td><b>Totales:</b></td>\n\
                                <td></td><td></td><td></td><td></td><td></td>\n\
                                <td></td><td></td><td></td><td></td></tr></tfoot>');
    
        $('#tblReport3').DataTable({
            "destroy": true,
            "scrollY": "400px",
            "order":[0,'desc'],
            "scrollCollapse": true,
            "paging": true,
            "info": true,
            "searching": false,
            "dom": 'T<"clear">lfrtip',
            "tableTools": {
                "sSwfPath": "/demo/resources/js/BootstrapPlugins/DataTables/copy_csv_xls_pdf.swf"
            },
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
                "url":"/demo/reports/getConsultationPayments",
                "data":{
                    "start":$( "#inputBeginingDate" ).val(),
                    "end":$( "#inputEndingDate" ).val()
                }
            },
            "columns":[
                {"title": "Fecha","render":function(row, data, dataIndex){
                    return moment(dataIndex.consultationDate).format("DD/MM/YYYY");
                }},
                {"title": "Familia","render":function(row, data, dataIndex){
                    return dataIndex.patient.fatherLastName + " " + dataIndex.patient.motherLastName;
                }},
                {"title": "Efectivo","data":"cash"},
                {"title": "Cheque","data":"check"},
                {"title": "Tarjeta","data":"card"},
                {"title": "Otro","data":"other"},
                {"title": "Total Pago","data":"paymentTotal"},
                {"title": "Resta","data":"rest"},
                {"title": "Total Consulta","data":"consultationTotal"},
                {"title": "A. Consultorio","data":"consultationCAT"},
                {"title": "A. Externas","data":"consultationEAT"}
            ],
            "initComplete":function(settings,json){

                $('#tblReport tbody').on( 'click', 'tr', function (e) {
                    var table = $('#tblReport').DataTable();
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                    }else{
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }   
                });
            },
            "footerCallback":function( tfoot, data, start, end, display ) {
                 var api = this.api(), data;

                 // Total over all pages
                 var total = 0.0;
                 for(var i =2; i < 11; i++){
                    total = 0.0; 
                    data = api.column( i ).data();
                    total = data.length ?
                    data.reduce( function (a, b) {
                            return a + b;
                    } ) :
                    0;
                    // Update footer
                    $( api.column( i ).footer() ).html('$'+ total);
                 }
            }
        });
}

function renderReport4(){
    if(parseInt($('input[name="radioOptionR4"]:checked').val()) === 1){
        $("#tblReport4").append('<tfoot><tr><td></td><td></td><td></td><td><b>Totales</b></td>\n\
                                        <td></td><td></td><td></td></tr></tfoot>');
        $('#tblReport4').DataTable({
            "destroy": true,
            "scrollY": "400px",
            "order":[0,'desc'],
            "scrollCollapse": true,
            "paging": true,
            "info": true,
            "searching": false,
            "dom": 'T<"clear">lfrtip',
            "tableTools": {
                "sSwfPath": "/demo/resources/js/BootstrapPlugins/DataTables/copy_csv_xls_pdf.swf"
            },
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
                "url":"/demo/reports/getConsultationReceipts",
                "data":{
                    "start":$( "#inputBeginingDate" ).val(),
                    "end":$( "#inputEndingDate" ).val()
                }
            },
            "columns":[
                {"title": "Folio","data":"receiptNumber"},
                {"title": "Fecha Emision","render":function(row, data, dataIndex){
                    return moment(dataIndex.consultationDate).format("DD/MM/YYYY");
                }},
                {"title": "Fecha Cita","render":function(row, data, dataIndex){
                    return moment(dataIndex.date).format("DD/MM/YYYY");
                }},
                {"title": "A favor de","data":"payerName"},
                {"title": "Valor","data":"idPayment.paymentTotal"},
                {"title": "Retencion","data":"isr"},
                {"title": "Neto","data":"total"}
            ],
            "initComplete":function(settings,json){

                $('#tblReport tbody').on( 'click', 'tr', function (e) {
                    var table = $('#tblReport').DataTable();
                    if ( $(this).hasClass('selected') ) {
                        $(this).removeClass('selected');
                    }else{
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }   
                });
            },
            "footerCallback":function( tfoot, data, start, end, display ) {
                 var api = this.api(), data;

                 // Total over all pages
                 var total = 0.0;
                 for(var i =4; i < 7; i++){
                    total = 0.0; 
                    data = api.column( i ).data();
                    console.log(data);
                    total = data.length ?
                    data.reduce( function (a, b) {
                            return a + b;
                    } ) :
                    0;
                    // Update footer
                    $( api.column( i ).footer() ).html('$'+ total);
                 }
            }
        });
    }else{
        $('#tblReport4').DataTable({
            "destroy": true,
            "scrollY": "400px",
            "order":[0,'desc'],
            "scrollCollapse": true,
            "paging": true,
            "info": true,
            "searching": false,
            "dom": 'T<"clear">lfrtip',
            "tableTools": {
                "sSwfPath": "/demo/resources/js/BootstrapPlugins/DataTables/copy_csv_xls_pdf.swf"
            },
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
                "url":"/demo/reports/getConsultationReceiptsTotals",
                "data":{
                    "start":$( "#inputBeginingDate" ).val(),
                    "end":$( "#inputEndingDate" ).val()
                }
            },
            "columns":[
                {"title": "Expedidor","data":"idExpeditor.idStaffMember.name"},
                {"title": "Desde","render":function(row, data, dataIndex){
                    return $( "#inputBeginingDate" ).val();
                }},
                {"title": "Hasta","render":function(row, data, dataIndex){
                    return $( "#inputEndingDate" ).val();
                }},
                {"title": "Recibos","data":"totalReceipts"},
                {"title": "Desde","data":"fromReceiptNumber"},
                {"title": "Hasta","data":"toReceiptNumber"},
                {"title": "Valor","data":"sumTotal"}
            ],
            "initComplete":function(settings,json){

                $('#tblReport tbody').on( 'click', 'tr', function (e) {
                    var table = $('#tblReport').DataTable();
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
}

function renderReport5(){
    
    $("#tblReport5").append('<tfoot><tr><td></td><td></td><td></td><td></td>\n\
                                        <td></td><td></td><td></td></tr></tfoot>');
    $('#tblReport5').DataTable({
        "destroy": true,
        "scrollY": "400px",
        "order":[0,'desc'],
        "scrollCollapse": true,
        "paging": true,
        "info": true,
        "searching": false,
        "dom": 'T<"clear">lfrtip',
        "tableTools": {
            "sSwfPath": "/demo/resources/js/BootstrapPlugins/DataTables/copy_csv_xls_pdf.swf"
        },
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
            "url":"/demo/reports/getConsultationReceipts",
            "data":{
                "start":$( "#inputBeginingDate" ).val(),
                "end":$( "#inputEndingDate" ).val()
            }
        },
        "columns":[
            {"title": "Folio","data":"receiptNumber"},
            {"title": "Fecha Emision","render":function(row, data, dataIndex){
                return moment(dataIndex.consultationDate).format("DD/MM/YYYY");
            }},
            {"title": "A favor de","data":"payerName"},
            {"title": "RFC","data":"rfc"},
            {"title": "Valor","data":"idPayment.paymentTotal"},
            {"title": "Retencion","data":"isr"},
            {"title": "Neto","data":"total"}
        ],
        "initComplete":function(settings,json){

            $('#tblReport5 tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblReport5').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }   
            });
        },
        "footerCallback":function( tfoot, data, start, end, display ) {
             var api = this.api(), data;

             // Total over all pages
             var total = 0.0;
             for(var i =4; i < 7; i++){
                total = 0.0; 
                data = api.column( i ).data();
                total = data.length ?
                data.reduce( function (a, b) {
                        return a + b;
                } ) :
                0;
                // Update footer
                $( api.column( i ).footer() ).html('$'+ total);
             }
        }
    });
}

function renderReport6(){
    
    $("#tblReport6").append('<tfoot><tr><td></td><td></td><td></td><td></td>\n\
                                        <td></td><td></td><td></td></tr></tfoot>');
    $('#tblReport6').DataTable({
        "destroy": true,
        "scrollY": "400px",
        "order":[0,'desc'],
        "scrollCollapse": true,
        "paging": true,
        "info": true,
        "searching": false,
        "dom": 'T<"clear">lfrtip',
        "tableTools": {
            "sSwfPath": "/demo/resources/js/BootstrapPlugins/DataTables/copy_csv_xls_pdf.swf"
        },
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
            "url":"/demo/reports/getConsultationReceipts",
            "data":{
                "start":$( "#inputBeginingDate" ).val(),
                "end":$( "#inputEndingDate" ).val()
            }
        },
        "columns":[
            {"title": "Folio","data":"receiptNumber"},
            {"title": "Fecha Emision","render":function(row, data, dataIndex){
                return moment(dataIndex.consultationDate).format("DD/MM/YYYY");
            }},
            {"title": "A favor de","data":"payerName"},
            {"title": "RFC","data":"rfc"},
            {"title": "Valor","data":"idPayment.paymentTotal"},
            {"title": "Retencion","data":"isr"},
            {"title": "Neto","data":"total"}
        ],
        "initComplete":function(settings,json){

            $('#tblReport6 tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblReport6').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }   
            });
        },
        "footerCallback":function( tfoot, data, start, end, display ) {
             var api = this.api(), data;

             // Total over all pages
             var total = 0.0;
             for(var i =4; i < 7; i++){
                total = 0.0; 
                data = api.column( i ).data();
                total = data.length ?
                data.reduce( function (a, b) {
                        return a + b;
                } ) :
                0;
                // Update footer
                $( api.column( i ).footer() ).html('$'+ total);
             }
        }
    });
}

function compare(a,b){
    return a[1] < b[1] ? 1 : -1;
}

function addFormValidator(){
    $.fn.bootstrapValidator.validators.validateDateRange = {
        /**
         * @param {BootstrapValidator} validator The validator plugin instance
         * @param {jQuery} $field The jQuery object represents the field element
         * @param {Object} options The validator options
         * @returns {Boolean}
         */
        validate: function(validator, $field, options) {
            // Get the field value
            var d1 = $( "#"+options.firstField ).val().split("/");
            var d2 = $field.val().split("/");
            return moment( new Date(d1[2], d1[1]-1, d1[0])).isBefore(new Date(d2[2],d2[1]-1,d2[0])); 
        }
    };
}