package com.vict.vict_new.sms.controller;

import com.vict.vict_new.sms.SmsRequestDto;
import com.vict.vict_new.sms.service.SmsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sms")
public class SmsController {

    private final SmsService service;

    @PostMapping("/send")
    public ResponseEntity<?> SendSMS(@RequestBody @Valid String phoneNum){
        service.sendSMS(phoneNum);
        return ResponseEntity.ok("문자를 전송했습니다.");

    }

    @PostMapping("/verifyCode")
    public ResponseEntity<?> VerifySmsCode(@RequestBody String code){
        boolean codeChk = service.verifyCode(code);
        if(codeChk){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(codeChk);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(codeChk);
        }


    }
}
