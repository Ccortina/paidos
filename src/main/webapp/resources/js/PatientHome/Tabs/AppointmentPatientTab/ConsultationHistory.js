/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){

    $('a[href="#tabConsults"]').on('shown.bs.tab', function (e) {
        $('#tblConsultationHistory').DataTable().columns.adjust().draw();
    });
});

function initializeConsultationHistoryTable(){
    $('#tblConsultationHistory').DataTable({
        "scrollY": "300px",
        "order":[0,'desc'],
        "scrollCollapse": true,
        "paging": false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar"
        },
        "ajax":{
            url:"/demo/patients/getConsultationsByPatient",
            data:{patient:function(){return $("#inputIdPatientApp").val();}}
        },
        "columns":[
            {"render":function(data,row,full){
                    return (moment(full['idAppointment']['date']).format('DD/MM/YYYY'));
            }},
            {"data":"abstract1"},
            {"data":"idAppointment.idStatus.status"}
        ],
        "initComplete":function(settings,json){

            $('#tblConsultationHistory tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblConsultationHistory').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    loadConsultationMeasures();
                    loadConsultationActivities();
                }   
            });
            initializeMeasuresHistoryTable();
        },
        "createdRow": function( row, data, dataIndex ) {
            switch(data.idAppointment.idStatus.status){
                case 'Completa':
                    $(row).css({'background-color':'#5BAA3A'});//vpNormal
                    $(row).addClass("vpNormal");
                    break;
                case 'Cancelada':
                    $(row).css({'background-color':'#E04747'});
                    $(row).addClass("vpExpired");
                    break;    
            }
        }
    });
}

function loadConsultationMeasures(){

    var data = $('#tblConsultationHistory').DataTable().row('.selected').data();
    if(checkNotUndefined(data)){
        if ( !$.fn.DataTable.isDataTable( '#tblConsultationHistoryMeasures' ) ) {
            $('#tblConsultationHistoryMeasures').DataTable({
                "scrollY": "300px",
                "order":[0,'desc'],
                "scrollCollapse": true,
                "paging": false,
                "info":false,
                "language": {
                    "emptyTable": "No hay informacion en la tabla.",
                    "search": "Buscar"
                },
                "columns":[
                    {"render":function(data,row,full){
                            return (moment(full['idAppointment']['date']).format('DD/MM/YYYY'));
                    }},
                    {"render":function(data,row,full){ 
                            return ( getAge(full['idAppointment']['idPatient']['birthday']));
                    }},
                    {"data":"weigth"},
                    {"data":"size"},
                    {"data":"pc"},
                    {"render":function(data,row,full){
                            return (full['ta']+"/"+full['ta2']+"-"+full['ta']/full['ta2']);
                    }},
                    {"data":"temperature"},
                    {"render":function(data,row,full){
                        return(((full['weigth']/full['size'])/full['size'])*10000);
                    }}
                ]});
            var table =$('#tblConsultationHistoryMeasures').DataTable();
            table.row.add(data).draw();
        }else{
            var table =$('#tblConsultationHistoryMeasures').DataTable();
            table.clear();
            table.row.add(data).draw();
        }
    }
}

function loadConsultationActivities(){

    var activities = $('#tblConsultationHistory').DataTable().row('.selected').data()['consultationactivityList'];
    
    if(checkNotUndefined(activities)){
        if ( !$.fn.DataTable.isDataTable( '#tblConsultationHistoryActivities' ) ) {
            $('#tblConsultationHistoryActivities').DataTable({
                "scrollY": "300px",
                "order":[0,'desc'],
                "scrollCollapse": true,
                "paging": false,
                "info":false,
                "language": {
                    "emptyTable": "No hay informacion en la tabla.",
                    "search": "Buscar"
                },
                "columns":[
                    {"data":"activity.activity"},
                    {"data":"cost"},
                    {"render":function(data,type,row){ 
                        if(row['includeInBill'] === 1){
                            return ('<span class="glyphicon glyphicon-ok"></span>');
                        }else{
                            return ('<span class="glyphicon glyphicon-remove"></span>');
                        }
                    }}
                ]});
            var table =$('#tblConsultationHistoryActivities').DataTable();
            activities.forEach(function(entry) {
                table.row.add(entry).draw();
            });
            
            
        }else{
            var table =$('#tblConsultationHistoryActivities').DataTable();
            table.clear();
            activities.forEach(function(entry) {
                table.row.add(entry).draw();
            });
        }
    }
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
