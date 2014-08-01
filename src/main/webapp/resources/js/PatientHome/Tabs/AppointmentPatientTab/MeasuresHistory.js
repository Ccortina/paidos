/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
 
    $('a[href="#tabMeasures"]').on('shown.bs.tab', function (e) {
        $('#tblMeasuresHistory').DataTable().columns.adjust().draw();
    });
});

function initializeMeasuresHistoryTable(){
    
    if ( !$.fn.DataTable.isDataTable( '#tblMeasuresHistory' ) ) {
        $('#tblMeasuresHistory').DataTable({
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
                        console.log(full['size']);
                    return(((full['weigth']/full['size'])/full['size'])*10000);
                }}
            ]});
        var table =$('#tblMeasuresHistory').DataTable();

        var table =$('#tblMeasuresHistory').DataTable();
        table.clear();
        $('#tblConsultationHistory').DataTable().rows().data().each(function(entry,row) {
            table.row.add(entry).draw();
        });
    }else{
        var table =$('#tblMeasuresHistory').DataTable();
        table.clear();
        $('#tblConsultationHistory').DataTable().rows().data().each(function(entry,row) {
            table.row.add(entry).draw();
        });
    }
    
}
