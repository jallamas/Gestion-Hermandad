$(function(){

  $(document).on('keyup','#pwd1, #pwd2',function(){
    var pwd1 = $('#pwd1').val().trim();
    var pwd2 = $('#pwd2').val().trim();
    if( !pwd1 || !pwd2 || pwd1 == '' || pwd2 == '' ){
      $('#pwd').removeClass('text-success').addClass('text-danger').text('Las contraseñas no coinciden');
    }
    
    else{
      if( pwd1 !== pwd2 ){
        $('#pwd').removeClass('text-success').addClass('text-danger').text('Las contraseñas no coinciden');
      }
      
      else{
      $('#pwd').removeClass('text-danger').addClass('text-success').text('Las contraseñas coinciden');
      }
    }
  });
});