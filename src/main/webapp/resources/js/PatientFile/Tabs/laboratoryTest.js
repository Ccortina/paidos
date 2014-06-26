/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
    initializeLaboratoryTestsPatientData();
    
    initializeLaboratoryTestsList();
    
    $('.datepicker').datepicker({
        "format":"dd-mm-yyyy",
        "autoclose":true,
        "String.Default":"mx"
    });
    $('.datepicker').datepicker('setDate',new Date());
    
    initializeEditLaboratoryTestsList();
});

function initializeLaboratoryTestsPatientData(){
    var patient = $("#consultationPatientId").val();
    
    $("#tblLaboratoryTestsPatientData").DataTable({
        "scrollY":"200px",
        "paging": false,
        "info":false,
        "ajax": {
            url:"/demo/patients/getLaboratoryTestsPatientData",
            data:{"patient":patient}
        },
        "columns":[
            {"data":"date"},
            {"data":"idLaboratoryTest.laboratoryTest"},
            {"data":"result"},
            {"defaultContent":"<a data-toggle='modal' href='#modalLaboratoryTestEdit' class='btn btn-primary' onclick='loadBasicFormJSON(this)'>Modificar</a>"},
            {"defaultContent":"<input type='button' class='btn btn-danger' value='Eliminar' onclick='deleteLaboratoryTest(this);'/>"}
            ],
        "language": {
            "emptyTable": "No hay informacion en la tabla."}    
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
                    $("#formLaboratoryTestEdit input[name=idLaboratoryTest]").val(table.row(this).data()["idLaboratoryTest"]).trigger('change');
                }
            });
        }
    });
}

function saveAddLaboratoryTestResult(){
    $.ajax({
        url:"/demo/patients/saveLaboratoryTestResult",
        data: $('#formLaboratoryTestAdd').serializeObject(),
        type:"POST",
        success:function(response){
            $("#tblLaboratoryTestsPatientData").DataTable().ajax.reload();
            $("#modalDivlLaboratoryTestAddAjaxResponse").html(response);
        }
    });
}

function deleteLaboratoryTest(button){
    var table = $("#tblLaboratoryTestsPatientData").DataTable();
    var idTestResult = table.row($(button).parent().parent()).data()["idLaboratoryTestResult"];
    
    $.ajax({
        url:"/demo/patients/deleteLaboratoryTestResult",
        data: {'idResult':idTestResult},
        type:"POST",
        success:function(response){
            $("#tblLaboratoryTestsPatientData").DataTable().ajax.reload();
        }
    });
}

function loadBasicFormJSON(button){
    var table = $("#tblLaboratoryTestsPatientData").DataTable();
    var data = table.row($(button).parent().parent()).data();

    $.each(data, function(name, val){
        if(name === "idLaboratoryTest"){ 
            val = val["idLaboratoryTest"];
            $el = $('[name="'+name+'"]','#formLaboratoryTestEdit');
            $el.val(val);
        }else if(name === "date"){
            var d = val.split('-');
            $("#formLaboratoryTestEdit input[name=date]").datepicker("setDate",d[2]+"-"+d[1]+"-"+d[0]);
        }else{
            $el = $('[name="'+name+'"]','#formLaboratoryTestEdit');
            $el.val(val); 
        }
    });
    
    $( ".onChange3" ).change(function() {
        $.ajax({
            url:"/demo/patients/editLaboratoryTestResult",
            data: $('#formLaboratoryTestEdit').serializeObject(),
            type:"POST",
            success:function(response){
                $("#tblLaboratoryTestsPatientData").DataTable().ajax.reload();
            }
        });
    });
}

/*
$.fn.datepicker.dates['mx'] = {
    days: ["Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Ssabado", "Domingo"],
    daysShort: ["DO", "LU", "MA", "MI", "JU", "VI", "SA", "DO"],
    daysMin: ["D", "L", "Ma", "M", "J", "V", "S", "D"],
    months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
    monthsShort: ["ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"],
    today: "Hoy",
    clear: "Limpiar"
};*/

