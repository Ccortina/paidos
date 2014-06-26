/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
    addSaveOnChangeToForm();
});

function addSaveOnChangeToForm(){
    $( ".onChange" ).change(function() {
        ajaxCall($(this).closest('form').attr('id'));
        
    });
}




