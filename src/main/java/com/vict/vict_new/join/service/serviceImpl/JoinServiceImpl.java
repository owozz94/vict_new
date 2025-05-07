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
        //기존에 존재하는 계정인지 여부
        int emailExist = dao.getEmailExist(user.getEmail());
        //한 개의 전화번호 인증으로 3개의 계정까지 생성 가능.
        int accountCount = dao.getAccountCount(user.getUserSeq());
        if(emailExist > 0){
            return 0;
        }else if(accountCount > 3){
            return -1;
        }else{
            if(emailExist == 0 && accountCount < 3){
                int insertUserManage = dao.insertUserManage(userManage); //UserManage 받는 부분 필요
                int insertUser = dao.insertUser(user);
                //위에 return 값 성공/실패시 검증 로직 필요.
            }
        }

    }
}
