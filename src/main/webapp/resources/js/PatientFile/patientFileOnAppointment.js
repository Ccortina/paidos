/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    
    initializeSibilingsTable();
    
    initializeAbstractPrescriptionTable();
    
    initializePatientRelativeTable();
    
    initializePatientFamilyAllRelativesTable();
    
    initializePatientFamilyPatientRelativesTable();
    
    disableForms();
    
    $('[id]').each(function(){
        var ids = $('[id="'+this.id+'"]');
        if(ids.length>1 && ids[0]===this){
            console.warn('Multiple IDs #'+this.id);
        };
            
    });
    
});

function initializeSibilingsTable()
{
    
    $('#tblSibilings').DataTable({
        "bSort":false,
        "scrollY": "200px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "searching":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla."
        },
        "ajax":{
            "url":"/demo/patients/getPatientSibilings",
            "data":{
                "idPatient":$('#hiddenPatientFileId').val()
            }
        },
        "columns":[
            {"targets": 0,
             "data": null,
             "render": function ( data, type, full, meta ) {
                 return data.firstName+" "+data.secondName+" "+data.fatherLastName+" "+data.motherLastName;
                }
            },
            {"data":"birthday"},
            {"data": null,
             "defaultContent": "<input type='button' class='btn btn-primary' value='Consultar' onclick='openSibilingFile(this);'/>"
            }
        ]
    });
}

function initializeAbstractPrescriptionTable()
{
    
}

function initializePatientRelativeTable(){
    var id = $('#hiddenPatientFileId').val();
    
    $('#tblPatientRelativesList').DataTable({
        "bSort":false,
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "searching":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla."
        },
        "ajax":{
            "url":"/demo/patients/getPatientRelatives",
            "data":{
                "idPatient":id
            }},
        "columns":[
            {"data":"id",
                "visible":false},
            {"data":"fullName"},
            {"data":"patientRelative.0.idRelationship.relationship"},
            {"data": null,
             "defaultContent": "<input type='button' class='btn btn-danger' value='Desasociar' onclick='unrelateRelative(this);'/>"
            }],
        "initComplete": function(settings, json) {
            addRowSelectionPatientRelativeTable();
        }
    });
}

function addRowSelectionPatientRelativeTable(){
    var table = $('#tblPatientRelativesList').DataTable();
    
    $('#tblPatientRelativesList tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('selected')){
                $(this).removeClass('selected');
            }
            else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    populateFormWithJSON( "formPatientFamilyDisplay" , "tblPatientRelativesList" );
                }
        });
}

function initializePatientFamilyAllRelativesTable(){
    
    $('#tblPatientFamilyAllRelatives').DataTable({
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":"/demo/patients/getAllRelatives",
        "columns":[
            {"data":"fatherLastName"},
            {"data":"motherLastName"},
            {"data":"firstName"},
            {"data":"rfc"},
            {"data":"homePhone"}],
        "initComplete": function(settings, json) {
            addRowSelectionPatientFamilyAllRelativesTable();
        }
    });
    
}

function addRowSelectionPatientFamilyAllRelativesTable(){
    var table = $('#tblPatientFamilyAllRelatives').DataTable();
    
    $('#tblPatientFamilyAllRelatives tbody').on( 'click', 'tr', function (e){
            if ( $(this).hasClass('selected')){
                $(this).removeClass('selected');
                $("#hiddenPatienFamilySelectedRelative").val(null);
            }
            else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#hiddenPatienFamilySelectedRelative").val(table.row(this).data()["id"]);
                }
        });
}

function initializePatientFamilyPatientRelativesTable(){
    var id = $('#hiddenPatientFileId').val();
    
    $('#tblPatientFamilyPatientRelatives').DataTable({
        "scrollY": "150px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "searching":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":"/demo/patients/getPatientRelatives",
            "data":{
                "idPatient":id
            }
        },
        "columns":[
            {"data":"id",
                "visible":false},
            {"data":"fatherLastName"},
            {"data":"motherLastName"},
            {"data":"firstName"},
            {"data":"rfc"},
            {"data":"patientRelative.0.idRelationship.relationship"}]
    });
    
}

function unrelateRelative(row){
    
    var rowData = $('#tblPatientRelativesList').DataTable().row($(row).parent().parent()[0]).data();
    var idPatient = $('#hiddenPatientFileId').val();
    var idRelative = rowData["id"];
    var idRelationship = rowData["patientRelative"]["0"]["idRelationship"]["id"];
    var data = {idPatient:idPatient,idRelative:idRelative,idRelationship:idRelationship};
    
    $.ajax({
        url:"/demo/patients/unrelateRelative",
        data: data,
        type:"POST",
        success:function(response){
            $('#tblPatientFamilyPatientRelatives').DataTable().ajax.reload(); 
            $('#tblPatientRelativesList').DataTable().ajax.reload();
            var node =$('#tblPatientRelativesList').DataTable().row(0).node();
            $(node).click();
        }
    });
}

function enableModifyPatientRelative(){
    $("#divHiddenModifyPatientRelative").show();
    $("#formPatientFamilyDisplay :input").each(function(){
       $(this).prop("disabled",false);
    });
    $("#divPatientFamily").hide();
}

function updateRelative(){
    
    $.ajax({
        url:$('#formPatientFamilyDisplay').attr("action"),
        data: $('#formPatientFamilyDisplay').serializeObject(),
        type:"POST",
        success:function(response){
            if( response === "addRelative" ){
                $('#tblPatientFamilyPatientRelatives').DataTable().ajax.reload(); 
                $('#tblPatientRelativesList').DataTable().ajax.reload();  
            }
        }
    });
    
    $("#divHiddenModifyPatientRelative").hide();
    $("#formPatientFamilyDisplay :input").each(function(){
       $(this).prop("disabled",true);
    });
    $("#divPatientFamily").show();
}

function updateRelativeCancel(){
   $("#divHiddenModifyPatientRelative").hide();
   $("#formPatientFamilyDisplay :input").each(function(){
        $(this).prop("disabled",true);
   });
   $("#divPatientFamily").show(); 
}

function openSibilingFile(button){
    $('#tblSibilings').DataTable().row();
    var rowData = $('#tblSibilings').DataTable().row($(button).parent().parent()[0]).data();
    window.open("/demo/patients/file/"+rowData.id);
}

function populateFormWithJSON( idForm , idTable ){
    var activity = $('#'+idTable).DataTable().row('.selected').data();
    console.log(activity);
    $.each(activity, function(name, val){
    
    $el = $('[name="'+name+'"]','#'+idForm);
    type = $el.attr("type");
    
    if( $el.is('select') ){
        type = 'select';
    }
    
    switch(type){
        case 'checkbox':
            $el.attr('checked', 'checked');
            break;
        case 'radio':
            $el.filter('[value="'+val+'"]').attr('checked', 'checked');
            break;
        case 'select':
            switch(name){
                case 'patientRelative':
                    $("option",$el).each(function(){
                        if(this.value == val[0]["idRelationship"]["id"])
                        { 
                            this.selected=true; 
                        } 
                    });
                break;
                case 'religion':
                    $("option",$el).each(function(){
                       if(this.value == val['id']){ 
                           $(this).prop('selected',true); 
                       }  
                    });
                break;
                default:
                    $("option",$el).each(function(){
                       if(this.value === val){ this.selected=true; } 
                    });
            }
            break;
        default:
            $el.val(val);
    }
    
    });
    
}

//Makes an ajax Call wiht the id of the table
function ajaxCall(ID){
    $.ajax({
        url:$('#'+ID).attr("action"),
        data: $('#'+ID).serializeObject(),
        type:$('#'+ID).attr("method"), 
        success:function(response){
            if( response === "addRelative" ){
                $('#tblPatientFamilyPatientRelatives').DataTable().ajax.reload(); 
                $('#tblPatientRelativesList').DataTable().ajax.reload();
                $("#divPatientFamily").show();
            }
        }
    });	
}

//This function converts a form in a json acceptedString
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//Disable all form on the page
function disableForms(){
    $("#divWarningAutosave").hide();
    $('.changeForm :input').each(function(){
       $(this).prop("disabled",true); 
    });
    
    $('form').each(function(){
       $("#btn"+$(this).attr("id")+"CancelEditForm").hide(); 
    });
}

//Enable a single form on the page and show the cancel button
function enableForm(form){
    $('form#'+ form +' :input').each(function(){
        $(this).prop("disabled",false);
    });
    
    $("#btn"+form+"CancelEditForm").show();
    $("#divWarningAutosave").show();
}

function consultPatient(){
    var appointment = $("#hiddenPatientIdAppointment").val();
    window.location="/demo/consultation/"+appointment;
}