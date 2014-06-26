/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    initializeTableMeasuresCatalog();
    
    initializeTableMeasureConsultation();
});

function initializeTableMeasureConsultation(){
    $("#tblMeasuresConsultation").DataTable({
        "scrollY":"100px",
        "paging": false,
        "searching":false,
        "info":false,
        "language": {
            "emptyTable": "No hay informacion en la tabla."},
        "columns":[
            null,
            null,
            null,
            {"visible":false},
            {"visible":false}
        ],
        "initComplete":function(){
            var table = $("#tblMeasuresConsultation").DataTable();
            
            $("#tblMeasuresConsultation").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            });
        }
    });
}

function initializeTableMeasuresCatalog(){
    $("#tblMeasureCatalog").DataTable({
        "scrollY":"100px",
        "paging": false,
        "searching":false,
        "info":false,
        "ajax":"/demo/consultation/getMeasuresCatalog",
        "columns":[
            {"data":"measure"}
            ],
        "language": {
            "emptyTable": "No hay informacion en la tabla."},
        "initComplete":function(settings,json){
            var table = $("#tblMeasureCatalog").DataTable();
            
            $("#tblMeasureCatalog").on( 'click', 'tr', function (e){
                if ( $(this).hasClass('selected')){
                    $(this).removeClass('selected');
                }
                else
                {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                    $("#formMeasuresAdd input[name=idMeasure]").val(table.row(this).data()["idMeasures"]);
                    $("#formMeasuresAdd input[name=units]").val(table.row(this).data()["units"]);
                }
            });
        }
    });
}

function saveMeasureValue(){
    var row = $("#tblMeasureCatalog").DataTable().row('.selected').data(); 
    if(typeof row == "undefined"){
        displayDangerAlert(" No se ha seleccionado una medida");
    }else{
        if($("#formMeasuresAdd input[name=measureValue]").val() === ""){
            displayDangerAlert(" El valor no puede estar vacio");
        }else{
    
            $("#tblMeasuresConsultation").DataTable().row.add([
                $("#tblMeasureCatalog").DataTable().row('.selected').data()["measure"],
                $("#formMeasuresAdd input[name=measureValue]").val(),
                $("#tblMeasureCatalog").DataTable().row('.selected').data()["units"],
                $("#formMeasuresAdd input[name=idMeasure]").val(),
                $("#tblMeasureCatalog").DataTable().row('.selected').data()["includePrescription"]
            ]).draw();
            $("#modalMeasuresAdd").modal('hide');
        }
    }
}

function deleteMeasureConsultationRow(){
    var row = $("tblMeasuresConsultation").DataTable().row('.selected').data();
    if(typeof row === 'undefined'){
        displayDangerAlert(" No se ha seleccionado una medida para elminar.");
    }else{
        var index = $("#tblMeasuresConsultation").DataTable().row('.selected').index();
        $("#tblMeasuresConsultation").DataTable().row(index).remove().draw();
    }
}

function saveNewMeasure(){
    if($("#formMeasureNew input[name=measure]").val() === ""){
        displayDangerAlert(" El nombre de la medida no puede estar vacio.");
    }else{
        if($("#formMeasureNew input[name=units]").val() === ""){
            displayDangerAlert(" La unidad no puede estar vacio.");
        }else{
            $.ajax({
                url:"/demo/consultation/saveNewMeasure",
                data:$("#formMeasureNew").serializeObject(),
                type:"POST",
                success:function(response){
                    $("#tblMeasureCatalog").DataTable().ajax.reload();
                },
                error:function(jqXHR,textStatus,errorThrown){
                    displayDangerAlert("Hubo errores durante la operacion.\n"+textStatus);
                }        
            });
            $("#modalMeasuresInnerModal").modal('hide');
        }
    }
    
}