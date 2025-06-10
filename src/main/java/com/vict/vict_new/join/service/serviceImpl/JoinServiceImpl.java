package com.vict.vict_new.join.service.serviceImpl;

import com.vict.vict_new.exception.UserRegistrationException;
import com.vict.vict_new.join.dao.JoinDao;
import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.dto.UserManage;
import com.vict.vict_new.join.service.JoinService;
import com.vict.vict_new.join.user.UserRole;
import com.vict.vict_new.util.SHA256;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        try {
            //패스워드 유효성 검사
            if(!isValidPassword(user.getPassword())){
                throw UserRegistrationException.invalidPasswordFormat(user.getPassword());
            }
            //기존에 존재하는 계정인지 여부
            int emailExist = dao.getEmailExist(user.getEmail());

            if(emailExist > 0){
                throw UserRegistrationException.emailAlreadyExists(user.getEmail());
            }else {
                try {
                    //시퀀스 생성
                    int userSeq = dao.getNewSeq();
                    user.setUserSeq(userSeq);
                    //패스워드 암호화
                    user.setSalt(sha256.getSalt());
                    String password = user.getPassword();
                    String encryption = sha256.Hashing(password.getBytes(), user.getSalt());
                    user.setPassword(encryption);
                    userManage.setUserSeq(user.getUserSeq());
                    userManage.setRole(UserRole.USER);
                    int insertUserManage = dao.insertUserManage(userManage); //UserManage 받는 부분 필요
                    int insertUser = dao.insertUser(user);
                    //위에 return 값 성공/실패시 검증 로직 필요.
                    if (insertUserManage <= 0 || insertUser <= 0) {
                        throw UserRegistrationException.userAlreadyExists();
                    }
                    return 1;
                }catch (Exception e){
                    log.error("User registration transaction failed. Cause: {}", e.getMessage(), e);
                    throw e; //트랜잭션 콜백
                }
            }
        } catch (Exception e) {
            throw UserRegistrationException.signupFailed();
        }
    }
}
