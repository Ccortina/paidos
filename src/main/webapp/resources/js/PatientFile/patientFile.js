/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    
    initializeSibilingsTable();
    
    initializePatientRelativeTable();
    
    initializeModifyRelativeForm();
    
    initializePatientFamilyAllRelativesTable();
    
    initializePatientFamilyPatientRelativesTable();
    
    $('a[href="#familia"]').on('shown.bs.tab', function (e) {
        $("#tblPatientRelativesList").DataTable().columns.adjust().draw();
    });
    
    //$('.inputNormal').inputmask('Regex',{regex:'[A-Za-z0-9-]{1}[" "A-Za-z0-9-αινσϊρ@]*'});
});

function initializeSibilingsTable()
{
    $('#tblSibilings').DataTable({
        "searching":false,
        "info":false,
        "ordering":false,
        "paging":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla."
        },
        "ajax":"/demo/patients/getPatientSibilings",
        "columns":[
            {"render":function(data,row,full){
                    return (full["firstName"]+" "+full["fatherLastName"]+" "+full["motherLastName"]);
            }},
            {"render":function(data,row,full){
                    return getAge(full["birthday"],new Date());
            }}
        ],
        "initComplete":function(settings,json){
            var table = $('#sibilingsTable').DataTable();

            $('#sibilingsTable tbody').on( 'click', 'tr', function (e) {
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
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            "url":"../getPatientRelatives",
            "data":{
                idPatient:id
            }},
        "columns":[
            {"data":"relative.fatherLastName"},
            {"data":"relative.motherLastName"},
            {"data":"relative.firstName"},
            {"data":"idRelationship.relationship"},
            {"data": null,
             "defaultContent": "<input type='button' class='btn btn-danger' value='Desasociar' onclick='unrelateRelative(this);'/>"
            }],
        "initComplete": function(settings, json) {
            var table = $('#tblPatientRelativesList').DataTable();
    
            $('#tblPatientRelativesList tbody').on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    modifyRelative();
                }
            });
        }
    });
}

function modifyRelative(){
    var data = $('#tblPatientRelativesList').DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $("#formModifyRelative :input").each(function(){
            if(this.name === 'religion'){
                $("option",$("#inputModifyRelativeReligionApp")).each(function(){
                   if(this.value == data['religion']['id']){this.selected=true;}
                });
            }else{
                if(this.name == "idRelationship"){
                    $("option",$("#selectPatientFamilyRelationship")).each(function(){
                       if(this.value == data['idRelationship']['idRelationship']){this.selected=true;}
                    });
                }else{
                    $(this).val(data['relative'][this.name]);
                }
            }
        });
    }else{
        displayWarningAlert("No ha seleccionado un familiar");
    }
}

function initializeModifyRelativeForm(){
    $('#formModifyRelative').bootstrapValidator({
        live:"disabled",
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            firstName: {
                validators: {
                    notEmpty: {
                        message: 'El nombre del paciente no puede estar vacio'
                    }
                }
            },
            fatherLastName: {
                validators: {
                    notEmpty: {
                        message: 'El apellido paterno del paciente no puede estar vacio'
                    }
                }
            } 
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        saveModifyRelative();
    });
}


function saveModifyRelative(){
    
    if(checkNotUndefined($('#tblPatientRelativesList').DataTable().row('.selected').data())){
        var data = [];
        var inputs = $('#formModifyRelative :input');

        //Collect form data
        inputs.each(function() {
            data.push({name:this.name,value:$(this).val()});  
        }); 
        
        //Send to controller
        $.ajax({
            url:"/demo/patients/saveModifyRelative",
            data:data,
            type: "POST",
            success:function(response){
                //Reload the patient table
                displaySuccessAlert("Se ha modificado correctamente");
                $("#tblPatientRelativesList").DataTable().ajax.reload();
                clearFormInputTextFields("formModifyRelative");
            },
            error: function(data, status, error) {
                displayDangerAlert("error"+error);
                console.error(error);
            }
        });
    }else{
        displayWarningAlert("No se ha seleccionado un familiar");
    }   
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
            {"data":"patientRelative.0.idRelationship.relationship"}]
    });
    
}

function unrelateRelative(row){
    
    var rowData = $('#tblPatientRelativesList').DataTable().row($(row).parent().parent()[0]).data();
    var idPatient = $('#hiddenPatientFileId').val();
    var idRelative = rowData["id"];
    var idRelationship = rowData["idRelationship"]["idRelationship"];
    var data = {idPatient:idPatient,idRelative:idRelative,idRelationship:idRelationship};
    
    
    var box = bootbox.confirm("<strong>Advertencia!</strong>Esta seguro de desasociar este familiar?", function(result) {
                    if(result){
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
                  });
        box.find('.modal-content').css({'color': '#8a6d3b','background-color': '#fcf8e3','border-color': '#faebcc'});
}

function updateRelativeCancel(){
   $("#divHiddenModifiyPatientRelative").hide();
   $("#formPatientFamilyDisplay :input").each(function(){
        $(this).prop("disabled",true);
   });
   $("#divPatientFamily").show(); 
}

function populateFormWithJSON( idForm , idTable ){
    var activity = $('#'+idTable).DataTable().row('.selected').data()['relative'];
    
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
                case 'idRelationship':
                    console.log($(this).attr('value').value);
                    $("option",$el).each(function(){
                       if(this.value == val[0]["idRelationship"]["idRelationship"]){ this.selected=true; } 
                    });
                break;
                case 'religion':
                    $("option",$el).each(function(){
                       if(this['id'] == val['id']){ 
                           this.selected=true; 
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
        method:"POST",
        success:function(response){
            displaySuccessAlert("Operacion realizada con exito");
            if( response === "addRelative" ){
                $('#tblPatientFamilyPatientRelatives').DataTable().ajax.reload(); 
                $('#tblPatientRelativesList').DataTable().ajax.reload();  
            }
        },
        error:function(data, status, error){
            displayDangerAlert("Ha ocurrido un error en la operacion: "+ error);
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

function consultPatient(){
    $.post("/demo/patients/patientConsultation?idPatient=" + $('#hiddenPatientFileId').val());
    $.ajax({
        url:"../patientConsultation",
        data: {idPatient:$('#hiddenPatientFileId').val()},
        type:"POST",
        success:function(response){
            window.location.href ="/demo/consultation/"+response;
        }
    });
}