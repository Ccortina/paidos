/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeImmunizationTable();
    
    $('.inputDate').inputmask("dd/mm/yyyy",{ "clearIncomplete": true });
    
    $('.inputInteger').inputmask("99");
    $("input:checkbox").click(function() {
        if ($(this).is(":checked")) {
            var group = "input:checkbox[name='" + $(this).attr("name") + "']";
            $(group).prop("checked", false);
            $(this).prop("checked", true);
        } else {
            $(this).prop("checked", false);
        }
    });
    
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
    }
}

function initializeFiltersForm(){
    $('#formFilters').bootstrapValidator({
        live:"disabled",
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            inputProgrammedDateStart: {
                enabled:false
            },
            inputProgrammedDateEnd: {
                enabled:false,
                validators: {
                    validateDateRange: {
                        message: 'La fecha de inicio no puede ser mayor',
                        firstField: 'inputProgrammedDateStart'
                    },
                    notEmpty: {
                        message: 'No puede estar vacio'
                    }
                }
            },
            inputAgeBeginYear: {
                enabled:false
            },
            inputAgeEndYear: {
                enabled:false,
                validators: {
                    validateValueGreater: {
                        message: 'Este valor debe ser mayor',
                        firstField: 'inputAgeBeginYear'
                    }
                }
            },
            inputAgeBeginMonth: {
                enabled:false
            },
            inputAgeEndMonth: {
                enabled:false,
                validators: {
                    validateValueGreater: {
                        message: 'Este valor debe ser mayor',
                        firstField: 'inputAgeBeginMonth'
                    }
                }
            },
            inputAgeBeginDay: {
                enabled:false
            },
            inputAgeEndDay: {
                enabled:false,
                validators: {
                    validateValueGreater: {
                        message: 'Este valor debe ser mayor',
                        firstField: 'inputAgeBeginDay'
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]'
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        filterTable();
    });
    
    //Initialize validator if date is not empty
    $('#formFilters').find('[name="inputProgrammedDateStart"]').blur(function(){
        var isEmpty = $(this).val() == '';
        $('#formFilters').bootstrapValidator('enableFieldValidators', 'inputProgrammedDateStart', !isEmpty)
                        .bootstrapValidator('enableFieldValidators', 'inputProgrammedDateEnd', !isEmpty);
    });
    
    $('#formFilters').find('[name="inputBirthdayStart"]').blur(function(){
        var isEmpty = $(this).val() == '';
        $('#formFilters').bootstrapValidator('enableFieldValidators', 'inputBirthdayStart', !isEmpty)
                        .bootstrapValidator('enableFieldValidators', 'inputBirthdayEnd', !isEmpty);
    });
    
    $('#formFilters').find('[name="inputAgeEndYear"]').blur(function(){
        var isEmpty = $(this).val() == '';
        $('#formFilters').bootstrapValidator('enableFieldValidators', 'inputAgeBeginYear', !isEmpty)
                        .bootstrapValidator('enableFieldValidators', 'inputAgeEndYear', !isEmpty);
    });
    
    $('#formFilters').find('[name="inputAgeEndMonth"]').blur(function(){
        var isEmpty = $(this).val() == '';
        $('#formFilters').bootstrapValidator('enableFieldValidators', 'inputAgeBeginMonth', !isEmpty)
                        .bootstrapValidator('enableFieldValidators', 'inputAgeEndMonth', !isEmpty);
    });
    
    $('#formFilters').find('[name="inputAgeEndDay"]').blur(function(){
        var isEmpty = $(this).val() == '';
        $('#formFilters').bootstrapValidator('enableFieldValidators', 'inputAgeBeginDay', !isEmpty)
                        .bootstrapValidator('enableFieldValidators', 'inputAgeEndDay', !isEmpty);
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
            var endDate = moment($field.val(),"DD/MM/YYYY");
            var beginDate = moment( $( "#"+options.firstField ).val(),"DD/MM/YYYY" );
           
            if( new Date(beginDate.format("YYYY-MM-DD")) <= new Date(endDate.format("YYYY-MM-DD")) ){
                return true;
            }
            return false;
        }
    };
    
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
}