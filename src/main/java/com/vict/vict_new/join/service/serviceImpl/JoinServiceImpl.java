package com.vict.vict_new.join.service.serviceImpl;

import com.vict.vict_new.exception.UserRegistrationException;
import com.vict.vict_new.join.dao.JoinDao;
import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.dto.UserManage;
import com.vict.vict_new.join.service.JoinService;
import com.vict.vict_new.join.user.UserRole;
import com.vict.vict_new.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final JoinDao dao;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public int getEmailExist(String email) {
        int emailExist = dao.getEmailExist(email);
        if(emailExist > 0){
            throw UserRegistrationException.userEmailAlreadyExists(email);
        }
        return emailExist;
    }

    @Override
    public int getPhoneExists(String phoneNum) {
        int phoneExists = dao.getPhoneExist(phoneNum);

        if(phoneExists > 0){
            throw UserRegistrationException.userPhoneNumAlreadyExists(phoneNum);
        }
        return phoneExists;
    }

    public boolean isValidPassword(String password){
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }
    @Override
    @Transactional
    public int insertUser(User user, UserManage userManage) {

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
                    //유저키 생성
                    String userKey = PasswordUtil.generateUserKey();
                    user.setUserKey(userKey);
                    //패스워드 암호화
                    user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
                    userManage.setUserKey(user.getUserKey());
                    userManage.setRole(UserRole.USER);
                    int existPhoneNum = dao.getPhoneExist(user.getPhoneNum());
                    int insertUserManage = dao.insertUserManage(userManage);
                    int insertUser = dao.insertUser(user);
                    //위에 return 값 성공/실패시 검증 로직 필요.
                    if (existPhoneNum > 0){
                        throw UserRegistrationException.userPhoneNumAlreadyExists(user.getPhoneNum());
                    }
                    else if (insertUserManage <= 0 || insertUser <= 0) {
                        throw UserRegistrationException.userAlreadyExists(user);
                    }
                    return 1;
                }catch (Exception e){
                    log.error("User registration transaction failed. Cause: {}", e.getMessage(), e);
                    throw UserRegistrationException.signupFailed(); //트랜잭션 콜백
                }
            }
    }
}
