package com.vict.vict_new.sms.service.serviceImpl;

import com.vict.vict_new.sms.SmsRequestDto;
import com.vict.vict_new.sms.service.SmsService;
import com.vict.vict_new.util.SmSCertificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmSCertificationUtil util;

    @Override
    public void SendSMS(SmsRequestDto dto) {
        String phoneNum = dto.getPhoneNum();
        String certificationCode = generateRandomNumber();
        util.sendSMS(phoneNum, certificationCode);
    }
    //랜덤한 4자리 생성
    private String generateRandomNumber(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
