/* 
* @Author Carlos Alfonso Cortina Arce
* @Email carlos.a.cortina@gmail.com
*/

$(document).ready(function(){

        initializeSibilingsTable();
        
        $('.inputDecimal').inputmask('Regex',{regex:"[0-9]+(\.[0-9][0-9]?)?"});
        $('.inputDate').inputmask("dd/mm/yyyy");
        $('.inputNormal').inputmask('Regex',{regex:'[A-Za-z0-9-]{1}[" "A-Za-z0-9-αινσϊρ]*'});
        $('.inputTextArea').inputmask('Regex',{regex:'[A-Za-z0-9-]{1}[" "A-Za-z0-9-\n\\.,αινσϊρ\/\t]*'});
        
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
        data.push({name:'activityPrice'+index,value:value["activityCost"]});
        data.push({name:'activityInclude'+index,value:value["includeInBill"]});
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
                data.push({name:'idCIE10'+index,value:value[1]["cie10"]["idCIE10"]});
                data.push({name:'idTreatment'+index,value:value[2]["idTreatment"]});
                data.push({name:'idMedecine'+index,value:""});
                data.push({name:'IdCommercialName'+index,value:""});
            }else{
                if(value[4] === ""){
                    data.push({name:'idCIE10'+index,value:value[1]["cie10"]["idCIE10"]});
                    data.push({name:'idTreatment'+index,value:value[2]["idTreatment"]});
                    data.push({name:'idMedecine'+index,value:value[3]["idDrug"]});
                    data.push({name:'IdCommercialName'+index,value:""});
                }else{
                    data.push({name:'idCIE10'+index,value:value[1]["cie10"]["idCIE10"]});
                    data.push({name:'idTreatment'+index,value:value[2]["idTreatment"]});
                    data.push({name:'idMedecine'+index,value:value[3]["idDrug"]});
                    data.push({name:'IdCommercialName'+index,value:value[4]["idcommercialName"]});
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
        "ajax":"/demo/consultation/getConsultationSibilings2",
        "columns":[
            {"render":function(data,row,full){
                    return (full["firstName"]+" "+full["fatherLastName"]+" "+full["motherLastName"]);
            }},
            {"render":function(data,row,full){
                    return getAge(full["birthday"],new Date());
            }}
        ],
        "initComplete":function(settings,json){
            var table = $('#sibilingsTable').DataTable();

            $('#sibilingsTable tbody').on( 'click', 'tr', function (e) {
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

function openSibilingFile(){
    var url= "/demo/patients/file/"+$("#sibilingsTable").DataTable().row('.selected').data()["idPatient"];
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

function displayWarningAlert(message){
    var box = bootbox.alert("<center><strong>Advertencia! </strong>"+message+"</center>");
    box.find('.modal-content').css({'color':'#8a6d3b','background-color':'#fcf8e3','border-color':'#faebcc'});
    window.setTimeout(function(){bootbox.hideAll();}, 2000);
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