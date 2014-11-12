/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){

    $(".monitorOP").on("change",function(){
        var res = $(this).prop("name").split("_");
        
        if($(this).prop("checked")){
            permissionsSum[res[0]] = parseInt(permissionsSum[res[0]]) + parseInt(res[1]);
        }else{
            permissionsSum[res[0]] -= res[1];
        }
        //console.log(permissionsSum[res[0]]);
    });
    
    $(".moduleMonitor").on("change",function(){
       
        //var res = $(this).prop('name').split("_");
        var name = $(this).prop('name');
        
        if($(this).prop("checked")){
            //The module is activated
            permissionsSum[name] = 1;
            $("#panelBody"+name+" :input").each(function(){
                $(this).prop("disabled",false);
            });
        }else{
            //The module is deactivated
            permissionsSum[name] = 0;
            $("#panelBody"+name+" :input").each(function(){
                $(this).prop("disabled",true);
            });
        }
    });
    
});

function savePermissions(){
    var data = [];
    
    $.each(permissionsSum, function( index, value ) {
        if(checkNotUndefined(index) && checkNotUndefined(value)){
            data.push({name:index,value:value});
        }
    });
    
    
    $('#loadingmessage').show();  // show the loading message
    $.ajax({url: contextPath+"/administration/updatePermissions",
            type: "POST",
            data: data,
            success:function(){
                $('#loadingmessage').hide();  
                displaySuccessAlert("Se han actualizado los permisos correctamente");
            },
            error:function(){
                $('#loadingmessage').hide();
                displayDangerAlert("Ha habido un error en la operacion");
            }
        });
}

function checkModulePermission( name , value){
    if(parseInt(value) === 1){
        $("#formPermissions [name='"+name+"']").prop("checked",true);
    }else{
        $("#formPermissions [name='"+name+"']").prop("checked",false);
        $("#panelBody"+name+" :input").each(function(){
            $(this).prop("disabled",true);
        });
    }
}

function setCurrentPermissions( name, number){
    switch(number){
        case 0:
            $("#formPermissions [name='"+name+"_1']").prop("checked",false);
            $("#formPermissions [name='"+name+"_2']").prop("checked",false);
            $("#formPermissions [name='"+name+"_4']").prop("checked",false);
            $("#formPermissions [name='"+name+"_8']").prop("checked",false);    
            break;
        case 1:
            $("#formPermissions [name='"+name+"_1']").prop("checked",true);
            $("#formPermissions [name='"+name+"_2']").prop("checked",false);
            $("#formPermissions [name='"+name+"_4']").prop("checked",false);
            $("#formPermissions [name='"+name+"_8']").prop("checked",false);
            break;
        case 2:
            $("#formPermissions [name='"+name+"_1']").prop("checked",false);
            $("#formPermissions [name='"+name+"_2']").prop("checked",true);
            $("#formPermissions [name='"+name+"_4']").prop("checked",false);
            $("#formPermissions [name='"+name+"_8']").prop("checked",false);
            break;
        case 3:
            $("#formPermissions [name='"+name+"_1']").prop("checked",true);
            $("#formPermissions [name='"+name+"_2']").prop("checked",true);
            $("#formPermissions [name='"+name+"_4']").prop("checked",false);
            $("#formPermissions [name='"+name+"_8']").prop("checked",false);
            break;
        case 4:
            $("#formPermissions [name='"+name+"_1']").prop("checked",false);
            $("#formPermissions [name='"+name+"_2']").prop("checked",false);
            $("#formPermissions [name='"+name+"_4']").prop("checked",true);
            $("#formPermissions [name='"+name+"_8']").prop("checked",false);
            break;
        case 5:
            $("#formPermissions [name='"+name+"_1']").prop("checked",true);
            $("#formPermissions [name='"+name+"_2']").prop("checked",false);
            $("#formPermissions [name='"+name+"_4']").prop("checked",true);
            $("#formPermissions [name='"+name+"_8']").prop("checked",false);
            break;
        case 6:
            $("#formPermissions [name='"+name+"_1']").prop("checked",false);
            $("#formPermissions [name='"+name+"_2']").prop("checked",true);
            $("#formPermissions [name='"+name+"_4']").prop("checked",true);
            $("#formPermissions [name='"+name+"_8']").prop("checked",false);
            break;
        case 7:
            $("#formPermissions [name='"+name+"_1']").prop("checked",true);
            $("#formPermissions [name='"+name+"_2']").prop("checked",true);
            $("#formPermissions [name='"+name+"_4']").prop("checked",true);
            $("#formPermissions [name='"+name+"_8']").prop("checked",false);
            break;
        case 8:
            $("#formPermissions [name='"+name+"_1']").prop("checked",false);
            $("#formPermissions [name='"+name+"_2']").prop("checked",false);
            $("#formPermissions [name='"+name+"_4']").prop("checked",false);
            $("#formPermissions [name='"+name+"_8']").prop("checked",true);
            break;
        case 9:
            $("#formPermissions [name='"+name+"_1']").prop("checked",true);
            $("#formPermissions [name='"+name+"_2']").prop("checked",false);
            $("#formPermissions [name='"+name+"_4']").prop("checked",false);
            $("#formPermissions [name='"+name+"_8']").prop("checked",true);
            break;
        case 10:
            $("#formPermissions [name='"+name+"_1']").prop("checked",false);
            $("#formPermissions [name='"+name+"_2']").prop("checked",true);
            $("#formPermissions [name='"+name+"_4']").prop("checked",false);
            $("#formPermissions [name='"+name+"_8']").prop("checked",true);
            break;
        case 11:
            $("#formPermissions [name='"+name+"_1']").prop("checked",true);
            $("#formPermissions [name='"+name+"_2']").prop("checked",true);
            $("#formPermissions [name='"+name+"_4']").prop("checked",false);
            $("#formPermissions [name='"+name+"_8']").prop("checked",true);
            break;    
        case 12:
            $("#formPermissions [name='"+name+"_1']").prop("checked",false);
            $("#formPermissions [name='"+name+"_2']").prop("checked",false);
            $("#formPermissions [name='"+name+"_4']").prop("checked",true);
            $("#formPermissions [name='"+name+"_8']").prop("checked",true);
            break;
        case 13:
            $("#formPermissions [name='"+name+"_1']").prop("checked",true);
            $("#formPermissions [name='"+name+"_2']").prop("checked",false);
            $("#formPermissions [name='"+name+"_4']").prop("checked",true);
            $("#formPermissions [name='"+name+"_8']").prop("checked",true);
            break;
        case 14:
            $("#formPermissions [name='"+name+"_1']").prop("checked",false);
            $("#formPermissions [name='"+name+"_2']").prop("checked",true);
            $("#formPermissions [name='"+name+"_4']").prop("checked",true);
            $("#formPermissions [name='"+name+"_8']").prop("checked",true);
            break;
        case 15:
            $("#formPermissions [name='"+name+"_1']").prop("checked",true);
            $("#formPermissions [name='"+name+"_2']").prop("checked",true);
            $("#formPermissions [name='"+name+"_4']").prop("checked",true);
            $("#formPermissions [name='"+name+"_8']").prop("checked",true);
            break;
    }
}


