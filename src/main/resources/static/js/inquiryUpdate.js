	window.onload=function(){
        let inquiryType = $('input[name=inquiryType]').val();
        console.log(inquiryType);
        $("select[name=inquiryType] option[value="+inquiryType+"]").prop("selected", true);

    };


function goBack(){
    history.back();
}

function inquiry(){
    const customer_form = $('#customer_form').serializeArray();
    console.log("오냐.;")
    $.ajax({
        type:"post",
        data:customer_form,
        url:"/inquiry/update",
        success:function(result){
            alert("등록되었습니다.");
            location.replace("/inquiry/list");
        }

    })
}