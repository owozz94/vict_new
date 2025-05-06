package com.vict.vict_new.sms.service.serviceImpl;

import com.vict.vict_new.sms.SmsRequestDto;
import com.vict.vict_new.sms.service.SmsService;
//import com.vict.vict_new.util.SmSCertificationUtil;
import com.vict.vict_new.util.SmSCertificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmSCertificationUtil util;

    private static SmsRequestDto smsDto;

    @Override
    public void sendSMS(String phoneNum) {
        smsDto = new SmsRequestDto();
        smsDto.setPhoneNum(phoneNum);
        String certificationCode = generateRandomNumber();
        smsDto.setSmsCode(certificationCode);
        util.sendSMS(phoneNum, smsDto.getSmsCode());

        System.out.println("request sms code ==> "+smsDto.getSmsCode());
    }

    @Override
    public boolean verifyCode(String code) {
        System.out.println("response sms code ==> "+code);
        boolean codeChk = false;
        if(code.equals(smsDto.getSmsCode())){
            codeChk = true;
        }else{
             codeChk = false;
        }
        return codeChk;
    }

    //랜덤한 4자리 생성
    private String generateRandomNumber(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
