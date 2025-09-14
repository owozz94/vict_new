function isChecked(){
    let checked = $('#cs_agree').is(':checked');
    let inquiry_type = $('select[name=inquiryType]').val();
    /*if(!checked){
        alert("개인정보 이용약관에 동의해주세요.");
        return false;
    }*/
    if(inquiry_type == ''){
        alert("문의유형을 선택해주세요.");
        return false;
    }
}
function goBack(){
    history.back();
}

function inquiry(){
    const inquiry_form = $('#inquiry_form').serializeArray();
    if(isChecked() == false){
         return false;
    }
    console.log("문의 완료");

    $.ajax({
        type:"post",
        data:inquiry_form,
        url:"/inquiry/insert",
        success:function(result){
            alert("등록되었습니다.");
            location.replace("/inquiry/list");
        }

    })
}
