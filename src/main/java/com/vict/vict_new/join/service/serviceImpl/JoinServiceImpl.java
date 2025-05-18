package com.vict.vict_new.join.service.serviceImpl;

import com.vict.vict_new.custom.UserRegistrationException;
import com.vict.vict_new.join.dao.JoinDao;
import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.dto.UserManage;
import com.vict.vict_new.join.service.JoinService;
import com.vict.vict_new.util.SHA256;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final JoinDao dao;
    private final SHA256 sha256;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public int getEmailExist(String email) {
        return dao.getEmailExist(email);
    }

    public boolean isValidPassword(String password){
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }
    @Override
    @Transactional
    public int insertUser(User user) {

        UserManage userManage = new UserManage();

        if(!isValidPassword(user.getPassword())){
            throw new UserRegistrationException("Password does not meet complexity requirements.", HttpStatus.BAD_REQUEST);
        }
        //시퀀스
        int userSeq = dao.getUserSeq();
        user.setUserSeq(userSeq);

        //패스워드 암호화
        user.setSalt(sha256.getSalt());
        String password = user.getPassword();
        String encryption = sha256.Hashing(password.getBytes(), user.getSalt());
        user.setPassword(encryption);

        userManage.setUserSeq(user.getUserSeq());
        //기존에 존재하는 계정인지 여부
        int emailExist = dao.getEmailExist(user.getEmail());
        //한 개의 전화번호 인증으로 3개의 계정까지 생성 가능.
        int accountCount = dao.getAccountCount(user.getUserSeq());
        if(emailExist > 0){
            throw new UserRegistrationException("Email already exists", HttpStatus.CONFLICT); //리소스 요청이 충돌할때
        }else if(accountCount > 3){
            throw new UserRegistrationException("Account email limit reached for user", HttpStatus.CONFLICT);
        }else{
            if(emailExist == 0 && accountCount < 3){
                try {
                    int insertUserManage = dao.insertUserManage(userManage); //UserManage 받는 부분 필요
                    int insertUser = dao.insertUser(user);
                    //위에 return 값 성공/실패시 검증 로직 필요.
                    if(insertUserManage > 0 && insertUser > 0){
                        return 1;

                    }else {
                        log.error("Transaction falied. rollback");
                        throw new UserRegistrationException("User account fail", HttpStatus.CONFLICT);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    return 1;
    }
}
