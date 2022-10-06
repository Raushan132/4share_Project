$(document).ready(function(){

 $('.btn-show-user').click(function(){
          
            var x=$("#user").attr('type');
            
            if(x==='password'){
                $('#user').attr('type','text');
                $('.btn-show-user').val('Hide');




            }else{
                 $('#user').attr('type','password');
                $('.btn-show-user').val('Show');

               
            }
            
        });


        $('.btn-show-guest').click(function(){
          
            var x=$("#guest").attr('type');
            
            if(x==='password'){
                $('#guest').attr('type','text');
                $('.btn-show-guest').val('Hide');



            }else{
                 $('#guest').attr('type','password');
                $('.btn-show-guest').val('Show');

            }
            
        });


});