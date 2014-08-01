/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
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
    }else{
        displayWarningAlert("No ha seleccionado un familiar");
    }
}
