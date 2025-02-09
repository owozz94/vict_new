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

        return dao.insertUser(user);
    }
}
