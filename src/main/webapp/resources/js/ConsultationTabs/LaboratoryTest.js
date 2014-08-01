/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
    initializeLaboratoryTestsPatientData();
    
    initializeLaboratoryTestsList();
    
    initializeEditLaboratoryTestsList();
    
    $('a[href="#inmunizaciones"]').on('shown.bs.tab', function (e) {
        $("#tblLaboratoryTestsPatientData").DataTable().columns.adjust().draw();
    });
    
    $('#modalLaboratoryTestEdit').on('show.bs.modal',function(event){
        loadEditLT(event);
    });
});

function initializeLaboratoryTestsPatientData(){
    var patient = $("#consultationPatientId").val();
    
    $("#tblLaboratoryTestsPatientData").DataTable({
        "scrollY":"200px",
        "paging": false,
        "info":false,
        "ajax": {
            url:"/demo/consultation/getLaboratoryTestsPatientData",
            data:{"patient":patient}
        },
        "columns":[
            {"data":"date"},
            {"data":"idLaboratoryTest.laboratoryTest"},
            {"data":"result"}
            ],
        "language": {
            "emptyTable": "No hay informacion en la tabla."
        },
        "initComplete":function(){
            var table = $("#tblLaboratoryTestsPatientData").DataTable();
            
            $("#tblLaboratoryTestsPatientData tbody").on('click','tr',function(e){
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

function initializeLaboratoryTestsList(){
    
    $("#tblLaboratoryTests").DataTable({
        "scrollY":"200px",
        "paging": false,
        "info":false,
        "ajax":"/demo/consultation/getLaboratoryTests",
        "columns":[
            {"data":"idLaboratoryTest",
                "visible":false},
            {"data":"laboratoryTest"}],
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search":"Buscar"},
        "initComplete":function(settings,json){
            var table = $("#tblLaboratoryTests").DataTable();
            
            $("#tblLaboratoryTests").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#formLaboratoryTestAdd input[name=idLaboratoryTest]").val(table.row(this).data()["idLaboratoryTest"]);
                }
            });
        }
    });
}

function initializeEditLaboratoryTestsList(){
    
    $("#tblEditLaboratoryTests").DataTable({
        "scrollY":"200px",
        "paging": false,
        "info":false,
        "ajax":"/demo/consultation/getLaboratoryTests",
        "columns":[
            {"data":"idLaboratoryTest",
                "visible":false},
            {"data":"laboratoryTest"}],
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search":"Buscar"},
        "initComplete":function(settings,json){
            var table = $("#tblEditLaboratoryTests").DataTable();
            
            $("#tblEditLaboratoryTests").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#formLaboratoryTestEdit input[name=idLaboratoryTest]").val(table.row(this).data()["idLaboratoryTest"]);
                }
            });
        }
    });
}

function saveAddLaboratoryTestResult(){
    var row = $("#tblEditLaboratoryTests").DataTable().row('.selected').data();
    
    if(row === 'undefined'){
        displayDangerAlert(" No se ha seleccionado un estudio de laboratorio.");
    }else{
        console.log($("#formLaboratoryTestEdit input[name=testResult]").val());
        if($("#formLaboratoryTestEdit input[name=testResult]").val() === ""){
            displayDangerAlert(" Los resultados no deben estar vacios.");
        }else{
            $.ajax({
                url:"/demo/consultation/saveLaboratoryTestResult",
                data: $('#formLaboratoryTestAdd').serializeObject(),
                type:"POST",
                success:function(response){
                    $("#tblLaboratoryTestsPatientData").DataTable().ajax.reload();
                    $("#modalDivlLaboratoryTestAddAjaxResponse").html(response);
                },
                error:function(jqXHR,textStatus,errorThrown){
                    displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
                }        
            });
            $('#modalLaboratoryTestAdd').modal("hide");
        }
    }
    
}

function deleteLaboratoryTest(){
    var row = $("#tblLaboratoryTestsPatientData").DataTable().row('.selected').data();
    
    if(row === 'undefined'){
        displayDangerAlert(" No se ha seleccionado un estudio de laboratorio para borrar.");
    }else{
        $.ajax({
            url:"/demo/consultation/deleteLaboratoryTestResult",
            data: {'idResult':row["idLaboratoryTestResult"]},
            type:"POST",
            success:function(response){
                $("#tblLaboratoryTestsPatientData").DataTable().ajax.reload();
            },
            error:function(jqXHR,textStatus,errorThrown){
                displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
            }     
        });
    }
}

function loadEditLT(){
    row = $("#tblLaboratoryTestsPatientData").DataTable().row('.selected').data();
    
    if(typeof row == 'undefined'){
        displayDangerAlert(" No se ha seleccionado un estudio de laboratorio.");
        e.preventDefault();
    }else{
        $.each(row, function(name, val){
            if(name === "idLaboratoryTest"){ 
                $el = $('[name="'+name+'"]','#formLaboratoryTestEdit');
                $el.val(val["idLaboratoryTest"]);
                $("#tblEditLaboratoryTests tr:contains("+val['laboratoryTest']+")").trigger('click');
            }else if(name === "date"){
                $el = $('[name="'+name+'"]','#formLaboratoryTestEdit');
                $el.val(moment(val,"YYYY-MM-DD").format('DD/MM/YYYY'));
            }else{
                $el = $('[name="'+name+'"]','#formLaboratoryTestEdit');
                $el.val(val); 
            }
        });
    }
}

function editPatientLT(e){
    var row = $("#tblEditLaboratoryTests").DataTable().row('.selected').data();
    
    if(row == 'undefined'){
        displayDangerAlert(" No se ha seleccionado un estudio de laboratorio.");
    }else{
        if($("#formLaboratoryTestEdit input[name=idLaboratoryTest]").val() === ""){
            displayDangerAlert(" Los resultados no deben estar vacios.");
        }else{
            $.ajax({
                    url:"/demo/consultation/editLaboratoryTestResult",
                    data: $('#formLaboratoryTestEdit').serializeObject(),
                    type:"POST",
                    success:function(response){
                        $("#tblLaboratoryTestsPatientData").DataTable().ajax.reload();
                    },
                    error:function(jqXHR,textStatus,errorThrown){
                    displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
                    }
            });
            $('#modalLaboratoryTestEdit').modal("hide");
            
        }
    }    
}
