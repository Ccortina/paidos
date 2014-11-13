/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeImmunizationTable();
    
    $("input:checkbox").click(function() {
        if ($(this).is(":checked")) {
            var group = "input:checkbox[name='" + $(this).attr("name") + "']";
            $(group).prop("checked", false);
            $(this).prop("checked", true);
        } else {
            $(this).prop("checked", false);
        }
    });
    
    addFormValidator();
    initializeFiltersForm();
});

function initializeImmunizationTable(){
        
    $("#tblImmunization").DataTable({
        "scrollY": "400px",
        "scrollCollapse": true,
        "deferRender": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar",
            "info": "Mostrando pagina _PAGE_ de _PAGES_"
        },
        "ajax":{url:"/demo/patients/getAllPatientImmunization",
            data:function(){
               return {idPatient:$("#inputIdPatientApp").val()} 
            }},
        "columns":[
            {"data":"patient.fatherLastName"},
            {"data":"patient.motherLastName"},
            {"data":"patient.firstName"},
            {"render":function(data,type,full){
                return (moment(full["patient"]["birthday"]).format("DD/MM/YYYY"));
            }},
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
                    return full.applicationDate;
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
            var table = $('#tblInmunization').DataTable();
            
            $('#tblInmunization tbody').on( 'click', 'tr', function (e) {
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


function filterTable(){
    var table = $("#tblImmunization").DataTable();
    var byPD = $("#checkProgrammedDatesRange").prop("checked"); //Filter By Programmed Dates range
    var byBD = $("#checkBirthdayRange").prop("checked"); //Filter By Birthdays range
    var byIm = $("#checkByInmunization").prop("checked"); //Filter By inmunization
    var byIT = $("#checkBytype").prop("checked"); //Filter By inmunization type
    var byAR = $("#checkAgesRange").prop("checked"); //Filter By age range
    
    /*
    //Filter by applied or not
    if($("input[type='checkbox']:checked").val() === "1"){
        table.column( 8 ).search('');
        table.column( 8 ).search( "Pendiente" ).draw();
    }else{
        if($("input[type='checkbox']:checked").val() === "2"){
            table.column( 8 ).search('');
            table.column( 8 ).search( "[0-9][0-9][0-9][0-9][\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])" , true, false ).draw();
        }else{
            table.column( 8 ).search('').draw();
        }   
    }
    //Filter By immunization
    if($("#selectVaccine").val() !== "0"){
        table.column( 4 ).search('');
        table.column( 4 ).search( $( "#selectVaccine option:selected" ).text() ).draw();
    }else{
        table.column( 4 ).search('').draw();
    }
    //Filter By Immunization type
    if($("#selectVaccineType").val() !== "0"){
        table.column( 5 ).search('');
        table.column( 5 ).search( $( "#selectVaccineType option:selected" ).text() ).draw();
    }else{
        table.column( 5 ).search('').draw();
    }*/
}

function initializeFiltersForm(){
    $('#formFilters').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            inputProgrammedDateStart: {
                enabled:false,
                validators:{
                    date:{
                        format:"DD/MM/YYYY",
                        message:"No es una fecha valida"
                    },
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            inputProgrammedDateEnd: {
                enabled:false,
                validators: {
                    date:{
                        format:"DD/MM/YYYY",
                        message:"No es una fecha valida"
                    },
                    validateDateRange:{
                        firstField:"inputProgrammedDateStart",
                        message:"Esta fecha no puede ser menor"
                    },
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            inputBirthdayStart: {
                enabled:false,
                validators:{
                    date:{
                        format:"DD/MM/YYYY",
                        message:"No es una fecha valida"
                    },
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            inputBirthdayEnd: {
                enabled:false,
                validators: {
                    date:{
                        format:"DD/MM/YYYY",
                        message:"No es una fecha valida"
                    },
                    validateDateRange:{
                        firstField:"inputBirthdayStart",
                        message:"Esta fecha no puede ser menor"
                    },
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            inputAgeBeginYear: {
                enabled:false,
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    },
                    digits: {
                        message: 'Solo numeros'
                    }
                }
            },
            inputAgeEndYear: {
                enabled:false,
                validators: {
                    validateValueGreater: {
                        message: 'Este valor debe ser mayor',
                        firstField: 'inputAgeBeginYear'
                    },
                    notEmpty: {
                        message: 'No puede estar vacio'
                    },
                    digits: {
                        message: 'Solo numeros'
                    }
                }
            },
            inputAgeBeginMonth: {
                enabled:false,
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    },
                    digits: {
                        message: 'Solo numeros'
                    }
                }
            },
            inputAgeEndMonth: {
                enabled:false,
                validators: {
                    validateValueGreater: {
                        message: 'Este valor debe ser mayor',
                        firstField: 'inputAgeBeginMonth'
                    },
                    notEmpty: {
                        message: 'No puede estar vacio'
                    },
                    digits: {
                        message: 'Solo numeros'
                    }
                }
            },
            inputAgeBeginDay: {
                enabled:false,
                validators:{
                    notEmpty: {
                        message: 'No puede estar vacio'
                    },
                    digits: {
                        message: 'Solo numeros'
                    }
                }
            },
            inputAgeEndDay: {
                enabled:false,
                validators: {
                    validateValueGreater: {
                        message: 'Este valor debe ser mayor',
                        firstField: 'inputAgeBeginDay'
                    },
                    notEmpty: {
                        message: 'No puede estar vacio'
                    },
                    digits: {
                        message: 'Solo numeros'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    })
    .on('success.form.bv', function(e) {
        e.preventDefault();
        filterTable();
    })
    .on('change','[name="checkProgrammedDatesRange"]',function(){
        var value = $(this).prop('checked');
        $('#formFilters').bootstrapValidator('enableFieldValidators', 'inputProgrammedDateStart', value)
                        .bootstrapValidator('enableFieldValidators', 'inputProgrammedDateEnd', value);
    })
    .on('change','[name="checkAgesRange"]',function(){
        var value = $(this).prop('checked');
        $('#formFilters').bootstrapValidator('enableFieldValidators', 'inputAgeBeginYear', value)
                        .bootstrapValidator('enableFieldValidators', 'inputAgeEndYear', value)
                        .bootstrapValidator('enableFieldValidators', 'inputAgeBeginMonth', value)
                        .bootstrapValidator('enableFieldValidators', 'inputAgeEndMonth', value)
                        .bootstrapValidator('enableFieldValidators', 'inputAgeBeginDay', value)
                        .bootstrapValidator('enableFieldValidators', 'inputAgeEndDay', value);        
                
    })
    .on('change','[name="checkBirthdayRange"]',function(){
        var value = $(this).prop('checked');
        $('#formFilters').bootstrapValidator('enableFieldValidators', 'inputBirthdayStart', value)
                        .bootstrapValidator('enableFieldValidators', 'inputBirthdayEnd', value);
    });
}

function addFormValidator(){
    $.fn.bootstrapValidator.validators.validateDateRange = {
        /**
         * @param {BootstrapValidator} validator The validator plugin instance
         * @param {jQuery} $field The jQuery object represents the field element
         * @param {Object} options The validator options
         * @returns {Boolean}
         */
        validate: function(validator, $field, options) {
            // Get the field value
            var d1 = $( "#"+options.firstField ).val().split("/");
            var d2 = $field.val().split("/");
            return moment( new Date(d1[2], d1[1]-1, d1[0])).isBefore(new Date(d2[2],d2[1]-1,d2[0])); 
        }
    };
}
    
$.fn.bootstrapValidator.validators.validateValueGreater = {
        /**
         * @param {BootstrapValidator} validator The validator plugin instance
         * @param {jQuery} $field The jQuery object represents the field element
         * @param {Object} options The validator options
         * @returns {Boolean}
         */
        validate: function(validator, $field, options) {
           // Get the field value
           var start = parseInt( $("#"+options.firstField).val(), 10 );;
           var end = parseInt( $field.val(), 10 );
           if( isNaN(start) ){ start = 0; }
           if( isNaN(end) ){ end = 0; }
           if( end >= start  ){
                return true;
           }
           return false;
        }
    };

//Custom filtering for the immunization table date range
$.fn.dataTable.ext.search.push(
    function( settings, data, dataIndex ) {

        if( checkNotEmptyString($("#inputProgrammedDateStart").val()) && checkNotEmptyString($("#inputProgrammedDateEnd").val()) ){
            var start = moment($("#inputProgrammedDateStart").val(),"DD/MM/YYYY");
            var end = moment($("#inputProgrammedDateEnd").val(),"DD/MM/YYYY");
            var range = moment().range(start, end);
            var date = moment(data[6]);
            if(date.within(range)){
                return true;
            }else{
                return false;
            }

        }else{
            return true;
        }
    }
);

$.fn.dataTable.ext.search.push(
    function( settings, data, dataIndex ) {

        if( checkNotEmptyString($("#inputBirthdayStart").val()) && checkNotEmptyString($("#inputBirthdayEnd").val()) ){
            var start = moment($("#inputBirthdayStart").val(),"DD/MM/YYYY");
            var end = moment($("#inputBirthdayEnd").val(),"DD/MM/YYYY");
            var range = moment().range(start, end);
            var date = moment(data[3],"DD/MM/YYYY");
            if(date.within(range)){
                return true;
            }else{
                return false;
            }

        }else{
            return true;
        }
    }
);