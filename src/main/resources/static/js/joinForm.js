let validator;

$(document).ready(function(){
    formValidate();

    $('#phoneNum').on('input', function(){
        let input = $(this).val();
        //숫자만 남김
        input = input.replace(/[^0-9]/g, '');
        //전화번호 포맷 000-0000-0000
        if(input.length > 3 && input.length <= 7){
            input = input.substring(0, 3) + '-' + input.substring(3);
        }else if(input.length > 7){
            input = input.substring(0, 3) + '-' + input.substring(3, 7) + '-' + input.substring(7);
        }
        //13자리 제한
        if(input.length > 13){
            input = input.substr(0, 13);
        }
        $(this).val(input);
    })
})

$('#joinForm').on('keyup change', function(){
    console.log("joinForm keyup or change")
    if (!validator) return; // 안전장치
    const formValid = validator.checkForm();
    //핸드폰 인증 여부
    let phoneFlag = $('input[name=verificationPhoneNum]').val() == 'true';
    const checked = $('.items:checked').length > 2;
    console.log("formValid=>"+formValid);
    console.log("phoneFlag=>"+phoneFlag);
    console.log("checked=>"+checked);
    if(formValid && phoneFlag && checked){
    console.log("들어옴")
        $('.signup-btn').addClass('active').attr('disabled', false);
    }else{
    console.log("안들어옴")
        $('.signup-btn').removeClass('active').attr('disabled', true);
    }
})

function selectEmail(){
    var inputEmail = $('#email').val().split('@')[0]; //이메일
    var selectEmail = $('select[name=emailBox]').val();
    const domains = {
        '0': '@',
        '1': '@gmail.com',
        '2': '@naver.com',
        '3': '@kakao.com',
        '4': '@daum.net',
        '5': '@nate.com'
    }
    $('#email').val(inputEmail+domains[selectEmail]);
    $('#email').valid();
}

//이메일 정규식
function emailValidate(email){
    const pattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;
    if(pattern.test(email) == false){
        return false;
    }
        return true;
}

function allCheck(){
    if($("input[name=all-items]").is(":checked") === true){
        $(".items").prop("checked", true);
        $(".marketing-items").prop("checked", true);
        $(".marketing-items").val('Y');
    }else{
        $(".items").prop("checked", false);
        $(".marketing-items").prop("checked", false);
        $(".marketing-items").val('N');
    }
 }

function isCheck(){
    if(marketingAgreed = $("[name=marketingAgreed]").is(":checked") === true){
        $(".marketing-items").val('Y');
    }else{
        $(".marketing-items").val('N');
    }

    var chkCount = $('input:checkbox:checked').length;

    if(chkCount > 3 && $("input[name=all-items]").is(":checked") === false){
        $("input[name=all-items]").prop('checked', true);
    }else{
        $("input[name=all-items]").prop('checked', false);
    }
}

//폼 유효성 검사
function formValidate(){
    console.log('formValidate');
    const joinForm = $('#joinForm');
     // 커스텀 메소드 추가
    $.validator.addMethod("phoneFormat", function(value) {
        if(!value) return true;
        // 유효성 검사: 010-XXXX-XXXX 형식 확인
        return /^010-[0-9]{4}-[0-9]{4}$/.test(value);
    }, "연락처 형식이 맞지 않습니다.");
    $.validator.addMethod("passwordCheck", function(value){
        if(!value) return true;
        return /^(?=.*[A-Za-z])(?=.*[0-9]).{8,}/.test(value);
    }, "8자 이상 영문,숫자 조합");

    validator = joinForm.validate({
        rules:{
            email:{
                required:true,
                email:true
            },
            phoneNum:{
                required:true,
                phoneFormat:true,
            },
            nickname:{
                required:true,
                minlength:2
            },
            password:{
               passwordCheck:true,
               required:true,
               minlength: 8,
            },
            password_2:{
              passwordCheck:true,
              required:true,
              minlength: 8,
              equalTo:password
            },
            verificationCode:{
                required:true
            }
        },
        messages:{
            email:{
                required:'이메일은 필수 입력 항목입니다.',
                email: '올바른 이메일 형식으로 입력하세요.'
            },
            phoneNum:{
                required:'연락처는 필수 입력 항목입니다.',
                phoneFormat:'연락처 형식이 맞지 않습니다.',
            },
            nickname:{
                required:'닉네임은 필수 입력 항목입니다.',
                minlength : '최소 {0}글자 이상 입력하세요.'
            },
            password:{
                required:'비밀번호는 필수 입력 항목입니다.',
                minlength : '최소 {0}글자 이상 입력하세요.'
            },
            password_2:{
                required:'비밀번호는 필수 입력 항목입니다.',
                minlength : '최소 {0}글자 이상 입력하세요.',
                equalTo: '동일한 비밀번호를 입력해 주세요.'
           },
           verificationCode:{
                required:'인증번호를 입력해주세요.'
           }
        },

        errorPlacement: function(error, element){
          if(element.attr('name') == 'email'){
            error.insertAfter(".email-group");
          }else if(element.attr('name') == 'phoneNum'){
            error.insertAfter('.phone-group');
            $('.verification-request-btn').attr('disabled', true); // 버튼 비활성화
          }
          else{
            error.insertAfter(element);
          }
        },
        success: function(label, element) {
            // 유효성 검사가 성공하면 버튼 활성화
            if ($(element).attr('name') === 'phoneNum') {
                $('.verification-request-btn').attr('disabled', false); // 버튼 활성화
            }
        },
        submitHandler: function(form) {
            console.log("모든 유효성 검사 통과");
        }
    });

    const phoneInput = $('#phoneNum').val();
     if (!$.validator.methods.phoneFormat(phoneInput) ){
          $('.verification-request-btn').attr('disabled', true); // 버튼 비활성화
     } else {
//          $('.verification-request-btn').attr('disabled', false); // 버튼 활성화
     }
}

function isValidPhoneNumber() {
    var phoneNum =  $('#phoneNum').val();
    var phone = phoneNum.replace(/[^0-9]/g, ''); //숫자가 아닌 경우 빈값으로 바꿈.
    if(phone.length > 3 && phone.length <= 7) {
        phone = phone.substr(0,3) + '-' + phone.substr(3);
    } else if(phone.length > 7) {
        phone = phone.substr(0,3) + '-' + phone.substr(3,4) + '-' + phone.substr(7);
    }
    $('#phoneNum').val(phone);
    console.log(phone);
}


function verifySmsCode() {
var smsCode =  $('#verificationCode').val();
    if(smsCode == ''){
        return alert('인증번호를 입력해주세요.');
    }
    // 인증번호 확인
    $.ajax({
        type:"post",
        data:smsCode,
        contentType : "application/json",
        url:"/sms/verifyCode",
        success:function(result){
                alert('인증되었습니다.');
                $('#verificationCode').attr('disabled','true');
                $('.verification-confirm-btn').attr('disabled','true');
                $('input[name=verificationPhoneNum]').val(true);
                $('#joinForm').trigger('change');
        },
        error:function(xhr, status, err){
            if(xhr.status == 401){
                alert('인증번호를 다시 확인해주세요.');
            }
            else{
                alert('error!');
            }
        }
    })
}
async function sendVerificationCode() {
    isValidPhoneNumber();
    var phoneNum =  $('#phoneNum').val();
    if(phoneNum == ''){
        return alert('핸드폰 번호를 입력해주세요.');
    }
    //핸드폰 존재 여부 체크
    const isValid = await phoneCheck();

    if(isValid){
        alert("인증번호를 보냈습니다.");
        $('.phone-verification-group').show();
        $('.verification-request-btn').css('display', 'none');
        $('#phoneNum').attr('readonly','true');
        phoneNum = phoneNum.replace(/-/g,''); //'-' 제거

        // 인증번호 전송 로직
        $.ajax({
            type:"post",
            data:phoneNum,
            contentType: "application/json",
            url:"/sms/send",
            success:function(result){
                console.log(result);
                return result;
            }
        })
    }
}

async function phoneCheck(){
    //핸드폰번호 중복여부 체크
    var phoneNum =  $('#phoneNum').val();
    console.log('핸드폰번호 중복여부 체크');

    try{
    await $.ajax({
        type:"get",
        data:{phoneNum},
        url:"/join/phoneExists",
        success:function(result){
            console.log(result);
        }
        });
        return true;
        }catch(xhr){
            if (xhr.status == 409) {
                alert('이미 가입된 번호입니다.');
                return false;
            } else {
                alert('error!');
                return false;
            }
    }
}

function join(){
    const join_form = $('#joinForm').serializeArray();

    $.ajax({
        type:"post",
        data:join_form,
        url:"/join/signup",
        success:function(result){
            alert('회원가입이 완료되었습니다.');
            console.log(result);
            location.replace("/login");
        },
        error:function(xhr, status, err){
            if(xhr.status == 400){
                alert('잘못된 요청입니다.');
            }
            else if(xhr.status == 403){
                alert('인가되지 않은 접근입니다.');
            }
            else if(xhr.status == 409){
                alert('이미 존재하는 이메일입니다.');
            }
            else{
                alert('error!');
            }
        }
     });
}