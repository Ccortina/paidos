/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeRelativesTable();
});

function initializeRelativesTable(){
    $('#tblRelative').DataTable({
        "scrollY": "300px",
        "scrollCollapse": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar",
            "lengthMenu": "Mostrar _MENU_ resultados por pagina",
             "paginate": {
                  "next": "Siguiente",
                  "previous": "Anterior"
              }
        },
        "ajax":"/demo/patients/getAllRelatives",
        "columns":[
            {"data":"fatherLastName"},
            {"render":function(data,row,full){
                return (replaceNull(full['motherLastName']));
            }},
            {"render":function(data,row,full){
                return (replaceNull(full['firstName']));
            }},
            {"render":function(data,type,row){ 
                if(parseInt(row['active']) === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }}
        ],
        "createdRow": function( row, data, dataIndex ) {
            if(data.active !== 1){
                $(row).css({"background-color":"#FDFD96"});
                $(row).addClass("vpSuspended");
            }
        },
        "initComplete": function(settings, json) {
            $('#tblRelative tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblRelative').DataTable();
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

function replaceNull(value){
    if(typeof value === 'undefined' || value === ''){
        return '';
    }else{
        return value;
    }
}

function checkNotUndefined(value){
    if(typeof value === 'undefined'){
        return false;
    }else{
        return true;
    }
}

function displayDangerAlert(message){
    var box = bootbox.alert("<center><strong>Advertencia!</strong>"+message+"</center>");
    box.find('.modal-content').css({'color': '#a94442','background-color': '#f2dede','border-color': '#ebccd1'});
    window.setTimeout(function(){bootbox.hideAll();}, 2000);
}

function displayWarningAlert(message){
    var box = bootbox.alert("<center><strong>Advertencia! </strong>"+message+"</center>");
    box.find('.modal-content').css({'color':'#8a6d3b','background-color':'#fcf8e3','border-color':'#faebcc'});
    window.setTimeout(function(){bootbox.hideAll();}, 2000);
}

function newRelative(){
    $('#relativesTabMenu a[href="#tabNew"]').tab('show');
}

