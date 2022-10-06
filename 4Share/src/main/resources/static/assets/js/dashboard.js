 $(document).ready(function(){
   	  	// $('.bar').click(function(){
   	  	// 	$('#navigation').toggleClass("active");
   	  	// 	$('.mid').toggleClass("active");
   	  	
   	  	// });

        $('.pass_btn').click(function(){
            $('.popup').addClass('active');
        });

        $('.cross').click(function(){
            $('.popup').removeClass('active');
        });

        $('.btn-show-user').click(function(){
          
            var x=$("#user").attr('type');
            
            if(x==='password'){
                $('#user').attr('type','text');
                $('.btn-show-user').val('Hide');

                /*ball moving on clicking hide and show btn*/
                $('.c1').css({"left":"20%","top":"70%"});
                $('.c2').css({"left":"80%","top":"20%"});
                $('.c3').css({"left":"5%","top":"21%"});
                $('.c4').css({"left":"65%","top":"85%"});



            }else{
                 $('#user').attr('type','password');
                $('.btn-show-user').val('Show');

                 /*ball moving on clicking hide and show btn*/
                $('.c1').css({"left":"5%","top":"21%"});
                $('.c2').css({"left":"80%","top":"8%"});
                $('.c3').css({"left":"20%","top":"70%"});
                $('.c4').css({"left":"85%","top":"83%"});
            }
            
        });


        $('.btn-show-guest').click(function(){
          
            var x=$("#guest").attr('type');
            
            if(x==='password'){
                $('#guest').attr('type','text');
                $('.btn-show-guest').val('Hide');

                 /*ball moving on clicking hide and show btn*/
                $('.c1').css({"left":"20%","top":"70%"});
                $('.c2').css({"left":"5%","top":"21%"});
                $('.c3').css({"left":"80%","top":"20%"});
                $('.c4').css({"left":"65%","top":"85%"});


            }else{
                 $('#guest').attr('type','password');
                $('.btn-show-guest').val('Show');

                  /*ball moving on clicking hide and show btn*/
                $('.c1').css({"left":"5%","top":"21%"});
                $('.c2').css({"left":"80%","top":"8%"});
                $('.c3').css({"left":"20%","top":"70%"});
                $('.c4').css({"left":"85%","top":"83%"});
            }
            
        });

   	  });


