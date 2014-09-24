/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
$(document).ready(function(){
    initializeGraph();
});

function initializeGraph(){
    var type = $("#graphType").val();
    
    switch(type){
        case '1':
            $("#graphTitle").text("Peso para la edad (0 a 36 meses)");
            initializeSpecificGraph('Edad (meses)','Peso (Kg)',3,1,0,0,36,20,
                "%s: Edad (Meses): %x.2 , Peso (Kg): %y.2",
                    "/demo/graphs/getPatientDataWeight4Age");
            break;
        case '2':
            $("#graphTitle").text("Talla para la edad (0 a 36 meses)");
            initializeSpecificGraph('Edad (meses)','Talla (cm)',3,5,0,40,36,110,
                "%s: Edad (Meses): %x.2 , Talla (cm): %y.2",
                    "/demo/graphs/getPatientDataSize4Age");
            break;
        case '3':
            $("#graphTitle").text("Peso para la talla");
            initializeSpecificGraph('Talla (cm)','Peso (Kg)',5,1,40,0,110,20,
                "%s: Talla (cm): %x.2 , Peso (Kg): %y.2",
                    "/demo/graphs/getPatientDataSizeWeight");
            break;
        case '4':
            $("#graphTitle").text("Circunferencia cefalica para la edad");
            initializeSpecificGraph('Edad (meses)','Circunferencia cefalica (cm)',3,2,0,30,36,54,
                "%s: Edad (meses): %x.2 , CC (cm): %y.2",
                    "/demo/graphs/getPatientDataAgePc");
            break;
        case '5':
            $("#graphTitle").text("Peso para la estatura");
            initializeSpecificGraph('Estatura (cm)','Peso (Kg)',5,1,75,8,125,30,
                "%s: Estatura (cm): %x.2 , Peso (Kg): %y.2",
                    "/demo/graphs/getPatientDataHeightWeight");
            break;
        case '6':
            $("#graphTitle").text("Peso para la edad (2 a 20 años)");
            initializeSpecificGraph('Edad (meses)','Peso (Kg)',12,5,24,10,240,105,
                "%s: Edad (Meses): %x.2 , Peso (Kg): %y.2",
                    "/demo/graphs/getPatientDataWeight4Age24to240");
            break;
        case '7':
            $("#graphTitle").text("Estatura para la edad (24 a 240 meses)");
            initializeSpecificGraph('Edad (meses)','Estatura (cm)',24,5,24,75,240,195,
                "%s: Edad (Meses): %x.2 , Estatura (cm): %y.2",
                    "/demo/graphs/getPatientDataHeight4Age");
            break;
        case '8':
            $("#graphTitle").text("IMC para la edad (2 a 20 años)");
            initializeSpecificGraph('Edad (meses)','IMC (Kg/m^2)',24,1,24,13,240,35,
                "%s: Edad (Meses): %x.2 , Peso (Kg): %y.2",
                    "/demo/graphs/getPatientDataAgeBmi");
            break; 
    }
}

function initializeSpecificGraph(xAxisLabel,yAxisLabel,stepsX,stepsY,xMin,yMin,xMax,yMax,tooltipText,ajaxUrl){
    
    $.ajax({
            url: ajaxUrl,
            type: "POST",
            success:function(response){
                //Format the json response
                var p3 = [],p5 =[], p10=[], p25=[],p50=[],p75=[],p90=[],p95=[],p97=[],pPatient =[];
                
                p3 = formatData(response["p3"]).sort(compare);
                p5 = formatData(response["p5"]).sort(compare);
                p10 = formatData(response["p10"]).sort(compare);
                p25 = formatData(response["p25"]).sort(compare);
                p50 = formatData(response["p50"]).sort(compare);
                p75 = formatData(response["p75"]).sort(compare);
                p90 = formatData(response["p90"]).sort(compare);
                p95 = formatData(response["p95"]).sort(compare);
                p97 = formatData(response["p97"]).sort(compare);
                pPatient = formatData(response["pPatient"]).sort(compare);

		$.plot($("#graphPlaceholder"),[
                    {color:"#ff535c",label:"P3",data:p3},
                    {color:"#fff53a",label:"P5",data:p5},
                    {color:"#f986ff",label:"P10",data:p10},
                    {color:"#03396c",label:"P25",data:p25},
                    {color:"#b6fcd5",label:"P50",data:p50},
                    {color:"#03396c",label:"P75",data:p75},
                    {color:"#f986ff",label:"P90",data:p90},
                    {color:"#fff53a",label:"P95",data:p95},
                    {color:"#ff535c",label:"P97",data:p97},
                    {color:"#0a1237",label:"Paciente",data:pPatient,lines:{show:false},points:{show:true}}
                ],
                {
                    legend:{
                        position:"se"
                    },
                    series: {
                            lines: { show: true },
                            points: { show: false }
                    },
                    grid:{
                        hoverable:true
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: tooltipText,
                        shifts: {
                                x: -60,
                                y: 25
                        }
                    },
                    xaxis:{
                        tickSize: stepsX
                    },
                    yaxis:{
                        tickSize: stepsY
                    },
                    zoom: {
                        interactive: true
                    },
                    pan: {
                        interactive: true
                    },
                    navigationControl: {
                        homeRange: {xmin:xMin,xmax:xMax,ymin:yMin,ymax:yMax},
                        position: { left: "60px", top: "20px" },
                        display: "block"
                    },
                    axisLabels: {
                        show: true
                    },
                    xaxes: [{
                        axisLabel: xAxisLabel
                    }],
                    yaxes: [{
                        position: 'left',
                        axisLabel: yAxisLabel
                        
                    }]
                });
            },
            error: function(data, status, error) {
                console.error(error);
            }
        });
}

function formatData(percentile){
    var data=[];
    
    for(var months in percentile){
        data.push([parseFloat(months),percentile[months]]);
    }
    
    return data;
}

function compare(a,b){
    return a[0] > b[0] ? 1 : -1;
}
