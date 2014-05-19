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
        }
        
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
            "url":"../getPatientRelatives",
            "data":{
                "idPatient":id
            }},
        "columns":[
            {"data":"id",
                "visible":false},
            {"data":"fullName"},
            {"data":"patientRelative.0.idRelationship.relationship"}],
        "initComplete": function(settings, json) {
            addRowSelectionPatientRelativeTable();
            $('#tblPatientRelativesList').DataTable().draw();
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
        "ajax":"../getAllRelatives",
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
                    console.log(table.row(this).data()["id"]);
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
            "url":"../getPatientRelatives",
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
            {"data":"patientRelative.0.idRelationship.relationship"},
            {"data": null,
             "defaultContent": "<input type='button' class='btn btn-danger' value='Desasociar' onclick='unrelateRelative(this);'/>"
            }]
    });
    
}

function unrelateRelative(row){
    
    var rowData = $('#tblPatientFamilyPatientRelatives').DataTable().row($(row).parent().parent()[0]).data();
    var idPatient = $('#hiddenPatientFileId').val();
    var idRelative = rowData["id"];
    var idRelationship = rowData["patientRelative"]["0"]["idRelationship"]["id"];
    var data = {idPatient:idPatient,idRelative:idRelative,idRelationship:idRelationship};
    
    $.ajax({
        url:"../unrelateRelative",
        data: data,
        type:"POST",
        success:function(response){
        $('#tblPatientFamilyPatientRelatives').DataTable().ajax.reload();    
        }
    });
}

function populateFormWithJSON( idForm , idTable ){
    var activity = $('#'+idTable).DataTable().row('.selected').data();
    
    $.each(activity, function(name, val){

    $el = $('[name="'+name+'"]','#'+idForm);
    
    if( $el.is('select') ){
        $("option",$el).each(function(){
            if(this.value === val ){ this.selected = true; }
        });
    }else{
        switch($el.attr("type")){
        case 'checkbox':
            $el.attr('checked', 'checked');
            break;
        case 'radio':
            $el.filter('[value="'+val+'"]').attr('checked', 'checked');
            break;
        default:
            $el.val(val);
            
        }
    }
    });
    
}

//Makes an ajax Call wiht the id of the table
function ajaxCall(ID){
    $.ajax({
        url:$('#'+ID).attr("action"),
        data: $('#'+ID).serializeObject(),
        type:"POST",
        success:function(response){
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
    console.log(o);
};