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
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/patients/getAllRelatives",
        "columns":[
            {"data":"fatherLastName"},
            {"render":function(data,row,full){
                return (replaceNull(full['motherLastName']));
            }},
            {"render":function(data,row,full){
                return (replaceNull(full['firstName'])+" "+replaceNull(full['secondName']));
            }},
            {"render":function(data,type,row){ 
                if(row['active'] === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }}
        ],
        "initComplete": function(settings, json) {
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

function replaceNull(value){
    if(typeof value === 'undefined' || value === ''){
        return '';
    }else{
        return value;
    }
}


