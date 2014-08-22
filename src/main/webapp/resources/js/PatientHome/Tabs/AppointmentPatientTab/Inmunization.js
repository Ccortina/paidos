/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
});

function initializePatientInmunizationAppTable(){
        
    $("#tblPatientInmunizationApp").DataTable({
        "ordering":false,
        "scrollY": "400px",
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{url:"/demo/patients/getPatientProgrammedVaccine",
            data:function(){
               return {idPatient:$("#inputIdPatientApp").val()} 
            }},
        "columns":[
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
                    return getAge(full.patient.birthday,full.applicationDate);
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
            var table = $('#tblConsultationPatientInmunization').DataTable();
            
            $('#tblConsultationPatientInmunization tbody').on( 'click', 'tr', function (e) {
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

//This method returns the age of the patient in days months and years
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

