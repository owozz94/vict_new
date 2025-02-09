package com.vict.vict_new.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class SHA256 {
    private static final int SALT_SIZE = 16;
    //Salt값 생성
    public String getSalt(){
        SecureRandom random = new SecureRandom();
        byte[] temp = new byte[SALT_SIZE];
        random.nextBytes(temp);

        return Byte_to_String(temp);
    }
    //바이트 값을 16진수로 변경
    private String Byte_to_String(byte[] temp) {
        StringBuilder sb = new StringBuilder();
        for(byte a : temp){
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }
    public String Hashing(byte[] password, String salt){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            for(int i = 0; i < 10000; i ++){
                String temp = Byte_to_String(password) + salt; //패스워드와 salt를 합쳐 새로운 문자열 생성
                md.update(temp.getBytes()); //temp의 문자열을 해싱해 md에 저장
                password = md.digest(); //md 객체의 다이제스트를 얻어 password  갱신
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Byte_to_String(password);
    }
}
