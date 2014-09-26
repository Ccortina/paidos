/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeCCATable();
});

function initializeCCATable(){
    
    $('#tblConsultationCostAbstract').DataTable({
        "scrollY": "300px",
        //"order":[0,'desc'],
        "deferRender": true,
        "scrollCollapse": true,
        "paging": true,
        "info": true,
        "language": {
            "emptyTable": "No hay informacion en la tabla.",
            "search": "Buscar",
            "lengthMenu": "Mostrar _MENU_ resultados por pagina",
             "paginate": {
                  "next": "Siguiente",
                  "previous": "Anterior"
              }
        },
        "ajax":{
            url:"/demo/income/getConsultationCostAbstract"
        },
        "columns":[
            {"render":function(row, data, dataIndex){
                return moment(dataIndex.consultationDate).format("DD/MM/YYYY");
            }},
            {"data":"consultationPatient.fatherLastName"},
            {"data":"consultationPatient.motherLastName"},
            {"data":"consultationPatient.firstName"},
            {"data":"idConsultationPaymentStatus.estatus"},
            {"data":"total"},
            {"data":"rest"}
        ],
        "initComplete":function(settings,json){

            $('#tblConsultationCostAbstract tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblConsultationCostAbstract').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }   
            });
        },
        "createdRow": function( row, data, dataIndex ) {
            console.log(data.idConsultationPaymentStatus);
            switch(data.idConsultationPaymentStatus.idConsultationPaymentEstatus){
                case 1:
                    $(row).css({'background-color':'#5BAA3A'});//vpNormal
                    $(row).addClass("vpSuspended");
                    break;
                case 2:
                    $(row).css({'background-color':'#5BAA3A'});
                    $(row).addClass("vpSuspended");
                    break;
                case 3:
                    $(row).css({'background-color':'#77fda0'});
                    $(row).addClass("vpNormal");
                    break;   
            }
        }
    });
}

