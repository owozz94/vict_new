package com.vict.vict_new.join.service.serviceImpl;

import com.vict.vict_new.join.dao.JoinDao;
import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.service.JoinService;
import com.vict.vict_new.util.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final JoinDao dao;
    private final SHA256 sha256;

    @Override
    public int getEmailExist(String email) {
        return dao.getEmailExist(email);
    }

    @Override
    public int insertUser(User user) {
        //시퀀스
        int userSeq = dao.getUserSeq();
        user.setUserSeq(userSeq);
        //패스워드 암호화
        user.setSalt(sha256.getSalt());
        String password = user.getPassword();
        String encryption = sha256.Hashing(password.getBytes(), user.getSalt());
        user.setPassword(encryption);
        //기존에 존재하는 회원인지 확인하는 로직 필요
        //입력 데이터 검증.
        //
        //이메일/전화번호 중복 검사.
        //
        //이메일/전화번호 인증.
        //
        //패스워드 해싱 알고리즘 개선.
        //
        //CAPTCHA 또는 봇 방지.
        //
        //민감한 데이터 암호화.
        //
        //약관 및 동의 처리.
        //
        //로그 기록 및 감사 가능성.
        //
        //계정 상태 관리.


        return dao.insertUser(user);
    }
}
