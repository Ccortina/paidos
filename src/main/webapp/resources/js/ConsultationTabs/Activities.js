/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    initializeActivitiesList();
        
    initializeSelectedActivitiesList();
        
    initializeActivityVaccineList("tblNewActivityVaccine");
        
    initializeActivityVaccineList("tblEditActivityVaccine");
    
});

function initializeActivitiesList()
{   
    $('#tblActivities').dataTable({
        "scrollY": "200px",
        "bPaginate": false,
        "bInfo": false,
        "aaSorting": [[ 2, "asc" ]],
        "sAjaxSource":"/demo/consultation/getAllActivities",
        "aoColumns":[
            {"mData":"activity"},
            {"mData":"idActivityType.type"},
            {"mData":"activityCost"}
        ],
        "initComplete": function(settings, json) {
            for( var i=0; i < json["aaData"].length; i++  ){
                
                //Add a delete button for to the objects
                json["aaData"][i].deleteButton = "<button type='button' class='btn btn-danger' onclick='deleteSelectedActivitiescRow(this)'>\n\
                                                      Eliminar</button>";
                
                //Add the activities that are default to the selectedactivities list
                if( json["aaData"][i]["consultationDefault"] === 1){
                    $("#tblSelectedActivities").dataTable().fnAddData(json["aaData"][i]);
                }
            }
            addRowSelectionActivitiesList();
        },
        "oLanguage": { 
            "sSearch": "Buscar:",
            "sEmptyTable": "No hay informacion en la tabla."}
    });
}

function addRowSelectionActivitiesList(){
    var table = $('#tblActivities').dataTable();
    
    $('#tblActivities tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('row_selected')){
                $(this).removeClass('row_selected');
            }
            else
                {
                    table.$('tr.row_selected').removeClass('row_selected');
                    $(this).addClass('row_selected');
                }
        });
}

function initializeSelectedActivitiesList()
{
    $('#tblSelectedActivities').dataTable({
        "bSort":false,
        "sScrollY": "200px",
        "bScrollCollapse": true,
        "bPaginate": false,
        "bFilter": false,
        "bInfo": false,
        "aoColumns":[
            {"mData":"activity"},
            {"mData":"activityCost"},
            {"mData":"deleteButton"}
        ],
        "oLanguage": {
            "sEmptyTable": "No hay informacion en la tabla."}
    });
}

function deleteSelectedActivitiescRow(row){
    $("#tblSelectedActivities").dataTable().fnDeleteRow($(row).parent().parent()[0]);
}

function addSelectedAcitivitiesRow(){
    var table = $("#tblSelectedActivities").DataTable();
    
    table.row.add($('#tblActivities').DataTable().row('.row_selected').data()).draw();
}

function initializeActivityVaccineList(tbl)
{
    $('#'+tbl).DataTable({
        "bSort":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "ajax":"/demo/consultation/getAllVaccines",
        "columns":[
            {"data":"idVaccine",
                "visible":false},
            {"data":"vaccine"}
        ],
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search":"Buscar"},
        "initComplete": function(settings, json) {
            addRowSelectionActivityVaccineList(tbl);
        }
    });
}

function addRowSelectionActivityVaccineList(tbl){
    var table = $('#'+tbl).DataTable();
    
    $('#'+tbl+' tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('selected')){
                $(this).removeClass('selected');
                $('[name="idVaccine"]','#'+tbl).val(null);
            }
            else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    if(tbl === "tblEditActivityVaccine"){
                        $('#editActivityIdVaccine').val(table.row(this).data()["idVaccine"]);
                    }else{
                        $('#newActivityIdVaccine').val(table.row(this).data()["idVaccine"]);
                    }
                }
        });
}

function editSelectedActivity(){
    var activity = $('#tblActivities').DataTable().row('.row_selected').data();
    
    $.each(activity, function(name, val){

    $el = $('[name="'+name+'"]','#editActivityForm');
    //console.log($('#tblEditActivityVaccine').DataTable().rows().column(0).data());
    
    if( $el.is('select') ){
        $("option",$el).each(function(){
            if(this.value === val ){ this.selected = true; }
        });
    }else{
        switch($el.attr("type")){
        case 'checkbox':
            $el.attr('checked', 'checked');
            break;
        case 'radio':
            $el.filter('[value="'+val+'"]').attr('checked', 'checked');
            break;
        default:
            if( name === "idVaccine"){
              if(val !== null){
                  $el.val(val["idVaccine"]);
              }else{
                  $el.val(null);
              }  
            }else{
                $el.val(val);
            }
            
        }
    }
    });
    
}

