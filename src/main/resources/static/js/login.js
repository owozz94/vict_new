
function login(){
   const loginForm = $('#loginForm').serializeArray();
      $.ajax({
           type:'post',
           url:'/login',
           data:loginForm,
           success:function(){
             alert("성공! 로그인");
        }
      })
}

//폼 유효성 검사
function formValidate(){
    const loginForm = $('#loginForm');
    loginForm.validate({
        rules:{
            email:{
                required:true,
                email:true
            },
            pwd:{
               required:true,
               minlength: 8
            }
        },
        messages:{
            email:{
                required:'이메일을 입력해주세요.',
                email: '올바른 이메일 형식으로 입력하세요.'
            },
            pwd:{
                required:'비밀번호를 입력해주세요.',
                minlength : '최소 {0}글자 이상 입력하세요.'
            }
        },
    });
}
//네이버 로그인
function naverLogin(){
    $.ajax({
        type:'post',
        url:'/naverLogin',
        success:function(result){
            alert("성공!");
        }
    })
}