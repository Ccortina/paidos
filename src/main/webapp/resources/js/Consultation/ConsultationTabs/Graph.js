/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    initializeConsultationMeasureTable();
    
    $('#patientFileTabMenu a[href="#graficas"]').on('shown.bs.tab',function(){
        $("#tblConsultationMeasure").DataTable().columns.adjust().draw();
    });
});

function initializeConsultationMeasureTable(){
    $("#tblConsultationMeasure").DataTable({
        "scrollY": "150px",
        "order":[0,'desc'],
        "scrollCollapse": false,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        ajax:"/demo/consultation/getConsultationsByPatient",
        "columns":[
            {"render":function(data,row,full){
                    return (moment(full['idAppointment']['date']).format('DD/MM/YYYY'));
            }},
            {"render":function(data,row,full){
                    return (getAge(full['idAppointment']['idPatient']['birthday'],full['idAppointment']['date']));
            }},
            {"data":"weigth"},
            {"data":"size"},
            {"data":"pc"},
            {"render":function(data,row,full){
                    return (full["ta"]+" / "+full["ta2"]+" - "+full["taAverage"]);
            }},
            {"data":"temperature"},
            {"data":"bmi"}
        ],
    });
}

function graph(type){
    window.open('/demo/graphs/'+type);
}

