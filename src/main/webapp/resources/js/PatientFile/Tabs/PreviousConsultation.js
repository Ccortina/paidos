/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeTablePreviousConsultation();
});

function initializeTablePreviousConsultation(){
    $("#tblPreviousConsultation").DataTable({
        "ajax":{
            "url":"/demo/patients/previousConsultation"
        },
        "columns":[
            {"data":"idAppointment.date"},
            {"targets": 1,
             "data": null,
             "render": function ( data, type, full, meta ) {
                 return data.;
                }
            }
        ]
        /*"order":[[0,'asc']],
        "displayLength": 25,
        "drawCallback":function( settings ){
            var api = this.api();
            var rows = api.rows( {page:'current'} ).nodes();
            var last = null;
            
            api.column(0,{page:'current'}).data().each(function (group,i){
                if( last !== group ){
                    $(rows).eq( i ).before(
                        '<tr class="group"><td colspan="5">'+group+'</td></tr>'
                    );
                            
                    last = group;        
                }
            });*/
        }
    });
}

