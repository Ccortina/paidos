/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeTable();
});

function initializeTable(){
    $('#tblBirthdays').DataTable({
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info": false,
        "searching": false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        ajax:"/demo/patients/getMonthsBirthdays",
        "columns":[
            {"data":"firstName"},
            {"data":"fatherLastName"},
            {"data":"motherLastName"},
            {"data":"birthday"}
        ]
    });
}


