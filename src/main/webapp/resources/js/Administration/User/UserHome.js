/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeModifyUserForm();
});

function modifyUser(){
    $('#userTabs a[href="#editUserInfo"]').tab('show');
}

function initializeModifyUserForm(){
    $("#formModifyUser").bootstrapValidator({
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
                    }
                }
            },
            prescriptionNumber: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            receiptNumber: {
                validators: {
                    notEmpty: {
                        message: 'Este campo no puede estar vacio'
                    }
                }
            },
            password: {
                enabled: false,
                validators: {
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
                enabled: false,
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
        submitButtons: 'button[type="submit"]',
        
    })
    // Enable the password/confirm password validators if the password is not empty
    .on('keyup', '[name="password"]', function() {
        var isEmpty = $(this).val() == '';
        $('#formModifyUser')
                .bootstrapValidator('enableFieldValidators', 'password', !isEmpty)
                .bootstrapValidator('enableFieldValidators', 'confirm_password', !isEmpty);

        // Revalidate the field when user start typing in the password field
        if ($(this).val().length == 1) {
            $('#formModifyUser').bootstrapValidator('validateField', 'password')
                            .bootstrapValidator('validateField', 'confirm_password');
        }
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
              url: contextPath+"/administration/modifyUser",
              data: $form.serialize(),
              success: function(){
                displaySuccessAlert("El usuario se ha modificado correctamente");
                window.open (contextPath+'/j_spring_security_logout','_self',false);
              },
              error:function(){
                displayDangerAlert("Ha habido un error en la operacion");
              }
            });
        });;
}