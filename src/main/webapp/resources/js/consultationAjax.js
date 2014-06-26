/* 
* @Author Carlos Alfonso Cortina Arce
* @Email carlos.a.cortina@gmail.com
*/

var oTable;
var json;
var reload;

$(document).ready(function(){

        initializeSibilingsTable();
        
        $('.inputDecimal').inputmask('Regex',{regex:"[0-9]+(\.[0-9][0-9]?)?"});
        $('.inputDate').inputmask("dd/mm/yyyy");
        $('.inputNormal').inputmask('Regex',{regex:'[A-Za-z0-9-]{1}[" "A-Za-z0-9-]*'});
        $('.inputTextArea').inputmask('Regex',{regex:'[A-Za-z0-9-]{1}[\sA-Za-z0-9-\n]*'});
        
        $('.datepicker').datetimepicker({
            pickDate:true,
            pickTime:false,
            language:'es'
        });
});

function saveConsultation(){
    //Recolect data
    //The basic info from the consultation
    var data = [];
    data.push({name:'prescription',value:$("#consultationPrescription").val()});
    data.push({name:'peea',value:$("#peea").val()});
    data.push({name:'ef',value:$("#ef").val()});
    
    //Activity List
    data.push({name:'activitySize',value:$("#tblSelectedActivities").DataTable().rows().data().length});
    $("#tblSelectedActivities").DataTable().rows().data().each(function(value,index){
        data.push({name:'activity'+index,value:value["idActivity"]});
    });
    
    //The diagnostic(s)
    data.push({name:'diagnosticSize',value:$("#consultationDiagnosticsTable").DataTable().rows().data().length});
    
    $("#consultationDiagnosticsTable").DataTable().rows().data().each(function(value,index){


        if(value[1] === ""){    //Check if diagnostic is empty
            if(value[3] !== ""){    //Check if Medecine id empty
                if(value[4] === ""){    //Check if commercial name is empty
                    data.push({name:'idCIE10'+index,value:""});
                    data.push({name:'idTreatment'+index,value:""});
                    data.push({name:'idMedecine'+index,value:value[3]["idDrug"]});
                    data.push({name:'IdCommercialName'+index,value:""});
                }else{
                    data.push({name:'idCIE10'+index,value:""});
                    data.push({name:'idTreatment'+index,value:""});
                    data.push({name:'idMedecine'+index,value:value[3]["idDrug"]});
                    data.push({name:'IdCommercialName'+index,value:value[4]["idcommercialName"]});
                }
            }
        }else{
            if(value[3] === ""){
                data.push({name:'idCIE10'+index,value:value[1]["idCIE10"]});
                data.push({name:'idTreatment'+index,value:value[2][0]["idTreatment"]});
                data.push({name:'idMedecine'+index,value:""});
                data.push({name:'IdCommercialName'+index,value:""});
            }else{
                if(value[4] === ""){
                    data.push({name:'idCIE10'+index,value:value[1]["idCIE10"]});
                    data.push({name:'idTreatment'+index,value:value[2][0]["idTreatment"]});
                    data.push({name:'idMedecine'+index,value:value[3][0]["idDrug"]});
                    data.push({name:'IdCommercialName'+index,value:value[4]["idcommercialName"]});
                }else{
                    data.push({name:'idCIE10'+index,value:value[1]["idCIE10"]});
                    data.push({name:'idTreatment'+index,value:value[2][0]["idTreatment"]});
                    data.push({name:'idMedecine'+index,value:value[3][0]["idDrug"]});
                    data.push({name:'IdCommercialName'+index,value:""});
                }
            }
        }
    });
    
    
    //The basic information of the consultation
    //data.push($("#formConsultationBasicData").serializeArray());
    $("#formConsultationBasicData input").each(function (index,row){
        data.push({name:$(row).attr('name'),value:$(row).val()});
    });
    
    //the extra measures related to the consultation
    data.push({name:"measureSize",value:$("#tblMeasuresConsultation").DataTable().rows().data().length});
    
    $("#tblMeasuresConsultation").DataTable().rows().data().each(function(value,index){
       data.push({name:"measure"+index,value:value[3]});
       data.push({name:"mValue"+index,value:value[1]});
    });
    
    //console.log(data);
    $.ajax({
        url:"/demo/consultation/saveConsultation",
        data:data,
        type: "POST",
        error: function(data, status, error) {
        console.log(data +"///////---//// "+error + "/////---///" + status);
        }
    });
}

//Sibilings table
function initializeSibilingsTable(){

    $('#sibilingsTable').DataTable({
        "searching":false,
        "info":false,
        "ordering":false,
        "paging":false,
        "ajax":"/demo/consultation/getConsultationSibilings",
        "columns":[
            {"data":"fullName"}
        ],
        "initComplete":function(settings,json){
            $('#sibilingsTable tbody tr').dblclick(function(e){
                
                var idSibiling = $("#sibilingsTable").DataTable().row(this).data()["idPatient"]["id"];
                openSibilingFile(idSibiling);
            });
        }
    });
}


//Sibilings table
function initializeConsultationDiagnosticAbstractTable(){

    $('#tblConsultationDiagnosticAbstract').DataTable({
        "info":false,
        "ordering":false,
        "paging":false,
        "ajax":"/demo/consultation/getConsultationSibilings",
        "columns":[
            {"data":"fullName"}
        ],
        "initComplete":function(settings,json){
            $('#sibilingsTable tbody tr').dblclick(function(e){
                
                var idSibiling = $("#sibilingsTable").DataTable().row(this).data()["idPatient"]["id"];
                openSibilingFile(idSibiling);
            });
        }
    });
}

//This function converts a form in a json acceptedString
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
    console.log(o);
};

function getCurrentDate(){
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();

    if(dd<10) {
        dd='0'+dd;
    } 

    if(mm<10) {
        mm='0'+mm;
    } 

    today = mm+'/'+dd+'/'+yyyy;
    return today;
}

function openSibilingFile(idSibiling){
    var url= "/demo/patients/file/"+idSibiling;
    window.open(url);
}

//Makes an ajax Call wiht the id of the table
function ajaxCall(ID){
    $.ajax({
        url:$('#'+ID).attr("action"),
        data: $('#'+ID).serializeObject(),
        type:"POST",
        success:function(response){
                    if( response === "function"){
                        $('#tblActivities').DataTable().ajax.reload();
                    }else{
                        $('#ajaxMessage').html(response);
                    }
                    if( response === "ProgramVaccinesFinished"){
                        $("#tblConsultationPatientInmunization").DataTable().ajax.reload();
                    }
                }
     });	
}

function displayDangerAlert(message){
    var box = bootbox.alert("<center><strong>Advertencia!</strong>"+message+"</center>");
    box.find('.modal-content').css({'color': '#a94442','background-color': '#f2dede','border-color': '#ebccd1'});
    window.setTimeout(function(){bootbox.hideAll();}, 2000);
}