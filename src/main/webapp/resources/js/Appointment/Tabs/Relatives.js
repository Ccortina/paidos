/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready({
    
});

function initializePatientRelativeAppointmentTable(){
    $('#tblRelativesApp').DataTable({
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":"/demo/patients/getPatientRelatives",
            "data": function(){
                return {idPatient:$("#inputIdPatientApp").val()};
            }
        },
        "columns":[
            {"data":"relative.fatherLastName"},
            {"data":"relative.motherLastName"},
            {"data":"relative.firstName"},
            {"data":"idRelationship.relationship"}
        ],
        "initComplete": function(settings, json) {
            $('#tblRelativesApp tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblRelativesApp').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    //console.log(table.row('.selected').data());
                    loadRelativeData();
                }   
            });
        }
    });
}

function loadRelativeData(){
    
    var data = $("#tblRelativesApp").DataTable().row('.selected').data()['relative'];
    
    var inputs = $('#formRelativeDataApp :input');
    
    //Collect form data
    inputs.each(function() {
        if(this.name === "religion"){
            //data.push({name:this.name,value:$(this).prop('checked')});
        }else{
            $(this).val(data[this.name]);
        }
        
    });
}

