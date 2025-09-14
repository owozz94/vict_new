package com.vict.vict_new.util;

import com.vict.vict_new.exception.UserRegistrationException;
import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmSCertificationUtil {
    @Value("${coolsms.apiKey}")
    private String apiKey;

    @Value("${coolsms.apiSecret}")
    private String apiSecret;

    @Value("${coolsms.fromNumber}")
    private String fromNumber;

    DefaultMessageService messageService; //메세지 서비스를 위한 객체
    @PostConstruct //의존성 주입이 완료된 후 초기화를 수행
    public void init(){
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr"); //메세지 서비스 초기화
    }

    //단일 메시지 발송
    public void sendSMS(String to, String certificationCode){
        Message message = new Message();
        message.setFrom(fromNumber); //발신자 번호 설정
        message.setTo(to); //수신자 번호 설정
        message.setText("[vict] 본인확인 인증번호 : " +certificationCode); //메시지 내용
        try {
            //this.messageService.sendOne(new SingleMessageSendingRequest(message)); //메시지 발송 요청
        }catch (UserRegistrationException ux){
            ux.sendSMSCodeFailed();
        }

    }


}
