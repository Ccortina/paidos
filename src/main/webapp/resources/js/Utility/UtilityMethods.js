/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function checkNotUndefined(value){
    if(typeof value === 'undefined'){
        return false;
    }else{
        return true;
    }
}

function replaceNull(value){
    if(typeof value === 'undefined' || value === '' || value === null){
        return '';
    }else{
        return value;
    }
}

function displayDangerAlert(message){
    var box = bootbox.alert("<center><span class='glyphicon glyphicon-remove'></span><strong> Error!</strong>"+message+"</center>");
    box.find('.modal-content').css({'color': '#b94a48','background-color': '#f2dede','border-color': '#eed3d7'});
    window.setTimeout(function(){bootbox.hideAll();}, 2000);
}

function displayWarningAlert(message){
    var box = bootbox.alert("<center><strong>Advertencia! </strong>"+message+"</center>");
    box.find('.modal-content').css({'color':'#8a6d3b','background-color':'#fcf8e3','border-color':'#fbeed5'});
    window.setTimeout(function(){bootbox.hideAll();}, 2000);
}

function displaySuccessAlert(message){
    var box = bootbox.alert("<center><span class='glyphicon glyphicon-ok'></span><strong> Exito! </strong>"+message+"</center>");
    box.find('.modal-content').css({'color':'#468847','background-color':'#dff0d8','border-color':'#d6e9c6'});
    window.setTimeout(function(){bootbox.hideAll();}, 2000);
}

