/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){

    $('.inputNormal').inputmask('Regex',{regex:'[A-Za-z0-9-]{1}[" "A-Za-z0-9-αινσϊρ]*'});
    $('.inputInteger').inputmask('Regex',{regex:'[0-9-]+'});
    $('.inputCurp').inputmask('Regex',{regex:"[A-Za-z]{1}[AEIOUaeiou]{1}[A-Za-z]{2}[0-9]{2}" +
                                        "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
                                        "[HMhm]{1}" +
                                        "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE|" +
                                        "as|bc|bs|cc|cs|ch|cl|cm|df|dg|gt|gr|hg|jc|mc|mn|ms|nt|nl|oc|pl|qt|qr|sp|sl|sr|tc|ts|tl|vz|yn|zs|ne)"+
                                        "[B-DF-HJ-NP-TV-Zb-df-hj-np-tv-z]{3}" +
                                        "[0-9A-Za-z]{1}[0-9]{1}$"});
    $('.inputEmail').inputmask('Regex', {regex: "[a-zA-Z0-9._%-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,4}" });                        
    initializeNewRelativeForm();
});

function initializeNewRelativeForm(){
    $('#formNewRelative').bootstrapValidator({
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
        addNewRelative();
    });
}

function addNewRelative(){
    var data = [];
    var inputs = $('#formNewRelative :input');
    
    //Collect form data
    inputs.each(function() {
        if($(this).val() !== ''){
            if( this.name === "active" ){
                data.push({name:this.name,value:$(this).prop('checked')});  
            }else{
                data.push({name:this.name,value:$(this).val()});  
            }
        }    
    });

    //Send to controller
    $.ajax({
        url:"/demo/relatives/saveNewRelative",
        data:data,
        type: "POST",
        success:function(response){
            //Reload the patient table
            $('#tblRelative').DataTable().ajax.reload();
            $('#relativesTabMenu a[href="#tabMain"]').tab('show');
            displaySuccessAlert("Operacion exitosa");
        },
        error: function(data, status, error) {
            displayDangerAlert("error");
        }
    });
}

