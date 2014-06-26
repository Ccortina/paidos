/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
    addSaveOnChangeToForm();
    initializeTablePatientAlergicDrugList();
    initializeTablePatientAvaibleDrugList();
});

function addSaveOnChangeToForm(){
    $( ".onChange" ).change(function() {
        ajaxCall($(this).closest('form').attr('id'));
        
    });
    
    $('a[href="#alergicos"]').on('shown.bs.tab', function (e) {
        $("#tblPADAvaibleDrug").DataTable().columns.adjust().draw();
    });
}


function initializeTablePatientAlergicDrugList(){
    $("#tblConsultationPatientAlergicDrug").DataTable({
        "bSort":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "searching":false,
        "ajax":"/demo/consultation/getPatientAlergicDrugs",
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "columns":[
            {"data":"drug"}
        ],
        "initComplete":function(){
            var table = $('#tblConsultationPatientAlergicDrug').DataTable();
            
            $('#tblConsultationPatientAlergicDrug tbody').on( 'click', 'tr', function (e) {
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

function initializeTablePatientAvaibleDrugList(){
    $("#tblPADAvaibleDrug").DataTable({
        "bSort":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "ajax":"/demo/consultation/getPatientAlergicAvaibleDrugs",
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "columns":[
            {"data":"drug"},
            {"data":"drugPresentationId.presentation"}
        ],
        "initComplete":function(){
            var table = $('#tblPADAvaibleDrug').DataTable();
            
            $('#tblPADAvaibleDrug tbody').on( 'click', 'tr', function (e) {
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

function submitAddAD(){
    var row = $("#tblPADAvaibleDrug").DataTable().row('.selected').data();
    
    if(typeof row == 'undefined'){
        displayDangerAlert(" No se ha seleccionado un Medicamento para agregar.");
    }else{
        $.ajax({
            url:"/demo/consultation/addPatientAlergicDrug",
            data:{idDrug:row["idDrug"]},
            type:"POST",
            error:function(jqXHR,textStatus,errorThrown){
                displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus,2000);
            },
            success:function(){
                $("#tblPADAvaibleDrug").DataTable().ajax.reload();
                $("#tblConsultationPatientAlergicDrug").DataTable().ajax.reload();
            }
        });
        $('#modalConsultationPatientAddAD').modal("hide");
    }
}

function submitDeleteAD(){
    var row = $("#tblConsultationPatientAlergicDrug").DataTable().row('.selected').data();
    
    if(typeof row == 'undefined'){
        displayDangerAlert(" No se ha seleccionado un Medicamento para agregar.");
    }else{
        $.ajax({
            url:"/demo/consultation/deletePatientAlergicDrug",
            data:{idDrug:row["idDrug"]},
            type:"POST",
            error:function(jqXHR,textStatus,errorThrown){
                displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus,2000);
            },
            success:function(){
                $("#tblConsultationPatientAlergicDrug").DataTable().ajax.reload();
                $("#tblConsultationPatientAlergicDrug").DataTable().ajax.reload();
            }
        });
    }
}


