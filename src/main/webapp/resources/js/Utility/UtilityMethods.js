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
    window.setTimeout(function(){bootbox.hideAll();}, 1000);
}


function clearFormInputTextFields(form){
    var inputs = $("#"+form+" :input");
    
    $.each(inputs,function( index, value ){
        $(value).val("");
    });
}

function getAge(fromdate, todate){
    if(todate) todate= new Date(todate);
    else todate= new Date();

    var age= [], fromdate= new Date(fromdate),
    y= [todate.getFullYear(), fromdate.getFullYear()],
    ydiff= y[0]-y[1],
    m= [todate.getMonth(), fromdate.getMonth()],
    mdiff= m[0]-m[1],
    d= [todate.getDate(), fromdate.getDate()],
    ddiff= d[0]-d[1];

    if(mdiff < 0 || (mdiff=== 0 && ddiff<0))--ydiff;
    if(mdiff<0) mdiff+= 12;
    if(ddiff<0){
        fromdate.setMonth(m[1]+1, 0);
        ddiff= fromdate.getDate()-d[1]+d[0];
        --mdiff;
    }
    if(mdiff> 0){ 
        age.push(mdiff+ ' M ');
    }else{
        age.push('0 M ');
    }
    if(ddiff> 0){
        age.push(ddiff+ ' D ');
    }else{
        age.push(ddiff+ '0 D ');
    }
    if(ydiff> 0){
        age.push(ydiff+ ' A ');
    }else{
        age.push('0 A ');
    }
      
    return age.join('');
}

function checkNotEmptyString(value){
    if(value === ""){
        return false;
    }else{
        return true;
    }
}
