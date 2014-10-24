/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeCPTable();
});

function initializeCPTable(){
    
    $('#tblPayment').DataTable({
        "scrollY": "300px",
        "order":[0,'desc'],
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
            url:"/demo/income/getConsultationPayment"
        },
        "columns":[
            {"render":function(row, data, dataIndex){
                return moment(dataIndex.date).format("DD/MM/YYYY");
            }},
            {"render":function(row, data, dataIndex){
                return dataIndex.patient.firstName+" "+dataIndex.patient.fatherLastName+
                        " "+dataIndex.patient.motherLastName;
            }},
            {"data":"paymentTotal"},
            {"data":"change"},
            {"data":"note"},
            {"data":"idPaymentType.type"}
        ],
        "initComplete":function(settings,json){

            $('#tblPayment tbody').on( 'click', 'tr', function (e) {
                var table = $('#tblPayment').DataTable();
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }else{
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }   
            });
        },
        "createdRow": function( row, data, dataIndex ) {
            if(data.idPaymentType.idConsultationPaymentType === 3){
                $(row).css({'background-color':'#FF2A4A'});//Cancelled
                $(row).addClass("vpExpired");
            }
        }
    });
}

function showDetails(){

    var row = $('#tblPayment').DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        $("#formPayment [name='cash']").val(row.cash);
        $("#formPayment [name='check']").val(row.check);
        $("#formPayment [name='card']").val(row.card);
        $("#formPayment [name='other']").val(row.other);
        $("#formPayment [name='checkD']").val(row.checkDigits);
        $("#formPayment [name='cardD']").val(row.cardDigits);
        $("#formPayment [name='otherD']").val(row.otherDescription);
        $("#formPayment [name='paymentTotal']").val(row.paymentTotal);
        $("#formPayment [name='change']").val(row.change);
        $("#formPayment [name='notes']").val(row.note);
        
        switch(row.idPaymentType.idPaymentType){
            case '1':
                $("#paymentTypeTitle").text("Liquidacion");
                break;
            case '2':
                $("#paymentTypeTitle").text("Pago Parcial");
                break;
            case '3':
                $("#paymentTypeTitle").text("Cancelado");
                break;
        }
        
        $("#paymentModal").modal("show");
    }else{
        displayWarningAlert("No se ha seleccionado un pago");
    }
}

function cancel(){
    var row = $("#tblPayment").DataTable().row('.selected').data();
    
    if(checkNotUndefined(row)){
        bootbox.confirm("Esta seguro de cancelar este pago?", function(result) {
            if(result){
                var data =[];
                data.push({name:"payment",value:row["idConsultationPayment"]});
                $.ajax({
                    url:"/demo/income/cancelPayment",
                    data:data,
                    success:function(response){
                        $("#tblPayment").DataTable().ajax.reload();
                    },
                    error:function(){

                    }
                });
                
            }
        });
    }else{
        displayWarningAlert("No se ha seleccionado un pago");
    }
    
}
