package com.vict.vict_new.sms.service;

import com.vict.vict_new.sms.SmsRequestDto;
import org.springframework.http.ResponseEntity;

public interface SmsService {

    void SendSMS(SmsRequestDto dto);
}
