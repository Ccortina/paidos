/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    initializeSelectedActivitiesList();
    
    initializeActivitiesList();    
        
    initializeActivityVaccineList("tblNewActivityVaccine");
        
    initializeActivityVaccineList("tblEditActivityVaccine");
    
    $('a[href="#actividades"]').on('shown.bs.tab', function (e) {
        $("#tblActivities").DataTable().columns.adjust().draw();
        $("#tblSelectedActivities").DataTable().columns.adjust().draw();
    });
    
});

function initializeActivitiesList()
{   
    $('#tblActivities').DataTable({
        "scrollY": "200px",
        "paging": false,
        "info": false,
        "ordering": [[ 2, "asc" ]],
        "scrollCollapse": true,
        "ajax":"/demo/consultation/getActivities",
        "columns":[
            {"data":"idActivity","visible":false},
            {"data":"activity"},
            {"data":"idActivityType.type"},
            {"data":"activityCost"}
        ],
        "initComplete": function(settings, json) {
            var table = $('#tblActivities').DataTable();
    
            $('#tblActivities tbody').on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
            
            table.rows().nodes().each(function(value,rowIndex){
               var data = table.row(value).data();
               
               if(data["consultationDefault"] === 1){
                   $("#tblSelectedActivities").DataTable().row.add(data).draw();
                   table.row(value).remove().draw(false);
               }
            });
        },
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        }
    });
}

function initializeSelectedActivitiesList()
{
    $('#tblSelectedActivities').DataTable({
        "ordering":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "searching": false,
        "info": false,
        "columns":[
            {"data":"idActivity","visible":false},
            {"data":"activity"},
            {"data":"activityCost"},
            {"render":function(data, type, row){
                if(row["includeInBill"] === 1){
                    return ("Si");
                }else{
                    return ("No");
                }
            }},
            {"defaultContent": "<button type='button' class='btn btn-danger' onclick='deleteSelectedActivitiescRow(this)'>Eliminar</button>"}
        ],
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "initComplete": function(settings, json) {
            var table = $('#tblSelectedActivities').DataTable();
    
            $('#tblSelectedActivities tbody').on( 'click', 'tr', function (e){
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

function deleteSelectedActivitiescRow(row){
    var table = $('#tblActivities').DataTable();
    var data = $("#tblSelectedActivities").DataTable().row($(row).parent().parent()[0]).data();
    table.row.add(data).draw();
    $("#tblSelectedActivities").DataTable().row($(row).parent().parent()[0]).remove().draw();
    
}

function addSelectedAcitivitiesRow(){
    var table = $("#tblSelectedActivities").DataTable();
    table.row.add($('#tblActivities').DataTable().row('.selected').data()).draw();
    table.row($('#tblActivities').DataTable().row('.selected')).remove().draw(false);
}

function resetEAVSearch(){
    $("#tblEditActivityVaccine").DataTable().column(0).search('',false,false).draw();
}

function initializeActivityVaccineList(tbl)
{
    $('#'+tbl).DataTable({
        "ordering":false,
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
    });
}

function loadEditActivity(){
    var activity = $('#tblActivities').DataTable().row('.selected').data();
    if(checkNotUndefined(activity)){
        $.each(activity, function(name, val){
            
            $el = $('[name="'+name+'"]','#editActivityForm');

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
                          $("#tblEditActivityVaccine").DataTable().column(0).search(val["idVaccine"],false,false).draw();
                      }else{
                          $el.val(null);
                      }  
                    }else{
                        $el.val(val);
                    }

                }
            }
        });
        $("#modalEditActivity").modal('show');
    }else{
        displayWarningAlert("No se ha seleccionado una actividad");
    }
    
}

function loadEditSelectedActivity(){
    var activity = $('#tblSelectedActivities').DataTable().row('.selected').data();
    if(checkNotUndefined(activity)){
        $.each(activity, function(name, val){
            
            $el = $('[name="'+name+'"]','#editSelectedActivityForm');

            if( $el.is('select') ){
                $("option",$el).each(function(){
                    if(this.value === val ){ this.selected = true; }
                });
            }else{
                switch($el.attr("type")){
                case 'checkbox':
                    if(val === 1){
                        $el.prop('checked', true);
                    }else{
                        $el.prop('checked', false);
                    }
                    break;
                case 'radio':
                    $el.filter('[value="'+val+'"]').attr('checked', 'checked');
                    break;
                default:
                    if( name === "idVaccine"){
                      if(val !== null){
                          $el.val(val["idVaccine"]);
                          $("#tblEditActivityVaccine").DataTable().column(0).search(val["idVaccine"],false,false).draw();
                      }else{
                          $el.val(null);
                      }  
                    }else{
                        $el.val(val);
                    }

                }
            }
        });
        $("#modalEditSelectedActivity").modal('show');
    }else{
        displayWarningAlert("No se ha seleccionado una actividad");
    }
    
}

function editActivity(){
    $.ajax({
        url:$('#editActivityForm').attr("action"),
        data: $('#editActivityForm').serializeObject(),
        type:"POST",
        success:function(response){
                $('#tblActivities').DataTable().ajax.reload();
        },
        error:function(response){
                displayDangerAlert("Error en la operacion");
        }
     });
}

function editSelectedActivity(){
    
    var originalData = $('#tblSelectedActivities').DataTable().row('.selected').data();
    var price = $("#editSelectedActivityForm input[name=activityCost]").val();
    var include = $("#editSelectedActivityForm input[name=includeInBill]").prop("checked");
    if(checkNotEmptyString(price)){
        originalData["activityCost"] = price;
        if(include){
            originalData["includeInBill"] = 1;
        }else{
            originalData["includeInBill"] = 0;
        }
        $('#tblSelectedActivities').DataTable().row('.selected').data(originalData).draw();
        
        $("#modalEditSelectedActivity").modal('hide'); 
    }else{
        displayWarningAlert("Falta informacion");
    }
}

function addActivity(){
    var activity = $("#newActivityForm input[name=activity]").val();
    var cost = $("#newActivityForm input[name=activityCost]").val();
    var type = $("#newActivityForm input[name=idActivityType]").val();
    
    if(checkNotEmptyString(activity) && checkNotEmptyString(cost) && checkNotEmptyString(type)){
        $.ajax({
            url:$('#newActivityForm').attr("action"),
            data: $('#newActivityForm').serializeObject(),
            type:"POST",
            success:function(response){
                    var table = $('#tblActivities').DataTable();
                    table.ajax.reload(function(json){
                        table.rows().nodes().each(function(value,rowIndex){
                           var data = table.row(value).data();
                           if(data["idActivity"] === parseInt(response)){
                               $("#tblSelectedActivities").DataTable().row.add(data).draw();
                               table.row(value).remove().draw(false);
                           }
                        });
                    });   
            },
            error:function(response){
                    displayDangerAlert("Error en la operacion");
            }
        });
        
       $("#modalAddNewActivity").modal('hide'); 
    }else{
        displayDangerAlert("Falta informacion");
    }
}

