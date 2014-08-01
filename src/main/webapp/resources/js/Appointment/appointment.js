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
       slotEventOverlap:false,
       header:{
           left:   'month agendaWeek agendaDay',
           center: 'title',
           right:  'today prev,next'
       },
       events: function(start,end,timezone,callback){
           
           $.ajax({
               url:'./appointment/getAppointments',
               dataType:'json',
               data:{
                   start:start.toISOString(),
                   end:end.toISOString(),
                   timezone:timezone
               },
               success:function(doc){
                   var events = [];
                   $(doc["aaData"]).each(function(){
                       console.log(this);
                      events.push({
                          title: this.idPatient.firstName+" "+this.idPatient.fatherLastName,
                          start:this.date+"T"+this.startTime+"-06:00",
                          end:this.date+"T"+this.endTime+"-06:00",
                          description:this.motive
                      });
                   });
                   callback(events);
               }
           });
       },
       eventRender:function(event,element){
           element.context.textContent = element.context.textContent+"\tMotivo: " + event.description;     
       }
   });
});


