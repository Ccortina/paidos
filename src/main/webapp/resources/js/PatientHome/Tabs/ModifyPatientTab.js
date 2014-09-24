/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
});

function modifyPatient(){
    if(checkNotUndefined($("#tblPatients").DataTable().row('.selected').data())){
        
        var idPatient = $("#tblPatients").DataTable().row('.selected').data()["idPatient"];
        $.ajax({
            url:"/demo/patients/getPatientBasicData",
            data:{idPatient:idPatient},
            type: "POST",
            success:function(response){
                $("#inputModifyPatientName").val(response['firstName']);
                $("#inputModifyPatientFLName").val(response['fatherLastName']);
                $("#inputModifyPatientMLName").val(response['motherLastName']);
                $("#inputModifyPatientBirthday").val(moment(response['birthday']).format('DD/MM/YYYY'));
                $("#inputModifyPatientCurp").val(response['curp']);
                $("#inputModifyPatientNotes").val(response['notes']);
                $("option",$("#inputModifyPatientGender")).each(function(){
                       if(this.value === response['sex']['idGender']){this.selected=true;}
                    });
                $("option",$("#inputModifyPatientMDoctor")).each(function(){
                       if(this.value == response['idDoctor']['idStaffMember']){this.selected=true;}
                    });

                if ( ! $.fn.DataTable.isDataTable( '#tblRelatives2' ) ) {
                    initializeRelatives2Table();
                }else{
                    $('#tblRelatives2').DataTable().ajax.reload();
                }
                
                if ( ! $.fn.DataTable.isDataTable( '#tblSelectedRelatives2' ) ) {
                    initializeSelectedRelatives2Table();
                }else{
                    $('#tblSelectedRelatives2').DataTable().ajax.reload();
                }
                $("#hiddenModifyIdPatient").val(idPatient);
                $('#patientTabMenu a[href="#tabEdit"]').tab('show');
            },
            error: function(data, status, error) {
                displayDangerAlert("error");
            }
        });
    }else{
        displayWarningAlert("Debe seleccionar un paciente para modificar");
    }   
}

function initializeRelatives2Table(){
    $('#tblRelatives2').DataTable({
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":"/demo/patients/getPatientAvaibleRelatives",
            "data": {idPatient:$("#tblPatients").DataTable().row('.selected').data()["idPatient"]}
        },
        "columns":[
            {"data":"relative.idRelative","visible":false},
            {"data":"relative.fatherLastName"},
            {"data":"relative.motherLastName"},
            {"data":"relative.firstName"},
            {"data":"relative.rfc"},
            {"data":"relative.homePhone"}],
        "initComplete": function(settings, json) {
            $('#tblRelatives2 tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblRelatives2').DataTable();
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

function initializeSelectedRelatives2Table(){
    $('#tblSelectedRelatives2').DataTable({
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
            "data": {idPatient:$("#tblPatients").DataTable().row('.selected').data()["idPatient"]}
        },
        "columns":[
            {"data":"relative.fatherLastName"},
            {"data":"relative.motherLastName"},
            {"data":"relative.firstName"},
            {"data":"relative.rfc"},
            {"data":"idRelationship.relationship"}
        ],
        "initComplete": function(settings, json) {
            $('#tblSelectedRelatives2 tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblSelectedRelatives2').DataTable();
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

function addPatientRelativeRelationshipM(){
    var data = $("#tblRelatives2").DataTable().row('.selected').data();
    $("#tblRelatives2").DataTable().row('.selected').remove().draw();
    data.idRelationship.idRelationship = $("#selectModifyPatientRRelationship").val();
    data.idRelationship.relationship = getSelectedText("selectModifyPatientRRelationship");

    $('#tblSelectedRelatives2').DataTable().row.add(data).draw();
}

function deletePatientRelativeRelationshipM(){
    //return relative to previous table
    var data =$("#tblSelectedRelatives2").DataTable().row('.selected').data();
    $('#tblRelatives2').DataTable().row.add(data).draw();
    $("#tblSelectedRelatives2").DataTable().row('.selected').remove().draw();
}

function saveModifiyPatient(){
    var data = [];
    var inputs = $('#formModifyPatient :input');
    //Collect form data
    inputs.each(function() {
        data.push({name:this.name,value:$(this).val()});
    });
    
    //Collect relative and relationship type
    var count = 0;
    $('#tblSelectedRelatives2').DataTable().rows().data().each(function(val,rIndex){
        count++;
        data.push({name:"idRelative"+count,value:val['relative']['idRelative']});
        data.push({name:"idRelationship"+count,value:val['idRelationship']['idRelationship']});  
    });
    data.push({name:"relativesCounter",value:count});
    //console.log(data);
    //Send to controller
    $.ajax({
        url:"/demo/patients/updatePatient",
        data:data,
        type: "POST",
        success:function(response){
            //Reload the patient table
            $("#tblPatients").DataTable().ajax.reload();
            $("#formNewPatient").data('bootstrapValidator').resetForm();
            $('#patientTabMenu a[href="#tabMain"]').tab('show');
        },
        error: function(data, status, error) {
            displayDangerAlert("error");
        }
    });
}

