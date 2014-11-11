/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeNewUserForm();
});

function initializeNewUserForm(){
    $("#formNewUser").bootstrapValidator({
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            firstName: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            lastName: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            professionalNumber: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    digits:{
                        message: 'Deben ser numeros'
                    }
                }
            },
            prescriptionNumber: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    digits:{
                        message: 'Deben ser numeros'
                    }
                }
            },
            receiptsNumber: {
                validators: {
                    digits:{
                        message: 'Deben ser numeros'
                    },
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    },
                    identical: {
                        field: 'confirm_password',
                        message: 'La contraseña y su confirmacion deben ser iguales'
                    },
                    stringLength: {
                        message: 'La contraseña debe ser minimo 6 caracteres y maximo 12',
                        min: 6,
                        max: 12
                    }
                }
            },
            confirm_password: {
                validators: {
                    notEmpty: {
                        message: 'La confirmacion de la contraseña no puede estar vacia'
                    },
                    identical: {
                        field: 'password',
                        message: 'La contraseña y su confirmacion deben ser iguales'
                    },
                    stringLength: {
                        message: 'La contraseña debe ser minimo 6 caracteres y maximo 12',
                        min: 6,
                        max: 12
                    }
                }
            },
            email:{
                validators:{
                    notEmpty: {
                        message: 'El correo electronico no puede estar vacio'
                    },
                    emailAddress: {
                        message: 'No es una direccion de correo valida'
                    }

                }
            }
        },
        submitButtons: 'button[type="submit"]'
        
    })
    .on('change', '[name="role"]', function() {
        console.log("valor=" + $(this).val());
        console.log("wtf");
        var isEmpty = parseInt($(this).val()) !== 3;
        $('#formNewUser')
                .bootstrapValidator('enableFieldValidators', 'professionalNumber', isEmpty)
                .bootstrapValidator('enableFieldValidators', 'prescriptionNumber', isEmpty)
                .bootstrapValidator('enableFieldValidators', 'receiptsNumber', isEmpty);
    })
    .on('success.form.bv', function(e) {
            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.ajax({
              type: "POST",
              url: contextPath+"/administration/addUser",
              data: $form.serialize(),
              success: function(response,status,jhqr){
                  if(response.status === "Error"){
                    displayDangerAlert(response.msg);
                  }else{
                    displaySuccessAlert("El usuario se ha agregado correctamente");
                        clearFormInputTextFields("formNewUser");
                        $form.data('bootstrapValidator').resetForm();
                  }   
              },
              error:function(){
                displayDangerAlert("Ha habido un error en la operacion");
              }
            });
        });
}

