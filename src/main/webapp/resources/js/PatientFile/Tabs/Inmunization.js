/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeProgrammedVaccineTable();
    
    initializeAvaibleVaccineTable();
    
    initializeExpiredVaccineTable();
    
    initializeSuspendedVaccineTable()
    
    $('a[href="#inmunizaciones"]').on('shown.bs.tab', function (e) {
        $("#tblConsultationPatientInmunization").DataTable().columns.adjust().draw();
    });
    
    $('#modalConsultationPatientEditPV').on('show.bs.modal',function(event){
        loadPVEdit(event);
    });
    
    $('#modalConsultationPatientAddPV').on('shown.bs.modal',function(event){
        $('#tblPVAvaibleVaccine').DataTable().columns.adjust().draw();
    });

    $('.inputDate').inputmask("dd/mm/yyyy");
    $('.inputNormal').inputmask('Regex',{regex:'[A-Za-z0-9-]{1}[" "A-Za-z0-9-αινσϊρ]*'});
});

function initializeProgrammedVaccineTable(){
        
    $("#tblConsultationPatientInmunization").DataTable({
        "ordering":false,
        "scrollY": "300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/patients/getPatientProgrammedVaccine",
        "columns":[
            {"data":"vaccine.vaccine"},
            {"data":"vaccine.idVaccineType.type"},
            {"data":"programedDate"},
            {"render":function(data, type, full, meta){
                
                return (full.vaccine.dayApply+" D "+full.vaccine.monthApply+" M "+full.vaccine.yearApply+" A");
            }},
            {"render":function(data, type, full, meta){
                if(full.applicationDate === null){
                    return ("Pendiente");
                }else{
                    return getAge(full.patient.birthday,full.applicationDate);
                }
            }},
            {"render":function(data, type, full, meta){
                if(full.applicationDate === null){
                    if(full.suspended === 0){
                        return "P";
                    }else{
                        if(full.suspended === 1){
                            return "S";
                        }else{
                            return "V";
                        }
                    }
                }else{
                    return "A";
                }
            }}    
        ],
        "createdRow": function( row, data, dataIndex ) {
            if(data.applicationDate === null){
                if(data.suspended !== 0){
                    if(data.suspended === 1){
                        $(row).css({"background-color":"#FDFD96"});
                        $(row).addClass("vpSuspended");
                    }else{
                        $(row).css({"background-color":"#FF6961"});
                        $(row).addClass("vpExpired");
                    }
                }
            }else{
                $(row).css({"background-color":"#77DD77"});
                $(row).addClass("vpNormal");
            }
        },
        "initComplete":function(){
            var table = $('#tblConsultationPatientInmunization').DataTable();
            
            $('#tblConsultationPatientInmunization tbody').on( 'click', 'tr', function (e) {
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

function loadPVEdit(e){ 
    row = $("#tblConsultationPatientInmunization").DataTable().row('.selected').data();
    if(typeof row == 'undefined'){
        displayDangerAlert(" No se ha seleccionado una vacuna.");
        e.preventDefault();
    }else{
        //load Vaccine section
        populateFormWithJSON("formEditPVVaccineSection",row['vaccine']);
        //load Patient Vaccine section
        populateFormWithJSON("formEditPVPatientVaccineSection",row);
    }
}

function submitEditPV(){
    var suspended = $("#formEditPVPatientVaccineSection input[name=suspended]").prop('checked');
    if(suspended){
        var notes = $("#formEditPVPatientVaccineSection input[name=notes]").val();
        if(notes === null || notes === "" ){
            displayDangerAlert("Las observaciones no pueden estar vacias en caso de suspension.");
        }else{
            ajaxCall("formEditPVPatientVaccineSection");
            $('#modalConsultationPatientEditPV').modal("hide");
        }
    }else{
        ajaxCall("formEditPVPatientVaccineSection");
        $('#modalConsultationPatientEditPV').modal("hide");
        $("#tblConsultationPatientInmunization").DataTable().ajax.reload();
        $("#tblConsultationPatientInmunization").DataTable().draw();
    } 
}

function checkSuspendedVaccine(){
        if($("#formEditPVPatientVaccineSection input[name=suspended]").prop('checked')){
            $("#formEditPVPatientVaccineSection input[name=programedDate]").attr("readonly",true);
            $("#formEditPVPatientVaccineSection input[name=applicationDate]").attr("readonly",true);
        }else{
            $("#formEditPVPatientVaccineSection input[name=programedDate]").attr("readonly",false);
            $("#formEditPVPatientVaccineSection input[name=applicationDate]").attr("readonly",false);
        }
}

function initializeAvaibleVaccineTable(){
    $("#tblPVAvaibleVaccine").DataTable({
        "ordering":false,
        "scrollY":"300px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/consultation/getAvaibleVaccine",
        "columns":[
            {"data":"vaccine"},
            {"data":"idVaccineType.type"},
            {"render":function(data, type, full, meta){
                return (full.dayApply + " D "+full.monthApply+" M "+full.yearApply+" Y");
            }}
        ],
        "initComplete":function(){
            var table = $('#tblPVAvaibleVaccine').DataTable();
            
            $('#tblPVAvaibleVaccine tbody').on( 'click', 'tr', function (e) {
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

function submitAddPV(){
    var row = $("#tblPVAvaibleVaccine").DataTable().row('.selected').data();
    
    if(typeof row === 'undefined'){
        displayDangerAlert(" No se ha seleccionado una vacuna para agregar.");
    }else{
        $.ajax({
            url:"/demo/consultation/addProgrammedVaccine",
            data:{idVaccine:$("#tblPVAvaibleVaccine").DataTable().row('.selected').data()["idVaccine"]},
            type:"POST",
            error:function(jqXHR,textStatus,errorThrown){
                displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
            },
            success:function(){
                $("#tblPVAvaibleVaccine").DataTable().ajax.reload();
                $("#tblConsultationPatientInmunization").DataTable().ajax.reload();
            }
        });
        $('#modalConsultationPatientAddPV').modal("hide");
    }
}

function deletePV(){
    var row = $("#tblConsultationPatientInmunization").DataTable().row('.selected').data();
    
    if(typeof row === 'undefined'){
        displayDangerAlert(" No se ha seleccionado una vacuna para eliminar.");
    }else{
        var box = bootbox.confirm("<strong>Advertencia!</strong>Esta seguro de eliminar esta vacuna programada?", function(result) {
                    if(result){
                        
                        $.ajax({
                            url:"/demo/consultation/deleteProgrammedVaccine",
                            data:{idVaccine:row["vaccine"]["idVaccine"]},
                            type:"POST",
                            error:function(jqXHR,textStatus,errorThrown){
                                displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
                            },
                            success:function(){
                                $("#tblConsultationPatientInmunization").DataTable().ajax.reload();
                            }
                        });
                    }
                  });
        box.find('.modal-content').css({'color': '#8a6d3b','background-color': '#fcf8e3','border-color': '#faebcc'});
    }
}

function initializeExpiredVaccineTable(){
    $("#tblPVExpiredVaccine").DataTable({
            "ordering":false,
            "scrollY":"200px",
            "scrollCollapse": true,
            "paging": false,
            "info":false,
            "language": {
                "emptyTable": "No hay informacion en la tabla.",
                "search": "Buscar"
            },
            "ajax":"/demo/consultation/getExpiredVaccine",
            "columns":[
                {"data":"vaccine.vaccine"}
            ],
            "initComplete":function(){
                var table = $('#tblPVExpiredVaccine').DataTable();

                $('#tblPVExpiredVaccine tbody').on( 'click', 'tr', function (e) {
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

function initializeSuspendedVaccineTable(){
    $("#tblPVSuspendedVaccine").DataTable({
            "ordering":false,
            "scrollY":"200px",
            "scrollCollapse": true,
            "paging": false,
            "info":false,
            "language": {
                "emptyTable": "No hay informacion en la tabla.",
                "search": "Buscar"
            },
            "ajax":"/demo/consultation/getSuspendedVaccine",
            "columns":[
                {"data":"vaccine.vaccine"}
            ],
            "initComplete":function(){
                var table = $('#tblPSuspendedVaccine').DataTable();

                $('#tblPVSuspendedVaccine tbody').on( 'click', 'tr', function (e) {
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

function suspendPV(){
    var row =  $("#tblPVExpiredVaccine").DataTable().row('.selected').data();
    
    if(typeof row == 'undefined'){
        displayDangerAlert("No se ha seleccionado una vacuna para suspender.");
    }else{
        if($("#inputPVSuspensionNotes").val() === ""){
            displayDangerAlert("El motivo de la suspension no puede estar vacio.");
        }else{
            $.ajax({
                url:"/demo/consultation/suspendProgrammedVaccine",
                data:{idVaccine:row["vaccine"]["idVaccine"]},
                type:"POST",
                error:function(jqXHR,textStatus,errorThrown){
                    displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
                },
                success:function(){
                    $("#tblPVExpiredVaccine").DataTable().ajax.reload();
                    $("#tblPVSuspendedVaccine").DataTable().ajax.reload();
                }
            });
        }
    }
}

function retrivePV(){
    var row =  $("#tblPVSuspendedVaccine").DataTable().row('.selected').data();
    
    if(typeof row == 'undefined'){
        displayDangerAlert("No se ha seleccionado una vacuna.");
    }else{

        $.ajax({
            url:"/demo/consultation/retriveProgrammedVaccine",
            data:{idVaccine:row["vaccine"]["idVaccine"]},
            type:"POST",
            error:function(jqXHR,textStatus,errorThrown){
                displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
            },
            success:function(){
                $("#tblPVExpiredVaccine").DataTable().ajax.reload();
                $("#tblPVSuspendedVaccine").DataTable().ajax.reload();
            }
        });
        
    }
}

function populateFormWithJSON( idForm , data ){
    
    $.each(data, function(name, val){
        $el = $('[name="'+name+'"]','#'+idForm);
        switch(name){
            case 'idVaccineType':
                $el.val(val['type']);
                break;
            case 'programedDate':
                $el.val(moment(val,"YYYY-MM-DD").format('DD/MM/YYYY'));
                break;
            case 'applicationDate':  
                $el.val(moment(val,"YYYY-MM-DD").format('DD/MM/YYYY'));
                break;
            case 'expirationDate':
                    $el.val(moment(val,"YYYY-MM-DD").format('DD/MM/YYYY'));
                break;    
            case 'suspended':
                if(val !== 2){
                    if(val === 1){
                        $("#formEditPVPatientVaccineSection input[name=suspended]").prop('checked',true);
                    }else{
                        $("#formEditPVPatientVaccineSection input[name=suspended]").prop('checked',false);
                    }
                }else{
                    $("#formEditPVPatientVaccineSection input[name=suspended]").prop('checked',false);
                }    
                break;
            case 'patientVaccinePK':
                $("#formEditPVPatientVaccineSection input[name=pvvaccine]").val(data['vaccine']['idVaccine']);
                break;
            default:
                $el.val(val);
        }
    });
    
    if($("#formEditPVPatientVaccineSection input[name=suspended]").prop('checked')){
        $("#formEditPVPatientVaccineSection input[name=programedDate]").attr("readonly",true);
        $("#formEditPVPatientVaccineSection input[name=applicationDate]").attr("readonly",true);
    }else{
        $("#formEditPVPatientVaccineSection input[name=programedDate]").attr("readonly",false);
        $("#formEditPVPatientVaccineSection input[name=applicationDate]").attr("readonly",false);
    }
    
}

//This method returns the age of the patient in days months and years
function getAge(fromdate, todate){
    if(todate) todate= new Date(todate);
    else todate= new Date();

    var age= [], fromdate= new Date(fromdate),
    y= [todate.getFullYear(), fromdate.getFullYear()],
    ydiff= y[0]-y[1],
    m= [todate.getMonth(), fromdate.getMonth()],
    mdiff= m[0]-m[1],
    d= [todate.getDate(), fromdate.getDate()],
    ddiff= d[0]-d[1];

    if(mdiff < 0 || (mdiff=== 0 && ddiff<0))--ydiff;
    if(mdiff<0) mdiff+= 12;
    if(ddiff<0){
        fromdate.setMonth(m[1]+1, 0);
        ddiff= fromdate.getDate()-d[1]+d[0];
        --mdiff;
    }
    if(mdiff> 0){ 
        age.push(mdiff+ ' M ');
    }else{
        age.push('0 M ');
    }
    if(ddiff> 0){
        age.push(ddiff+ ' D ');
    }else{
        age.push(ddiff+ '0 D ');
    }
    if(ydiff> 0){
        age.push(ydiff+ ' A ');
    }else{
        age.push('0 A ');
    }
      
    return age.join('');
}