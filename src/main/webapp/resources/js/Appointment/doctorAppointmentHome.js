/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
   $('#calendar').fullCalendar({
       defaultView:'agendaDay',
       allDaySlot: false,
       timezone:'Mexico/General',
       minTime: '07:00:00',
       maxTime: '20:00:00',
       header:{
           left:   'month agendaWeek agendaDay',
           center: 'title',
           right:  'today prev,next'
       },
       events: function(start,end,timezone,callback){
           
           $.ajax({
               url:'/demo/appointment/getDoctorAppointmentsList',
               dataType:'JSON',
               data:{
                   start:start.toISOString(),
                   end:end.toISOString(),
                   timezone:timezone,
               },
               success:function(doc){
                   var events = [];
                   $(doc["aaData"]).each(function(){
                      events.push({
                          id:this.idAppointment,
                          title: ","+this.idPatient.firstName+" "+this.idPatient.fatherLastName+",",
                          start:this.date+"T"+this.startTime+"-06:00",
                          end:this.date+"T"+this.endTime+"-06:00",
                          description:this.motive,
                          url:"/demo/consultation/"+this.idAppointment,
                          color:colorBasedOnStatus(this.idStatus.idAppointmentStatus)
                      });
                   });
                   callback(events);
               },
               error: function(error) {
                console.log(error);
                console.log('there was an error while fetching events!');
               }
           });
       },
       eventClick: function(event) {
           
           bootbox.confirm("Quiere ir a la consulta?", function(result) {
                        if(result){
                            window.location.href = event.url;
                        }
                    });
           return (false);
       },
       eventRender:function(event,element){
           element.context.textContent = element.context.textContent+"Motivo: " + event.description;
           //element.find('span.fc-event-title').html(element.find('span.fc-event-title').text("<br/>"));		
       }
   });
   
   $('.inputDate').inputmask("dd/mm/yyyy",{"oncleared": function(){
                                                $("#"+$(this).closest("form").attr('id')).data('bootstrapValidator').revalidateField($(this).attr('name'));
                                            }});
});

function colorBasedOnStatus(status){
   switch(status){
        case 1:
            return 'rgb(221, 221, 221)';
            break;
        case 2:
            return 'rgb(58, 135, 173)';
            break;
        case 3:
            return 'rgb(91, 170, 58)';
            break;
        case 4,5,6,7,10:
            return 'rgb(237, 237, 59)';
            break;
        case 8,11:
            return 'rgb(224, 71, 71)';
            break;
        case 9:
            return 'rgb(65, 204, 187)';
            break;
        default:
           return 'rgb(237, 237, 59)';
   }
}


