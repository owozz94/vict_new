	window.onload=function(){
        let inquiryType = $('input[name=inquiryType]').val();
        console.log(inquiryType);
        $("select[name=inquiryType] option[value="+inquiryType+"]").prop("selected", true);

    };