/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializePatientsTable();
    
    $('.inputDate').inputmask("dd/mm/yyyy",{"oncleared": function(){
                                                $("#"+$(this).closest("form").attr('id')).data('bootstrapValidator').revalidateField($(this).attr('name'));
                                            }});
    $('.inputNormal').inputmask('Regex',{regex:'[A-Za-z0-9-]{1}[" "A-Za-z0-9-αινσϊρ]*'});
    $('.inputCurp').inputmask('Regex',{regex:"[A-Za-z]{1}[AEIOUaeiou]{1}[A-Za-z]{2}[0-9]{2}" +
                                        "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
                                        "[HMhm]{1}" +
                                        "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE|" +
                                        "as|bc|bs|cc|cs|ch|cl|cm|df|dg|gt|gr|hg|jc|mc|mn|ms|nt|nl|oc|pl|qt|qr|sp|sl|sr|tc|ts|tl|vz|yn|zs|ne)"+
                                        "[B-DF-HJ-NP-TV-Zb-df-hj-np-tv-z]{3}" +
                                        "[0-9A-Za-z]{1}[0-9]{1}$"});
    $('.inputTime').inputmask('Regex',{regex:'(""[0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]',
                                        "oncleared": function(){
                                                $("#"+$(this).closest("form").attr('id')).data('bootstrapValidator').revalidateField($(this).attr('name'));
                                        }});
    $('.inputDecimal').inputmask('Regex',{regex:"[0-9]+(\.[0-9][0-9]?)?"});
});

function initializePatientsTable(){
    $('#tblPatients').DataTable({
        "scrollY": "400px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/patients/getPatientsByDoctor",
        "columns":[
            {"data":"firstName"},
            {"data":"secondName"},
            {"data":"fatherLastName"},
            {"data":"motherLastName"},
            {"data":"birthday"},
            {"data":"sex"},
            {"render":function(data,type,row){ 
                if(row['active'] === 1){
                    return ('<span class="glyphicon glyphicon-ok"></span>');
                }else{
                    return ('<span class="glyphicon glyphicon-remove"></span>');
                }
            }},
            {"data":"idDoctor.idStaffMember",
            "visible":false}
        ],
        "initComplete":function(settings,json){

            $('#tblPatients tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblPatients').DataTable();
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

function getSelectedText(elementId) {
    var elt = document.getElementById(elementId);

    if (elt.selectedIndex == -1)
        return null;

    return elt.options[elt.selectedIndex].text;
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

function getAllPatients(){
   $("#tblPatients").DataTable().ajax.url('/demo/patients/getAllPatients').load();
   $("#btngetAllPatients").prop("disabled",true);
}

function filterPatientsByDoctor(){
    var idDoctor = $("#selectFilterPatientsByDoctor").val();
    
    $("#tblPatients").DataTable().column(7).search(idDoctor,false,false).draw();
}

function quitFilter(){
    $("#tblPatients").DataTable().column(7).search('',false,false).draw();
}

function deletePatient(){
    if(checkNotUndefined($("#tblPatients").DataTable().row('.selected').data())){
        var patient = $("#tblPatients").DataTable().row('.selected').data();
        if(patient['active'] === 1){
            bootbox.dialog({
              message: "Esta seguro de querer eliminar este paciente?",
              title: "Verficar",
              buttons: {
                confirm: {
                  label: "Si!",
                  className: "btn-primary",
                  callback: function(){
                      $.ajax({
                        url:"/demo/patients/deletePatient",
                        data:{idPatient:patient["idPatient"]},
                        type: "POST",
                        success:function(response){
                            //Reload the patient table
                            $("#tblPatients").DataTable().ajax.reload();
                            $("#btngetAllPatients").prop("disabled",false);
                        },
                        error: function(data, status, error) {
                            displayDangerAlert("Error al borrar el paciente");
                        }
                    });
                  }
                },
                cancel:{
                  label: "No!",
                  className: "btn-danger",
                  callback: function(){
                  }
                }
              }
            });
        }else{
            displayWarningAlert("Este paciente ya esta inactivo");
        }
    }else{
        displayWarningAlert("No se ha seleccionado un paciente");
    }
}

function checkNotUndefined(value){
    if(typeof value === 'undefined'){
        return false;
    }else{
        return true;
    }
}

function openPatientFile(){
    var patient = $("#tblPatients").DataTable().row(".selected").data();
     
    if(checkNotUndefined(patient)){
        window.location = "/demo/patients/file/"+patient['idPatient'];
    }else{
        displayWarningAlert("No ha seleccionado un paciente");
    }
}
