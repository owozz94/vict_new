package com.vict.vict_new.sms.service;

import com.vict.vict_new.sms.SmsRequestDto;
import org.springframework.http.ResponseEntity;

public interface SmsService {
    //cool sms 코드 전송
    void sendSMS(String phoneNum);
    // code 확인
    boolean verifyCode(String code);
}
