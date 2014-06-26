/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    initializeGraphsPatientDataTable();
        
    saveOnchangeEditConsultationData();
        
    $("#modalGraph").on('shown.bs.modal',drawGraph);
});

function initializeGraphsPatientDataTable(){
    
    $("#tblGraphsPastConsultation").DataTable({
       "bSort":false,
       "scrollY": "200px",
       "scrollCollapse": true,
       "paging": false,
       "info":false,
       "ajax":"/demo/consultation/getGraphPatientData",
       "columns":[
            {"data":"idConsultation",
                "visible": false},
            {"data":"date"},
            {"data":"age"},
            {"data":"weight"},
            {"data":"size"},
            {"data":"pc"},
            {"data":"ta"},
            {"data":"ta2"},
            {"data":"taaverage"},
            {"data":"temperature"},
            {"data":"imc"}],
        "initComplete":function(settings,json){
            var table = $("#tblGraphsPastConsultation").DataTable();
            $("#tblGraphsPastConsultation").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }
    });
}

function saveOnchangeEditConsultationData(){
    $( ".onChange2" ).change(function() {
        
        var s = $("#inputGraphSize").val(); 
        var w = $("#inputGraphWeight").val();
        $("#inputGraphIMC").val(w / ((s/100.00)*(s/100.00)));
        
        //ajaxCall("formGraphEdit");
        $.ajax({
            url:$('#formGraphEdit').attr("action"),
            data: $('#formGraphEdit').serializeObject(),
            type:"POST",
            success:function(response){
                $("#tblGraphsPastConsultation").DataTable().ajax.reload();
            }
         });
    });
}

function loadGraphPacientData(){
    
    var selectedRow = $("#tblGraphsPastConsultation").DataTable().row('.selected').data();
    $.each(selectedRow, function(name, val){

    $el = $('[name="'+name+'"]','#formGraphEdit');

    
    $el.val(val);
 

    });
}

function drawGraph(graph){
    
    $.jqplot('divChart',  [[[1, 2],[3,5.12],[5,13.1],[7,33.6],[9,85.9],[11,219.9]]]); 
    
    var url;
    switch(graph){
        case 1:
            url = "/demo/consultation/graph1";
            break;
            case 2:
                break;
                case 3:
                    break;
                    case 4:
                        break;
                        case 5:
                            break;
                            case 6:
                                break;
    }
    
    $.ajax({
        type:"POST",
        url:url,
        dataType:"json",
        success:function(response){
            console.log("algo");
            console.log(response);
        },
        error: function (xhr, ajaxOptions, thrownError) {
        alert(xhr.status);
        alert(ajaxOptions);
        alert(thrownError);
      }
    });
    
}
