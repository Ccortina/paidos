/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeModifyRelativeForm();
});

function modifyRelative(){
    var data = $('#tblRelative').DataTable().row('.selected').data();
    
    if(checkNotUndefined(data)){
        $("#formModifyRelative :input").each(function(){
            if(this.name === 'religion'){
                $("option",$("#inputModifyRelativeReligionApp")).each(function(){
                   if(this.value == data['religion']['id']){this.selected=true;}
                });
            }else{
                $(this).val(data[this.name]);
            }
        });
        
        $('#formModifyRelative').data('bootstrapValidator').resetForm();
        $('#relativesTabMenu a[href="#tabEdit"]').tab('show');
    }else{
        displayWarningAlert("No ha seleccionado un familiar");
    }
}

function initializeModifyRelativeForm(){
    $('#formModifyRelative').bootstrapValidator({
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
    var data = [];
    var inputs = $('#formModifyRelative :input');
    
    //Collect form data
    inputs.each(function() {
        data.push({name:this.name,value:$(this).val()});  
    });

    //Send to controller
    $.ajax({
        url:"/demo/relatives/saveModifyRelative",
        data:data,
        type: "POST",
        success:function(response){
            //Reload the patient table
            $("#tblRelative").DataTable().ajax.reload();
        },
        error: function(data, status, error) {
            displayDangerAlert("error");
        }
    });
    
    $('#relativesTabMenu a[href="#tabMain"]').tab('show');
}