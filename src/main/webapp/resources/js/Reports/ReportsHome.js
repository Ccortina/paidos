/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){

    $("#selectReport").on("change",function(){
        showReport();
    });

    for (var i = new Date().getFullYear(); i > 1900; i--)
    {
        $(".selectYear").append($('<option />').val(i).html(i));
    }
});

function showReport(){
    var type = $("#selectReport").val();
    
    switch(type){
        case '1':
            $(".forHiding").hide();
            $("#divReport1Options").show();
            break;
        case '2':
            $(".forHiding").hide();
            renderReport2();
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
                        tickLength: 0
                    },
                    grid:{
                        hoverable:true
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: function(label, xval, yval, flotItem){
                            console.log(diagnosticsNames[xval]);
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

function compare(a,b){
    return a[1] < b[1] ? 1 : -1;
}