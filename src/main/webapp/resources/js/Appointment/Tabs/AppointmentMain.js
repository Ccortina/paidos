/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#inputMainDate").val(moment().format("DD/MM/YYYY"));
    initializeMainDateForm();
    initializeMainTable();
    
    for (var i = new Date().getFullYear(); i > 1900; i--)
    {
        $('#selectYear').append($('<option />').val(i).html(i));
    }
    
    //Set current month and year
    $("#selectMonth").val(new Date().getMonth());
    $("#selectYear").val(new Date().getFullYear());
    
    drawConsultationsMonthPerDayChart();
    
    $("#selectMonth").on('change',function(){
        drawConsultationsMonthPerDayChart();
    });
    
    $("#selectYear").on('change',function(){
        drawConsultationsMonthPerDayChart();
    });
    
});

function initializeMainTable(){
    $("#tblMain").DataTable({
        "scrollY": "500px",
        "ordering":false,
        "scrollCollapse": false,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            url:"/demo/appointment/getDoctorAppointmentsList",
            data:{
                start:function(){return ($("#inputMainDate").val());}}
            },
        "columns":[
            {"data":"startTime"},
            {"render":function(data,row,full){
                return (full['idPatient']['firstName']+" "+full['idPatient']['fatherLastName']);
            }},
            {"data":"motive"},
            {"data":"idStatus.status"}
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

function initializeMainDateForm(){
     $('#formMainDate').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            date: {
                validators: {
                    notEmpty: {
                        message: 'La fecha de la cita no puede estar vacia'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'No es una fecha valida'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
    }).on('success.field.bv', '[name="date"]', function(e, data) {
        if ( !$.fn.DataTable.isDataTable( '#tblMain' ) ) {
            initializeMainTable();
        }else{
            $("#tblMain").DataTable().ajax.reload(); 
        }
    });
    
}

function consult(){
    var row = $("#tblMain").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        bootbox.confirm("Quiere ir a la consulta?", function(result) {
            if(result){

                window.location.href = "/demo/consultation/"+$("#tblMain").DataTable().row('.selected').data()["idAppointment"];
            }
        });
    }else{
        displayWarningAlert("No ha seleccionado una cita");
    }
}

function patientFile(){
     var row = $("#tblMain").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
    bootbox.confirm("Quiere ir al expediente del paciente?", function(result) {
        if(result){ 
            window.location.href = "/demo/patients/file/"+row["idPatient"]["idPatient"];
        }
    });
    }else{
        displayWarningAlert("No ha seleccionado una cita");
    }
}

function drawConsultationsMonthPerDayChart(){
    var data = [];
    data.push({name:"month",value:$("#selectMonth").val()});
    data.push({name:"year",value:$("#selectYear").val()});
    
    $.ajax({
            url: "/demo/graphs/getConsultsOfMonthPerDay",
            type: "POST",
            data: data,
            success:function(response){
                var data = [];
                
                //Format data
                for(var day in response){
                    data.push([parseInt(day),parseInt(response[day])]);
                }
                
                console.log(data);
		$.plot($("#graphPlaceholder"),[
                    {color:"#c0def0",label:"Consultas",data:data}
                ],
                {
                    legend:{
                        position:"se"
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
                        homeRange: {xmin:1,
                            xmax:new Date($("#selectYear").val(),
                            $("#selectMonth").val()+1,0).getDate(),
                            ymin:0,ymax:20},
                        position: { left: "60px", top: "20px" },
                        display: "block",
                        buttons:"basic"
                    },
                    axisLabels: {
                        show: true
                    },
                    xaxes: [{
                        axisLabel: "Dia"
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
