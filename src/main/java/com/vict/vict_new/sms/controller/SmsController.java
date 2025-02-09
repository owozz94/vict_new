package com.vict.vict_new.sms.controller;

import com.vict.vict_new.sms.SmsRequestDto;
import com.vict.vict_new.sms.service.SmsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sms")
public class SmsController {

    private final SmsService service;

    @PostMapping("/send")
    public ResponseEntity<?> SendSMS(@RequestBody @Valid SmsRequestDto dto){
        service.SendSMS(dto);
        return ResponseEntity.ok("문자를 전송했습니다.");

    }
}
