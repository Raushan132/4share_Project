 $(document).ready(function(){
        $('#password').keyup(function(){
          var value= $('#password').val();
          var charcter=value.charAt(0);
          if(charcter=='a' || charcter=='A') $('#username').val("ADMIN");
          else if(charcter=='u' || charcter=='U') $('#username').val("USER");
          else if(charcter=='g' || charcter=='G') $('#username').val("GUEST");
          else $('#username').val("GUEST");
        });
		$(".error").fadeOut(3000);
		$(".logout").fadeOut(3000);
    });